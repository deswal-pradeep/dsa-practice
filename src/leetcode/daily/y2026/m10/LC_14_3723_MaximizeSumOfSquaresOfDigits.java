package leetcode.daily.y2026.m10;

public class LC_14_3723_MaximizeSumOfSquaresOfDigits {
    public String maxSumOfSquares(int num, int sum) {
        StringBuilder builder = new StringBuilder();
        if(sum > 9 * num)
            return "";
        for(int i = 0; i < num; i++){
            if(sum >= 9){
                builder.append("9");
                sum -= 9;
            } else if (sum > 0){
                builder.append(sum);
                sum = 0;
            } else {
                builder.append("0");
            }
        }
        return builder.toString();
    }

    static void main() {
        String s = new LC_14_3723_MaximizeSumOfSquaresOfDigits().maxSumOfSquares(2, 17);
        System.out.println(s);
    }
}
