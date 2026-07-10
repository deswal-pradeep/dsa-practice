package leetcode.daily.y2026.m04;

import java.util.HashMap;
import java.util.Map;

public class LC_20260417_3761_minimumAbsoluteDistanceBetweenMirrorPairs {
    //https://leetcode.com/problems/minimum-absolute-distance-between-mirror-pairs/?envType=daily-question&envId=2026-04-17
    static void main() {
        int ans  = new LC_20260417_3761_minimumAbsoluteDistanceBetweenMirrorPairs().minMirrorPairDistance(new int[]{120,21});
        System.out.println(ans);
    }
    public int minMirrorPairDistance(int[] nums) {
        int n = nums.length;
        Integer ans = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            int rev = reverse(nums[i]);
            Integer pIndex = map.get(nums[i]);
            if(pIndex != null){
                ans = Math.min(i - pIndex, ans);
            }
            map.put(rev, i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int reverse(int x){
        int val = 0;
        while(x > 0){
            val = val * 10 + (x % 10);
            x /= 10;
        }
        return val;
    }
}
