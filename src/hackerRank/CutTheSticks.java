package hackerRank;

import java.util.Scanner;

public class CutTheSticks {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int smallest = 99999;
		int nextSmallest = 99999;

		int n = in.nextInt();
		int arr[] = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
			smallest = (smallest < arr[i]) ? smallest : arr[i];
		}

		in.close();
		if (n == 0)
			return;

		System.out.println(n);

		int count = 999;

		while (count > 0) {
			count = 0;
			
			// first cut
			for (int i = 0; i < n; i++) {
				arr[i] = arr[i] - smallest;
				
				if (arr[i] > 0 ) {
					count++;
					if(arr[i] < nextSmallest){
						nextSmallest = arr[i];
					}					
				}
			}
			
			smallest = nextSmallest;
			nextSmallest = 9999;
			
			if (count > 0)
				System.out.println(count);
			
		}

	}

}
