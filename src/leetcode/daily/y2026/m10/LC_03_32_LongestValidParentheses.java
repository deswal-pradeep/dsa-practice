package leetcode.daily.y2026.m10;

import java.util.Arrays;
import java.util.Stack;

public class LC_03_32_LongestValidParentheses {
    public int longestValidParentheses(String s) {
        char[] arr = s.toCharArray();
        int n = s.length();
        int l = 0;
        int r = 0;
        int[] tunnels = new int[n];
        Arrays.fill(tunnels, -1);
        Stack<Integer> stack = new Stack<>();
        int maxLength = 0;
        while(r < n){
            char c = arr[r];
            if(c == '('){
                stack.push(r);
            } else {
                if(!stack.isEmpty()){
                    int index = stack.pop();
                    tunnels[index] = r;
                    tunnels[r] = index;
                }
            }
            r++;
        }
        int lastLength = 0;
        for(int i = 0; i < n; i++){
            if(tunnels[i] != -1){

                //tunnel is present, it's a pair
                int length = tunnels[i] - i + 1;
                //continous tunnel
                if(i > 0 && tunnels[i-1] != -1){
                    length = length + lastLength;
                }
                maxLength  = Math.max(maxLength, length);
                lastLength = length;
                i = tunnels[i];
            }
        }
        return maxLength;
    }

    static void main() {
        int ans = new LC_03_32_LongestValidParentheses()
                .longestValidParentheses("(()(((()");
        System.out.println(ans);
    }
}
