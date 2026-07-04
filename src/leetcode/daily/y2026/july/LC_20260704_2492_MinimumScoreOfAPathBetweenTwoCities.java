package leetcode.daily.y2026.july;

import java.util.*;

public class LC_20260704_2492_MinimumScoreOfAPathBetweenTwoCities {
    public int minScore(int n, int[][] roads) {
        List<int[]>[] edges = new ArrayList[n+1];
        for(int i = 0; i <= n; i++)
            edges[i] = new ArrayList<>();

        int left = Integer.MAX_VALUE;
        int right = -1;
        for(int[] road : roads){
            edges[road[0]].add(new int[]{road[1], road[2]});
            edges[road[1]].add(new int[]{road[0], road[2]});
            left = Math.min(left, road[2]);
            right = Math.max(right, road[2]);
        }

        int ans = bfs(edges, n);
        return ans;
    }

    int bfs(List<int[]>[] edges, int n){
        int[] dis = new int[n+1];
        boolean[] visited = new boolean[n+1];
        int minPath = Integer.MAX_VALUE;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, Integer.MAX_VALUE});
        dis[1] = Integer.MAX_VALUE;
        visited[1] = true;
        boolean reachable = false;
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int u = curr[0];
            int d = dis[u];
            if(u == n){
                reachable = true;
                minPath = Math.min(d, minPath);
            }
            for(int[] edge : edges[u]){
                int v = edge[0];
                minPath = Math.min(minPath, edge[1]);
                dis[v] = Math.min(edge[1], d);
                if(!visited[v]){
                    visited[v] = true;
                    queue.offer(new int[]{v, Math.min(edge[1], d)});
                }
            }
        }
        return reachable ? minPath : -1;
    }

    static void main() {
        /*int[][] roads = new int[][]{{18,20,9207},{14,12,1024},{11,9,3056},
                {8,19,416},{3,18,5898},{17,3,6779},{13,15,3539},{15,11,1451},
                {19,2,3805},{9,8,2238},{1,16,618},{16,14,55},{17,7,6903},{12,13,1559},
                {2,17,3693}};*/
        int[][] roads = new int[][]{{1,2,2},{1,3,4},{3,4,7}};
        int ans = new LC_20260704_2492_MinimumScoreOfAPathBetweenTwoCities().minScore(4, roads);
        System.out.println(ans);
    }
}
