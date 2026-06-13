package leetcode.daily.y2026.may;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/cyclically-rotating-a-grid/?envType=daily-question&envId=2026-05-09
public class LC_20260509_1914_CyclicallyRotatingAGrid {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int nLayer = Math.min(m, n) / 2;

        for(int layer = 0; layer < nLayer; layer++){
            List<int[]> indexes = new ArrayList<>();
            List<Integer> val = new ArrayList<>();
            //left
            for(int i = layer; i < m - layer - 1; i++){
                indexes.add(new int[]{i, layer});
                val.add(grid[i][layer]);
            }
            //down
            for(int i = layer; i < n-layer-1; i++){
                indexes.add(new int[]{m-layer-1, i});
                val.add(grid[m-layer-1][i]);
            }
            //right
            for(int i = m - layer -1; i > layer; i--){
                indexes.add(new int[]{i, n - layer - 1});
                val.add(grid[i][n - layer - 1]);
            }
            //up
            for(int i = n - layer - 1; i > layer; i--){
                indexes.add(new int[]{layer, i});
                val.add(grid[layer][i]);
            }

            int total = val.size();
            int kk = k % total;

            for(int i = 0; i < total; i++){
                int[] index = indexes.get(i);
                int row = index[0];
                int col = index[1];
                int idx = (i + total - kk) % total;
                grid[row][col] = val.get(idx);
            }
        }
        return grid;
    }

    static void main() {
        int[][] grid = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        int[][] ans = new LC_20260509_1914_CyclicallyRotatingAGrid().rotateGrid(grid, 2);
    }
}
