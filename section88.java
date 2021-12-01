class Solution {

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
         return hours;


         // calculate the ceiling... a faster approach 剑指源码
         
         // int hours = 0;
         // for( int pile: piles ) {
         //     hours += (pile + speed - 1) / speed;
         // }
         // return hours;
     }

}
