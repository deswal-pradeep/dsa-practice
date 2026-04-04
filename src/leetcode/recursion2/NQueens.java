package leetcode.recursion2;

import java.util.*;

public class NQueens {
    static void main() {
        System.out.println(new NQueens().solveNQueens(2));
    }
    public List<List<String>> solveNQueens(int n) {
        //your code goes here
        char[][] arr = new char[n][n];
        List<String> set = new ArrayList<>();
        List<List<String>> all = new ArrayList<>();
        for(int i=0; i<n; i++){
            for(int j = 0; j < n; j++){
                arr[i][j] = '.';
            }
        }
        sol(arr, 0, n, set, all);
        return all;
    }

    public void sol(char[][] arr, int row, int n, List<String> set, List<List<String>> all){
        if(row == n){
            all.add(new ArrayList<>(set));
            return;
        }
        //try all column in this row
        for(int col=0; col < n; col++){
            arr[row][col] = 'Q';
            if(isBoardSafe(arr, row, col)){
                set.add(new String(arr[row]));
                sol(arr, row+1, n, set, all);
                set.remove(set.size()-1);
            }
            arr[row][col] = '.';
        }
    }

    boolean isBoardSafe(char[][] arr, int row, int col){
        //check diagonal upward
        int i = row-1;
        int j = col-1;
        while(i>=0 && j>=0){
            if(arr[i][j] == 'Q'){
                return false;
            }
            i--;
            j--;
        }

        //check upward
        i = row-1;
        j = col;
        while(i>=0){
            if(arr[i][j] == 'Q')
                return false;
            i--;
        }
        return true;
    }
}
