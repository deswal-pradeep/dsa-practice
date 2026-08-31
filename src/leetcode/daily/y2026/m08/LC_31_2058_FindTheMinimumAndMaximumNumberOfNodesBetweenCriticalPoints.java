package leetcode.daily.y2026.m08;

import java.util.Arrays;

public class LC_31_2058_FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints {
    static class  ListNode {
        int val;
        ListNode next;
        ListNode(int val){ this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode first = head.next.next;
        ListNode second = head.next;
        ListNode third = head;

        int firstPoint = -1;
        int lastPoint = -1;
        int currentPoint = 1;
        int previousPoint = -1;
        int minDistance = (int)1e9;
        int maxDistance = (int)-1e9;

        while(first != null){
            if((second.val > first.val && second.val > third.val)
                    ||(second.val < first.val && second.val < third.val)){
                //a critical point
                if(firstPoint == -1){
                    firstPoint = currentPoint;
                }
                if(previousPoint != -1){
                    minDistance = Math.min(minDistance, currentPoint - previousPoint);
                }
                previousPoint = currentPoint;
                lastPoint = currentPoint;
            }
            third = second;
            second = first;
            first = first.next;
            currentPoint++;
        }
        maxDistance = Math.max(maxDistance, lastPoint - firstPoint);
        return minDistance == (int)1e9
                ? new int[]{-1, -1} :
                new int[]{minDistance, maxDistance};
    }

    static void main() {
        ListNode node7 = new ListNode(2);
        ListNode node6 = new ListNode(1, node7);
        ListNode node5 = new ListNode(5, node6);
        ListNode node4 = new ListNode(2, node5);
        ListNode node3 = new ListNode(1, node4);
        ListNode node2 = new ListNode(3, node3);
        ListNode node1 = new ListNode(5, node2);
        int[] ints = new LC_31_2058_FindTheMinimumAndMaximumNumberOfNodesBetweenCriticalPoints().nodesBetweenCriticalPoints(node1);
        System.out.println(Arrays.toString(ints));
    }
}
