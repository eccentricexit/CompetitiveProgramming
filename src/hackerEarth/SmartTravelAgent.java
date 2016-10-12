/*******************************************************************************
 * Author: Matheus Faria de Alencar
 *******************************************************************************/
package hackerEarth;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;



public class SmartTravelAgent {
	
	Map<Integer,Node> map = new HashMap<Integer,Node>();
	Set<Node> visited = new HashSet<>();
	Stack<Integer> route = new Stack<>();
	
	Node source;
	Node terminal;
	int passengers;

	public static void main(String[] args) {	
		
		InputReader ir = new InputReader(System.in);
		SmartTravelAgent sta = new SmartTravelAgent();
		
		ir.readInt();
		int M = ir.readInt();

		for(int m=1;m<=M;m++){
			int c1 = ir.readInt();
			int c2 = ir.readInt();
			int w = ir.readInt();
			
			Node n1 = sta.map.get(c1);
			if(n1 == null)
				n1 = sta.new Node(c1);
			
			Node n2 = sta.map.get(c2);
			if(n2 == null)
				n2 = sta.new Node(c2);
			
			
			n1.adjNodes.put(c2, n2);
			n2.adjNodes.put(c1, n1);
			
			
			Edge out = n1.adjEdge.get(c2);
			if(out==null)
				out = sta.new Edge(n1,n2,w);
			
			n1.adjEdge.put(c2,out);
			
			Edge in = n2.adjEdge.get(c1);
			if(in == null)
				in = sta.new Edge(n2,n1,w);
			
			n2.adjEdge.put(c1, in);
			
			sta.map.put(c1, n1);
			sta.map.put(c2, n2);			
		}
		
		sta.source = sta.map.get(ir.readInt());
		sta.terminal = sta.map.get(ir.readInt());
		
		sta.passengers = ir.readInt();
		
		sta.dijkstra();
		sta.printBestRoute();
		System.out.println();
		sta.printMinNumberTrips();
	}
	
	private void printMinNumberTrips() {		
		int maxPerTrip = terminal.currentBottleNeck;
		int remainingPassangers = passengers;	

		int count =0;		
		while (remainingPassangers>0)
		{
			remainingPassangers -= maxPerTrip-1;
			count++;
		}

		
		System.out.print(count);
		
	}

	private void printBestRoute() {		
		while(!route.isEmpty())
			System.out.print(route.pop()+" ");
	}

	private void dijkstra() {
		 
		source.currentBottleNeck = Integer.MAX_VALUE;
		
		PriorityQueue<Node> q = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if(o2.currentBottleNeck>o1.currentBottleNeck)
					return 1;
				else
					return -1;
				
			}			
		}); 
		
		q.add(source);
		
		while(!q.isEmpty()){
			Node node = q.remove();
			if(visited.contains(node))
				continue;
			
			visited.add(node);			
			
			for(Node adjNode:node.adjNodes.values()){
				Edge e = node.adjEdge.get(adjNode.index);
								
				if(adjNode.currentBottleNeck<Math.min(node.currentBottleNeck,e.weight)){
					adjNode.currentBottleNeck = Math.min(node.currentBottleNeck,e.weight);
					adjNode.parent = node;
					q.add(adjNode);
				}
			}			
		}
		
		Node nextNode = terminal.parent;
		route.add(terminal.index);
		while(nextNode!=null){
			route.add(nextNode.index);
			nextNode = nextNode.parent;
		}
	}

	class Node{
		int index;	
		Node parent;
		int currentBottleNeck;
		Map<Integer,Node> adjNodes;
		Map<Integer,Edge> adjEdge;
		
		public Node(int index) {
			super();
			this.index = index;			
			this.currentBottleNeck = -1;
			this.adjNodes = new HashMap<>();
			this.adjEdge = new HashMap<>();
		}
	}
	
	class Edge{
		int weight;
		Node source;
		Node terminal;
		
		public Edge(Node source, Node terminal,int weight) {
			super();
			this.weight = weight;
			this.source = source;
			this.terminal = terminal;
		}		
	}

}


class InputReader {
	 
	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;
	private SpaceCharFilter filter;

	public InputReader(InputStream stream) {
		this.stream = stream;
	}

	public int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	public int readInt() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public String readString() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
	}

	public boolean isSpaceChar(int c) {
		if (filter != null)
			return filter.isSpaceChar(c);
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	public String next() {
		return readString();
	}

	public interface SpaceCharFilter {
		public boolean isSpaceChar(int ch);
	}
}