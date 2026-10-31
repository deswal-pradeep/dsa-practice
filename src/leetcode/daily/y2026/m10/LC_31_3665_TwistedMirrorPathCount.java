package leetcode.daily.y2026.m10;

import java.util.Arrays;

public class LC_31_3665_TwistedMirrorPathCount {
    static int MOD = 1_000_000_007;
    int m, n;
    public int uniquePaths(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int[][][] mem = new int[m][n][3];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++)
                Arrays.fill(mem[i][j], -1);
        }
        return dp(grid, 0, 0, 0, mem);
    }

    int dp(int[][] grid, int i, int j, int prevMove, int[][][] mem){
        if(i == m-1 && j == n-1)
            return 1;
        if(i == m || j == n)
            return 0;
        if(mem[i][j][prevMove] != -1)
            return mem[i][j][prevMove];
        int result = 0;
        if(grid[i][j] == 0){
            result = (dp(grid, i, j+1, 1, mem)
                    + dp(grid, i+1, j, 2, mem)) % MOD;
        } else {
            //a mirror
            if(prevMove == 1)
                result = dp(grid, i+1, j, 2, mem);
            if(prevMove == 2)
                result = dp(grid, i, j+1, 1, mem);
        }
        mem[i][j][prevMove] = result;
        return result;
    }

    static void main() {
        int[][] grid = new int[][]{{0,1,0}, {0,0,1}, {1,0,0}};
        int ans = new LC_31_3665_TwistedMirrorPathCount().uniquePaths(grid);
        System.out.println(ans);
    }
}
