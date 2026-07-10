package leetcode.daily.y2026.m05;

//https://leetcode.com/problems/search-in-rotated-sorted-array/?envType=daily-question&envId=2026-05-22
public class LC_22_33_SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] == target)
                return mid;
            if(nums[mid] >= nums[left]){
                //means left is sorted
                if(target < nums[left] || target > nums[mid]){
                    //target is not in range, go right
                    left = mid+1;
                } else {
                    //target is in range, go left
                    right = mid-1;
                }
            } else{
                //right is sorted
                if(target < nums[mid] || target > nums[right]){
                    //check on left side
                    right = mid-1;
                } else {
                    //go right
                    left = mid+1;
                }
            }
        }
        return -1;
    }

    static void main() {
        int search = new LC_22_33_SearchInRotatedSortedArray()
                .search(new int[]{3, 1}, 1);
        System.out.println(search);
    }
}
