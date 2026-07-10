package leetcode.daily.y2026.m05;

public class LC_01_396_RotateFunction {
    int n;
    public int maxRotateFunction(int[] nums) {
        int f = 0;
        int n = nums.length;
        int numSum = 0;
        for (int i = 0; i < n; i++) {
            f += i * nums[i];
            numSum += nums[i];
        }
        int res = f;
        for (int i = n - 1; i > 0; i--) {
            f += numSum - n * nums[i];
            res = Math.max(res, f);
        }
        return res;
    }

    public int maxRotateFunction1(int[] nums) {
        n = nums.length;
        long[] sSum = new long[n];
        long[] pSum = new long[n];
        sSum[n-1] = nums[n-1];
        for(int i = n-2; i >=0; i--){
            sSum[i] = sSum[i+1] + nums[i];
        }
        pSum[0] = nums[0];
        for(int i = 1; i < n; i++){
            pSum[i] = pSum[i-1] + nums[i];
        }
        long fValue = 0;
        for(int i = 0; i < n; i++){
            fValue = fValue + (i * nums[i]);
        }
        long maxi = fValue;
        for(int i = 1; i < n; i++){
            fValue = fValue + ((n-1) * nums[i-1]);
            fValue = fValue - sSum[i];
            if(i-2 >= 0)
                fValue = fValue - pSum[i-2];
            maxi = Math.max(maxi, fValue);
        }
        return (int)maxi;
    }

    static void main() {
        int ans = new LC_01_396_RotateFunction().maxRotateFunction(new int[]{-8, 5, -10, -5, -7, -2, -7, 0});
        System.out.println(ans);
    }
}
