package leetcode.daily.y2026.may;

import java.util.Arrays;

//https://leetcode.com/problems/count-the-number-of-special-characters-ii/description/?envType=daily-question&envId=2026-05-27
public class LC_20260527_3121_CountTheNumberOfSpecialCharactersII {
    public int numberOfSpecialChars(String word) {
        char[] arr = word.toCharArray();
        int[] sMap = new int[26];
        int[] lMap = new int[26];
        Arrays.fill(lMap, -1);
        Arrays.fill(sMap, -1);

        for(int i = 0; i < arr.length; i++){
            char c = arr[i];
            if(c >= 'a' && c <= 'z'){
                sMap[c-'a'] = i;
            } else {
                if(lMap[c-'A'] == -1)
                    lMap[c-'A'] = i;
            }
        }

        int ans = 0;
        for(int i = 0; i < 26; i++){
            ans += lMap[i] >= 0 && sMap[i] >= 0 && lMap[i] > sMap[i] ? 1 : 0;
        }
        return ans;
    }

    static void main() {
        int ans = new LC_20260527_3121_CountTheNumberOfSpecialCharactersII()
                .numberOfSpecialChars("AbBCab");
        System.out.println(ans);
    }
}
