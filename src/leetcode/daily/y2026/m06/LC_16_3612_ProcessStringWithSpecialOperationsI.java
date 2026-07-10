package leetcode.daily.y2026.m06;

public class LC_16_3612_ProcessStringWithSpecialOperationsI {
    public String processStr(String s) {
        char[] arr = s.toCharArray();
        StringBuilder builder = new StringBuilder();
        for(char c : arr){
            if(c == '#'){
                builder.append(builder);
            } else if (c == '%'){
                builder.reverse();
            } else if (c == '*'){
                if(!builder.isEmpty())
                    builder.deleteCharAt(builder.length()-1);
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    static void main() {
        String ans = new LC_16_3612_ProcessStringWithSpecialOperationsI()
                .processStr("a#b%*");
        System.out.println(ans);
    }
}
