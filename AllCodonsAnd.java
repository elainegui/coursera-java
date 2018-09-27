
public class AllCodonsAnd {
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

	public static String findGene(String dna) {
		// start index is ATG
		int startIndex = dna.indexOf("ATG");
		if (startIndex == -1) {
			return "";
		}

		int taaIndex = findStopCodon(dna, startIndex, "TAA");
		int tagIndex = findStopCodon(dna, startIndex, "TAG");
		int tgaIndex = findStopCodon(dna, startIndex, "TGA");
		// if taaIndex == -1 OR
		// if tgaIndex !=-1 AND tgaIndex < taaIndex
		// then set minIndex to tgaIndex
		// else set minIndex to taaIndex
		int minIndex = 0;
		if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
			minIndex = tgaIndex;
		} else {
			minIndex = taaIndex;
		}
		// if tagIndex !=-1 and tagIndex < minIndex
		// then set minIndex to tagIndex
		// if minIndex==-1, return ""
		if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
			minIndex = tagIndex;
		} else {
			minIndex = taaIndex;
		}

		if (minIndex == -1) {
			return "";
		}
		return dna.substring(startIndex, minIndex + 3);
	}

	public static void testFindGene() {
		String dna = "ATGCCCGGGAAATAACCC";
		String gene = findGene(dna);
		if (!gene.equals("ATGCCCGGGAAATAA")) {
			System.out.println("error");
		}
		System.out.println("tests finished");
	}

	public static void main(String[] args) {
		testFindGene();
		// findGene("ATGCCCGGGAAATAACCC");
	}
}
