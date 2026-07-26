package leetcode.daily.y2026.m07;

public class LC_26_628_MaximumProductOfThreeNumbers {
    public int maximumProduct(int[] nums) {
        // 2 smallest negative numbers
        // 3 largest numbers
        int max1 = (int)-1e9;
        int max2 = (int)-1e9;
        int max3 = (int)-1e9;
        int min1 = (int) 1e9;
        int min2 = (int) 1e9;
        for(int num : nums){
            if(num > max1){
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2){
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }

            if(num < min1){
                min2 = min1;
                min1 = num;
            } else if (num < min2){
                min2 = num;
            }
        }

        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }

    static void main() {
        int ans1 = new LC_26_628_MaximumProductOfThreeNumbers()
                .maximumProduct(new int[]{-1, -2, -3});
        int ans2 = new LC_26_628_MaximumProductOfThreeNumbers()
                .maximumProduct(new int[]{1, 2, 4, -1, -2, -3});
        System.out.println(ans1);
        System.out.println(ans2);
    }
}
