class Solution {

    // Question 258
    /**
     * IDEA 1:
     * Just calculate, the condition is sum of each digit should be less than 10,
     *   Otherwise, sum each digit of the new sum again
     */

     public int addDigits(int num) {
         int sum = 0;
         while( sum < 10 ) {
             while( num > 0 ) {
                 sum += num % 10;
                 num /= 10;
             }
             if( sum < 10 )
                 break;
             num = sum;
             sum = 0;
         }
         return sum;
     }

    /**
     * IDEA 2:
     * Pattern: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 will return the same 0-9
     *            10,11,12,13,14,15,16,17,18 will return the same number above
     *            19,20,21,22,23,24,25,26,27 will repeat the process
     *            28,29.....................
     *            .......................,99
     *           100,101,...................
     *
     * The pattern is every 9 digits will return its digit above. So we can use
     * given number % 9, to figure out its remainder. note. If the remainder
     * is 0, we need to return 9.
     */
     public int addDigits(int num) {
         if( num == 0 )
             return num;
         else if( num % 9 == 0 )
             return 9;
         else
             return num % 9;
     }

}
