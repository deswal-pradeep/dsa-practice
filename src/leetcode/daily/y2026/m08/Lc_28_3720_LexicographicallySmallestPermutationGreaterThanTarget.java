package leetcode.daily.y2026.m08;

import java.util.Arrays;

//https://leetcode.com/problems/lexicographically-smallest-permutation-greater-than-target/?envType=daily-question&envId=2026-08-27
public class Lc_28_3720_LexicographicallySmallestPermutationGreaterThanTarget {
    public String lexGreaterPermutation(String s, String target) {
        char[] arr = s.toCharArray();
        int[] f = new int[26];
        for(int i = 0; i < arr.length; i++)
            f[arr[i]-'a']++;

        char[] ans = new char[arr.length];
        int pivot = -1; //first mismatch
        Character pivotChar = null;
        for(int i = 0; i < arr.length; i++){
            char c = target.charAt(i);
            if(f[c-'a'] > 0){
                ans[i] = c;
                f[c-'a']--;
            } else {
                pivotChar = c;
                pivot = i;
                break;
            }
        }

        //find just next greater than pivotChar
        boolean biggerFound = false;
        if(pivotChar != null){
            for(char c = (char)(pivotChar+1); c <= 'z'; c++){
                if(f[c-'a'] > 0){
                    ans[pivot] = c;
                    f[c-'a']--;
                    biggerFound = true;
                    break;
                }
            }
        }
        int idx = biggerFound ? pivot+1 : pivot;
        for(int i = 0; i < 26; i++){
            while(f[i] > 0){
                ans[idx++] = (char)('a' + i);
                f[i]--;
            }
        }

        if(!biggerFound){
            pivot = nextPerm(pivot == -1 ? arr.length-1: pivot-1, ans);
            if(pivot == -1){
                return "";
            }
        }
        return new String(ans);
    }

    int nextPerm(int end, char[] arr){
        int left = -1;
        int right = -1;
        for(int i = end; i >= 0; i--){
            for(int j = i+1; j < arr.length; j++){
                if(arr[j] > arr[i]){
                    if(right == -1 || arr[j] < arr[right]) {
                        left = i;
                        right = j;
                    }
                }
            }
            if(left != -1)
                break;
        }
        if(left == -1)
            return -1;
        char t = arr[left];
        arr[left] = arr[right];
        arr[right] = t;
        Arrays.sort(arr, left+1, arr.length);
        return left;
    }

    static void main() {
        String s = new Lc_28_3720_LexicographicallySmallestPermutationGreaterThanTarget()
                .lexGreaterPermutation("abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdab",
                        "dcbadcbadcbadcbadcbadcbadcbadcbadcbadcbadcbadcbadc");
        System.out.println(s);
    }
}
