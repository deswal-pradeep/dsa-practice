package leetcode.daily.y2026.m08;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/description/?envType=daily-question&envId=2026-08-12
public class LC_12_2958_LengthOfLongestSubarrayWithAtMostKFrequency {
    public int maxSubarrayLength(int[] nums, int k) {
        int l = 0;
        int r = 0;
        int n = nums.length;
        Map<Integer, Integer> freqs = new HashMap<>();
        int maxLen = 0;
        while(r < n){
            int updatedFreq = freqs.getOrDefault(nums[r], 0) + 1;
            freqs.put(nums[r], updatedFreq);
            if(updatedFreq > k){
                while(freqs.get(nums[r]) > k){
                    int newF = freqs.get(nums[l]) - 1;
                    if(newF > 0)
                        freqs.put(nums[l], newF);
                    else
                        freqs.remove(nums[l]);
                    l++;
                }
            } else {
                maxLen = Math.max(maxLen, r - l + 1);
            }
            r++;
        }
        return maxLen;
    }

    static void main() {
        int ans = new LC_12_2958_LengthOfLongestSubarrayWithAtMostKFrequency()
                .maxSubarrayLength(new int[]{1, 2, 3, 1, 2, 3, 1, 2}, 2);
        System.out.println(ans);
    }
}
