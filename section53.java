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
}
