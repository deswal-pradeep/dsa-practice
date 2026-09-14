package leetcode.daily.y2026.m09;

//https://leetcode.com/problems/rectangle-overlap/?envType=daily-question&envId=2026-09-14
public class LC_14_836_RectangleOverlap {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        //rect2(x1,y1) should be on strict left rect1.x2 and down rect1.y2
        //rect2(x2,y2) should be on strict right rect1.x1 and up rect1.y1
        return rec2[0] < rec1[2] && rec2[1] < rec1[3]
                && rec2[2] > rec1[0] && rec2[3] > rec1[1];
    }
}
