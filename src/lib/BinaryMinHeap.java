package lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.Map;


public class BinaryMinHeap {	
	
	List<Node> heap;	
	public Map<Node,Integer> nodePosition;	
	
	public BinaryMinHeap() {
		super();
		heap = new ArrayList<Node>();
		nodePosition = new HashMap<>();
	}
	
	
	public void add(Node node){		
		heap.add(node);
		int position = heap.size()-1;		
		nodePosition.put(node, position);
		
		int parentPosition = (position-1)/2;
		
		while(parentPosition>=0){
			Node parentNode = heap.get(parentPosition);
			if(parentNode.data>node.data){
				swap(node,parentNode);
				parentPosition = (parentPosition-1)/2;
			}else{
				break;
			}
		}		
	}
	
	public Node extractMin(){
		if(heap.size()>1){			
			Node min = heap.get(0);		
			nodePosition.remove(min);
			
			Node last = heap.remove(heap.size()-1);
			nodePosition.remove(last);
			nodePosition.put(last, 0);
			heap.set(0, last);		
			
			
			//Bubble down
			while(true){
				int leftPos = (nodePosition.get(last)*2)+1;
				int rightPos = (nodePosition.get(last)*2)+2;
				
				if(leftPos>heap.size()-1)
					break;
				
				if(rightPos>heap.size()-1)
					rightPos = leftPos;
				
				Node leftNode = heap.get(leftPos);
				Node rightNode = heap.get(rightPos);
				
				Node smallest = (leftNode.data<rightNode.data)?leftNode:rightNode;
				if(last.data>smallest.data){
					swap(last,smallest);
				}else{
					break;
				}			
			}			
			return min;
		}else{
			return heap.remove(0);
		}
	}
	
	public boolean isEmpty(){
		return heap.isEmpty();
	}
	
	public void decrease(int positionInHeap, int value){
		Node node = heap.get(positionInHeap);
		node.data = value;
		
		int parentPosition = (positionInHeap-1)/2;
		
		while(parentPosition>=0){
			Node parentNode = heap.get(parentPosition);
			if(parentNode.data>node.data){
				swap(node,parentNode);
				parentPosition = (parentPosition-1)/2;
			}else{
				break;
			}
		}	
	}
	
	void swap(Node node1, Node node2){        
		int node1Position = nodePosition.get(node1);
		int node2Position = nodePosition.get(node2);
		
		heap.set(node1Position, node2);
		heap.set(node2Position, node1);
		
		int temp = node1Position;
		node1Position = node2Position;
		node2Position = temp;
		
		nodePosition.remove(node1);
		nodePosition.remove(node2);
		
		nodePosition.put(node1, node1Position);
		nodePosition.put(node2,node2Position);		
	}
	
	public boolean contains(Node node){
		return nodePosition.containsKey(node);
	}
	
	public int getSize(){
		return heap.size();
	}
	


	public void printMin(){
		System.out.println("Min: "+heap.get(0).data+", Index: "+heap.get(0).index);
	}
	
	public void printItemsBFS(){		
		Queue<Node> q = new LinkedList<Node>();
		
		int currentIndex = 0;
		q.add(heap.get(0));
		
		while(!q.isEmpty()){
			Node node = q.remove();
			currentIndex = nodePosition.get(node);
			
			System.out.print(node.data+" ");
			int left = (currentIndex*2)+1;
			int right = (currentIndex*2)+2;
			
			if(left<=heap.size()-1){
				q.add(heap.get(left));
			}
			
			if(right<=heap.size()-1){
				q.add(heap.get(right));
			}
		}
		
		System.out.println();
	}
	
	public static void main(String[] args) {		
		BinaryMinHeap minHeap = new BinaryMinHeap();
		minHeap.add(new Node(1,-1));		
		minHeap.add(new Node(6,2));		
		minHeap.add(new Node(3,6));		
		minHeap.add(new Node(7,4));		
		minHeap.add(new Node(2,5));		
		minHeap.add(new Node(7,7));		
		minHeap.add(new Node(5,19));		
		minHeap.add(new Node(6,3));		
		minHeap.add(new Node(8,39));		
		minHeap.add(new Node(28,34));		
		minHeap.add(new Node(38,45));		
		minHeap.add(new Node(58,13));			
		
		minHeap.decrease(10, -5);			
		minHeap.decrease(8, -2);		
		minHeap.printItemsBFS();
		
		System.out.println("-5 -2 6 -1 2 7 19 4 3 34 5 13 <- Correct output");
		
		
		minHeap.extractMin();
		minHeap.printItemsBFS();
		
		System.out.println("-2 -1 6 3 2 7 19 4 13 34 5 <- Correct output");
		
		minHeap.extractMin();
		minHeap.printItemsBFS();
		
		System.out.println("-1 2 6 3 5 7 19 4 13 34 <- Correct output");
		
		System.out.println();
		minHeap.printItemsInNodePositionMap();
		minHeap.printItemsInHeap();
	}


	private void printItemsInHeap() {		
		for(Node n:heap)
			System.out.print(n.data+" ");
		
		System.out.print(" <- heap");
		System.out.println();
	}


	private void printItemsInNodePositionMap() {		
		for(Integer i:nodePosition.values())
			System.out.print(heap.get(i).data+" ");
		
		System.out.print(" <- nodeMap");
		System.out.println();
	}

}
