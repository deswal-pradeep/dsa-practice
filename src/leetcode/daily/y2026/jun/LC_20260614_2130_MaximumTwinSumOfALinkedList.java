package leetcode.daily.y2026.jun;

import java.util.Stack;

//https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/?envType=daily-question&envId=2026-06-14
public class LC_20260614_2130_MaximumTwinSumOfALinkedList {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    public int pairSum(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode prev = null;
        ListNode curr = slow;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        ListNode p1 = head;
        ListNode p2 = prev;

        int maxi = (int) -1e9;
        while (p2 != null) {
            maxi = Math.max(maxi, p1.val + p2.val);
            p1 = p1.next;
            p2 = p2.next;
        }
        return maxi;

    }

    public int pairSum_2(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode ptr = head;
        while (ptr != null) {
            stack.push(ptr.val);
            ptr = ptr.next;
        }

        int n = stack.size();
        Stack<Integer> other = new Stack<Integer>();
        for (int i = 0; i < n / 2; i++) {
            other.push(stack.pop());
        }
        int maxi = (int) -1e9;
        while (!other.isEmpty()) {
            int a = other.pop();
            int b = stack.pop();
            maxi = Math.max(maxi, a + b);
        }
        return maxi;
    }

    static void main() {
        ListNode node1 = new ListNode(5);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        int ans = new LC_20260614_2130_MaximumTwinSumOfALinkedList()
                .pairSum(node1);
        System.out.println(ans);
    }
}
