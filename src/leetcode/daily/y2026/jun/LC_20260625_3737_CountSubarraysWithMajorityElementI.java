package leetcode.daily.y2026.jun;

public class LC_20260625_3737_CountSubarraysWithMajorityElementI {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int[] f = new int[n];
        int freq = 0;
        for(int i = 0; i < n; i++){
            if(nums[i] == target)
                freq++;
            f[i] = freq;
        }

        int ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                int f1 = i > 0 ? f[i-1] : 0;
                int f2 = f[j];
                int length = j - i + 1;
                if(f2 - f1 > length/2)
                    ans++;
            }
        }
        return ans;
    }

    static void main() {
        int ans = new LC_20260625_3737_CountSubarraysWithMajorityElementI()
                .countMajoritySubarrays(new int[]{1, 7, 8, 2}, 5);
        System.out.println(ans);
    }
}
