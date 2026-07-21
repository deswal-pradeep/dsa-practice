package leetcode.daily.y2026.m07;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/maximize-active-section-with-trade-i/editorial/?envType=daily-question&envId=2026-07-21
public class LC_21_3499_MaximizeActiveSectionWithTradeI {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int cnt1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') cnt1++;
        }

        List<Integer> zeroBlocks = new ArrayList<>();
        int i = 0;
        while (i < n) {
            int start = i;
            while (i < n && s.charAt(i) == s.charAt(start)) {
                i++;
            }
            if (s.charAt(start) == '0') {
                zeroBlocks.add(i - start);
            }
        }

        int m = zeroBlocks.size();
        if (m < 2) {
            return cnt1;
        }
        int bestGain = 0; // Optimal Increment
        for (int j = 0; j < m - 1; j++) {
            bestGain = Math.max(
                    bestGain,
                    zeroBlocks.get(j) + zeroBlocks.get(j + 1)
            );
        }

        return cnt1 + bestGain;
    }

    public int maxActiveSectionsAfterTrade_1(String s) {
        List<Integer> list = new ArrayList<>(s.length()+2);
        String aug = "1" + s + "1";

        int val = 1;
        for(int i = 1; i < aug.length(); i++){
            if(aug.charAt(i) == aug.charAt(i-1)){
                val = val + (aug.charAt(i) > '0' ? 1 : -1);
            } else {
                list.add(val);
                val = aug.charAt(i) > '0' ? 1 : -1;
            }
        }
        list.add(val);
        int ans = 0;
        ans = list.stream().filter(a -> a > 0).reduce(Integer::sum).get() - 2;
        int maxBlockChange = 0;
        for(int i = 0; i < list.size() - 4; i++){
            int curr = list.get(i);
            int n1 = list.get(i+1);
            int n2 = list.get(i+2);
            int n3 = list.get(i+3);
            int n4 = list.get(i+4);

            if(curr > 0 && n2 > 0 && n4 > 0
                    && n1 < 0 && n3 < 0){
                int possible = Math.abs(n1) + Math.abs(n3);
                maxBlockChange = Math.max(possible, maxBlockChange);
            }
        }
        return ans + maxBlockChange;
    }

    static void main() {
        int ans = new LC_21_3499_MaximizeActiveSectionWithTradeI().maxActiveSectionsAfterTrade("111110011001001001");
        System.out.println(ans);
    }
}
