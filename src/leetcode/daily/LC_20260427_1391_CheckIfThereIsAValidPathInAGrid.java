package leetcode.daily;

public class LC_20260427_1391_CheckIfThereIsAValidPathInAGrid {
    int m, n;
    int[] left = {0, -1};
    int[] right = {0, 1};
    int[] up = {-1, 0};
    int[] down = {1, 0};
    boolean[][] visited;
    public boolean hasValidPath(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        return dfs(grid, 0, 0);
    }
    int[][] getMoves(int street){
        if(street == 1){
            return new int[][]{left, right};
        } else if (street == 2){
            return new int[][]{up, down};
        } else if (street == 3){
            return new int[][]{left, down};
        } else if (street == 4){
            return new int[][]{right, down};
        } else if (street == 5){
            return new int[][]{left, up};
        }
        return new int[][]{right, up};
    }
    boolean dfs(int[][] grid, int i, int j){
        if(i == m-1 && j == n-1)
            return true;
        visited[i][j] = true;
        for(int[] move : getMoves(grid[i][j])){
            int newX = i + move[0];
            int newY = j + move[1];
            if(newX >= 0 && newX < m
                    && newY >= 0 && newY < n
                    && !visited[newX][newY]
                    && areStreetCont(move, grid[newX][newY])){
                if(dfs(grid, newX, newY))
                    return true;
            }
        }
        return false;
    }

    boolean areStreetCont(int[] move, int next){
        if(move == left){
            return next == 4 || next == 6 || next == 1;
        } else if (move == right){
            return next == 1 || next == 3 || next == 5;
        } else if(move == up){
            return next == 2 || next == 4 || next == 3;
        } else if(move == down){
            return next == 2 || next == 5 || next == 6;
        }
        return false;
    }

    static void main() {
        int[][] grid = {{4, 1}, {6, 1}};
        boolean ans = new LC_20260427_1391_CheckIfThereIsAValidPathInAGrid().hasValidPath(grid);
        System.out.println(ans);
    }
}
