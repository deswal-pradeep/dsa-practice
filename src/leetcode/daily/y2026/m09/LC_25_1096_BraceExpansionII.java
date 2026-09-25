package leetcode.daily.y2026.m09;

import com.sun.source.tree.Tree;

import java.util.*;

public class LC_25_1096_BraceExpansionII {
    public List<String> braceExpansionII(String expression) {
        Deque<Character> ops = new ArrayDeque<>();
        List<Set<String>> stack = new ArrayList<>();
        int n = expression.length();
        for(int i = 0; i < n; i++){
            char c = expression.charAt(i);
            if(c == ','){
                while(!ops.isEmpty() && ops.peek()=='*'){
                    perform(ops, stack);
                }
                ops.push('+');
            } else if (expression.charAt(i) == '{'){
                if(isLastCharALetterOrClosingBraces(expression, i)){
                    ops.push('*');
                }
                ops.push('{');

            } else if (expression.charAt(i) == '}'){
                //time to pop until you find a
                while (!ops.isEmpty() && ops.peek() != '{') {
                    perform(ops, stack);
                }
                ops.pop();
            } else {
                if(isLastCharALetterOrClosingBraces(expression, i)){
                    ops.push('*');
                }
                StringBuilder sb = new StringBuilder();
                sb.append(expression.charAt(i));
                Set<String> set = new TreeSet<>();
                set.add(sb.toString());
                stack.add(set);
            }
        }
        while (!ops.isEmpty()){
            perform(ops, stack);
        }
        return new ArrayList<>(stack.getLast());
    }

    private static boolean isLastCharALetterOrClosingBraces(String expression, int i) {
        return i > 0 && (expression.charAt(i - 1) == '}' || Character.isLetter(expression.charAt(i - 1)));
    }

    void perform(Deque<Character> ops, List<Set<String>> stack){
        int l = stack.size()-2;
        int r = stack.size() - 1;
        if(ops.peek()=='+'){
            stack.get(l).addAll(stack.get(r));
        } else {
            Set<String> temp = new TreeSet<>();
            for(String left : stack.get(l)){
                for(String right : stack.get(r)){
                    temp.add(left + right);
                }
            }
            stack.set(l, temp);
        }
        ops.pop();
        stack.remove(r);
    }

    static void main() {
        List<String> strings = new LC_25_1096_BraceExpansionII().braceExpansionII("{a,b}{c,{d,e}}");
        System.out.println(strings);
    }
}
