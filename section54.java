class Solution {

    // Question 538
    /**
     * IDEA 1:
     * traverse the tree using a reverse-in-order order, since its a binary
     *   search tree, right side is always bigger than the left side, using the
     *   recursive
     *
     * This approach is more time efficient
     *
     * Time: O(n)
     * Space: O(1)
     *
     */
     public TreeNode convertBST(TreeNode root) {
         replaceValueWithSum( root, 0 );
     }

     private int replaceValueWithSum(TreeNode node, int value){
         if( node == null )
             return 0;
         int right = replaceValueWithSum(node.right, value);
         node.val += right == 0? value: right;
         int left = replaceValueWithSum(node.left, node.val);
         return left == 0? node.val: left;
     }

    /**
     * IDEA 2:
     * Same idea, but not using the recursive, using stack instead
     *
     * This approach is more memory efficient
     *
     * Time: O(n)
     * Space: O(logN)
     *
     * p.s. 剑指源码
     *
     */
     public TreeNode convertBST(TreeNode root) {
         Stack<TreeNode> stack = new Stack<>();
         TreeNode cur = root;
         int sum = 0;
         while( cur != null || !stack.isEmpty() ) {
             while( cur != null ) {
                 stack.push(cur);
                 cur = cur.right;
             }
             cur = stack.pop();
             sum += cur.val;
             cur.val = sum;
             cur = cur.left;
         }
         return root;
     }


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

    // Question 540
    /**
     * IDEA 1:
     *
     * Use XOR
     *
     */
     public int singleNonDuplicate( int[] nums ) {
         int x = nums[0];
         for( int i = 1; i < nums.length; ++i )
             x =^ nums[i];
         return x;
     }



    /**
     * IDEA 2:
     *
     * Use Binary search
     *
     * Inspired by 剑指
     * Ideally should be faster than above if a large amount is reached
     *
     */
     public int singleNonDuplicate( int[] nums ) {
         int front = 0, back = nums.length - 1;
         if( back == 0 )
             return nums[0];
         if( nums[back] != nums[back - 1] )
             return nums[back];
         back /= 2;
         while( front <= back ) {
             int mid = (front + back) / 2;
             if( mid == 0 )
                 return nums[0];
             if( nums[mid * 2] != nums[mid * 2 + 1] ) {
                 if( nums[mid * 2] != nums[mid * 2 - 1] )
                     return nums[mid * 2];
                 back = mid;
             }
             else
                 front = mid;
         }
         return -1;
     }

    /**
     * IDEA 3:
     *
     * Use Binary search
     *
     * p.s. 剑指源码
     *
     */
     public int singleNonDuplicate( int[] nums ) {
         int front = 0, back = nums.length / 2;
         while( front <= back ) {
             int mid = ( front + back ) / 2;
             int i = mid * 2;
             if( i < nums.length - 1 && nums[i] != nums[i + 1] ) {
                 if( mid == 0 || nums[i - 2] == nums[i - 1]) {
                     return nums[i];
                 }
                 right = mid - 1;
             } else
                 left = mid + 1;
         }
         return nums[nums.length - 1];
     }


}
