package lib;

public class MatrixUtil {
	public static void printIntMatrix(int[][] matrix,int length, int height){
		for(int i=0;i<height;i++){			
			for(int j=0;j<length;j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
}
