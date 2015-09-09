package gres.problem.mtp;

import gres.problem.util.InstanceReader;

public class MutationTestingProblem {
	
	protected String instance;
	
	protected int numberOfTestCases;
	
	protected int numberOfMutants;
	
	protected int numberOfPairWises;
	
	protected int[][] mutantsCoverage;
	
	protected int[][] pairWiseCoverage;
	
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
		
		reader.close();
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
		
		return 0.0;
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
