/*******************************************************************************
 * Author: Matheus Faria de Alencar
 *******************************************************************************/
package geeksForGeeks;

/**
 * Checks for zeros surrounded by ones on matrix
 * @author rigel
 *
 */
public class MatrixPool {
	
	static int[][] matrix;

	public static void main(String[] args) {		
		
		matrix = new int[][]{
				{1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,0,1,0,0,1,1,0,0,1,1,1,1},
				{1,1,1,0,0,1,1,1,1,1,0,0,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1}
				};
		
		System.out.print(numberOfPools(matrix,4,13));
		
	}
	
	static int numberOfPools(int[][] matrix,int r,int c){
		int numberOfPools = 0;
		
		for(int i=1;i<r;i++){
			for(int j=1;j<c;j++){	
				
				if(matrix[i][j]==0){
					if(checkMatrix(i,j,r,c))numberOfPools++;
				}
				
			}
		}	
		
		return numberOfPools;
	}
	
	private static boolean checkMatrix(int i, int j,int n,int m) {
		
		int rightColumn = m;
		int bottomRow = n;
		
		for(int y=j;y<=rightColumn;y++){
			if(matrix[i][y]==1){
				rightColumn = y-1; 
			}			
		}
		
		for(int x=i;x<bottomRow;x++){
			if(matrix[x][j]==1){
				bottomRow = x-1; 
			}
		}
		
		for(int y=j;y<=rightColumn;y++){
			for(int x=i;x<bottomRow;x++){
				if(matrix[x][y]!=0)
					return false;
			}
		}
		
		int x = i-1;
		while(x<=bottomRow+1){
			if(matrix[x][j-1]!=1)
				return false;
			
			if(matrix[x][rightColumn+1]!=1)
				return false;
			
			x++;
		}
		
		int y = j-1;
		while(y<=rightColumn+1){
			if(matrix[i-1][y]!=1)
				return false;
			
			if(matrix[bottomRow+1][y]!=1)
				return false;		
			
			y++;
		}
		
		
		return true;
	}		

}
