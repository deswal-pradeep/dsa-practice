package leetcode.daily.y2026.m07;

import java.util.Arrays;

//https://leetcode.com/problems/sum-of-gcd-of-formed-pairs/?envType=daily-question&envId=2026-07-16
public class LC_16_3867_SumOfGCDOfFormedPairs {
    public long gcdSum(int[] nums) {
        int max = nums[0];
        int[] prefixGcd = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            max = Math.max(max, nums[i]);
            prefixGcd[i] = gcd(max, nums[i]);
        }
        Arrays.sort(prefixGcd);
        int l = 0;
        int r = prefixGcd.length-1;
        long sumGcd = 0;
        while(l < r){
            sumGcd += gcd(prefixGcd[l++], prefixGcd[r--]);
        }
        return sumGcd;
    }

    int gcd(int a, int b){
        while(b != 0){
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    static void main() {
        long gcdSum = new LC_16_3867_SumOfGCDOfFormedPairs().gcdSum(new int[]{3, 6, 2, 8});
        System.out.println(gcdSum);
    }
}
