class Solution {

    // Question 84
    /**
     * IDEA:
     * Using two loops to represent the left edge and right edge, we need to
     *   figure out the minimum height and corresponding width and calculate the
     *   related area. Return the max area
     *
     * Space: O(1)
     * Time: O(n^2)
     * Time Limit Exceeded
     *
     * ps. 剑指源码
     */
     public int largestRectangleArea(int[] heights) {
         int maxArea = 0;
         for( int i = 0; i < heights.length; ++i) {
             int min = heights[i];
             for( int j = i; j < heights.length; ++j) {
                 min = Math.min( min, heights[j]);
                 int area = min * (j - i + 1);
                 maxArea = Math.max(maxArea, area);
             }
         }
         return maxArea;
     }


    /**
     * IDEA 2:
     * Using one loop to represent the height. Inside of these two loops, use
     *   two loops to figure out the left and right index that has the higher or
     *   equal height(which forms a rectangle) calculate the area, and Return
     *   the largest one
     *
     * Space: O(1)
     * Time: O(n^2), worst
     *
     * ps. 剑指源码
     */
     public int largestRectangleArea(int[] heights) {
         int maxArea = 0;
         for( int i = 0; i < heights.length; ++i) {
             int leftIndex = i - 1;
             int rightIndex = i + 1;
             while( leftIndex >= 0 ) {
                 if( heights[leftIndex] < heights[i] )
                     break;
                 --leftIndex;
             }
             ++leftIndex;
             while( rightIndex < heights.length ) {
                 if( heights[rightIndex] < heights[i] )
                     break;
                 ++rightIndex;
             }
             --rightIndex;
             maxArea = Math.max( maxArea,
                 (rightIndex - leftIndex + 1) * heights[i] );
         }
         return maxArea;
     }

    /**
     * IDEA 3:
     * divide and conquer, find out the index of the smallest height, and use it
     *   as pivot to figure out the area on the left, right, and its minimum.
     *   Keep on dividing, until find the smallest unit, then return. Will use
     *   the recurse functionality here.
     *
     * Space: O(1)
     * Time: O(nlogn)
     *
     * ps. 剑指源码
     */
     public int largestRectangleArea(int[] heights) {
         return helper(heights, 0, heights.length);
     }

     private int helper( int[] heights, int start, int end ) {
         if( start == end )
             return 0;

         if( start + 1 == end )
             return heights[start];

         int minIndex = start;
         for( int i = start + 1; i < end; i++ ) {
             if( heights[i] < heights[minIndex]) {
                 minIndex = i;
             }
         }

         int area = ( end - start ) * heights[minIndex];
         int left = helper(heights, start, minIndex);
         int right = helper(heights, minIndex + 1, end);

         area = Math.max(area, left);
         return Math.max(area, right);
     }

    /**
     * IDEA 4:
     *
     * TODO
     *
     * Space: O()
     * Time: O()
     *
     * ps. 剑指源码
     */
     public int largestRectangleArea(int[] heights) {
         Stack<Integer> stack = new Stack<>();
         stack.push(-1);
         int maxArea = 0;
         for( int i = 0; i < heights.length; ++i) {
             while( stack.peek() != -1
               && heights[stack.peek()] >= heights[i] ) {
                 int height = heights[stack.pop()]
                 int width = i - stack.peek() - 1;
                 maxArea = Math.max(maxArea, height * width);
               }
               stack.push(i);
         }

         while( stack.peek() != -1 ) {
             int height = heights[stack.pop()]
             int width = heights.length - stack.peek() - 1;
             maxArea = Math.max(maxArea, height * width);
         }
         return maxArea;
     }

}
