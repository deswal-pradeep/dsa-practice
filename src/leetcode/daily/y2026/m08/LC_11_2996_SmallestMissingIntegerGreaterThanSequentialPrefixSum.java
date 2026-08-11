package leetcode.daily.y2026.m08;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/smallest-missing-integer-greater-than-sequential-prefix-sum/?envType=daily-question&envId=2026-08-11
public class LC_11_2996_SmallestMissingIntegerGreaterThanSequentialPrefixSum {
    public int missingInteger(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums)
            set.add(num);
        int prefixSum = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(nums[i] != nums[i-1]+1){
                break;
            }
            prefixSum += nums[i];
        }
        while(set.contains(prefixSum))
            prefixSum++;
        return prefixSum;
    }

    static void main() {
        int ans = new LC_11_2996_SmallestMissingIntegerGreaterThanSequentialPrefixSum()
                .missingInteger(new int[]{4, 5, 6, 7, 8, 8, 9, 4, 3, 2, 7});
        System.out.println(ans);
    }
}
