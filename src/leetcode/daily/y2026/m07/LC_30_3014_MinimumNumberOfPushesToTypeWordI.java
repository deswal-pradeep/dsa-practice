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

    public int minimumPushes_1(String word) {
        int[][] f = new int[26][2];
        for(char c : word.toCharArray()){
            f[c-'a'][0] = (int)c;
            f[c-'a'][1]++;
        }
        Arrays.sort(f, (a,b) -> {return b[1] - a[1];});
        int ans = 0;
        int count = 0;
        for(int i = 0; i < f.length; i++){
            if(f[i][1] > 0){
                ans = ans + ((count / 8 + 1) * f[i][1]);
                count++;
            }
        }
        return ans;
    }

    static void main() {
        int ans = new LC_30_3014_MinimumNumberOfPushesToTypeWordI().minimumPushes("abcd");
        System.out.println(ans);
    }
}
