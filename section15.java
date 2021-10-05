class Solution {

    // Question 142
    /**
     * IDEA 1:
     * Find the node that is inside of the loop first, figure our how many nodes
     *   are inside of the loop, find the start of the loop!
     *
     *   Space: O(1)
     *   Time: O(n)
     *
     * ps. 剑指源码
     */

     public ListNode detectCycle( ListNode head ) {
         ListNode inLoop = getNodeInLoop(head);
         if( inLoop == null)
             return null;
         int loopCount = 1;
         for( ListNode n = inLoop; n.next != inLoop; n = n.next)
             loopCount++;

         ListNode fast = head;
         for( int i = 0; i < loopCount; ++i )
             fast = fast.next;

         ListNode slow = head;
         while( fast != slow ) {
             fast = fast.next;
             slow = slow.next;
         }
         return slow;
     }

     private ListNode getNodeInLoop(ListNode head) {
         if( head == null || head.next == null )
             return null;
         ListNode slow = head.next;
         ListNode fast = slow.next;
         while( slow != null && fast != null ) {
             if( slow == fast )
                 return slow;
             slow = slow.next;
             fast = fast.next;
             if( fast != null )
                 fast = fast.next;
         }
         return null;
     }

    /**
     * IDEA 2:
     *   TODO
     *   Space: O(1)
     *   Time: O(n)
     *
     * ps. 剑指源码
     */

     public ListNode detectCycle( ListNode head ) {
         ListNode inLoop = getNodeInLoop(head);
         if( inLoop == null)
             return null;
         ListNode node = head;
         while( node != inLoop ) {
             node = node.next;
             inLoop = inLoop.next;
         }
         return node;
     }

     private ListNode getNodeInLoop(ListNode head) {
         if( head == null || head.next == null )
             return null;
         ListNode slow = head.next;
         ListNode fast = slow.next;
         while( slow != null && fast != null ) {
             if( slow == fast )
                 return slow;
             slow = slow.next;
             fast = fast.next;
             if( fast != null )
                 fast = fast.next;
         }
         return null;
     }


    // Question 143
    /**
     * IDEA 1:
     * Use fast and slow to find out the middle of the list, and reverse the
     *   list
     *
     *   Space: O(1)
     *   Time: O(n)
     *
     * inspired by 剑指
     */
     public void reorderList(ListNode head) {
         ListNode middle = middleNode(head);
         ListNode reversed = reverseList(middle);
         ListNode headNext = null, reversedNext = null;
         while( head != null && reversed != null ) {
             headNext = head.next;
             reversedNext = reversed.next;
             head.next = reversed;
             reversed.next = headNext;
             head = headNext;
             reversed = reversedNext;
         }
         if( head != null )
             head.next = null;
     }

     public ListNode middleNode(ListNode head) {
         ListNode slow = head, fast = head;
         while( fast != null ) {
             fast = fast.next;
             slow = slow.next;
             if( fast != null )
                 fast = fast.next;
             else
                 break;
         }
         return slow;
     }

     public ListNode reverseList( ListNode head ) {
         if( head == null )
             return null;
         ListNode prev = null;
         ListNode next = head;
         while( head.next != null ) {
             next = head.next;
             head.next = prev;
             prev = head;
             head = next;
         }
         head.next = prev;
         return head;
     }

    /**
     * IDEA 2:
     * Use fast and slow to find out the middle of the list, and reverse the
     *   list
     *
     *   Space: O(1)
     *   Time: O(n)
     *
     * ps. 剑指源码
     */
     public void reorderList(ListNode head) {
         ListNode dummy = new ListNode(0);
         dummy.next = head;
         ListNode fast = dummy;
         ListNode slow = dummy;
         while( fast != null && fast.next != null ) {
             slow = slow.next;
             fast = fast.next;
             if( fast.next != null )
                 fast = fast.next;
         }

         ListNode temp = slow.next;
         slow.next = null;
         link(head, reverseList(temp), dummy);
     }

     private void link(ListNode node1, ListNode node2, ListNode head) {
         ListNode prev = head;
         while( node1 != null && node2 != null) {
             ListNode temp = node1.next;

             prev.next = node1;
             node1.next = node2;
             prev = node2;

             node1 = temp;
             node2 = node2.next;
         }
         if( node1 != null )
             prev.next = node1;
     }

}
