package leetcode.daily.y2026.april;

import java.util.*;

public class LC_20260421_1722_MinimumHammingDistanceAfterSwapOperations {
    static void main() {
        int[] source = {71,13,6,60,22,31};
        int[] target = {66,57,2,60,22,73};
        int[][] allowedSwaps = {{4,5},{0,4}};
        int ans = new LC_20260421_1722_MinimumHammingDistanceAfterSwapOperations()
                .minimumHammingDistance(source, target, allowedSwaps);
        System.out.println(ans);
    }
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        Map<Integer, List<Integer>> sourceSets = new HashMap<>();
        Map<Integer, List<Integer>> targetSets = new HashMap<>();
        DSU dsu = new DSU(n);
        for(int[] path : allowedSwaps){
            dsu.union(path[0], path[1]);
        }
        for(int i = 0; i < n; i++){
            int setIdentifier = dsu.findParent(i);
            List<Integer> sourceSet = sourceSets.computeIfAbsent(setIdentifier, k -> new ArrayList<>());
            List<Integer> targetSet = targetSets.computeIfAbsent(setIdentifier, k -> new ArrayList<>());
            sourceSet.add(source[i]);
            targetSet.add(target[i]);
        }

        int totalDistance = 0;
        for(Integer idx : sourceSets.keySet()){
            List<Integer> sourceList = sourceSets.get(idx);
            List<Integer> targetList = targetSets.get(idx);
            Collections.sort(sourceList);
            Collections.sort(targetList);
            totalDistance = totalDistance + getDistanceForSorted(sourceList, targetList);
        }
        return totalDistance;
    }

    private int getDistanceForSorted(List<Integer> l1, List<Integer> l2){
        int i = 0;
        int j = 0;
        int same = 0;
        while(i < l1.size() && j < l2.size()) {
            if (l1.get(i).equals(l2.get(j))) {
                i++;
                j++;
                same++;
            } else if (l1.get(i) < l2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return l1.size() - same;
    }

    private static class DSU {
        int[] parent;
        int[] rank;
        DSU(int n){
            parent = new int[n];
            for(int i = 0; i < n; i++){
                parent[i] = i;
            }
            rank = new int[n];
        }

        int findParent(int x){
            if(parent[x] == x)
                return x;
            int p = findParent(parent[x]);
            parent[x] = p;
            return p;
        }

        void union(int u, int v){
            int up = findParent(u);
            int vp = findParent(v);
            if(up == vp)
                return;
            if(rank[up] == rank[vp]){
                parent[vp] = up;
                rank[up]++;
            } else if (rank[vp] < rank[up]){
                parent[vp] = up;
            } else {
                parent[up] = vp;
            }
        }
    }
}
