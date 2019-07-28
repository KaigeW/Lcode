class Solution {

    // Question 26
    /**
     * IDEA :
     * Need to make first several chars unique, and return the length
     * Used two pointers, one to represent the unique index, one to represent
     * array Index
     */
     public int removeDuplicates(int[] nums) {
         // Time: O(N)
         //
         // Space: O(1)
         //
         if( nums.length == 0 )
             return 0;
         int start = 0;
         int index = 1;
         while( index < nums.length ){
             if( nums[start] != nums[index] ){
                 nums[++start] = nums[index];
             }
             index++;
         }
         return ++start;
     }
}
