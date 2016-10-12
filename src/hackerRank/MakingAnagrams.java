/*******************************************************************************
 * Author: Matheus Faria de Alencar
 *******************************************************************************/
package hackerRank;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MakingAnagrams {
	

	public static void main(String[] args) {
		String a = "cde";
		String b = "abc";
		
		System.out.print(numberNeeded(a,b));
	}

	private static int numberNeeded(String a, String b) {
		Map<Character,Integer> mapA = getMap(a);
		Map<Character,Integer> mapB = getMap(b);
		Set<Character> checkedChars = new HashSet<>();
		
		int minA = getMinDeletions(mapA,mapB,checkedChars);
		int minB = getMinDeletions(mapB,mapA,checkedChars);			
		
		return minA+minB;
	}

	private static int getMinDeletions(Map<Character, Integer> mapB, Map<Character, Integer> mapA, Set<Character> checkedChars) {
		int count = 0;
		
		for(Map.Entry<Character, Integer> entry:mapA.entrySet()){
			if(checkedChars.contains(entry.getKey()))
				continue;
			
			Integer bCount = mapB.get(entry.getKey());
			if(bCount == null){
				count+=entry.getValue();
				checkedChars.add(entry.getKey());
				continue;
			}
			
			if(bCount>entry.getValue())
				count += bCount - entry.getValue();
			else
				count += entry.getValue() - bCount;			
			checkedChars.add(entry.getKey());
		}
		
		return count;
	}

	private static Map<Character,Integer> getMap(String word) {
		Map<Character,Integer> map = new HashMap<>();
		
		for(Character c :word.toCharArray()){
			Integer count = map.get(c);
			if(count==null)
				count = 0;
			map.put(c, count+1);			
		}
		
		return map;
	}

}
