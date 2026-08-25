package leetcode.daily.y2026.m08;

import java.util.HashSet;
import java.util.Set;

public class LC_25_3718_SmallestMissingMultipleOfK {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }

        for(int i = 1; i < 110; i++){
            if(!set.contains(i*k))
                return i*k;
        }
        return -1;
    }

    static void main() {
        int ans = new LC_25_3718_SmallestMissingMultipleOfK()
                .missingMultiple(new int[]{8, 2, 3, 4, 6}, 2);
        System.out.println(ans);
    }
}
