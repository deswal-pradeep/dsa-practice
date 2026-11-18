package leetcode.daily.y2026.m11;

import java.util.Arrays;

public class LC_18_2914_MinimumNumberOfChangesToMakeBinaryStringBeautiful {
    public int minChanges(String s) {
        int minChangesRequired = 0;

        // Check pairs of characters (i, i+1) with step size 2
        for (int i = 0; i < s.length(); i += 2) {
            // If characters in current pair don't match,
            // we need one change to make them equal
            if (s.charAt(i) != s.charAt(i + 1)) {
                minChangesRequired++;
            }
        }

        return minChangesRequired;
    }
    //too many states, maximum can go to n^2 memory
    public int minChanges_dp(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int[][][] mem = new int[n][n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                Arrays.fill(mem[i][j], -1);
            }
        }
        return dp(arr, 0, 0, 0, mem);
    }

    int dp(char[] arr, int i, int length, int zeroCount, int[][][] mem){
        if(i == arr.length)
            return Math.min(length - zeroCount, zeroCount);
        if(mem[i][length][zeroCount] != -1)
            return mem[i][length][zeroCount];
        //continue same one, if we continue the same
        int changeNeeded = dp(arr, i+1, length+1, arr[i] == '0' ? zeroCount+1 : zeroCount, mem);
        if(i % 2 == 0){
            changeNeeded = Math.min(changeNeeded,
                    Math.min(length - zeroCount, zeroCount)
                            + dp(arr, i+1, 1, arr[i] == '0' ? 1 : 0, mem));
        }
        mem[i][length][zeroCount] = changeNeeded;
        return changeNeeded;
    }

    static void main() {
        int ans = new LC_18_2914_MinimumNumberOfChangesToMakeBinaryStringBeautiful()
                .minChanges("10001111101");
        System.out.println(ans);
    }
}
