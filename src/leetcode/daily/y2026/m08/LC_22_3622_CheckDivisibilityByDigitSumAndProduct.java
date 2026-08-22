package leetcode.daily.y2026.m08;

//https://leetcode.com/problems/check-divisibility-by-digit-sum-and-product/?envType=daily-question&envId=2026-08-22
public class LC_22_3622_CheckDivisibilityByDigitSumAndProduct {
    public boolean checkDivisibility(int n) {
        int digitSum = 0;
        int digitProduct = 1;
        int val = n;
        while(n > 0){
            int d = n % 10;
            digitSum += d;
            digitProduct *= d;
            n /= 10;
        }
        return val % (digitSum + digitProduct) == 0;
    }

    static void main() {
        boolean b = new LC_22_3622_CheckDivisibilityByDigitSumAndProduct().checkDivisibility(23);
        System.out.println(b);
    }
}
