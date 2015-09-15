package gres.problem.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JaccardCoefficient {

	public static double similarity(Integer[] x, Integer[] y) {
		if ((x == null || y == null)) {
			throw new IllegalArgumentException(
					"The arguments x and y must be not NULL");
		}

		return similarity(Arrays.asList(x), Arrays.asList(y));
	}

	public static double similarity(List<Integer> x, List<Integer> y) {
		if (x.size() == 0 && y.size() == 0) {
			return 1.0;
		}

		Set<Integer> unionXY = new HashSet<Integer>(x);
		unionXY.addAll(y);

		Set<Integer> intersectionXY = new HashSet<Integer>(x);
		intersectionXY.retainAll(y);

		return (double) intersectionXY.size() / (double) unionXY.size();
	}

	public static double dissimilarity(List<Integer> x, List<Integer> y) {
		return 1.0 - similarity(x, y);
	}

	public static double dissimilarity(Integer[] x, Integer[] y) {
		return 1.0 - similarity(x, y);
	}
}
