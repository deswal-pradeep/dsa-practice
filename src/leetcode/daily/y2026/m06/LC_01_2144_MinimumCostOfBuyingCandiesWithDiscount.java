package leetcode.daily.y2026.m06;

import java.util.Arrays;

//https://leetcode.com/problems/minimum-cost-of-buying-candies-with-discount/?envType=daily-question&envId=2026-06-01
public class LC_20260601_2144_MinimumCostOfBuyingCandiesWithDiscount {
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);
        int total = 0;
        for(int i = cost.length-1, ind = 1; i >= 0; i--, ind++){
            if(ind%3 == 0){
                continue;
            }
            total+=cost[i];
        }
        return total;
    }

    static void main() {
        int ans = new LC_20260601_2144_MinimumCostOfBuyingCandiesWithDiscount()
                .minimumCost(new int[]{6, 5, 7, 9, 2, 2});
        System.out.println(ans);
    }
}
