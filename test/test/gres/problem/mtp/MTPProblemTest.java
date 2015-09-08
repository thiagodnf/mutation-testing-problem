package test.gres.problem.mtp;

import static org.junit.Assert.assertEquals;
import gres.problem.mtp.MTPProblem;
import org.junit.Test;

public class MTPProblemTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheInstanceNameIsNull() {
		new MTPProblem(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheInstanceNameIsEmpty() {
		new MTPProblem("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheSolutionIsNullOnMutationScore() {
		new MTPProblem().getMutationScore(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheSolutionIsEmptyOnMutationScore() {
		new MTPProblem().getMutationScore(new int[]{});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheSolutionIsNullOnTestCaseScore() {
		new MTPProblem().getTestCaseScore(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheSolutionIsEmptyOnTestCaseScore() {
		new MTPProblem().getTestCaseScore(new int[]{});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheSolutionIsNullOnPairWiseScore() {
		new MTPProblem().getPairWiseScore(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheSolutionIsEmptyOnPairWiseScore() {
		new MTPProblem().getPairWiseScore(new int[]{});
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheSolutionSizeIsWrongOnMutationScore() {
		new MTPProblem().getMutationScore(new int[]{0});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheSolutionSizeIsWrongOnPairWiseScore() {
		new MTPProblem().getPairWiseScore(new int[]{0});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTheSolutionSizeIsWrongOnTestCaseScore() {
		new MTPProblem().getTestCaseScore(new int[]{0});
	}	
	
	@Test
	public void testShouldReturnZeroWhenAllGenesOfSolutionIsZero() {
		MTPProblem problem = new MTPProblem();
		
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
