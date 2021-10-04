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

}
