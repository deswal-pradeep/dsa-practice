package leetcode.daily.y2026.m07;

//https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/?envType=daily-question&envId=2026-07-27
public class LC_27_1464_MaximumProductOfTwoElementsInAnArray {
    public int maxProduct(int[] nums) {
        int max1 = (int)-1e9;
        int max2 = (int)-1e9;

        for(int num : nums){
            if(num-1 > max1){
                max2 = max1;
                max1 = num-1;
            } else if(num-1 > max2){
                max2 = num-1;
            }
        }
        return max1 * max2;
    }

    static void main() {
        int ans = new LC_27_1464_MaximumProductOfTwoElementsInAnArray()
                .maxProduct(new int[]{3, 4, 5, 2});
        System.out.println(ans);
    }
}
