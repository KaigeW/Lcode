class Solution {

    // Question 873
    /**
     * IDEA:
     * Use dynamic
     *
     * p.s. 剑指源码
     *
     */
     public int lenLongestFibSubseq(int[] A) {
         Map<Integer, Integer> hash = new HashMap<>();
         for( int i = 0; i < A.length; ++i ) {
             hash.put(A[i], i);
         }

         int[][] dp = new int[A.length][A.length];
         int result = 2;

         for( int i = 1; i < A.length; ++i ) {
             for( int j = 0; j < i; j++ ) {
                 int k = hash.getOrDefault( A[i] - A[j], -1 );
                 dp[i][j] = k >= 0 && k < j? dp[j][k] + 1: 2;

                 result = Math.max(result, dp[i][j]);
             }
         }

         return result > 2? result: 0;

     }

    // Question 875
    /**
     * IDEA:
     * Use binary search to search for the perfect speed
     *
     * Inspired by 剑指
     *
     */
     public int minEatingSpeed(int[] piles, int H) {
         int min = 1, max = 1;
         for( int temp: piles ) {
             max = temp > max ? temp : max;
         }

         while( min <= max ) {
             int mid = (min + max) / 2;
             int hours = getTime(piles, mid);
             if( hours <= H ) {
                 if ( mid == 1 || getTime(piles, mid - 1) > H)
                     return mid;
                 max = mid - 1;
             }
             else
                 min = mid + 1;
         }
     }

     private int getTime( int[] piles, int speed ) {
         int hours = 0;
         for( int temp: piles ) {
             hours += temp % mid != 0? 1 + temp / mid: temp / mid;
         }
         return )hours;


         // calculate the ceiling... a faster approach 剑指源码

         // int hours = 0;
         // for( int pile: piles ) {
         //     hours += (pile + speed - 1) / speed;
         // }
         // return hours;
     }

}
