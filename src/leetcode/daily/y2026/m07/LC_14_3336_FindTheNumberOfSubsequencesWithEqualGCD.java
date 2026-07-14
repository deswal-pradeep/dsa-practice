package leetcode.daily.y2026.m07;

import java.util.Arrays;
import java.util.OptionalInt;

//https://leetcode.com/problems/find-the-number-of-subsequences-with-equal-gcd/description/?envType=daily-question&envId=2026-07-14
public class LC_14_3336_FindTheNumberOfSubsequencesWithEqualGCD {
    static int MOD = 1_000_000_007;
    int n;

    public int subsequencePairCount(int[] nums) {
        n = nums.length;
        int max = Arrays.stream(nums).max().getAsInt();
        int[][] prev = new int[max+1][max+1];

        prev[0][0] = 1;
        for(int ind = 0; ind < n; ind++){
            int[][] curr = new int[max+1][max+1];
            int x = nums[ind];
            for(int gcd1 = 0; gcd1 <= max; gcd1++){
                for(int gcd2 = 0; gcd2 <= max; gcd2++){
                    int val = prev[gcd1][gcd2];
                    if(val == 0)
                        continue;
                    curr[gcd1][gcd2] = (curr[gcd1][gcd2] + val)%MOD;
                    curr[gcd(gcd1,x)][gcd2] = (curr[gcd(gcd1,x)][gcd2] + val)%MOD;
                    curr[gcd1][gcd(gcd2,x)] = (curr[gcd1][gcd(gcd2,x)] + val)%MOD;
                }
            }
            prev = curr;
        }
        int ans = 0;
        for (int g = 1; g <= max; g++) {
            ans = (ans + prev[g][g]) % MOD;
        }
        return ans;
    }

    public int subsequencePairCount_downward(int[] nums) {
        n = nums.length;
        int max = Arrays.stream(nums).max().getAsInt();
        int[][] prev = new int[max+1][max+1];
        int[][] curr = new int[max+1][max+1];
        for(int g1 = 1; g1 <= max; g1++){
            prev[g1][g1] = 1;
        }
        for(int ind = n-1; ind >=0; ind--){
            for(int gcd1 = 0; gcd1 <= max; gcd1++){
                for(int gcd2 = 0; gcd2 <= max; gcd2++){
                    int count = 0;
                    count = count + prev[gcd1][gcd2];
                    count %= MOD;
                    count = count + prev[gcd(gcd1, nums[ind])][gcd2];
                    count %= MOD;
                    count = count + prev[gcd1][gcd(gcd2, nums[ind])];
                    count %= MOD;
                    curr[gcd1][gcd2] = count;
                }
            }
            int[][] temp = curr;
            curr = prev;
            prev = temp;
        }
        return prev[0][0];
    }

    public int subsequencePairCount_dp(int[] nums) {
        n = nums.length;
        int max = Arrays.stream(nums).max().getAsInt();
        int[][][] dp = new int[n+1][max+1][max+1];
        for(int g1 = 1; g1 <= max; g1++){
            dp[n][g1][g1] = 1;
        }
        for(int ind = n-1; ind >=0; ind--){
            for(int gcd1 = 0; gcd1 <= max; gcd1++){
                for(int gcd2 = 0; gcd2 <= max; gcd2++){
                    int count = 0;
                    count = count + dp[ind+1][gcd1][gcd2];
                    count %= MOD;
                    count = count + dp[ind+1][gcd(gcd1, nums[ind])][gcd2];
                    count %= MOD;
                    count = count + dp[ind+1][gcd1][gcd(gcd2, nums[ind])];
                    count %= MOD;
                    dp[ind][gcd1][gcd2] = count;
                }
            }
        }
        return dp[0][0][0];
    }

    public int subsequencePairCount_mem(int[] nums) {
        n = nums.length;
        int max = Arrays.stream(nums).max().getAsInt();
        int[][][] mem = new int[n][max+1][max+1];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < max+1; j++){
                Arrays.fill(mem[i][j], -1);
            }
        }

        int ans = countGcdPairs(nums, 0, 0, 0, mem);

        return ans;
    }

    int countGcdPairs(int[] nums, int ind, int gcd1, int gcd2, int[][][] mem){
        if(ind == n){
            return gcd1 == gcd2 && gcd1 > 0 ? 1 : 0;
        }
        if(mem[ind][gcd1][gcd2] != -1){
            return mem[ind][gcd1][gcd2];
        }
        //every index has 3 choices
        int count = 0;
        count = count + countGcdPairs(nums, ind+1, gcd1, gcd2, mem);
        count %= MOD;
        count = count + countGcdPairs(nums, ind+1, gcd(gcd1, nums[ind]), gcd2, mem);
        count %= MOD;
        count = count + countGcdPairs(nums, ind+1, gcd1, gcd(gcd2, nums[ind]), mem);
        count %= MOD;
        mem[ind][gcd1][gcd2] = count;
        return count;
    }

    int gcd(int a, int b){
        if(a == 0)
            return b;
        if(b == 0)
            return a;

        while(a != b){
            if(a > b)
                a = a - b;
            else
                b = b - a;
        }
        return a;
    }

    static void main() {
        int ans = new LC_14_3336_FindTheNumberOfSubsequencesWithEqualGCD().subsequencePairCount(new int[]{1,1,1,1});
        System.out.println(ans);
    }
}
