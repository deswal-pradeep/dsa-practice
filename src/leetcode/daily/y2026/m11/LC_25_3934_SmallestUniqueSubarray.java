package leetcode.daily.y2026.m11;

import java.util.HashMap;
import java.util.Map;

public class LC_25_3934_SmallestUniqueSubarray {
    public int smallestUniqueSubarray(int[] nums) {
        long MOD = 1_000_000_007;
        long BASE = 911_382_323L;
        int n = nums.length;
        long[] p = new long[n+1];
        long[] h = new long[n+1];
        p[0] = 1;
        for(int i = 1; i <= n; i++){
            p[i] = (p[i-1] * BASE) % MOD;
            h[i] = ((h[i-1] * BASE) + nums[i-1]) % MOD;
        }
        int low = 1;
        int high = n;
        while(low < high){
            int mid = (low + high) / 2;
            if(isUnique(mid, n, h, p, MOD)){
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    boolean isUnique(int len, int n, long[] h, long[] p, long MOD){
        Map<Long, Integer> map = new HashMap<>();
        for(int l = 0; l + len <= n; l++){
            int r = l + len;
            long key = (h[r] - (h[l] * p[len] % MOD) + MOD) % MOD;
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        for(int value : map.values()){
            if(value == 1)
                return true;
        }
        return false;
    }

    static void main() {
        int ans = new LC_25_3934_SmallestUniqueSubarray().smallestUniqueSubarray(new int[]{1, 1, 2, 2, 1});
        System.out.println(ans);
    }
}
