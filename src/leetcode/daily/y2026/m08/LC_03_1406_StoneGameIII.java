package leetcode.daily.y2026.m08;

import java.util.Arrays;

public class LC_03_1406_StoneGameIII {
    int n;

    public String stoneGameIII(int[] stoneValue) {
        int[] arr = stoneValue;
        n = stoneValue.length;
        int[][] dp = new int[n+3][2];
        for(int i = n-1; i >= 0; i--){
            for(int turn = 0; turn < 2; turn++){
                int val1 = arr[i];
                int val2 = i+1 < n ? arr[i+1] : 0;
                int val3 = i+2 < n ? arr[i+2] : 0;
                int ans = 0;
                if(turn == 0){
                    ans = max(val1 + dp[i+1][1],
                            val1 + val2 + dp[i+2][1],
                            val1 + val2 + val3 + dp[i+3][1]);
                } else {
                    ans = min(-val1 + dp[i+1][0],
                            -val1-val2 + dp[i+2][0],
                            -val1-val2-val3 + dp[i+3][0]);
                }
                dp[i][turn] = ans;
            }
        }
        int score = dp[0][0];
        String ans = "Tie";
        ans = score > 0 ? "Alice" : ans;
        ans = score < 0 ? "Bob" : ans;
        return ans;

    }

    public String stoneGameIII_mem(int[] stoneValue) {
        n = stoneValue.length;
        int[][] mem = new int[n][2];
        for(int i = 0; i < n; i++){
            Arrays.fill(mem[i], -1);
        }
        int score = play(stoneValue, 0, 0, mem);
        String ans = "Tie";
        ans = score > 0 ? "Alice" : ans;
        ans = score < 0 ? "Bob" : ans;
        return ans;
    }

    int play(int[] arr, int i, int turn, int[][] mem){
        if(i >= n){
            return 0;
        }
        if(mem[i][turn] != -1)
            return mem[i][turn];
        int val1 = arr[i];
        int val2 = i+1 < n ? arr[i+1] : 0;
        int val3 = i+2 < n ? arr[i+2] : 0;
        int ans = 0;
        if(turn == 0){
            ans = max(val1 + play(arr, i+1, 1, mem),
                    val1 + val2 + play(arr, i+2, 1, mem),
                    val1 + val2 + val3 + play(arr, i+3, 1, mem)
            );
        } else {
            ans = min(-val1 + play(arr, i+1, 0, mem),
                    -val1-val2 + play(arr, i+2, 0, mem),
                    -val1-val2-val3 + play(arr, i+3, 0, mem)
            );
        }
        mem[i][turn] = ans;
        return ans;
    }

    int max(int val1, int val2, int val3){
        return Math.max(Math.max(val1, val2), val3);
    }

    int min(int val1, int val2, int val3){
        return Math.min(Math.min(val1, val2), val3);
    }

    static void main() {
        String s = new LC_03_1406_StoneGameIII().stoneGameIII(new int[]{1,1,1,0,1,1,1,1,1,1});
        System.out.println(s);
    }
}
