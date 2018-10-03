
public class AllCodons {
	public static int findStopCodon(String dnaStr, int startIndex, String stopCodon) {
		// we have three stop codons: TAA TAG and TGA
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

	public static void testFindStop() {
		//
		String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
		// 01234567890123456789012345
		int dex = findStopCodon(dna, 0, "TAA");
		if (dex != 9) {
			System.out.println("error on 9");
		}
		dex = findStopCodon(dna, 9, "TAA");
		if (dex != 21) {
			System.out.println("error on 21");
		}
		dex = findStopCodon(dna, 1, "TAA");
		if (dex != -1) {
			System.out.println("error on 26");
		}
		dex = findStopCodon(dna, 0, "TAG");
		if (dex != -1) {
			System.out.println("error on 26 TAG");
		}
		System.out.println("Tests finished");
	}

	public static String findGene(String dna) {
		int startIndex = dna.indexOf("ATG");
		if (startIndex == -1) {
			return "";
		}

		int stopIndex = Math.min(findStopCodon(dna, 0, "TAA"), findStopCodon(dna, 0, "TAG"));
		stopIndex = Math.min(stopIndex, findStopCodon(dna, startIndex, "TGA"));
		if (stopIndex == dna.length()) {
			return "";
		} else {
			return dna.substring(startIndex, stopIndex + 3);
		}
	}

	public static void main(String[] args) {
		System.out.println(findGene("ATGGCGTGATAATTAATCGTAG"));
		System.out.println(findGene("ATGxxxyyyzzz"));
		testFindStop();
	}

}
