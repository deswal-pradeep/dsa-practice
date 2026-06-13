package leetcode.daily.y2026.jun;

import java.util.Arrays;

//https://leetcode.com/problems/total-waviness-of-numbers-in-range-ii/?envType=daily-question&envId=2026-06-05
public class LC_20260605_3753_TotalWavinessOfNumbersInRangeII {
    //https://leetcode.com/problems/total-waviness-of-numbers-in-range-ii/?envType=daily-question&envId=2026-06-05
    String s;
    int n;
    long[][][][][] cntMem = new long[16][11][11][2][2];
    long[][][][][] sumMem = new long[16][11][11][2][2];

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1-1);
    }

    long solve(long num){
        if(num < 100)
            return 0;
        s = Long.toString(num);
        n = s.length();
        for(int a = 0; a < 16; a++){
            for(int b = 0; b < 11; b++){
                for(int c = 0; c < 11; c++){
                    for(int d = 0; d < 2; d++) {
                        Arrays.fill(cntMem[a][b][c][d], -1);
                        Arrays.fill(sumMem[a][b][c][d], -1);
                    }
                }
            }
        }
        long[] result = dfs(0, -1, -1, true, true);
        return result[1];
    }

    long[] dfs(int pos, int prev, int curr, boolean allUp, boolean suffixZero){
        if(pos == n){
            return new long[]{1L, 0L};
        }
        if(cntMem[pos][prev+1][curr+1][allUp ? 1 : 0][suffixZero ? 1 : 0] != -1){
            return new long[]{
                    cntMem[pos][prev+1][curr+1][allUp ? 1 : 0][suffixZero ? 1 : 0],
                    sumMem[pos][prev+1][curr+1][allUp ? 1 : 0][suffixZero ? 1 : 0]
            };
        }
        long cnt = 0;
        long sum = 0;
        int up = allUp ? s.charAt(pos) - '0' : 9;
        for(int digit = 0; digit <= up; digit++){
            boolean newAllUp = allUp && digit == up;
            boolean newSuffixZero = suffixZero && digit == 0;
            int newPrev = curr;
            int newCurr = newSuffixZero ? -1 : digit;
            long[] result = dfs(pos+1, newPrev, newCurr, newAllUp, newSuffixZero);
            long subCnt = result[0];
            long subSum = result[1];

            //if current value make a peak / valley
            //ignore the results from leading zero, for leading zero, it can't be considered a valid one
            //when newSuffixZero is zero, means this sum was contributed because of a leading zero
            if(!newSuffixZero
                    && curr >= 0 && prev >= 0
                    && ((curr > prev && curr > digit)
                    || (curr < prev && curr < digit))
            ){
                sum += subCnt;
            }
            cnt += subCnt;
            sum += subSum;
        }
        cntMem[pos][prev+1][curr+1][allUp ? 1 : 0][suffixZero ? 1 : 0] = cnt;
        sumMem[pos][prev+1][curr+1][allUp ? 1 : 0][suffixZero ? 1 : 0] = sum;
        return new long[]{cnt, sum};
    }

    static void main() {
        long l = new LC_20260605_3753_TotalWavinessOfNumbersInRangeII().totalWaviness(4848L, 10000L);
        //long l = new LC_20260605_3753_TotalWavinessOfNumbersInRangeII().totalWaviness(120L, 130L);
        System.out.println(l);
    }
}
