package leetcode.daily.y2026.m11;

public class LC_26_3360_StoneRemovalGame {
    public boolean canAliceWin_simulation(int n) {
        int take = 10,
                k = 0;
        while (n >= take) {
            n -= take;
            take--;
            k++;
        }
        return k % 2 == 1;
    }

    public boolean canAliceWin(int n) {
        int k = (int) ((21 - Math.sqrt(441 - 8 * n)) / 2);
        return k % 2 == 1;
    }
}
