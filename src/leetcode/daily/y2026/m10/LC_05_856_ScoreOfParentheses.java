package leetcode.daily.y2026.m10;

import java.util.Stack;

public class LC_05_856_ScoreOfParentheses {
    public int scoreOfParentheses(String s) {
        Stack<Character> ops = new Stack<>();
        Stack<Long> vars = new Stack<>();
        ops.push(s.charAt(0));
        for(int i = 1; i < s.length(); i++){
            char p = s.charAt(i-1);
            char c = s.charAt(i);
            if(p == '(' && c == ')'){
                ops.pop();
                vars.push(1L);
            } else if (p == '(' && c == '('){
                vars.push(2L);
                ops.push('*');
                ops.push('(');
            } else if (p == ')' && c == '('){
                ops.push('+');
                ops.push('(');
            } else if (p == ')' && c == ')'){
                while(ops.peek() != '('){
                    char op = ops.pop();
                    Long op1 = vars.pop();
                    Long op2 = vars.pop();
                    Long result = op == '*' ? op1 * op2 : op1 + op2;
                    vars.push(result);
                }
                ops.pop();
            }
        }
        while(!ops.isEmpty()){
            char op = ops.pop();
            Long op1 = vars.pop();
            Long op2 = vars.pop();
            Long result = op == '*' ? op1 * op2 : op1 + op2;
            vars.push(result);
        }
        return vars.pop().intValue();
    }

    static void main() {
        int ans = new LC_05_856_ScoreOfParentheses().scoreOfParentheses("()()");
        System.out.println(ans);
    }
}
