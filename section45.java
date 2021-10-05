class Solution {
    // question 445

    /**
     * IDEA 1:
     * Reverse the linkedlist, then do the calculation
     * Space: O(n)
     * Time: O(n)
     * Inspired by 剑指
     */
     public ListNode addTwoNumbers(ListNode head1, ListNode head2) {
         if( head1 != null )
             head1 = reverseList(head1);
         if( head2 != null )
             head2 = reverseList(head2);
         return addList(head1, head2);
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

     public ListNode addList( ListNode head1, ListNode head2 ) {
         int sum = 0;
         boolean carry = false;
         ListNode head = null;
         while( head1 != null || head2 != null ) {
             if( head1 != null ){
                 sum += head1.val;
                 head1 = head1.next;
             }
             if( head2 != null ){
                 sum += head2.val;
                 head2 = head2.next;
             }
             sum += carry? 1 : 0;
             carry = false;
             if( sum >= 10 ) {
                 sum -= 10;
                 carry = true;
             }

             head = new ListNode(sum, head);
             sum = 0;
         }
         if( carry )
             head = new ListNode(1, head);
         return head;
     }

}
