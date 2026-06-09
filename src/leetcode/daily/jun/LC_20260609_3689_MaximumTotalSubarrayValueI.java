package leetcode.daily.jun;

//https://leetcode.com/problems/maximum-total-subarray-value-i/description/?envType=daily-question&envId=2026-06-09
public class LC_20260609_3689_MaximumTotalSubarrayValueI {
    public long maxTotalValue(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int num : nums){
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return 1l * k * (max - min);
    }

    static void main() {
        long ans = new LC_20260609_3689_MaximumTotalSubarrayValueI()
                .maxTotalValue(new int[]{1,3,2,7,6,9,4,3,0,8,10,11,15,3,2,1}, 4);
        System.out.println(ans);
    }
}
