package hackerRank;

import java.util.Scanner;

public class BetweenTwoSets {
	int[] A,B;
	

	public BetweenTwoSets(int[] a, int[] b) {
		super();
		A = a;
		B = b;
	}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[] A = new int[n];
		int[] B = new int[m];
		for (int i = 0; i < n; i++) 
			A[i]=sc.nextInt();
		
		for (int i = 0; i < m; i++) 
			B[i]=sc.nextInt();
		
		sc.close();
		
		BetweenTwoSets bts = new BetweenTwoSets(A,B);
		bts.printBetweenCount();

	}


	private void printBetweenCount() {
		System.out.println(getCount());
		
	}


	private int getCount() {
		int count = 0;
		
		main:
		for(int x = 1;x<=100;x++){
			
			for (int i = 0; i < A.length; i++) 
				if(x%A[i]!=0)
					continue main;
			
			
			for (int i = 0; i < B.length; i++) 
				if(B[i]%x!=0)
					continue main;
			
			count++;
		}
		return count;
	}

}
