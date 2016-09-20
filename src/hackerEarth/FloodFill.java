package hackerEarth;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class FloodFill {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		sc.nextLine();
		
		boolean[][] blocked = new boolean[N][M];
		boolean[][] visited = new boolean[N][M];
		
		for(int i=0;i<M;i++){
			String[] tokens = sc.nextLine().split(" ");
			for(int j=0;j<tokens.length;j++){
				int val = Integer.parseInt(tokens[j]);
				blocked[i][j] = (val==0)?true:false;
			}
		}
		sc.close();
		
		//Source
		int sX = 0;
		int sY = 0;
		
		//Destination
		int X = N-1;
		int Y = M-1;
		
		FloodFill fF = new FloodFill();
		
		printResult(fF.floodFillDFSRecursive(blocked,visited,sX,sY,X,Y,N,M));
		printResult(fF.floodFillBFS(blocked,sX,sY,X,Y,N,M));
		
	}

	private boolean floodFillBFS(boolean[][] blocked, int sX, int sY, int x, int y, int N,int M) {			
		
		Map<Integer,Node> nodeMap = new HashMap<Integer,Node>();
		
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				if(blocked[i][j])
					continue;
				
				Node s = nodeMap.get(i);
				if(s==null)
					s = new Node(i);
				
				Node t = nodeMap.get(j);
				if(t==null)
					t = new Node(j);
				
				s.adjNodes.put(j,t);
				nodeMap.put(i, s);
				nodeMap.put(j, t);
			}			
		}
		
		Node source = nodeMap.get(0);		
		Queue<Node> q = new LinkedList<Node>();
		
		q.add(source);
		
		while(!q.isEmpty()){
			Node n = q.remove();
			
			if(n.visited)
				continue;
			
			n.visited = true;
			if(n.index==N-1)
				return true;
			
			for(Node adjNode:n.adjNodes.values())
				q.add(adjNode);						
		}
		
		
		return false;
	}

	private static void printResult(boolean canReach) {
		if(canReach)
			System.out.println("Yes");
		else
			System.out.println("No");		
	}

	private boolean floodFillDFSRecursive(boolean[][] blocked, boolean[][] visited, int sX, int sY, int X, int Y, int N, int M) {
		if(sX==X && sY==Y)
			return true;
		
		if(sX>N-1 || sY>M-1)
			return false;
		
		if(sX<0 || sY<0)
			return false;
		
		if(blocked[sX][sY])
			return false;
			
		if(visited[sX][sY])
			return false;
		
		visited[sX][sY]=true;
		
		if(floodFillDFSRecursive(blocked,visited,sX,sY+1,X,Y,N,M))
			return true;
		
		
		if(floodFillDFSRecursive(blocked,visited,sX+1,sY,X,Y,N,M))
			return true;
			
		if(floodFillDFSRecursive(blocked,visited,sX-1,sY,X,Y,N,M))
			return true;		
		
		
		if(floodFillDFSRecursive(blocked,visited,sX,sY-1,X,Y,N,M))
			return true;
		
		return false;
	}
	
	class Node{
		public boolean visited;
		public Node(int index) {
			super();
			this.index = index;
			this.adjNodes = new HashMap<Integer,Node>();
		}
		int index;		
		Map<Integer,Node> adjNodes;		
		
	}	

}
