package datastructures;

public class SegmentTree {
    int[] a;
    int[] tree;
    public SegmentTree(int[] a){
        this.a = a;
        tree = new int[a.length * 4];
        buildTree(0, 0, a.length-1);
    }
    void buildTree(int ind, int l, int r){
        if(l == r){
            tree[ind] = a[l];
            return;
        }
        int mid = (l + r) / 2;
        buildTree(2 * ind + 1, l, mid);
        buildTree(2 * ind + 2, mid+1, r);
        tree[ind] = Math.min(tree[2 * ind + 1], tree[2 * ind + 2]);
    }
    int queryRange(int r1, int r2){
        if(r1 < 0 || r2 >= a.length)
            throw new IllegalArgumentException();
        return query(r1, r2, 0, a.length-1, 0);
    }
    int query(int r1, int r2, int l, int r, int ind){
        if(l >= r1 && r <= r2){
            //node range fully inside
            return tree[ind];
        } else if (r < r2 || l > r1) {
            //node range fully outside
            return Integer.MAX_VALUE;
        } else {
            //node range is partial
            int mid = (l + r) / 2;
            int left = query(r1, r2, l, mid, 2 * ind + 1);
            int right = query(r1, r2, mid+1, r, 2 * ind + 2);
            return Math.min(left, right);
        }
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
            update(i, val, l, mid, 2 * ind + 1);
        else
            update(i, val, mid+1, r, 2 * ind + 2);
        tree[ind] = Math.min(tree[2 * ind + 1], tree[2 * ind + 2]);
    }

    static void main() {
        int[] a = {1 , 3, 4, 0, 2, 5, 6};
        SegmentTree segmentTree = new SegmentTree(a);
        System.out.println(segmentTree.queryRange(2, 4));
        segmentTree.update(3, 3);
        segmentTree.update(0, 2);
        System.out.println(segmentTree.queryRange(0, 6));

    }
}
