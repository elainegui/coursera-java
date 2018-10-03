
public class Part3 {
	public static boolean twoOccurrences(String stringa, String stringb) {
		// return true if stringa appears at least 2x in stringb
		if (stringb.contains(stringa)) {
			System.out.println(stringb.contains(stringa));
			int indexStringb = stringb.indexOf(stringa);
			if (stringb.indexOf(stringa, indexStringb + 1) != -1) {
				return true;
			}
		}
		return false;
	}

	public static String lastPart(String stringa, String stringb) {
		if (stringb.contains(stringa)) {
			return stringb.substring(stringb.lastIndexOf(stringa));
		} else
			return stringb;
	}

	public static void testing() {
		// return true
		System.out.println("\"a\", \"banana\"");
		System.out.println(twoOccurrences("a", "banana"));
		// return true
		System.out.println("\"by\", \"A Story by Abby\"");
		System.out.println(twoOccurrences("by", "A Story by Abby"));
		// return false
		System.out.println("\"atg\", \"ctgtatgta\"");
		System.out.println(twoOccurrences("atg", "ctgtatgta"));

		// return a stringb substring, beggining from the end of stringa
		System.out.println("\"an\", \"banana\"");
		System.out.println(lastPart("an", "banana"));
		// no stringb substring, return stringa
		System.out.println("\"zoo\", \"forest\"");
		System.out.println(lastPart("zoo", "forest"));
	}

	public static void main(String[] args) {
		testing();
	}
}
