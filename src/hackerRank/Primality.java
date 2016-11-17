package hackerRank;

import lib.FastIO;

public class Primality {	
	

	public static void main(String[] args) {
		FastIO sc = new FastIO();
		int P = sc.nextInt();
				
		System.out.println();
		
		Primality checker = new Primality();
		for(int p=1;p<=P;p++){
			int n = sc.nextInt();
			checker.printIsPrime(n);
		}
		sc.close();
	
	}

	private void printIsPrime(int n) {		
		if(isPrime(n))
			System.out.println("Prime");
		else
			System.out.println("Not prime");
	}

	private boolean isPrime(int n) {
		if(n==2)
			return true;
		
		if(n==1 || (n & 1)==0)
			return false;
		
		for(int i=3;i<=Math.sqrt(n);i+=2)
			if(n%i==0)
				return false;					
		
		return true;
	}

}
