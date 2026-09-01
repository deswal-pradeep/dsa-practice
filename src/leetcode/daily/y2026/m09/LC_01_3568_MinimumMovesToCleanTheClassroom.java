package leetcode.daily.y2026.m09;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class LC_01_3568_MinimumMovesToCleanTheClassroom {
    int m;
    int n;
    int initialEnergy;
    int[][] moves = new int[][]{{0,1}, {1, 0}, {0,-1}, {-1,0}};

    public int minMoves(String[] classroom, int energy) {
        m = classroom.length;
        n = classroom[0].length();
        //i, j, e, steps, mask(which L's has been picked)
        int[][] lPositions = new int[m][n];
        int fullMask = 0;
        int lPos = 1;

        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(classroom[i].charAt(j) == 'S'){
                    queue.offer(new int[]{i, j, energy, 0, 0});
                }
                if(classroom[i].charAt(j) == 'L'){
                    lPositions[i][j] = lPos++;
                    fullMask = fullMask | (1 << lPositions[i][j]);
                }
            }
        }
        int[][][] bestEnergy = new int[m][n][fullMask];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++)
                Arrays.fill(bestEnergy[i][j], -1);
        }
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int currEnergy = curr[2];
            int currSteps = curr[3];
            int currMask = curr[4];
            if(currMask == fullMask){
                return currSteps;
            }
            currEnergy = classroom[x].charAt(y) != 'R' ? currEnergy : energy;
            if(currEnergy == 0){
                continue;
            }
            if(bestEnergy[x][y][currMask] == -1
                    || bestEnergy[x][y][currMask] < currEnergy){
                bestEnergy[x][y][currMask] = currEnergy;
                for(int[] move : moves){
                    int newX = x + move[0];
                    int newY = y + move[1];
                    int newEnergy = currEnergy - 1;
                    if(newX >= 0 && newX < m && newY >= 0 && newY < n
                            && classroom[newX].charAt(newY) != 'X'){
                        int newMask = classroom[newX].charAt(newY) != 'L'
                                ? currMask
                                :  currMask | (1 << lPositions[newX][newY]);
                        queue.offer(new int[]{newX, newY, newEnergy,
                                currSteps+1, newMask});
                    }
                }
            } else {
                //already higher energy has been tried, for the same mask, so skipping it now
            }
        }
        return -1;
    }

    static void main() {
        int ans = new LC_01_3568_MinimumMovesToCleanTheClassroom().minMoves(new String[]{"S", "L", "R", "L"}, 2);
        System.out.println(ans);
    }
}
