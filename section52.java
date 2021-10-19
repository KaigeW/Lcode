class Solution {

    // Question 515
    /**
     * IDEA:
     *
     * Time: O(n)
     * Space: O(n)
     */
     public List<Integer> largestValues(TreeNode root) {
         List<Integer> resultList = new ArrayList<>();
         if( root == null )
             return resultList;
         int size = 1;
         Queue<TreeNode> tree = new LinkedList<>();
         tree.offer(root);
         while( !tree.isEmpty() ) {
             int tempSize = 0;
             int max = Integer.MIN_VALUE;
             for( int i = 0; i < size; ++i ){
                 TreeNode node = tree.poll();
                 max = max < node.val? node.val : max;
                 if( node.left != null ) {
                     tempSize++;
                     tree.offer(node.left);
                 }
                 if( node.right != null ){
                     tempSize++;
                     tree.offer(node.right);
                 }
             }
             size = tempSize;
             resultList.add(max);
         }
         return resultList;
     }

    /**
     * IDEA 2:
     *
     * Time: O(n)
     * Space: O(n)
     *
     * p.s. 剑指源码
     */
     public List<Integer> largestValues(TreeNode root) {
         int current = 0;
         int next = 0;
         Queue<TreeNode> queue = new LinkedList<>();
         if( root != null ) {
             queue.offer(root);
             current = 1;
         }

         List<Integer> result = new LinkedList<>();
         int max = Integer.MIN_VALUE;
         while( !queue.isEmpty() ) {
             TreeNode node = queue.poll();
             current--;
             max = Math.max(max, node.val);

             if( node.left != null ) {
                 queue.offer(node.left);
                 next++;
             }

             if(node.right != null ) {
                 queue.offer(node.right);
                 next++;
             }

             if( current == 0 ) {
                 result.add(max);
                 max = Integer.MIN_VALUE;
                 current = next;
                 next = 0;
             }
         }
         return result;
     }

     /**
      * IDEA 3:
      * Use two Queues to represent two different levels
      *
      * Time: O(n)
      * Space: O(n)
      *
      * p.s. 剑指源码
      *
      */
      public List<Integer> largestValues(TreeNode root) {
          Queue<TreeNode> queue1 = new LinkedList<>();
          Queue<TreeNode> queue2 = new LinkedList<>();
          if( root != null ) {
              queue1.offer(root);
          }
          List<Integer> result = new LinkedList<>();
          int max = Integer.MIN_VALUE;
          while( !queue1.isEmpty() ) {
              TreeNode node = queue1.poll();
              max = Math.max(max, node.val);

              if( node.left != null )
                  queue2.offer(node.left);

              if( node.right != null )
                  queue2.offer(node.right);

              if( queue1.isEmpty() ) {
                  result.add(max);
                  max = Integer.MIN_VALUE;

                  Queue<TreeNode> temp = queue1;
                  queue1 = queue2;
                  queue2 = temp;
              }
          }
          return result;
      }
}
