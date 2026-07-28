package tuf.dsa_practice.lc_hard;

public class LC_41_FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        boolean isOnePresent = false;
        for(int i = 0; i < n; i++){
            if(nums[i] == 1){
                isOnePresent = true;
            }
            if(nums[i] <= 0){
                nums[i] = 1;
            }
        }

        for(int i = 0; i < n; i++){
            int val = Math.abs(nums[i]);
            if(val == n){
                nums[0] = - Math.abs(nums[0]);
            } else if(val > 0 && val < n){
                nums[val] = - Math.abs(nums[val]);
            }
        }

        if(!isOnePresent)
            return 1;

        for(int i = 2; i < n; i++){
            if(nums[i] > 0){
                return i;
            }
        }

        if(nums[0] > 0)
            return n;

        return n+1;
    }

    static void main() {
        int ans = new LC_41_FirstMissingPositive().firstMissingPositive(new int[]{0});
        System.out.println(ans);
    }
}
