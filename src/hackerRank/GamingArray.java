package hackerRank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class GamingArray {

	public static void main(String[] args) {
		FastIO sc = new FastIO();
		int G = sc.nextInt();
		for (int g = 0; g < G; g++) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			
			Map<Integer,Integer> map = new HashMap<>();
			for(int i=0;i<N;i++){
				arr[i] = sc.nextInt();
				map.put(arr[i],i);
			}
			
			int[] sorted = arr.clone();
			Arrays.sort(sorted);
			
			boolean bob = true;			
			int r = arr.length-1;
			
			int last = sorted.length-1;
			while(r>=0){
				int biggest = 0;
				
				for(int i=last;i>=0;i--){
					biggest = map.get(sorted[i]);
					if(biggest>r){
						continue;
					}
					last = i-1;	
					break;
				}
				
				r = biggest-1;
				bob = !bob;
			}
			
			if(!bob)
				System.out.println("BOB");
			else
				System.out.println("ANDY");
						
		}
		
		sc.close();

	}
	
	private static class FastIO {
		
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

		public void close() {
			try {
				br.close();
			} catch (IOException e) {			
				e.printStackTrace();
			}		
		}
	}

}
