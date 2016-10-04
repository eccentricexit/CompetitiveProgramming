package hackerRank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class TwoCharacters {

	static Map<Character,Integer> map;
	static char largest1Position=0	,largest2Position=0;
	
	public static void main(String[] args) {
		FastIO sc = new FastIO();
		int S = sc.nextInt();
		String input = sc.next();
		List<Character> word = new ArrayList<>();
		
		map = new HashMap<>();			
		
		
		for(int s=0;s<S;s++){
			char c = input.charAt(s);			
			word.add(c);
			Integer charPosition = map.get(c);
			if(charPosition==null){				
				map.put(c, 1);
			}else{
				int currentCharCount = map.get(c);
				map.put(c, currentCharCount+1);				
			}			
		}
		
		
		int i=0;
		for(Map.Entry<Character, Integer> entry:map.entrySet()){
			char c = entry.getKey();
			int count = entry.getValue();
			
			if(i==0){
				largest1Position=c;				
			}
			if(i==1){
				largest2Position=c;
				compareAndSwap();
			}
			
			if(i>1){
				if(count>map.get(largest2Position))
					largest2Position = c;
				
				compareAndSwap();
			}
			
			i++;
		}
		
		int j=0;
		int size = word.size();
		for(j=0;i<size;){
			if(j>=size)
				break;
			
			char c = word.get(j); 
			if(c!=largest1Position && c!=largest2Position){
				word.remove(j);
				size--;
			}
			else
				j++;
		}				
		
		if(word.size()<2){
			System.out.print(0);
			return;
		}
		
		
		printWord(word);
		
		int maxLenght = 0;
		int currentMaxLength = 0;		
		
		for(int z=1;z<word.size();z++){
			if(word.get(z-1)!=word.get(z)){
				currentMaxLength++;
			}else{				
				if(maxLenght<currentMaxLength){
					maxLenght = currentMaxLength+1;
					currentMaxLength = 0;
				}
			}
		}
		
		System.out.print(maxLenght);

	}
	
	private static void printWord(List<Character> word) {
		for(Character c:word)
			System.out.print(c);
		
	}

	private static void compareAndSwap() {
		int A = map.get(largest1Position);
		int B = map.get(largest2Position);
		
		char temp = largest1Position;
		
		if(B>A){
			largest1Position = largest2Position;
			largest2Position = temp;
		}		
	}

	private static class FastIO{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		
		String next(){
			
			while(str==null || !str.hasMoreTokens()){
				try {
					str = new StringTokenizer(br.readLine());
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
			
			return str.nextToken();
		}
		
		int nextInt(){
			return Integer.parseInt(next());
		}
	}

}
