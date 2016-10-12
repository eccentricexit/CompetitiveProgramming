/*******************************************************************************
 * Author: Matheus Faria de Alencar
 *******************************************************************************/
package hackerRank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class DijkstraShortestReach {   
	
	int N;
	int M;
	Node S;
	
	Map<Integer,Node> map = new HashMap<>();
	
		
	public static void main(String[] args) {
		FastReader fr = new FastReader();
		
		int T = fr.nextInt();		
		for(int test=1;test<=T;test++){
			DijkstraShortestReach dsr = new DijkstraShortestReach();
			
			dsr.N = fr.nextInt();
			dsr.M = fr.nextInt();
			
			for(int m=1;m<=dsr.M;m++){
				int x = fr.nextInt();
				int y = fr.nextInt();
				int w = fr.nextInt();
				
				Node s = dsr.map.get(x);
				Node t = dsr.map.get(y);
				
				if(s==null)
					s = new Node(x);
				
				if(t==null)
					t = new Node(y);
				
				if(!s.adjNodes.containsKey(t)){
					s.adjNodes.put(y, t);
				}				
				s.putSmallestEdge(new Edge(t,w));
				
				if(!t.adjNodes.containsKey(s)){
					t.adjNodes.put(x, s);
				}
				t.putSmallestEdge(new Edge(s,w));
				
				dsr.map.put(s.index, s);
				dsr.map.put(t.index, t);
			}
			int s = fr.nextInt();
			dsr.S = dsr.map.get(s);
			
			dsr.dijkstra();
			dsr.printShortestDistances();
			System.out.println();
		}
	}
	
	private void printShortestDistances() {		
		for(int i=1;i<=N;i++){
			if(i==S.index)
				continue;
			
			Node n = map.get(i);
			if(n.currentMinDist == Integer.MAX_VALUE)
				System.out.print(-1+" ");
			else
				System.out.print(n.currentMinDist+" ");
			
		}
	}

	private void dijkstra() {
		S.currentMinDist = 0;		
		PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if(o2.currentMinDist<o1.currentMinDist)
					return 1;
				else
					return -1;
			}
		});
		
		q.add(S);
		
		while(!q.isEmpty()){
			Node node = q.remove();
			
			for(Node adjNode : node.adjNodes.values()){				
				int w = node.edges.get(adjNode.index).weight;
				
				if(adjNode.currentMinDist>node.currentMinDist+w){
					adjNode.currentMinDist = node.currentMinDist+w;
					q.add(adjNode);
				}
				
			}			
			
		}
		
	}

	private static class FastReader{		
		BufferedReader br;
		StringTokenizer st;
		
		public FastReader(){
			br=new BufferedReader(new InputStreamReader(System.in));
		}
		
		String next(){
			while( st == null || !st.hasMoreTokens()){
				try {
					st = new StringTokenizer(br.readLine());
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			
			return st.nextToken();
		}
		
		int nextInt(){
			return Integer.parseInt(next());
		}
	}
	
	static class Node{
		int index;		
		Node parent;		
		Map<Integer,Edge> edges;
		Map<Integer,Node> adjNodes;
		int currentMinDist;
		
		public Node(int index) {
			super();
			this.index = index;
			edges = new HashMap<>();
			adjNodes = new HashMap<>();
			currentMinDist = Integer.MAX_VALUE;
		}
		
		void putSmallestEdge(Edge b){		
			if(!edges.containsKey(b.terminal.index)){
				edges.put(b.terminal.index, b);
				return;
			}
			
			Edge a = edges.get(b.terminal.index);
			if(b.weight<a.weight)
				a = b;			
			
			edges.put(a.terminal.index, a);
		}
	}
	
	static class Edge{		
		Node terminal;
		int weight;
		public Edge(Node terminal, int weight) {
			super();			
			this.terminal = terminal;
			this.weight = weight;
		}
	}
}

