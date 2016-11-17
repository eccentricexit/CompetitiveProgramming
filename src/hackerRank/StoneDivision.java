package hackerRank;

import java.util.Arrays;
import java.util.Scanner;

public class StoneDivision {
	long n;
	long[] set;
	boolean gameOver = false;
	boolean player = true; //0 == first, 1 == second, set to start with the second player because the loop will invert it.
	
	public StoneDivision(long n, long[] set) {
		super();
		this.n = n;
		this.set = set;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		int M = sc.nextInt();
		long[] set = new long[M];

		for (int m = 0; m < M; m++)
			set[m] = sc.nextLong();

		sc.close();

		Arrays.sort(set);
		StoneDivision sd = new StoneDivision(n, set);
		sd.printWinner();
	}

	private void printWinner() {			
		while(!gameOver){
			player = !player;
			play();			
		}
		
		if(player)
			System.out.println("First");
		else
			System.out.println("Second");
	}

	private void play() {
		long divisor = -1;
		for(int i=set.length-1;i>=0;i--){
			if(n%set[i]==0){
				divisor = set[i];
				break;
			}
		}
		
		if(divisor == -1){
			gameOver = true;
			return;
		}
		
		if(isEven(divisor)){
			player = !player;			
		}
		
		n /= divisor;
	}

	private boolean isEven(long divisor) {		
		return divisor%2==0;
	}
}
