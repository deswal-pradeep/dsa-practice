package amazon;

import java.util.Arrays;

public class Robots {


    static void main() {
        int[] pos = new int[]{0, 2, 4, 7, 9, 12, 15};
        int[] ranges = new int[]{6, 3, 10, 1, 5, 5, 0};
        int[] costs = new int[]{2, 3, 1, 0, 1, 4, 0};
        int minCosts = new Robots().findMinCosts(pos, ranges, costs);
        System.out.println(minCosts);
    }

    private int findMinCosts(int[] pos, int[] ranges, int[] costs) {
        return 0;
    }
}
