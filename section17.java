class Solution {

    // Question 167
    /**
     * IDEA: Same as question 1
     Counter case: If array has < 2 elements, will return an empty array
     * Used HashMap to store the (arrayVal, index) pair
     * Will have a loop walk through each element in the nums
     * First, will check if any pairing data(target - currentVal)
     * of the currentVal is inside of the set.
     *
     * If so, create a new array with two index and return it
     *
     * Otherwise, add the currentVal into hashmap.
     *
     * If the loop terminates, means no matching pairs exist
     * return the empty array
     */
     public int[] twoSum(int[] nums, int target){
         Map<Integer, Integer> maps = new HashMap<>();
         // Time: O(n)
         // In the worst case scenario, containsKey and put will be called n
         // times, which will be O(2n)
         // Space:O(n)
         // In the worst case scenario, extra hashmap will take n elements
         for( int i = 0; i < nums.length; ++i ){
             if( maps.containsKey(target-nums[i]) )
                 return new int[]{(int)(maps.get(target-nums[i]))+1, ++i};
             else
                 maps.put(nums[i],i);
         }
         return new int[]{};
     }

    /**
     * IDEA 2:
     * Have two pointer pointing to the front and back, since it's having an
     *   increasing pattern, we have have two numbers sum together and compare
     *   it with the target. If greater than the target, we can decrease the
     *   right side index, if less than the target, we can increase the left
     *   side index
     * Time: Worst O(n)
     * Space: O(1)
     * ps. 剑指源码
     */
     public int[] twoSum( int[] nums, int target ) {
         int front = 0;
         int back = nums.length - 1;
         while( front != back ) {
             if( nums[front] + nums[back] == target )
                 return new int[] {front, back};
             else if ( nums[front + nums[back] > target )
                 --back;
             else
                 ++front;
         }
         return null;
     }

}
