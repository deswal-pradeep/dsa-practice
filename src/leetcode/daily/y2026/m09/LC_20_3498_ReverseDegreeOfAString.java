package leetcode.daily.y2026.m09;

//https://leetcode.com/problems/reverse-degree-of-a-string/?envType=daily-question&envId=2026-09-20
public class LC_20_3498_ReverseDegreeOfAString {
    public int reverseDegree(String s) {
        int ans = 0;
        for(int i = 0; i < s.length(); i++){
            int val = 'z' - s.charAt(i) + 1;
            ans += (val * (i+1));
        }
        return ans;
    }
}
