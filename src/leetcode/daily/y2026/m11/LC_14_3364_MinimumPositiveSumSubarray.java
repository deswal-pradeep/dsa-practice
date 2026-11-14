package leetcode.daily.y2026.m11;

import java.util.List;

public class LC_14_3364_MinimumPositiveSumSubarray {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        //bruteforce way for now as limits are less, can be done with prefix
        int minSum = Integer.MAX_VALUE;
        for(int i = 0; i < nums.size(); i++){
            int sum = 0;
            for(int j = i; j < nums.size(); j++){
                int length = j - i + 1;
                sum += nums.get(j);
                if(length >= l && length <= r && sum > 0){
                    //valid subarray
                    minSum = Math.min(minSum, sum);
                }
            }
        }
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}
