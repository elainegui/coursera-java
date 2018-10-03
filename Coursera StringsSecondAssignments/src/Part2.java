
public class Part2 {
	public static int howMany(String stringa, String stringb) {
		// how many times stringa appears in stringb
		// stringa must not overlap
		int indexStart = 0;
		int currIndex = 0;
		int indexNext = 0;
		int count = 0;
		while (true) {
			indexStart = stringb.indexOf(stringa, currIndex);
			System.out.println("indexStart " + indexStart);
			if (indexStart == -1 || indexStart == 0) {
				break;
			}
			count++;
			currIndex = indexStart + stringa.length();
			System.out.println("next iteration");
		}
		return count;
	}

	public static void testHowMany() {
		String stringa = "GAA";
		String stringb = "ATGAACGAATTGAATC";
		System.out.println(stringa + ", " + stringb);
		System.out.println("how many: " + howMany(stringa, stringb));

		stringa = "AA";
		stringb = "ATAAAA";
		System.out.println(stringa + ", " + stringb);
		System.out.println("how many: " + howMany(stringa, stringb));

		stringa = "XX";
		stringb = "ATAAAA";
		System.out.println(stringa + ", " + stringb);
		System.out.println("how many: " + howMany(stringa, stringb));

		stringa = "";
		stringb = "ATAAAA";
		System.out.println(stringa + ", " + stringb);
		System.out.println("how many: " + howMany(stringa, stringb));

		stringa = "AA";
		stringb = "";
		System.out.println(stringa + ", " + stringb);
		System.out.println("how many: " + howMany(stringa, stringb));

		stringa = "";
		stringb = "";
		System.out.println(stringa + ", " + stringb);
		System.out.println("how many: " + howMany(stringa, stringb));
	}

	public static void main(String[] args) {
		testHowMany();
	}
}
