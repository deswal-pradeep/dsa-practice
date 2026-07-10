package leetcode.daily.y2026.m07;

import java.util.*;

//https://leetcode.com/problems/find-the-safest-path-in-a-grid/description/?envType=daily-question&envId=2026-07-01
public class LC_01_2812_FindTheSafestPathInAGrid {
    int n;
    int[][] moves = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        n = grid.size();
        int[][] md = new int[n][n];
        Queue<int[]> tQueue = new LinkedList<>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(grid.get(i).get(j) == 1){
                    tQueue.add(new int[]{i, j});
                    md[i][j] = 0;
                } else {
                    md[i][j] = -1;
                }
            }
        }
        while(!tQueue.isEmpty()){
            int[] ele = tQueue.poll();
            for(int[] move : moves){
                int newX = ele[0] + move[0];
                int newY = ele[1] + move[1];
                if(isValid(newX , newY)
                        && md[newX][newY] == -1){
                    md[newX][newY] = md[ele[0]][ele[1]] + 1;
                    tQueue.add(new int[]{newX, newY});
                }
            }
        }

        //minimum safety is 0, maximum safety is 2 * n;
        int left = 0;
        int right = 2 * n;
        int ans = 0;
        //finding upper bound
        while(left <= right){
            int mid = (left + right) / 2;
            boolean possible = bfsWithV(md, mid);
            if(possible){
                ans = mid;
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return ans;
    }

    boolean bfsWithV(int[][] md, int v){
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        if(md[0][0] >= v){
            queue.add(new int[]{0, 0});
            visited[0][0] = true;
        }
        while(!queue.isEmpty()){
            int[] ele = queue.poll();
            if(ele[0] == n-1 && ele[1] == n-1){
                return true;
            }
            for(int[] move : moves){
                int newX = ele[0] + move[0];
                int newY = ele[1] + move[1];
                if(isValid(newX, newY)
                        && !visited[newX][newY]
                        && md[newX][newY] >= v){
                    visited[newX][newY] = true;
                    queue.add(new int[]{newX, newY});
                }
            }
        }
        return false;
    }

    int[] bfs(int i, int j, List<List<Integer>> grid){
        //if not found you have 4 ways to go everytime but it should not be visited
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        visited[i][j] = true;
        queue.add(new int[]{i, j});
        while(!queue.isEmpty()){
            int[] ele = queue.poll();
            if(grid.get(ele[0]).get(ele[1]) == 1){
                return ele;
            }
            int[][] moves = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
            for(int[] move : moves){
                int newX = ele[0] + move[0];
                int newY = ele[1] + move[1];
                if(isValid(newX, newY)
                        && !visited[newX][newY]){
                    visited[newX][newY] = true;
                    queue.add(new int[]{newX, newY});
                }
            }
        }
        return null;
    }

    boolean isValid(int x, int y){
        return x >=0 && x < n && y >=0 && y < n;
    }

    static void main() {
        List<List<Integer>> grid = new ArrayList<>();
        grid.add(Arrays.asList(0,0,0,1));
        grid.add(Arrays.asList(0,0,0,0));
        grid.add(Arrays.asList(0,0,0,0));
        grid.add(Arrays.asList(1,0,0,0));
        int ans = new LC_01_2812_FindTheSafestPathInAGrid().maximumSafenessFactor(grid);
        System.out.println(ans);
    }
}
