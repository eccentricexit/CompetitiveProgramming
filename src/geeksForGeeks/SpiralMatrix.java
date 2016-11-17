package geeksForGeeks;

public class SpiralMatrix {
	
	static int direction = 0;
	
	public static void main(String[] args) {
		int N = 5;
		int M = 6;
		int[][] matrix = new int[][]{
			{26,25,24,23,22,21},
			{27,10,9 ,8 ,7 ,20},
			{28,11,2 ,1 ,6 ,19},
			{29,12,3 ,4 ,5 ,18},
			{30,13,14,15,16,17}			
		};
		
		printSpiral(matrix,N,M);
		
	}

	private static void printSpiral(int[][] matrix, int n, int m) {
		int i = (n/2);
		int j = (m/2);		
		Pair p = new Pair(i,j);
		System.out.print(matrix[p.i][p.j]+" ");
		
		int steps = 1;		
		while(i<n && j<m && i>=0 && j>=0){	
			for (int k = 1; k <= steps ; k++) {
				p = p.next();
				System.out.print(matrix[p.i][p.j]+" ");				
			}
			direction++;
			for (int k = 1; k <= steps ; k++) {
				p = p.next();
				System.out.print(matrix[p.i][p.j]+" ");				
			}			
			steps++;
			direction++;
		}
	}
	
	
	private static class Pair{
		int i,j;

		public Pair(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		
		public Pair next(){
			int i = this.i;
			int j = this.j;
			
			if(direction>3) direction = 0;
			
			switch (direction) {
			case 0: 		//left
				j--;
				break;	
			case 1:			//down
				i++;
				break;	
			case 2:			//right
				j++;
				break;
			case 3:			//up
				i--; 
				break;			
			}
			
			return new Pair(i,j);
		}
		
	}
	
}
