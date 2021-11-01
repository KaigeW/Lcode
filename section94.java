class Solution {

    // Question 933
    /**
     * IDEA:
     * Use Queue structure to detects pattern, 
     *
     * similar to 剑指源码
     */
     static class RecentCounter {
         private Queue<Integer> queue;

         public RecentCounter() {
             this.queue = new LinkedList();
         }

         public int ping(int t) {
             queue.offer(t);
             while( queue.peek() < t - 3000) {
                 queue.poll();
             }
             return queue.size();
         }
     }
}
