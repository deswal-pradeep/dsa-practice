package amazon;

import java.util.*;

public class LC_863_AllNodesDistanceKInBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    static class Pair {
        TreeNode node;
        int d;
        Pair(TreeNode node, int d){
            this.node = node;
            this.d = d;
        }
    }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        Map<TreeNode, TreeNode> parents = new HashMap<>();
        dfs(root, null, parents);
        Queue<Pair> queue = new LinkedList<>();
        Map<TreeNode, Boolean> visited = new HashMap<>();
        queue.offer(new Pair(target, 0));
        visited.put(target, true);
        while(!queue.isEmpty()){
            Pair pair = queue.poll();
            TreeNode curr = pair.node;
            if(pair.d == k){
                ans.add(curr.val);
                continue;
            }
            TreeNode parentCurr = parents.get(curr);
            if(parentCurr != null && !visited.getOrDefault(parentCurr, false)){
                queue.offer(new Pair(parentCurr, pair.d+1));
                visited.put(parentCurr, true);
            }
            if(curr.left != null && !visited.getOrDefault(curr.left, false)){
                queue.offer(new Pair(curr.left, pair.d+1));
                visited.put(curr.left, true);
            }
            if(curr.right != null && !visited.getOrDefault(curr.right, false)){
                queue.offer(new Pair(curr.right, pair.d+1));
                visited.put(curr.right, true);
            }
        }
        return ans;
    }

    void dfs(TreeNode curr, TreeNode prev, Map<TreeNode, TreeNode> parents){
        if(curr == null)
            return;
        parents.put(curr, prev);
        if(curr.left != null){
            dfs(curr.left, curr, parents);
        }
        if(curr.right != null){
            dfs(curr.right, curr, parents);
        }
    }
}
