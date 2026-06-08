package leetcode.daily.jun;

import java.util.Arrays;

public class LC_20260608_2161_PartitionArrayAccordingToGivenPivot {
    public int[] pivotArray_1(int[] nums, int pivot) {
        int[] ans = new int[nums.length];
        int lessI = 0;
        int greaterI = nums.length - 1;
        for (int i = 0, j = nums.length - 1; i < nums.length; i++, j--) {
            if (nums[i] < pivot) {
                ans[lessI] = nums[i];
                lessI++;
            }
            if (nums[j] > pivot) {
                ans[greaterI] = nums[j];
                greaterI--;
            }
        }
        while (lessI <= greaterI) {
            ans[lessI] = pivot;
            lessI++;
        }
        return ans;
    }

    public int[] pivotArray(int[] nums, int pivot) {
        int[] ans =  new int[nums.length];
        int i = 0;
        for(int num : nums){
            if(num < pivot){
                ans[i++] =  num;
            }
        }

        for(int num : nums){
            if(num == pivot){
                ans[i++] =  num;
            }
        }

        for(int num : nums){
            if(num > pivot){
                ans[i++] =  num;
            }
        }

        return ans;
    }

    static void main() {
        int[] ans = new LC_20260608_2161_PartitionArrayAccordingToGivenPivot()
                .pivotArray(new int[]{9, 12, 5, 10, 14, 3, 10}, 10);
        System.out.println(Arrays.toString(ans));
    }
}
