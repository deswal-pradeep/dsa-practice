package leetcode.daily.y2026.m06;

//https://leetcode.com/problems/number-of-zigzag-arrays-ii/description/?envType=daily-question&envId=2026-06-24
public class LC_24_3700_NumberOfZigZagArraysII {
    static int MOD = 1_000_000_007;
    long[][] power(long[][] base, long exp) {
        int n = base.length;
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++)
            res[i][i] = 1;
        while (exp > 0) {
            if ((exp & 1) == 1)
                res = multiply(res, base);
            base = multiply(base, base);
            exp >>= 1;
        }
        return res;
    }

    long[][] multiply(long[][] a, long[][] b) {
        int rows = a.length;
        int common = a[0].length;
        int cols = b[0].length;
        long[][] res = new long[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < common; k++) {
                for (int j = 0; j < cols; j++) {
                    res[i][j] += a[i][k] * b[k][j];
                    res[i][j] %= MOD;
                }
            }
        }
        return res;
    }

    public int zigZagArrays(int n, int l, int r){
        int m = r - l + 1;
        long[][] base = new long[m][m];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < i; j++){
                base[i][m-j-1] = 1;
            }
        }
        long[][] tPowered = power(base, n-2);
        long[][] basic = new long[m][1];
        for(int i = 0; i < m; i++){
            basic[i][0] = i;
        }
        long[][] answerMatrix = multiply(tPowered, basic);
        long ans = 0;
        for(int i = 0; i < m; i++){
            ans += answerMatrix[i][0];
            ans %= MOD;
        }
        ans = (ans * 2) % MOD;
        return (int)ans;
    }
    public int zigZagArrays_1(int n, int l, int r) {
        long[] prev = new long[r+1];
        long[] curr = new long[r+1];

        int z = 0;
        //for n = 2 set all values
        for(int i = l; i <= r; i++) {
            prev[i] = z;
            z++;
        }

        for(int k = 3; k <= n; k++){
            long count = 0;
            for(int i = l; i <= r; i++) {
                curr[i] = count;
                count = count + prev[r-i+l];
                count %= MOD;
            }
            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        long ans = 0;
        for(int i = l; i <= r ; i++){
            ans = ans + prev[i];
            ans %= MOD;
        }
        ans = (ans * 2) % MOD;
        return (int) ans;
    }
    
    static void main() {
        int ans = new LC_24_3700_NumberOfZigZagArraysII()
                .zigZagArrays(20, 1, 7);
        System.out.println(ans);
    }
}
