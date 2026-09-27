package leetcode.daily.y2026.m09;

import java.util.Stack;

//https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/description/?envType=daily-question&envId=2026-09-27
public class LC_27_1190_ReverseSubstringsBetweenEachPairOfParentheses {
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        char[] arr = s.toCharArray();
        int n = arr.length;
        for(int i = 0; i < n; i++){
            char c = arr[i];
            if(c == ')'){
                StringBuilder builder = new StringBuilder();
                while(stack.peek() != '('){
                    builder.append(stack.pop());
                }
                stack.pop();
                //push again
                String str = builder.toString();
                for(int j = 0; j < str.length(); j++){
                    stack.push(str.charAt(j));
                }
                continue;
            }
            stack.push(c);
        }
        StringBuilder builder = new StringBuilder();
        while(!stack.isEmpty()){
            builder.append(stack.pop());
        }
        return builder.reverse().toString();
    }
    public String reverseParentheses_tunnelWay(String s) {
        int n = s.length();
        Stack<Integer> openParenthesisIndices = new Stack<>();
        int[] tunnel = new int[n];
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(c == '('){
                openParenthesisIndices.push(i);
            } else if (c == ')'){
                int j = openParenthesisIndices.pop();
                tunnel[i] = j;
                tunnel[j] = i;
            }
        }
        StringBuilder result = new StringBuilder();
        for(int curr = 0, direction = 1; curr < n; curr += direction){
            char c = s.charAt(curr);
            if(c == '(' || c == ')'){
                curr = tunnel[curr];
                direction = -direction;
                continue;
            }
            result.append(c);
        }
        return result.toString();
    }

    static void main() {
        String s = new LC_27_1190_ReverseSubstringsBetweenEachPairOfParentheses()
                .reverseParentheses("(ed(et(oc))el)");
        System.out.println(s);
    }
}
