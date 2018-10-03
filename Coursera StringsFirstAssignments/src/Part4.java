import edu.duke.*;

public class Part4 {
	public static void findYouTube() {
		URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
		String youTubeStr = "youtube.com";
		for (String word : ur.words()) {
			String wordLowerCase = word.toLowerCase();
			if (wordLowerCase.contains(youTubeStr)) {

				// find the " before youtube.com
				int indexBegin = wordLowerCase.indexOf(youTubeStr);
				// find the " after youtube.com
				int indexEnd = word.lastIndexOf("\"");
				System.out.println(indexBegin);
				System.out.println(indexEnd);
				System.out.println(word.substring(indexBegin, indexEnd));
			}
		}
	}

	public static void main(String[] args) {
		findYouTube();
	}
}
