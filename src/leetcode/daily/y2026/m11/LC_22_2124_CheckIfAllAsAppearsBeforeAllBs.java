package leetcode.daily.y2026.m11;

public class LC_22_2124_CheckIfAllAsAppearsBeforeAllBs {
    public boolean checkString(String s) {
        boolean seen = false;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 'b')
                seen = true;
            if(seen && s.charAt(i) == 'a')
                return false;
        }
        return true;
    }
}
