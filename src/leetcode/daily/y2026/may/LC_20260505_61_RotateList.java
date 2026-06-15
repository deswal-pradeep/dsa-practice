package leetcode.daily.y2026.may;

public class LC_20260505_61_RotateList {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode rotateRight(ListNode head, int k) {
        int size = 0;
        if(head == null || k == 0)
            return head;

        ListNode ptr = head;
        ListNode tail = head;
        while(ptr != null){
            size++; tail = ptr; ptr = ptr.next;
        }
        int rotate = size - (k % size);
        ptr = head;
        while(--rotate > 0){
            ptr = ptr.next;
        }
        tail.next = head;
        head = ptr.next;
        ptr.next = null;
        return head;
    }

    static void main() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode head = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        LC_20260505_61_RotateList caller = new LC_20260505_61_RotateList();
        ListNode headAns = caller.rotateRight(head, 2);
        caller.print(headAns);
    }

    void print(ListNode head){
        ListNode ptr = head;
        StringBuilder builder = new StringBuilder("[");
        while(ptr != null){
            builder.append(ptr.val+",");
            ptr = ptr.next;
        }
        builder.deleteCharAt(builder.length()-1);
        builder.append("]");
        System.out.println(builder);
    }
}
