package leetcode.daily.y2026.m09;

import java.util.Arrays;

//https://leetcode.com/problems/number-of-sets-of-k-non-overlapping-line-segments/?envType=daily-question&envId=2026-09-16
public class LC_16_1621_NumberOfSetsOfKNonOverlappingLine {
    static int MOD = 1_000_000_007;
    public int numberOfSets(int n, int k) {
        int[][][] mem = new int[n][2][k+1];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 2; j++)
                Arrays.fill(mem[i][j], -1);
        }
        return count(0, 0, n, k, mem);
    }

    int count(int ind, int seg, int n, int k, int[][][] mem){
        if(k == 0)
            return seg == 0 ? 1 : 0;
        if(ind == n)
            return 0;
        if(mem[ind][seg][k] != -1)
            return mem[ind][seg][k];
        int ways = 0;
        if(seg == 0){
            ways += count(ind+1, 0, n, k, mem);
            ways%= MOD;
            ways += count(ind+1, 1, n, k, mem);
            ways %= MOD;
        }
        if(seg == 1){
            ways += count(ind+1, 1, n, k, mem);
            ways%= MOD;
            ways += count(ind+1, 1, n, k-1, mem);
            ways%= MOD;
            ways += count(ind+1, 0, n, k-1, mem);
            ways%= MOD;
        }
        mem[ind][seg][k] = ways;
        return ways;
    }

    static void main() {
        int ans = new LC_16_1621_NumberOfSetsOfKNonOverlappingLine().numberOfSets(5, 2);
        System.out.println(ans);
    }
}
