package leetcode.daily.y2026.m06;

public class LC_20260623_3699_NumberOfZigZagArraysI {
    int n, l , r;
    int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        this.n = n;
        this.l = l;
        this.r = r;

        long[][] dp = new long[r+1][n+1];
        int z = 0;
        //for n = 2 set all values
        for(int i = l; i <= r; i++) {
            dp[i][2] = z;
            z++;
        }

        for(int k = 3; k <= n; k++){
            long count = 0;
            for(int i = l; i <= r; i++) {
                dp[i][k] = count;
                count = count + dp[r-i+l][k-1];
                count %= MOD;
            }
        }

        long ans = 0;
        for(int i = l; i <= r ; i++){
            ans = ans + dp[i][n];
            ans %= MOD;
        }
        ans = (ans * 2) % MOD;
        return (int) ans;
    }

    public int zigZagArrays_mem(int n, int l, int r) {
        this.n = n;
        this.l = l;
        this.r = r;
        long ans = count(0, 0, 0);
        return (int) ans;
    }

    long count(int idx, int prev, int pprev){
        if(idx == n){
            return 1;
        }
        long ans = 0;
        if(pprev < prev || prev == 0 || pprev == 0){
            for(int i = l; i < prev; i++){
                ans += count(idx+1, i, prev);
                ans %= MOD;
            }
        }
        if (pprev > prev || prev == 0 || pprev == 0) {
            for(int i = Math.max(prev+1, l); i <= r; i++){
                ans += count(idx+1, i, prev);
                ans %= MOD;
            }
        }
        return ans;
    }

    static void main() {
        int ans = new LC_20260623_3699_NumberOfZigZagArraysI()
                .zigZagArrays(4, 1, 3);
        System.out.println(ans);
    }
}
