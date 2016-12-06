package hackerRank;

import java.util.Scanner;

public class CatsAndMouse {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int Q = sc.nextInt();
		
		for(int q=1;q<=Q;q++){
			int A = sc.nextInt();
			int B = sc.nextInt();
			int C = sc.nextInt();
			
			if(C > A && C>B){
				if(A>B)
					System.out.println("Cat A");
				
				if(B>A)
					System.out.println("Cat B");
				
				if(A==B)
					System.out.println("Mouse C");					
			}
			
			if(C < A && C<B){
				if(A<B)
					System.out.println("Cat A");
				
				if(B<A)
					System.out.println("Cat B");
				
				if(A==B)
					System.out.println("Mouse C");					
			}
			
			if((C>A && C<B)||(C<A && C>B)){
				if(Math.abs(A-C)<Math.abs(B-C))
					System.out.println("Cat A");
				
				if(Math.abs(B-C)<Math.abs(A-C))
					System.out.println("Cat B");
				
				if(Math.abs(B-C)==Math.abs(A-C))
					System.out.println("Mouse C");
			}
			
			if(A==C && B==C)
				System.out.println("Mouse C");
			
			if(A==C && B!=C)
				System.out.println("Cat A");
			
			if(B==C && A!=C)
				System.out.println("Cat B");
		}
		sc.close();

	}

}
