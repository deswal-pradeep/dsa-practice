package leetcode.daily.y2026.m09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LC_18_1520_MaximumNumberOfNonOverlappingSubstrings {
    public List<String> maxNumOfSubstrings_editorial(String s) {
        Seg[] seg = new Seg[26];
        for (int i = 0; i < 26; ++i) {
            seg[i] = new Seg(-1, -1);
        }
        // Preprocess the left and right endpoints.
        for (int i = 0; i < s.length(); ++i) {
            int charIdx = s.charAt(i) - 'a';
            if (seg[charIdx].left == -1) {
                seg[charIdx].left = seg[charIdx].right = i;
            } else {
                seg[charIdx].right = i;
            }
        }
        for (int i = 0; i < 26; ++i) {
            if (seg[i].left != -1) {
                for (int j = seg[i].left; j <= seg[i].right; ++j) {
                    int charIdx = s.charAt(j) - 'a';
                    if (
                            seg[i].left <= seg[charIdx].left &&
                                    seg[charIdx].right <= seg[i].right
                    ) {
                        continue;
                    }
                    seg[i].left = Math.min(seg[i].left, seg[charIdx].left);
                    seg[i].right = Math.max(seg[i].right, seg[charIdx].right);
                    j = seg[i].left;
                }
            }
        }
        // Greedily select intervals.
        Arrays.sort(seg);
        List<String> ans = new ArrayList<>();
        int end = -1;
        for (Seg segment : seg) {
            int left = segment.left,
                    right = segment.right;
            if (left == -1) {
                continue;
            }
            if (end == -1 || left > end) {
                end = right;
                ans.add(s.substring(left, right + 1));
            }
        }
        return ans;
    }

    class Seg implements Comparable<Seg> {

        int left, right;

        public Seg(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public int compareTo(Seg rhs) {
            if (right == rhs.right) {
                return rhs.left - left;
            }
            return right - rhs.right;
        }
    }

    public List<String> maxNumOfSubstrings(String s) {
        int[][] map = new int[26][2];
        for(int i = 0; i < 26; i++)
            Arrays.fill(map[i], -1);
        for(int i = 0; i < s.length(); i++){
            int ind = s.charAt(i)-'a';
            if(map[ind][0] == -1)
                map[ind][0] = i;
            map[ind][1] = i;
        }
        int[][] newMap = expand(map, s);

        Arrays.sort(newMap, Comparator.comparingInt(a -> a[1]-a[0]+1));
        List<int[]> list = new ArrayList<>();
        for(int[] sub : newMap){
            if(sub[0] == -1)
                continue;
            if(!hasOverlap(list, sub)){
                list.add(sub);
            }
        }
        List<String> ans = new ArrayList<>();
        for(int[] sub : list){
            ans.add(s.substring(sub[0], sub[1]+1));
        }
        return ans;
    }
    int[][] expand(int[][] map, String s){
        for(int i = 0; i < 26; i++){
            int[] curr = map[i];
            if(curr[0] == -1)
                continue;
            for(int j = curr[0]; j <= curr[1]; j++){
                char c = s.charAt(j);
                if(c != s.charAt(curr[0])){
                    int[] match = map[c-'a'];
                    if(match[0] < curr[0] || match[1] > curr[1]){
                        curr[0] = Math.min(curr[0], match[0]);
                        curr[1] = Math.max(curr[1], match[1]);
                        j = curr[0];
                    }
                }
            }
        }
        return map;
    }

    boolean hasOverlap(List<int[]> list, int[] sub){
        for(int[] item : list){
            if(!(item[1] < sub[0] || item[0] > sub[1]))
                return true;
        }
        return false;
    }

    static void main() {
        List<String> ans = new LC_18_1520_MaximumNumberOfNonOverlappingSubstrings()
                .maxNumOfSubstrings("dzdabazbbccd");
        System.out.println(ans);
    }
}
