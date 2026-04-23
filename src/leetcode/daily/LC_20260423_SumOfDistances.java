package leetcode.daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC_20260423_SumOfDistances {
    //https://leetcode.com/problems/sum-of-distances/?envType=daily-question&envId=2026-04-23
    static void main() {
        new LC_20260423_SumOfDistances().distanceEditorial(new int[]{100, 100, 100});
    }
    public long[] distanceEditorial(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            groups.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        long[] res = new long[n];
        for (List<Integer> group : groups.values()) {
            long total = 0;
            for (int idx : group) {
                total += idx;
            }
            long prefixTotal = 0;
            int sz = group.size();
            for (int i = 0; i < sz; i++) {
                int idx = group.get(i);
                res[idx] = total - prefixTotal * 2 + (long) idx * (2 * i - sz);
                prefixTotal += idx;
            }
        }
        return res;
    }

    public long[] distance(int[] nums) {
        int n = nums.length;
        System.out.println(n);
        long[] ans = new long[n];
        Map<Integer, Long> sum = new HashMap<>();
        Map<Integer, Integer> freq = new HashMap<>();
        for(int i = 0; i < n; i++){
            sum.put(nums[i], sum.getOrDefault(nums[i], 0l) + i);
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }

        Map<Integer, Long> preSum = new HashMap<>();
        Map<Integer, Integer> preFreq = new HashMap<>();
        for(int i = 0; i < n; i++){
            long pSum = preSum.getOrDefault(nums[i], 0l);
            int pFreq = preFreq.getOrDefault(nums[i], 0);
            long sSum = sum.get(nums[i]) - pSum - i;
            int sFreq = freq.get(nums[i]) - pFreq - 1;
            ans[i] = ((1l * i * pFreq) - pSum)
                    + (sSum - (1l * i * sFreq));
            preSum.put(nums[i], pSum + i);
            preFreq.put(nums[i], pFreq + 1);

        }
        return ans;
    }
}
