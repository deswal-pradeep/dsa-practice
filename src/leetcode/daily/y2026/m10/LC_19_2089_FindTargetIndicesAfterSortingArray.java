package leetcode.daily.y2026.m10;

import java.util.ArrayList;
import java.util.List;

public class LC_19_2089_FindTargetIndicesAfterSortingArray {
    public List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> list = new ArrayList<>();
        int smaller = 0;
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] < target){
                smaller++;
            } else if (nums[i] == target){
                count++;
            }
        }
        while(count-- > 0){
            list.add(smaller++);
        }
        return list;
    }
}
