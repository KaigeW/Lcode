class Solution {

    // Question 814
    /**
     * IDEA 1:
     * use post-order to traverse the tree
     * ps. 剑指源码
     *
     */
     public TreeNode pruneTree(TreeNode root) {
         if( root == null )
             return root;

         root.left = pruneTree(root.left);
         root.right = pruneTree(root.right);

         if( root.left == null && root.right == null && root.val == 0)
             return null;

         return root;
     }

    /**
     * IDEA 2:
     * TODO: use while loop to try
     *
     */
     public TreeNode pruneTree(TreeNode root) {
         if( root == null )
             return root;

         root.left = pruneTree(root.left);
         root.right = pruneTree(root.right);

         if( root.left == null && root.right == null && root.val == 0)
             return null;

         return root;
     }

}
