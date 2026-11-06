package leetcode.daily.y2026.m11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LC_06_2747_CountZeroRequestServers {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1]));
        Map<Integer, Integer> freq = new HashMap<>();
        int[][] qs = new int[queries.length][2];
        for(int i = 0; i < queries.length; i++){
            qs[i][0] = i;
            qs[i][1] = queries[i];
        }
        Arrays.sort(qs, (a, b) -> {
            if(a[1] == b[1])
                return a[0] - b[0];
            return a[1] - b[1];
        });
        int[] ans = new int[queries.length];
        int l = 0;
        int r = 0;
        for(int i = 0; i < qs.length; i++){
            int low = qs[i][1] - x;
            int high = qs[i][1];
            while(r < logs.length && logs[r][1] <= high){
                freq.put(logs[r][0], freq.getOrDefault(logs[r][0], 0) + 1);
                r++;
            }
            while(l < logs.length && logs[l][1] < low){
                freq.put(logs[l][0], freq.getOrDefault(logs[l][0], 0) - 1);
                if(freq.get(logs[l][0]) == 0)
                    freq.remove(logs[l][0]);
                l++;
            }
            ans[qs[i][0]] = n - freq.size();
        }
        return ans;
    }

    static void main() {
        int[] ints = new LC_06_2747_CountZeroRequestServers().countServers(3, new int[][]{{1, 3}, {2, 6}, {1, 5}}, 5, new int[]{10, 11});
        System.out.println(Arrays.toString(ints));
    }

}
