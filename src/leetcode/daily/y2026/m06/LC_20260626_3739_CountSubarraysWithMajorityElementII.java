package leetcode.daily.y2026.m06;

public class LC_20260626_3739_CountSubarraysWithMajorityElementII {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        for(int i = 0; i < n; i++){
            nums[i] = nums[i] == target ? 1 : -1;
        }

        int[] pref = new int[nums.length+1];
        for(int i = 1; i < pref.length; i++){
            pref[i] = pref[i-1] + nums[i-1];
        }

        long ans = 0;
        int[] pre = new int[2 * n + 2];
        int[] f = new int[2 * n + 2];
        int shift = n+1;
        f[shift] = 1;
        for(int i = 1; i < pref.length; i++){
            ans += pre[shift + pref[i] - 1] + f[shift + pref[i]-1];
            f[shift + pref[i]] = f[shift + pref[i]]+1;
            if(pref[i] > pref[i-1]){
                pre[shift + pref[i]] = pre[shift + pref[i-1]] + f[shift + pref[i-1]];
            }
        }
        return ans;
    }

    static void main() {
        long ans = new LC_20260626_3739_CountSubarraysWithMajorityElementII()
                .countMajoritySubarrays(new int[]{1,1,1,1}, 1);
        System.out.println(ans);
    }
}
