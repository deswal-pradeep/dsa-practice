package leetcode.daily.y2026.m07;

import java.util.Arrays;

public class LC_31_3016_MinimumNumberOfPushesToTypeWordII {
    public int minimumPushes(String word) {
        int[] f = new int[26];
        for(char c : word.toCharArray()){
            f[c-'a']++;
        }
        int ans = 0;
        int count = 0;
        Arrays.sort(f);
        for(int i = f.length-1; i >= 0; i--){
            if(f[i] == 0)
                break;
            if(f[i] > 0){
                ans = ans + ((count / 8 + 1) * f[i]);
                count++;
            } else {
                break;
            }
        }
        return ans;
    }

    static void main() {
        int ans = new LC_31_3016_MinimumNumberOfPushesToTypeWordII().minimumPushes("aabbccddeeffgghhiiiiii");
        System.out.println(ans);
    }
}
