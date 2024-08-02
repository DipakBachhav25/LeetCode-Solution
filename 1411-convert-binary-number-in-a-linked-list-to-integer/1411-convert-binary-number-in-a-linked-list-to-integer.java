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
    public int getDecimalValue(ListNode head) {

        int size = 0;
        ListNode temp = head;
        while(temp != null){
            size++;
            temp = temp.next;
        }

        temp = head;
        int decimal = 0;
        int power = size-1;
        while(temp != null){
            decimal += (temp.val * (int)Math.pow(2, power));
            power--;
            temp = temp.next;
        }
        return decimal;
    }
}