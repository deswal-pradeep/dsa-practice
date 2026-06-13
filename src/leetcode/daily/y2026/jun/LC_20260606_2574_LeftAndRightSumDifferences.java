package leetcode.daily.y2026.jun;

import java.util.Arrays;

//https://leetcode.com/problems/left-and-right-sum-differences/description/?envType=daily-question&envId=2026-06-06
public class LC_20260606_2574_LeftAndRightSumDifferences {
    public int[] leftRightDifference(int[] nums) {
        int sum = 0;
        int[] ans = new int[nums.length];

        int leftSum = 0;
        for(int i = 0; i < nums.length; i++){
            ans[i] += leftSum;
            leftSum += nums[i];
        }
        int rightSum = 0;
        for(int i = nums.length -1; i>=0; i--){
            ans[i] = Math.abs(ans[i] - rightSum);
            rightSum+=nums[i];
        }
        return ans;
    }

    static void main() {
        int[] ans = new LC_20260606_2574_LeftAndRightSumDifferences()
                .leftRightDifference(new int[]{10, 4, 8, 3});
        System.out.println(Arrays.toString(ans));
    }
}
