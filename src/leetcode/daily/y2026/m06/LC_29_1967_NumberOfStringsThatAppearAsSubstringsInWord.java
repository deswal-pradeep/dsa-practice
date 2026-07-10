package leetcode.daily.y2026.m06;

public class LC_20260629_1967_NumberOfStringsThatAppearAsSubstringsInWord {
    public int numOfStrings(String[] patterns, String word) {
        int ans = 0;
        for(String p : patterns){
            if(word.contains(p))
                ans++;
        }
        return ans;
    }

    static void main() {
        int ans = new LC_20260629_1967_NumberOfStringsThatAppearAsSubstringsInWord()
                .numOfStrings(new String[]{"a", "abc", "bc", "d"}, "abc");
        System.out.println(ans);
    }
}
