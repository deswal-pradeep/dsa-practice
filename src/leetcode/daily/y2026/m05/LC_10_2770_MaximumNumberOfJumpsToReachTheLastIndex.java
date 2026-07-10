package leetcode.daily.y2026.m05;

//https://leetcode.com/problems/maximum-number-of-jumps-to-reach-the-last-index/?envType=daily-question&envId=2026-05-10
public class LC_20260510_2770_MaximumNumberOfJumpsToReachTheLastIndex {
    int n;
    public int maximumJumps(int[] nums, int target) {
        n = nums.length;
        int[] mem = new int[n];
        for(int i = 0; i < n; i++){
            mem[i] = -1;
        }
        int ans = maxSteps(0, nums, target, mem);
        return ans < 0 ? -1 : ans;
    }

    int maxSteps(int ind, int[] nums, int target, int[] mem){
        if(ind == n-1){
            return 0;
        }
        if(mem[ind] != -1)
            return mem[ind];

        int maxi = (int)-1e9;
        for(int j = ind+1; j < n; j++){
            if(Math.abs(nums[j] - nums[ind]) <= target){
                maxi = Math.max(maxi, 1 + maxSteps(j, nums, target, mem));
            }
        }
        mem[ind] = maxi;
        return maxi;
    }

    static void main() {
        int ans = new LC_20260510_2770_MaximumNumberOfJumpsToReachTheLastIndex().maximumJumps(
                new int[]{1,3,6,4,1,2}, 2);
        System.out.println(ans);
    }
}
