class Solution {

    // Question 1122
    /**
     * IDEA 1:
     * Using Counting Sort
     */
     public int[] relativeSortArray(int[] arr1, int[] arr2) {
         int min = Integer.MAX_VALUE;
         int max = Integer.MIN_VALUE;

         int[] result = new int[arr1.length];

         for( int i : arr1 ) {
             if( min > i ) min = i;
             if( max < i ) max = i;
         }

         int[] counts = new int[max - min + 1];

         for( int i : arr1 ) {
             counts[i - min]++;
         }

         int index = 0;
         // loop thru the privilege array first
         for( int i: arr2 ) {
             while( counts[i - min] != 0 ) {
                 result[index++] = i;
                 counts[i - min]--;
             }
         }

         // ascending order for the rest
         for( int i = 0; i < counts.length; ++i ) {
             if( counts[i] != 0 ) {
                 while( counts[i] != 0 ) {
                     result[index++] = i + min;
                     counts[i]--;
                 }
             }
         }
         return result;
     }

    /**
     * IDEA 2:
     * Using Counting Sort
     *
     * p.s. 剑指源码
     *
     */
     public int[] relativeSortArray(int[] arr1, int[] arr2) {
         int[] counts = new int[1001];
         for( int num: arr1) {
             counts[num]++;
         }

         int i = 0;
         for( int num: arr2 ) {
             while( counts[num] > 0 ) {
                 arr1[i++] = num;
                 counts[num]--;
             }
         }

         for( int num = 0; num < counts.length; num++) {
             while( counts[num] > 0 ) {
                arr1[i++] = num;
                counts[num] --;
             }
         }
         return arr1;
     }
}
