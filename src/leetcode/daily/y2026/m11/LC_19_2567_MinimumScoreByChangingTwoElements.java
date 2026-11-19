package leetcode.daily.y2026.m11;

public class LC_19_2567_MinimumScoreByChangingTwoElements {
    public int minimizeSum(int[] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int min3 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        for(int i = 0; i < nums.length; i++){
            if(max1 < nums[i]){
                max3 = max2;
                max2 = max1;
                max1 = nums[i];
            } else if (max2 < nums[i]){
                max3 = max2;
                max2 = nums[i];
            } else if (max3 < nums[i]){
                max3 = nums[i];
            }
            if(nums[i] < min1){
                min3 = min2;
                min2 = min1;
                min1 = nums[i];
            } else if (nums[i] < min2){
                min3 = min2;
                min2 = nums[i];
            } else if(nums[i] < min3){
                min3 = nums[i];
            }
        }
        return Math.min(
                max2 - min2,
                Math.min(
                        max1 - min3,
                        max3 - min1));
    }

    static void main() {
        int ans = new LC_19_2567_MinimumScoreByChangingTwoElements()
                .minimizeSum(new int[]{59, 27, 9, 81, 33});
        System.out.println(ans);
    }
}
