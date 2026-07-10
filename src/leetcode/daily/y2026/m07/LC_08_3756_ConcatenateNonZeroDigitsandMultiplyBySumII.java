package leetcode.daily.y2026.m07;

import java.util.Arrays;

//https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum-ii/description/?envType=daily-question&envId=2026-07-08
public class LC_08_3756_ConcatenateNonZeroDigitsandMultiplyBySumII {
    static int MOD = 1_000_000_007;
    static class SegmentTree{
        int[] sum;
        long[] val;
        int[] len;
        int[] arr;
        int[] pow;
        SegmentTree(int[] arr){
            sum = new int[arr.length*4];
            val = new long[arr.length*4];
            len = new int[arr.length*4];
            pow = new int[arr.length+1];
            pow[0] = 1;
            for(int i = 1; i < pow.length; i++){
                pow[i] = (int)((1l * pow[i-1] * 10)%MOD);
            }
            this.arr = arr;
            buildTree(0, 0, arr.length-1);
        }

        private void buildTree(int ind, int low, int high){
            if(low == high){
                sum[ind] = arr[low];
                val[ind] = arr[low];
                len[ind] = arr[low] == 0 ? 0 : 1;
                return;
            }
            int mid = (low + high) >> 1;
            buildTree(ind*2+1, low, mid);
            buildTree(ind*2+2, mid+1, high);
            sum[ind] = sum[ind*2+1] + sum[ind*2+2];
            val[ind] = ((val[ind*2+1] * pow[len[ind*2+2]]) % MOD) + val[ind*2+2];
            val[ind] %= MOD;
            len[ind] = len[ind*2+1] + len[ind*2+2];
        }

        private int[] queryRange(int l, int r){
            return query(0, 0, arr.length-1, l, r);
        }

        private int[] query(int ind, int low, int high, int l, int r){
            if(l <= low && r >= high){
                //fully inside
                return new int[]{sum[ind], (int)val[ind], len[ind]};
            } else if (r < low || l > high){
                //fully outside
                return new int[]{0, 0, 0};
            } else {
                int mid = (low + high) >> 1;
                int[] left = query(ind*2+1, low, mid, l, r);
                int[] right = query(ind*2+2, mid+1, high, l, r);
                int sum = left[0] + right[0];
                int len = left[2] + right[2];
                long val = (1l * left[1] * pow[right[2]]) + right[1];
                val %= MOD;
                return new int[]{sum,  (int)val, len};
            }
        }
    }
    public int[] sumAndMultiply(String s, int[][] queries) {
        int[] pow = new int[s.length()+1];
        int[] sum = new int[s.length()+1];
        int[] cnt = new int[s.length()+1];
        long[] x = new long[s.length()+1];
        pow[0] = 1;
        for(int i = 1; i < pow.length; i++){
            pow[i] = (int)((10L * pow[i-1]) % MOD);
        }

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            int d = c - '0';
            x[i+1] = d > 0 ?  ((x[i] * 10 + d) % MOD): x[i];
            sum[i+1] = sum[i] + d;
            cnt[i+1] = d > 0 ? cnt[i]+1 : cnt[i];
        }

        int[] ans = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            int[] query = queries[i];
            int l = query[0];
            int r = query[1] + 1;
            int len = cnt[r] - cnt[l];
            long valX  = ((((x[r] - ((x[l] * pow[len])%MOD)) + MOD) % MOD) * (sum[r] - sum[l])) % MOD;
            ans[i] = (int)valX;
        }
        return ans;
    }
    public int[] sumAndMultiply_1(String s, int[][] queries) {
        int[] arr = new int[s.length()];
        int[] ans = new int[queries.length];
        char[] charArr = s.toCharArray();
        for(int i = 0; i < arr.length; i++){
            arr[i] = charArr[i] - '0';
        }
        SegmentTree tree = new SegmentTree(arr);
        for(int i = 0; i < queries.length; i++){
            int[] query = queries[i];
            int[] result = tree.queryRange(query[0], query[1]);
            ans[i] = (int)((1l * result[0] * result[1])%MOD);
        }
        return ans;
    }


    public int[] sumAndMultiply_2(String s, int[][] queries){
        int[] ans = new int[queries.length];
        char[] arr = s.toCharArray();

        for(int i= 0; i < queries.length; i++){
            int[] query = queries[i];
            int l = query[0];
            int r = query[1];
            long val = 0;
            long sum = 0;
            for(int j = l; j <= r; j++){
                char c = arr[j];
                if(c != '0'){
                    val = val * 10;
                    val = val + (c - '0');
                    val %= MOD;
                    sum+=c - '0';
                }
            }
            ans[i] =  (int)((val * sum) % MOD);
        }
        return ans;
    }

    static void main() {
        int[] ans = new LC_08_3756_ConcatenateNonZeroDigitsandMultiplyBySumII()
                .sumAndMultiply("83653355979889175588", new int[][]{{0,19}});

        int[] ans1 = new LC_08_3756_ConcatenateNonZeroDigitsandMultiplyBySumII()
                .sumAndMultiply_2("83653355979889175588", new int[][]{{0,19}});
        System.out.println(Arrays.toString(ans));
        System.out.println(Arrays.toString(ans1));
    }
}
