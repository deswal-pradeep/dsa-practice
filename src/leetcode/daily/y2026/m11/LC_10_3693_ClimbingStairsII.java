package leetcode.daily.y2026.m11;

import java.util.Arrays;

public class LC_10_3693_ClimbingStairsII {
    int n;
    public int climbStairs(int n, int[] costs) {
        this.n = n;
        int[] mem = new int[n];
        Arrays.fill(mem, -1);
        return dp(0, costs, mem);
    }

    int dp(int idx, int[] costs, int[] mem){
        if(idx >= n)
            return 0;
        if(mem[idx] != -1){
            return mem[idx];
        }
        int cost = costs[idx] + 1 + dp(idx+1, costs, mem);
        if(idx+1 < n)
            cost = Math.min(cost, costs[idx+1] + 4 + dp(idx+2, costs, mem));
        if(idx + 2 < n)
            cost = Math.min(cost, costs[idx+2] + 9 + dp(idx+3, costs, mem));
        mem[idx] = cost;
        return cost;
    }

    static void main() {
        int ans = new LC_10_3693_ClimbingStairsII().climbStairs(4, new int[]{5, 1, 6, 2});
        System.out.println(ans);
    }
}
