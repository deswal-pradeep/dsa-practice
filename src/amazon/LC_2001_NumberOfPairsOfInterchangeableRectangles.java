package amazon;

import java.util.HashMap;
import java.util.Map;

public class LC_2001_NumberOfPairsOfInterchangeableRectangles {
    public long interchangeableRectangles(int[][] rectangles) {
        Map<Double, Long> map = new HashMap<>();
        for(int[] r : rectangles){
            double d = r[0] * 1.0 / r[1];
            map.put(d, map.getOrDefault(d, 0L) + 1);
        }
        long ans = 0L;
        for(long val : map.values()){
            ans += (val - 1) * val / 2;
        }
        return ans;
    }
}
