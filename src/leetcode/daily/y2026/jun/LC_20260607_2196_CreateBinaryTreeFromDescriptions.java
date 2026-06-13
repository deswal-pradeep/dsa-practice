package leetcode.daily.y2026.jun;

import java.util.HashMap;
import java.util.Map;

public class LC_20260607_2196_CreateBinaryTreeFromDescriptions {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Map<Integer, Integer> parentMap = new HashMap<>();

        for(int[] desc : descriptions){
            int parent = desc[0];
            int child = desc[1];
            int isLeft = desc[2];
            TreeNode parentNode = map.computeIfAbsent(parent, (key) -> new TreeNode(parent));
            TreeNode childNode = map.computeIfAbsent(child, (key) -> new TreeNode(child));
            parentMap.putIfAbsent(child, parent);
            if(isLeft == 1){
                parentNode.left = childNode;
            } else{
                parentNode.right = childNode;
            }
        }
        TreeNode root = null;
        for(Integer key : map.keySet()){
            //root does not any parent
            if(!parentMap.containsKey(key)){
                root = map.get(key);
            }
        }
        return root;
    }

    static void main() {
        int[][] arr = new int[][]{{20,15,1}, {20,17,0}, {50,20,1}, {50,80,0}, {80,19,1}};
        TreeNode ans = new LC_20260607_2196_CreateBinaryTreeFromDescriptions()
                .createBinaryTree(arr);
        System.out.println(ans);
    }
}
