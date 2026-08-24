package leetcode.daily.y2026.m08;

public class LC_24_1872_StoneGameVIII {
    int n;
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        // Convert stones into prefix sums.
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }

        // Base case:
        // Only one possible move remains: take all stones.
        int dp = stones[n - 1];

        // Compute dp backwards.
        for (int i = n - 2; i > 0; i--) {
            dp = Math.max(dp, stones[i] - dp);
        }

        return dp;
    }

    public int stoneGameVIII_wrong(int[] stones) {
        n = stones.length;
        int[] prefix = new int[n+1];
        for(int i = 1; i < prefix.length; i++){
            prefix[i] = prefix[i-1] + stones[i-1];
        }
        return dp(stones, 0, 0, prefix);
    }

    int dp(int[] arr, int ind, int turn, int[] prefix){
        if(ind >= n-1){
            return 0;
        }

        if(turn == 0){

            int diff = 0;
            int maxDiff = (int)-1e9;
            for(int i = ind+1; i < n; i++){
                if(i == n-1)
                    diff = prefix[i+1];
                else
                    diff = prefix[i+1] - dp(arr, i, 1, prefix);
                maxDiff = Math.max(maxDiff, diff);
            }
            return maxDiff;
        } else {
            int diff = 0;
            int minDiff = (int)1e9;
            for(int i = ind+1; i < n; i++){
                if(i == n-1)
                    diff = prefix[i+1];
                else
                    diff = prefix[i+1]-dp(arr, i, 0, prefix);
                minDiff = Math.min(minDiff, diff);
            }
            return minDiff;
        }
    }

    static void main() {
        int ans = new LC_24_1872_StoneGameVIII().stoneGameVIII(new int[]{1,-5,-3,2,-5});
        System.out.println(ans);
    }
}
