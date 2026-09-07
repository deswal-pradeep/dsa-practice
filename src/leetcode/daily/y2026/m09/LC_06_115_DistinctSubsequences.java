package leetcode.daily.y2026.m09;

import java.util.Arrays;

public class LC_06_115_DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int[][] mem = new int[s.length()][t.length()];
        for(int i = 0; i < s.length(); i++){
            Arrays.fill(mem[i], -1);
        }
        return count(s, t, 0, 0, mem);
    }

    int count(String a, String b, int i, int j, int[][] mem){
        if(j == b.length())
            return 1;
        if(i == a.length())
            return 0;
        if(mem[i][j] != -1)
            return mem[i][j];
        int ans = 0;
        if(a.charAt(i) == b.charAt(j)){
            //matching
            //skip this match
            ans += count(a, b, i+1, j, mem);
            //considered this match
            ans += count(a, b, i+1, j+1, mem);
        } else {
            //not matching
            ans += count(a, b, i+1, j, mem);
        }
        mem[i][j] = ans;
        return ans;
    }

    static void main() {
        int ans = new LC_06_115_DistinctSubsequences().numDistinct("rabbbit", "rabbit");
        System.out.println(ans);
    }
}
