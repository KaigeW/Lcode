class Solution {
    // Question 746
    /**
     * IDEA 1:
     * Use dynamic programming
     *
     * p.s. 剑指源码
     *
     */

     public int minCostClimbingStairs( int[] cost ) {
         int len = cost.length;
         return Math.min(helper(cost, len - 2), helper( cost, len - 1));
     }

     private int helper( int[] cost, int i ) {
         if( i < 2 )
             return cost[i];
         return Math.min( helper( cost, i - 2 ), helper( cost, i - 1 ) )
                + cost[i];
     }

   /**
    * IDEA 2:
    * Use cache to prevent the extra calculation
    *
    * still dynamic programming tho
    *
    * p.s. 剑指源码
    *
    * when input is [1, 100] not working!
    */

    public int minCostClimbingStairs( int[] cost ) {
        int len = cost.length;
        int[] dp = new int[len];

        helper( cost, len - 1, dp );
        return Math.min(dp[len - 2], dp[len - 1]);
    }

    private void helper( int[] cost, int i, int[] dp ) {
        if( i < 2 )
            dp[i] = cost[i];
        else if( dp[i] == 0 ) {
            helper( cost, i - 2, dp );
            helper( cost, i - 1, dp );
            dp[i] = Math.min(dp[i - 2], dp[i - 1]) + cost[i];
        }
    }


    /**
     * IDEA 3:
     * Use normal programming
     *
     * but dynamic thinking
     *
     * p.s. 剑指源码
     *
     */
     public int minCostClimbingStairs( int[] cost ) {
         int len = cost.length;
         int[] dp = new int[len];
         dp[0] = cost[0];
         dp[1] = cost[1];

         for( int i = 2; i < len; ++i ) {
             dp[i] = Math.min(dp[i - 2], dp[i - 1]) + cost[i];
         }

         return Math.min(dp[len - 2], dp[len - 1]);
     }


    /**
     * IDEA 3:
     * Use normal programming
     *
     * but dynamic thinking
     *
     */

     public int minCostClimbingStairs( int[] cost ) {
         int len = cost.length;
         if( len < 2 )
             return 0;

         int[] realMinCost = new int[cost.length + 1];

         int i = 2;
         realMinCost[0] = 0;
         realMinCost[1] = 0;
         while( i < len + 1 ) {
             realMinCost[i] = Math.min( realMinCost[ i - 1 ] + cost[i - 1],
                                        realMinCost[ i - 2 ] + cost[i - 2] );
                                        ++i;
         }
         return realMinCost[len];
     }

    /**
     * IDEA 4:
     * Use normal programming
     *
     * but dynamic thinking
     *
     * p.s. 剑指源码 best
     *
     */
     public int minCostClimbingStairs( int[] cost ) {
         int[] dp = new int[] {cost[0], cost[1]};
         for( int i = 2; i < cost.length; ++i )
             dp[i % 2] = Math.min(dp[0], dp[1]) + cost[i];
         return Math.min(dp[0], dp[1]);
     }


}
