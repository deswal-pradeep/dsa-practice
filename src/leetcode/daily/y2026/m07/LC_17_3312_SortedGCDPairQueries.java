package leetcode.daily.y2026.m07;

import java.util.*;

//https://leetcode.com/problems/sorted-gcd-pair-queries/?envType=daily-question&envId=2026-07-17
public class LC_17_3312_SortedGCDPairQueries {
    public int[] gcdValues(int[] nums, long[] queries) {
        int n = nums.length;
        int m = Arrays.stream(nums).max().getAsInt();
        long[] cnt = new long[m+1];
        /**
         * First, count the occurrences of each value in nums and store them in the array cnt.
         * Then, for every positive integer i∈[1,m], enumerate all of its multiples to count
         * how many elements in nums are divisible by i. Any pair formed from these elements has a GCD of at least i.
         * However, "at least i" is not the same as "exactly i", because it also includes pairs
         * whose GCD is 2i, 3i, and so on.
         */
        //count freq of a number
        for(int num : nums)
            cnt[num]++;

        //count frequency of numbers and its multiples
        for(int i = 1; i <= m; i++){
            for(int j = i*2; j <= m; j+=i){
                cnt[i] += cnt[j];
            }
        }
        //count pairs
        for (int i = 1; i <= m; i++) {
            cnt[i] = (cnt[i] * (cnt[i] - 1)) / 2;
        }
        //remove pairs with inclusion exclusion
        for (int i = m; i >= 1; i--) {
            for (int j = i * 2; j <= m; j += i) {
                cnt[i] -= cnt[j];
            }
        }

        long[] prefixGcd = new long[cnt.length+1];
        for(int i = 0; i < cnt.length; i++){
            prefixGcd[i+1] = prefixGcd[i] + cnt[i];
        }
        //find first index
        int[] ans = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            int ind = findIndex(prefixGcd, queries[i]);
            ans[i] = ind-1;
        }
        return ans;
    }

    int findIndex(long[] prefixGcd, long ind){
        int l = 0;
        int r = prefixGcd.length-1;
        int ans = 0;
        while(l <= r){
            int mid = (l + r) >> 1;
            if(prefixGcd[mid] >= ind+1){
                r = mid-1;
                ans = mid;
            } else {
                l = mid+1;
            }
        }
        return ans;
    }

    static void main() {
        int[] ints = new LC_17_3312_SortedGCDPairQueries()
                .gcdValues(new int[]{2,5,7,8,9,12,15,8,9,10},
                        new long[]{39});
        System.out.println(Arrays.toString(ints));
    }
}
