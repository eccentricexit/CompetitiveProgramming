/*******************************************************************************
 * Author: Matheus Faria de Alencar
 *******************************************************************************/
package hackerEarth;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class TopologicalSort {

	Map<Integer,Node> map = new HashMap<>();
	Set<Node> visited = new HashSet<>();
	Stack<Node> result = new Stack<>();
	
	public static void main(String[] args) {
		TopologicalSort ts= new TopologicalSort();
		
		Scanner sc = new Scanner(System.in);
		sc.nextInt();
		int M = sc.nextInt();		
		
		for(int i=1;i<=M;i++){
			int s = sc.nextInt();
			int t = sc.nextInt();
			
			Node sNode = ts.map.get(s);
			if(sNode==null)
				sNode = ts.new Node(s,null);
			
			Node tNode = ts.map.get(t);
			if(tNode==null)
				tNode = ts.new Node(t,null);
			
			sNode.children.put(t, tNode);
			tNode.parent = sNode;
			
			ts.map.put(s, sNode);
			ts.map.put(t, tNode);
		}
		sc.close();	
		
		ts.topologicalSortAllNodes();
		
		while(!ts.result.isEmpty())
			System.out.print(ts.result.pop().index + " ");
	}
	
	void topologicalSortAllNodes(){		
		for(Node node:map.values()){
			topologicalSort(node);		
		}		
	}
	
	private void topologicalSort(Node node) {
		if(visited.contains(node))
			return;
		
		visited.add(node);
		
		for(Node child:node.children.values()){
			topologicalSort(child);
		}
		
		result.push(node);		
	}

	class Node{		
		int index;
		Node parent;
		Map<Integer,Node> children;
		
		Node(int index, Node parent) {
			super();
			this.index = index;
			this.parent = parent;
			children = new HashMap<>();
		}
	}
}

