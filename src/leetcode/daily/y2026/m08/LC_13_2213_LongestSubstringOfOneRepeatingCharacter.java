package leetcode.daily.y2026.m08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC_13_2213_LongestSubstringOfOneRepeatingCharacter {
    static class Node {
        char leftChar;
        char rightChar;

        int prefix;
        int suffix;
        int max;
        int len;

        Node(char c) {
            leftChar = rightChar = c;
            prefix = suffix = max = len = 1;
        }

        Node(char leftChar, char rightChar,
             int prefix, int suffix, int max, int len) {
            this.leftChar = leftChar;
            this.rightChar = rightChar;
            this.prefix = prefix;
            this.suffix = suffix;
            this.max = max;
            this.len = len;
        }
    }

    static class SegmentTree {
        Node[] tree;
        char[] arr;
        int n;

        SegmentTree(char[] arr) {
            this.arr = arr;
            this.n = arr.length;
            tree = new Node[4 * n];

            build(0, 0, n - 1);
        }

        void build(int node, int l, int r) {
            if (l == r) {
                tree[node] = new Node(arr[l]);
                return;
            }

            int mid = l + (r - l) / 2;

            build(2 * node + 1, l, mid);
            build(2 * node + 2, mid + 1, r);

            tree[node] = merge(
                    tree[2 * node + 1],
                    tree[2 * node + 2]
            );
        }

        void update(int index, char c) {
            update(0, 0, n - 1, index, c);
        }

        void update(int node, int l, int r, int index, char c) {
            if (l == r) {
                arr[l] = c;
                tree[node] = new Node(c);
                return;
            }

            int mid = l + (r - l) / 2;

            if (index <= mid) {
                update(2 * node + 1, l, mid, index, c);
            } else {
                update(2 * node + 2, mid + 1, r, index, c);
            }

            tree[node] = merge(
                    tree[2 * node + 1],
                    tree[2 * node + 2]
            );
        }

        Node merge(Node left, Node right) {

            char leftChar = left.leftChar;
            char rightChar = right.rightChar;

            int prefix = left.prefix;
            int suffix = right.suffix;

            int max = Math.max(left.max, right.max);

            if (left.rightChar == right.leftChar) {

                // Entire left segment has same character
                if (left.prefix == left.len) {
                    prefix = left.len + right.prefix;
                }

                // Entire right segment has same character
                if (right.suffix == right.len) {
                    suffix = right.len + left.suffix;
                }

                // Join suffix of left + prefix of right
                max = Math.max(max, left.suffix + right.prefix);
            }

            return new Node(
                    leftChar,
                    rightChar,
                    prefix,
                    suffix,
                    max,
                    left.len + right.len
            );
        }

        int getMax() {
            return tree[0].max;
        }
    }

    public int[] longestRepeating(
            String s,
            String queryCharacters,
            int[] queryIndices) {

        int q = queryIndices.length;
        int[] ans = new int[q];

        SegmentTree tree = new SegmentTree(s.toCharArray());

        for (int i = 0; i < q; i++) {
            tree.update(
                    queryIndices[i],
                    queryCharacters.charAt(i)
            );

            ans[i] = tree.getMax();
        }

        return ans;
    }

    static void main() {
        int[] ints = new LC_13_2213_LongestSubstringOfOneRepeatingCharacter()
                .longestRepeating("abyzz", "aa", new int[]{2, 1});
        System.out.println(Arrays.toString(ints));
    }
}
