package leetcode.daily.y2026.m05;

public class LC_20260529_3300_MinimumElementAfterReplacementWithDigitSum {
    public int minElement(int[] nums) {
        int mini = (int)1e9;
        for(int num : nums){
            mini = Math.min(mini, digitSum(num));
        }
        return mini;
    }

    int digitSum(int num){
        int dSum = 0;
        while(num > 0){
            dSum += (num % 10);
            num /= 10;
        }
        return dSum;
    }

    static void main() {
        int ans = new LC_20260529_3300_MinimumElementAfterReplacementWithDigitSum()
                .minElement(new int[]{10, 12, 13, 14});
        System.out.println(ans);
    }
}
