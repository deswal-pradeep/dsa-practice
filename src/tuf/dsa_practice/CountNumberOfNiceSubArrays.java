package tuf.dsa_practice;

public class CountNumberOfNiceSubArrays {
    //https://takeuforward.org/plus/dsa/problems/count-number-of-nice-subarrays?subject=dsa-concept-revision
    static void main() {
        int ans = new CountNumberOfNiceSubArrays().numberOfOddSubarrays(new int[]{1, 1, 2, 1, 1}, 3);
        System.out.println(ans);
    }
    public int numberOfOddSubarrays(int[] nums, int k) {
        //your code goes here
        int n = nums.length;
        int left = 0;
        int right = 0;

        int oddCount = 0;
        int ans = 0;
        while(right < n){
            while(right < n && oddCount <= k){
                if(nums[right] % 2 != 0){
                    oddCount++;
                }
                if(oddCount == k){
                    ans++;
                }
                right++;
            }
            while(left < n && oddCount >= k){
                if(nums[left] % 2 != 0){
                    oddCount--;
                }
                if(oddCount == k){
                    ans++;
                }
                left++;
            }
        }
        return ans;
    }
}
