package leetcode.daily.y2026.july;

import java.util.*;

//https://leetcode.com/problems/network-recovery-pathways/description/?envType=daily-question&envId=2026-07-03
public class LC_20260703_3620_NetworkRecoveryPathways {
    int n, m;
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        m = edges.length;
        n = online.length;
        int left = Integer.MAX_VALUE;
        int right = 0;

        List<List<int[]>> edgesMap = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edgesMap.add(new ArrayList<>());
        }

        int ans = -1;
        for (int[] e : edges) {
            if(online[e[0]] && online[e[1]]){
                edgesMap.get(e[0]).add(e);
                left = Math.min(left, e[2]);
                right = Math.max(right, e[2]);
            }
        }
        if (!check(edgesMap, left, k)) {
            return -1;
        }
        while(left <= right){
            int mid = (left + right)/2;
            boolean result = check(edgesMap, mid, k);
            if(result){
                ans = mid;
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return ans;
    }

    private boolean check(List<List<int[]>> g, int scoreAllowed, long k) {
        long[] dis = new long[n];
        Arrays.fill(dis, Long.MAX_VALUE);
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));

        dis[0] = 0;
        pq.offer(new long[] { 0, 0 });

        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            int u = (int) top[0];
            long d = top[1];
            if (d > k)
                return false;

            if (u == n - 1)
                return true;

            if (d > dis[u])
                continue;

            for (int[] edge : g.get(u)) {
                int v = edge[1];
                int w = edge[2];
                if (w < scoreAllowed) {
                    continue;
                }
                if (dis[v] > dis[u] + w) {
                    dis[v] = dis[u] + w;
                    pq.offer(new long[] {v, dis[v]});
                }
            }
        }
        return false;
    }
    public int findMaxPathScore_dfs(int[][] edges, boolean[] online, long k) {
        int m = edges.length;
        int n = online.length;
        Map<Integer, List<int[]>> edgesMap = new HashMap<>();
        for (int[] e : edges) {
            edgesMap.computeIfAbsent(e[0], key -> new LinkedList<>());
            edgesMap.get(e[0]).add(e);
        }
        Deque<long[]> stack = new ArrayDeque<>();
        stack.push(new long[]{0, 0, Integer.MAX_VALUE});
        long maxWeight = -1;

        while (!stack.isEmpty()) {
            long[] curr = stack.pop();
            int u = (int) curr[0];
            long totalCost = curr[1];
            long minCost = curr[2];

            if (u == n - 1) {
                maxWeight = Math.max(maxWeight, minCost);
            }
            for (int[] edge : edgesMap.getOrDefault(u, Collections.emptyList())) {
                int v = edge[1];
                int cost = edge[2];
                if (online[v]) {
                    long newMinCost = Math.min(minCost, cost);
                    long newTotalCost = totalCost + cost;
                    if (newMinCost > maxWeight && newTotalCost <= k) {
                        stack.push(new long[]{v, newTotalCost, newMinCost});
                    }
                }
            }
        }
        return (int) maxWeight;
    }

    public int findMaxPathScore_bfs(int[][] edges, boolean[] online, long k) {
        int m = edges.length;
        int n = online.length;
        Map<Integer, List<int[]>> edgesMap = new HashMap<>();
        for(int[] e : edges){
            edgesMap.computeIfAbsent(e[0], key -> new LinkedList<>());
            edgesMap.get(e[0]).add(e);
        }
        Queue<long[]> queue = new LinkedList<>();
        queue.add(new long[]{0, 0, Integer.MAX_VALUE});
        long maxWeight = 0;
        while(!queue.isEmpty()){
            long[] curr = queue.poll();
            int u = (int)curr[0];
            long totalCost = curr[1];
            long minCost = curr[2];
            if(u == n-1){
                //reached
                maxWeight = Math.max(maxWeight, minCost);
            }
            for(int[] edge : edgesMap.getOrDefault(u, Collections.emptyList())){
                int v = edge[1];
                int cost = edge[2];
                if(online[v]){
                    long newMinCost = Math.min(minCost, cost);
                    long newTotalCost = totalCost + cost;
                    if(newMinCost > maxWeight && newTotalCost <= k){
                        queue.add(new long[]{v, newTotalCost, newMinCost});
                    }
                }
            }
        }
        return (int)maxWeight;
    }

    static void main() {
        int[][] edges = new int[][]{{0,1,7},{1,4,5},{0,2,6},{2,3,6},{3,4,2},{2,4,6}};
        boolean[] online = new boolean[]{true,true,true,false,true};
        int maxPathScore = new LC_20260703_3620_NetworkRecoveryPathways().findMaxPathScore(edges, online, 12);
        System.out.println(maxPathScore);
    }

    class Solution {
        
        public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
            int n = online.length;
            List<int[]>[] g = new ArrayList[n];
            int[] deg = new int[n];
            for (int i = 0; i < n; i++) {
                g[i] = new ArrayList<>();
            }

            int l = Integer.MAX_VALUE,
                    r = 0;
            for (int[] edge : edges) {
                int u = edge[0],
                        v = edge[1],
                        w = edge[2];
                if (!online[u] || !online[v]) {
                    continue;
                }
                g[u].add(new int[] { v, w });
                deg[v]++;
                l = Math.min(l, w);
                r = Math.max(r, w);
            }

            // Delete unreachable nodes
            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i < n; i++) {
                if (deg[i] == 0) {
                    q.offer(i);
                }
            }
            while (!q.isEmpty()) {
                int u = q.poll();
                for (int[] edge : g[u]) {
                    int v = edge[0];
                    if (--deg[v] == 0 && v != 0) {
                        q.offer(v);
                    }
                }
            }

            if (!check(l, k, g, deg, n)) {
                return -1;
            }

            while (l <= r) {
                int mid = (l + r) >> 1;
                if (check(mid, k, g, deg, n)) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return r;
        }

        private boolean check(int mid, long k, List<int[]>[] g, int[] deg, int n) {
            long[] dp = new long[n];
            Arrays.fill(dp, Long.MAX_VALUE / 2);
            int[] cdeg = deg.clone();
            dp[0] = 0;

            Queue<Integer> q = new LinkedList<>();
            q.offer(0);

            while (!q.isEmpty()) {
                int u = q.poll();
                if (u == n - 1) {
                    return dp[u] <= k;
                }

                for (int[] edge : g[u]) {
                    int v = edge[0],
                            w = edge[1];
                    if (w >= mid) {
                        dp[v] = Math.min(dp[v], dp[u] + w);
                    }
                    if (--cdeg[v] == 0) {
                        q.offer(v);
                    }
                }
            }
            return false;
        }
    }

    class Solution_1 {

        private List<int[]>[] g;
        private long[] memo;
        private int n;

        public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
            n = online.length;
            g = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                g[i] = new ArrayList<>();
            }

            int l = Integer.MAX_VALUE;
            int r = 0;
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];
                if (!online[u] || !online[v]) {
                    continue;
                }
                g[u].add(new int[] { v, w });
                l = Math.min(l, w);
                r = Math.max(r, w);
            }

            if (!check(l, k)) {
                return -1;
            }

            while (l <= r) {
                int mid = (l + r) >> 1;
                if (check(mid, k)) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return r;
        }

        private boolean check(int mid, long k) {
            memo = new long[n];
            Arrays.fill(memo, -1);
            return dfs(0, mid) <= k;
        }

        private long dfs(int u, int mid) {
            if (u == n - 1) {
                return 0;
            }
            if (memo[u] != -1) {
                return memo[u];
            }

            long res = Long.MAX_VALUE / 2;
            for (int[] edge : g[u]) {
                int v = edge[0];
                int w = edge[1];
                if (w >= mid) {
                    res = Math.min(res, dfs(v, mid) + w);
                }
            }
            memo[u] = res;
            return res;
        }
    }
}
