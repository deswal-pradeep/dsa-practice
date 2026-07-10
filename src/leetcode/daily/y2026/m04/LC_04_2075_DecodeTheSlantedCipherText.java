package leetcode.daily.y2026.m04;

public class LC_04_2075_DecodeTheSlantedCipherText {
    //https://leetcode.com/problems/decode-the-slanted-ciphertext/?envType=daily-question&envId=2026-04-04
    static void main() {
        System.out.println(new LC_04_2075_DecodeTheSlantedCipherText().decodeCiphertext("ch   ie   pr", 3));
    }
    public String decodeCiphertext(String encodedText, int rows) {
        int m = rows;
        int n = encodedText.length() / m;
        StringBuilder builder = new StringBuilder("");
        for(int col = 0; col < n; col++){
            for(int row = 0; row < m; row++){
                if(col+row < n){
                    int index = (row * n) + (col+row);
                    builder.append(encodedText.charAt(index));
                }
            }
        }
        //remove trailing spaces
        //find last non space char
        String s = builder.toString();
        int index = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != ' '){
                index = i;
            }
        }
        if(index < s.length()-1)
            s = s.substring(0, index+1);
        return s;
    }
}
