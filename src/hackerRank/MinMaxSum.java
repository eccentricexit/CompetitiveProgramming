package hackerRank;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class MinMaxSum {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int d = in.nextInt();
        int e = in.nextInt();
        in.close();
        
        int[] arr = new int[]{a,b,c,d,e};
        Arrays.sort(arr);
        
        //for(int i = 0;i<arr.length;i++)
        //    System.out.print(arr[i]+" ");
        
        BigInteger small = BigInteger.ZERO;        
        small = small.add(
        	BigInteger.valueOf(arr[0]))
        	.add(BigInteger.valueOf(arr[1]))
        	.add(BigInteger.valueOf(arr[2]))
        	.add(BigInteger.valueOf(arr[3]));
        	
        	
       BigInteger big = BigInteger.ZERO;       
       big = big.add(BigInteger.valueOf(arr[1]))
       .add(BigInteger.valueOf(arr[2]))
       .add(BigInteger.valueOf(arr[3]))
       .add(BigInteger.valueOf(arr[4]));        	
        
        
        
        System.out.println(small.toString()+" "+big.toString());
	}

}
