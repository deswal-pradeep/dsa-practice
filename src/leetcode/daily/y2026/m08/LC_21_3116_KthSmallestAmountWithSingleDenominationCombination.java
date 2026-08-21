package leetcode.daily.y2026.m08;

public class LC_21_3116_KthSmallestAmountWithSingleDenominationCombination {
    public long findKthSmallest(int[] coins, int k) {
        long mini = Long.MAX_VALUE;
        for(int coin : coins)
            mini = Math.min(mini, coin);
        long low = mini;
        long high = k * mini;
        long ans = high;

        while(low <= high){
            long mid = (low + high) / 2;
            long smallerCount = countSmallerAmounts(coins, mid);
            if(smallerCount >= k){
                high = mid-1;
                ans = mid;
            } else {
                low = mid+1;
            }
        }
        return ans;
    }

    long countSmallerAmounts(int[] coins, long mid){
        int n = coins.length;
        long smallerCount = 0;
        int allCount = 1 << n;
        for(int x = 1; x < allCount; x++){
            int count = 0;
            long lcm = 1;
            for(int j = 0; j < n; j++){
                //check if jth bit set or not in x
                if ((x & (1 << j)) != 0) {
                    lcm = lcm(lcm, coins[j]);
                    count++;
                }
            }
            if(count % 2 != 0){
                //odd
                smallerCount = smallerCount + (mid / lcm);
            } else {
                //even
                smallerCount = smallerCount - (mid / lcm);
            }
        }
        return smallerCount;
    }

    long lcm(long val1, long val2) {
        return (val1 / gcd(val1, val2)) * val2;
    }

    long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    static void main() {
        long kthSmallest = new LC_21_3116_KthSmallestAmountWithSingleDenominationCombination()
                .findKthSmallest(new int[]{5, 2}, 7);
        System.out.println(kthSmallest);
    }
}
