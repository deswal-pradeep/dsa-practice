package leetcode.daily.y2026.m09;

import java.util.Arrays;

public class LC_22_3525_FindXValueOfArrayII {
    static class SegmentTree {
        int[] arr;
        int n, k;
        int[][] tree;
        SegmentTree(int[] arr, int k){
            this.n = arr.length;
            this.k = k;
            tree = new int[arr.length * 4][k+1];
            this.arr = arr;
            buildTree(0, 0, n-1);
        }

        private void buildTree(int ind, int left, int right){
            if(left == right){
                makeLeaf(ind, left);
                return;
            }
            int mid = (left + right) / 2;
            buildTree(2 * ind + 1, left, mid);
            buildTree(2 * ind + 2, mid+1, right);
            merge(tree[2 * ind + 1], tree[2 * ind + 2], tree[ind]);
        }

        private void merge(int[] leftNode, int[] rightNode, int[] result){
            if(leftNode == null){
                result[k] = rightNode[k];
                System.arraycopy(rightNode, 0, result, 0, k);
            } else if (rightNode == null){
                result[k] = leftNode[k];
                System.arraycopy(leftNode, 0, result, 0, k);
            } else {
                int r1 = leftNode[k];
                int r2 = rightNode[k];
                int r = (r1 * r2) % k;
                result[k] = r;
                for(int i = 0; i < k; i++){
                    result[i] = leftNode[i];
                }
                for(int i = 0; i < k; i++){
                    int r3 = (r1 * i) % k;
                    result[r3] += rightNode[i];
                }
            }
        }

        private void makeLeaf(int ind, int left) {
            Arrays.fill(tree[ind], 0);
            int val = arr[left] % k;
            tree[ind][val] = 1;
            tree[ind][k] = val;
        }

        void update(int i, int value){
            arr[i] = value;
            update(0, 0, n-1, i);
        }
        void update(int ind, int left, int right, int index){
            if(left == right){
                makeLeaf(ind, left);
                return;
            }
            int mid = (left + right) / 2;
            if(index <= mid){
                update(2 * ind + 1, left, mid, index);
            } else {
                update(2 * ind + 2, mid+1, right, index);
            }
            merge(tree[2 * ind + 1], tree[2 * ind + 2], tree[ind]);
        }

        int[] query(int L, int R){
            return queryTree(0, 0, n-1, L, R);
        }
        int[] queryTree(int ind, int l, int r, int L, int R){
            //when fully inside
            if(L <= l && r <= R){
                return tree[ind];
            }
            //when fully outside
            else if (l > R || r < L) {
                return null;
            } else {
                int mid = (l + r) / 2;
                int[] result = new int[k+1];
                int[] leftNode = queryTree(2 * ind + 1, l, mid, L, R);
                int[] rightNode = queryTree(2 * ind + 2, mid+1, r, L, R);
                merge(leftNode, rightNode, result);
                return result;
            }
        }
    }
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        SegmentTree tree = new SegmentTree(nums, k);
        int[] ans = new int[queries.length];
        int i = 0;
        for(int[] query : queries){
            tree.update(query[0], query[1]);
            int[] result = tree.query(query[2], nums.length-1);
            ans[i++] = result[query[3]];
        }
        return ans;
    }

    static void main() {
        int[] nums = new int[]{1,2,3,4,5};
        int k = 3;
        int[][] queries = new int[][]{{2,2,0,2}, {3,3,3,0}, {0,1,0,1}};
        int[] ints = new LC_22_3525_FindXValueOfArrayII().resultArray(nums, k, queries);
        System.out.println(Arrays.toString(ints));
    }
}
