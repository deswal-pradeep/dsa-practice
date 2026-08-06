package leetcode.daily.y2026.m08;

//https://leetcode.com/problems/smallest-divisible-digit-product-i/?envType=daily-question&envId=2026-08-06
public class LC_06_3345_SmallestDivisibleDigitProductI {
    public int smallestNumber(int n, int t) {
        int x = n;
        while(!check(n,t)){
            n++;
        }
        return n;
    }

    boolean check(int x, int t){
        int val = 1;
        while(x > 0){
            val = val * (x % 10);
            x /= 10;
        }
        return val % t == 0;
    }
}
