
public class ExerciseDebuggingPart1 {

	public static void findAbc(String input) {
		int index = input.indexOf("abc");
		System.out.println("indexBefore "+ index);
		while (true) {
			if (index == -1 || index >= input.length() - 3) {
				break;
			}
			System.out.println("indexAfter "+index);
			String found = input.substring(index + 1, index + 4);
			System.out.println(found);
			index = input.indexOf("abc", index + 3);
			// System.out.println("index+1= "+(index+1));
			// System.out.println("index+4="+(index+4));
		}
	}

	public static void test() {
		//findAbc("abcd");
		// findAbc("abcdabcx");
		//findAbc("abcdabc");
		// findAbc("aabcxaaabc");
		//findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
		findAbc("abcabcabcabca");
	}

	public static void main(String[] args) {
		test();

	}

}
