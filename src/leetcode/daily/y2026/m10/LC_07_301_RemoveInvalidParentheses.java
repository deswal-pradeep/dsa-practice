package leetcode.daily.y2026.m10;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class LC_07_301_RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        int countInvalid = 0;
        int depth = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '('){
                depth++;
            } else if(c == ')') {
                if(depth == 0) countInvalid++;
                else depth--;
            }
        }
        countInvalid += depth;
        Set<String> set = new LinkedHashSet<>();
        permute(s.toCharArray(), 0, countInvalid, 0, new char[s.length()-countInvalid], 0, set);
        if(set.isEmpty())
            set.add("");
        return new ArrayList<>(set);
    }

    void permute(char[] arr, int ind, int skipsAllowed, int depth, char[] ans, int ansInd, Set<String> set){
        if(skipsAllowed < 0 || depth < 0)
            return;
        if(ind == arr.length || ansInd == ans.length){
            if(depth == 0)
                set.add(new String(ans));
            return;
        }
        //skip is done when allowed and skip is done only when
        if(skipsAllowed > 0 &&
                (arr[ind] == '(' || arr[ind] == ')')){
            //skip allowed
            permute(arr, ind+1, skipsAllowed-1, depth, ans, ansInd, set);
        }
        if(arr[ind] == '(')
            depth += 1;
        else if (arr[ind] == ')')
            depth -= 1;
        ans[ansInd] = arr[ind];
        permute(arr, ind+1, skipsAllowed, depth, ans, ansInd+1, set);
    }

    static void main() {
        List<String> strings = new LC_07_301_RemoveInvalidParentheses().removeInvalidParentheses("x(");
        System.out.println(strings);
    }
}
