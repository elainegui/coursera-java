
public class Part2 {
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
		System.out.println(cgRatio("ATGCCATAG"));
		System.out.println("vCTGxxxCTGyyyCTGkkkCTG");
		System.out.println(countCTG("vCTGxxxCTGyyyCTGkkkCTG"));
		System.out.println("vCTGxxxCTGyyyCTGkkknnCTGwCTGCTGv");
		System.out.println(countCTG("vCTGxxxCTGyyyCTGkkknnCTGwCTGCTGv"));
	}
}
