package amazon;

import java.util.Stack;

public class LC_402_RemoveKDigits {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < num.length(); i++){
            char curr = num.charAt(i);
            if(stack.isEmpty() || stack.peek() <= curr){
                stack.push(curr);
                continue;
            }
            while(k > 0 && !stack.isEmpty() && stack.peek() > curr){
                stack.pop();
                k--;
            }
            stack.push(curr);
        }
        while(k > 0){
            stack.pop();
            k--;
        }
        StringBuilder builder = new StringBuilder();
        while(!stack.isEmpty()){
            builder.append(stack.pop());
        }
        String newNum = builder.reverse().toString();
        //find prefix zeros
        int i = 0;
        while(i < newNum.length() && newNum.charAt(i) == '0'){
            i++;
        }
        newNum = newNum.substring(i, newNum.length());
        return newNum.isEmpty() ? "0" : newNum;
    }
}
