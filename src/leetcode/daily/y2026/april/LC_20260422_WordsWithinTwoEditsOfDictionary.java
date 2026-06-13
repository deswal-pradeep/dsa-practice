package leetcode.daily.y2026.april;

import java.util.ArrayList;
import java.util.List;

public class LC_20260422_WordsWithinTwoEditsOfDictionary {
    static void main() {
        new LC_20260422_WordsWithinTwoEditsOfDictionary()
                .twoEditWords(new String[]{"word","note","ants","wood"}, new String[]{"wood","joke","moat"});
    }
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        int n = queries.length;
        List<String> ans = new ArrayList<>(queries.length);
        for(int i = 0; i < queries.length; i++){
            for(int j = 0; j < dictionary.length; j++){
                if(editDistance(queries[i], dictionary[j], 2)){
                    ans.add(queries[i]);
                    break;
                }
            }
        }
        return ans;
    }

    boolean editDistance(String a, String b, int allowed){
        int n = a.length();
        int distance = 0;
        for(int i = 0; i < n; i++){
            if(a.charAt(i) != b.charAt(i))
                distance++;
            if(distance > allowed)
                return false;
        }
        return distance <= allowed;
    }
}
