package leetcode.daily.y2026.m08;

import java.util.Arrays;

public class LC_09_1140_StoneGameII {
    int n;
    public int stoneGameII(int[] piles) {
        n = piles.length;
        int[][][] mem = new int[2*n][n][2];
        for(int i = 0; i < 2*n; i++){
            for(int j = 0; j < n; j++){
                Arrays.fill(mem[i][j], -1);
            }
        }
        return f(piles, 1, 0, 0, mem);
    }

    int f(int[] piles, int m, int ind, int turn, int[][][] mem){
        if(ind == n)
            return 0;
        if(mem[m][ind][turn] != -1)
            return mem[m][ind][turn];
        int aScore = 0;
        if(turn == 0){
            //alice turn
            int score = 0;
            int t = 0;
            for(int x = 1; x <= 2 * m && ind+x-1 < n; x++){
                t = t + piles[x + ind - 1];
                score = Math.max(score,
                        t + f(piles, Math.max(x, m), ind + x, 1, mem));
            }
            aScore = score;
        } else {
            //bob turn
            int score = (int)1e9;
            for(int x = 1; x <= 2 * m && ind+x-1 < n; x++){
                score = Math.min(score,
                        f(piles, Math.max(x, m), ind + x, 0, mem));
            }
            aScore = score;
        }
        mem[m][ind][turn] = aScore;
        return aScore;
    }
}
