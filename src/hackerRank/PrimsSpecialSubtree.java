package hackerRank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PrimsSpecialSubtree {

	Node source;
	
	Map<Integer,Node> map = new HashMap<>();
	List<Edge> mst;
	
	public static void main(String[] args) {
		FastInput fi = new FastInput();
		PrimsSpecialSubtree pss = new PrimsSpecialSubtree();
		fi.nextInt();
		int M = fi.nextInt();
		
		while(M -->0){
			int x = fi.nextInt();
			int y = fi.nextInt();
			int w = fi.nextInt();
			
			Node s = pss.map.get(x);
			if(s==null)
				s=new Node(x);
							
			Node t = pss.map.get(y);
			if(t==null)
				t=new Node(y);
			
			s.edges.put(y, new Edge(t, w));
			t.edges.put(x, new Edge(s,w));
			
			s.adjNodes.put(y, t);
			t.adjNodes.put(x, s);
			
			pss.map.put(x, s);
			pss.map.put(y, t);			
		}
		
		int s =fi.nextInt();
		pss.source = pss.map.get(s);
		pss.source.currentMinDistance = 0;
		
		pss.mst = pss.getMST();
		pss.printWeightSum();
		
	}	
	
	private List<Edge> getMST() {
		List<Edge> result = new ArrayList<>();
		
		PriorityQueue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if(o2.currentMinDistance<o1.currentMinDistance)
					return 1;
				else
					return -1;
			}
		});
		
		for(Node n:map.values())
			q.add(n);
		
		
		Map<Node,Edge> nodeToEdge = new HashMap<>();
		
		while(!q.isEmpty()){
			Node node = q.remove();
			Edge e = nodeToEdge.get(node);
			if(e!=null)
				result.add(e);
			
			for(Node adjNode : node.adjNodes.values()){
				int w = node.currentMinDistance+node.edges.get(adjNode.index).weight;
				if(q.contains(adjNode) && adjNode.currentMinDistance>node.currentMinDistance+w){
					q.remove(adjNode);
					adjNode.currentMinDistance = node.currentMinDistance+w;
					q.add(adjNode);
					
					nodeToEdge.put(adjNode,node.edges.get(adjNode.index));
				}
			}
			
		}
		
		
		return result;
	}

	private void printWeightSum() {		
		int sum = 0;
		for(Edge e:mst)
			sum += e.weight;
		
		System.out.print(sum);
			
	}

	static class Node{
		int index;
		Node parent;
		Map<Integer,Edge> edges;
		Map<Integer,Node> adjNodes;
		
		int currentMinDistance;
		
		public Node(int index) {
			super();
			this.index = index;
			edges = new HashMap<>();
			adjNodes = new HashMap<>();
			currentMinDistance = Integer.MAX_VALUE;
		}
		
	}
	
	static class Edge{
		public Edge(Node terminal, int weight) {
			super();
			this.terminal = terminal;
			this.weight = weight;
		}
		Node terminal;
		int weight;
	}
	
	static class FastInput{
		BufferedReader br;
		StringTokenizer st;
		
		public FastInput(){
			br = new BufferedReader(new InputStreamReader(System.in));			
		}
		
		String next(){
			
			while(st==null || !st.hasMoreTokens()){
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}			
			return st.nextToken();
		}
		
		int nextInt(){
			return Integer.parseInt(next());
		}
		
	}

}
