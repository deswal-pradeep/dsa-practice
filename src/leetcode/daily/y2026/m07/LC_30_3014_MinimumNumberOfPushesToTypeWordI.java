package leetcode.daily.y2026.m07;

import java.util.Arrays;

public class LC_30_3014_MinimumNumberOfPushesToTypeWordI {
    public int minimumPushes(String word) {
        int n = word.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += i / 8 + 1;
        }
        return ans;
    }

    static void main() {
        int ans = new LC_30_3014_MinimumNumberOfPushesToTypeWordI().minimumPushes("abcd");
        System.out.println(ans);
    }
}
