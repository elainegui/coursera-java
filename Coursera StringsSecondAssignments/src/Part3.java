
public class Part3 {
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

	public static int countGenes(String dna) {
		String gene = "";
		int count = 0;
		while (true) {
			gene = findGene(dna);
			if (gene.equals("")) {
				break;
			}
			count++;
			dna = dna.substring(dna.indexOf(gene) + gene.length());
		}
		return count;
	}

	public static void testCountGenes() {
		// 1gene
		String dna = "ATGxxxTAA";
		System.out.println("dna: " + dna + " \ncount genes: " + countGenes(dna));

		// 2genes
		dna = "ATGxxxTAAzzzATGyyyTGA";
		System.out.println("dna: " + dna + " \ncount genes: " + countGenes(dna));

		// 3genes
		dna = "ATGxxxTAAzzzATGyyyTGAsssATGdddTGA";
		System.out.println("dna: " + dna + " \ncount genes: " + countGenes(dna));
	}

	public static void main(String[] args) {
		// testFindStopCodon();
		// testFindGene();
		// testCountGenes();
	}

}
