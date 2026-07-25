package leetcode.daily.y2026.m07;

//https://leetcode.com/problems/maximum-product-of-two-digits/description/?envType=daily-question&envId=2026-07-25
public class LC_25_3536_MaximumProductOfTwoDigits {
    public int maxProduct(int n) {
        int max1 = -1;
        int max2 = -1;
        while(n > 0){
            int d = n%10;
            if(max1 < d){
                max2 = max1;
                max1 = d;
            } else if (max2 < d){
                max2 = d;
            }
            n /= 10;
        }
        return max1 * max2;
    }

    static void main() {
        int ans = new LC_25_3536_MaximumProductOfTwoDigits().maxProduct(234512345);
        System.out.println(ans);
    }
}
