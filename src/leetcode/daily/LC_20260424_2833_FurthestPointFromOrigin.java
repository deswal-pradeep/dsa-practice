package leetcode.daily;

//https://leetcode.com/problems/furthest-point-from-origin/?envType=daily-question&envId=2026-04-24
public class LC_20260424_2833_FurthestPointFromOrigin {
    int n;
    public int furthestDistanceFromOrigin(String moves) {
        n = moves.length();
        int lCount = 0;
        int rCount = 0;
        int uCount = 0;
        char[] arr = moves.toCharArray();
        for(int i = 0; i < n; i++){
            if(arr[i] == 'L')
                lCount++;
            else if (arr[i] == 'R')
                rCount++;
            else
                uCount++;
        }
        return uCount + Math.abs(lCount-rCount);
    }

    static void main() {
        System.out.println(new LC_20260424_2833_FurthestPointFromOrigin().furthestDistanceFromOrigin("_R__LL_"));
    }
}
