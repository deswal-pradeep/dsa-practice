package leetcode.daily.may;

//https://leetcode.com/problems/minimum-common-value/description/?envType=daily-question&envId=2026-05-19
public class LC_20260519_2540_MinimumCommonValue {
    public int getCommon(int[] nums1, int[] nums2) {
        int i1 = 0;
        int i2 = 0;
        while(i1 < nums1.length && i2 < nums2.length){
            if(nums1[i1] == nums2[i2]){
                return nums1[i1];
            } else if(nums1[i1] < nums2[i2]){
                i1++;
            } else {
                i2++;
            }
        }
        return -1;
    }

    static void main() {
        int common = new LC_20260519_2540_MinimumCommonValue()
                .getCommon(new int[]{1,2,3,6},
                        new int[]{2,3,4,5});
        System.out.println(common);
    }
}
