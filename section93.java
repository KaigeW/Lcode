class Solution {

    // Question 926
    /**
     *  IDEA 1:
     *  dynamic programming
     *
     *  Will cause stackOverFlow if string is insanely long
     *
     **/

     public int minFlipsMonoIncr(String s) {
         int[] potentialTarget = new int[] { Integer.MAX_VALUE };
         helper( 0, s, '0', 0, potentialTarget);
         return potentialTarget[0];
     }

     private void helper( int index, String s, char prev, int modi,
       int[] result) {
         if( index == s.length() ) {
             result[0] = Math.min(result[0], modi);
             return;
         }

         if( s.charAt(index) == prev ) {
             helper( index + 1, s, prev, modi, result );
         } else {
             if( prev == '0' ) {
                 helper( index + 1, s, '1', modi, result );
                 helper( index + 1, s, '0', modi + 1, result );
             }
             else
                 helper( index + 1, s, '1', modi + 1, result );
         }
     }



    /**
     *  IDEA 2:
     *  dynamic programming
     *
     *  Use cache to store the result first
     *
     * Inspired by 剑指
     *
     **/
     public int minFlipsMonoIncr(String s) {
         int len = s.length();

         int[] one = new int[len];
         int[] zero = new int[len];

         if( len == 0 || len == 1 )
             return 0;
         if( s.charAt(0) == '0' ) {
             one[0] = 1;
             zero[0] = 0;
         } else {
             one[0] = 0;
             zero[0] = 1;
         }
         for( int i = 1; i < s.length(); ++i ) {
             if( s.charAt(i) == '0' ) {
                 one[i] = Math.min(one[i - 1], zero[i - 1]) + 1;
                 zero[i] = zero[i - 1];
             } else {
                 one[i] = Math.min(one[i - 1], zero[i - 1]);
                 zero[i] = zero[i - 1] + 1;
             }
         }
         return Math.min(one[s.length() - 1], zero[s.length() - 1]);
     }


    /**
     *  IDEA 3:
     *  dynamic programming
     *
     *  Use cache to store the result first
     *
     *  SuperAdvancedMemoryEffient size 2 array
     *
     *  Inspired by 剑指
     *
     **/
     public int minFlipsMonoIncr(String s) {
         int len = s.length();

         if( len == 0 || len == 1 )
             return 0;

         int[][] dp = new int[2][2];
         char ch = s.charAt(0);
         dp[0][0] = ch == '0'? 0 : 1;
         dp[1][0] = ch == '1'? 0 : 1;

         boolean flip = true;

         for( int i = 1; i < len; ++i ) {
             ch = s.charAt(i);
             if( flip ) {
                 int prev0 = dp[0][0];
                 int prev1 = dp[1][0];

                 dp[0][1] = prev0 + (ch == '0'? 0 : 1);
                 dp[1][1] = Math.min(prev0, prev1) + (ch == '1'? 0 : 1);
             } else {
                 int prev0 = dp[0][1];
                 int prev1 = dp[1][1];

                 dp[0][0] = prev0 + (ch == '0'? 0 : 1);
                 dp[1][0] = Math.min(prev0, prev1) + (ch == '1'? 0 : 1);
             }
             flip = flip? false:true;
         }

         return flip? Math.min(dp[0][0], dp[1][0]) : Math.min(dp[0][1], dp[1][1]);
     }
}
