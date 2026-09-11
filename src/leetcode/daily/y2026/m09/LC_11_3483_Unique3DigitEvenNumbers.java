package leetcode.daily.y2026.m09;

//https://leetcode.com/problems/unique-3-digit-even-numbers/editorial/?envType=daily-question&envId=2026-09-11
public class LC_11_3483_Unique3DigitEvenNumbers {
    public int totalNumbers(int[] digits) {
        int ans = 0;
        int[] digitsFreq = new int[10];
        for(int i = 0; i < digits.length; i++)
            digitsFreq[digits[i]]++;

        for(int i = 100; i < 1000; i+=2){
            if(can(digitsFreq, i))
                ans++;
        }
        return ans;
    }

    boolean can(int[] digitsFreq, int val){
        int[] f = new int[10];
        while(val > 0){
            f[val%10]++;
            val = val / 10;
        }
        for(int i = 0; i < f.length; i++){
            if(digitsFreq[i] < f[i])
                return false;
        }
        return true;
    }

    public int totalNumbers_editorial(int[] digits) {
        int n = digits.length;
        boolean[] vis = new boolean[1000];
        int ans = 0;

        for (int i = 0; i < n; ++i) {
            if (digits[i] == 0) {
                continue;
            }
            for (int j = 0; j < n; ++j) {
                if (j == i) {
                    continue;
                }
                for (int k = 0; k < n; ++k) {
                    if (k == i || k == j || digits[k] % 2 != 0) {
                        continue;
                    }
                    int x = digits[i] * 100 + digits[j] * 10 + digits[k];
                    if (!vis[x]) {
                        vis[x] = true;
                        ++ans;
                    }
                }
            }
        }

        return ans;
    }
}
