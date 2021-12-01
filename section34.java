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
     * Another way to count the 1s, use i & i - 1
     * ( i always has one more 1 comparing to i & i - 1 )
     * e.g.
     *     i = 1100,   i - 1 = 1011; i & i - 1 --> 1000, it canceled out a '1'
     *     i = 1000,   i - 1 = 0111; i & i - 1 --> 0000, it canceled out Another
     *                     1 and became a 0!
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
     * Integer i is has one more 1 than i & (i - 1)
     * Therefore
     * ps. 剑指源码
     * O(n)
     */
     public int[] countBits( int n ) {
         int[] rtn = new int[n + 1];
         for( int i = 1; i <= n; ++i ) {
             rtn[i] = rtn[i & (i - 1)] + 1;
         }
         return rtn;
     }

    /**
     * IDEA 4:
     * Based on the previous experience, we know that
     *   Odd i is i/2 in binary form shifting left one digit and, set the right
     *   most to 1 ->  e.g. 3 -> 11, 1 -> 01
     *   Even i is i/2 in binary form shifting left one digit
     *                 e.g. 1 -> 1, 2 -> 10
     * ps. 剑指源码
     * O(n)
     */
     public int[] countBits( int n ) {
         int[] rtn = new int[n + 1];
         for( int i = 1; i <= n; ++i ) {
             rtn[i] = rtn[i >> 1] + (1 & i);
         }
         return rtn;
     }



}
