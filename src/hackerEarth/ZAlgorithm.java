//Special thanks to Tushar Roy @ https://www.youtube.com/channel/UCZLJf_R2sWyUtXSKiKlyvAw
//For the excelent tutorials.

package hackerEarth;

import java.util.HashSet;
import java.util.Set;
import lib.Tools;

public class ZAlgorithm {

	public static void main(String[] args) {
		String text="abbabababba";
		String pattern="baba";
		Tools.StopWatch sw = new Tools.StopWatch();
		
		sw.start();
		Set<Integer> patternIndexes = getIndexesZQuadratic(text,pattern);		
		printSet(patternIndexes);
		sw.stopPrint();
		
		sw.start();
		patternIndexes = getIndexesZLinear(text,pattern);		
		printSet(patternIndexes);
		sw.stopPrint();		
	}

	private static Set<Integer> getIndexesZQuadratic(String text, String pattern) {
		Set<Integer> set = new HashSet<>();
		String comb = pattern+"$"+text;
		char[] arr = comb.toCharArray();
		int[] sizes = new int[arr.length];
						
		for(int k=1;k<arr.length;k++){
			int i=k;
			int j=0;
			while(true){
				if(i<arr.length && arr[j]==arr[i]){
					i++;
					j++;
				}else{
					if(j==pattern.length())
						set.add(k);
					
					sizes[k]=j;
					j=0;
					break;
				}
			}
		}	
		
		return set;
	}
	
	private static Set<Integer> getIndexesZLinear(String text, String pattern) {
		Set<Integer> set = new HashSet<>();
		String comb = pattern+"$"+text;
		char[] arr = comb.toCharArray();
		
		int[] z = new int[arr.length];
		int left=0,right=0;
		
		
		for(int k=1;k<arr.length;k++){
			if(k>right){ //are we outside the Z box?
				left=right=k;
				while(right<arr.length && arr[right]==arr[right-k]){
					right++;
				}
				z[k] = right-k;
				right--;
				if(z[k]==pattern.length())
					set.add(k);
			}else{
				int k1 = k-left;
				if(z[k1] < right - k + 1){	//if the value does not touch the right bound,
					z[k] = z[k1]; 			//simply copy it.
					if(z[k]==pattern.length())
						set.add(k);
				}else{
					left = k;
					while(right < arr.length && arr[right] == arr[right - k]){
						right++;
					}
					z[k] = right-k;
					right--;
					if(z[k]==pattern.length())
						set.add(k);
				}
				
			}
		}
		
		
		return set;
	}
	
	private static void printSet(Set<Integer> patternIndexes) {
		for(Integer i:patternIndexes)
			System.out.print(i+" ");
		System.out.println();
	}

}
