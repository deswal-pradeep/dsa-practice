package leetcode.daily.y2026.m04;

public class LC_26_1559_DetectCyclesIn2dGrid {
    int m;
    int n;
    public boolean containsCycle(char[][] grid){
        m = grid.length;
        n = grid[0].length;
        DSU dsu = new DSU(m * n);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i+1 < m && grid[i][j] == grid[i+1][j]){
                    if(!dsu.union(i*n+j, (i+1)*n+j))
                        return true;
                }

                if(j+1 < n && grid[i][j] == grid[i][j+1]){
                    if(!dsu.union(i*n+j, i*n + (j+1)))
                        return true;
                }
            }
        }
        return false;
    }
    static class DSU {
        int[] parent, rank;
        DSU(int n){
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++) parent[i] = i;
        }

        int findParent(int x){
            if(parent[x] != x)
                parent[x] = findParent(parent[x]);
            return parent[x];
        }

        boolean union(int x, int y){
            int px = findParent(x);
            int py = findParent(y);
            if(px == py) return false;
            if(rank[px] == rank[py]){
                parent[py] = px;
                rank[px]++;
            } else if (rank[px] > rank[py]){
                parent[py] = px;
            } else {
                parent[px] = py;
            }
            return true;
        }
    }
    int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public boolean containsCycle1(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j]){
                    if(isCyclePresent(grid, visited, i, j, -1, -1))
                        return true;
                }
            }
        }
        return false;
    }

    boolean isCyclePresent(char[][] grid, boolean[][] visited,
                           int x, int y, int px, int py){
        visited[x][y] = true;
        for(int[] move : moves){
            int newX = x + move[0]; int newY = y + move[1];
            if(newX < 0 || newX >= m || newY < 0 || newY >= n) continue;
            if(grid[newX][newY] != grid[x][y]) continue;
            if(!visited[newX][newY]){
                if(isCyclePresent(grid, visited, newX, newY, x, y)) return true;
            } else if (!(newX == px && newY == py)){
                return true;
            }
        }
        return false;
    }

    static void main() {
        char[][] grid = {{'c','c','c','a'},{'c','d','c','c'},{'c','c','e','c'},{'f','c','c','c'}};
        new LC_26_1559_DetectCyclesIn2dGrid().containsCycle(grid);
    }
}
