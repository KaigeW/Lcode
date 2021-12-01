class Solution {

    // Question 35
    /**
     * IDEA: division method
     */
     public int searchInsert(int[] nums, int target) {
         int front = 0, back = nums.length - 1;
         if( back == -1 )
             return -1;
         int mid;
         while( front < back ) {
             mid = ( front + back ) / 2;
             if( target < nums[mid] )
                 back = mid - 1;
             else if( target == nums[mid])
                 return mid;
             else
                 front = mid + 1;
         }
         if( target <= nums[front] )
             return front;
         return front + 1;
     }

     /**
      * IDEA: division method
      *
      * p.s. 剑指源码
      *
      */

      public int searchInsert(int[] nums, int target) {
          int left = 0;
          int right = nums.length - 1;
          while( left <= right ) {
              int mid = (left + right) / 2;
              if( nums[mid] >= target ) {
                  if( mid == 0 || nums[mid - 1] < target ) {
                      return mid;
                  }
                  right = mid - 1;
              } else {
                  left = mid + 1;
              }
          }
          return nums.length;
      }

}
