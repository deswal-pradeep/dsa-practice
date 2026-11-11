package leetcode.daily.y2026.m11;

public class LC_11_3789_MinimumCostToAcquireRequiredItems {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long ans = 0;
        ans += ((long) Math.min(need1, need2) * Math.min(cost1 + cost2, costBoth));
        int newNeed1 = need1 - Math.min(need1, need2);
        int newNeed2 = need2 - Math.min(need1, need2);
        if(newNeed1 > 0){
            ans += ((long) newNeed1 * Math.min(cost1, costBoth));
        }
        if(newNeed2 > 0){
            ans += ((long) newNeed2 * Math.min(cost2, costBoth));
        }
        return ans;
    }

    static void main() {
        long l = new LC_11_3789_MinimumCostToAcquireRequiredItems()
                .minimumCost(3, 2, 1, 3, 2);
        System.out.println(l);
    }
}
