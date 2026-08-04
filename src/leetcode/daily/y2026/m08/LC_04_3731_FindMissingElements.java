package leetcode.daily.y2026.m08;

import java.util.ArrayList;
import java.util.List;

public class LC_04_3731_FindMissingElements {
    public List<Integer> findMissingElements(int[] nums) {
        int min = (int)1e9;
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        int[] set = new int[max - min + 1];
        for(int num : nums)
            set[num-min] = 1;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < set.length; i++){
            if(set[i] == 0)
                list.add(i + min);
        }
        return list;
    }

    static void main() {
        List<Integer> missingElements = new LC_04_3731_FindMissingElements()
                .findMissingElements(new int[]{1, 4, 2, 5});
        System.out.println(missingElements);
    }
}
