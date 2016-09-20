package hackerEarth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import lib.Graph;
import lib.Vertex;

public class ShortestPath {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		Graph<Integer> graph = new Graph<>(true);
		
		List<Edge> edges = new ArrayList<>();
		ArrayList<List<Node>> adjNodes = new ArrayList<List<Node>>();		
		
		for(int i=0;i<M;i++){
			int u = sc.nextInt()-1;
			int v = sc.nextInt()-1;
			int w = sc.nextInt();
			
			graph.addEdge(u, v, w);
			edges.add(new Edge(u,v,w));			
			
			while(adjNodes.size()<u+1){				
				adjNodes.add(new ArrayList<Node>());
			}
			
			
			List<Node> adjOfI = adjNodes.get(u);
			if(adjOfI==null){
				adjOfI = new ArrayList<Node>();				
			}			
			adjOfI.add(new Node(v,99999));
			
		}
		
		sc.close();
		
		ShortestPath sP = new ShortestPath();
		
		printList(sP.bellmanFord(graph));			
		printList(sP.dijkstra(edges,adjNodes,N));
		printList(sP.floydWarshall(edges,N));
		
	}	

	private int[] floydWarshall(List<Edge> edges, int N) {
		int[][] dist = new int[N][N];		
		int[] dis = new int[N];
		
		for(int i = 0; i < N; i++){
	        for(int j = 0; j < N; j++){	     
	        	if(i!=j){
	        		dist[i][j]=99999;
	        	}else{
	        		dist[i][j]=0;
	        	}
	        }
		}
		
		dist[0][0] = 0;
		
		for(Edge e:edges){
			dist[e.u][e.v] = e.w;
		}
		
		for(int k = 0; k < N; k++){
		    for(int i =0; i < N; i++){
		        for(int j = 0; j < N; j++){
		            dist[i][j] = Math.min( dist[i][j], dist[i][k] + dist[k][j] );		            
		        }
		    }
		}
		
		for(int i=0;i<N;i++)
			dis[i] = dist[0][i];
		
		
		return dis;
	}

	int[] dijkstra(List<hackerEarth.Edge> edges, ArrayList<List<Node>> adjNodes, int N) {
		int[] dis = new int[N];
		boolean[] vis = new boolean[N];
		
		for(int i=0;i<dis.length;i++){			
			dis[i] = 99999;
		}
		dis[0] = 0;
		
		PriorityQueue<Node> q = new PriorityQueue<Node>(new Comparator<Node>() {			
			@Override
			public int compare(Node o1, Node o2) {
				if(o1.currentDistance<o2.currentDistance){
					return -1;
				}else{
					return 1;
				}
			}
		});		 
		 
		q.add(new Node(0,dis[0]));
				
		while(!q.isEmpty()){
			Node n = q.remove();
			if(vis[n.index])
				continue;
			
			vis[n.index] = true;
			
			if(adjNodes.size()<n.index+1 || adjNodes.get(n.index) == null || adjNodes.get(n.index).size()==0)
				continue;
			
			for(Node adjNode:adjNodes.get(n.index)){				
				int w = 0;
				for(Edge e:edges){
					if(e.u==n.index && e.v == adjNode.index){
						w=e.w;
						break;
					}
				}
				
				//relax
				if(dis[adjNode.index]>dis[n.index]+w){
					dis[adjNode.index]=dis[n.index]+w;
					adjNode.currentDistance = dis[adjNode.index];
					adjNode.parent = n;
					q.add(adjNode);
				}
			}			
		}
		
		return dis;
		
	}

	@SuppressWarnings("unused")
	private Vertex<Integer> getVertexForEdge(lib.Edge<Integer> e, Vertex<Integer> current) {		
		return e.getVertex1().equals(current) ? e.getVertex2() : e.getVertex1();
	}

	private static void printList(int[] js) {
		System.out.println();
		for(int i =1;i<js.length;i++)
			System.out.print(js[i] + " ");
		
	}
	
	@SuppressWarnings("unused")
	private static void printList(Collection<Integer> collection) {
		System.out.println();
		int j=0;
		for(Integer i:collection){
			if(j==collection.size()-1)
				break;
			System.out.print(i+" ");
			j++;
		}
	}

	public int[] bellmanFord(Graph<Integer> graph) {
		int V = graph.getAllVertex().size();
		
		int[] dis = new int[V];
		for(int i=0;i<V;i++)
			dis[i]=99999;
		
		dis[0]=0;
		
		Integer[] parent = new Integer[V];		
		
		for(int i=1;i<graph.getAllVertex().size()-1;i++){			
			for(lib.Edge<Integer> e:graph.getAllEdges()){
				bellmanFordRelax(e,dis,parent);
			}
		}
		
		return dis;
	}

	private void bellmanFordRelax(lib.Edge<Integer> e, int[] dis, Integer[] parent) {
		int v1Index = (int) e.getVertex1().getId();
		int v2Index = (int) e.getVertex2().getId();
		
		if(dis[v1Index]+e.getWeight()<dis[v2Index]){
			dis[v2Index] = dis[v1Index]+e.getWeight();
			parent[v2Index] = v1Index;
		}		
	}
	
	
}

class Node{
	int index;
	int currentDistance;
	Node parent;
	
	public Node(int index,int currentDistance){
		this.index = index;
		this.currentDistance = currentDistance;
	}		
}

class Edge{
	int u;
	int v;
	int w;
	
	public Edge(int u, int v, int w) {
		super();
		this.u = u;
		this.v = v;
		this.w = w;
	}

	
	
}
