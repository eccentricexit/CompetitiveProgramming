package hackerRank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RansomNote {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		
		Map<String,Integer> ransom = new HashMap<>();
		Map<String,Integer> magazine = new HashMap<>();
				
		for (int magazine_i = 0; magazine_i < m; magazine_i++) {
			String word = in.next();
			Integer wordCount = magazine.get(word);
			wordCount = (wordCount==null)?0:wordCount;
			
			magazine.put(word,wordCount+1);
		}
		
		for (int ransom_i = 0; ransom_i < n; ransom_i++) {
			String word = in.next();
			Integer wordCount = ransom.get(word);
			wordCount = (wordCount==null)?0:wordCount;
			
			ransom.put(word,wordCount+1);
		}

		if (canWrite(ransom, magazine)) {
			System.out.print("Yes");
		} else {
			System.out.print("No");
		}
		
		in.close();
	}

	private static boolean canWrite(Map<String, Integer> ransom, Map<String, Integer> magazine) {
		
		for(Map.Entry<String, Integer> entry : ransom.entrySet()){
			Integer wordCountInRansom = entry.getValue();
			Integer wordCountInMagazine = magazine.get(entry.getKey());
			if(wordCountInRansom>wordCountInMagazine)
				return false;
						
		}
		
		return true;
	}

}
