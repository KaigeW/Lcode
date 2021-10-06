class Solution {

    // Question 234
    /**
     * IDEA:
     * find the middle node, break down to two list, reverse the second one
     * Loop thru to find out if the loop exists
     *
     * ps. 剑指源码
     * Inspired by 剑指
     */
     public boolean isPalindrome(ListNode head) {
         if( head == null || head.next == null )
             return true;
         ListNode fast = head.next, slow = head;
         while( fast.next != null ) {
             fast = fast.next;
             slow = slow.next;
             if( fast.next != null )
                 fast = fast.next;
             else
                 break;
         }

         ListNode reverseNode = slow.next;
         slow.next = null;

         ListNode prev = null;
         ListNode next = reverseNode;
         while( reverseNode.next != null ) {
             next = reverseNode.next;
             reverseNode.next = prev;
             prev = reverseNode;
             reverseNode = next;
         }

         reverseNode.next = prev;

         while( head != null && reverseNode != null ) {
             if( head.val != reverseNode.val )
                 return false;
             head = head.next;
             reverseNode = reverseNode.next;
         }

         if( head == null || head.next == null )
             return true;
         return false;
     }

    /**
     * IDEA 2:
     * find the middle node, break down to two list, reverse the second one
     * Loop thru to find out if the loop exists
     * Above is faster somehow !!!
     *
     * ps. 剑指源码
     */
     public boolean isPalindrome(ListNode head) {
         if( head == null || head.next == null ) {
             return true;
         }

         ListNode slow = head;
         ListNode fast = head.next;
         while( fast.next != null && fast.next.next != null) {
             fast = fast.next.next;
             slow = slow.next;
         }

         ListNode secondHalf = slow.next;
         if( fast.next != null) {
             secondHalf = slow.next.next;
         }

         slow.next = null;
         return equals( secondHalf, reverseList(head));
     }

     private boolean equals(ListNode head1, ListNode head2) {
         while( head1 != null && head2 != null) {
             if( head1.val != head2.val) {
                 return false;
             }

             head1 = head1.next;
             head2 = head2.next;
         }

         return head1 == null && head2 == null;
     }

     public ListNode reverseList( ListNode head ) {
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

}
