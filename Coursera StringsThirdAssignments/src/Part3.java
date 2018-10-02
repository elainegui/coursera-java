import edu.duke.FileResource;
import edu.duke.StorageResource;
import edu.duke.URLResource;

public class Part3 {
	public static void processGenes(StorageResource sr) {
		// print all Strings that are longer than 9 characters
		// print the n. of strings that are longer than 9 characters
		// print the Strings in sr whose C-G-ratio is higher than 0.35
		// print the number of strings in sr whose C-G-ratio is higher than 0.35
		// print the length of the longest gene in sr

		int countLengthGreaterThan9 = 0;
		int countCgRatioGreaterThan35 = 0;
		int longestGeneLength = 0;
		int countLengthGreaterThan60 = 0;

		for (String str : sr.data()) {
			System.out.println(str);
			if (str.length() > longestGeneLength) {
				longestGeneLength = str.length();
			}
			if (str.length() > 9) {
				System.out.print(str + "\tLength > than 9 " + "\t");
				countLengthGreaterThan9++;
			}
			if (cgRatio(str) > 0.35) {
				System.out.println("cgRatio > 0.35\n");
				countCgRatioGreaterThan35++;
			}
			if (str.length() > 60) {
				System.out.println("string greater than 60");
				countLengthGreaterThan60++;
			}
			System.out.println();
		}

		System.out.println();
		System.out.println("total cg Ratio > 35: " + countCgRatioGreaterThan35);
		System.out.println("Total length > 9: " + countLengthGreaterThan9);
		System.out.println("Length of the longest gene: " + longestGeneLength);
		System.out.println("N. genes greater than 60: " + countLengthGreaterThan60);
	}

	public static double cgRatio(String dna) {
		// returns the ratio of C’s and G’s in dna
		dna = dna.toUpperCase();
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
		int sumCG = countC + countG;
		int length = dna.length();

		return (float) sumCG / length;
	}

	public static StorageResource getAllGenes(String dna) {
		dna = dna.toUpperCase();
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

	public static String findGene(String dna, int startIndex) {
		dna = dna.toUpperCase();
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

	public static int findStopCodon(String dna, int startIndex, String stopCodon) {
		dna = dna.toUpperCase();
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

	public static void testprocessGenes() {
		/*
		 * String dna= ""; // v v v v v v dna =
		 * "AAAxxxATGxxxGGGvvvCCCzzzTAAbbbATGnnnTAAbbbATGxxxATTbbbTTAmmmTGA";
		 * StorageResource geneList = new StorageResource(); System.out.println(dna);
		 * geneList=getAllGenes(dna); processGenes(geneList);
		 */
		/*
		 * FileResource fr = new FileResource("src/dna/brca1line.fa"); String dna =
		 * fr.asString(); System.out.println(dna); StorageResource genes =
		 * getAllGenes(dna); //System.out.println("size"+genes.size());
		 * processGenes(genes);
		 */

		URLResource ur = new URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
		String dna = ur.asString();
		// System.out.println(dna);
		StorageResource genes = getAllGenes(dna);
		System.out.println("size" + genes.size());
		processGenes(genes);
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

	public static void testCountCTG() {
		URLResource ur = new URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
		String dna = ur.asString();
		int countCtg = countCTG(dna);
		System.out.println("CTG count:" + countCtg);
	}

	public static void main(String[] args) {
		testprocessGenes();
		// testCountCTG();
	}
}
