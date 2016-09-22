package lib;

import java.util.HashMap;
import java.util.Map;

public class Node {
	public int index;
	
	public Map<Integer,Node> adjNodes;
	public Map<Integer,Edge> adjEdges;
	public int data;	

		
	public Node(int index) {
		super();
		this.index = index;
		adjNodes = new HashMap<>();
		adjEdges = new HashMap<>();
	}
	
	public Node(int index,int data) {
		super();
		this.index = index;
		adjNodes = new HashMap<>();
		adjEdges = new HashMap<>();
		this.data = data;
	}

	public static Node copy(Node node) {
		Node copy = new Node(node.index);
		copy.adjEdges = node.adjEdges;
		copy.adjNodes = node.adjNodes;
		copy.data = node.data;		
		
		return copy;
	}
	
	
}
