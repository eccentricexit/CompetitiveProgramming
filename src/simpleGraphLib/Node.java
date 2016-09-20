package simpleGraphLib;

import java.util.HashMap;
import java.util.Map;

public class Node {
	int index;
	Node parent;
	Map<Integer,Node> adjNodes;
	Map<Integer,Edge> adjEdges;
	public Node(int index) {
		super();
		this.index = index;
		adjNodes = new HashMap<>();
		adjEdges = new HashMap<>();
	}
	
	
}
