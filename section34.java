class Solution {

    // Question 338
    /**
     * IDEA 1:
     * count the digits of 1 in each number
     * O(kn)
     */
     public int[] countBits(int n) {
         int[] rtn = new int[n + 1];
         for( int i = 0; i < n + 1; ++i )
             rtn[i] = countOne(i);
         return rtn;
     }

     public int countOne( int num ) {
         int rtn = 0;
         while( num != 0 ) {
             if( num % 2 == 1 )
                 rtn++;
             num /= 2;
         }
         return rtn;
     }

    /**
     * IDEA 2:
     * Another way to count the zeros, use i & i - 1
     * e.g.
     *     i = 1100,   i - 1 = 1011; i & i - 1 --> 1000, it canceled out a '1'
     *     i = 1000,   i - 1 = 0111; i & i - 1 --> 0000, it canceled out Another
     *                      and became a 0!
     * O(kn)
     * ps. 剑指源码
     */
     public int[] countBits( int n ) {
         int[] rtn = new int[n + 1];
         for( int i = 0; i <= n; ++i ) {
             int j = i;
             while( j != 0 ) {
                 rtn[i]++;
                 j = j & (j - 1);
             }
         }
         return rtn;
     }

    /**
     * IDEA 3:
     * Based on the previous experience, we know that
     * Integer i is has 1 more binary digit than i & (i - 1)
     * Therefore
     * ps. 剑指源码
     */
     public int[] countBits( int n ) {
         int[] rtn = new int[n + 1];
         for( int i = 1; i <= n; ++i ) {
             rtn[i] = rtn[i & (i - 1)] + 1;
         }
         return rtn;
     }

}
