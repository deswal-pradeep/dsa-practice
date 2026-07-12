package leetcode.daily.y2026.m07;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/rank-transform-of-an-array/editorial/?envType=daily-question&envId=2026-07-12
public class LC_12_1331_RankTransformOfAnArray {
    public int[] arrayRankTransform(int[] arr) {
        if(arr.length == 0)
            return arr;
        Map<Integer, Integer> rank = new HashMap<>();
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        rank.put(copy[0], 1);
        int r = 1;
        for(int i = 1; i < copy.length; i++){
            if(copy[i] != copy[i-1]){
                rank.put(copy[i], ++r);
            }
        }
        int[] ans = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            ans[i] = rank.get(arr[i]);
        }
        return ans;
    }

    static void main() {
        int[] ans = new LC_12_1331_RankTransformOfAnArray()
                .arrayRankTransform(new int[]{37, 12, 28, 9, 100, 56, 80, 5, 12});
        System.out.println(Arrays.toString(ans));
    }
}
