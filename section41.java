class Solution {
    // question 413
    /**
     *  IDEA 1:
     *  Just List, it will show the pattern
     *
     * e.g. [1,2,3,4] it has 2 3s, 1 4. So 2 + 1 = 3
     **/
     public int numberOfArithmeticSlices(int[] nums) {
         if( nums.length >= 3 ){
             int count = 0;
             int tempCount = 0;
             int pattern = nums[1] - nums[0];
             for( int i = 1; i < nums.length; ++i ) {
                 if ( nums[i] - nums[i - 1] == pattern )
                     tempCount++;
                 else {
                     pattern = nums[i] - nums[i - 1];
                     while( --tempCount > 0 ) {
                       count += tempCount;
                     }
                     tempCount++;
                 }
             }
             while( --tempCount > 0 ) {
               count += tempCount;
             }
             tempCount++;
             return count;
         }
         return 0;
     }

     /**
      *  IDEA 2:
      *  dynamic programming? TODO
      **/

      public int numberOfArithmeticSlices(int[] nums) {
          if(nums.length < 3)
              return 0;
          int ans[] = new int[nums.length];
          for(int i = 2; i < nums.length; i++){
              if(nums[i-1] - nums[i-2] == nums[i]-nums[i-1])
                  ans[i] = ans[i-1]+1;
          }
          int sum = 0;
          for(int i = 2; i < ans.length; i++)
              sum += ans[i];
          return sum;
      }
}
