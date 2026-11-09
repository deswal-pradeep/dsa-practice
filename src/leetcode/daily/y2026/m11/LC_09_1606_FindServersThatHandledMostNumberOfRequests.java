package leetcode.daily.y2026.m11;

import java.util.*;

public class LC_09_1606_FindServersThatHandledMostNumberOfRequests {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        List<Integer> list = new LinkedList<>();
        int maxCount = 0;
        TreeMap<Integer, int[]> available = new TreeMap<>();
        PriorityQueue<int[]> used = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for(int i = 0; i < k; i++){
            available.put(i, new int[]{i, 0, 0});
            //index, busy until, taskCount
        }
        int[][] tasks = new int[arrival.length][2];
        for(int i = 0; i < arrival.length; i++){
            tasks[i] = new int[]{arrival[i], load[i]};
        }
        //Arrays.sort(tasks, Comparator.comparingInt(a -> a[0]));
        for(int i = 0; i < arrival.length; i++){
            int[] task = tasks[i];

            while(!used.isEmpty() && used.peek()[1] <= task[0]){
                int[] server = used.poll();
                available.put(server[0], server);
            }

            if(available.isEmpty()){
                continue; //drop the task
            }
            //index needed
            int index = i % k;
            Integer ceilingKey = available.ceilingKey(index);
            Integer neededKey = ceilingKey != null ? ceilingKey : available.ceilingKey(0);
            int[] server = available.get(neededKey);
            server[1] = task[0] + task[1];
            server[2] += 1;
            if (maxCount < server[2]) {
                list = new LinkedList<>();
                maxCount = server[2];
            }

            if (maxCount == server[2]) {
                list.add(server[0]);
            }
            available.remove(server[0]);
            used.offer(server);
        }
        return list;
    }

    static void main() {
        List<Integer> integers = new LC_09_1606_FindServersThatHandledMostNumberOfRequests()
                .busiestServers(7, new int[]{1,3,4,5,6,11,12,13,15,19,20,21,23,25,31,32},
                        new int[]{9,16,14,1,5,15,6,10,1,1,7,5,11,4,4,6});
        System.out.println(integers);
    }
}
