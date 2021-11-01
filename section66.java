class Solution {
    // question 653
    /**
     * IDEA 1:
     * Pre-order traverse, use a hashset to store the value, and check the
     *   matched pair as traverse goes
     *
     * Space: O(n)
     * Time: O(n)
     *
     **/
     public boolean findTarget(TreeNode root, int k) {
         Set<Integer> nums = new HashSet<>();
         return checkTarget(root, k, nums);
     }

     public boolean checkTarget(TreeNode node, int k, Set<Integer> nums) {
         if( node == null )
             return false;
         if( nums.contains(node.val))
             return true;
         nums.add(k - node.val);
         return checkTarget(node.left, k, nums) || checkTarget(node.right, k, nums);
     }

    /**
     * IDEA 2:
     * Pre-order traverse, use a hashset to store the value, and check the
     *   matched pair as traverse goes, using stack
     *
     * p.s. 剑指源码
     *
     * Space: O(n)
     * Time: O(n)
     *
     **/
     public boolean findTarget( TreeNode root, int k ) {
         Set<Integer> set = new HashSet<>();
         Stack<TreeNode> stack = new Stack<>();
         TreeNode cur = root;
         while( cur != null || !stack.isEmpty() ) {
             while( cur != null ) {
                 stack.push(cur);
                 cur = cur.left;
             }

             cur = stack.pop();
             if( set.contains( k - cur.val)) {
                 return true;
             }
             set.add(cur.val);
             cur = cur.right;
         }
         return false;
     }
}
