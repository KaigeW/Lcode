class Solution {
    // question 112
    /**
     *  IDEA 1:
     *  Traverse the tree, find out each possible path from root to leaf
     *
     **/
     public boolean hasPathSum(TreeNode root, int targetSum) {
         if( root != null )
             return traverseTree( root, targetSum, root.val );
         return false;
     }

     private boolean traverseTree( TreeNode node, int targetSum, int sum ) {
         if( node == null )
             return false;
         // ATTENTION TO THE LAST CONDITION, it prechecks the equality
         if( node.left == null && node.right == null && targetSum == sum + node.val )
             return true;

         return traverseTree( node.left, targetSum, sum + node.val)
           || traverseTree( node.right, targetSum, sum + node.val);
     }

    /**
     *  IDEA 2:
     *  Traverse the tree, find out each possible path from root to leaf
     *  Different tree traversal but still in in-order
     **/

     public boolean hasPathSum( TreeNode root, int targetSum ) {
         if( root != null )
             return false;
         if( root.left == null && root.right == null && targetSum - root.val == 0)
             return true;
         return hasPathSum(root.left, targetSum - root.val)
           || hasPathSum(root.right, targetSum - root.val);
     }

    // question 118?
    /**
     *  IDEA 1:
     *  Just List
     **/

     public List<List<Integer>> generate(int numRows) {
         List rtn = new ArrayList<ArrayList<Integer>>();
         List nums = new ArrayList<Integer>();
         if( numRows > 0 ) {
             nums.add(1);
             rtn.add(new ArrayList<>(nums));
         }
         if( numRows > 1 ) {
             nums.add(1);
             rtn.add(new ArrayList<>(nums));
         }
         numRows -= 2;
         while( numRows-- > 0 ) {
             List temp = new ArrayList<>();
             temp.add(1);
             for( int i = 0; i < nums.size() - 1; ++i )
                 temp.add((int)nums.get(i)+(int)nums.get(i+1));
             temp.add(1);
             nums = temp;
             rtn.add(temp);
         }
         return rtn;
     }


}
