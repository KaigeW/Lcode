class Solution {

    // Question 62
    /**
     * IDEA 1:
     * Dynamic Programming
     *
     * same as 剑指 idea2
     *
     */

     public int uniquePaths(int m, int n) {
         int[][] pathCount = new int[m][n];

         for( int i = 0; i < m; ++i )
             pathCount[i][0] = 1;

         for( int i = 0; i < n; ++i )
             pathCount[0][i] = 1;

         for( int x = 1; x < m; ++x ) {
             for( int y = 1; y < n; ++y ) {
                 pathCount[x][y] = pathCount[x - 1][y] + pathCount[x][y - 1];
             }
         }

         return pathCount[m - 1][n - 1];
     }

    /**
     * IDEA 2:
     * Dynamic Programming
     *
     * p.s. 剑指源码
     *
     */
     public int uniquePaths( int m, int n ) {
         int[][] dp = new int[m][n];
         return helper( m - 1, n - 1, dp );
     }

     private int helper( int i, int j, int[][] dp ) {
         if( dp[i][j] == 0 ) {
             if( i == 0 || j == 0 )
                 dp[i][j] = 1;
             else
                 dp[i][j] = helper( i - 1, j, dp) + helper( i, j - 1, dp);
         }

         return dp[i][j];
     }

    /**
     * IDEA 3:
     * Dynamic Programming
     *
     * p.s. 剑指源码
     *
     */

     public int uniquePaths( int m, int n ) {
         int[] dp = new int[n];
         Arrays.fill(dp, 1);
         for( int i = 1; i < m; ++i ) {
             for( int j = 1; j < n; ++j ) {
                 dp[j] += dp[j - 1];
             }
         }
         return dp[n-1];
     }


    // Question 64
    /**
     * IDEA 1:
     * Dynamic Programming
     *
     * Similar to 剑指 idea 1
     *
     */
     public int minPathSum( int[][] grid ) {
         int[][] dp = new int [grid.length][grid[0].length];

         // initialize the fixed mined length
         dp[0][0] = grid[0][0];
         for( int i = 1; i < grid.length; ++i )
             dp[i][0] = dp[i - 1][0] + grid[i][0];
         for( int i = 1; i < grid[0].length; ++i )
             dp[0][i] = dp[0][i - 1] + grid[0][i];

         for( int i = 1; i < grid.length; ++i ) {
             // line 86 87 can be replaced by the below command 剑指源码
             // dp[i][0] = dp[i - 1][0] + grid[i][0];
             for( int j = 1; j < grid[0].length; ++j ) {
                 dp[i][j] = grid[i][j] +
                   (dp[i - 1][j] > dp[i][j - 1] ? dp[i][j - 1] : dp[i - 1][j]);
             }
         }

         return dp[grid.length - 1][grid[0].length - 1];
     }



    /**
     * IDEA 2:
     * Dynamic Programming
     *
     * Use 1D array(smaller memory) use at all
     *
     * same as 剑指 idea2
     *
     */
     public int minPathSum( int[][] grid ) {
         int[] dp = new int[grid[0].length];
         dp[0] = grid[0][0];
         for( int i = 1; i < grid[0].length; ++i )
             dp[i] = dp[i - 1] + grid[0][i];

         for( int i = 1; i < grid.length; ++i ) {
             dp[0] = dp[0] + grid[i][0];
             for( int j = 1; j < grid[0].length; ++j ) {
                 dp[j] = grid[i][j] +
                   ( dp[j] > dp[j - 1]? dp[j - 1] : dp[j] );
             }
         }
         return dp[grid[0].length - 1];
     }


    /**
     * IDEA 3:
     * Dynamic Programming
     *
     * No extra memory use at all
     *
     */
     public int minPathSum( int[][] grid ) {
         for( int i = 1; i < grid[0].length; ++i )
             grid[0][i] = grid[0][i - 1] + grid[0][i];

         for( int i = 1; i < grid.length; ++i ) {
             grid[i][0] = grid[i - 1][0] + grid[i][0];
             for( int j = 1; j < grid[0].length; ++j ) {
                 grid[i][j] = grid[i][j] +
                   (grid[i - 1][j] > grid[i][j - 1] ? grid[i][j - 1] : grid[i - 1][j]);
             }
         }

         return grid[grid.length - 1][grid[0].length - 1];
     }

    // Question 67
    /**
     * IDEA 1:
     * Using addition rules to add each digit into StringBuilder, and reverse
     *   after finished
     */
     public String addBinary(String a, String b) {
         StringBuilder sb = new StringBuilder();
         int i = a.length() - 1;
         int j = b.length() - 1;
         boolean carry = false;
         int buf = 0;
         while( i >= 0 || j >= 0 ) {
             if( carry )
                buf++;
             carry = false;
             if( i >= 0 && a.charAt(i) == '1' )
                buf++;
             if( j >= 0 && b.charAt(j) == '1' )
                buf++;
             if( buf >= 2 ) {
                 carry = true;
                 buf -= 2;
             }
             sb.append((char)('0' + buf));
             buf = 0;
             --i;
             --j;
         }
         if( carry )
             sb.append('1');
         return sb.reverse().toString();
     }

    /**
      * IDEA 2:
     * 剑指源码
     */
     public String addBinary( String a, String b ) {
         StringBuilder result = new StringBuilder();
         int i = a.length() - 1;
         int j = b.length() - 1;
         int carry = 0;
         while( i >= 0 || j >= 0 ) {
             int digitA = i >= 0 ? a.charAt(i--) - '0' : 0;
             int digitB = j >= 0 ? b.charAt(j--) - '0' : 0;
             int sum = digitA + digitB + carry;
             carry = sum >= 2? 1 : 0;
             sum = sum >= 2? sum - 2: sum;
             result.append(sum);
         }
         if( carry == 1 )
             result.append(1);
         return result.reverse().toString();
     }


    // Question 69
    /**
     * IDEA 1:
     * Inspired by 剑指
     */
     public int mySqrt( int n ) {
         int start = 1, end = n;
         while( start <= end ) {
             int mid = start + ( end - start ) / 2;
             if( mid <= n / mid ) {
                 if( (mid + 1) > n / (mid + 1) )
                     return mid;
                 start = mid + 1;
             }
             else
                 end = mid - 1;
         }
         return 0;
     }

}
