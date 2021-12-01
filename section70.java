class Solution {

    // Question 703
    /**
     *  IDEA 1:
     *  p.s. 剑指源码
     **/
     static class KthLargest {
         private PriorityQueue<Integer> minHeap;
         private int size;

         public KthLargest( int k, int[] nums ) {
             size = k;
             minHeap = new PriorityQueue<>();
             for( int num: nums) {
                 add(num);
             }
         }

         public int add( int val ) {
             if( minHeap.size() < size )
                 minHeap.offer(val);
             else if( val > minHeap.peek() ) {
                 minHeap.poll();
                 minHeap.offer(val);
             }

             return minHeap.peek();
         }
     }
}
