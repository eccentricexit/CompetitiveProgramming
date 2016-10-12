/*******************************************************************************
 * Author: Matheus Faria de Alencar
 *******************************************************************************/
package hackerRank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class FloydCityOfBlindingLights {
	
	int N;
	int M;	
	int dist[][];
	int Q;
	Query[] queries;
	
	static int INF = 9999999;

	public static void main(String[] args) {
		FloydCityOfBlindingLights fld = new FloydCityOfBlindingLights();
		FastReader sc = new FastReader();
		
		fld.N = sc.nextInt();
		fld.M = sc.nextInt();
		fld.dist = new int[fld.N+1][fld.N+1];
		for(int i=1;i<=fld.N;i++){
			for(int j=1;j<=fld.N;j++){
				if(i==j){					
					continue;
				}
				
				fld.dist[i][j] = INF;
			}
		}
		
		for(int m=1;m<=fld.M;m++){
			int s = sc.nextInt();
			int t = sc.nextInt();
			int w = sc.nextInt();
			
			fld.dist[s][t] = w;			
		}
		
		fld.Q = sc.nextInt();
		fld.queries = new Query[fld.Q];
		for(int q=0;q<fld.Q;q++){
			int s = sc.nextInt();
			int t = sc.nextInt();
			
			fld.queries[q] = new Query(s,t);
		}
		
		sc.close();
		
		fld.runFloydWarshall();
		fld.printQueryResults();
	}
	
	private void runFloydWarshall() {
		
		for(int k=1;k<=N;k++){
			for(int i=1;i<=N;i++){
				for(int j=1;j<=N;j++){
					if(dist[i][j]>dist[i][k] + dist[k][j]){
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
		
	}
	
	
	private void printQueryResults() {	
		for(Query q:queries){
			int result = (dist[q.s][q.t]==INF)?-1:dist[q.s][q.t];
			System.out.println(result);
		}			
	}
	
	private static class Query{
		
	public Query(int s, int t) {
			super();
			this.s = s;
			this.t = t;
		}
		int s;
		int t;
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
		
		void close(){
			try {
				br.close();
			} catch (IOException e) {			
				e.printStackTrace();
			}
		}
	}

}
