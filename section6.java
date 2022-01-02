class Solution {

    // Question 56
    /**
     * IDEA 1:
     * Sort the array first, then merge thru the array
     */
     public int[][] merge( int[][] intervals ) {
         Arrays.sort( intervals, (i1, i2) -> i1[0] - i2[0] );

         LinkedList<int[]> merged = new LinkedList<>();
         int i = 1;
         merged.add(intervals[0]);
         while( i < intervals.length ) {
             int[] last = (int[]) merged.getLast();
             if( last[1] >= intervals[i][0] ) {
                 // mergeable
                 if( last[1] < intervals[i][1])
                     last[1] = intervals[i][1];
             } else {
                 // unmergeable
                 merged.add(intervals[i]);
             }
             ++i;
         }
         int[][] result = new int[merged.size()][];
         return merged.toArray(result);
     }


    /**
     * IDEA 2:
     * Sort the array first, then merge thru the array using a different tech
     *
     * p.s. 剑指源码
     *
     */
     public int[][] merge( int[][] intervals ) {
         Arrays.sort( intervals, (i1, i2) -> i1[0] - i2[0] );

         List<int[]> merged = new LinkedList<>();
         int i = 0;
         while( i < intervals.length ) {
             int[] temp = intervals[i]; // was creating a new array, waste memory
             int j = i + 1;
             while( j < intervals.length && intervals[j][0] <= temp[1] ) {
                 // serves as a fastener for loop one, it still goes thru the
                 // loop one time
                 temp[1] = Math.max(temp[1], intervals[j][1]);
                 j++;
             }

             merged.add(temp);
             i = j;
         }

         int[][] result = new int[merged.size()][];
         return merged.toArray(result);
     }

}
