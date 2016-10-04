package hackerRank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SockMerchant {

	public static void main(String[] args) {
		FastIO sc = new FastIO();
		
		int N = sc.nextInt();
		Map<Integer,Integer> map = new HashMap<>();
		
		for(int i=0;i<N;i++){
			int pos = sc.nextInt();
			Integer val = map.get(pos);
			if(val==null)
				val = 1;
			else
				val++;
			
			map.put(pos, val);
		}
		int pairCount = 0;
		for(Integer i :map.values()){
			pairCount += i/2;
		}
		
		System.out.print(pairCount);

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
