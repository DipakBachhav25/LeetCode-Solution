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
    public ListNode removeNthFromEnd(ListNode head, int n) {

        int size = 0;
        ListNode temp = head;
        while(temp != null){  // calculate the size of Linked List
            temp = temp.next;
            size++;
        }

        if(n == size){  // if node is head then new head become next node of head
            head = head.next;
            return head;
        }

        int i=1;
        ListNode prev = head;
        while(i < size-n){  // find the previous node of deleting node
            prev = prev.next;
            i++;
        }

        prev.next = prev.next.next;  // and make previous node next is previous node next.next
        return head;
        
    }
}