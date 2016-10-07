package hackerEarth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class KMPTutorial {

	public static void main(String[] args) {
		FastReader sc = new FastReader();
		String P = sc.next();
		String T = sc.next();		
		
		new KMPTutorial().printNumberOfOcurrences(P,T);		
		
		sc.close();
	}
	
	public void printNumberOfOcurrences(String pattern,String text){
		int[] lps = generateLpsFromPattern(pattern);
		Set<Integer> ocurrenceLocations = findOcurrenceLocations(pattern, text,lps);		
		System.out.print(ocurrenceLocations.size());
	}

	private Set<Integer> findOcurrenceLocations(String pattern, String text,int[] lps) {
		Set<Integer> ocurrenceLocations = new HashSet<>();
		
		int i=0,j=0;
		
		while(i<text.length()){
			if(match(pattern, text, i, j)){
				i++;
				j++;
			}
			
			if(j==pattern.length()){
				int position = i-j;
				ocurrenceLocations.add(position);
				j = lps[j-1];
			}else if(i<text.length() && !match(pattern,text,i,j)){
				if(j!=0)
					j=lps[j-1];
				else
					i++;
			}
		}
		
		return ocurrenceLocations;
	}

	private static boolean match(String pattern, String text, int i, int j) {
		return text.charAt(i)==pattern.charAt(j);
	}

	private int[] generateLpsFromPattern(String pattern) {
		int[] lps = new int[pattern.length()];		
		
		int i=1,j=0;
		for(i=1;i<pattern.length();){						
			if(match(pattern, pattern, j, i)){
				lps[i] = j+1;
				i++;
				j++;
			}else{
				if(j!=0){
					j = lps[j-1];
				}else{
					lps[i] = 0;
					i++;
				}
			}
		}
		
		return lps;
	}

	@SuppressWarnings("unused")
	private static void printArr(int[] arr) {
		for(Integer i:arr)
			System.out.print(i+" ");
		
	}
	
	
	private static class FastReader{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		
		String next(){
			
			while(str == null || !str.hasMoreTokens()){
				try {
					str = new StringTokenizer(br.readLine());
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
			
			return str.nextToken(); 
		}

		public void close() {
			try {
				br.close();
			} catch (IOException e) {			
				e.printStackTrace();
			}			
		}
	}

}
