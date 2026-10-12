package leetcode.daily.y2026.m10;

import java.util.ArrayList;
import java.util.List;

public class LC_12_2897_ApplyOperationsOnArrayToMaximizeSumOfSquares {
    static int MOD = 1_000_000_007;
    public int maxSum(List<Integer> nums, int k) {
        int[] places = new int[32];
        for(int i = 0; i < nums.size(); i++){
            int val = 1; int ind = 0; int num = nums.get(i);
            while(val <= num){
                places[ind] = places[ind] + ((num & val) == val ? 1 : 0);
                val = val << 1;
                ind++;
            }
        }
        long ans = 0;
        int ind = 0;
        while(ind < k){
            int val = 0;
            for(int i = 31; i >= 0; i--){
                if(places[i] > 0){
                    val = val | (1 << i);
                    places[i]--;
                }
            }
            ans = ans + ((long) val * val);
            ans %= MOD;
            ind++;
        }
        return (int)ans;
    }

    static void main() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(6);
        list.add(5);
        list.add(8);
        int ans = new LC_12_2897_ApplyOperationsOnArrayToMaximizeSumOfSquares().maxSum(list, 2);
        System.out.println(ans);
    }
}
