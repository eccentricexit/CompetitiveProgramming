/*******************************************************************************
 * Author: Matheus Faria de Alencar
 *******************************************************************************/
package hackerRank;

import java.util.Scanner;
public class FindTheRunningMedian {
    
    public static void main(String[] args) {        
               
        Scanner in = new Scanner(System.in);        
        int n = in.nextInt();
        
        Median h = new Median(n);        
        for(int i=1;i<=n;i++){
            int value = in.nextInt();
            h.insert(value);
            System.out.println(h.getMedian());
        }
        in.close();
    }
    
    private static class Median{ 
        MaxHeap left;
        MinHeap right;        
        boolean alternate = false;
        int size = 0;
        
        public Median(int capacity) {
             left = new MaxHeap(capacity);
             right = new MinHeap(capacity);
        }

        private float getMedian() {
            if(size<2){
                if(left.size()>0){
                    return left.peek();
                }else{
                    return right.peek();
                }                
            }
            
            if(size%2!=0){                
                if(left.size()>right.size()){
                    return left.peek();
                }else{
                    return right.peek();
                }   
            }
            
            float a = left.peek();
            float b = right.peek();
            
            float median = (a+b)/2f;
            return median;
        }
        
        public void insert(int i) {            
            if(alternate){
                right.insert(i);
            }else{
                left.insert(i);
            }
            
            alternate = !alternate;
            if(left.size()>0 && right.size()>0 && left.peek()>right.peek()){
                swapRoots();
            }
            size++;
        }
        
        private void swapRoots() {
            int temp = left.peek();
            left.swapRootWith(right.arr[0]);
            right.swapRootWith(temp);            
        }        
        
        private static abstract class Heap{
            int[] arr;
            int size;
            
            public Heap(int capacity){
                arr = new int[capacity];
                size = 0;
            }
            
            public void insert(int i){
                if(size==0){
                    arr[0] = i;
                    size++;
                    return;
                }
                
                arr[size] = i;
                heapifyUp(size);
                size++;
            }
            
            private void heapifyUp(int current) {
                int p = getParent(current);
                while(p>=0 && compare(p,current)){
                    swap(current,p);
                    current = p;
                    p = getParent(current);
                }
            }
            
            public int peek(){
                return arr[0];
            }        
            
            public int size(){
                return size;
            }
            
            public void swapRootWith(int i){
                arr[0] = i;
                heapifyDown(0);
            }
            
            void heapifyDown(int i){
                int current = i;
                int l = getLeft(i);
                int r = getRight(i);
                
                if(l<size && compare(current,l))
                    current = l;
                
                if(r<size && compare(current,r))
                    current = r;
                
                if(current!=i){
                    swap(i,current);
                    heapifyDown(current);
                }
            }           
            
            abstract boolean compare(int parent, int child);
            
            int getLeft(int i){
            	int left = (i*2)+1;
                return left;
            }
            
            private int getRight(int i){
            	int right = (i*2)+2;
                return right;
            }
            
            private int getParent(int i){
                int parent = (i-1)/2;
                return parent;
            }        

            protected void swap(int i, int p) {
                int temp = arr[i];
                arr[i] = arr[p];
                arr[p] = temp;
            } 
        }
        
        private static class MaxHeap extends Heap{
            public MaxHeap(int capacity) {
                super(capacity);
            }

            @Override
            boolean compare(int parent, int child) {
                return arr[parent]<arr[child];
            }        
        }
        
        private static class MinHeap extends Heap{
            public MinHeap(int n) {
                super(n);
            }

            @Override
            boolean compare(int parent, int child) {
                return arr[parent]>arr[child];
            }       
        }
        
    }
    

        
}
