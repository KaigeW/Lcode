class Solution {

    // Question 131
    /**
     * IDEA 1:
     * Use Backtracking
     *
     * p.s. 剑指源码
     *
     */
     public List<List<String>> partition( String s ) {
         List<List<String>> result = new LinkedList<>();
         helper( result, 0, new LinkedList<String>(), s );

         return result;
     }

     public void helper( List<List<String>> result, int start,
       LinkedList<String> temp, String s) {
         if( start == s.length() ) {
             result.add(new LinkedList<>( temp ));
         } else {
             for( int i = start; i < str.length(); ++i ) {
                 if( isPalindrome(s, start, i)) {
                     temp.add(s.substring(start, i + 1));
                     helper( result, i + 1, temp, s );
                     temp.removeLast();
                 }
             }
         }
     }

     private boolean isPalindrome( String str, int start, int end ) {
         while( start < end ) {
             if( str.charAt( start++ ) != str.charAt(end--) ) {
                 return false;
             }
         }
         return true;
     }



    // Question 132
    /**
     * IDEA 1:
     * Dynamic programming
     *
     * p.s. 剑指源码
     *
     */

     public int minCut( String s ) {
         int len = s.length();
         boolean[][] isPal = new boolean[len][len];

         for( int i = 0; i < len; i++ ) {
             for( int j = 0; j <= i; ++j ) {
                 char ch1 = s.charAt(i);
                 char ch2 = s.charAt(j);
                 if( ch1 == ch2  && (i <= j + 1 || isPal[j + 1][i - 1]))
                     isPal[j][i] = true;
             }
         }

         int[] dp = new int[len];
         for( int i = 0; i < len; ++i ) {
             if( isPal[0][i] )
                 dp[i] = 0;
             else {
                 dp[i] = i;
                 for( int j = 1; j <= i; j++ ) {
                     if( isPal[j][i]) {
                         // 若string本身不是一个回文，
                         // 我们查所有从j(1-->i)开始到i的substring中是否是回文
                         // 最糟糕情况 就是 dp中前一个index的值加上 1
                         //
                         dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                     }
                 }
             }
         }
         return dp[len - 1];
     }


    // Question 136
    /**
     * IDEA 1:
     * Each integer can be covered to a 32 bits Binary
     *   we can add all of the numbers up, bit by bit, into an array
     *   eventually, we can check if each digits can be fully divided by 2
     *   and have the numbers back
     * O(33n)
     */
     public int singleNumber( int[] nums ) {
         int[] bitSum = new int[32];
         for( int num: nums ) {
             for( int i = 0; i < 32; ++i )
                 bitSum[i] += ( num >> ( 31 - i ) ) & 1;
         }

         int result = 0;
         for( int i = 0; i < 32; ++i ) {
             result = (result << 1) + bitSum[i] % 2;
         }

         return result;
     }

    /**
     * IDEA 2:
     *  Use XOR
     *    e.g. [1,2,3,4,5,4,3,2,1]
     *    (1 ^ 1) ^ (2 ^ 2) ^ (3 ^ 3) ^ (4 ^ 4) ^ 5
     */
     public int singleNumber( int[] nums ) {
         int result = 0;
         for( int num : nums )
             result ^= num;
         return result;
     }

     // Question 137
     /**
      * IDEA 1:
      * Each integer can be covered to a 32 bits Binary
      *   we can add all of the numbers up, bit by bit, into an array
      *   eventually, we can check if each digits can be fully divided by 3
      *   and have the numbers back
      * O(33n)
      */
      public int singleNumber( int[] nums ) {
          int[] bitSum = new int[32];
          for( int num: nums ) {
              for( int i = 0; i < 32; ++i )
                  bitSum[i] += ( num >> ( 31 - i ) ) & 1;
          }

          int result = 0;
          for( int i = 0; i < 32; ++i ) {
              result = (result << 1) + bitSum[i] % 3;
          }

          return result;
      }

}
