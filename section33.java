class Solution {
    // question 322
    /**
     * IDEA:
     * dynamic programming: 0-1 backpack
     *
     * p.s. 剑指源码
     */
     public int coinChange( int[] coins, int amount ) {
         int[] dp = new int[target + 1];
         Arrays.fill( dp, target + 1);
         dp[0] = 0;

         for( int coin: coins ) {
             for( int j = target; j >= 1; j-- ) {
                 for( int k = 1; k * coin <= j; ++k ) {
                     dp[j] = Math.min(dp[j], dp[j - k * coin] + k);
                 }
             }
         }
         return dp[target] > target ? -1: dp[target];
     }

    /**
     * IDEA 1:
     * dynamic programming: 0-1 backpack
     *
     * p.s. 剑指源码
     */
     public int coinChange( int[] coins, int target ) {
         int[] dp = new int[target + 1];
         for( int i = 1; i <= target; ++i ) {
             dp[i] = target + 1;
             for( int coin: coins ) {
                 if( i >= coin )
                     dp[i] = Math.min(dp[i], dp[i - coin] + 1);
             }
         }

         return dp[target] > target? -1: dp[target];
     }
}
