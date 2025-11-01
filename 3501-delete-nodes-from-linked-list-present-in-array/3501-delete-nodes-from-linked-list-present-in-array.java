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
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> numSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        ListNode res = new ListNode(-1);
        res.next = head;
        ListNode prev = res;
        ListNode curr = head;

        while(curr != null){
            if(numSet.contains(curr.val)){
                prev.next = curr.next;
            }else{
                prev = curr;
            }
            curr = curr.next;
        }

        return res.next;
    }
}