package leetcode.daily.y2026.m04;

import java.util.Arrays;

public class LC_20260411_MinimumDistanceBetweenThreeEqualElements {
    //https://leetcode.com/problems/minimum-distance-between-three-equal-elements-ii/description/?envType=daily-question&envId=2026-04-11

    static void main() {
        int[] arr = {1,1,2,3,2,1,2};
        System.out.println(new LC_20260411_MinimumDistanceBetweenThreeEqualElements().minimumDistance(arr));
    }
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int[] lastSeenAt = new int[100001];
        int[] prevSeenAt = new int[n];
        Arrays.fill(lastSeenAt, -1);
        Arrays.fill(prevSeenAt, -1);

        int minDistance = Integer.MAX_VALUE;
        int[] arr = new int[3];
        for(int i = 0; i < n; i++){
            prevSeenAt[i] = lastSeenAt[nums[i]];
            int count = 0;
            int j = i;
            while(count < 3 && j != -1){
                arr[count] = j;
                count++;
                j = prevSeenAt[j];
            }
            if(count == 3){
                int d = Math.abs(arr[0] - arr[1]) + Math.abs(arr[1] - arr[2]) + Math.abs(arr[2] - arr[0]);
                minDistance = Math.min(minDistance, d);
            }
            lastSeenAt[nums[i]] = i;
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}
