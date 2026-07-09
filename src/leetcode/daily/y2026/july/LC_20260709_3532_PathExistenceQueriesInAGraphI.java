package leetcode.daily.y2026.july;

import java.util.Arrays;

//https://leetcode.com/problems/path-existence-queries-in-a-graph-i/description/?envType=daily-question&envId=2026-07-09
public class LC_20260709_3532_PathExistenceQueriesInAGraphI {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int count = 0;
        int[] dest = new int[n];

        for(int i = 0; i < n; i++){
            if(i == n-1 || nums[i+1] - nums[i] > maxDiff){
                while(count >= 0){
                    dest[i-count] = i;
                    count --;
                }
                count = 0;
            } else {
                count++;

            }
        }
        boolean[] ans = new boolean[queries.length];
        for(int i = 0; i < queries.length; i++){
            int u = queries[i][0];
            int v = queries[i][1];
            if(u > v){
                int temp = u;
                u = v;
                v = temp;
            }
            ans[i] = dest[u] >= v ? true : false;
        }
        return ans;
    }

    static void main() {
        boolean[] booleans = new LC_20260709_3532_PathExistenceQueriesInAGraphI()
                .pathExistenceQueries(4, new int[]{2, 5, 6, 8},
                        2, new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}});
        System.out.println(Arrays.toString(booleans));
    }
}
