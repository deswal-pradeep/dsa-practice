package leetcode.daily.y2026.m09;

//https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/?envType=daily-question&envId=2026-09-10
public class LC_10_2265_CountNodesEqualToAverageOfSubtree {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public int averageOfSubtree(TreeNode root) {
        int[] ans = new int[]{0};
        count(root, ans);
        return ans[0];
    }

    int[] count(TreeNode node, int[] ans){
        if(node == null){
            return new int[]{0, 0};
        }
        int val = node.val;
        int[] leftCount = count(node.left, ans);
        int[] rightCount = count(node.right, ans);
        int sum = val + leftCount[0] + rightCount[0];
        int count = 1 + leftCount[1] + rightCount[1];
        int average = sum / count;
        if(average == val)
            ans[0]++;
        return new int[]{sum, count};
    }
}
