class Solution {

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
