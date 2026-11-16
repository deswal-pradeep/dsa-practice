package leetcode.daily.y2026.m11;

public class LC_16_2243_CalculateDigitSumOfAString {
    public String digitSum(String s, int k) {
        StringBuilder builder;
        while(s.length() > k){
            builder = new StringBuilder();
            int sum = 0;
            for(int i = 0; i < s.length(); i++){
                if(i % k == 0 && i > 0){
                    builder.append(sum);
                    sum = 0;
                }
                sum += (int)s.charAt(i) - '0';
                if(i == s.length()-1)
                    builder.append(sum);
            }
            s = builder.toString();
        }
        return s;
    }

    static void main() {
        String s = new LC_16_2243_CalculateDigitSumOfAString().digitSum("11111222223", 3);
        System.out.println(s);
    }
}
