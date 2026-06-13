package leetcode.daily.y2026.april;

import java.util.Arrays;

//https://leetcode.com/problems/maximum-path-score-in-a-grid/?envType=daily-question&envId=2026-04-30
public class LC_20260430_3742_MaximumPathScoreInAGrid {
    int m, n;
    int INT_MAX = (int)-1e9+7;
    public int maxPathScore(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        int[][][] mem = new int[m][n][k+1];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                Arrays.fill(mem[i][j], INT_MAX);
            }
        }
        int cost = path(grid, 0, 0, k, mem);
        return cost < 0 ? -1 : cost;
    }

    int path(int[][] grid, int i, int j, int k, int[][][] mem){
        if(i == m-1 && j == n-1){
            if((grid[i][j] > 0 && k > 0)
                    || grid[i][j] == 0 && k >= 0){
                return grid[i][j];
            }
        }

        if(i == m || j == n || k < 0){
            return (int)-1e9;
        }
        if(mem[i][j][k] != INT_MAX){
            return mem[i][j][k];
        }
        //2 choices, either go right or down
        int cost = grid[i][j] == 0 ? 0 : 1;
        int score = grid[i][j]
                + Math.max(
                path(grid, i+1, j, k - cost, mem),
                path(grid, i, j+1, k - cost, mem));
        mem[i][j][k] = score;
        return score;
    }

    static void main() {
        int[][] grid = {{0, 1, 0, 1, 0, 0, 2, 0}};
        int ans = new LC_20260430_3742_MaximumPathScoreInAGrid().maxPathScore(grid, 88);
        System.out.println(ans);
    }
}
