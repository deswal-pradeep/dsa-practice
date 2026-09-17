package leetcode.daily.y2026.m09;

public class LC_17_1477_FindTwoNonOverlappingSubArraysEachWithTargetSum {
    int MAX_VALUE = (int)1e9;
    public int minSumOfLengths(int[] arr, int target) {
        int l = 0;
        int r = 0;
        int[] prefix = new int[arr.length+1];
        int minLength = MAX_VALUE;
        int sum = 0;
        prefix[0] = MAX_VALUE;
        while(r < arr.length){
            sum += arr[r];
            while(sum >= target && l <= r){
                if(target == sum){
                    int len = r - l + 1;
                    minLength = Math.min(minLength, len);
                }
                sum -= arr[l];
                l++;
            }
            prefix[r+1] = minLength;
            r++;
        }

        minLength = MAX_VALUE;
        int[] suffix = new int[arr.length];
        sum = 0;
        l = arr.length-1;
        r = arr.length-1;
        while(l >= 0){
            sum += arr[l];
            while(sum >= target && r >= l){
                if(target == sum){
                    int len = r - l + 1;
                    minLength = Math.min(minLength, len);
                }
                sum -= arr[r];
                r--;
            }
            suffix[l] = minLength;
            l--;
        }

        int ans = MAX_VALUE;
        for(int i = 0; i < arr.length; i++){
            ans = Math.min(ans, prefix[i] + suffix[i]);
        }
        return ans == MAX_VALUE ? -1 : ans;
    }

    static void main() {
        int ans = new LC_17_1477_FindTwoNonOverlappingSubArraysEachWithTargetSum()
                .minSumOfLengths(new int[]{3,2,2,4,3}, 3);
        System.out.println(ans);
    }
}
