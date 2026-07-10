package leetcode.daily.y2026.m06;

//https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/?envType=daily-question&envId=2026-06-15
public class LC_15_2095_DeleteTheMiddleNodeOfALinkedList {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode deleteMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while(fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        if(prev == null){
            head = null;
        } else {
            prev.next = slow.next;
            slow.next = null;
        }
        return head;
    }

    static void main() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(7);
        ListNode node5 = new ListNode(1);
        ListNode node6 = new ListNode(2);
        ListNode node7 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        ListNode newHead = new LC_15_2095_DeleteTheMiddleNodeOfALinkedList()
                .deleteMiddle(node1);
        ListNode ptr = newHead;
        StringBuilder builder = new StringBuilder("[");
        while(ptr != null){
            builder.append(ptr.val+",");
            ptr = ptr.next;
        }
        if (builder.charAt(builder.length()-1) == ',')
            builder.deleteCharAt(builder.length()-1);
        builder.append("]");
        System.out.println(builder);
    }
}
