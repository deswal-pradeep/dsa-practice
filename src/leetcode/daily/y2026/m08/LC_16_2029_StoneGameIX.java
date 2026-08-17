package leetcode.daily.y2026.m08;

public class LC_16_2029_StoneGameIX {
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];
        for(int s : stones){
            count[s%3]++;
        }
        return f(count[0], count[1], count[2], 0, 0);
    }

    boolean f(int zero, int one, int two, int sum, int turn){
        if(zero == 0 && one == 0 && two == 0){
            return false;
        }
        //alice turn
        boolean ans = false;
        if(turn == 0){
            ans = false;
            if(zero > 0){
                if((sum % 3) == 0)
                    ans = ans | false;
                else
                    ans = ans | f(zero-1, one, two, sum, 1);
            }
            if(one > 0){
                if((sum + 1) % 3 == 0)
                    ans = ans | false;
                else
                    ans = ans | f(zero, one-1, two, (sum + 1)%3, 1);
            }
            if(two > 0){
                if((sum + 2)% 3 == 0)
                    ans = ans | false;
                else
                    ans = ans | f(zero, one, two-1, (sum + 2)%3, 1);
            }
        } else {
            ans = true;
            if(zero > 0){
                if(sum % 3 == 0)
                    ans = ans & true;
                else
                    ans = ans & f(zero-1, one, two, sum, 0);
            }
            if(one > 0){
                if((sum + 1) % 3 == 0)
                    ans = ans & true;
                else
                    ans = ans & f(zero, one-1, two, (sum + 1)%3, 0);
            }
            if(two > 0){
                if((sum + 2)% 3 == 0)
                    ans = ans & true;
                else
                    ans = ans & f(zero, one, two-1, (sum + 2)%3, 0);
            }
        }
        return ans;
    }

    static void main() {
        for(int z = 0; z <= 5; z++){
            for(int o = 0; o <= 4; o++){
                for(int t = 0; t <=4; t++){
                    boolean ans = new LC_16_2029_StoneGameIX().f(z,o,t,0,0);
                    System.out.println(z+", "+o+", "+t+" : "+ans);
                }
            }
        }
    }
}
