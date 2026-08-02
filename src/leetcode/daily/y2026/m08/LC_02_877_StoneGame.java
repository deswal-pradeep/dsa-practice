package leetcode.daily.y2026.m08;

public class LC_02_877_StoneGame {
    public boolean stoneGame(int[] piles) {
        return true;
        //return play(piles, 0, piles.length-1, 0, 0, 1);
    }

    boolean play(int[] piles, int i, int j, int scoreA, int scoreB, int turn){
        if(i > j){
            return scoreA > scoreB;
        }
        if(turn == 1){
            return play(piles, i+1, j, scoreA + piles[i], scoreB, 2)
                    | play(piles, i, j-1, scoreA + piles[j], scoreB, 2);
        } else {
            return play(piles, i+1, j, scoreA, scoreB + piles[i], 1)
                    & play(piles, i, j-1, scoreA, scoreB + piles[j], 1);
        }
    }
}
