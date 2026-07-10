package leetcode.daily.y2026.m06;

public class LC_20260602_3633_EarliestFinishTimeForLandAndWaterRidesI {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        //there are 2 picks, first pick can be from both, pick1 should be as soon as possible
        int landFirst = Integer.MAX_VALUE;
        int waterSecond = Integer.MAX_VALUE;
        int waterFirst = Integer.MAX_VALUE;
        int landSecond = Integer.MAX_VALUE;
        for(int i = 0; i < landStartTime.length; i++){
            landFirst = Math.min(landFirst, landStartTime[i] + landDuration[i]);
        }
        for(int i = 0; i < waterStartTime.length; i++){
            waterFirst = Math.min(waterFirst, waterStartTime[i] + waterDuration[i]);
        }

        for(int i = 0; i < landStartTime.length; i++){
            landSecond = Math.min(landSecond,
                    (Math.max(landStartTime[i], waterFirst))
                            + landDuration[i]);

        }

        for(int i = 0; i < waterStartTime.length; i++){
            waterSecond = Math.min(waterSecond,
                    (Math.max(waterStartTime[i], landFirst))
                            + waterDuration[i]);
        }
        return Math.min(landSecond, waterSecond);
    }

    static void main() {
        int ans = new LC_20260602_3633_EarliestFinishTimeForLandAndWaterRidesI()
                .earliestFinishTime(
                        new int[]{2,8}, new int[]{4,1},
                        new int[]{6}, new int[]{3});
        System.out.println(ans);

    }
}
