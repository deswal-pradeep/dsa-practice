package leetcode.daily.y2026.m08;

//https://leetcode.com/problems/stone-game-iv/?envType=daily-question&envId=2026-08-10
public class LC_10_1510_StoneGameIV {
    public boolean winnerSquareGame(int n) {
        Boolean[][] mem = new Boolean[2][n+1];
        return f(n, 0, mem);
    }

    boolean f(int n, int turn, Boolean[][] mem){
        if(n == 0){
            //if bob's turn, alice wins
            return turn == 1;
        }
        if(mem[turn][n] != null)
            return mem[turn][n];
        boolean ans = false;
        if(turn == 0){
            boolean result = false;
            for(int i = 1; i * i <= n; i++){
                result = result | f(n - (i*i), 1, mem);
            }
            ans = result;
        } else {
            boolean result = true;
            for(int i = 1; i * i <= n; i++){
                result = result & f(n - (i*i), 0, mem);
            }
            ans = result;
        }
        mem[turn][n] = ans;
        return ans;
    }

    static void main() {
        boolean result = new LC_10_1510_StoneGameIV().winnerSquareGame(7);
        System.out.println(result);
    }
}
