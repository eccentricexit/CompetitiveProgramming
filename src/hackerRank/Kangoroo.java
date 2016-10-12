/*******************************************************************************
 * Author: Matheus Faria de Alencar
 *******************************************************************************/
package hackerRank;

import java.util.Scanner;

public class Kangoroo {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x1 = sc.nextInt();
		int v1 = sc.nextInt();
		
		int x2 = sc.nextInt();
		int v2 = sc.nextInt();
		
		sc.close();
		
		int dv = v1-v2;
		int distance = x2-x1;
		
		if(willMeet(dv,distance)){
			System.out.println("YES");
		}else{
			System.out.println("NO");
		}		
	}

	private static boolean willMeet(int dv, int distance) {
		if(dv<=0)
			return false;
		
		if(distance%dv==0){
			return true;
		}else{		
			return false;
		}
	}

}
