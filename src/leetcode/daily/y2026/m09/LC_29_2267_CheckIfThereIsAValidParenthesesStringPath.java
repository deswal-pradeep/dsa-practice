package leetcode.daily.y2026.m09;

public class LC_29_2267_CheckIfThereIsAValidParenthesesStringPath {
    int m, n;
    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        Boolean[][][] mem = new Boolean[m][n][m+n+1];
        return dp(grid, 0, 0, 0, mem);
    }

    boolean dp(char[][] arr, int i, int j, int count, Boolean[][][] mem){
        if((i == m-1 && j == n) || (i == m && j == n-1)){
            return count == 0;
        }
        if(i >= m || j >= n){
            return false;
        }
        if(count < 0)
            return false;

        if(mem[i][j][count] != null)
            return mem[i][j][count];
        boolean ans = false;
        if(arr[i][j] == '('){
            ans = ans | dp(arr, i+1, j, count+1, mem);
            ans = ans | dp(arr, i, j+1, count+1, mem);
        } else if (arr[i][j] == ')'){
            ans = ans | dp(arr, i+1, j, count-1, mem);
            ans = ans | dp(arr, i, j+1, count-1, mem);
        }
        mem[i][j][count] = ans;
        return ans;
    }

    static void main() {
        char[][] grid = new char[][]{{'(','(','('}, {')','(',')'}, {'(','(',')'}, {'(','(',')'}};
        boolean b = new LC_29_2267_CheckIfThereIsAValidParenthesesStringPath().hasValidPath(grid);
        System.out.println(b);
    }
}
