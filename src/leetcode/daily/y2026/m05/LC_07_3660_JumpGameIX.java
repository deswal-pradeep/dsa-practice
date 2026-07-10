package leetcode.daily.y2026.m05;

import java.util.Arrays;

//https://leetcode.com/problems/jump-game-ix/?envType=daily-question&envId=2026-05-07
public class LC_07_3660_JumpGameIX {
    int n;
    public int[] maxValue(int[] nums) {
        n = nums.length;
        int[] maxArr = new int[n];
        maxArr[0] = nums[0];
        for(int i = 1; i < n; i++)
            maxArr[i] = Math.max(nums[i], maxArr[i-1]);


        int minRight = nums[n-1];
        int[] ans = new int[n];
        ans[n-1] = maxArr[n-1];
        for(int i = n-2; i >= 0; i--){
            ans[i] = maxArr[i];
            if(minRight < ans[i]){
                ans[i] = Math.max(ans[i+1], ans[i]);
            }
            minRight = Math.min(minRight, nums[i]);
        }
        return ans;
    }

    static void main() {
        int[] ans = new LC_07_3660_JumpGameIX().maxValue(new int[]{30,21,5,35,24});
        System.out.println(Arrays.toString(ans));
    }
}
