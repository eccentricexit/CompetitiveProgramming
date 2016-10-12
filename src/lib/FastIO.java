/*******************************************************************************
 * Author: Matheus Faria de Alencar
 *******************************************************************************/
package lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FastIO {
	
	BufferedReader br;
	StringTokenizer str;
	
	public FastIO(){
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public String next(){
		while(str==null || !str.hasMoreTokens()){
			try {
				str = new StringTokenizer(br.readLine());
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}		
		
		return str.nextToken();
	}
	
	public int nextInt(){
		return Integer.parseInt(next());
	}
}
