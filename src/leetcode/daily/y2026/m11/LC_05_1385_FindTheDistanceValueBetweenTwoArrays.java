package leetcode.daily.y2026.m11;

public class LC_05_1385_FindTheDistanceValueBetweenTwoArrays {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int ans = 0;
        for(int i = 0; i < arr1.length; i++){
            boolean res = false;
            for(int j = 0; j < arr2.length; j++){
                if(Math.abs(arr1[i] - arr2[j]) <= d){
                    res = true;
                    break;
                }
            }
            if(!res)
                ans++;
        }
        return ans;
    }
}
