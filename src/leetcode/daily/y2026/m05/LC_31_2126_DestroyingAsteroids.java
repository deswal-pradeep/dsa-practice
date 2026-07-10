package leetcode.daily.y2026.m05;

import java.util.Arrays;

public class LC_31_2126_DestroyingAsteroids {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long pMass = mass;
        for(int ast : asteroids){
            if(pMass < ast)
                return false;
            pMass += ast;
        }
        return true;
    }

    static void main() {
        boolean result = new LC_31_2126_DestroyingAsteroids()
                .asteroidsDestroyed(10, new int[]{3, 9, 19, 5, 21});
        System.out.println(result);
    }
}
