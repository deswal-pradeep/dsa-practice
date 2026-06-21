package leetcode.daily.y2026.jun;

public class LC_20260621_1833_MaximumIceCreamBars {
    public int maxIceCream(int[] costs, int coins) {
        int max = 0;
        for(int cost : costs)
            max = Math.max(max, cost);

        int[] cntArray = new int[max+1];

        for(int cost  : costs)
            cntArray[cost]++;

        int ans = 0;

        for(int i = 0; i < cntArray.length; i++){
            if(i > coins)
                break;
            int coinsNeededForFull = cntArray[i] * i;
            if(coins >= coinsNeededForFull){
                coins = coins - coinsNeededForFull;
                ans += cntArray[i];
            } else {
                int count = coins / i;
                coins = coins - (count * i);
                ans += count;
                break;
            }
        }
        return ans;
    }

    static void main() {
        int ans = new LC_20260621_1833_MaximumIceCreamBars()
                .maxIceCream(new int[]{1, 3, 2, 4, 1}, 7);
        System.out.println(ans);
    }
}
