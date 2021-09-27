class Solution {

    // Question 862
    /**
     * IDEA:
     * Use two pointer to pointing at the same index,
     */
     public int numSubarrayProductLessThanK(int[] nums, int k) {
         int front = 0;
         int back = 0;
         int product = nums[0];
         int count = 0;
         while( front <= back && front <= nums.length - 1
             && back <= nums.length - 1 ) {
                 // if the product is smaller than k, we can increase count
                 //   based on the current numbers in the array
                 if( product < k ) {
                     count += (back - front + 1);
                     ++back;
                     // if we're not at the end, we can move the back to the
                     //   right
                     if( back <= nums.length - 1)
                         product *= nums[back];
                 }
                 // else we start removing the element in front, to keep window
                 //   slide
                 else {
                     // one special scenario is when front == back, and the
                     //   product is greater, we can move the back pointer, to
                     //   break the dead loop
                     if( front == back ) {
                         ++back;
                         if( back <= nums.length - 1)
                             product *= nums[back];
                     }
                     else {
                         product /= nums[front];
                         ++front;
                     }
                 }
         }
         return count;
     }

    /**
     * IDEA 2:
     * Different two pointer approach
     * ps. 剑指源码
     */
     public int numSubarrayProductLessThanK(int[] nums, int k) {
         long product = 1;
         int left = 0;
         int count = 0;
         for( int right = 0; right < nums.length; ++right ) {
             product *= nums[right];
             while( left <= right && product >= k ) {
                 product /= nums[left++];
             }
             count += right >= left? right - left + 1 : 0;
         }
         return count;
     }


}
