/*******************************************************************************
 * Author: Matheus Faria de Alencar
 *******************************************************************************/
package hackerRank;

import java.util.Scanner;
import java.util.Stack;

public class BalancedBrackets {

	public static boolean isBalanced(String expression) {
        Stack<Character> s = new Stack<>();
        for(Character c:expression.toCharArray()){
        	if(isClosingBracket(c)){
        		if(s.isEmpty())
        			return false;
        		
                Character previous = s.pop();
                if(!matches(previous,c))
                	return false;
            }else{
                s.add(c);
            }
        }
        
        if(s.isEmpty())
        	return true;
        else
        	return false;
     }
  
    private static boolean matches(Character previous, Character c) {
		if(previous=='[' && c==']')
			return true;
		if(previous=='(' && c==')')
			return true;
		if(previous=='{' && c=='}')
			return true;
		
    	
		return false;
	}

	private static boolean isClosingBracket(Character c) {
		if(c.equals(')') || c.equals(']') || c.equals('}'))
			return true;
		
		return false;
	}

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++) {
            String expression = in.next();
            boolean answer = isBalanced(expression);
            if(answer)
                System.out.println("YES");
            else System.out.println("NO");
        }
        
        in.close();
    }
    
    

}
