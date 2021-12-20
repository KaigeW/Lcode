class Solution {

    // Question 93
    /**
     * IDEA 1:
     * Use Backtracking
     *
     */
     public List<String> partition( String s ) {
         List<String> result = new LinkedList<>();
         if( s.length() <= 12 && s.length() >= 4 )
             helper( result, new StringBuilder(), s, 0, 4);
         return result;
     }

     private void helper( List<String> result, StringBuilder sb, String s,
       int start, int remain ) {
         if( start == s.length() && remain == 0 ) {
             result.add(sb.substring(0, sb.length() - 1).toString());
         } else {
             for( int i = 1; i < 4; i++ ) {
                 if( start + i <= s.length() && checkValidaity(s.substring(start, start + i))) {
                     sb.append( s.substring(start, start + i) );
                     sb.append('.');
                     helper(result, sb, s, start + i, remain - 1);
                     sb.delete( sb.length() - i - 1, sb.length());
                 }
             }
         }
     }

     private boolean checkValidaity( String s ) {
         if( s.length() > 1 && (s.charAt(0) == '0' || Integer.parseInt(s) > 255) )
             return false;
         return true;
     }

     /**
      * IDEA 2:
      * Use Backtracking
      *
      * p.s. 剑指源码
      *
      */
      public List<String> restoreIpAddresses( String s ) {
          List<String> result = new LinkedList<>();
          helper(s, 0, 0, "", "", result);

          return result;
      }

      private void helper( String s, int i, int segI, String seg, String ip,
        List<String> result ) {

          if( i == s.length() && segI == 3 && isValidSeg(seg) ) {
              result.add(ip + seg);
          } else if ( i < s.length() && segI <= 3 ) {
              char ch = s.charAt(i);
              if( isValidSeg(seg + ch) ) {
                  helper(s, i + 1, segI, seg + ch, ip, result);
              }

              if( seg.length() > 0 && segI < 3 ) {
                  helper( s, i + 1, segI + 1, "" + ch, ip + seg + ".", result);
              }
          }
      }

      private boolean isValidSeg( String seg ) {
          return Integer.valueOf(seg) <= 255
            && (seg.equals("0") || seg.charAt(0) != '0' );
      }



    // Question 97
    /**
     * IDEA 1:
     * dynamic way
     *
     * Inspired by 剑指
     *
     */
     public boolean isInterleave( String s1, String s2, String s3 ) {
         int len1 = s1.length();
         int len2 = s2.length();
         int len3 = s3.length();

         if( len1 + len2 != len3 )
             return false;

         boolean[][] dp = new boolean[len1 + 1][len2 + 1];

         dp[0][0] = true;

         for( int i = 0; i < len1; ++i ) {
             dp[i + 1][0] = dp[i][0] && s1.charAt(i) == s3.charAt(i);
         }

         for( int j = 0; j < len2; ++j ) {
             dp[0][j + 1] = dp[0][j] && s2.charAt(j) == s3.charAt(j);
         }

         for( int i = 0; i < len1; ++i ) {
             for( int j = 0; j < len2; ++j ) {
                 char ch3 = s3.charAt(i + j + 1);
                 dp[i + 1][j + 1] = dp[i][j + 1] && s1.charAt(i) == ch3
                      || dp[i + 1][j] && s2.charAt(j) == ch3;
             }
         }

         return dp[len1][len2];
     }



    /**
     * IDEA 2:
     * dynamic way
     *
     * Use one array to do this
     */


}
