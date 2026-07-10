package leetcode.daily.y2026.m06;

//https://leetcode.com/problems/find-the-highest-altitude/?envType=daily-question&envId=2026-06-19
public class LC_20260619_1732_FindTheHighestAltitude {
    public int largestAltitude(int[] gain) {
        int alt = 0;
        int maxi = 0;
        for(int g : gain){
            alt += g;
            maxi = Math.max(alt, maxi);
        }
        return maxi;
    }

    static void main() {
        int ans = new LC_20260619_1732_FindTheHighestAltitude()
                .largestAltitude(new int[]{-4, -3, -2, -1, 4, 3, 2});
        System.out.println(ans);
    }
}
