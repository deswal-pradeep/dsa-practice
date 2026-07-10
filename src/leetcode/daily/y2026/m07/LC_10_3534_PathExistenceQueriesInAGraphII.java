package leetcode.daily.y2026.m07;

import java.util.*;

public class LC_10_3534_PathExistenceQueriesInAGraphII {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] nodes = new int[n][2];
        for(int i = 0; i < n; i++){
            nodes[i] = new int[]{i, nums[i]};
        }
        Arrays.sort(nodes, Comparator.comparingInt(a -> a[1]));
        int[] nodeIndexes = new int[n];
        int[] parent = new int[n];

        parent[nodes[0][0]] = nodes[0][0];
        for(int i = 1; i < n; i++){
            if(nodes[i][1] - nodes[i-1][1] <= maxDiff){
                parent[nodes[i][0]] = parent[nodes[i-1][0]];
            } else {
                parent[nodes[i][0]] = nodes[i][0];
            }
        }
        //will help in getting neighbours, then go both sides until maxDiff and search
        for(int i = 0; i < n; i++){
            nodeIndexes[nodes[i][0]] = i;
        }

        int[] reachable = new int[n];
        int left = 0;
        int right = 1;
        while(left < n){
            while(right < n && nodes[right][1] - nodes[left][1] <= maxDiff){
                right++;
            }
            reachable[left] = right - 1;
            left++;
        }
        int m  = log(n) + 1;
        int[][] d = new int[m][n];
        d[0] = reachable;
        for(int i = 1; i < m; i++){
            for(int j = 0; j < n; j++){
                d[i][j] = d[i-1][d[i-1][j]];
            }
        }


        //no we have neighbours together, no need to save the neighbours separately.
        int[] ans = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            int u = queries[i][0];
            int v = queries[i][1];
            ans[i] = bfs(nodeIndexes, nodes, u, v, n, parent, d);
        }
        return ans;
    }

    int log(int n){
        return 32 - Integer.numberOfLeadingZeros(n);
    }

    int bfs(int[] nodeIndexes, int[][] nodes, int u, int v, int n, int[] parent, int[][] d){
        if(parent[u] != parent[v])
            return -1;
        int left = nodeIndexes[u];
        int right = nodeIndexes[v];
        //swap so that left is always smaller
        if(left > right){
            int temp = left;
            left = right;
            right = temp;
        }
        int curr = left;
        int distance = 0;
        while(curr < right){
            int next = d[0][curr];
            int tempD = 1;
            for(int i = 1; i < d.length; i++){
                if(d[i][curr] < right){
                    next = d[i][curr];
                    tempD = 1 << i;
                } else {
                    break;
                }
            }
            curr = next;
            distance += tempD;
        }
        return curr >= right ? distance : -1;
    }

    static void main() {
        int[] ans = new LC_10_3534_PathExistenceQueriesInAGraphII()
                .pathExistenceQueries(8, new int[]{81,119,118,56,157,186,28,193}, 64, new int[][]{{7,6}});

        System.out.println(Arrays.toString(ans));

    }
}
