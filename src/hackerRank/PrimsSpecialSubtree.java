package hackerRank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import lib.BinaryMinHeap;
import lib.Edge;
import lib.Node;

public class PrimsSpecialSubtree {
		
	Map<Integer,Node> map = new HashMap<>();	
	private static int S;
	
	public static void main(String[] args) {
		FastInput fi = new FastInput();
		PrimsSpecialSubtree pss = new PrimsSpecialSubtree();
		fi.nextInt();
		int M = fi.nextInt();
		
		for(int m=1;m<=M;m++){
			int x = fi.nextInt()-1;
			int y = fi.nextInt()-1;
			int w = fi.nextInt();
			
			Node s = pss.map.get(x);
			if(s==null)
				s=new Node(x,Integer.MAX_VALUE);
							
			Node t = pss.map.get(y);
			if(t==null)
				t=new Node(y,Integer.MAX_VALUE);
			
			s.adjEdges.put(y, new Edge(x,y, w));
			t.adjEdges.put(x, new Edge(y,x,w));
			
			s.adjNodes.put(y, t);
			t.adjNodes.put(x, s);
			
			pss.map.put(x, s);
			pss.map.put(y, t);			
		}		
		S =fi.nextInt()-1;			
		
		pss.printWeightSum();
		
	}	
	
	private List<Edge> getMST() {
		List<Edge> result = new ArrayList<>();		
		
		BinaryMinHeap minHeap = new BinaryMinHeap();
		Map<Integer,Edge> nodeToEdge = new HashMap<>();
		
		for(Node n:map.values())
			minHeap.add(n);		
		
		minHeap.decrease(S, 0);
		
		while(!minHeap.isEmpty()){
			Node min = minHeap.extractMin();
			
			
			if(nodeToEdge.containsKey(min.index))
				result.add(nodeToEdge.get(min.index));
			
			for(Node adjNode : min.adjNodes.values()){
				if(!minHeap.contains(adjNode))
					continue;
				
				Edge edgeToAdjNode = min.adjEdges.get(adjNode.index);
				if(adjNode.data>edgeToAdjNode.w){
					minHeap.decrease(minHeap.nodePosition.get(adjNode), min.adjEdges.get(adjNode.index).w);
					nodeToEdge.put(adjNode.index,  min.adjEdges.get(adjNode.index));
				}
			}
		}				
		
		return result;
	}

	
	
	private void printWeightSum() {		
		int sum = 0;
		for(Edge e:getMST())
			sum += e.w;
		
		System.out.print(sum);
			
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
