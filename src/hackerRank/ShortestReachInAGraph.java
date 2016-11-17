package hackerRank;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import lib.FastIO;

public class ShortestReachInAGraph {
	private Map<Integer,Set<Integer>> adjList;	
	private boolean[] visited;
	private Map<Integer,Integer> distance;
	private int source;

	public ShortestReachInAGraph(Map<Integer, Set<Integer>> adjList, int source,int n) {
		visited = new boolean[n];
		this.adjList = adjList;		
		this.source = source;
	}

	public static void main(String[] args) {
		FastIO sc = new FastIO();
		int Q = sc.nextInt();
		
		
		sc.next();
		
		for(int q=1;q<=Q;q++){
			
			int N = sc.nextInt(); 		
			int M = sc.nextInt();			
			
			Map<Integer,Set<Integer>> adjList = new HashMap<>();
			for(int m=1;m<=M;m++){
				int u = sc.nextInt();
				int v = sc.nextInt();
				
				if(!adjList.containsKey(u))
					adjList.put(u, new HashSet<>());
				
				if(!adjList.containsKey(v))
					adjList.put(v, new HashSet<>());
				
				adjList.get(u).add(v);
				adjList.get(v).add(u);				
			}	
			
			int S = sc.nextInt();
			ShortestReachInAGraph graph = new ShortestReachInAGraph(adjList,S,N);				
			graph.printShortestDistances();
			System.out.println();
		}
		sc.close();
	}

	private void printShortestDistances() {
		if(distance==null)
			distance = getShortestDistances();
		
		for(int i=0;i<visited.length;i++){
			if(i==source-1)
				continue;
			
			if(visited[i])
				System.out.print(distance.get(i+1)+" ");
			else
				System.out.print("-1 ");
		}
	}

	private Map<Integer, Integer> getShortestDistances() {
		Map<Integer,Integer> distance = new HashMap<>();
		
		Queue<Integer> q = new LinkedList<>();
		q.add(source);
		q.add(null); //used to track depth
		
		int currentDepth = 0;
		
		while(!q.isEmpty()){
			Integer node = q.remove();
			if(node==null){
				currentDepth++;
				if(!q.isEmpty())
					q.add(null);
				continue;
			}				
			
			if(visited[node-1])
				continue;
			
			visited[node-1] = true;
			distance.put(node, currentDepth*6);
			
			Set<Integer> children = adjList.get(node);
			if(children==null)
				continue;
			
			Iterator<Integer> iterator = children.iterator();			
			while(iterator.hasNext()){
				Integer child = iterator.next();
				if(visited[child-1])
					continue;
				
				q.add(child);
			}		
			
		}		
		
		return distance;
	}

}
