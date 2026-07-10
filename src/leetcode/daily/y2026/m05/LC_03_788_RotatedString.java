package leetcode.daily.y2026.m05;

//https://leetcode.com/problems/rotated-digits/description/
public class LC_03_788_RotatedString {
    static void main() {
        boolean ans = new LC_03_788_RotatedString().rotateString("abcde", "bcdea");
        System.out.println(ans);
    }

    public boolean rotateString(String s, String goal) {
        String s1 = s + s;
        return s.length() == goal.length() && s1.contains(goal);
    }
}
