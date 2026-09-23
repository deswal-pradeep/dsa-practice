package leetcode.daily.y2026.m09;

public class LC_23_1658_MinimumOperationsToReduceXToZero {
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for(int num : nums)
            sum += num;
        if(sum < x)
            return -1;
        if(sum == x)
            return nums.length;

        int k = sum - x;
        int l = 0;
        int r = 0;
        int maxLength = -1;
        sum = 0;
        while(r < nums.length){
            sum += nums[r];
            while(sum >= k){
                if(sum == k){
                    maxLength = Math.max(maxLength, r - l + 1);
                }
                sum -= nums[l];
                l++;
            }
            r++;
        }
        return maxLength < 0 ? -1 : nums.length - maxLength;
    }

    static void main() {
        int ans = new LC_23_1658_MinimumOperationsToReduceXToZero()
                .minOperations(new int[]{5}, 5);
        System.out.println(ans);
    }
}
