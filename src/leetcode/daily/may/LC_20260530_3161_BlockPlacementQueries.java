package leetcode.daily.may;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

//https://leetcode.com/problems/block-placement-queries/description/?envType=daily-question&envId=2026-05-30
public class LC_20260530_3161_BlockPlacementQueries {
    private static class SegmentTree{
        int[] a;
        int[] tree;

        SegmentTree(int[] a){
            this.a = a;
            this.tree = new int[a.length*4];
            buildTree(0, 0, a.length-1);
        }

        void buildTree(int ind, int l , int r){
            if(l == r){
                tree[ind] = a[l];
                return;
            }
            int mid = (l + r) / 2;
            buildTree(2 * ind + 1, l, mid);
            buildTree(2 * ind + 2, mid+1, r);
            tree[ind] = Math.max(tree[2 * ind + 1], tree[2 * ind + 2]);
        }

        void update(int i, int val){
            update(i, val, 0, a.length-1, 0);
        }

        void update(int i, int val, int l, int r, int ind){
            if(l == r){
                tree[ind] = val;
                return;
            }
            int mid = (l + r) / 2;
            if(i <= mid)
                update(i, val, l, mid, ind*2+1);
            else
                update(i, val, mid+1, r, ind*2+2);
            tree[ind] = Math.max(tree[ind*2+1], tree[ind*2+2]);
        }

        int queryRange(int r1, int r2){
            return query(r1, r2, 0, a.length-1, 0);
        }

        int query(int r1, int r2, int l, int r,  int ind){
            //there are three case
            if(l >= r1 && r <= r2){
                //fully inside
                return tree[ind];
            } else if (l > r2 || r < r1){
                return -1;
            } else {
                //node range is partial
                int mid = (l + r) / 2;
                int left = query(r1, r2, l, mid, 2 * ind + 1);
                int right = query(r1, r2, mid+1, r, 2 * ind + 2);
                return Math.max(left, right);
            }
        }
    }

    public List<Boolean> getResults(int[][] queries) {
        List<Boolean> list = new ArrayList<>();
        int maxIndex = 0;
        for(int[] q : queries){
            maxIndex = Math.max(maxIndex, q[1]);
        }
        int[] a = new int[maxIndex+2];
        a[a.length-1] = a.length-1;
        SegmentTree segmentTree = new SegmentTree(a);
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        set.add(a.length-1);
        for(int[] q : queries){
            if(q[0] == 1){
                //type1 query
                int higher = set.higher(q[1]);
                int lower = set.lower(q[1]);
                a[higher] = higher - q[1];
                segmentTree.update(higher, higher - q[1]);
                a[q[1]] = q[1] - lower;
                segmentTree.update(q[1], q[1] - lower);
                set.add(q[1]);
            } else {
                //type 2 query
                int lower = set.lower(q[1]);
                int segMax = segmentTree.queryRange(0, lower);
                segMax = segMax == Integer.MAX_VALUE ? -1 : segMax;
                int value = Math.max(segMax, q[1] - lower);
                list.add(value >= q[2]);
            }
        }
        return list;
    }

    static void main() {
        //int[][] queries = new int[][]{{1,2},{2,3,3},{2,3,1},{2,2,2}};
        //int[][] queries = new int[][]{{1,2},{1,30},{1,28},{1,46},{1,11},{1,23},{1,37},{1,130},{1,128},{1,13},{1,102},{1,105},{1,7},{1,144},{1,50},{1,142},{1,55},{2,109,46}};
        int[][] queries = new int[][]{{1,2}, {1,30}, {2,123,118}, {2,160,118}};
        List<Boolean> results = new LC_20260530_3161_BlockPlacementQueries()
                .getResults(queries);
        System.out.println(results);
    }
}
