/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode rightHead = slow.next;
        slow.next = null;

        return merge(sortList(head), sortList(rightHead));
    }

    public static ListNode merge(ListNode head1, ListNode head2){
        ListNode newLL = new ListNode();
        ListNode tmp = newLL;

        while(head1 != null && head2 != null){
            if(head1.val < head2.val){
                tmp.next = head1;
                head1 = head1.next;
            }else {
                tmp.next = head2;
                head2 = head2.next;
            }
            tmp = tmp.next;
        }

        if(head1 != null)
            tmp.next = head1;
        if(head2 != null)
            tmp.next = head2;
            
        return newLL.next;
    }
}