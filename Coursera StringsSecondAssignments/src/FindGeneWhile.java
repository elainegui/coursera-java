
public class FindGeneWhile {

	public static String findGene(String dna) {
		int startIndex = dna.indexOf("ATG");
		int currIndex = dna.indexOf("TAA", startIndex + 3);

		while (currIndex != -1) {
			if ((currIndex - startIndex) % 3 == 0) {
				return dna.substring(startIndex, currIndex + 3);
			} else {
				currIndex = dna.indexOf("TAA", currIndex + 1);
			}
		}
		return "";
	}

	public static void testFindGeneSimple() {
		String dna = "ATGGCGTAATTAATCG";
		System.out.println("DNA strand is " + dna);
		String gene = findGene(dna);
		System.out.println("Gene is "+ gene);
		
		dna = "CGATGTTTGTAAGCCTAAGCTATAA";
		System.out.println("DNA strand is " + dna);
		gene = findGene(dna);
		System.out.println("Gene is "+ gene);
		
		dna = "CGATGGTTGTAAGCCTAAGCTAAA";
		System.out.println("DNA strand is " + dna);
		gene = findGene(dna);
		System.out.println("Gene is "+ gene);
	}

	public static void main(String[] args) {
		testFindGeneSimple();
	}

}
