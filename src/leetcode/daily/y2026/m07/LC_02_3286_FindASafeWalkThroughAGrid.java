package leetcode.daily.y2026.m07;

import java.util.*;

public class LC_02_3286_FindASafeWalkThroughAGrid {
    int m, n;
    int[][] moves = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        m = grid.size();
        n = grid.get(0).size();
        int pathDistance = dijkstra(grid);
        int pathDistance1 = zeroOneBfs(grid);
        return pathDistance < health;
    }

    private int dijkstra(List<List<Integer>> grid) {
        int m = grid.size();
        int n = grid.get(0).size();

        int[][] dist = new int[m][n];
        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        dist[0][0] = grid.get(0).get(0);
        pq.offer(new int[]{0, 0, dist[0][0]});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int x = curr[0];
            int y = curr[1];
            int d = curr[2];

            if (d != dist[x][y])
                continue;
            if (x == m - 1 && y == n - 1) {
                return d;
            }
            for (int[] dir : moves) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }
                int newDist = d + grid.get(nx).get(ny);
                if (newDist < dist[nx][ny]) {
                    dist[nx][ny] = newDist;
                    pq.offer(new int[]{nx, ny, newDist});
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    boolean isValid(int x, int y){
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    private int zeroOneBfs(List<List<Integer>> grid) {
        int m = grid.size();
        int n = grid.get(0).size();

        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Deque<int[]> dq = new ArrayDeque<>();

        dist[0][0] = grid.get(0).get(0);
        dq.offerFirst(new int[]{0, 0});

        while (!dq.isEmpty()) {
            int[] curr = dq.pollFirst();

            int x = curr[0];
            int y = curr[1];

            if (x == m - 1 && y == n - 1) {
                return dist[x][y];
            }

            for (int[] dir : moves) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }

                int w = grid.get(nx).get(ny);
                int newDist = dist[x][y] + w;

                if (newDist < dist[nx][ny]) {
                    dist[nx][ny] = newDist;

                    if (w == 0) {
                        dq.offerFirst(new int[]{nx, ny});
                    } else {
                        dq.offerLast(new int[]{nx, ny});
                    }
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    static void main() {
        List<List<Integer>> grid = new ArrayList<>();
        grid.add(Arrays.asList(0,1,1,0,0,0));
        grid.add(Arrays.asList(1,0,1,0,0,0));
        grid.add(Arrays.asList(0,1,1,1,0,1));
        grid.add(Arrays.asList(0,0,1,0,1,0));
        boolean safeWalk = new LC_02_3286_FindASafeWalkThroughAGrid().findSafeWalk(grid, 3);
        System.out.println(safeWalk);
    }
}
