package leetcode.daily.y2026.m10;

import java.util.PriorityQueue;

public class LC_10_2333_MinimumSumOfSquaredDifference {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        PriorityQueue<Long> queue = new PriorityQueue<>((a, b) -> Long.compare(b, a));
        long ans = 0;
        for(int i = 0; i < nums1.length; i++){
            queue.add((long) Math.abs(nums1[i] - nums2[i]));
        }
        long ops = k1 + k2;
        long count = 0;
        while(ops > 0 && !queue.isEmpty()){
            long val = queue.poll();
            count = count+1;
            long peek = queue.isEmpty() ? 0 : queue.peek();
            //up to peek is allowed
            if(ops > count * (val-peek)){
                //all will become peek
                ops = ops - (count * (val - peek));
            } else {
                //some will become peek, some will remain
                long opsAllowed = ops;
                long val1 = val - (opsAllowed / count);
                long val2 = val1 - 1;
                long val2Count = opsAllowed % count;
                long val1Count = count - val2Count;
                ans = ((val1 * val1) * val1Count) + (val2 * val2)*val2Count;
                ops = 0;
            }
        }
        while(!queue.isEmpty()){
            long val = queue.poll();
            ans = ans + (val * val);
        }
        return ans;
    }

    static void main() {
        int[] num1 = new int[]{1,4,10,12};
        int[] num2 = new int[]{5,8,6,9};
        long l = new LC_10_2333_MinimumSumOfSquaredDifference().minSumSquareDiff(num1, num2, 1, 1);
        System.out.println(l);
    }
}
