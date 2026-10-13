package leetcode.daily.y2026.m10;

import java.util.*;

public class LC_13_3727_MaximumAlternatingSumOfSquares {
    public long maxAlternatingSum(int[] nums) {
        long ans = 0;
        for(int i = 0; i < nums.length; i++){
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        int n = nums.length;
        for(int i = 0; i < nums.length/2; i++){
            ans -= nums[i];
            ans += nums[n-i-1];
        }
        if(n % 2 != 0) ans += nums[n/2];
        return ans;
    }

    static void main() {
        long l = new LC_13_3727_MaximumAlternatingSumOfSquares()
                .maxAlternatingSum(new int[]{1, -1, 2, -2, 3, -3});
        System.out.println(l);
    }
}
