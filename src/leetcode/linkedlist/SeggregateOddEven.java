package leetcode.linkedlist;

class Solution {
    static void main() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode sHead = oddEvenList(head);
        IO.println(sHead);
    }

    public static ListNode oddEvenList(ListNode head) {
        //YOUR CODE GOES HERE
        ListNode oddHead = null;
        ListNode oddPtr = null;
        ListNode evenHead = null;
        ListNode evenPtr = null;
        ListNode ptr = head;
        int i = 1;
        while(ptr != null){
            if(i % 2 != 0){
                if(oddHead == null){
                    oddHead = ptr;
                    oddPtr = ptr;
                } else {
                    oddPtr.next = ptr;
                    oddPtr = ptr;
                }
            } else {
                if(evenHead == null){
                    evenHead = ptr;
                    evenPtr = ptr;
                } else {
                    evenPtr.next = ptr;
                    evenPtr = ptr;
                }
            }
            ptr = ptr.next;
            i++;
        }
        if(evenPtr != null)
            evenPtr.next = null;
        if(oddPtr != null)
            oddPtr.next = evenHead;
        return oddHead;
    }

    static class ListNode{
        public int data;
        public ListNode next;
        ListNode() { data = 0; next = null; }
        ListNode(int x) { data = x; next = null; }
        ListNode(int x, ListNode next) { data = x; this.next = next; }
    }
}
