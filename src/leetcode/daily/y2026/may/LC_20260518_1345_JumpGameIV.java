package leetcode.daily.y2026.may;

import java.util.*;

public class LC_20260518_1345_JumpGameIV {
    public int minJumps(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            List<Integer> list = map.computeIfAbsent(arr[i], k -> {return new ArrayList<>();});
            list.add(i);
        }
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[arr.length];
        visited[0] = true;
        queue.add(new int[]{0, 0});
        while(!queue.isEmpty()){
            int[] node = queue.poll();
            int index = node[0];
            int steps = node[1];
            if(index == arr.length-1)
                return node[1];
            List<Integer> list = map.get(arr[index]);
            for(int idx : list){
                if(idx != index && !visited[idx]){
                    visited[idx] = true;
                    queue.add(new int[]{idx, steps+1});
                }

            }
            list.clear();
            if(index+1 < arr.length && !visited[index+1]){
                visited[index+1] = true;
                queue.add(new int[]{index+1, steps+1});
            }
            if(index-1 > 0 && !visited[index-1]){
                visited[index-1] = true;
                queue.add(new int[]{index-1, steps+1});
            }
        }
        return -1;
    }

    static void main() {
        int ans = new LC_20260518_1345_JumpGameIV()
                .minJumps(new int[]{100,-23,-23,404,100,23,23,23,3,404});
        System.out.println(ans);
    }

}
