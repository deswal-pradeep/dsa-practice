package leetcode.daily.y2026.m06;

import java.util.*;

public class LC_20260620_1840_MaximumBuildingHeight {
    static class Node {
        int index;
        int height;

        Node(int[] r){
            index = r[0];
            height = r[1];
        }

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", height=" + height +
                    '}';
        }
    }
    public int maxBuilding(int n, int[][] restrictions){
        List<Node> nodes = new ArrayList<>(restrictions.length+2);
        int i = 0;
        boolean isLastInRes = false;
        for(int[] r : restrictions){
            nodes.add(new Node(r));
            if(r[0] == n)
                isLastInRes = true;
        }
        nodes.add(new Node(new int[]{1, 0}));
        if(!isLastInRes)
            nodes.add(new Node(new int[]{n, n-1}));
        Collections.sort(nodes, Comparator.comparingInt(a -> a.index));

        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for(int j = 0; j < nodes.size(); j++){
            Node node = nodes.get(j);
            if(node.index > 1)
                heap.add(new int[]{node.index, node.height, -1, j});
            if(node.index < n)
                heap.add(new int[]{node.index, node.height, 1, j});
        }

        while(!heap.isEmpty()){
            int[] ele = heap.poll();
            if(ele[2] == -1){
                //left
                int currIndex = ele[3];
                Node node = nodes.get(currIndex);
                Node leftNode = nodes.get(currIndex-1);
                if(leftNode.height > node.height + (node.index - leftNode.index)){
                    if(leftNode.index > 1)
                        heap.offer(new int[]{leftNode.index, node.height + (node.index - leftNode.index), -1, currIndex-1});
                    leftNode.height = node.height + (node.index - leftNode.index);
                }
            } else {
                //right
                int currIndex = ele[3];
                Node node = nodes.get(currIndex);
                Node rightNode = nodes.get(currIndex+1);
                if(rightNode.height > node.height + (rightNode.index - node.index)){
                    if(rightNode.index < n)
                        heap.offer(new int[]{rightNode.index, node.height + (rightNode.index - node.index), 1, currIndex+1});
                    rightNode.height = node.height + (rightNode.index - node.index);
                }
            }
        }

        int maxHeight = 0;
        for(int j = 1; j < nodes.size(); j++){
            Node node1 = nodes.get(j-1);
            Node node2 = nodes.get(j);
            int leftIndex = node1.index;
            int leftHeight = node1.height;
            int rightIndex = node2.index;
            int rightHeight = node2.height;

            if(node1.height < node2.height){
                leftIndex = leftIndex + (node2.height - node1.height);
                leftHeight = node2.height;
            } else {
                rightIndex = rightIndex - (node1.height - node2.height);
                rightHeight = node1.height;
            }


            int diff = rightIndex - leftIndex;
            int maxHeightBetween = leftHeight + diff / 2;
            maxHeight = Math.max(maxHeight, maxHeightBetween);
        }
        return maxHeight;
    }

    public int maxBuilding_refined(int n, int[][] restrictions) {
        // Convert the constraints to a list for manipulation
        List<int[]> r = new ArrayList<>();
        for (int[] res : restrictions) {
            r.add(new int[] { res[0], res[1] });
        }

        // Add restriction (1, 0)
        r.add(new int[] { 1, 0 });
        // Sort by position
        r.sort((a, b) -> Integer.compare(a[0], b[0]));
        // Add restriction (n, n-1)
        if (r.get(r.size() - 1)[0] != n) {
            r.add(new int[] { n, n - 1 });
        }

        int m = r.size();

        // Pass restrictions from left to right
        for (int i = 1; i < m; ++i) {
            int dist = r.get(i)[0] - r.get(i - 1)[0];
            r.get(i)[1] = Math.min(r.get(i)[1], r.get(i - 1)[1] + dist);
        }

        // Pass restrictions from right to left
        for (int i = m - 2; i >= 0; --i) {
            int dist = r.get(i + 1)[0] - r.get(i)[0];
            r.get(i)[1] = Math.min(r.get(i)[1], r.get(i + 1)[1] + dist);
        }

        int ans = 0;
        for (int i = 0; i < m - 1; ++i) {
            // Calculate the maximum height of the buildings between r[i][0] and r[i][1]
            int dist = r.get(i + 1)[0] - r.get(i)[0];
            int best = (dist + r.get(i)[1] + r.get(i + 1)[1]) / 2;
            ans = Math.max(ans, best);
        }

        return ans;
    }

    static void main() {
        int[][] res = new int[][]{{5,3},{2,5},{7,4},{10,3}};
        int ans = new LC_20260620_1840_MaximumBuildingHeight().maxBuilding(10, res);
        System.out.println(ans);
    }
}
