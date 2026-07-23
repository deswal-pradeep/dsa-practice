package leetcode.daily.y2026.m07;

//https://leetcode.com/problems/number-of-unique-xor-triplets-i/?envType=daily-question&envId=2026-07-23
public class LC_23_3513_NumberOfUniqueXORTripletsI {
    public int uniqueXorTriplets(int[] nums) {
        return nums.length <= 2 ? nums.length : getAns(nums.length);
    }

    int getAns(int n){
        int i = 1;
        while(i <= n){
            i = i << 1;
        }
        return i;
    }

    static void main() {
        int ans = new LC_23_3513_NumberOfUniqueXORTripletsI()
                .uniqueXorTriplets(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        System.out.println(ans);
    }
}
