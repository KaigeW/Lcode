class Solution {
    // question 173
    /**
     * IDEA 1:
     * Inorder traverse, we might use the stack to save the nodes
     *
     * p.s. 剑指源码
     * TODO write the non-recursive version of in-order traverse
     *
     **/
     static class BSTIterator {

         Stack<TreeNode> stack;
         TreeNode current;

         public BSTIterator(TreeNode root) {
             current = root;
             stack = new Stack<>();
         }

         public int next() {
             while( current != null ) {
                 stack.push(current);
                 current = current.left;
             }
             cur = stack.pop();
             int rtn = cur.val;
             cur = cur.right;
             return rtn;
         }

         public boolean hasNext() {
             return current != null || !stack.isEmpty();
         }
     }
}
