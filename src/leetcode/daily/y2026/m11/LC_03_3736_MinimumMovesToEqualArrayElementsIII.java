package leetcode.daily.y2026.m11;

public class LC_03_3736_MinimumMovesToEqualArrayElementsIII {
    public int minMoves(int[] nums) {
        int maxi = nums[0];
        int ans = 0;
        for(int i = 1; i < nums.length; i++)
            maxi = Math.max(maxi, nums[i]);
        for(int i = 0; i < nums.length; i++)
            ans += (maxi - nums[i]);
        return ans;
    }

    static void main() {
        int ans = new LC_03_3736_MinimumMovesToEqualArrayElementsIII().minMoves(new int[]{4, 4, 5});
        System.out.println(ans);
    }
}
