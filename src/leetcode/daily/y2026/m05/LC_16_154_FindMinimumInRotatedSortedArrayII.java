package leetcode.daily.y2026.m05;

//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/description/?envType=daily-question&envId=2026-05-16
public class LC_20260516_154_FindMinimumInRotatedSortedArrayII {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length-1;
        return min(nums, low, high);
    }

    int min(int[] nums, int low, int high){
        while(low < high){
            int mid = (low + high) >> 1;
            if(nums[mid] == nums[low] && nums[mid] == nums[high]){
                return Math.min(min(nums, low, mid), min(nums, mid+1, high));
            }
            else if(nums[mid] <= nums[high]){
                //go left
                high = mid;
            } else {
                //go right
                low = mid + 1;
            }
        }
        return nums[high];
    }

    static void main() {
        int min = new LC_20260516_154_FindMinimumInRotatedSortedArrayII()
                .findMin(new int[]{2, 2, 2, 0, 1});
        System.out.println(min);
    }
}
