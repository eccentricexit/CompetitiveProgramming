package hackerRank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class GridlandMetro {	
	
	
	public static void main(String[] args) {		
		int N;
		int M;	
		int K;			
		FastIO sc = new FastIO();
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		BigInteger availableCells = BigInteger.valueOf(N).multiply(BigInteger.valueOf(M));
		Map<Integer,List<Track>> map = new HashMap<>();
		
		
		for(int k=0;k<K;k++){
			int r = sc.nextInt();
			int c1=sc.nextInt();
			int c2 = sc.nextInt();				
			
			Track track = new Track(r, c1, c2);			
			
			List<Track> tracksInRow = map.get(r);
			if(tracksInRow==null){
				//it means this is the first track of the row
				//being added				
				
				tracksInRow = new ArrayList<Track>();
				tracksInRow.add(track);
				map.put(r, tracksInRow);				
			}else{				
				//there is already at least on track on this row				
				
				tracksInRow.add(track);
				map.put(r, tracksInRow);				
			}				
		}
		
		updateTrainValues(map);		
		int usedCellCOunt = getCells(map);
		
		availableCells = availableCells.subtract(BigInteger.valueOf(usedCellCOunt));
		
		System.out.print(availableCells);	
		
	}
	
	private static int getCells(Map<Integer, List<Track>> map) {
		int count = 0;
		
		for(List<Track> row:map.values()){
			for(Track t:row){
				for(int i=0;i<t.overlaped.length;i++){
					if(!t.overlaped[i])
						count++;
				}
			}
		}
		
		return count;
	}

	private static void updateTrainValues(Map<Integer, List<Track>> map) {
		for(List<Track> row : map.values()){
			
			for(int i=1;i<row.size();i++){
				Track currentTrack = row.get(i);
				for(int j=0;j<i;j++){
					Track previousTrack = row.get(j);
					if(previousTrack.collidesWith(currentTrack)){
						previousTrack.updateCollided(currentTrack);
					}
				}
			}
			
		}		
	}

	private static class Track{
		boolean[] overlaped;
		
		public Track(int r, int c1, int c2) {
			super();
			this.r = r;
			this.c1 = c1;
			this.c2 = c2;
			length = c2-c1+1;
			overlaped = new boolean[length];
		}
		@SuppressWarnings("unused")
		public void updateCollided(Track track) {
			int c1_a = c1;
			int c2_a = c2;
			
			int c1_b = track.c1;
			int c2_b = track.c2;
			
			while(c1_a<=c2_a){
				if(c1_a<c1_b){
					c1_a++;
					continue;
				}
				
				if(c1_b>c1_a){
					c1_b++;
					continue;
				}				
					
				overlaped[c1_a-c1] = true;
				c1_a++;
				c1_b++;
			}
			
		}
		
		public boolean collidesWith(Track track) {
			if(c2<track.c1 || c1>track.c2)
				return false;
			else
				return true;
		}
		
		@SuppressWarnings("unused")
		int r;
		int c1;
		int c2;
		int length;
		
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
