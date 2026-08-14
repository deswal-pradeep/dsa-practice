package leetcode.daily.y2026.m08;

//https://leetcode.com/problems/maximum-length-substring-with-two-occurrences/?envType=daily-question&envId=2026-08-14
public class LC_14_3090_MaximumLengthSubstringWithTwoOccurrences {
    public int maximumLengthSubstring(String s) {
        int n = s.length();
        int[] f = new int[26];
        int l = 0;
        int r = 0;
        int maxi = 0;
        while(r < n){
            char c = s.charAt(r);
            f[c - 'a']++;
            while(f[c-'a'] > 2){
                f[s.charAt(l)-'a']--;
                l++;
            }
            maxi = Math.max(maxi, r - l + 1);
            r++;
        }
        return maxi;
    }

    static void main() {
        int ans = new LC_14_3090_MaximumLengthSubstringWithTwoOccurrences()
                .maximumLengthSubstring("bcbbbcba");
        System.out.println(ans);
    }
}
