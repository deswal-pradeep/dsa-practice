package leetcode.daily.y2026.m11;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC_13_Redo_3420_CountNonDecreasingSubarraysAfterKOperations {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        Deque<int[]>  st = new ArrayDeque<>();
        long change = 0; //current window
        long ans = 0; //count of subarrays
        int right = n-1;
        for(int left = n-1; left >= 0; left--){
            int curMin = nums[left];
            int curMinCount = 1;
            while(!st.isEmpty() && st.peekFirst()[0] <= curMin){
                int[] pair = st.pollFirst();
                curMinCount += pair[1];
                change += (long)(curMin-pair[0]) * pair[1];
            }
            st.offerFirst(new int[]{curMin, curMinCount});
            while(change > k){
                int[] last = st.peekLast();
                last[1]--;
                change -= last[0] - nums[right];
                right--;
                if(last[1] == 0)
                    st.pollLast();
            }
            ans += right-left+1;
        }
        return ans;
    }

    static void main() {
        long l = new LC_13_Redo_3420_CountNonDecreasingSubarraysAfterKOperations()
                .countNonDecreasingSubarrays(new int[]{6, 3, 1, 2, 4, 4}, 7);
        System.out.println(l);
    }
}
