
public class Part1 {

	public static int findStopCodonAnd(String dnaStr, int startIndex, String stopCodon) {
		int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
		while (currIndex != -1) {
			int diff = currIndex - startIndex;
			if (diff % 3 == 0) {
				return currIndex;
			} else {
				currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
			}
		}
		return -1;
	}

	public static String findSimpleGene(String dna) {
		String substringDna = "";
		int indexStart = dna.indexOf("ATG");
		if (indexStart == -1) {
			return substringDna;
		}
		int indexStop = dna.indexOf("TAA") + 3;
		if (indexStop == -1) {
			return substringDna;
		}

		if ((indexStop - indexStart) % 3 == 0) {

			substringDna = dna.substring(indexStart, indexStop);
		}
		return substringDna;
	}

	public static void testSimpleGene() {
		// DNA with no “ATG”
		System.out.println("String DNA: AGCATCGAAAAATAA");
		System.out.println("Gene: " + findSimpleGene("AGCATCGAAAAATAA"));

		// DNA with no “TAA”
		System.out.println("String DNA: TTATGGAAAAATAC");
		System.out.println("Gene: " + findSimpleGene("TTATGGAAAAATAC"));

		// DNA with no “ATG” or no “TAA”
		System.out.println("String DNA: TTATCGAAAAATAC");
		System.out.println("Gene: " + findSimpleGene("TTATCGAAAAATAC"));

		// DNA with ATG , TAA and the substring between them
		// is a multiple of 3 (a gene)
		System.out.println("String DNA: CTATGTATCGAAAAATATAAGTC");
		System.out.println("Gene: " + findSimpleGene("CTATGTATCGAAAAATATAAGTC"));

		// DNA with ATG, TAA and the substring between them
		// is not a multiple of 3
		System.out.println("String DNA: CTATGTATCGAATAAATATAAGTC");
		System.out.println("Gene: " + findSimpleGene("CTATGTATCGAATAAATATAAGTC"));
	}

	public static void main(String[] args) {
		testSimpleGene();
	}
}
