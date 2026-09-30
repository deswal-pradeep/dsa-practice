package leetcode.daily.y2026.m09;

import java.util.Arrays;

//https://leetcode.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/description/?envType=daily-question&envId=2026-09-30
public class LC_30_1111_MaximumNestingDepthOfTwoValidParenthesesStrings {
    public int[] maxDepthAfterSplit(String seq) {
        int max = 0;
        int depth = 0;
        for(int i = 0; i < seq.length(); i++){
            char c = seq.charAt(i);
            if(c == '('){
                depth++;
                max = Math.max(max, depth);
            } else {
                depth--;
            }
        }
        int aDepth = max / 2;
        int[] ans = new int[seq.length()];
        depth = 0;
        for(int i = 0; i < ans.length; i++){
            char c = seq.charAt(i);
            if(c == '('){
                depth++;
                ans[i] = depth <= aDepth ? 0 : 1;
            } else {
                ans[i] = depth <= aDepth ? 0 : 1;
                depth--;
            }
        }
        return ans;
    }

    static void main() {
        int[] ints = new LC_30_1111_MaximumNestingDepthOfTwoValidParenthesesStrings()
                .maxDepthAfterSplit("()(())()");
        System.out.println(Arrays.toString(ints));
    }
}
