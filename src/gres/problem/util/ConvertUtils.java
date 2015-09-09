package gres.problem.util;

/**
 * ConvertUtils Class
 * 
 * @author Thiago Nascimento
 * @version 1.0
 * @since 2013-07-23
 */
public class ConvertUtils {
	
	/**
	 * Convert from string array to double array
	 * @param array Array will be converted
	 * 
	 * @return A double array
	 */
	public static double[] toDoubleArray(String[] array){
		if (array == null) {
			throw new IllegalArgumentException("array shouldn't be null");
		}
		
		double[] result = new double[array.length];
		
		for (int i = 0; i < array.length; i++) {
			result[i] = Double.valueOf(array[i].trim());
		}
		
		return result;
	}
	
	/**
	 * Convert from string array to int array
	 * 
	 * @param array Array will be converted
	 * @return A int array
	 */
	public static int[] toIntArray(String[] array){
		if (array == null) {
			throw new IllegalArgumentException("array shouldn't be null");
		}
		
		int[] result = new int[array.length];
		
		for (int i = 0; i < array.length; i++) {
			result[i] = Integer.valueOf(array[i].trim());
		}
		
		return result;
	}	
}
