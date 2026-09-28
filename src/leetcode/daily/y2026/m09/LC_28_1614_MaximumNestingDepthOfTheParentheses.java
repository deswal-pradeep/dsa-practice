package leetcode.daily.y2026.m09;

//https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/description/?envType=daily-question&envId=2026-09-28
public class LC_28_1614_MaximumNestingDepthOfTheParentheses {
    public int maxDepth(String s) {
        int depth = 0;
        int maxDepth = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '('){
                depth++;
                maxDepth = Math.max(maxDepth, depth);
            } else if (c == ')'){
                depth--;
            }
        }
        return maxDepth;
    }

    static void main() {
        int ans = new LC_28_1614_MaximumNestingDepthOfTheParentheses().maxDepth("(1+(2*3)+((8)/4))+1");
        System.out.println(ans);
    }
}
