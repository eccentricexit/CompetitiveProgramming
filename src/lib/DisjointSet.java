package lib;

import java.util.HashMap;
import java.util.Map;


public class DisjointSet {
	
	Map<Long,Node> map = new HashMap<>();
	
	class Node{
		Node parent;
		long data;
		int rank;
		
		public Node(long data){
			this.data = data;
			parent = this;
			rank = 0;
		}
	}
	
	public void makeSet(long data){
		 Node node = new Node(data);
		 map.put(data, node);		 
	}
	
	public void union(long data1, long data2){
		Node node1 = map.get(data1);
		Node node2 = map.get(data2);
		
		Node parent1 = findSet(node1);
		Node parent2 = findSet(node2);
				
		//are already in the same set
		if(parent1.data == parent2.data)
			return;
		
		if(parent1.rank>=parent2.data){
			parent1.rank = (parent1.rank==parent2.rank)?
					parent1.rank + 1: parent1.rank;
			
			parent2.parent = parent1;			
		}else{
			parent1.parent = parent2;
		}		
	}
	
	public long findSet(long data){
		return findSet(map.get(data)).data;
	}

	private Node findSet(Node node) {
		if(node.parent == node)
			return node;	
		
		node.parent = findSet(node.parent);
		return node.parent;
	}
	
	

}