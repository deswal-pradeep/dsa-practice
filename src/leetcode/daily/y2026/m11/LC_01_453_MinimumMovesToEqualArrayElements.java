package leetcode.daily.y2026.m11;

import java.util.Arrays;

//https://leetcode.com/problems/minimum-moves-to-equal-array-elements/?envType=daily-question&envId=2026-11-01
public class LC_01_453_MinimumMovesToEqualArrayElements {
    public int minMoves_sortAndCountDiffMulBy1ToNMinus(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length-1;
        int j = 1;
        int ans = 0;
        for(int i = n-1; i >= 0; i--){
            int diff = nums[i+1] - nums[i];
            ans = ans + (diff * j);
            j += 1;
        }
        return ans;
    }
    //if we see increasing n-1 elements by 1 means decreasing largest number by 1 .. smart
    public int minMoves(int[] nums) {
        int min = nums[0];
        for(int i = 0; i < nums.length; i++){
            min = Math.min(min, nums[i]);
        }
        int ans = 0;
        for(int i = 0; i < nums.length; i++){
            ans = ans + (nums[i] - min);
        }
        return ans;
    }

}
