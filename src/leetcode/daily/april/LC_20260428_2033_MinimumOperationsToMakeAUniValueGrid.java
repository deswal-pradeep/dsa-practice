package leetcode.daily.april;

import java.util.Arrays;

//https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/description/?envType=daily-question&envId=2026-04-28
public class LC_20260428_2033_MinimumOperationsToMakeAUniValueGrid {
    int m, n;
    public int minOperations(int[][] grid, int x){
        m = grid.length;
        n = grid[0].length;
        int len = m * n;
        int[] arr = new int[len];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                arr[i * n + j] = grid[i][j];
                if(Math.abs(grid[i][j] - grid[0][0]) % x != 0)
                    return -1;
            }
        }
        //sorting is needed for prefix-suffix approach to work
        //because we dont know how many numbers are less than or greater than currentValue
        //so prefixSum and suffixSum will not help
        Arrays.sort(arr);
        int[] pSum = new int[len];
        int[] sSum = new int[len];

        for(int i = 1; i < len; i++){
            pSum[i] = pSum[i-1] + arr[i-1];
        }
        for(int i = len-2; i >= 0; i--){
            sSum[i] = sSum[i+1] + arr[i+1];
        }

        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < len; i++){
            int leftSteps = (arr[i] * i - pSum[i])/x;
            int rightSteps = (sSum[i] - (arr[i] * (len-i-1)))/x;
            ans = Math.min(ans, leftSteps + rightSteps);
        }
        return ans;
    }
    public int minOperations_2(int[][] grid, int x) {
        m = grid.length;
        n = grid[0].length;
        int[] arr = new int[m * n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                arr[i * n + j] = grid[i][j];
            }
        }
        Arrays.sort(arr);
        for(int i = 1; i < arr.length; i++){
            if((arr[i] - arr[i-1]) % x != 0)
                return -1;
        }
        int ans = 0;
        int selected = arr[arr.length/2];
        for(int i = 0; i < arr.length; i++){

            ans = ans + Math.abs(arr[i] - selected) / x;
        }
        return ans;
    }
    //approach, sort and try all
    public int minOperations_1(int[][] grid, int x) {
        m = grid.length;
        n = grid[0].length;
        int[] arr = new int[m * n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                arr[i * n + j] = grid[i][j];
            }
        }
        Arrays.sort(arr);
        int steps = 0;
        int target = arr[0];
        for(int i = 1; i < arr.length; i++){
            int diff = arr[i] - target;
            if(diff % x != 0){
                return -1;
            }
            steps = steps + (arr[i] - target) / x;
        }

        int minSteps = steps;
        int count = arr.length;
        for(int i = 1; i < arr.length; i++){
            count = count - 2;
            int delta = (arr[i] - arr[i-1])/x;
            steps = steps - (count * delta);
            minSteps = Math.min(minSteps, steps);
        }
        return minSteps;
    }

    static void main() {
        int[][] grid = {{2, 4}, {6, 8}};
        int ans = new LC_20260428_2033_MinimumOperationsToMakeAUniValueGrid().minOperations(grid, 2);
        System.out.println(ans);
    }
}
