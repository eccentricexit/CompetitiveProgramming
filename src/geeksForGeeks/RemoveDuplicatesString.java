/*******************************************************************************
 * Author: Matheus Faria de Alencar
 *******************************************************************************/
package geeksForGeeks;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesString {

	public static void main(String[] args) {
		String word = "geeksforgeeks";
		StringBuilder builder = new StringBuilder(word);		
		Set<Character> set = new HashSet<>();
		
		int wordLenght = word.length();
		for(int i=0;i<wordLenght;){
			char c = builder.charAt(i);
			if(set.contains(c)){
				builder.deleteCharAt(i);
				wordLenght--;
				continue;
			}
			set.add(word.charAt(i));
			i++;
		}
		
		System.out.println(builder.toString());
		
		
		
		
		
		
	}

	

}
