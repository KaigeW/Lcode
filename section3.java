class Solution {

    // Question 23
    /**
     * IDEA :
     * Use minimum heap to get the minimum from these lists
     *
     * Inspired by 剑指
     *
     */
     public ListNode mergeKLists( ListNode[] lists ) {
         ListNode result = new ListNode();
         ListNode pt = result;
         PriorityQueue<ListNode> minHeap = new PriorityQueue<>( (n1, n2)
           -> n1.val - n2.val );
         for( ListNode node: lists ){
             if( node != null )
                 minHeap.offer(node);
         }

         while( !minHeap.isEmpty() ) {
             ListNode temp = minHeap.poll();
             if( temp.next != null )
                 minHeap.offer(temp.next);
             pt.next = temp;
             pt = pt.next;
         }
         return result.next;
     }


    /**
     * IDEA 2:
     * Use mergeSort
     *
     */
     public ListNode mergeKLists( ListNode[] lists ) {
         if( lists.length == 0 )
             return null;
         return mergeKLists( lists, 0, lists.length - 1 );
     }

     public ListNode mergeKLists( ListNode[] lists, int start, int end ) {
         if( start == end )
             return lists[start];

         ListNode firstList = mergeKLists(lists, start, (start + end) / 2);
         ListNode secondList = mergeKLists(lists, (start + end) / 2 + 1, end );

         return mergeList(firstList, secondList);
     }

     private ListNode mergeList( ListNode firstList, ListNode secondList ) {
         ListNode returnList = new ListNode();
         ListNode pt = returnList;

         while( firstList != null && secondList != null ) {
             if( firstList.val > secondList.val ) {
                 pt.next = secondList;
                 secondList = secondList.next;
             } else {
                 pt.next = firstList;
                 firstList = firstList.next;
             }
             pt = pt.next;
         }

         if( firstList == null ) {
             pt.next = secondList;
         } else {
             pt.next = firstList;
         }

         return returnList.next;
     }

    /**
     * IDEA 3:
     * Use mergeSort
     *
     * p.s. 剑指源码
     *
     */
     public ListNode mergeKLists( ListNode[] lists ) {
         if( lists.length == 0 ) 
             return null;
         return mergeLists( lists, 0, lists.length );
     }

     private ListNode mergeLists( ListNode[] lists, int start, int end ) {
         if( start + 1 == end )
             return lists[start];
         int mid = ( start + end ) / 2;
         ListNode head1 = mergeLists( lists, start, mid );
         ListNode head2 = mergeLists( lists, mid, end );
         return mergeList( head1, head2 );
     }


    // Question 26
    /**
     * IDEA :
     * Need to make first several chars unique, and return the length
     * Used two pointers, one to represent the unique index, one to represent
     * array Index
     */
     public int removeDuplicates(int[] nums) {
         // Time: O(N)
         //
         // Space: O(1)
         //
         if( nums.length == 0 )
             return 0;
         int start = 0;
         int index = 1;
         while( index < nums.length ){
             if( nums[start] != nums[index] ){
                 nums[++start] = nums[index];
             }
             index++;
         }
         return ++start;
     }

    // Question 29
    /**
     * IDEA 1:
     * 剑指！自我实现
     */
     public int divide(int dividend, int divisor) {
         boolean negative = false;
         if( dividend == Integer.MIN_VALUE && divisor == -1 )
             return Integer.MAX_VALUE;
         if( dividend < 0 )
             negative = negative ? false : true ;
         else
             dividend = -dividend;
         if( divisor < 0 )
             negative = negative ? false : true ;
         else
             divisor = -divisor;

         int result = 0;
         int multi = divisor;
         int time = 1;
         while( dividend <= divisor ) {
             if( dividend <= multi ) {
                 dividend -= multi;
                 result += time;
                 if( (long) time * 2 < Integer.MAX_VALUE ) {
                     multi += multi;
                     time += time;
                 }
             } else {
                 multi /= 2;
                 time /= 2;
             }
         }
         if(negative) return -result;
         return result;
     }

    /**
     * IDEA 2:
     * 剑指！官方实现
     */
     public int divide( int dividend, int divisor ) {
         if ( dividend == 0x80000000 && divisor == -1 ) {
             return Integer.MAX_VALUE;
         }
         int negative = 2;
         if ( dividend > 0 ) {
             negative--;
             dividend = -dividend;
         }
         if ( divisor > 0 ) {
             negative --;
             divisor = - divisor;
         }

         int result = divideCore( dividend, divisor );
         return negative == 1 ? -result : result;
     }

     private int divideCore( int dividend, int divisor ) {
         int result = 0;
         while ( dividend <= divisor ) {
             int value = divisor;
             int quotient = 1;
             while ( value > 0xc0000000 && dividend <= value + value ) {
                 quotient += quotient;
                 value += value;
             }
             result += quotient;
             dividend -= value;
         }
         return result;
     }

}
