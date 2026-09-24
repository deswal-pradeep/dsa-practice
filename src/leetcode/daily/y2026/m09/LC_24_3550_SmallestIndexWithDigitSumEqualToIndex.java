package leetcode.daily.y2026.m09;

//https://leetcode.com/problems/smallest-index-with-digit-sum-equal-to-index/?envType=daily-question&envId=2026-09-24
public class LC_24_3550_SmallestIndexWithDigitSumEqualToIndex {
    public int smallestIndex(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            if(digiSum(nums[i]) == i)
                return i;
        }
        return -1;
    }

    int digiSum(int val){
        int dSum = 0;
        while(val > 0){
            dSum += (val % 10);
            val /= 10;
        }
        return dSum;
    }
}
