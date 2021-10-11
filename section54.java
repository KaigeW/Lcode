class Solution {

    // Question 539
    /**
     * IDEA 1:
     * Use hashmap to store boolean value at the specific time
     *
     * Time: O(N)
     * Space: O(1)
     *
     * Inspired by 剑指
     *
     */
     public int findMinDifference(List<String> timePoints) {
         boolean[] timeFrame = new boolean[1440];

         for( String a: timePoints ) {
             int time = Integer.parseInt(a.substring(0,2)) * 60
                      + Integer.parseInt(a.substring(3,5));
             if( !timeFrame[time] )
                 timeFrame[time] = true;
             else
                 return 0;
         }
         int prev = -1, first = -1;
         int min = Integer.MAX_VALUE;
         for( int i = 0; i < 1440; ++i ) {
             if( !timeFrame[i] )
                 continue;
             if( first == -1 )
                 first = i;
             if( prev != -1 ) {
                 int difference = i - prev;
                 min = min > difference? difference: min;
             }
             prev = i;
         }
         int difference = 1440 + first - prev;
         return min > difference? difference: min;
     }


    /**
     * IDEA 2:
     * Use hashmap to store boolean value at the specific time
     *
     * Time: O(N)
     * Space: O(1)
     *
     * The one above might be faster!
     *
     * p.s. 剑指源码
     *
     */
     public int findMinDifference(List<String> timePoints) {
         if( timePoints.size() > 1440 ) {
             return 0;
         }

         boolean minuteFlag[] = new boolean[1440];
         for(String time: timePoints) {
             String t[] = time.split(":");
             int min = Integer.parseInt(t[0]) * 60
                     + Integer.parseInt(t[1]);
             if( minuteFlag[min]) {
                 return 0;
             }
             minuteFlag[min] = true;
         }
         return helper(minuteFlag);
     }

     private int helper( boolean[] minuteFlags) {
         int minDiff = minuteFlags.length - 1;
         int prev = -1;
         int first = minuteFlags.length - 1;
         int last = -1;
         for( int i = 0; i < minuteFlags.length; ++i ) {
             if( minuteFlags[i]) {
                 if( prev >= 0 ) {
                     minDiff = Math.min(i - prev, minDiff);
                 }

                 prev = i;
                 first = Math.min(i, first);
                 last = Math.max(i, last);
             }
         }

         minDiff = Math.min( first + minuteFlags.length - last, minDiff);
         return minDiff;
     }
}
