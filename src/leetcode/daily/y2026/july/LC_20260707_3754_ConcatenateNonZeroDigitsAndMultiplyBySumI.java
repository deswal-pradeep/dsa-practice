package leetcode.daily.y2026.july;

import java.util.Stack;

public class LC_20260707_3754_ConcatenateNonZeroDigitsAndMultiplyBySumI {
    public long sumAndMultiply(int n) {
        int val = 0;
        long sum = 0;
        while(n > 0){
            int rem = n % 10;
            if(rem > 0){
                val = val * 10;
                val += rem;
                sum += rem;
            }
            n = n / 10;
        }
        int rev = 0;
        while(val > 0){
            rev = rev * 10;
            rev = rev + (val % 10);
            val = val / 10;
        }
        return rev * sum;
    }

    public long sumAndMultiply_1(int n) {
        Stack<Integer> stack = new Stack<>();
        while(n > 0){
            int rem = n % 10;
            if(rem > 0)
                stack.push(rem);
            n = n / 10;
        }
        long sum = 0;
        int val = 0;
        while(!stack.isEmpty()){
            int el = stack.pop();
            sum += el;
            val = val * 10;
            val = val + el;
        }
        return val * sum;
    }

    static void main() {
        long l = new LC_20260707_3754_ConcatenateNonZeroDigitsAndMultiplyBySumI().sumAndMultiply(10203004);
        System.out.println(l);
    }
}
