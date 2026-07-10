package leetcode.daily.y2026.m04;

public class LC_20260413_1848_MinimumDistanceToTargetElement {
    static void main() {
        new LC_20260413_1848_MinimumDistanceToTargetElement().getMinDistance(new int[] {1,1,1,1,1,1,1,1,1,1}, 1, 0);
    }

    public int getMinDistance(int[] nums, int target, int start) {
        int minAbs = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == target){
                int abs = Math.abs(i - start);
                if(abs < minAbs){
                    minAbs = abs;
                }
            }
        }
        return minAbs;
    }
}
