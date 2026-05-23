package leetcode.daily.may;

//https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/?envType=daily-question&envId=2026-05-23
public class LC_20260523_1752_CheckIfArrayIsSortedAndRotated {
    public boolean check(int[] nums){
        int n = nums.length;
        if(n <= 1) return true;
        int inversionCount = 0;
        // For every pair, count the number of inversions.
        for(int i = 1; i < n; i++){
            if(nums[i] < nums[i-1]) inversionCount++;
            if(inversionCount > 1) return false;
        }
        // Also check between the last and the first element due to rotation
        if(nums[0] < nums[n-1]) inversionCount++;
        return inversionCount <= 1;
    }

    public boolean check1(int[] nums) {
        //1. otherwise everywhere nums[i-1] <= nums[i]
        //2. one nums[i-1] > nums[i] is ok
        //3. if points happens, al number should be <= nums[0]
        boolean oneDrop = false;
        for(int i = 1; i < nums.length; i++){
            if(oneDrop && nums[i] < nums[i-1]){
                return false;
            }
            if(!oneDrop && nums[i] < nums[i-1]){
                oneDrop = true;
            }
            if(oneDrop && nums[i] > nums[0]){
                return false;
            }
        }
        return true;
    }

    static void main() {
        boolean check = new LC_20260523_1752_CheckIfArrayIsSortedAndRotated()
                .check(new int[]{3, 4, 5, 1, 2});
        System.out.println(check);
    }
}
