/*******************************************************************************
 * Author: Matheus Faria de Alencar
 *******************************************************************************/
package hackerEarth;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class MinimumSpanningTrees {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.nextInt();
		int M = sc.nextInt();
		
		List<Edge<Integer>> edges = new ArrayList<>();
		HashMap<Integer,Vertex<Integer>> vertices = new HashMap<>();
		
		Graph<Integer> graph = new Graph<>(false);
		
		for(int i=1;i<=M;i++){
			Vertex<Integer> v = new Vertex<>(sc.nextInt());
			Vertex<Integer> u = new Vertex<>(sc.nextInt());
			int w = sc.nextInt();
			
			Edge<Integer> e1 = new Edge<>(v,u,w);
			Edge<Integer> e2 = new Edge<>(u,v,w);
			
			v.addAdjacentVertex(e1, u);
			u.addAdjacentVertex(e2, v);
			
			edges.add(e1);
			edges.add(e2);
			
			graph.addEdge(v.getId(), u.getId(),w);
			
			if(!vertices.containsKey(v.getId())){
				vertices.put((int) v.getId(), v);
			}
			
			if(!vertices.containsKey(u.getId())){
				vertices.put((int) u.getId(), u);
			}
		}
		
		sc.close();
		
		MinimumSpanningTrees mst = new MinimumSpanningTrees();		
		System.out.println(mst.kruskal(edges,vertices));
		System.out.println(mst.prims(graph));	
		
	}

	
	//Algorithm from Tushar Roy's class on youtube - 13/Sep/2016
	public int prims(Graph<Integer> graph){
		
		int weight = 0;        
        BinaryMinHeap<Vertex<Integer>> minHeap = new BinaryMinHeap<>();

        //map of vertex to edge which gave minimum weight to this vertex.
        Map<Vertex<Integer>,Edge<Integer>> vertexToEdge = new HashMap<>();

        //stores final result
        List<Edge<Integer>> result = new ArrayList<>();

        //insert all vertices with infinite value initially.
        for(Vertex<Integer> v : graph.getAllVertex()){
            minHeap.add(Integer.MAX_VALUE, v);            
        }

        //start from any random vertex
        Vertex<Integer> startVertex = graph.getAllVertex().iterator().next();

        //for the start vertex decrease the value in heap + map to 0
        minHeap.decrease(startVertex, 0);

        //iterate till heap + map has no elements in it
        while(!minHeap.isEmpty()){
            //extract min value vertex from heap + map
            Vertex<Integer> current = minHeap.extractMin();

            //get the corresponding edge for this vertex if present and add it to final result.
            //This edge wont be present for first vertex.
            Edge<Integer> spanningTreeEdge = vertexToEdge.get(current);
            if(spanningTreeEdge != null) {
                result.add(spanningTreeEdge);
                weight += spanningTreeEdge.getWeight();
                //System.out.println(e.getVertex1()+" -> "+e.getVertex2()+" weight: "+ e.getWeight() );
            }

            //iterate through all the adjacent vertices
            for(Edge<Integer> edge : current.getEdges()){
                Vertex<Integer> adjacent = getVertexForEdge(current, edge);
                //check if adjacent vertex exist in heap + map and weight attached with this vertex is greater than this edge weight
                if(minHeap.containsKey(adjacent) && minHeap.getWeight(adjacent) > edge.getWeight()){
                    //decrease the value of adjacent vertex to this edge weight.
                    minHeap.decrease(adjacent, edge.getWeight());
                    //add vertex->edge mapping in the graph.
                    vertexToEdge.put(adjacent, edge);
                }
            }
        }
        return weight;
    }

	
	public Vertex<Integer> getVertexForEdge(Vertex<Integer> v, Edge<Integer> e){
        return e.getVertex1().equals(v) ? e.getVertex2() : e.getVertex1();
    }

	public int kruskal(List<Edge<Integer>> edges, HashMap<Integer,Vertex<Integer>> vertices){
		EdgeComparator ec = new EdgeComparator();
		Collections.sort(edges,ec);
		
		DisjointSet ds = new DisjointSet();
		
		ArrayList<Edge<Integer>> resultEdges = new ArrayList<Edge<Integer>>();
		
		for(Vertex<Integer> v:vertices.values()){
			ds.makeSet(v.getId());
		}		
		
		for(Edge<Integer> e:edges){		
			int root1 = (int) ds.findSet(e.getVertex1().getId());
			int root2 = (int) ds.findSet(e.getVertex2().getId());
			
			if(root1==root2)
				continue;
			
			resultEdges.add(e);
			ds.union(e.getVertex1().getId(), e.getVertex2().getId());
		}
		
		int totalWeight = 0;
		for(Edge<Integer> e:resultEdges){
			totalWeight += e.getWeight();
			//System.out.println(e.getVertex1()+" -> "+e.getVertex2()+" weight: "+ e.getWeight() );
		}
		
		return totalWeight;
	}
	
	private static class EdgeComparator implements Comparator<Edge<Integer>> {
        @Override
        public int compare(Edge<Integer> edge1, Edge<Integer> edge2) {
            if (edge1.getWeight() <= edge2.getWeight()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
	
	
    
    private static class DisjointSet {
    	
    	Map<Long,Node> map = new HashMap<>();
    	
    	class Node{
    		Node parent;
    		long data;
    		int rank;
    		
    		public Node(long data){
    			this.data = data;
    			parent = this;
    			rank = 0;
    		}
    	}
    	
    	public void makeSet(long data){
    		 Node node = new Node(data);
    		 map.put(data, node);		 
    	}
    	
    	public void union(long data1, long data2){
    		Node node1 = map.get(data1);
    		Node node2 = map.get(data2);
    		
    		Node parent1 = findSet(node1);
    		Node parent2 = findSet(node2);
    				
    		//are already in the same set
    		if(parent1.data == parent2.data)
    			return;
    		
    		if(parent1.rank>=parent2.data){
    			parent1.rank = (parent1.rank==parent2.rank)?
    					parent1.rank + 1: parent1.rank;
    			
    			parent2.parent = parent1;			
    		}else{
    			parent1.parent = parent2;
    		}		
    	}
    	
    	public long findSet(long data){
    		return findSet(map.get(data)).data;
    	}

    	private Node findSet(Node node) {
    		if(node.parent == node)
    			return node;	
    		
    		node.parent = findSet(node.parent);
    		return node.parent;
    	}

    }

    private static class Edge<T>{
        private boolean isDirected = false;
        private Vertex<T> vertex1;
        private Vertex<T> vertex2;
        private int weight;
        
        Edge(Vertex<T> vertex1, Vertex<T> vertex2,boolean isDirected,int weight){
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
            this.isDirected = isDirected;
        }
        
        public Edge(Vertex<T> vertex1, Vertex<T> vertex2,int weight){
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;        
        }
        
        public Vertex<T> getVertex1(){
            return vertex1;
        }
        
        public Vertex<T> getVertex2(){
            return vertex2;
        }
        
        public int getWeight(){
            return weight;
        }
        
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((vertex1 == null) ? 0 : vertex1.hashCode());
            result = prime * result + ((vertex2 == null) ? 0 : vertex2.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Edge<?> other = (Edge<?>) obj;
            if (vertex1 == null) {
                if (other.vertex1 != null)
                    return false;
            } else if (!vertex1.equals(other.vertex1))
                return false;
            if (vertex2 == null) {
                if (other.vertex2 != null)
                    return false;
            } else if (!vertex2.equals(other.vertex2))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "Edge [isDirected=" + isDirected + ", vertex1=" + vertex1
                    + ", vertex2=" + vertex2 + ", weight=" + weight + "]";
        }
    }

    private static class Vertex<T> {
        long id;
        private List<Edge<T>> edges = new ArrayList<>();
        private List<Vertex<T>> adjacentVertex = new ArrayList<>();
        
        public Vertex(long id){
            this.id = id;
        }
        
        public long getId(){
            return id;
        }
        
        public void addAdjacentVertex(Edge<T> e, Vertex<T> v){
            edges.add(e);
            adjacentVertex.add(v);
        }
        
        @Override
        public String toString(){
            return String.valueOf(id);
        }
        
        public List<Edge<T>> getEdges(){
            return edges;
        }
        
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (int) (id ^ (id >>> 32));
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Vertex<?> other = (Vertex<?>) obj;
            if (id != other.id)
                return false;
            return true;
        }
    }

    private static class Graph<T>{

        private List<Edge<T>> allEdges;
        private Map<Long,Vertex<T>> allVertex;
        boolean isDirected = false;
        
        public Graph(boolean isDirected){
            allEdges = new ArrayList<Edge<T>>();
            allVertex = new HashMap<Long,Vertex<T>>();
            this.isDirected = isDirected;
        }
        
        public void addEdge(long id1,long id2, int weight){
            Vertex<T> vertex1 = null;
            if(allVertex.containsKey(id1)){
                vertex1 = allVertex.get(id1);
            }else{
                vertex1 = new Vertex<T>(id1);
                allVertex.put(id1, vertex1);
            }
            Vertex<T> vertex2 = null;
            if(allVertex.containsKey(id2)){
                vertex2 = allVertex.get(id2);
            }else{
                vertex2 = new Vertex<T>(id2);
                allVertex.put(id2, vertex2);
            }

            Edge<T> edge = new Edge<T>(vertex1,vertex2,isDirected,weight);
            allEdges.add(edge);
            vertex1.addAdjacentVertex(edge, vertex2);
            if(!isDirected){
                vertex2.addAdjacentVertex(edge, vertex1);
            }

        }
        
        public List<Edge<T>> getAllEdges(){
            return allEdges;
        }
        
        public Collection<Vertex<T>> getAllVertex(){
            return allVertex.values();
        }
        @Override
        public String toString(){
            StringBuffer buffer = new StringBuffer();
            for(Edge<T> edge : getAllEdges()){
                buffer.append(edge.getVertex1() + " " + edge.getVertex2() + " " + edge.getWeight());
                buffer.append("\n");
            }
            return buffer.toString();
        }
    }
    
    private static class BinaryMinHeap<T> {

        private List<Node> allNodes = new ArrayList<>();
        private Map<T,Integer> nodePosition = new HashMap<>();
            
        public class Node {
            int weight;
            T key;
        }

        /**
         * Checks where the key exists in heap or not
         */
        public boolean containsKey(T key){
            return nodePosition.containsKey(key);
        }

        /**
         * Add key and its weight to they heap
         */
        public void add(int weight,T key) {
            Node node = new Node();
            node.weight = weight;
            node.key = key;
            allNodes.add(node);
            int size = allNodes.size();
            int current = size - 1;
            int parentIndex = (current - 1) / 2;
            nodePosition.put(node.key, current);

            while (parentIndex >= 0) {
                Node parentNode = allNodes.get(parentIndex);
                Node currentNode = allNodes.get(current);
                if (parentNode.weight > currentNode.weight) {
                    swap(parentNode,currentNode);
                    updatePositionMap(parentNode.key,currentNode.key,parentIndex,current);
                    current = parentIndex;
                    parentIndex = (parentIndex - 1) / 2;
                } else {
                    break;
                }
            }
        }

        /**
         * Checks with heap is empty or not
         */
        public boolean isEmpty(){
            return allNodes.size() == 0;
        }

        /**
         * Decreases the weight of given key to newWeight
         */
        public void decrease(T data, int newWeight){
            Integer position = nodePosition.get(data);
            allNodes.get(position).weight = newWeight;
            int parent = (position -1 )/2;
            while(parent >= 0){
                if(allNodes.get(parent).weight > allNodes.get(position).weight){
                    swap(allNodes.get(parent), allNodes.get(position));
                    updatePositionMap(allNodes.get(parent).key,allNodes.get(position).key,parent,position);
                    position = parent;
                    parent = (parent-1)/2;
                }else{
                    break;
                }
            }
        }

        /**
         * Get the weight of given key
         */
        public Integer getWeight(T key) {
            Integer position = nodePosition.get(key);
            if( position == null ) {
                return null;
            } else {
                return allNodes.get(position).weight;
            }
        }

        /**
         * Returns the min node of the heap
         */
        public Node extractMinNode() {
            int size = allNodes.size() -1;
            Node minNode = new Node();
            minNode.key = allNodes.get(0).key;
            minNode.weight = allNodes.get(0).weight;

            int lastNodeWeight = allNodes.get(size).weight;
            allNodes.get(0).weight = lastNodeWeight;
            allNodes.get(0).key = allNodes.get(size).key;
            nodePosition.remove(minNode.key);
            nodePosition.remove(allNodes.get(0));
            nodePosition.put(allNodes.get(0).key, 0);
            allNodes.remove(size);

            int currentIndex = 0;
            size--;
            while(true){
                int left = 2*currentIndex + 1;
                int right = 2*currentIndex + 2;
                if(left > size){
                    break;
                }
                if(right > size){
                    right = left;
                }
                int smallerIndex = allNodes.get(left).weight <= allNodes.get(right).weight ? left : right;
                if(allNodes.get(currentIndex).weight > allNodes.get(smallerIndex).weight){
                    swap(allNodes.get(currentIndex), allNodes.get(smallerIndex));
                    updatePositionMap(allNodes.get(currentIndex).key,allNodes.get(smallerIndex).key,currentIndex,smallerIndex);
                    currentIndex = smallerIndex;
                }else{
                    break;
                }
            }
            return minNode;
        }
        /**
         * Extract min value key from the heap
         */
        public T extractMin(){
            Node node = extractMinNode();
            return node.key;
        }

        private void swap(Node node1,Node node2){
            int weight = node1.weight;
            T data = node1.key;
            
            node1.key = node2.key;
            node1.weight = node2.weight;
            
            node2.key = data;
            node2.weight = weight;
        }

        private void updatePositionMap(T data1, T data2, int pos1, int pos2){
            nodePosition.remove(data1);
            nodePosition.remove(data2);
            nodePosition.put(data1, pos1);
            nodePosition.put(data2, pos2);
        }
        

    }
	

}





