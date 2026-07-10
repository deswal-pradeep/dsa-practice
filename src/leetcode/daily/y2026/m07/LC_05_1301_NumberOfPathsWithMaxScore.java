package leetcode.daily.y2026.m07;

import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/number-of-paths-with-max-score/description/?envType=daily-question&envId=2026-07-05
public class LC_05_1301_NumberOfPathsWithMaxScore {
    static int MOD = 1_000_000_007;
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int[][][] dp = new int[n+1][n+1][2];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i == 1 & j == 1){
                    dp[i][j] = new int[]{0, 1};
                    continue;
                }
                if(board.get(i-1).charAt(j-1) == 'X'){
                    dp[i][j] = new int[]{0, 0};
                    continue;
                }
                int[] way1 = dp[i-1][j];
                int[] way2 = dp[i][j-1];
                int[] way3 = dp[i-1][j-1];

                int max = Math.max(way1[0], way2[0]);
                max = Math.max(max, way3[0]);
                int value = board.get(i-1).charAt(j-1) != 'S' ? (int)(board.get(i-1).charAt(j-1) - '0') : 0;

                int ways = 0;
                if(max == way1[0])
                    ways = (ways + way1[1]) % MOD;
                if(max == way2[0])
                    ways = (ways + way2[1]) % MOD;
                if(max == way3[0])
                    ways = (ways + way3[1]) % MOD;
                int[] result = ways == 0 ? new int[]{0, 0} : new int[]{max+value, ways};
                dp[i][j] = result;
            }
        }
        return dp[n][n];
    }
    public int[] pathsWithMaxScore_1(List<String> board) {
        int n = board.size();
        int[][][] mem = new int[n][n][2];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n ; j++){
                mem[i][j] = null;
            }
        }
        int[] ans = count(board, n-1, n-1, mem);
        return ans;
    }

    int[]  count(List<String> board, int i, int j, int[][][] mem){
        if(i == 0 && j == 0){
            return new int[]{0, 1};
        }
        if(i < 0 || j < 0 || board.get(i).charAt(j) == 'X'){
            return new int[]{0, 0};
        }

        if(mem[i][j] != null){
            return mem[i][j];
        }

        int[] way1 = count(board, i-1, j, mem);
        int[] way2 = count(board, i, j-1, mem);
        int[] way3 = count(board, i-1, j-1, mem);

        int max = Math.max(way1[0], way2[0]);
        max = Math.max(max, way3[0]);
        int value = board.get(i).charAt(j) != 'S' ? (int)(board.get(i).charAt(j) - '0') : 0;

        int ways = 0;
        if(max == way1[0])
            ways = (ways + way1[1]) % MOD;
        if(max == way2[0])
            ways = (ways + way2[1]) % MOD;
        if(max == way3[0])
            ways = (ways + way3[1]) % MOD;
        int[] result = ways == 0 ? new int[]{0, 0} : new int[]{max+value, ways};
        mem[i][j] = result;
        return result;
    }

    static void main() {
        List<String> board = Arrays.asList("E12","1X1","21S");
        int[] ans = new LC_05_1301_NumberOfPathsWithMaxScore().pathsWithMaxScore(board);
        System.out.println(Arrays.toString(ans));
    }
}
