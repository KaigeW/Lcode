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

    // Question 146
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
     class LRUCache {

         public LRUCache(int capacity) {

         }

         public int get(int key) {

         }

         public void put(int key, int value) {

         }
     }

    // Question 148
    /**
     * IDEA 1:
     * Use mergeSort to sort to linkedList
     *
     * inspired by 剑指
     */
     public ListNode sortList( ListNode head ) {

         if( head == null || head.next == null )
             return head;

         ListNode head1, head2;

         head1 = head;
         head2 = halfHead(head);

         head1 = sortList(head1);
         head2 = sortList(head2);

         return mergeSort( head1, head2 );
     }

     private ListNode halfHead( ListNode head ) {
         ListNode newHead = new ListNode();
         newHead.next = head;
         ListNode pt1 = newHead;
         ListNode pt2 = newHead;

         while( pt2 != null && pt2.next != null ) {
             pt1 = pt1.next;
             pt2 = pt2.next;
             pt2 = pt2.next;
         }

         newHead = pt1.next;
         pt1.next = null;
         return newHead;
     }

     private ListNode mergeSort( ListNode head1, ListNode head2 ) {
         ListNode newHead = new ListNode();
         ListNode pt = newHead;
         ListNode pt1 = head1, pt2 = head2;
         while( pt1 != null && pt2 != null ) {
             if( pt1.val < pt2.val ) {
                 newHead.next = pt1;
                 pt1 = pt1.next;
             } else {
                 newHead.next = pt2;
                 pt2 = pt2.next;
             }
             newHead = newHead.next;
         }
         if( pt1 == null )
             newHead.next = pt2;
         else
             newHead.next = pt1;
         return pt.next;
     }

    /**
     * IDEA 2:
     *
     * p.s. 剑指源码
     *
     */
     public ListNode sortList( ListNode head ) {
         if( head == null || head.next == null ) {
             return head;
         }

         ListNode head1 = head;
         ListNode head2 = split( head );

         head1 = sortList(head1);
         head2 = sortList(head2);

         return merge( head1, head2 );
     }

     private ListNode split( ListNode head ) {
         ListNode slow = head;
         ListNode fast = head.next;
         while( fast != null && fast.next != null ) {
             slow = slow.next;
             fast = fast.next.next;
         }
         ListNode second = slow.next;
         slow.next = null;

         return second;
     }

     private ListNode merge( ListNode head1, ListNode head2 ) {
         ListNode dummy = new ListNode(0);
         ListNode cur = dummy;
         while( head1 != null && head2 != null ) {
             if( head1.val < head2.val ) {
                 cur.next = head1;
                 head1 = head1.next;
             } else {
                 cur.next = head2;
                 head2 = head2.next;
             }

             cur = cur.next;
         }
         cur.next = head1 == null? head2 : head1;
         return dummy.next;
     }

    // Question 150
    /**
     * IDEA 1:
     *   Loop thru the string list from the front. If saw number, push to the
     *     stack, Otherwise, pop two numbers from the stack, execute the
     *     corresponding operation, and push the result back to the stack
     *   Space: O(N)
     *   Time: O(N)
     *
     */
     public int evalRPN(String[] tokens) {
         Stack<Integer> nums = new Stack<>();
         for( String str: tokens ){
             char temp = str.charAt(0);
             if( (temp >= '0' && temp <= '9')
               || (temp == '-' && str.length() > 1) )
                 nums.push(Integer.parseInt(str));
             else{
                 int sec = nums.pop();
                 int fir = nums.pop();
                 if( temp == '+' ) {
                     nums.push(fir + sec);
                 }
                 else if( temp == '-') {
                     nums.push(fir - sec);
                 }
                 else if( temp == '*') {
                     nums.push(fir * sec);
                 }
                 else if( temp == '/') {
                     nums.push(fir / sec);
                 }
             }
         }
         return nums.pop();
     }

    /**
     * IDEA 2:
     *   Similar to above. But use String literal and switch statement
     *     to compare and calculate
     *   Space: O(N)
     *   Time: O(N)
     *
     * p.s. 剑指源码
     */
     public int evalRPN(String[] tokens) {
         Stack<Integer> stack = new Stack<Integer>();
         for( String token: tokens) {
             switch(token) {
                 case "+":
                 case "-":
                 case "*":
                 case "/":
                     int num1 = stack.pop();
                     int num2 = stack.pop();
                     stack.push(calculate(num2, num1, token));
                     break;
                 default:
                     stack.push(Integer.parseInt(token));
             }
         }

         return stack.pop();
     }

     private int calculate(int num1, int num2, String operator) {
         switch(operator) {
             case "+":
                 return num1 + num2;
             case "-":
                 return num1 - num2;
             case "*":
                 return num1 * num2;
             case "/":
                 return num1 / num2;
             default:
                 return 0;
         }
     }



}
