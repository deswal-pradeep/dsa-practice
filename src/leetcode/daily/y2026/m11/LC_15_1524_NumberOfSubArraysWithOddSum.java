package leetcode.daily.y2026.m11;

public class LC_15_1524_NumberOfSubArraysWithOddSum {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;
        int ans = 0;
        int oddCount = 0;
        int evenCount = 0;
        for(int i = 0; i < arr.length; i++){
            int prevOdd = oddCount;
            int prevEven = evenCount;
            if(arr[i] % 2 == 0){
                evenCount = prevEven + 1;
                oddCount = prevOdd;
            } else {
                oddCount = prevEven + 1;
                evenCount = prevOdd;
            }
            oddCount %= MOD;
            evenCount %= MOD;
            ans += oddCount;
            ans %= MOD;
        }
        return ans;
    }

    static void main() {
        int ans = new LC_15_1524_NumberOfSubArraysWithOddSum()
                .numOfSubarrays(new int[]{1,3,5});
        System.out.println(ans);
    }
}
