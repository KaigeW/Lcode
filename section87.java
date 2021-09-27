class Solution {

    // Question 862
    /**
     * IDEA:
     * Use two pointer to loop thru
     * Time: O(n)
     * Space: O(1)
     */
     public int shortestSubarray(int[] nums, int k) {
         int minLen = Integer.MAX_VALUE;
         int sum = 0;
         int left = 0;
         for( int right = 0; right < nums.length; right++ ) {
             sum += nums[right];
             while( left <= right && sum >= k ) {
                 minLen = Math.min( minLen , right - left + 1);
                 sum -= nums[left++];
             }
         }
         return minLen = Integer.MAX_VALUE ? -1 : minLen;
     }

}
