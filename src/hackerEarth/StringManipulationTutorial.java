/*******************************************************************************
 * Author: Matheus Faria de Alencar
 *******************************************************************************/
package hackerEarth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class StringManipulationTutorial {
	
	int T;
	
	
	public static void main(String[] args) {
		StringManipulationTutorial smt = new StringManipulationTutorial();
		FastReader sc = new FastReader();
		
		smt.T = sc.nextInt();
		for(int t=1;t<=smt.T;t++){
			int wordSize=0;
			char[] values = new char[100000];

			Character nextChar = sc.nextChar();
			while(nextChar!=null){
				values[wordSize] = nextChar;
				wordSize++;
				nextChar = sc.nextChar();
			}			
				
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			List<Character> chars = new ArrayList<>();
			for(int n=N;n<=M;n++)
				chars.add(values[n]);
			
			Collections.sort(chars,new Comparator<Character>() {
				@Override
				public int compare(Character o1, Character o2) {
					if(o2.charValue()==o1.charValue())
						return 0;
					
					if(o2.charValue()>o1.charValue())
						return 1;
					else
						return -1;
				}
			});						
			
			int n=0;
			for(int i=0;i<wordSize;i++){
				if(i>=N && i<=M){
					System.out.print(chars.get(n));
					n++;
				}else{
					System.out.print(values[i]);
				}				
			}	
			System.out.println();
		}

	}

	
	private static class FastReader{
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
		
		Character nextChar(){
			int c = -1;
			try {
				c = br.read();
			} catch (IOException e) {				
				e.printStackTrace();
			}
			
			if(c!=-1 && c!=32){
				return (char) c;
			}else{
				return null;
			}
		}
	}

}
