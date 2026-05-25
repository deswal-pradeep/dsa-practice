package leetcode.daily.may;

//https://leetcode.com/problems/jump-game-vii/?envType=daily-question&envId=2026-05-25
public class LC_20260525_1871_JumpGameVII {
    int n;
    public boolean canReach(String s, int minJump, int maxJump) {
        char[] arr = s.toCharArray();
        n = arr.length;
        if(arr[n-1] == '1' || arr[0] == '1')
            return false;

        int[] dp = new int[n];
        int[] pre = new int[n];
        dp[0] = 1;

        for(int i = 0; i < minJump; i++)
            pre[i] = 1;

        for(int i = minJump; i < n; i++){
            int left = i-maxJump;
            int right = i-minJump;
            if(arr[i] == '0'){
                int total = pre[right] - (left <= 0 ? 0 : pre[left - 1]);
                dp[i] = total != 0 ? 1 : 0;
            }
            pre[i] = pre[i - 1] + dp[i];
        }
        return dp[n-1] == 1;
    }

    boolean dfs(int ind, char[] arr, int minJump, int maxJump, int[] mem){
        if(ind == n-1){
            mem[ind] = 1;
            return true;
        }

        if(mem[ind] != -1){
            return mem[ind] == 1;
        }
        boolean result = false;
        for(int i = minJump; i <= maxJump; i++){
            if(i + ind < n && arr[i + ind] == '0'){
                result = result | dfs(i+ind, arr, minJump, maxJump, mem);
            }
        }
        mem[ind] = result ? 1 : 0;
        return result;
    }

    static void main() {
        StringBuilder builder = new StringBuilder("");
        for(int i = 0; i < 100000; i++)
                builder.append("0");

        boolean ans = new LC_20260525_1871_JumpGameVII()
                .canReach("0111111111111111111111111111111101111101111111111111111110",
                        5, 26);
        System.out.println(ans);
    }
}
