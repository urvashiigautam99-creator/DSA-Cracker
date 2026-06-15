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
        // Base case: If the list has only 1 node, deleting it leaves an empty list
        if (head == null || head.next == null) {
            return null;
        }
        
        // Use a dummy node to seamlessly manage pointers
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy;
        ListNode fast = head;
        
        // Move fast by 2 steps and slow by 1 step
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // 'slow' is now right before the middle node. Skip the middle node to delete it.
        slow.next = slow.next.next;
        
        return dummy.next;
    }
}
