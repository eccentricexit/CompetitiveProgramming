package hackerRank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

//TIMEOUT FOR TESTCASES 7 AND 9
public class DagQueries {
	
	Map<Integer,Node> map;

	public DagQueries(int n) {
		super();
		this.map = new HashMap<>();
		for(int i=1;i<=n;i++)
			map.put(i, new Node(i));
	}

	public static void main(String[] args) {
		FastIO sc = new FastIO();
		int N = sc.nextInt();
		int M = sc.nextInt();
		int Q = sc.nextInt();
		
		DagQueries dq = new DagQueries(N);		
		
		for (int m = 0; m < M; m++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			
			dq.addEdge(u,v);
		}
		
		for(int q=1;q<=Q;q++){
			int code = sc.nextInt();
			int source = sc.nextInt();
			
			switch (code) {
			case 1:
				dq.setChildNodesTo(sc.nextInt(),source);
				break;
			case 2:
				dq.setChildNodesAvGreaterThanXTo(sc.nextInt(),source);
				break;
			case 3:
				dq.printValueOf(source);
				break;			
			}
		}
		
		
		sc.close();
	}
	
	private void setChildNodesAvGreaterThanXTo(int x, int source) {
		Stack<Node> s = new Stack<>();
		Set<Node> visited = new HashSet<>();
		
		Node u = map.get(source);				
		s.add(u);		
		while(!s.isEmpty()){
			Node n = s.pop();
			if(visited.contains(n))
				continue;
			
			visited.add(n);
			
			if(n.av>x)
				n.av = x;
			
			for(Node child:n.children)
				s.add(child);
		}
	}

	private void setChildNodesTo(int x, int source) {
		Stack<Node> s = new Stack<>();
		Set<Node> visited = new HashSet<>();		
		
		Node u = map.get(source);		
		s.add(u);
		while(!s.isEmpty()){
			Node n = s.pop();
			if(visited.contains(n))
				continue;
			
			visited.add(n);
			
			n.av = x;
			
			for(Node child:n.children)
				s.add(child);
		}
		
	}

	private void printValueOf(int source) {
		Node n = map.get(source);
		if(n!=null)
			System.out.println(n.av);
		
	}

	private void addEdge(int u, int v) {
		Node s = map.get(u);
		if(s==null)
			s = new Node(u);
		
		Node t = map.get(v);
		if(t==null)
			t = new Node(v);
		
		s.children.add(t);		
		map.put(s.index, s);
		map.put(t.index, t);
	}

	private static class Node{
		int index;
		int av;
		Set<Node> children;
		
		public Node(int index) {
			super();
			this.index = index;
			this.av = 0;
			this.children = new HashSet<>();
		}	
		
	}
	
	private static class FastIO {
		
		BufferedReader br;
		StringTokenizer str;
		
		public FastIO(){
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		
		public String next(){
			while(str==null || !str.hasMoreTokens()){
				try {
					str = new StringTokenizer(br.readLine());
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}		
			
			return str.nextToken();
		}
		
		public int nextInt(){
			return Integer.parseInt(next());
		}

		public void close() {
			try {
				br.close();
			} catch (IOException e) {			
				e.printStackTrace();
			}		
		}
	}

}
