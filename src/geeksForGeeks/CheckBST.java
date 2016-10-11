package geeksForGeeks;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CheckBST {
	
	static Map<Integer,Node> map = new HashMap<>();
	static Node root;

	public static void main(String[] args) {
		int[] nodes = new int[]{3,2,5,1};
		for(Integer i:nodes){
			add(new Node(i),root);
		}
		
		map.get(2).right = new Node(4);
		
		printTreeBFS();		
		
		System.out.println(isBST(root,Integer.MIN_VALUE));

	}
	
	private static boolean isBST(Node root, int previous) {		
		if(root.left!=null && root.left.index!=previous){
			isBST(root.left,previous);
		}
		
		if(previous>root.index)
			return false;
		
		previous = root.index;
		
		if(root.right !=null)			
			return isBST(root.right,previous);
		
		return (root.index>previous);
	}
	


	private static class Node{
		
		public Node(int index) {
			super();
			this.index = index;
		}
		
		int index;
		Node left;
		Node right;
		public void print() {
			System.out.print(index+" ");
			
		}
	}
	
	static void add(Node node, Node parent){
		if(root==null){
			root = node;
			map.put(node.index,node);
			return;
		}
		
		if(node.index<parent.index){
			if(parent.left!=null){
				add(node,parent.left);
			}else{
				parent.left = node;
				map.put(node.index, node);
			}
		}else{
			if(parent.right!=null){
				add(node,parent.right);
			}else{
				parent.right = node;
				map.put(node.index, node);
			}
		}
	}
	
	static void printTreeBFS(){		
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		q.add(null);		

		if(q.peek()==null){
			System.out.println("Árvore vazia.");
			return;
		}				


		while(!q.isEmpty()){
			Node node = q.remove();

			if(node!=null){
				node.print();
				if(node.left!=null)
					q.add(node.left);

				if(node.right!=null)
					q.add(node.right);				
			}else{
				System.out.println();

				if(!q.isEmpty())
					q.add(null);
			}
		}

		System.out.println();
	}

}
