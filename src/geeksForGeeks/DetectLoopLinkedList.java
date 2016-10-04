package geeksForGeeks;

import java.util.HashSet;
import java.util.Set;

public class DetectLoopLinkedList {

	public static void main(String[] args) {
		Node head = new Node(50);
		head.next = new Node(20);
	    head.next.next = new Node(15);
	    head.next.next.next = new Node(4);
	    head.next.next.next.next = new Node(10);
	    
	    head.next.next.next.next.next = head.next.next;	    
	    
	    System.out.println("has loop: "+((detectAndRemoveLoop(head))?"Yes.":"No."));
	    
	    head.printList();
	}
	
	private static boolean detectAndRemoveLoop(Node head) {
		Set<Node> set = new HashSet<>();
		set.add(head);
		
		Node current = head;
		while(current.next!=null){
			set.add(current);
			
			if(set.contains(current.next)){
				current.next = null;
				return true;
			}else{
				if(current.next!=null)
					current = current.next;				
			}
		}
		
		return false;
	}

	private static class Node{
		int data;
		Node next;
		public Node(int data) {
			super();
			this.data = data;
		}
		public void printList() {
			System.out.print(data+" ");
			if(next!=null)
				next.printList();
			
		}		
	}

}
