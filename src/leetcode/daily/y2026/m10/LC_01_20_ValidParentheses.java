package leetcode.daily.y2026.m10;

import java.util.Stack;

public class LC_01_20_ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '(' || c == '{' || c == '['){
                stack.push(c);
            } else {
                if(stack.isEmpty())
                    return false;
                char peek = stack.peek();
                if((peek == '(' && c != ')')
                        || (peek == '{' && c != '}')
                        || (peek == '[' && c != ']'))
                    return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    static void main() {
        boolean valid = new LC_01_20_ValidParentheses().isValid("()");
        System.out.println(valid);
    }
}
