package leetcode.daily.y2026.m08;

public class LC_15_3702_LongestSubsequenceWithNonZeroBitwiseXOR {
    public int longestSubsequence(int[] nums) {
        int xorAll = 0;
        boolean areAllZero = true;
        for(int num : nums){
            xorAll = xorAll ^ num;
            if(num > 0)
                areAllZero = false;
        }
        int ans = nums.length;
        if(areAllZero)
            ans = 0;
        else if (xorAll == 0)
            ans = nums.length - 1;
        return ans;
    }

    static void main() {
        int ans = new LC_15_3702_LongestSubsequenceWithNonZeroBitwiseXOR()
                .longestSubsequence(new int[]{1, 2, 3});
        System.out.println(ans);
    }
}
