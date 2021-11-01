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
}
