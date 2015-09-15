package test.gres.problem.mtp;

import static org.junit.Assert.assertEquals;
import gres.problem.mtp.MutationTestingProblem;
import org.junit.Test;

public class MutationTestingProblemTest {
	
	protected int[][] pairWiseCoverage = {
		{1, 0 ,1 ,1 ,1},
		{1, 0 ,0 ,1 ,0},
		{0, 1 ,0 ,1 ,0},
		{1, 1 ,0 ,1 ,0},
		{0, 0 ,1 ,1 ,1}
	};
	
	protected int[][] mutantsCoverage = {
		{0, 1 ,0 ,1 ,0},
		{0, 1 ,0 ,0 ,0},
		{1, 0 ,0 ,1 ,1},
		{1, 1 ,1 ,0 ,1},
		{1, 0 ,1 ,1 ,1}
	};
		
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheInstanceNameIsNull() {
		new MutationTestingProblem(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheInstanceNameIsEmpty() {
		new MutationTestingProblem("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheSolutionIsNullOnMutationScore() {
		new MutationTestingProblem().getMutationScore(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheSolutionIsEmptyOnMutationScore() {
		new MutationTestingProblem().getMutationScore(new int[]{});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheSolutionIsNullOnTestCaseScore() {
		new MutationTestingProblem().getTestCaseScore(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheSolutionIsEmptyOnTestCaseScore() {
		new MutationTestingProblem().getTestCaseScore(new int[]{});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheSolutionIsNullOnPairWiseScore() {
		new MutationTestingProblem().getPairWiseScore(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheSolutionIsEmptyOnPairWiseScore() {
		new MutationTestingProblem().getPairWiseScore(new int[]{});
	}	
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheSolutionSizeIsWrongOnMutationScore() {
		new MutationTestingProblem().getMutationScore(new int[]{0});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheSolutionSizeIsWrongOnPairWiseScore() {
		new MutationTestingProblem().getPairWiseScore(new int[]{0});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheSolutionSizeIsWrongOnTestCaseScore() {
		new MutationTestingProblem().getTestCaseScore(new int[]{0});
	}	
	
	@Test
	public void testShouldReturnZeroWhenAllGenesOfSolutionIsZero() {
		MutationTestingProblem problem = new MutationTestingProblem();
		
		problem.setNumberOfTestCases(5);		
		problem.setNumberOfMutants(5);
		problem.setNumberOfPairWises(5);
		
		assertEquals(0, problem.getTestCaseScore(new int[] { 0, 0, 0, 0, 0 }), 0);		
		assertEquals(0, problem.getPairWiseScore(new int[] { 0, 0, 0, 0, 0 }), 0);
		assertEquals(0, problem.getMutationScore(new int[] { 0, 0, 0, 0, 0 }), 0);
	}
	
	@Test
	public void testShouldReturnOneOnMutationScore() {
		MutationTestingProblem problem = new MutationTestingProblem();
		
		problem.setNumberOfTestCases(5);
		problem.setNumberOfMutants(5);
		
		problem.setMutantsCoverage(mutantsCoverage);
		
		assertEquals(1, problem.getMutationScore(new int[] { 1, 1, 1, 0, 0 }), 0);
		assertEquals(1, problem.getMutationScore(new int[] { 1, 1, 0, 0, 0 }), 0);
		assertEquals(1, problem.getMutationScore(new int[] { 0, 1, 0, 0, 1 }), 0);
	}
	
	@Test
	public void testShouldReturnOneForAllFitnessFunctionWhenAllTestCaseIsSelected() {
		MutationTestingProblem problem = new MutationTestingProblem();
		
		problem.setNumberOfTestCases(5);		
		problem.setNumberOfMutants(5);
		problem.setNumberOfPairWises(5);
		
		problem.setMutantsCoverage(mutantsCoverage);
		problem.setPairWiseCoverage(pairWiseCoverage);
		
		assertEquals(1, problem.getTestCaseScore(new int[] { 1, 1, 1, 1, 1 }), 0);
		assertEquals(1, problem.getMutationScore(new int[] { 1, 1, 1, 1, 1 }), 0);
		assertEquals(1, problem.getPairWiseScore(new int[] { 1, 1, 1, 1, 1 }), 0);
	}
	
	@Test
	public void testShouldReturnAValidValue() {
		MutationTestingProblem problem = new MutationTestingProblem();
		
		problem.setNumberOfTestCases(5);		
		problem.setNumberOfMutants(5);
		problem.setNumberOfPairWises(5);
		
		problem.setMutantsCoverage(mutantsCoverage);
		problem.setPairWiseCoverage(pairWiseCoverage);
		
		assertEquals(2.0 / 5.0, problem.getSimilarity(3, 4, pairWiseCoverage), 0);
		assertEquals(2.0 / 5.0, problem.getSimilarity(4, 3, pairWiseCoverage), 0);
		assertEquals(2.0 / 5.0, problem.getSimilarity(2, 3, pairWiseCoverage), 0);
		assertEquals(2.0 / 5.0, problem.getSimilarity(3, 2, pairWiseCoverage), 0);
		assertEquals(1.0 / 4.0, problem.getSimilarity(0, 4, pairWiseCoverage), 0);
		assertEquals(1.0 / 4.0, problem.getSimilarity(4, 0, pairWiseCoverage), 0);
	}
	
	@Test
	public void testShouldReturnAValidValueForSimilarityScore() {
		MutationTestingProblem problem = new MutationTestingProblem();
		
		problem.setNumberOfTestCases(5);		
		
		int[][] featuresCoverage = new int[][]{
			{1,1,1,0},
			{0,0,1,1},
			{0,0,0,1},
		};
		
		double[][] similarity = new double[4][4];
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (i != j) {
					similarity[i][j] = problem.getSimilarity(i, j, featuresCoverage);
					similarity[j][i] = similarity[i][j];
				}
			}
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				double v1 = problem.getSimilarity(i, j, featuresCoverage);
				double v2 = problem.getSimilarity(j, i, featuresCoverage);
				assertEquals(v1, v2, 0);
				assertEquals(v2, v1, 0);
			}
		}
				
		assertEquals(1.0 / 1.0, problem.getSimilarity(0, 1, featuresCoverage), 0);
		assertEquals(1.0 / 1.0, problem.getSimilarity(1, 0, featuresCoverage), 0);
		assertEquals(1.0 / 2.0, problem.getSimilarity(2, 0, featuresCoverage), 0);
		assertEquals(1.0 / 2.0, problem.getSimilarity(0, 2, featuresCoverage), 0);
		assertEquals(1.0 / 3.0, problem.getSimilarity(2, 3, featuresCoverage), 0);
		assertEquals(1.0 / 3.0, problem.getSimilarity(3, 2, featuresCoverage), 0);
		
		assertEquals(0, problem.getSimilarityScore(new int[] { 1, 0, 0, 1 }, similarity), 0);
		assertEquals(1.0/3.0, problem.getSimilarityScore(new int[] { 0, 0, 1, 1 }, similarity), 0);
		assertEquals(((1.0/2.0)+0+(1.0/3.0))/3,problem.getSimilarityScore(new int[] { 1, 0, 1, 1 }, similarity), 0);
		assertEquals(((1.0/2.0)+0+(1.0/3.0))/3,problem.getSimilarityScore(new int[] { 0, 1, 1, 1 }, similarity), 0);
		assertEquals((1.0+0.0+0.0)/3.0,problem.getSimilarityScore(new int[] { 1, 1, 0, 1 }, similarity), 0);
	}
}
