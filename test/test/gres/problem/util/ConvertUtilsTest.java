package test.gres.problem.util;

import gres.problem.util.ConvertUtils;
import static org.junit.Assert.*;
import org.junit.Test;

public class ConvertUtilsTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTryingToConvertANullArrayToDoubleArray() {
		ConvertUtils.toDoubleArray(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShouldThrowAnExceptionWhenTryingToConvertANullArrayToIntegerArray() {
		ConvertUtils.toIntArray(null);
	}
	
	@Test(expected = NumberFormatException.class)
	public void testShouldThrowAnExceptionWhenTryingToConvertAnArrayWithCharacterToDoubleArray() {
		ConvertUtils.toDoubleArray(new String[]{"1","a"});
	}
	
	@Test(expected = NumberFormatException.class)
	public void testShouldThrowAnExceptionWhenTryingToConvertAnArrayWithCharacterToIntArray() {
		ConvertUtils.toIntArray(new String[]{"1","a"});
	}
	
	@Test
	public void testShouldToReturnAEmptyArrayWhenAnEmptyArrayBeSubmited() {
		assertEquals(0, ConvertUtils.toIntArray(new String[] {}).length);
		assertEquals(0, ConvertUtils.toDoubleArray(new String[] {}).length);
	}

	@Test
	public void testShouldToReturnAnIntArrayWhenBeSubmited() {
		int[] expected = new int[] { 1, 2, 3, 4, 5 };
		int[] result = ConvertUtils.toIntArray(new String[] { "1", "2", "3", "4", "5" });
		
		assertEquals(expected.length, result.length);
		
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i]);
		}
	}
	
	@Test
	public void testShouldToReturnADoubleArrayWhenBeSubmited() {
		double[] expected = new double[] { 1.9, 2.3, -0.4, 9.5, 5.0 };		
		double[] result = ConvertUtils.toDoubleArray(new String[] { "1.9", "2.3", "-0.4", "9.5", "5" });
		
		assertEquals(expected.length, result.length);
		
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], result[i], 0);
		}
	}
}
