package leetcode.daily.y2026.m07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/shift-2d-grid/description/?envType=daily-question&envId=2026-07-20
public class LC_20_1260_Shift2DGrid {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] gridNew = new int[m][n];
        k = k % (m * n);

        for(int i = 0; i < m * n; i++){
            int ind = i + k;
            int row = i / n;
            int col = i - (row * n);
            int rowN = ind / n;
            int colN = ind - (rowN * n);
            rowN = rowN % m;
            gridNew[rowN][colN] =  grid[row][col];
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int[] row : gridNew) {
            List<Integer> list = new ArrayList<>();
            for (int num : row) {
                list.add(num);
            }
            result.add(list);
        }

        return result;
    }

    static void main() {
        //int[][] grid = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
        int[][] grid = new int[][]{{1}, {2}};
        List<List<Integer>> lists = new LC_20_1260_Shift2DGrid().shiftGrid(grid, 23);
        for(List<Integer> row : lists){
            System.out.println(row);
        }
    }
}
