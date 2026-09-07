package leetcode.daily.y2026.m09;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC_07_940_DistinctSubsequencesII {
    int n;
    int MOD = 1_000_000_007;
    public int distinctSubseqII_self(String s) {
        int n = s.length();
        int[] prev = new int[n];
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            prev[i] = map.getOrDefault(s.charAt(i), -1);
            map.put(s.charAt(i), i);
        }
        int[][] dp = new int[n+1][n+1];
        int[][] prefix = new int[n+1][n+1];
        for(int len = 1; len <= n; len++){
            int prefixSum = 0;
            for(int i = len; i <= n; i++){
                int prevIndex = prev[i-1];
                if(len == 1){
                    dp[len][i] = prevIndex == -1 ? 1 : 0;

                    prefixSum += dp[len][i];
                    prefixSum %= MOD;
                    prefix[len][i] = prefixSum;
                    continue;
                }
                int sum = 0;
                sum += prefix[len-1][i-1] - prefix[len-1][Math.max(prevIndex, 0)];
                sum %= MOD;
                dp[len][i] = sum;

                prefixSum += dp[len][i];
                prefixSum %= MOD;
                prefix[len][i] = prefixSum;
            }
        }
        int ans = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= n; j++){
                ans += dp[i][j];
                ans %= MOD;
            }
        }
        return ans;
    }

    public int distinctSubseqII(String S) {
        int MOD = 1_000_000_007;
        int N = S.length();
        int[] dp = new int[N+1];
        dp[0] = 1;

        int[] last = new int[26];
        Arrays.fill(last, -1);

        for (int i = 0; i < N; ++i) {
            int x = S.charAt(i) - 'a';
            dp[i+1] = dp[i] * 2 % MOD;
            if (last[x] >= 0)
                dp[i+1] -= dp[last[x]];
            dp[i+1] %= MOD;
            last[x] = i;
        }

        dp[N]--;
        if (dp[N] < 0) dp[N] += MOD;
        return dp[N];
    }

    static void main() {
        int ans = new LC_07_940_DistinctSubsequencesII().distinctSubseqII("abcdc");
        System.out.println(ans);
    }
}
