package leetcode.daily.y2026.m05;

import java.util.Arrays;

//https://leetcode.com/problems/minimum-initial-energy-to-finish-tasks/description/?envType=daily-question&envId=2026-05-12
public class LC_20260512_1665_MinimumInitialEnergyToFinishTasks {
    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - a[0] - (b[1] - b[0]));
        int ans = 0;
        for (int[] task : tasks) {
            ans = Math.max(ans + task[0], task[1]);
        }
        return ans;
    }

    public int minimumEffort_1(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> {return (b[1]-b[0]) - (a[1]-a[0]);});
        int left = 1;
        int right = 0;
        for(int i = 0; i < tasks.length; i++)
            right += tasks[i][1];
        return binary(tasks, left, right);
    }

    int binary(int[][] tasks, int left, int right){
        int ans = right;
        while(left <= right){
            int mid = (left + right) / 2;
            if(!isPossible(tasks, mid)){
                left = mid+1;
            } else {
                right = mid-1;
                ans = mid;
            }
        }
        return ans;
    }

    boolean isPossible(int[][] tasks, int val){
        for(int i = 0; i < tasks.length; i++){
            if(val < tasks[i][1]){
                return false;
            }
            val = val - tasks[i][0];
        }
        return true;
    }

    static void main() {
        int[][] tasks = {{1,3},{2,4},{10,11},{10,12},{8,9}};
        int ans = new LC_20260512_1665_MinimumInitialEnergyToFinishTasks().minimumEffort(tasks);
        System.out.println(ans);
    }
}
