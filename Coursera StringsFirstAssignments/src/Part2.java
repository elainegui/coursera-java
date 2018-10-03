
public class Part2 {
	// add two more parameters
	public static String findSimpleGene(String dna, String startCodon, String stopCodon) {
		String dnaSetted = "";
		char firstChar = dna.charAt(0);
		if (Character.isLowerCase(firstChar)) {
			dnaSetted = dna.toLowerCase();
		} else {
			dnaSetted = dna.toUpperCase();
		}
		String substringDna = "";
		int indexStart = dnaSetted.indexOf(startCodon);
		if (indexStart == -1) {
			return substringDna;
		}
		int indexStop = dnaSetted.indexOf(stopCodon) + 3;
		if (indexStop == -1) {
			return substringDna;
		}

		if ((indexStop - indexStart) % 3 == 0) {

			substringDna = dnaSetted.substring(indexStart, indexStop);
		}
		return substringDna;
	}

	public static String findGeneWhile(String dna) {
		int startIndex = dna.indexOf("ATG");
		if (startIndex == -1) {
			return "";
		}
		int taaIndex = findStopCodon(dna, startIndex, "TAA");
		int tagIndex = findStopCodon(dna, startIndex, "TAG");
		int tgaIndex = findStopCodon(dna, startIndex, "TGA");

		/*
		 * int minIndex = -1; if (taaIndex < tagIndex && taaIndex < tgaIndex) { minIndex
		 * = taaIndex; } else if (tagIndex < taaIndex && tagIndex < tgaIndex) { minIndex
		 * = tagIndex; } else if (tgaIndex < taaIndex && tgaIndex < tagIndex) { minIndex
		 * = tgaIndex; } else if (minIndex == dna.length()) { return ""; }
		 */

		int endIndex = 0;
		if (taaIndex == -1 && tagIndex == -1 && tgaIndex == -1) {
			return "";
		}
		if (taaIndex == -1 && (tagIndex != -1 && tgaIndex != -1)) {
			if (tagIndex > tgaIndex) {
				endIndex = tgaIndex;
			} else {
				endIndex = tagIndex;
			}
		}
		if (tagIndex == -1 && (taaIndex != -1 && tgaIndex != -1)) {
			if (taaIndex > tgaIndex) {
				endIndex = tgaIndex;
			} else {
				endIndex = taaIndex;
			}
		}
		if (tgaIndex == -1 && (tagIndex != -1 && taaIndex != -1)) {
			if (tagIndex > taaIndex) {
				endIndex = taaIndex;
			} else {
				endIndex = tagIndex;
			}
		} else {
			if (taaIndex < tagIndex && taaIndex < tgaIndex) {
				endIndex = taaIndex;
			}
			if (tagIndex < taaIndex && tagIndex < tgaIndex) {
				endIndex = tagIndex;
			}
			if (tgaIndex < taaIndex && tgaIndex < tagIndex) {
				endIndex = tgaIndex;
			}
		}
		return dna.substring(startIndex, endIndex);
	}

	public static String findGene(String dna) {
		int startIndex = dna.indexOf("ATG");
		if (startIndex == -1) {
			return "";
		}
		int taaIndex = findStopCodon(dna, startIndex, "TAA");
		int tagIndex = findStopCodon(dna, startIndex, "TAG");
		int tgaIndex = findStopCodon(dna, startIndex, "TGA");
		// int temp= Math.min(taaIndex, tagIndex);
		// int minIndex = Math.min(temp, tgaIndex);
		int minIndex = 0;
		if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
			minIndex = tgaIndex;
			System.out.println(minIndex);
		} else {
			minIndex = taaIndex;
			System.out.println("2   " + minIndex);
		}

		if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
			minIndex = tagIndex;
			System.out.println("3   " + minIndex);
		}
		// additional code
		if (minIndex == -1 || (taaIndex != -1 && taaIndex < minIndex)) {
			minIndex = taaIndex;
			System.out.println("4   " + minIndex);
		}
		if (minIndex == -1) {
			System.out.println("5   " + minIndex);
			return "";
		}
		System.out.println("6   " + minIndex);
		return dna.substring(startIndex, minIndex + 3);
	}

	public static int findStopCodon(String dnaStr, int startIndex, String stopCodon) {
		int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
		while (currIndex != -1) {
			if ((currIndex - startIndex) % 3 == 0) {
				// return dnaStr.substring(startIndex, (currIndex + 3));
				return currIndex;
			} else {
				// currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
				currIndex += 1;
			}
		}
		return -1;
	}

	public static void testSimpleGene() {
		// DNA with no “ATG”
		System.out.println("String DNA: AGCATCGAAAAATAA");
		System.out.println("Gene: " + findSimpleGene("AGCATCGAAAAATAA", "ATG", "TAA"));

		// DNA with no “TAA”
		System.out.println("String DNA: TTATGGAAAAATAC");
		System.out.println("Gene: " + findSimpleGene("TTATGGAAAAATAC", "ATG", "TAA"));

		// DNA with no “ATG” or no “TAA”
		System.out.println("String DNA: TTATCGAAAAATAC");
		System.out.println("Gene: " + findSimpleGene("TTATCGAAAAATAC", "ATG", "TAA"));

		// DNA with ATG , TAA and the substring between them
		// is a multiple of 3 (a gene)
		System.out.println("String DNA: CTATGTATCGAAAAATATAAGTC");
		System.out.println("Gene: " + findSimpleGene("CTATGTATCGAAAAATATAAGTC", "ATG", "TAA"));

		// DNA with ATG, TAA and the substring between them
		// is not a multiple of 3
		System.out.println("String DNA: CTATGTATCGAATAAATATAAGTC");
		System.out.println("Gene: " + findSimpleGene("CTATGTATCGAATAAATATAAGTC", "ATG", "TAA"));

		// DNA paramether is in lowercase, return empty string
		System.out.println("String DNA: ctatgtatcgaataaatataagtc");
		System.out.println("Gene: " + findSimpleGene("ctatgtatcgaataaatataagtc", "atg", "taa"));

		// DNA paramether is in uppercase, return gene
		System.out.println("String DNA: ctatgtatcgaaaaatataagct");
		System.out.println("Gene: " + findSimpleGene("ctatgtatcgaaaaatataagtc", "atg", "taa"));
	}

	public static void main(String[] args) {
		// Exercise Part 2
		testSimpleGene();
		// System.out.println(findSimpleGene("AAATGCCCTAACTAGATTAAGAAACC", "ATG",
		// "TAA"));

		// System.out.println(findGeneWhile("AATGCGATAATTAATCG"));
		// String dna = "ATGCCCGGGAAATAACCC";
		// String gene = findGene(dna);
		// System.out.println(gene);
		// if(gene.equals("ATGCCCGGGAAATAA")) {
		// System.out.println("error");
		// }
		// System.out.println("tests finished");

		// System.out.println(findGeneWhile("ACTGCCCTAACCC"));

		// System.out.println(findStopCodon("ATGCCCGGGAAATAACCC", 0, "TAA"));
		// System.out.println(findGeneWhile("ATGCCCGGGAAATAACCC"));
		// 1TAG
		// System.out.println(findGeneWhile("ATGCCCGGGAAATAGCCC"));
	}
}
