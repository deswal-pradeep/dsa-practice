package leetcode.daily.y2026.may;

import java.util.Arrays;

public class LC_20260504_48_RotateArray {
    static void main() {
        int[][] mat = {{1,2,3},{4,5,6},{7,8,9}};
        new LC_20260504_48_RotateArray().rotate(mat);
        for(int i = 0; i < mat.length; i++){
            System.out.println(Arrays.toString(mat[i]));
        }
    }
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }
}
