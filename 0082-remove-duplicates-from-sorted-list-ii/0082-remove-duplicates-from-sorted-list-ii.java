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
    public ListNode deleteDuplicates(ListNode head) {
        // Create a dummy node pointing to head to handle edge cases
        ListNode dummy = new ListNode(0, head);
      
        // Previous node pointer to maintain the list after removing duplicates
        ListNode previous = dummy;
      
        // Current node pointer to traverse the list
        ListNode current = head;
      
        // Traverse the entire linked list
        while (current != null) {
            // Skip all nodes with the same value as current node
            while (current.next != null && current.next.val == current.val) {
                current = current.next;
            }
          
            // Check if we found duplicates
            if (previous.next == current) {
                // No duplicates found, move previous pointer forward
                previous = current;
            } else {
                // Duplicates found, skip all duplicate nodes
                previous.next = current.next;
            }
          
            // Move to the next node
            current = current.next;
        }
      
        // Return the head of the modified list
        return dummy.next;
    }
}