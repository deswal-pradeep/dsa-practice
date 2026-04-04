import java.util.*;

public class Main {
    int n, m;
    static class Stat {
        int robot;
        char dir;
        int canKill;
        Stat(int robot, int canKill, char dir){
            this.robot = robot;
            this.canKill = canKill;
            this.dir = dir;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            Stat stat = (Stat) o;
            return robot == stat.robot && dir == stat.dir;
        }

        @Override
        public int hashCode() {
            int result = robot;
            result = 31 * result + dir;
            return result;
        }
    }

    static void main() {
        int[][] coins = {{0,1,-1},{1,-2,3},{2,-3,4}};
        int ans = new Main().maximumAmount(coins);
        System.out.println(ans);
    }
    public int maximumAmount(int[][] coins) {
        m = coins.length;
        n = coins[0].length;
        Integer[][][] mem = new Integer[m][n][3];
        int[][] arr = coins;
        int[][][] dp = new int[m][n][3];
        for(int i = 0; i < 3; i++){
            dp[m-1][n-1][i] = (i > 0 && arr[m-1][n-1] < 0) ? 0 : arr[m-1][n-1];
        }
        for(int i = m-1; i>=0; i--){
            for(int j = n-1; j>=0; j--){
                for(int nCount = 2; nCount >=0; nCount--){
                    if(i==m-1 && j == n-1)
                        continue;
                    int maxPath = Integer.MIN_VALUE;
                    if(arr[i][j] < 0 && nCount > 0){
                        //if it is less than zero, robot may / may not neutralize
                        if(i+1 <= m-1)
                            maxPath = Math.max(maxPath, 0 + dp[i+1][j][nCount-1]);
                        if(j+1 <= n-1)
                            maxPath = Math.max(maxPath, 0 + dp[i][j+1][nCount-1]);
                    }
                    if(i+1 <= m-1)
                        maxPath = Math.max(maxPath, arr[i][j] + dp[i+1][j][nCount]);
                    if(j+1 <= n-1)
                        maxPath = Math.max(maxPath, arr[i][j] + dp[i][j+1][nCount]);
                    dp[i][j][nCount] = maxPath;
                }
            }
        }
        return dp[0][0][2];
        //return maxPath(coins, 0, 0, 2, mem);
    }

    int maxPath(int[][] arr, int i, int j, int nCount, Integer[][][] mem){
        if(i == m-1 && j == n-1)
            return nCount > 0 && arr[i][j] < 0 ? 0 : arr[i][j];

        if(mem[i][j][nCount] != null){
            return mem[i][j][nCount];
        }

        int maxPath = Integer.MIN_VALUE;
        if(arr[i][j] < 0 && nCount > 0){
            //if it is less than zero, robot may / may not neutralize
            if(i+1 <= m-1)
                maxPath = Math.max(maxPath, 0 + maxPath(arr, i+1, j, nCount-1, mem));
            if(j+1 <= n-1)
                maxPath = Math.max(maxPath, 0 + maxPath(arr, i, j+1, nCount-1, mem));
        }
        if(i+1 <= m-1)
            maxPath = Math.max(maxPath, arr[i][j] + maxPath(arr, i+1, j, nCount, mem));
        if(j+1 <= n-1)
            maxPath = Math.max(maxPath, arr[i][j] + maxPath(arr, i, j+1, nCount, mem));
        mem[i][j][nCount] = maxPath;
        return maxPath;
    }
}