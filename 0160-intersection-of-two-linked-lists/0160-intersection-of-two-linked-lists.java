/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA;
        ListNode B = headB;
        int sizeA = 0, sizeB = 0;



        while(A != B){
            A = (A == null) ? headB:A.next;
            B = (B == null) ? headA:B.next;
        }

        return A;

    }
}