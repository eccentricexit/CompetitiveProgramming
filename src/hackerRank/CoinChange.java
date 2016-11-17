package hackerRank;

import java.util.Arrays;
import java.util.Scanner;

public class CoinChange {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int coins[] = new int[M];
        for(int coins_i=0; coins_i < M; coins_i++){
            coins[coins_i] = sc.nextInt();
        }
        sc.close();
        
        printCombinations(N, M, coins);
    }

	private static void printCombinations(int N, int M, int[] coins) {
		Arrays.sort(coins);
		
        long[][] arr = buildDPMatrix(N, M, coins);    	
    	
    	System.out.println(arr[M][N]);
	}

	private static long[][] buildDPMatrix(int N, int M, int[] coins) {
		long[][] arr = new long[M+1][N+1];
		
				
		for (int i = 1; i <= M; i++) {
    		int coin = coins[i-1];

    		for (int j = 1; j <= N; j++) {    			
    			if(coin>j)
    				arr[i][j] = arr[i-1][j];
    			else{
    				arr[i][j] = arr[i-1][j];
    				
    				if(j-coin==0){
    					arr[i][j]++;
    				}else{
    					arr[i][j] += arr[i][j-coin];
    				}
    			}
    		}
    	}
		
		return arr;
	}

}
