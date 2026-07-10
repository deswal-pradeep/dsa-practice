package leetcode.daily.y2026.m05;

//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/?envType=daily-question&envId=2026-05-15
public class LC_15_153_FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while(low < high){
            int mid = (low + high) >> 1;
            if(nums[mid] < nums[high]){
                //means right half is sorted but ans might be there at mid as well
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return nums[high];
    }

    static void main() {
        int min = new LC_15_153_FindMinimumInRotatedSortedArray()
                .findMin(new int[]{4, 5, 6, 7, 0, 1, 2});
        System.out.println(min);
    }
}
