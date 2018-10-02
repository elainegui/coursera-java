import edu.duke.*;

public class Part1 {

	public static int findStopCodon(String dna, int startIndex, String stopCodon) {
		startIndex = dna.indexOf("ATG");
		int currIndex = dna.indexOf(stopCodon, startIndex + 3);
		while (currIndex != -1 && startIndex != -1) {// && startIndex != -1
			int diff = currIndex - startIndex;
			if (diff % 3 == 0) {
				return currIndex;
			} else {
				currIndex = dna.indexOf(stopCodon, currIndex + 1);
			}
		}
		return -1;
	}

	public static String findGene(String dna, int startIndex) {
		startIndex = dna.indexOf("ATG", startIndex);
		if (startIndex == -1) {
			return "";
		}
		int tgaIndex = findStopCodon(dna, startIndex, "TGA");
		int taaIndex = findStopCodon(dna, startIndex, "TAA");
		int tagIndex = findStopCodon(dna, startIndex, "TAG");
		int minIndex = 0;
		if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
			minIndex = tgaIndex;
		} else {
			minIndex = taaIndex;
		}
		if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
			minIndex = tagIndex;
		}
		if (minIndex == -1) {
			return "";
		}
		System.out.println(dna.substring(startIndex, minIndex + 3));
		return dna.substring(startIndex, minIndex + 3);
	}

	public static void testFindStopCodon() {
		System.out.println("String DNA: CTATGTATCGAAAAATATAAGTC");
		System.out.println("Gene: " + findStopCodon("CTATGTATCGAAAAATATAAGTC", 0, "TAA"));
		System.out.println("Gene: " + findStopCodon("CTATGTATCGAAAAATATAAGTC", 0, "TGA"));
		System.out.println("Gene: " + findStopCodon("CTATGTATCGAAAAATATAAGTC", 0, "TAG"));

		System.out.println("String DNA: CTATGTATCGAAAAATATGAGTC");
		System.out.println("Gene: " + findStopCodon("CTATGTATCGAAAAATATGAGTC", 0, "TAA"));
		System.out.println("Gene: " + findStopCodon("CTATGTATCGAAAAATATGAGTC", 0, "TGA"));
		System.out.println("Gene: " + findStopCodon("CTATGTATCGAAAAATATGAGTC", 0, "TAG"));

		System.out.println("String DNA: CTATGTATCGAAAAATATAGGTC");
		System.out.println("Gene: " + findStopCodon("CTATGTATCGAAAAATATAGGTC", 0, "TAA"));
		System.out.println("Gene: " + findStopCodon("CTATGTATCGAAAAATATAGGTC", 0, "TGA"));
		System.out.println("Gene: " + findStopCodon("CTATGTATCGAAAAATATAGGTC", 0, "TAG"));

		// no start codon found
		System.out.println("String DNA: CTATATATCGAAAAATATAGGTC");
		System.out.println("Gene: " + findStopCodon("CTATATATCGAAAAATATAGGTC", 0, "TAA"));
		System.out.println("Gene: " + findStopCodon("CTATATATCGAAAAATATAGGTC", 0, "TGA"));
		System.out.println("Gene: " + findStopCodon("CTATATATCGAAAAATATAGGTC", 0, "TAG"));

		// no start codon found and no stop codon found
		System.out.println("String DNA: CTATATATCGAAAAATATACGTC");
		System.out.println("Gene: " + findStopCodon("CTATATATCGAAAAATATAGGTC", 0, "TAA"));
		System.out.println("Gene: " + findStopCodon("CTATATATCGAAAAATATAGGTC", 0, "TGA"));
		System.out.println("Gene: " + findStopCodon("CTATATATCGAAAAATATAGGTC", 0, "TAG"));

		// difference not multiple of 3
		System.out.println("String DNA: CTATGTATCGAAAAATTAAGTC");
		System.out.println("Gene: " + findStopCodon("CTATGTATCGAAAAATTAAGTC", 0, "TAA"));
		System.out.println("Gene: " + findStopCodon("CTATGTATCGAAAAATTAAGTC", 0, "TGA"));
		System.out.println("Gene: " + findStopCodon("CTATGTATCGAAAAATTAAGTC", 0, "TAG"));
	}

	public static void testFindGene() {
		// test dna with no ATG
		String dna = "CTATATATCGAAAAATATAGGTC";
		System.out.println("String DNA: " + dna);
		System.out.println("Gene: " + findGene(dna, 0));
		// dna with “ATG” and one valid stop codon
		dna = "CTATGTATCGAAAATAAAGGTC";
		System.out.println("String DNA: " + dna);
		System.out.println("Gene: " + findGene(dna, 0));
		// test dna with “ATG” and multiple valid stop codons
		dna = "xxxATGyyyTAAzzzTAGwwwTGA";
		System.out.println("String DNA: " + dna);
		System.out.println("Gene: " + findGene(dna, 0));
		// dna with “ATG” and no valid stop codons
		dna = "xxxATGyyyAAAzzzAAGwwwCGA";
		System.out.println("String DNA: " + dna);
		System.out.println("Gene: " + findGene(dna, 0));
	}

	public static StorageResource getAllGenes(String dna) {
		StorageResource geneList = new StorageResource();
		int startIndex = 0;
		while (true) {
			String currentGene = findGene(dna, 0);
			if (currentGene.isEmpty()) {
				break;
			}
			geneList.add(currentGene);
			startIndex = 0;
			startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
			dna = dna.substring(startIndex);
		}
		return geneList;
	}

	public static double cgRatio(String dna) {
		// returns the ratio of C’s and G’s in dna
		int indexC = dna.indexOf("C");
		int indexG = dna.indexOf("G");
		int countC = 0;
		int countG = 0;
		String dnaForC = dna;
		String dnaForG = dna;
		while (indexC != -1) {
			countC++;
			dnaForC = dnaForC.substring(indexC + 1);
			indexC = dnaForC.indexOf("C");
		}
		while (indexG != -1) {
			countG++;
			dnaForG = dnaForG.substring(indexG + 1);
			indexG = dnaForG.indexOf("G");
		}
		double cgRatio = 0;
		int sumCG = countC + countG;
		int length = dna.length();
		return (float) sumCG / length;
	}

	public static int countCTG(String dna) {
		int startIndex = dna.indexOf("CTG");
		int currIndex = 0;
		int count = 0;
		while (startIndex <= dna.length()) {
			if (startIndex == -1) {
				break;
			}
			startIndex = startIndex + 3;
			dna = dna.substring(startIndex);
			count++;
			startIndex = dna.indexOf("CTG");
		}
		return count;
	}

	public static void main(String[] args) {
		// testFindStopCodon();
		testFindGene();
		// exercise1
		// System.out.println(findGene("AATGCTAACTAGCTGACTAAT"));
		// testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
		// String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
		// System.out.println(findGene(dna, 0));
	}

	public static void testOn(String dna) {
		System.out.println("Testing getAllGenes on " + dna);
		StorageResource genes = new StorageResource();
		genes = getAllGenes(dna);
		System.out.println("get all genes: " + getAllGenes(dna));
		for (String g : genes.data()) {
			System.out.println("test sr");
			System.out.println(g);
		}
	}
}
