package leetcode.daily.y2026.m10;

//https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/?envType=daily-question&envId=2026-10-06
public class LC_06_921_MinimumAddToMakeParenthesesValid {
    public int minAddToMakeValid(String s) {
        int balance = 0;
        int ans = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '(')
                balance++;
            else
                balance--;
            if(balance < 0){
                ans++;
                balance = 0;
            }
        }
        return ans + balance;
    }

    static void main() {
        int ans = new LC_06_921_MinimumAddToMakeParenthesesValid().minAddToMakeValid("))((");
        System.out.println(ans);
    }
}
