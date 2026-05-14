package leetcode.daily.may;

//https://leetcode.com/problems/check-if-array-is-good/?envType=daily-question&envId=2026-05-14
public class LC_20260514_2784_CheckIfArrayIsGood {
    public boolean isGood(int[] nums) {
        int max = nums[0];
        int n = nums.length;
        int[] f = new int[n];
        for(int i = 0; i < n; i++){
            max = Math.max(max, nums[i]);
            if(nums[i] >= nums.length)
                return false;
            f[nums[i]] += 1;
        }

        for(int i = 1; i < n-1; i++){
            if(f[i] != 1)
                return false;
        }
        return f[n-1] == 2 ? true : false;
    }

    static void main() {
        boolean ans = new LC_20260514_2784_CheckIfArrayIsGood()
                .isGood(new int[]{3, 4, 4, 1, 2, 1});
        System.out.println(ans);
    }
}
