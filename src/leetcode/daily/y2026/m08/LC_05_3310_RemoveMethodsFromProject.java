package leetcode.daily.y2026.m08;

import java.util.*;

public class LC_05_3310_RemoveMethodsFromProject {
    static class DSU {
        int[] parent;
        int[] rank;
        DSU(int n){
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++)
                parent[i] = i;
        }
        int findParent(int x){
            if(parent[x] == x)
                return x;
            parent[x] = findParent(parent[x]);
            return parent[x];
        }
        void union(int x, int y){
            int px = findParent(x);
            int py = findParent(y);
            if(px == py){
                parent[py] = px;
                rank[px]++;
            } else if (px > py){
                parent[py] = px;
            } else {
                parent[px] = py;
            }
        }
    }

    public List<Integer> remainingMethods_dsu(int n, int k, int[][] invocations) {

        DSU dsu = new DSU(n);

        // Adjacency list using array
        List<Integer>[] nbrs = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            nbrs[i] = new ArrayList<>();
        }

        for (int[] inv : invocations) {
            dsu.union(inv[0], inv[1]);
            nbrs[inv[0]].add(inv[1]);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        Set<Integer> infected = new HashSet<>();

        queue.offer(k);
        visited[k] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            infected.add(curr);

            for (int nbr : nbrs[curr]) {
                if (!visited[nbr]) {
                    visited[nbr] = true;
                    queue.offer(nbr);
                }
            }
        }

        int parentK = dsu.findParent(k);

        Set<Integer> fullGroup = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (dsu.findParent(i) == parentK) {
                fullGroup.add(i);
            }
        }

        boolean groupCanBeRemoved = fullGroup.size() == infected.size();

        List<Integer> ans = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            if (groupCanBeRemoved && fullGroup.contains(i)) {
                continue;
            }
            ans.add(i);
        }

        return ans;
    }

    public List<Integer> remainingMethods_withoutDSU(int n, int k, int[][] invocations) {
        List<Integer>[] edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int[] inv : invocations) {
            edges[inv[0]].add(inv[1]);
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        Set<Integer> infected = new HashSet<>();
        queue.offer(k);
        visited[k] = true;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            infected.add(curr);

            for (int nbr : edges[curr]) {
                if (!visited[nbr]) {
                    visited[nbr] = true;
                    queue.offer(nbr);
                }
            }
        }
        //is there inv which is not visited but points to visited ones
        boolean inwardEdgeToVisited = false;
        for(int[] inv : invocations){
            if(!visited[inv[0]] && visited[inv[1]]){
                inwardEdgeToVisited = true;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(!inwardEdgeToVisited && visited[i])
                continue;
            ans.add(i);
        }
        return ans;
    }

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        int[] inDegree = new int[n];

        for (int[] inv : invocations) {
            edges[inv[0]].add(inv[1]);
            inDegree[inv[1]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(k);
        boolean[] suspicious = new boolean[n];
        suspicious[k] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : edges[u]) {
                inDegree[v]--;

                if (!suspicious[v]) {
                    queue.offer(v);
                    suspicious[v] = true;
                }
            }
        }

        boolean canRemoveAll = true;
        List<Integer> remaining = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (suspicious[i] && inDegree[i] > 0) {
                canRemoveAll = false;
                break;
            } else if (!suspicious[i]) {
                remaining.add(i);
            }
        }

        if (!canRemoveAll) {
            List<Integer> allNodes = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                allNodes.add(i);
            }
            return allNodes;
        }

        return remaining;
    }

    static void main() {
        int[][] inv = new int[][]{{1,2}, {0,2}, {0,1}, {3,4}};
        List<Integer> integers = new LC_05_3310_RemoveMethodsFromProject().remainingMethods(5, 0, inv);
        System.out.println(integers);
    }
}
