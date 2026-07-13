package leetcode.daily.y2026.m07;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/sequential-digits/?envType=daily-question&envId=2026-07-13
public class LC_13_1291_SequentialDigits {
    public List<Integer> sequentialDigits(int low, int high) {
        int lowDigits = countDigits(low);
        int highDigits = countDigits(high);

        List<Integer> ans = new ArrayList<>();
        for (int d = lowDigits; d <= highDigits; d++) {
            recurse(d, ans, low, high);
        }
        return ans;
    }

    int countDigits(int val){
        int cnt = 0;
        while(val > 0){
            val = val / 10;
            cnt++;
        }
        return cnt;
    }

    void recurse(int digits, List<Integer> seq, int low, int high){
        char[] arr = new char[digits];
        int d = 1;
        for(int i = 0; i < arr.length; i++){
            arr[i] = (char)('0' + d);
            d++;
        }

        while(arr[arr.length - 1] <= '9'){
            String s = new String(arr);
            int val = Integer.parseInt(s);
            if(val >= low && val <= high){
                seq.add(val);
            }
            for(int i = 0; i < arr.length; i++){
                arr[i] = (char)(arr[i] + 1);
            }
        }
    }

    static void main() {
        List<Integer> integers = new LC_13_1291_SequentialDigits().sequentialDigits(100, 300);
        System.out.println(integers);
    }
}
