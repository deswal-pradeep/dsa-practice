package leetcode.lc75;

public class LC_175_ReverseWordInAString {
    static void main() {
        String ans = new LC_175_ReverseWordInAString().reverseWords("  hello   world  ");
        System.out.println(ans);
    }

    public String reverseWords(String s) {
        String[] arr = s.trim().split("\\s+");
        StringBuilder builder = new StringBuilder();
        for(int i = arr.length-1; i > 0; i--){
            builder.append(arr[i]);
            builder.append(" ");
        }
        builder.append(arr[0]);
        return builder.toString();
    }
}
