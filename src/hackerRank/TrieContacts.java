/*******************************************************************************
 * Author: Matheus Faria de Alencar
 *******************************************************************************/
package hackerRank;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import lib.FastIO;

public class TrieContacts {

	TrieNode trieRoot;
	Map<String,TrieNode> wordMap;
	
	public TrieContacts() {
		super();
		trieRoot = new TrieNode(null);
		wordMap = new HashMap<>();
	}
	
	public void add(String partial){
		add(partial.toCharArray(),0,trieRoot);
	}
	
	private void add(char[] partial,int i,TrieNode current){
		TrieNode next = current.children.get(partial[i]);
		if(next==null){
			current.children.put(partial[i], new TrieNode(current));			
			next = current.children.get(partial[i]);
		}
		
		if(i+1<partial.length){
			add(partial,i+1,next);
		}else{
			next.isEndOfWord = true;
			if(!wordMap.containsKey(new String(partial))){
				wordMap.put(new String(partial), next);
				updateWordCount(next);
			}			
		}
	}	
	
	/**
	 * Moves up the trie incrementing wordCount of every node.
	 * This way every node has a count of how many words are under them.
	 * @param last
	 */
	private void updateWordCount(TrieNode last) {
		last.wordCount = 1;
		TrieNode parent = last.parent;
		while(parent!=null){
			parent.wordCount++;
			parent = parent.parent;
		}		
	}

	public int countOccurrences(String partial){
		TrieNode last = findLastNode(partial);
		if(last == null)
			return 0;
		
		return last.wordCount;
	}
	
	private TrieNode findLastNode(String partial){
		TrieNode lastNode = wordMap.get(partial); 
		if(lastNode == null){
			//traverse
			TrieNode current = trieRoot;
			for(int i=0;i<partial.length();i++){
				TrieNode node = current.children.get(partial.charAt(i));
				if(node==null)
					return null;
				
				current = node;
			}
			lastNode = current;
		}
		
		return lastNode;
	}
	
	/**
	 * This counts the occurrences using DFS but is not fast enough.
	 * For better performance TrieNodes now have wordCount on them
	 * which get updated after a new word is added.
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private int countOccurrencesDFS(String partial,TrieNode current){
		int count = 0;		
		Stack<TrieNode> s = new Stack<>();
		s.push(current);		
		
		while(!s.isEmpty()){
			TrieNode node = s.pop();
			if(node.isEndOfWord)
				count++;
			
			for(TrieNode child:node.children.values())
				s.push(child);			
		}
		
		return count;
	}
	
	private static class TrieNode{
		Map<Character,TrieNode> children;
		boolean isEndOfWord;
		int wordCount;
		TrieNode parent;
		
		public TrieNode(TrieNode parent) {
			children = new HashMap<>();
			isEndOfWord = false;
			wordCount=0;
			this.parent = parent;
		}		
	}
	
	public static void main(String[] args) {
		TrieContacts contacts = new TrieContacts();		
		FastIO sc = new FastIO();		
		
		int N = sc.nextInt();		
		for(int n=1;n<=N;n++){
			String operation = sc.next();
			String partial = sc.next();
			
			switch(operation){
				case "add":{
					contacts.add(partial);
					break;
				}
				case "find":{
					System.out.println(contacts.countOccurrences(partial));
					break;
				}
			}
		}
	}

}
