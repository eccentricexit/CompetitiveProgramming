package geeksForGeeks;

import lib.MatrixUtil;

public class RotateMatrix {

	public static void main(String[] args) {
		int N = 5;
		int M = 5;
		int[][] matrix = new int[][]{
			{1,1,1,0,0},
			{1,1,1,0,0},
			{0,0,0,0,0},
			{0,0,0,0,0},
			{0,0,0,0,0}			
		};
		
		//MatrixUtil.printIntMatrix(matrix,N,M);
		
		matrix = rotateClockwise(matrix,N,M);
		matrix = rotateClockwise(matrix,N,M);
		
		MatrixUtil.printIntMatrix(matrix,N,M);		
	}

	private static int[][] rotateClockwise(int[][] matrix, int n, int m) {	
		assert matrix != null;
		
		int[][] T = new int[n][m];
						
		for(int j=m-1;j>=0;j--){
			for(int i=0;i<n;i++){	
				int k = m-j-1;
				T[i][j] = matrix[k][i];
			}
		}
		
		
		return T;
	}

}
