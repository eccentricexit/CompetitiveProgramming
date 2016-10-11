package hackerRank;

import java.util.LinkedList;
import java.util.Queue;

public class IsThisABinarySearchTree {

	public static void main(String[] args) {		

	}
	
	 boolean checkBST(Node root) {	        
	        Queue<Node> q = new LinkedList<Node>();
	        
	        if(root.left!=null)
	        	inOrder(root.left,q);
	        q.add(root);
	        
	        if(root.right!=null)
	        	inOrder(root.right,q);
	        
	        
	        return checkAscendingOrder(q);        
	   }

	private void inOrder(Node root, Queue<Node> q) {
		if(root.left!=null)
        	inOrder(root.left,q);
        q.add(root);
        
        if(root.right!=null)
        	inOrder(root.right,q);		
	}

	private boolean checkAscendingOrder(Queue<Node> q) {
		Node previous = q.remove();
		if(previous==null)
		    return true;
		
		while(!q.isEmpty()){
		    Node current = q.remove();
		    if(current.data <= previous.data)
		        return false;
		    previous = current;
		}
		
		return true;
	}
	 
	 static class Node{
		 public Node(int i) {
			data = i;
		}
		int data;
		 Node left;
		 Node right;		 
	 }

}
