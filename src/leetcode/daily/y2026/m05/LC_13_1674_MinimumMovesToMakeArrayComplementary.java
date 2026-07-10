package leetcode.daily.y2026.m05;

//https://leetcode.com/problems/minimum-moves-to-make-array-complementary/?envType=daily-question&envId=2026-05-13
public class LC_13_1674_MinimumMovesToMakeArrayComplementary {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] diff = new int[2 * limit + 2];
        for(int i = 0; i < n/2; i++){
            int a  = Math.min(nums[i], nums[n-1-i]);
            int b  = Math.max(nums[i], nums[n-1-i]);
            diff[2] += 2;
            diff[a+1] -= 1;
            diff[a+b] -= 1;
            diff[a+b+1] += 1;
            diff[b+limit+1] += 1;
        }

        int ans = n;
        int currOps = 0;
        for(int c = 2; c <= 2 * limit; c++){
            currOps += diff[c];
            ans = Math.min(ans, currOps);
        }
        return ans;
    }

    static void main() {
        int ans = new LC_13_1674_MinimumMovesToMakeArrayComplementary()
                .minMoves(new int[]{1, 2, 4, 3}, 4);
        System.out.println(ans);
    }
}
