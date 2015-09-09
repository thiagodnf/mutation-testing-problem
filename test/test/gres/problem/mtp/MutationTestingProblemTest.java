package test.gres.problem.mtp;

import static org.junit.Assert.assertEquals;
import gres.problem.mtp.MutationTestingProblem;
import org.junit.Test;

public class MutationTestingProblemTest {
	
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
	
//	@Test
//	public void testShouldReturnOneWhenAllGenesOfSolutionIsOne() {
//		MTPProblem problem = new MTPProblem();
//		problem.setNumberOfTestCases(5);
//		assertEquals(1, problem.getTestCaseScore(new int[] { 1, 1, 1, 1, 1 }), 0);
//		assertEquals(1, problem.getPairWiseScore(new int[] { 1, 1, 1, 1, 1 }), 0);
//		assertEquals(1, problem.getMutationScore(new int[] { 1, 1, 1, 1, 1 }), 0);
//	}
	
//	@Test
//	public void testShouldReturnRightSumWhenAllGenesOfSolutionIsOne() {
//		MTPProblem problem = new MTPProblem();
//		problem.setNumberOfTestCases(5);
//		assertEquals(1, problem.getTestCaseScore(new int[] { 1, 1, 1, 1, 1 }), 0);
//	}
}
