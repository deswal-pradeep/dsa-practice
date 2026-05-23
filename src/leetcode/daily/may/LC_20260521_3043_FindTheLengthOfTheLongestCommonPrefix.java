package leetcode.daily.may;

import java.util.Arrays;

//https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/description/?envType=daily-question&envId=2026-05-21
public class LC_20260521_3043_FindTheLengthOfTheLongestCommonPrefix {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        String[] strArr1 = new String[arr1.length];
        String[] strArr2 = new String[arr2.length];
        for(int i = 0; i < arr1.length; i++){
            strArr1[i] = "" + arr1[i];
        }
        for(int i = 0; i < arr2.length; i++){
            strArr2[i] = "" + arr2[i];
        }
        Arrays.sort(strArr1);
        Arrays.sort(strArr2);
        int ind1 = 0;
        int ind2 = 0;
        int maxi = 0;
        while(ind1 < strArr1.length && ind2 < strArr2.length){
            maxi = Math.max(maxi, commonPrefix(strArr1[ind1], strArr2[ind2]));
            if(strArr1[ind1].compareTo(strArr2[ind2]) < 0) ind1++;
            else ind2++;
        }
        return maxi;
    }

    int commonPrefix(String a, String b){
        int i = 0;
        int prefixLength = 0;
        while(i < a.length() && i < b.length()){
            if(a.charAt(i) == b.charAt(i))
                prefixLength++;
            else
                break;
            i++;
        }
        return prefixLength;
    }

    static void main() {
        int ans = new LC_20260521_3043_FindTheLengthOfTheLongestCommonPrefix()
                .longestCommonPrefix(
                        new int[]{1,2,3},
                        new int[]{4,4,4});
        System.out.println(ans);
    }
}
