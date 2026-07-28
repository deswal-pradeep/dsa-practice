package leetcode.daily.y2026.m07;

import java.util.Arrays;

//https://leetcode.com/problems/smallest-palindromic-rearrangement-i/description/?envType=daily-question&envId=2026-07-28
public class LC_28_3517_SmallestPalindromicRearrangementI {
    public String smallestPalindrome(String s) {
        int len = s.length();
        int subLen = s.length() / 2;
        String sub = s.substring(0, subLen);
        char[] arr = sub.toCharArray();
        Arrays.sort(arr);
        StringBuilder subBuilder = new StringBuilder();
        subBuilder.append(arr);
        StringBuilder ans = new StringBuilder();
        if(len % 2 == 0){
            ans.append(subBuilder)
                    .append(subBuilder.reverse());
        } else {
            ans.append(subBuilder)
                    .append(s.charAt(subLen))
                    .append(subBuilder.reverse());
        }
        return ans.toString();
    }

    static void main() {
        String z = new LC_28_3517_SmallestPalindromicRearrangementI()
                .smallestPalindrome("babab");
        System.out.println(z);
    }
}
