class Solution {

    // Question 198
    /**
     * IDEA 1:
     * Dynamic programming
     *
     * Too many recursive methods!!
     */

     public int rob(int[] nums) {
         int len = nums.length;
         return Math.max( helper( nums, len - 1), helper(nums, len - 2) );
     }

     private int helper( int[] nums, int index ) {
         if( index >= 0 )
             if( index < 2 )
                 return nums[index];
             else {
                 int a = helper( nums, index - 2 ) + nums[index];

                 if( index > 2 ) {
                     int b = helper( nums, index - 3 ) + nums[index];
                     a = Math.max( a, b );
                 }
                 return a;
             }
         else
             return 0;
     }



    /**
     * IDEA 2:
     * Dynamic programming
     *
     * Use cache
     *
     * p.s. 剑指源码
     *
     */

     public int rob(int[] nums) {
         if( nums.length == 0 )
             return 0;

         int[] dp = new int[nums.length];
         Arrays.fill( dp, -1 );

         helper(nums, nums.length - 1, dp);
         return dp[nums.length - 1];
     }

     private void helper( int[] nums, int i, int[] cache) {
         if( i == 0 )
             cache[i] = nums[0];
         else if ( i == 1 )
             cache[i] = Math.max(nums[0], nums[1]);
         else if ( cache[i] < 0 ) {
             helper( nums, i - 2, cache );
             helper( nums, i - 1, cache );
             cache[i] = Math.max( cache[ i - 1 ], cache[ i - 2 ] + nums[i] );
         }
     }

    /**
     * IDEA 3:
     * Dynamic programming
     *
     * Use cache
     *
     */

     public int rob( int[] nums ) {
         int len = nums.length;
         if( len == 0 )
             return 0;
         int[] best = new int[len];
         best[0] = nums[0];
         if( len > 1 ) {
             best[1] = Math.max(nums[0], nums[1]);
             if( len > 2 ) {
                 for( int i = 2; i < len; ++i ) {
                     best[i] = Math.max( best[i - 2] + nums[i], best[i - 1]);
                 }
             }
         }

         // The if len > 1 condition can be replaced by the following code
         //
         // p.s. 剑指源码
         //
         // if( len > 1 )
         //     best[1] = Math.max(nums[0], nums[1]);
         // for( int i = 2; i < len; ++i )
         //     best[1] = Math.max( best[ i - 2 ] + nums[i], best[i - 1] );

         return best[len - 1];
     }

    /**
     * IDEA 4:
     * Dynamic programming
     *
     * Use a smaller size cache
     *
     * p.s. 剑指源码
     *
     */
     public int rob( int[] nums ) {
         if( nums.length == 0 ) {
             return 0;
         }

         int[] dp = new int[2];
         dp[0] = nums[0];

         if( nums.length > 1 ) {
             dp[1] = Math.max(nums[0], nums[1]);
         }

         for( int i = 2; i < nums.length; ++i ) {
             dp[i % 2] = Math.max(dp[(i - 1) % 2], dp[(i - 2) % 2] + nums[i]);
         }

         return dp[(nums.length - 1) % 2];
     }



    /**
     * IDEA 5:
     * 2d array wow
     *
     * p.s. 剑指源码
     *
     */
     public int rob( int[] nums ) {
         int len = nums.length;
         if( len == 0 )
             return 0;

         int[][] dp = new int[2][2];
         dp[0][0] = 0;
         dp[1][0] = nums[0];

         for( int i = 1; i < len; ++i ) {
             dp[0][i % 2] = Math.max(dp[0][(i - 1) % 2]), dp[1][(i - 1) % 2];
             dp[0][i % 2] = nums[i] + dp[0][(i - 1) % 2];
         }

         return Math.max(dp[0][(len - 1) % 2], dp[1][(len - 1) % 2]);
     }


    // Question 199
    /**
     * IDEA:
     *
     * Time: O(n)
     * Space: O(n)
     */

     public List<Integer> rightSideView(TreeNode root) {
         List<Integer> resultList = new LinkedList<>();
         if( root == null )
             return resultList;
         Queue<TreeNode> tree = new LinkedList<>();
         tree.offer(root);
         int loops = 1;
         int nextLoop = 0;
         while( !tree.isEmpty() ) {
             TreeNode temp = tree.poll();
             if( temp.left != null ){
                 tree.offer(temp.left);
                 nextLoop++;
             }
             if( temp.right != null ){
                 tree.offer(temp.right);
                 nextLoop++;
             }
             --loops;
             if( loops == 0 ) {
                 loops = nextLoop;
                 nextLoop = 0;
                 resultList.add(temp.val);
             }
         }
         return resultList;
     }

    /**
     * IDEA2:
     *
     * Using two queues
     *
     * Time: O(n)
     * Space: O(n)
     *
     * p.s. 剑指源码
     */
     public List<Integer> rightSideView(TreeNode root) {
         List<Integer> view = new LinkedList<>();
         if( root == null ) {
             return view;
         }

         Queue<TreeNode> queue1 = new LinkedList<>();
         Queue<TreeNode> queue2 = new LinkedList<>();
         queue1.offer(root);
         while( !queue1.isEmpty() ) {
             TreeNode node = queue1.poll();
             if( node.left != null ) {
                 queue2.offer(node.left);
             }
             if( node.right != null ) {
                 queue2.offer(node.right);
             }

             if( queue1.isEmpty() ) {
                 view.add(node.val);
                 queue1 = queue2;
                 queue2 = new LinkedList<>();
             }
         }

         return view;
     }

}
