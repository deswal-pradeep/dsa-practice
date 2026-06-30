package leetcode.daily.y2026.jun;

//https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/?envType=daily-question&envId=2026-06-30
public class LC_20260630_1358_NumberOfSubstringsContainingAllThreeCharacters {
    public int numberOfSubstrings(String s) {
        long cnt = 0;
        int[] f = new int[3];
        int l = 0;
        int r = 0;
        int n = s.length();
        while(r < n){
            f[s.charAt(r) - 'a']++;
            while(isValid(f)){
                f[s.charAt(l) - 'a']--;
                l++;
            }
            //means here not valid, count substrings which end at r and not valid
            cnt = cnt + (r - l + 1);
            r++;
        }
        long total = (1l * n * (n+1))/2;
        return (int)(total - cnt);
    }

    boolean isValid(int[] f){
        return f[0] > 0 && f[1] > 0 && f[2] > 0;
    }

    static void main() {
        new LC_20260630_1358_NumberOfSubstringsContainingAllThreeCharacters().numberOfSubstrings("aaacb");
    }
}
