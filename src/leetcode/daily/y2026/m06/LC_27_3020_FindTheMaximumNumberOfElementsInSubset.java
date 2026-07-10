package leetcode.daily.y2026.m06;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC_27_3020_FindTheMaximumNumberOfElementsInSubset {
    public int maximumLength(int[] nums) {
        Map<Integer, Integer> f = new HashMap<>();
        for(int i = 0; i < nums.length; i++)
            f.put(nums[i], f.getOrDefault(nums[i], 0) + 1);

        int oneCnt = f.getOrDefault(1, 0);
        int maxLength = oneCnt % 2 == 0 ? oneCnt - 1 :  oneCnt;
        f.remove(1);

        for(int val : f.keySet()){
            int res = 0;
            int x = val;
            while(f.containsKey(x) && f.get(x) > 1){
                res += 2;
                x *= x;
            }
            res = f.containsKey(x) ? res+1 : res - 1;
            maxLength = Math.max(maxLength, res);
        }
        return maxLength;
    }

    public int maximumLength_1(int[] nums) {
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> f = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            f.put(nums[i], f.getOrDefault(nums[i], 0) + 1);
        }

        int maxLength = 0;
        for(int i = 0; i < nums.length; i++){
            int length = 0;
            int val = nums[i];
            if(!visited.contains(val)){
                int freq = f.getOrDefault(val, 0);

                int depth = 0;
                while(true){
                    visited.add(val);
                    if(val == 1){
                        length = freq % 2 == 0 ? freq - 1 : freq;
                        visited.add(val);
                        break;
                    }
                    if(freq >= 2){
                        //then try for more
                        depth++;
                    } else if (freq == 1){
                        //then its last
                        length = depth * 2 + 1;
                        break;
                    } else if(freq == 0){
                        //previous was the last
                        length = (depth - 1) * 2 + 1;
                        break;
                    }
                    val = val * val;
                    freq = f.getOrDefault(val, 0);
                }
            }
            maxLength = Math.max(maxLength, length);
        }
        return maxLength;
    }

    static void main() {
        int ans = new LC_27_3020_FindTheMaximumNumberOfElementsInSubset()
                .maximumLength(new int[]{5, 4});
        System.out.println(ans);
    }
}
