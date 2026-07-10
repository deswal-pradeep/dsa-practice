package leetcode.daily.y2026.m06;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC_28_1846_MaximumElementAfterDecreasingAndRearranging {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        int[] counts = new int[n + 1];

        for (int num : arr) {
            counts[Math.min(num, n)]++;
        }

        int ans = 1;
        for (int num = 2; num <= n; num++) {
            ans = Math.min(ans + counts[num], num);
        }

        return ans;
    }
    public int maximumElementAfterDecrementingAndRearranging_on(int[] arr){
        Map<Integer, Integer> f = new HashMap<>();
        for(int num : arr){
            f.put(num, f.getOrDefault(num, 0) + 1);
        }

        int cnt = 0;
        int curr = 1;
        int unadj = 0;
        while(cnt <= arr.length-1){
            int freq = f.getOrDefault(curr, 0);
            if(freq > 0){
                if(unadj==0){
                    cnt = cnt + freq;
                    curr = curr + 1;
                } else if(unadj < freq){
                    freq = freq - unadj;
                    unadj = 0;
                    cnt = cnt + freq;
                    curr = curr + 1;
                } else if(unadj >= freq){
                    unadj = unadj - freq;
                    //curr = curr + 1;
                }
            } else {
                unadj++;
                cnt = cnt + 1;
                curr = curr + 1;
            }
        }
        return curr - 1;
    }
    public int maximumElementAfterDecrementingAndRearranging_nLogN(int[] arr) {
        Arrays.sort(arr);
        int ans = 1;
        arr[0] = 1;
        for(int i = 1; i < arr.length; i++){
            if(arr[i] == arr[i-1]){
                continue;
            } else {
                arr[i] = arr[i-1]+1;
                ans = arr[i];
            }
        }
        return ans;
    }

    static void main() {
        int[] arr = new int[]{63,94,4,61,6,40};
        int ans = new LC_28_1846_MaximumElementAfterDecreasingAndRearranging()
                .maximumElementAfterDecrementingAndRearranging(arr);
        System.out.println(ans);
    }
}
