
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

	public static String findGene(String dna) {
		int startIndex = dna.indexOf("ATG");
		if (startIndex == -1) {
			return "";
		}
		int tgaIndex = findStopCodon(dna, startIndex, "TGA");
		int taaIndex = findStopCodon(dna, startIndex, "TAA");
		int tagIndex = findStopCodon(dna, startIndex, "TAG");
		System.out.println("test tagIndex" + tagIndex);
		System.out.println("test tgaIndex" + tgaIndex);
		System.out.println("test taaIndex" + taaIndex);
		int minIndex = 0;
		if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
			minIndex = tgaIndex;
			System.out.println("test1");
		} else {
			minIndex = taaIndex;
			System.out.println("test2");
		}
		if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
			minIndex = tagIndex;
			System.out.println("test3");
		}
		if (minIndex == -1) {
			System.out.println("test4");
			return "";
		}
		System.out.println("test5");
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
		System.out.println("Gene: " + findGene(dna));
		// dna with “ATG” and one valid stop codon
		dna = "CTATGTATCGAAAATAAAGGTC";
		System.out.println("String DNA: " + dna);
		System.out.println("Gene: " + findGene(dna));
		// test dna with “ATG” and multiple valid stop codons
		dna = "xxxATGyyyTAAzzzTAGwwwTGA";
		System.out.println("String DNA: " + dna);
		System.out.println("Gene: " + findGene(dna));
		// dna with “ATG” and no valid stop codons
		dna = "xxxATGyyyAAAzzzAAGwwwCGA";
		System.out.println("String DNA: " + dna);
		System.out.println("Gene: " + findGene(dna));
	}

	public static void printAllGenes(String dna) {
		while (true) {
			System.out.println("Gene: " + findGene(dna));
			// find genes and print them
			// if no gene break;
			if (findGene(dna).isEmpty()) {
				break;
			}
		}
	}

	public static void main(String[] args) {
		// testFindStopCodon();
		// testFindGene();
		// exercise1
		System.out.println(findGene("AATGCTAACTAGCTGACTAAT"));
	}
}
