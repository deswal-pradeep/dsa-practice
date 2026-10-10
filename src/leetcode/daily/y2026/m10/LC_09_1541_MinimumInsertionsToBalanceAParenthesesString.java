package leetcode.daily.y2026.m10;

public class LC_09_1541_MinimumInsertionsToBalanceAParenthesesString {
    public int minInsertions(String s) {
        int depth = 0;
        int insertion = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '('){
                depth++;
            } else if (c == ')' && i < s.length() - 1 && s.charAt(i+1) == ')'){
                i++;
                if(depth == 0) insertion++; //))
                else depth--;//())
            } else {
                if(depth == 0) {
                    //)
                    insertion+=2;
                }
                else {
                    //()
                    insertion++;
                    depth--;
                }
            }
        }
        insertion += (depth * 2);
        return insertion;
    }

    static void main() {
        int ans = new LC_09_1541_MinimumInsertionsToBalanceAParenthesesString()
                .minInsertions("))())(");
        System.out.println(ans);
    }
}
