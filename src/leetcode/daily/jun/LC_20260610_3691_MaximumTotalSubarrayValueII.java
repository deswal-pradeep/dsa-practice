package leetcode.daily.jun;

import java.util.PriorityQueue;

public class LC_20260610_3691_MaximumTotalSubarrayValueII {
    static class SparseTree {
        int[][] sparse;
        int type;
        public SparseTree(int[] arr, int type){
            this.type = type;
            buildTree(arr, type);
        }

        private void buildTree(int[] arr, int type) {
            int n = arr.length;
            int k = (int)(Math.log(n)/Math.log(2));
            sparse = new int[k+1][n];
            sparse[0] = arr;

            for(int j = 1; j <= k; j++){
                for(int i = 0; i+(1 << j) <= n; i++){
                    if(type == 0){
                        sparse[j][i] = Math.min(sparse[j-1][i], sparse[j-1][i+(1 << (j-1))]);
                    } else {
                        sparse[j][i] = Math.max(sparse[j-1][i], sparse[j-1][i+(1 << (j-1))]);
                    }
                }
            }
        }

        int query(int l, int r){
            int count = r - l + 1;
            int k = (int)(Math.log(count)/Math.log(2));
            int ans = 0;
            if(type == 0){
                ans = Math.min(sparse[k][l], sparse[k][r-(1<<k)+1]);
            } else {
                ans = Math.max(sparse[k][l], sparse[k][r-(1<<k)+1]);
            }
            return ans;
        }
    }
    public long maxTotalValue(int[] nums, int k) {
        SparseTree minTree = new SparseTree(nums, 0);
        SparseTree maxTree = new SparseTree(nums, 1);
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int l = 0; l < n; l++) {
            pq.offer(
                    new int[] {
                            maxTree.query(l, n-1) - minTree.query(l, n-1),
                            l,
                            n - 1,
                    }
            );
        }
        long ans = 0;
        while(k-- > 0){
            int[] top = pq.poll();
            ans += top[0];
            int l = top[1];
            int r = top[2];
            if (r > l) {
                pq.offer(
                        new int[] {
                                maxTree.query(l, r-1) - minTree.query(l, r-1),
                                l,
                                r - 1,
                        }
                );
            }
        }
        return ans;
    }
    static void main() {
        long ans = new LC_20260610_3691_MaximumTotalSubarrayValueII()
                .maxTotalValue(new int[]{4, 2, 5, 1}, 3);
        System.out.println(ans);
    }
}
