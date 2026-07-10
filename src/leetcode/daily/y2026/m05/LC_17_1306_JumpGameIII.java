package leetcode.daily.y2026.m05;

//https://leetcode.com/problems/jump-game-iii/?envType=daily-question&envId=2026-05-17
public class LC_17_1306_JumpGameIII {
    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        return dfs(arr, start, visited);
    }

    boolean dfs(int[] arr, int ind, boolean[] visited){
        if(ind < 0 || ind >= arr.length){
            return false;
        }
        visited[ind] = true;
        if(arr[ind] == 0){
            return true;
        }
        boolean result = false;
        int left = ind - arr[ind];
        int right = ind + arr[ind];
        if(left >= 0 && left < arr.length && !visited[left])
            result = result | dfs(arr, left, visited);
        if(right >= 0 && right < arr.length && !visited[right])
            result = result | dfs(arr, right, visited);

        return result;
    }

    static void main() {
        boolean result = new LC_17_1306_JumpGameIII()
                .canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 5);
        System.out.println(result);
    }
}
