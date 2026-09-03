package leetcode.daily.y2026.m09;

//https://leetcode.com/problems/construct-uniform-parity-array-ii/?envType=daily-question&envId=2026-09-03
public class LC_03_3876_ConstructUniformParityArrayII {
    public boolean uniformArray(int[] nums1) {
        int minOdd = (int)1e9+1;
        int minEven = (int)1e9+2;
        for(int num : nums1){
            if((num & 1) == 0){
                //even
                minEven = Math.min(minEven, num);
            } else {
                //odd
                minOdd = Math.min(minOdd, num);
            }
        }
        if(minEven == (int)1e9+2 || minOdd == (int)1e9+1){
            return true;
        }
        return minEven > minOdd;
    }

    static void main() {
        boolean ans = new LC_03_3876_ConstructUniformParityArrayII().uniformArray(new int[]{1, 4, 7});
        System.out.println(ans);
    }
}
