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
    public ListNode deleteMiddle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        int i = 0;
        while(fast != null && fast.next != null){  // slow-fast pointer help to find out mid of Linked List
            slow = slow.next;
            fast = fast.next.next;
            i++;
        }

        if(i == 0){  //  if Linked list contain single node then delete the node
            head = head.next;
            return head;
        }

        int j=1;
        ListNode prev = head;
        while(j < i){  // else find the previous node of mid
            prev = prev.next;
            j++;
        }

        prev.next = prev.next.next;  //  remove the mid from Linked List
        return head;


        
    }
}