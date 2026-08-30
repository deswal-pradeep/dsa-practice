package leetcode.daily.y2026.m08;

//https://leetcode.com/problems/removing-minimum-and-maximum-from-array/description/?envType=daily-question&envId=2026-08-30
public class LC_30_2091_RemovingMinimumAndMaximumFromArray {
    public int minimumDeletions(int[] nums) {
        int minIndex = 0;
        int maxIndex = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[minIndex] > nums[i]){
                minIndex = i;
            }
            if(nums[maxIndex] < nums[i]){
                maxIndex = i;
            }
        }

        int minDeletion = (int)1e9;
        int frontCount = Math.max(minIndex, maxIndex)+1;
        minDeletion = Math.min(minDeletion, frontCount);
        int backCount = nums.length - Math.min(minIndex, maxIndex);
        minDeletion = Math.min(minDeletion, backCount);
        int fronAndBack = Math.min(minIndex, maxIndex) + 1
                + nums.length - Math.max(minIndex, maxIndex);
        minDeletion = Math.min(minDeletion, fronAndBack);
        return minDeletion;
    }

    static void main() {
        int ans = new LC_30_2091_RemovingMinimumAndMaximumFromArray().minimumDeletions(new int[]{2, 10, 7, 5, 4, 1, 8, 6});
        System.out.println(ans);
    }
}
