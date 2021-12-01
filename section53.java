class Solution {

    // Question 525
    /**
     * IDEA:
     * Use sum array to figure out
     * ps. 剑指源码
     * All of possible sums will be stored into the array
     * Time O(n)
     * Space O(n)
     */
     public int findMaxLength(int[] nums) {
         Map<Integer, Integer> sumToIndex = new HashMap();
         sumToIndex.put(0, -1);
         int sum = 0;
         int maxLen = 0;
         for( int i = 0; i < nums.length; ++i ) {
             sum += nums[i] == 0? -1 : 1;
             if( sumToIndex.containsKey(sum)) {
                 maxLen = Math.max(maxLen, i - sumToIndex.get(sum));
             } else {
                 sumToIndex.put(sum, i);
             }
         }
         return maxLen;
     }

    // Question 528
    /**
     * IDEA:
     * Use Binary search to achieve this
     */
     static class Solution {
         private int[] sums;
         private int total;

         public Solution( int[] w ) {
             sums = new int[w.length];
             for( int i = 0; i < w.length; ++i ) {
                 total += w[i];
                 sums[i] = total;
             }
         }

         public int pickIndex() {
             Random random = new Random();
             int p = random.nextInt(total);

             int left = 0;
             int right = sums.length;
             while( left <= right ) {
                 int mid = (left + right) / 2;
                 if( sums[mid] > p ) {
                     if( mid == 0 || (sums[mid - 1] <= p) ) {
                         return mid;
                     }
                     right = mid - 1;
                 } else {
                     left = mid + 1;
                 }
             }
             return -1;
         }
     }

}
