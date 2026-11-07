package leetcode.daily.y2026.m11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LCL_07_1882_ProcessTasksUsingServers {
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> pool = new PriorityQueue<>((a, b) -> {
            if(a[1] != b[1]) return a[1] - b[1];
            else return a[0] - b[0];
        });
        PriorityQueue<int[]> poolUsed = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        for(int i = 0; i < servers.length; i++)
            pool.offer(new int[]{i, servers[i], 0});
        int[] ans = new int[tasks.length];
        int j = 0;
        int currTask = 0;
        while(currTask < tasks.length){
            while(!poolUsed.isEmpty() && poolUsed.peek()[2] == j){
                pool.offer(poolUsed.poll());
            }
            if(!pool.isEmpty()){
                int[] s = pool.poll();
                ans[currTask] = s[0];
                s[2] = Math.max(s[2], j) + tasks[currTask];
                poolUsed.offer(s);
                currTask++;
                if(j < currTask)
                    j++;
            } else {
                j = poolUsed.peek()[2];
            }
        }
        return ans;
    }

    static void main() {
        int[] ints = new LCL_07_1882_ProcessTasksUsingServers().assignTasks(new int[]{5,1,4,3,2}, new int[]{2,1,2,4,5,2,1});
        System.out.println(Arrays.toString(ints));
    }
}
