package leetcode.daily.y2026.m07;

import java.util.HashMap;
import java.util.Map;

public class LC_11_2685_CountTheNumberOfCompleteComponents {
    static class DSU {
        int[] arr;
        int [] parent;
        int[] rank;
        DSU(int n){
            this.parent = new int[n];
            this.rank = new int[n];
            for(int i = 0; i < n; i++){
                parent[i] = i;
            }
        }
        int findParent(int x){
            if(parent[x] == x)
                return parent[x];
            parent[x] = findParent(parent[x]);
            return parent[x];
        }
        void union(int u, int v){
            int up = findParent(u);
            int vp = findParent(v);
            if(rank[up] == rank[vp]){
                parent[vp] = up;
                rank[up]++;
            } else if (rank[up] > rank[vp]){
                parent[vp] = up;
            } else {
                parent[up] = vp;
            }
        }
    }
    public int countCompleteComponents(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        for(int[] edge : edges){
            dsu.union(edge[0], edge[1]);
        }
        Map<Integer, Integer> parentEdgeCount = new HashMap<>();
        for(int[] edge : edges){
            int parent = dsu.findParent(edge[0]);
            parentEdgeCount.put(parent, parentEdgeCount.getOrDefault(parent, 0)+1);
        }
        Map<Integer, Integer> parentNodeCount = new HashMap<>();
        for(int i = 0; i < n; i++){
            int parent = dsu.findParent(i);
            parentNodeCount.put(parent, parentNodeCount.getOrDefault(parent,0)+1);
        }
        int validComponents = 0;
        for(Integer parent : parentNodeCount.keySet()){
            int edgeCount = parentEdgeCount.getOrDefault(parent, 0);
            int nodeCount = parentNodeCount.get(parent);
            int needEdgeCount = nodeCount * (nodeCount - 1) / 2;
            validComponents = validComponents + (needEdgeCount ==  edgeCount ? 1 : 0);
        }
        return validComponents;
    }

    static void main() {
        int ans = new LC_11_2685_CountTheNumberOfCompleteComponents()
                .countCompleteComponents(6, new int[][]{{0, 1}, {0, 2}, {1, 2}, {3, 4}, {3,5}});
        System.out.println(ans);
    }
}
