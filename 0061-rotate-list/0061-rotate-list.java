class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        // Step 1: Edge cases
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Step 2: Find length and tail node
        ListNode tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Step 3: Handle rotations larger than length
        k = k % length;
        if (k == 0) return head;

        // Step 4: Make the list circular
        tail.next = head;

        // Step 5: Find the new tail
        // New tail is at (length - k) steps from head
        ListNode newTail = head;
        for (int i = 0; i < length - k - 1; i++) {
            newTail = newTail.next;
        }

        // Step 6: Break the circle
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}