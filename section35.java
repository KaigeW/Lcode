class Solution {

    // Question 346
    /**
     * unlock required
     *
     * similar to 剑指源码
     */
     static class MovingAverage {
         double sum = 0;
         int size;
         Queue<Integer> queue;

         public MovingAverage(int size) {
             this.size = size;
             this.queue = new LinkedList<Integer>();
         }

         public double next(int val) {
             queue.add(val);
             sum += val;
             if( queue.size() <= size )
                 return sum / queue.size();
             else {
                 sum -= queue.remove();
                 return sum / size;
             }
         }
     }

    // Question 347
    /**
     * unlock required
     *
     * similar to 剑指源码
     */
     public int[] topKFrequent(int[] nums, int k) {
         Map<Integer, Integer> numToCount = new HashMap<>();
         for( int num: nums ) {
             numToCount.put(num, numToCount.getOrDefault(num, 0) + 1);
         }

         Queue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
           (e1, e2) -> e1.getValue() - e2.getValue());

         for( Map.Entry<Integer, Integer> entry: numToCount.entrySet()) {
             if( minHeap.size() < k )
                 minHeap.offer(entry);
             else {
                 if( entry.getValue() > minHeap.peek().getValue() ) {
                     minHeap.poll();
                     minHeap.offer(entry);
                 }
             }
         }
         int[] result = new int[minHeap.size()];
         int i = 0;
         for(Map.Entry<Integer, Integer> entry: minHeap)
             result[i++] = entry.getKey();
         return result;
     }
}
