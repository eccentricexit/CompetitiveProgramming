package hackerRank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import lib.DisjointSet.Node;
import lib.DisjointSet;
import lib.Edge;

public class KruskalReallySpecialSubtree {
	
	Map<Integer,Node> map = new HashMap<>();
	
	private int N;
	private int M;

	public static void main(String[] args) {
		FastInput sc = new FastInput();
		KruskalReallySpecialSubtree sst = new KruskalReallySpecialSubtree();

		sst.N = sc.nextInt();
		sst.M = sc.nextInt();	
		for(int m=1;m<=sst.M;m++){	
			int x = sc.nextInt();
			int y = sc.nextInt();
			int r = sc.nextInt();			
			Node s = sst.map.get(x);
			if(s == null)
				s = new Node(x);
			
			Node t = sst.map.get(y);
			if(t == null)
				t = new Node(y);			
			
			
			
			Edge e = s.adjEdges.get(y);
			if(e!=null)
				e = (e.w<r)?e:new Edge(x,y,r);
			else
				e = new Edge(x,y,r);
			s.adjEdges.put(y,e);
			
			
			e = t.adjEdges.get(x);
			if(e!=null)
				e = (e.w<r)?e:new Edge(y,x,r);
			else
				e = new Edge(y,x,r);
			t.adjEdges.put(x, e);
			
			s.adjNodes.put(t.index, t);
			t.adjNodes.put(s.index, s);			
			
			sst.map.put(s.index, s);
			sst.map.put(t.index, t);			
		}
		
		sc.nextInt();
		sc.close();
		
		sst.printSumOfMst();		
	}
	
	private void printSumOfMst() {
		int sum = 0;
		for(Edge e:getMST())
			sum += e.w;
		
		System.out.print(sum);
	}

	private List<Edge> getMST() {
		List<Edge> result = new ArrayList<Edge>();
		DisjointSet ds = new DisjointSet();
		for(Node node:map.values())
			ds.makeSet(node);
		
		
		List<Edge> edges = new ArrayList<>();
		boolean[][] isEdgeAdded = new boolean[N+1][N+1];
		
		for(Node node:map.values()){			
			for(Edge e:node.adjEdges.values()){
				if(isEdgeAdded[e.s][e.t] || isEdgeAdded[e.t][e.s])
					continue;
				
				isEdgeAdded[e.s][e.t] = isEdgeAdded[e.t][e.s] = true;
				edges.add(e);				
			}
		}		
		

		
		Collections.sort(edges,new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				if(o2.w<o1.w)
					return 1;
				else if(o2.w>o1.w)
					return -1;
				else {
					int sum1 = o1.s + o1.t + o1.w;
					int sum2 = o2.s + o2.t + o2.w;					
					if(sum2<sum1)
						return 1;
					else
						return -1;					
				}
			}
		});

		for(Edge e:edges){
			if(ds.findSet(e.s)==ds.findSet(e.t))
				continue;
			
			result.add(e);
			ds.union(e.s, e.t);
		}
		
		return result;
	}

	private static class FastInput{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;		
		
		public String next(){
			while(str==null || !str.hasMoreTokens()){
				try {
					str = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
			return str.nextToken();
		}
		
		public int nextInt(){
			return Integer.parseInt(next());
		}
		
		public void close(){
			try {
				br.close();
			} catch (IOException e) {			
				e.printStackTrace();
			}			
		}
	}
	
	static void printEdges(List<Edge> edges){
		System.out.println();
		
		for(Edge e:edges)
			System.out.println(e.s+" "+e.t+" "+e.w);
	}

}
