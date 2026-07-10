package leetcode.daily.y2026.m04;

import java.util.*;

public class LC_16_3488_ClosestEqualElementQueries {
    //https://leetcode.com/problems/closest-equal-element-queries/?envType=daily-question&envId=2026-04-16
    static void main() {
        List<Integer> ans = new LC_16_3488_ClosestEqualElementQueries().solveQueries(new int[]{1, 3, 1, 4, 1, 3, 2}, new int[]{0, 3, 5});
        System.out.println(ans);
    }
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        List<Integer>  ans = new ArrayList<>(n);
        int[] prevSeenAt = new int[n];
        int[] nextSeenAt = new int[n];
        for(int i = 0; i < n; i++){
            prevSeenAt[i] = -1;
            nextSeenAt[i] = -1;
        }
        Map<Integer, Integer> mapp = new HashMap<>();
        for(int i = 0; i < 2*n; i++){
            int currInd = i % n;
            int val = nums[currInd];
            Integer ind = mapp.get(val);

            if(ind != null && prevSeenAt[currInd] == -1){
                prevSeenAt[currInd] = ind;
                nextSeenAt[ind] = currInd;
            }
            mapp.put(val, currInd);
        }

        System.out.println(Arrays.toString(prevSeenAt));
        System.out.println(Arrays.toString(nextSeenAt));

        for(int i=0; i < queries.length; i++){
            int valIndex = queries[i];
            int pIndex = prevSeenAt[valIndex];
            int nIndex = nextSeenAt[valIndex];

            if(pIndex == valIndex && nIndex == valIndex){
                ans.add(-1);
            } else {
                int d1 = nIndex > valIndex ? nIndex - valIndex : n - valIndex + nIndex;
                int d2 = valIndex > pIndex ? valIndex - pIndex : n - pIndex + valIndex;
                ans.add(Math.min(d1, d2));
            }
        }
        return ans;
    }
}
