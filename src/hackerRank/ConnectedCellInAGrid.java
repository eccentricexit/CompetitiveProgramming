package hackerRank;

import java.util.Scanner;

public class ConnectedCellInAGrid {
	
	int n,m;
	int[][] arr;
	int largestRegionSize = 0;
	boolean[][] visited;
	
	public ConnectedCellInAGrid(int[][] arr, int n, int m) {
		this.arr = arr;
		this.n = n;
		this.m = m;
		this.visited = new boolean[n][m];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int arr[][] = new int[n][m];
		
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++)
				arr[i][j]=sc.nextInt();						
		}
		sc.close();
		
		ConnectedCellInAGrid cc = new ConnectedCellInAGrid(arr,n,m);
		cc.printLargestRegionSize();
	}

	private void printLargestRegionSize() {
		findLargestRegion();
		System.out.println(largestRegionSize);		
	}

	private void findLargestRegion() {		
		
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				if(wasVisitedBefore(i, j))
					continue;
				
				int currentRegionSize = 0;
				if(arr[i][j]==1){
					currentRegionSize = exploreRegionDfs(i,j);
				}		
				
				largestRegionSize = Math.max(currentRegionSize, largestRegionSize);
			}
		}
		
	}
	
	private int exploreRegionDfs(int i, int j) {		
		if(isOutOfBounds(i, j))
			return 0;
		
		if(wasVisitedBefore(i, j))			
			return 0;
		
		if(!isPartOfRegion(i, j))
			return 0;
				
		visited[i][j] = true;
		int regionSize = 0;

		//topRow
		regionSize += exploreRegionDfs(i-1, j-1);
		regionSize += exploreRegionDfs(i-1, j);
		regionSize += exploreRegionDfs(i-1, j+1);
		
		//middleRow
		regionSize += exploreRegionDfs(i, j-1);
		regionSize += 1;
		regionSize += exploreRegionDfs(i, j+1);
		
		//bottomRow
		regionSize += exploreRegionDfs(i+1, j-1);
		regionSize += exploreRegionDfs(i+1, j);
		regionSize += exploreRegionDfs(i+1, j+1);
		
		return regionSize;
	}

	private boolean isPartOfRegion(int i, int j) {
		return arr[i][j]==1;
	}

	private boolean wasVisitedBefore(int i, int j) {
		return visited[i][j];
	}

	private boolean isOutOfBounds(int i, int j) {
		return i<0 || i>n-1 || j<0 || j>m-1;
	}	

}
