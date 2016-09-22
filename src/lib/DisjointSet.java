package lib;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet {
	
	Map<Integer,Node> map = new HashMap<>();
	
	public void makeSet(Node node){
		Node n = new Node(node.index);
		n.rank = 0;
		n.parent = n.index;
		map.put(n.index, n);
	}
	
	public void union(int index1, int index2){		
		if(findSet(index1) == findSet(index2))
			return;		
	
		Node root1 = map.get(findSet(index1));
		Node root2 = map.get(findSet(index2));
		
		if(root1.rank>=root2.rank){
			root2.parent = root1.parent;
			if(root1.rank == root2.rank)
				root1.rank++;
		}else{
			root1.parent = root2.parent;			
		}
		
	}
	
	public int findSet(int index){
		Node n = map.get(index);
		if(n.parent == n.index)
			return n.index;
		
		n.parent = findSet(n.parent);
		return n.parent;
	}
	
	public static void main(String[] args) {
		DisjointSet ds = new DisjointSet();
        ds.makeSet(new Node(1));
        ds.makeSet(new Node(2));
        ds.makeSet(new Node(3));
        ds.makeSet(new Node(4));
        ds.makeSet(new Node(5));
        ds.makeSet(new Node(6));
        ds.makeSet(new Node(7));

        ds.union(1, 2);
        System.out.println(ds.findSet(2));
        ds.union(2, 3);
        System.out.println(ds.findSet(3));
        ds.union(4, 5);
        System.out.println(ds.findSet(4));
        ds.union(6, 7);
        System.out.println(ds.findSet(6));
        ds.union(5, 6);
        System.out.println(ds.findSet(7));
        ds.union(3, 7);
        System.out.println(ds.findSet(7));
        
        
        
        
        
        
        
	}
	
	public static class Node extends lib.Node{

		public Node(int index) {
			super(index);			
		}
		
		int rank;
		int parent;
		
	}

}
