class Solution {

    // Question 494
    /**
     * IDEA:
     * Inspired by youtube huahua
     *
     */
     public int findTargetSumWays(int[] nums, int target) {
         int sum = 0;
         for( int num: nums )
             sum += num;
         if( ((sum + target) & 1) == 1  || target > sum || target < -sum )
             return 0;
         
         int[][] waysCount = new int[nums.length + 1][2 * sum + 1];

         waysCount[0][sum] = 1;
         for( int i = 1; i < nums.length + 1; ++i ) {
             for( int j = -sum; j <= sum; ++j ) {
                 if( j - nums[i - 1] >= -sum )
                     waysCount[i][j + sum] += waysCount[i-1][j - nums[i - 1] + sum];
                 if( j + nums[i - 1] <= sum )
                     waysCount[i][j + sum] += waysCount[i-1][j + nums[i - 1] + sum];
             }
         }
         for( int[] arr: waysCount ) {
             System.out.println(Arrays.toString(arr));
         }
         return waysCount[nums.length][target + sum];

     }
}
