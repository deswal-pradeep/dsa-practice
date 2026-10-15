package leetcode.daily.y2026.m10;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC_15_2913_SubarraysDistinctElementSumOfSquaresI {
    public int sumCounts(List<Integer> nums) {
        int ans = 0;
        for(int i = 0; i < nums.size(); i++){
            Set<Integer> set = new HashSet<>();
            for(int j = i; j < nums.size(); j++){
                set.add(nums.get(j));
                ans += (set.size() * set.size());
            }
        }
        return ans;
    }

    static void main() {
        int ans = new LC_15_2913_SubarraysDistinctElementSumOfSquaresI()
                .sumCounts(Arrays.asList(1, 2, 1));
        System.out.println(ans);
    }
}
