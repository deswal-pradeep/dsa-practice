package leetcode.daily.y2026.m04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/problems/maximize-the-distance-between-points-on-a-square/description/?envType=daily-question&envId=2026-04-25
public class LC_20260425_3464_MaximizeTheDistanceBetweenPointsOnASquare {
    static void main() {

    }

    public int maxDistance(int side, int[][] points, int k) {
        List<Long> arr = new ArrayList<>();
        for(int[] p : points){
            int x = p[0];
            int y = p[1];
            if(x == 0)
                arr.add((long)y);
            else if (y == side)
                arr.add((long)side + x);
            else if (x == side)
                arr.add((long)side * 3 - y);
            else
                arr.add((long)side * 4 - x);
        }
        Collections.sort(arr);
        int low = 1;
        int high = side;
        int ans = 0;
        while(low <= high){
            int mid = (low + high) / 2;
            if(check(arr, mid, k, side)){
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    boolean check(List<Long> arr, int limit, int k, int side){
        long perimeter = side * 4L;
        for(long start : arr){
            long end = start + perimeter - limit;
            long cur = start;
            for (int i = 0; i < k - 1; i++) {
                int idx = lowerBound(arr, cur + limit);
                if (idx == arr.size() || arr.get(idx) > end) {
                    cur = -1;
                    break;
                }
                cur = arr.get(idx);
            }
            if (cur >= 0) {
                return true;
            }
        }
        return false;
    }

    int lowerBound(List<Long> arr, long target){
        int left = 0;
        int right = arr.size();
        while(left < right){
            int mid = (left +  right) / 2;
            if(arr.get(mid) < target){
                left = mid+1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
