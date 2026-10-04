package leetcode.daily.y2026.m10;

import java.util.Stack;

//https://leetcode.com/problems/valid-parenthesis-string/editorial/?envType=daily-question&envId=2026-10-04
public class LC_04_678_ValidParenthesisString {
    public boolean checkValidString(String s) {
        Stack<Integer> openBrackets = new Stack < > ();
        Stack<Integer> asterisks = new Stack < > ();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                openBrackets.push(i);
            }
            else if (ch == '*') {
                asterisks.push(i);
            } else {
                if (!openBrackets.empty()) {
                    openBrackets.pop();
                } else if (!asterisks.isEmpty()) {
                    asterisks.pop();
                } else {
                    return false;
                }
            }
        }
        while (!openBrackets.isEmpty() && !asterisks.isEmpty()) {
            if (openBrackets.pop() > asterisks.pop()) {
                return false; // '*' before '(' which cannot be balanced.
            }
        }
        return openBrackets.isEmpty();
    }
    public boolean checkValidString_left_right_ptr(String s) {
        int openCount = 0;
        int closeCount = 0;
        int length = s.length() - 1;
        for (int i = 0; i <= length; i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '*') {
                openCount++;
            } else {
                openCount--;
            }

            if (s.charAt(length - i) == ')' || s.charAt(length - i) == '*') {
                closeCount++;
            } else {
                closeCount--;
            }
            if (openCount < 0 || closeCount < 0) {
                return false;
            }
        }
        return true;
    }

    int n;
    public boolean checkValidString_dp(String s) {
        this.n = s.length();
        char[] arr = s.toCharArray();
        boolean[][] dp = new boolean[n+1][n+2];
        dp[n][1] = true;
        for(int ind = n-1; ind >= 0; ind--){
            for(int balance = n; balance > 0; balance--){
                boolean result;
                if(arr[ind] == '('){
                    result = dp[ind+1][balance+1];
                } else if (arr[ind] == ')'){
                    result = dp[ind+1][balance-1];
                } else {
                    result = dp[ind+1][balance] |
                            dp[ind+1][balance+1] |
                            dp[ind+1][balance-1];
                }
                dp[ind][balance] = result;
            }
        }
        return dp[0][1];
    }

    public boolean checkValidString_mem(String s) {
        this.n = s.length();
        char[] arr = s.toCharArray();
        Boolean[][] mem = new Boolean[n][n+1];
        boolean[][] dp = new boolean[n+1][n+1];
        return dp(arr, 0, 0, mem);
    }

    boolean dp(char[] arr, int ind, int balance, Boolean[][] mem){
        if(ind == n){
            return balance == 0;
        }
        if(balance < 0)
            return false;

        if(mem[ind][balance] != null)
            return mem[ind][balance];

        boolean result = false;
        if(arr[ind] == '('){
            result = dp(arr, ind+1, balance+1, mem);
        } else if (arr[ind] == ')'){
            result = dp(arr, ind+1, balance-1, mem);
        } else {
            result = dp(arr, ind+1, balance, mem) |
                    dp(arr, ind+1, balance+1, mem) |
                    dp(arr, ind+1, balance-1, mem);
        }
        mem[ind][balance] = result;
        return result;
    }

    static void main() {
        boolean b = new LC_04_678_ValidParenthesisString().checkValidString("(");
        System.out.println(b);
    }
}
