package gres.problem.mtp;

import gres.problem.util.InstanceReader;
import gres.problem.util.JaccardCoefficient;

import java.util.ArrayList;
import java.util.List;

public class MutationTestingProblem {
	
	protected String instance;
	
	protected int numberOfTestCases;
	
	protected int numberOfMutants;
	
	protected int numberOfPairWises;
	
	protected int numberOfFeatures;
	
	protected String[][] features;
	
	protected int[][] mutantsCoverage;
	
	protected int[][] pairWiseCoverage;
	
	protected int[][] featuresCoverage;
	
	protected double[][] similarityAmongMutants;

	protected double[][] similarityAmongPairWises;
	
	protected double[][] similarityAmongFeatures;
	
	public MutationTestingProblem(){
		
	}
	
	public MutationTestingProblem(String instance) {
		if (instance == null || instance.isEmpty()) {
			throw new IllegalArgumentException("Instance's name cannot be null or empty");
		}
		
		this.instance = instance;
		
		InstanceReader reader = new InstanceReader(instance);
		
		reader.open();
		
		this.numberOfTestCases = reader.readInt();
		this.numberOfMutants = reader.readInt();
		this.mutantsCoverage = reader.readIntMatrix(numberOfMutants, numberOfTestCases, " ");
		this.numberOfPairWises = reader.readInt();
		this.pairWiseCoverage = reader.readIntMatrix(numberOfPairWises, numberOfTestCases, " ");
		this.numberOfFeatures = reader.readInt();
		this.features = reader.readStringMatrix(numberOfFeatures, numberOfTestCases, " ");
		this.featuresCoverage = reader.readIntMatrix(numberOfFeatures, numberOfTestCases, " ");
		
		reader.close();
		
		this.similarityAmongMutants = new double[numberOfTestCases][numberOfTestCases];
		this.similarityAmongPairWises = new double[numberOfTestCases][numberOfTestCases];
		this.similarityAmongFeatures = new double[numberOfTestCases][numberOfTestCases];
		
		for (int i = 0; i < numberOfTestCases; i++) {
			for (int j = i; j < numberOfTestCases; j++) {
				if (i != j) {
					this.similarityAmongMutants[i][j] = getSimilarity(i, j, mutantsCoverage);
					this.similarityAmongMutants[j][i] = similarityAmongMutants[i][j];
					this.similarityAmongPairWises[i][j] = getSimilarity(i, j, pairWiseCoverage);
					this.similarityAmongPairWises[j][i] = similarityAmongPairWises[i][j];
					this.similarityAmongFeatures[i][j] = getSimilarity(i, j, featuresCoverage);
					this.similarityAmongFeatures[j][i] = similarityAmongFeatures[i][j];
				}
			}
		}
		
		
	}
	
	public double getSimilarity(int i, int j, int[][] matrix) {
		List<Integer> m1 = new ArrayList<Integer>();
		List<Integer> m2 = new ArrayList<Integer>();

		for (int lin = 0; lin < matrix.length; lin++) {
			if (matrix[lin][i] == 1) {
				m1.add(lin);
			}
			if (matrix[lin][j] == 1) {
				m2.add(lin);
			}
		}

		return JaccardCoefficient.similarity(m1, m2);
	}
	
	public double getTestCaseScore(int[] solution) {
		if (solution == null || solution.length == 0) {
			throw new IllegalArgumentException("Solution cannot be null or empty");
		}
		
		if (solution.length != numberOfTestCases) {
			throw new IllegalArgumentException("Size of solution cannot be different of number of test case");
		}

		int selectedTestCases = 0;

		for (int i = 0; i < solution.length; i++) {
			if (solution[i] == 1) {
				selectedTestCases++;
			}
		}

		return (double) selectedTestCases / (double) numberOfTestCases;
	}
	
	public double getMutationScore(int[] solution) {
		if (solution == null || solution.length == 0) {
			throw new IllegalArgumentException("Solution cannot be null or empty");
		}
		
		if (solution.length != numberOfTestCases) {
			throw new IllegalArgumentException("Size of solution cannot be different of number of test case");
		}

		int[] visited = new int[numberOfMutants];
		int killedMutants = 0;

		for (int i = 0; i < solution.length; i++) {
			if (solution[i] == 1) {
				// Test case has been selected
				for (int j = 0; j < numberOfMutants; j++) {
					if (mutantsCoverage[j][i] == 1 && visited[j] == 0) {
						// Mutant has not yet been visited
						visited[j] = 1;
						killedMutants++;
					}
				}
			}
		}

		return (double) killedMutants / (double) numberOfMutants;
	}
	
	public double getPairWiseScore(int[] solution){
		if (solution == null || solution.length == 0) {
			throw new IllegalArgumentException("Solution cannot be null or empty");
		}
		
		if (solution.length != numberOfTestCases) {
			throw new IllegalArgumentException("Size of solution cannot be different of number of test case");
		}
		
		int[] visited = new int[numberOfMutants];
		int visitedPairs = 0;

		for (int i = 0; i < solution.length; i++) {
			if (solution[i] == 1) {
				// Test case has been selected
				for (int j = 0; j < numberOfPairWises; j++) {
					if (pairWiseCoverage[j][i] == 1 && visited[j] == 0) {
						visited[j] = 1;
						visitedPairs++;
					}
				}
			}
		}
		
		return (double) visitedPairs / (double) numberOfPairWises;
	}
	
	public double getSimilarityScore(int[] solution, double[][] matrix) {
		double sum = 0.0;
		double count = 0.0;
		
		for (int i = 0; i < solution.length; i++) {
			if (solution[i] == 1) {
				for (int j = 0; j < solution.length; j++) {
					if (solution[j] == 1) {
						if (i != j) {
							sum += matrix[i][j];
							count++;
						}
					}
				}
			}
		}
		
		return sum / count;
	}
	
	public double getSimilarityScoreAmongMutants(int[] solution) {
		return getSimilarityScore(solution, similarityAmongMutants);
	}
	
	public double getSimilarityScoreAmongPairWises(int[] solution) {
		return getSimilarityScore(solution, similarityAmongPairWises);
	}
	
	public double getSimilarityScoreAmongFeatures(int[] solution) {
		return getSimilarityScore(solution, similarityAmongFeatures);
	}

	public String getInstance() {
		return instance;
	}

	public int getNumberOfTestCases() {
		return numberOfTestCases;
	}

	public void setNumberOfTestCases(int numberOfTestCases) {
		this.numberOfTestCases = numberOfTestCases;
	}

	public int getNumberOfMutants() {
		return numberOfMutants;
	}

	public void setNumberOfMutants(int numberOfMutants) {
		this.numberOfMutants = numberOfMutants;
	}

	public int getNumberOfPairWises() {
		return numberOfPairWises;
	}

	public void setNumberOfPairWises(int numberOfPairWises) {
		this.numberOfPairWises = numberOfPairWises;
	}

	public int[][] getMutantsCoverage() {
		return mutantsCoverage;
	}

	public void setMutantsCoverage(int[][] mutantsCoverage) {
		this.mutantsCoverage = mutantsCoverage;
	}

	public int[][] getPairWiseCoverage() {
		return pairWiseCoverage;
	}

	public void setPairWiseCoverage(int[][] pairWiseCoverage) {
		this.pairWiseCoverage = pairWiseCoverage;
	}
}
