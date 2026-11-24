package leetcode.daily.y2026.m11;

public class LC_24_3411_MaximumSubarrayWithEqualProducts {
    public int maxLength(int[] nums) {
        long gcdValue = 1;
        long lcmValue;
        long prodValue = 1;
        int maxLength = 2;
        for(int i = 0; i < nums.length; i++){
            gcdValue = nums[i];
            lcmValue = nums[i];
            prodValue = nums[i];
            for(int j = i+1; j < nums.length; j++){
                long val = nums[j];
                prodValue = prodValue * val;
                gcdValue = gcd(gcdValue, val);
                lcmValue = (lcmValue * val) / gcd(lcmValue, val);
                if(prodValue > lcmValue * gcdValue)
                    break;
                if(prodValue == lcmValue * gcdValue)
                    maxLength = Math.max(j-i+1, maxLength);
            }
        }
        return maxLength;
    }

    long gcd(long a, long b){
        while(b != 0){
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}
