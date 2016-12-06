package hackerRank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class RadioTransmitters {

	int[] arr;
	Map<Integer,Integer> map;
	int k;
	public RadioTransmitters(int[] arr, int k) {
		this.k = k;
		
		this.arr = arr;
		Arrays.sort(arr);
		
		map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) 
			map.put(arr[i],i);			
		
	}

	public static void main(String[] args) {
		FastIO sc = new FastIO();
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] arr = new int[N];
		
		for (int i = 0; i < arr.length; i++)
			arr[i] = sc.nextInt();		
		
		RadioTransmitters rt = new RadioTransmitters(arr,K);
		rt.printAntennaeCount();
		
		sc.close();
	}

	private void printAntennaeCount() {
		int count = countAntennae();
		System.out.println(count);		
	}

	private int countAntennae() {
		int count = 0;
		
		Integer i = 0;
		while(i<arr.length){			
			if(i==arr.length-1){
				count++;
				break;
			}
			
			int current = arr[i];
			
			int pos = current+k;
			Integer next = null;			
			while(next==null && pos>i){
				next = map.get(pos);
				pos--;
			}
			
			if(next!=null){				
				i = map.get(pos+1+k+1);
			}else{
				i = map.get(current+k+1);
			}		
			
			if(i==null){				
				for(int z=pos+1+k+1+1;z<=arr[arr.length-1];z++){
					if(map.get(z)!=null){
						i=map.get(z);
						break;
					}
				}
			}
			
			count++;			
			if(pos+1+k+1>arr[arr.length-1]){
				break;
			}
		}
		
		return count;
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
