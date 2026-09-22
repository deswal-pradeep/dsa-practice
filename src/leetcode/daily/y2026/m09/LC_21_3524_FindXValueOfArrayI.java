package leetcode.daily.y2026.m09;

import java.util.Arrays;

public class LC_21_3524_FindXValueOfArrayI {
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n+1][k];

        for(int i = 1; i <= n; i++){
            dp[i][nums[i-1]%k] = 1;
            for(int j = 0; j < k; j++){
                int cnt = dp[i-1][j];
                int newR = (int)((1l * nums[i-1] * j)%k);
                dp[i][newR] += cnt;
            }
        }
        long[] ans = new long[k];
        for(int i = 0; i <= n; i++){
            for(int j = 0; j < k; j++){
                ans[j] += dp[i][j];
            }
        }
        return ans;
    }

    static void main() {
        long[] longs = new LC_21_3524_FindXValueOfArrayI()
                .resultArray(new int[]{1, 2, 3, 4, 5}, 3);
        System.out.println(Arrays.toString(longs));
    }
}
