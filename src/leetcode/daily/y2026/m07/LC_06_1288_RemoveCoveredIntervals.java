package leetcode.daily.y2026.m07;

import java.util.Arrays;

public class LC_06_1288_RemoveCoveredIntervals {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            int compare1 = Integer.compare(a[0], b[0]);
            if(compare1 == 0)
                return Integer.compare(a[1], b[1]) * -1;
            return compare1;
        });

        int maxEndSeenSoFar = 0;
        int count = 0;

        for (int[] interval: intervals) {
            if(interval[1] > maxEndSeenSoFar) {
                maxEndSeenSoFar = interval[1];
                count++;
            }
        }
        return count;
    }
    public int removeCoveredIntervals_1(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            int compare1 = Integer.compare(a[0], b[0]);
            if(compare1 == 0)
                return Integer.compare(a[1], b[1]) * -1;
            return compare1;
        });
        boolean[] covered = new boolean[intervals.length];
        for(int i = 0; i < intervals.length; i++){
            for(int j = i+1; j < intervals.length; j++){
                int[] first = intervals[i];
                int[] second = intervals[j];
                if(second[0] > first[1]){
                    break;
                }
                if(first[0] <= second[0] && first[1] >= second[1])
                    covered[j] = true;
            }
        }
        int count = 0;
        for(boolean b : covered){
            if(!b) count++;
        }
        return count;
    }

    static void main() {
        int ans = new LC_06_1288_RemoveCoveredIntervals()
                .removeCoveredIntervals(new int[][]{{1,2},{1,4},{3,4}});
        System.out.println(ans);
    }
}
