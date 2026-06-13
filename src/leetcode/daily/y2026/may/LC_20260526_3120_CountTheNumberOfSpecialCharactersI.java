package leetcode.daily.y2026.may;

//https://leetcode.com/problems/count-the-number-of-special-characters-i/description/?envType=daily-question&envId=2026-05-26
public class LC_20260526_3120_CountTheNumberOfSpecialCharactersI {
    public int numberOfSpecialChars(String word) {
        char[] arr = word.toCharArray();
        int[] sMap = new int[26];
        int[] lMap = new int[26];
        for(int i = 0; i < arr.length; i++){
            char c = arr[i];
            if(c >= 'A' && c <= 'Z'){
                lMap[c-'A']++;
            } else {
                sMap[c-'a']++;
            }
        }

        int ans = 0;
        for(int i = 0; i < 26; i++){
            ans += Math.min(sMap[i], lMap[i]) > 0 ? 1 : 0;
        }
        return ans;
    }

    static void main() {
        int ans = new LC_20260526_3120_CountTheNumberOfSpecialCharactersI()
                .numberOfSpecialChars("aaAAbcBC");
        System.out.println(ans);
    }
}
