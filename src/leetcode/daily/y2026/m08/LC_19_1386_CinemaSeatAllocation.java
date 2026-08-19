package leetcode.daily.y2026.m08;

import java.util.Arrays;

//https://leetcode.com/problems/cinema-seat-allocation/?envType=daily-question&envId=2026-08-19
public class LC_19_1386_CinemaSeatAllocation {
    public int maxNumberOfFamilies_negate(int n, int[][] A) {
        Arrays.sort(A, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int res = n << 1;
        int m = 0;

        for (int i = 0; i < A.length; i++) {
            m |= (1 << A[i][1]);
            if (i == A.length - 1 || A[i][0] != A[i + 1][0]) {
                int c = ((m & 0x3C) == 0 ? 1 : 0) + ((m & 0x3C0) == 0 ? 1 : 0);
                if (c == 0 && (m & 0xF0) == 0)
                    c = 1;
                res -= 2 - c;
                m = 0;
            }
        }

        return res;
    }

    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Arrays.sort(reservedSeats, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int ans = 0;
        int prevRow = 0;
        for(int i = 0; i < reservedSeats.length;){
            int currRow = reservedSeats[i][0];
            int fullEmptyRowsCount = currRow - prevRow - 1;
            ans += (fullEmptyRowsCount * 2);
            int[] inel = new int[3];
            while(i < reservedSeats.length
                    && reservedSeats[i][0] == currRow){
                int currSeat = reservedSeats[i][1];
                if(currSeat >= 2 && currSeat <= 5)
                    inel[0] = 1;
                if(currSeat >= 4 && currSeat <= 7)
                    inel[1] = 1;
                if(currSeat >= 6 && currSeat <= 9)
                    inel[2] = 1;
                i++;
            }
            if(inel[0] == 0){
                ans += 1;
            }
            if(inel[2] == 0)
                ans += 1;
            if(inel[1] == 0 && inel[0] == 1 && inel[2] == 1)
                ans += 1;
            prevRow = currRow;
        }
        int lastFullEmptyCount = n - prevRow;
        if(lastFullEmptyCount > 0)
            ans += (lastFullEmptyCount * 2);
        return ans;
    }

    static void main() {
        //int[][] alloc = new int[][]{{1,2},{1,3},{1,8},{2,6},{3,1},{3,10}};
        int[][] alloc = new int[][]{{2,3}};
        int ans = new LC_19_1386_CinemaSeatAllocation().maxNumberOfFamilies(3, alloc);
        System.out.println(ans);
    }
}
