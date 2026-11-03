package leetcode.daily.y2026.m11;

import java.util.Arrays;

public class LC_02_462_MinimumMovesToEqualArrayElementsII {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int meanIndex = n / 2;
        int meanValue = nums[meanIndex];
        int ans = 0;
        for(int i = 0; i < n; i++){
            ans += Math.abs(nums[i] - meanValue);
        }
        return ans;
    }

    static void main() {
        int ans = new LC_02_462_MinimumMovesToEqualArrayElementsII().minMoves2(new int[]{1, 10, 2, 9});
        System.out.println(ans);
    }
}
