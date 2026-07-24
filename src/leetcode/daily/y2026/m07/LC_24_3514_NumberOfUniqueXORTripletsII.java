package leetcode.daily.y2026.m07;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/number-of-unique-xor-triplets-ii/description/?envType=daily-question&envId=2026-07-24
public class LC_24_3514_NumberOfUniqueXORTripletsII {

    public int uniqueXorTriplets(int[] nums) {
        int maxi = Arrays.stream(nums).max().getAsInt();
        maxi = (1 << (32 - Integer.numberOfLeadingZeros(maxi)));
        boolean[] set = new boolean[maxi];

        for(int i = 0; i < nums.length; i++){
            for(int j = i; j < nums.length; j++){
                int val = nums[i] ^ nums[j];
                set[val] = true;
            }
        }

        boolean[] finalSet = new boolean[maxi];
        for(int i = 0; i < maxi; i++){
            for(int j = 0; j < nums.length; j++){
                int val = i ^ nums[j];
                if(set[val]){
                    finalSet[i] = true;
                    break;
                }
            }
        }
        int ans = 0;
        for(boolean b : finalSet){
            if(b)
                ans++;
        }
        return ans;
    }

    public int uniqueXorTriplets_1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            for(int j = i; j < nums.length; j++){
                set.add(nums[i] ^ nums[j]);
            }
        }
        Set<Integer> finalSet = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            for(Integer val : set){
                finalSet.add(nums[i] ^ val);
            }
        }
        return finalSet.size();
    }

    static void main() {
        int ans = new LC_24_3514_NumberOfUniqueXORTripletsII()
                .uniqueXorTriplets(new int[]{10, 11, 12, 14, 16});
        System.out.println(ans);
    }
}
