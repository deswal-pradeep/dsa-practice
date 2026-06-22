package leetcode.daily.y2026.jun;

public class LC_20260622_1189_MaximumNumberOfBalloons {
    public int maxNumberOfBalloons(String text) {
        int[] f = new int[26];
        for(char c : text.toCharArray()){
            f[c-'a']+=1;
        }
        int ans = Math.min(f['b'-'a'], f['a'-'a']);
        ans = Math.min(ans, f['l'-'a'] / 2);
        ans = Math.min(ans, f['o'-'a'] / 2);
        ans = Math.min(ans, f['n'-'a']);
        return ans;
    }

    static void main() {
        int ans = new LC_20260622_1189_MaximumNumberOfBalloons()
                .maxNumberOfBalloons("loonbalxballpoon");
        System.out.println(ans);
    }
}
