package leetcode.daily.y2026.m09;

//https://leetcode.com/problems/smallest-stable-index-i/description/?envType=daily-question&envId=2026-09-04
public class LC_04_3903_SmallestStableIndexI {
    public int firstStableIndex(int[] nums, int k) {
        int[] suffixMin = new int[nums.length];
        suffixMin[nums.length-1] = nums[nums.length-1];
        for(int i = nums.length-2; i >= 0; i--){
            suffixMin[i] = Math.min(suffixMin[i+1], nums[i]);
        }
        int maxi = -(int)1e9;
        for(int i = 0; i < nums.length; i++){
            maxi = Math.max(maxi, nums[i]);
            if(maxi - suffixMin[i] <= k){
                return i;
            }
        }
        return -1;
    }

    static void main() {
        int ans = new LC_04_3903_SmallestStableIndexI().firstStableIndex(new int[]{5, 0, 1, 4}, 3);
        System.out.println(ans);
    }
}
