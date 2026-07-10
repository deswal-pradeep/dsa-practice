package leetcode.daily.y2026.m06;

public class LC_20260618_1344_AngleBetweenHandsOfAClock {
    public double angleClock(int hour, int minutes) {
        double hourAngle = (hour + (minutes / 60.0)) * 30.0;
        hourAngle = hourAngle >= 360 ? hourAngle - 360 : hourAngle;
        double minuteAngle = (minutes * 6.0);
        double ans = hourAngle > minuteAngle
                ? hourAngle -  minuteAngle
                : minuteAngle - hourAngle;
        ans = Math.min(360 - ans, ans);
        return ans;
    }

    static void main() {
        double angle = new LC_20260618_1344_AngleBetweenHandsOfAClock()
                .angleClock(12, 30);
        System.out.println(angle);
    }
}
