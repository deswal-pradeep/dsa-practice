package leetcode.daily.y2026.jun;

import java.util.LinkedList;
import java.util.List;

public class LC_20260611_3558_NumberOfWaysToAssignEdgeWeightsI {

    int MOD = 1000000007;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length+1;
        //find the deepest node using dfs
        int maxNode = 0;
        List<Integer>[] graph = new List[n+1];
        for(int i = 0; i <= n; i++){
            graph[i] = new LinkedList<>();
        }
        for(int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        boolean[] visited = new boolean[n+1];
        int maxDepth = dfs(1, 0, visited, graph);

        long ans = 0;
        /*for(int i = 1; i <= maxDepth; i+=2){
            ans += nCr(maxDepth, i);
            ans %= MOD;
        }*/
        ans = 1;
        for(int i = 1; i < maxDepth; i++){
            ans = ans << 1;
            ans = ans % MOD;
        }
        return (int)ans;
    }

    int dfs(int node, int depth, boolean[] visited, List<Integer>[] graph){
        int maxDepth = 0;
        List<Integer> edges =  graph[node];
        visited[node] = true;
        for(int edge : edges){
            if(!visited[edge])
                maxDepth = Math.max(maxDepth, dfs(edge, depth+1, visited, graph) + 1);
        }
        return maxDepth;
    }

    static void main() {
        //int[][] edges = new int[][]{{1,2},{1,3},{3,4},{3,5}};
        int[][] edges = new int[][]{{3,2},{2,1}};
        int ans = new LC_20260611_3558_NumberOfWaysToAssignEdgeWeightsI()
                .assignEdgeWeights(edges);
        System.out.println(ans);
    }
}
