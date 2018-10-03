
import java.util.*;
import java.io.*;

public class Other {
	static Scanner scanner = new Scanner(System.in);

	static int getMaxPairwiseProduct(int[] numbers) {
		int max_product = 0;
		//int n = scanner.nextInt();
		int n = numbers.length;

		/* int j = 0; */
		// for (int num : numbers) {
		//
		// System.out.println(num);
		// num++;
		// }
		// for (int i = 1; i < n; i++) {
		// System.out.println(i);
		// }
		/* if (i != j) { */
		for (int first = 0; first < n; ++first) {

			for (int second = first + 1; second < n; ++second) {

				if (first != second) {
					max_product = Math.max(max_product, numbers[first] * numbers[second]);
				}
			}
			/* } */
			/*
			 * } }
			 */}
		return max_product;
	}

	public static void main(String[] args) {
		int n = scanner.nextInt();
		int[] numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = scanner.nextInt();
		}
		System.out.println(getMaxPairwiseProduct(numbers));
	}

}
