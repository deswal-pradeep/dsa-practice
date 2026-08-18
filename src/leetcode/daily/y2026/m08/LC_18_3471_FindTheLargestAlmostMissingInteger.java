package leetcode.daily.y2026.m08;

public class LC_18_3471_FindTheLargestAlmostMissingInteger {
    public int largestInteger(int[] nums, int k) {
        int[] f = new int[51];
        int maxi =0;
        for(int num : nums){
            f[num]++;
        }
        int ans = -1;
        if(k == 1){
            for(int i = f.length-1; i >= 0; i--)
                if(f[i] == 1)
                    return i;
        } else if(k == nums.length){
            for(int i = f.length-1; i >= 0; i--)
                if(f[i] >= 1)
                    return i;
        }


        if(f[nums[0]]==1){
            ans = nums[0];
        }
        if(f[nums[nums.length-1]] == 1
                && (ans == -1 || nums[nums.length-1] > nums[0]) )
            ans = nums[nums.length-1];
        return ans;
    }

    static void main() {
        int ans = new LC_18_3471_FindTheLargestAlmostMissingInteger()
                .largestInteger(new int[]{3, 9, 2, 1, 7}, 3);
        System.out.println(ans);
    }
}
