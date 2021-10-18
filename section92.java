class Solution {

    // Question 919
    /**
     * IDEA:
     *
     * Inspire to 剑指源码, but could be a bit simpler
     */
     static class CBTInserter {

         private Queue<TreeNode> tree;
         private TreeNode root;

         public CBTInserter(TreeNode root) {
             tree = new LinkedList<>();
             this.root = root;
             tree.offer(root);
             while( tree.peek().left != null && tree.peek().right != null ) {
                 TreeNode node = tree.poll();
                 tree.offer(node.left);
                 tree.offer(node.right);
             }
         }

         public int insert(int val) {
             while( !tree.isEmpty() ) {
                 TreeNode current = tree.peek();
                 if( current.left != null ) {
                     if( current.right != null ) {
                         tree.poll();
                     } else {
                         tree.offer(current.left);
                         current.right = new TreeNode(val, null, null);
                         tree.offer(current.right);
                         return current.val;
                     }
                 } else {
                     current.left = new TreeNode(val, null, null);
                     tree.offer(current.left);
                     return current.val;
                 }
             }
         }

         public TreeNode get_root() {
             return this.root;
         }
     }

    /**
     * IDEA 2:
     *
     * p.s. 剑指源码
     */
     static class CBTInserter {

         private Queue<TreeNode> tree;
         private TreeNode root;

         public CBTInserter(TreeNode root) {
             tree = new LinkedList<>();
             this.root = root;
             tree.offer(root);
             while( tree.peek().left != null && tree.peek().right != null ) {
                 TreeNode node = tree.poll();
                 tree.offer(node.left);
                 tree.offer(node.right);
             }
         }

         public int insert(int val) {
             TreeNode current = tree.peek();
             if( current.left == null ) {
                 current.left = new TreeNode(val, null, null);
             } else if( current.right == null ) {
                 tree.poll();
                 current.right = new TreeNode(val, null, null);
                 tree.offer(current.left);
                 tree.offer(current.right);
             }
             return current.val;
         }

         public TreeNode get_root() {
             return this.root;
         }
     }
}
