/*******************************************************************************
 * Author: Matheus Faria de Alencar
 *******************************************************************************/
package geeksForGeeks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import lib.Node;

public class TopologicalSort {
	
	Map<Integer,Node> map = new HashMap<>();	
	Stack<Node> stack = new Stack<>();
	Set<Node> visited = new HashSet<>();
	
	public static void main(String[] args) {		
		TopologicalSort ts = new TopologicalSort();
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();		
		
		for(int test=1;test<=T;test++){
			int E = sc.nextInt();
			sc.nextInt();
			
			for(int e=1;e<=E;e++){
				int u = sc.nextInt();
				int v = sc.nextInt();
				
				Node s = ts.map.get(u);
				if(s==null)
					s = new Node(u);
				
				Node t = ts.map.get(v);
				if(t==null)
					t = new Node(v);
				
				
				s.adjNodes.put(t.index, t);
				
				
				ts.map.put(s.index, s);
				ts.map.put(t.index, t);				
			}
		}
		sc.close();
		
		int[] arr = ts.topoSort();
		printaArr(arr);
		
		
	}

	private static void printaArr(int[] arr) {
		for(Integer i:arr)
			System.out.print(i+" ");
		
	}

	public int[] topoSort() {	
		for(Node n:map.values()){
			topoSort(n);
		}
		
		int[] result = new int[map.size()];
		int i=0;
		while(!stack.isEmpty()){
			result[i]=stack.pop().index;
			i++;
		}
		
		return result;
	}	
	
	private void topoSort(Node n){
		if(visited.contains(n))
			return;
		
		visited.add(n);		
		if(n.adjNodes.size()>0){
			for(Node child:n.adjNodes.values()){
				topoSort(child);
			}
			stack.add(n);
		}else{
			stack.add(n);
		}
	}
	
	
	

}














