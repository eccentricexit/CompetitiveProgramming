package hackerRank;

import java.util.Scanner;

public class AppendAndDelete {
	String a,b;	

	public AppendAndDelete(String a, String b) {
		super();
		this.a = a;
		this.b = b;		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
		String b = sc.next();
		int k = sc.nextInt();
		sc.close();
		
		AppendAndDelete aad = new AppendAndDelete(a,b);
		aad.printCanObtainInOperations(k);
	}

	private void printCanObtainInOperations(int k) {	
		if(canObtainInOperations(k))
			System.out.println("Yes");
		else
			System.out.println("No");
		
	}

	private boolean canObtainInOperations(int k) {
		int i=0;
		
		while(i<Math.min(a.length(),b.length())){
			if(a.charAt(i)!=b.charAt(i))
				break;
			
			i++;
		}
		
		int deletions = a.length()-i;
		int additions = b.length()-i;
		int operations = deletions+additions;
		
		if(k==operations)
			return true;
		
		if(k-a.length()==b.length())
			return true;
		
		if(k>=operations+2)
			return true;
		
		
		return false;
		
	}

}
