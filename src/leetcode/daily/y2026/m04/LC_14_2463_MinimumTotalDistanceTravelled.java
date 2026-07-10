package leetcode.daily.y2026.m04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LC_14_2463_MinimumTotalDistanceTravelled {
    //https://leetcode.com/problems/minimum-total-distance-traveled/?envType=daily-question&envId=2026-04-14
    static void main() {
        List<Integer> robots = Arrays.asList(0,4,6);
        int[][] factories = {{2,2}, {6,2}};
        long ans = new LC_14_2463_MinimumTotalDistanceTravelled().minimumTotalDistance(null, null);
        System.out.println(ans);
    }
    int rLength;
    int fLength;
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> {return a[0] - b[0];});
        List<Integer> factoryList = new ArrayList<>();
        for(int[] f : factory){
            for(int i = 0; i < f[1]; i++)
                factoryList.add(f[0]);
        }
        int rLength = robot.size();
        int fLength = factoryList.size();
        long[][] mem = new long[rLength][fLength];
        for(int i = 0; i < rLength; i++){
            Arrays.fill(mem[i], -1l);
        }
        return assign(0, 0, factoryList, robot, mem);
    }

    long assign(int rIndex, int fIndex, List<Integer> factory, List<Integer> robot, long[][] mem){
        if(rIndex == robot.size())
            return 0;
        if(fIndex == factory.size())
            return (long)1e12; // no factort left to assign

        if(mem[rIndex][fIndex] != -1)
            return mem[rIndex][fIndex];
        //pick this factory
        long pick = Math.abs(robot.get(rIndex) - factory.get(fIndex))
                + assign(rIndex+1, fIndex+1, factory, robot, mem);
        //don't pick this factory
        long notPick = assign(rIndex, fIndex+1, factory, robot, mem);
        mem[rIndex][fIndex] = Math.min(pick, notPick);
        return mem[rIndex][fIndex];
    }
}
