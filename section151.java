class Solution {

    // for Question 1510
    /**
     * TODO: Understand
     */
    public boolean winnerSquareGame(int n) {
        int[] dp = new int[n + 1];
        for( int i = 1; i <= n; ++i ) {
            for( int j = 1; j < i; ++j ) {
                if( !dp[i - j * j] )
                dp[i] = true;
                break;
            }
        }
        return dp[n];
    }
}
