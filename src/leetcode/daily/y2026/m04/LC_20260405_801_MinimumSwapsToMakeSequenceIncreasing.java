package leetcode.daily.y2026.m04;

public class LC_20260405_801_MinimumSwapsToMakeSequenceIncreasing {
    int n;

    public int minSwap(int[] nums1, int[] nums2) {
        n = nums1.length;
        return swap(nums1, nums2, 0);
    }

    int swap(int[] nums1, int[] nums2, int i){
        if(i == n)
            return 0;

        int count = Integer.MAX_VALUE;
        //swap it
        //only if it needs a swap
        if((i < n-1 && ( nums1[i] >= nums1[i+1] || nums2[i] >= nums2[i+1]))
                || ( i > 0 && ( nums1[i-1] >= nums1[i] || nums2[i-1] >= nums2[i] ))
        ){
            int temp = nums1[i];
            nums1[i] = nums2[i];
            nums2[i] = temp;
            count = Math.min(count, 1 + swap(nums1, nums2, i + 1));
            temp = nums1[i];
            nums1[i] = nums2[i];
            nums2[i] = temp;
        } else {
            count = Math.min(count, swap(nums1, nums2, i + 1));
        }

        return count;
    }

    static void main() {
        int[] nums1 = {2,3,2,5,6};
        int[] nums2 = {0,1,4,4,5};
        int ans = new LC_20260405_801_MinimumSwapsToMakeSequenceIncreasing().minSwap(nums1, nums2);
        System.out.println(ans);
    }

}
