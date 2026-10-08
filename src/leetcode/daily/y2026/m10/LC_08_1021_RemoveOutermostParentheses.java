package leetcode.daily.y2026.m10;

public class LC_08_1021_RemoveOutermostParentheses {
    public String removeOuterParentheses(String s) {
        StringBuilder builder = new StringBuilder();
        int depth = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '('){
                depth++;
                if(depth != 1) builder.append(c);
            } else {
                depth--;
                if(depth != 0) builder.append(c);
            }
        }
        return builder.toString();
    }

    static void main() {
        
    }
}
