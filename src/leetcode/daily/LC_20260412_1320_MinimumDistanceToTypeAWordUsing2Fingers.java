package leetcode.daily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC_20260412_1320_MinimumDistanceToTypeAWordUsing2Fingers {
    static void main() {
        System.out.println(new LC_20260412_1320_MinimumDistanceToTypeAWordUsing2Fingers().minimumDistance("CAKE"));
    }
    int n;
    public int minimumDistance(String word) {
        int n = word.length();
        int INF = (int)1e9;

        int[][][] dp = new int[n][27][27];

        // initialize
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 27; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        int first = word.charAt(0) - 'A';
        dp[0][first][26] = 0;
        dp[0][26][first] = 0;

        // build DP
        for (int ind = 1; ind < n; ind++) {
            int cur = word.charAt(ind) - 'A';

            for (int f1 = 0; f1 < 27; f1++) {
                for (int f2 = 0; f2 < 27; f2++) {

                    if (dp[ind - 1][f1][f2] == INF) continue;

                    // move finger1
                    int cost1 = (f1 == 26 ? 0 : getDistance(f1, cur));
                    dp[ind][cur][f2] = Math.min(
                            dp[ind][cur][f2],
                            dp[ind - 1][f1][f2] + cost1
                    );

                    // move finger2
                    int cost2 = (f2 == 26 ? 0 : getDistance(f2, cur));
                    dp[ind][f1][cur] = Math.min(
                            dp[ind][f1][cur],
                            dp[ind - 1][f1][f2] + cost2
                    );
                }
            }
        }

        // answer
        int ans = INF;
        for (int f1 = 0; f1 < 27; f1++) {
            for (int f2 = 0; f2 < 27; f2++) {
                ans = Math.min(ans, dp[n - 1][f1][f2]);
            }
        }

        return ans;
    }

    public int minimumDistanceRec(String word) {
        n = word.length();
        int[][][] mem = new int[n][27][27];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 27; j++)
                Arrays.fill(mem[i][j], -1);
        }
        return findDistance(n-1, 26, 26, word.toCharArray(), mem);
    }

    int findDistance(int ind, int f1, int f2, char[] arr, int[][][] mem){
        if(ind < 0){
            return 0;
        }
        if(mem[ind][f1][f2] != -1)
            return mem[ind][f1][f2];
        int cur = arr[ind] - 'A';
        int distance1 = (f1==26 ? 0 : getDistance(f1, cur))
                + findDistance(ind-1, cur, f2, arr, mem);
        int distance2 = (f2==26 ? 0 : getDistance(f2, cur))
                + findDistance(ind-1, f1, cur, arr, mem);
        int d =  Math.min(distance1, distance2);
        mem[ind][f1][f2] = d;
        return d;
    }

    private int getDistance(int p, int q) {
        int x1 = p / 6, y1 = p % 6;
        int x2 = q / 6, y2 = q % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
