package leetcode.daily.y2026.m11;

public class LC_12_3105_LongestStrictlyIncreasingOrStrictlyDecreasingSubarray {
    public int longestMonotonicSubarray(int[] nums) {
        int maxLength = 1;
        for(int i = 0; i < nums.length; i++){
            int type = 0; //1 increasing, -1 decrasing
            for(int j = i+1; j < nums.length; j++){
                if(nums[j] == nums[j-1]){
                    maxLength = Math.max(maxLength, j - i);
                    break;
                }
                if(type == 0){
                    type = nums[j] > nums[j-1] ? 1 : -1;
                } else if (type == 1 && nums[j] < nums[j-1]){
                    maxLength = Math.max(maxLength, j - i);
                    break;
                } else if(type == -1 && nums[j] > nums[j-1]){
                    maxLength = Math.max(maxLength, j - i);
                    break;
                }
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }
        return maxLength;
    }

    static void main() {
        int ans = new LC_12_3105_LongestStrictlyIncreasingOrStrictlyDecreasingSubarray()
                .longestMonotonicSubarray(new int[]{1, 4, 3, 3, 2});
        System.out.println(ans);
    }
}
