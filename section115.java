class Solution {

    // Question 1143
    /**
     * IDEA 1:
     * Use Backtracking
     *
     * p.s. 剑指源码
     *
     */
     public int longestCommonSubsequence( String text1, String text2 ) {
         int len1 = text1.length();
         int len2 = text2.length();

         int[][] dp = new int[len1 + 1][len2 + 1];

         for( int i = 0; i < len1; ++i ) {
             for( int j = 0; j < len2; ++j ) {
                 if( text1.charAt(i) == text2.charAt(j) ) {
                     dp[i + 1][j + 1] = dp[i][j] + 1;
                 } else {
                     dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                 }
             }
         }

         return dp[len1][len2];
     }

    /**
     * IDEA 2:
     * Use Backtracking
     *
     * p.s. 剑指源码
     * Use 2d array to save space
     *
     */
     public int longestCommonSubsequence( String text1, String text2 ) {
         int len1 = text1.length();
         int len2 = text2.length();

         if( len1 < len2 )
             return longestCommonSubsequence(text2, text1);

         int[][] dp = new int[2][len2 + 1];

         for( int i = 0; i < len1; ++i ) {
             for( int j = 0; j < len2; ++j ) {
                 if( text1.charAt(i) == text2.charAt(j) ) {
                     dp[(i + 1) % 2][j + 1] = dp[i%2][j] + 1;
                 } else {
                     dp[(i + 1) % 2][j + 1] = Math.max(dp[i%2][j + 1], dp[(i + 1)%2][j]);
                 }
             }
         }

        return dp[len1%2][len2];
     }

    /**
     * IDEA 3:
     * Use Backtracking
     *
     * p.s. 剑指源码
     * Use 1d array to save space
     *
     */
     public int longestCommonSubsequence( String text1, String text2 ) {
         int len1 = text1.length();
         int len2 = text2.length();

         if( len1 < len2 )
             return longestCommonSubsequence(text2, text1);

         int[] dp = new int[len2 + 1];

         for( int i = 0; i < len1; ++i ) {
             int prev = dp[0];
             for( int j = 0; j < len2; ++j ) {
                 int cur;
                 if( text1.charAt(i) == text2.charAt(j) ) {
                     cur = prev + 1;
                 } else {
                     cur = Math.max(dp[j], dp[j + 1]);
                 }

                 prev = dp[j + 1];
                 dp[j + 1] = cur;
             }
         }

         return dp[len2];
     }

}
