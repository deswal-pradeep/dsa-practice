package leetcode.daily.y2026.m09;

public class LC_09_3871_CountCommasInRangeII {
    public long countCommas(long n) {
        long ans = 0;
        ans += Math.max(0, n - 999L);
        ans += Math.max(0, n - 999999L);
        ans += Math.max(0, n - 999999999L);
        ans += Math.max(0, n - 999999999999L);
        ans += Math.max(0, n - 999999999999999L);
        return ans;
    }

    static void main() {
        long l = new LC_09_3871_CountCommasInRangeII().countCommas(1004590);
        System.out.println(l);
    }
}
