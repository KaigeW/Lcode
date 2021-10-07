class Solution {

    // Question 430
    /**
     * IDEA 1:
     * Using recursive call to do the flattening
     *
     *   Space: O(1)
     *   Time: O(n)
     *
     * Inspired by 剑指
     *
     */
     public Node flatten(Node head) {
         flattenAlltheNode(head);
         return head;
     }

     private Node flattenAlltheNode(Node head) {
         Node pre = null;
         while( head != null ) {
             if( head.child != null ) {
                 Node temp = head.next, tail = flattenAlltheNode(head.child);
                 // reorder the head and its child
                 head.next = head.child;
                 head.child.prev = head;
                 head.child = null;
                 // reorder tail and head.next
                 if( temp != null ) {
                     tail.next = temp;
                     temp.prev = tail;
                 }
             }
             pre = head;
             head = head.next;
         }
         return pre;
     }

    /**
     * IDEA 2:
     * Using recursive call to do the flattening
     *
     *   Space: O(1)
     *   Time: O(n)
     *
     * ps. 剑指源码
     *
     */
     public Node flatten(Node head) {
         flattenGetTail(head);
         return head;
     }

     private Node flattenGetTail(Node head) {
         Node node = head;
         Node tail = null;
         while( node != null ) {
             Node next = node.next;
             if( node.child != null ) {
                 Node child = node.child;
                 Node childTail = flattenGetTail(node.child);

                 node.child = null;
                 node.next = child;
                 child.prev = node;
                 childTail.next = next;
                 if( next != null ) {
                   next.prev = childTail;
                 }

                 tail = childTail;
             } else {
                 tail = node;
             }
             node = next;
         }
         return tail;
     }

    /**
     * IDEA 3:
     * Using recursive call to do the flattening
     *
     *   Space: O(1)
     *   Time: O(n)
     *
     * Leetcode
     *
     */
     public Node flatten(Node head) {
         if( head == null) return head;
            // Pointer
         Node p = head;
         while( p!= null) {
             /* CASE 1: if no child, proceed */
             if( p.child == null ) {
                 p = p.next;
                 continue;
             }
             /* CASE 2: got child, find the tail of the child and link it to p.next */
             Node temp = p.child;
             // Find the tail of the child
             while( temp.next != null )
                 temp = temp.next;
             // Connect tail with p.next, if it is not null
             temp.next = p.next;
             if( p.next != null )  p.next.prev = temp;
             // Connect p with p.child, and remove p.child
             p.next = p.child;
             p.child.prev = p;
             p.child = null;
         }
         return head;
     }

}
