package leetcode.daily.y2026.may;

public class LC_20260502_788_RotateDigits {
    public int rotatedDigits(int n) {
        int[] dp = new int[n + 1];
        int count = 0;

        for (int i = 0; i <= n; i++) {
            if (i < 10) {
                if (i == 0 || i == 1 || i == 8) {
                    dp[i] = 1;
                } else if (i == 2 || i == 5 || i == 6 || i == 9) {
                    dp[i] = 2;
                    count++;
                } else {
                    dp[i] = 0;
                }
            } else {
                int a = dp[i / 10];
                int b = dp[i % 10];

                if (a == 1 && b == 1) {
                    dp[i] = 1;
                } else if (a >= 1 && b >= 1) {
                    dp[i] = 2;
                    count++;
                } else {
                    dp[i] = 0;
                }
            }
        }

        return count;
    }

    public int rotatedDigits1(int n) {
        int ans = 0;
        int[] mem = new int[n+1];
        for(int i = 1; i <= n; i++){
            if(isValid(i)){
                ans++;
            }
        }
        return ans;
    }

    boolean isValid(int x){
        boolean contain347 = false;
        boolean atleastOneOf2569Exist = false;
        while(x > 0){
            int digit = x % 10;
            if(digit == 2 || digit == 5 || digit == 6 || digit == 9){
                atleastOneOf2569Exist = true;
            }
            if(digit == 3 || digit == 4 || digit == 7){
                contain347 = true;
            }
            x = x / 10;
        }
        return !contain347 && atleastOneOf2569Exist;
    }

    static void main() {
        int ans = new LC_20260502_788_RotateDigits().rotatedDigits(10);
        System.out.println(ans);
    }
}
