package leetcode.daily.y2026.m09;

public class LC_08_3870_CountCommasInRange {
    public int countCommas(int n) {
        return (n >= 1000 ? n - 999 : 0);
    }
}
