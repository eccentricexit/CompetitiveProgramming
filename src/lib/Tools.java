/*******************************************************************************
 * Author: Matheus Faria de Alencar
 *******************************************************************************/
package lib;

import java.util.Set;

public class Tools {
	
	public static class StopWatch {
		long start;
		long stop;
		public void start(){
			start = System.nanoTime();	
		}
		public void stopPrint(){
			stop = System.nanoTime();
			print();		
		}

		private void print() {		
			System.out.print("StopWatch: "+(stop-start));
			System.out.println();
		}	
	}
	
	public static <T> void printArray(T[] collection){
		for(T element:collection)
			System.out.print(element+" ");
	}

	public static void printCollection(Set<Integer> patternIndexes) {
		printArray(patternIndexes.toArray());		
	}

	public static void printArray(int[] arr) {
		for(Integer element:arr)
			System.out.print(element+" ");		
	}

}
