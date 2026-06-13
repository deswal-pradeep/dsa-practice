package leetcode.daily.y2026.may;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LC_20260520_2657_FindThePrefixCommonArrayOfTwoArrays {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int[] ans = new int[A.length];
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < A.length; i++){
            set.add(A[i]); set.add(B[i]);
            ans[i] = 2 * (i+1) - set.size();
        }
        return ans;
    }
    public int[] findThePrefixCommonArray1(int[] A, int[] B) {
        int[] ans = new int[A.length];
        Map<Integer, Integer> f1 =new HashMap<>();
        Map<Integer, Integer> f2 =new HashMap<>();
        for(int i = 0; i < A.length; i++){
            int val1 = A[i];
            int val2 = B[i];
            f1.put(val1, f1.getOrDefault(val1, 0) + 1);
            f2.put(val2, f2.getOrDefault(val2, 0) + 1);
            int count = count(f1, f2);
            ans[i] =count;
        }
        return ans;
    }

    public int count(Map<Integer, Integer> f1, Map<Integer, Integer> f2){
        int count = 0;
        for(Map.Entry<Integer, Integer> entry : f1.entrySet()){
            Integer key = entry.getKey();
            int val1 = entry.getValue();
            int val2 = f2.getOrDefault(key, 0);
            count = count + Math.min(val1, val2);
        }
        return count;
    }

    static void main() {
        int[] ans = new LC_20260520_2657_FindThePrefixCommonArrayOfTwoArrays()
                .findThePrefixCommonArray(
                        new int[]{1,3,2,4},
                        new int[]{3,1,2,4});
        System.out.println(Arrays.toString(ans));

    }
}
