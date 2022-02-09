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



     // question 416

     /**
      * IDEA:
      * backtracking, but result in exceeding time limit when having a large set
      *
      * brutal force
      *
      */
      public boolean canPartition( int[] nums ) {
          int sum = 0;
          for( int num: nums ) {
              sum += num;
          }

          if( sum % 2 == 1 )
              return false;

          List<List<Integer>> combine = new LinkedList<>();
          helper( nums, combine, new LinkedList<Integer>(), sum / 2, 0);

          if( !combine.isEmpty() ) {
              for( List<Integer> list: combine )
                  System.out.println(list.toString());
              return true;
          }
          return false;
      }

      public void helper( int[] nums, List<List<Integer>> combine, LinkedList<Integer> list, int target, int index ) {
          if( target == 0 )
              combine.add(new LinkedList<>(list));
          else if( index < nums.length ) {
              helper( nums, combine, list, target, index + 1 );

              list.add(nums[index]);
              helper( nums, combine, list, target - nums[index], index + 1);
              list.removeLast();
          }
      }


     /**
      *  IDEA 1:
      *
      *  TODO
      *  Dynamic Programming
      *
      * p.s. 剑指源码
      *
      **/
      public boolean canPartition( int[] nums ) {
          int sum = 0;
          for( int num: nums ) {
              sum += num;
          }

          if( sum % 2 == 1 )
              return false;

          return subsetSum( nums, sum / 2 );
      }

      private boolean subsetSum( int[] nums, int target ) {
          Boolean[][] dp = new Boolean[nums.length + 1][ target + 1 ];
          return helper( nums, dp, nums.length, target );
      }

      private boolean helper( int[] nums, Boolean[][] dp, int i, int j) {
          if( dp[i][j] == null ) {
              if( j == 0 )
                  dp[i][j] = true;
              else if( i == 0 )
                  dp[i][j] = false;
              else {
                  dp[i][j] = helper(nums, dp, i - 1, j);
                  if( !dp[i][j] && j >= nums[i - 1]) {
                      dp[i][j] = helper( nums, dp, i - 1, j - nums[i - 1]);
                  }
              }
          }
          return dp[i][j];
      }


      /**
       *  IDEA 2:
       *
       *  Dynamic Programming
       *
       *  p.s. leetcode bbs
       *
       **/
       public boolean canPartition( int[] nums ) {
           int sum = 0;
           for( int num: nums ) {
               sum += num;
           }

           // if the sum is odd, no need to go further
           if( (sum & 1) == 1 )
               return false;

           int target = sum / 2;

           boolean[][] dp = new boolean[nums.length + 1][target + 1];

           // if the target is 0, it would always return true
           for( int i = 0; i < nums.length + 1; ++i )
               dp[i][0] = true;

           // if the we didn't select number, it means the target will never be
           //   reached. Because it would always be 0
           for( int j = 1; j < target + 1; ++j )
               dp[0][j] = false;


           for( int i = 1; i < nums.length + 1; ++i ) {
               for( int j = 1; j < target + 1; ++j ) {
                   // if last number has an approach that reaches the target,
                   //   we don't need to do anything then! (achieved by the
                   //   following statement and left side of the or )
                   dp[i][j] = dp[i - 1][j];

                   // when the target is bigger than the current number
                   //   we get the chance to add it into our set

                   // second condition is needed when the previous number does
                   //   not have an approach of that reaches the target
                   if( j >= nums[i - 1] )
                       dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i - 1]];
               }
           }

           // in this case, any possible num combination will make this value true
           return dp[nums.length][target];
       }
}
