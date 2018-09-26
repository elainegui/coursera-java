
import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {

	static long getMaxPairwiseProductFast(long[] numbers) {
		int index1 = 0;
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] > numbers[index1]) {
				index1 = i;
			}
		}

		int index2 = (index1 == 0) ? 1 : 0;
		for (int i = 1; i < numbers.length; i++) {
			if (i != index1 && numbers[i] > numbers[index2]) {
				index2 = i;
			}
		}

		return numbers[index1] * numbers[index2];
	}

	public static void main(String[] args) {
		/*
		 * FastScanner scanner = new FastScanner(System.in); int n = scanner.nextInt();
		 * long[] numbers = new long[n]; for (int i = 0; i < n; i++) { numbers[i] =
		 * scanner.nextInt(); } System.out.println(getMaxPairwiseProductFast(numbers));
		 */

		long[] A = new long[2 * 10 ^ 5 + 1];
		for (int i = 0; i < (2 * 10 ^ 5); i++) {
			A[i] = i;
			System.out.println(getMaxPairwiseProductFast(A));
		}
	}

	static class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		FastScanner(InputStream stream) {
			try {
				br = new BufferedReader(new InputStreamReader(stream));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}
	}

}