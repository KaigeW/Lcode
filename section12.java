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
         if( root == null )
             return false;
         if( root.left == null && root.right == null
           && targetSum - root.val == 0)
             return true;
         return hasPathSum(root.left, targetSum - root.val)
           || hasPathSum(root.right, targetSum - root.val);
     }

    // question 113
    /**
     *  IDEA :
     *  Based on the previous idea, return the list of list object of the result
     *
     *  Space: O(n)
     *  Time: O(n)
     */
     public List<List<Integer>> pathSum( TreeNode root, int targetSum ) {
         List<List<Integer>> resultList = new LinkedList<List<Integer>>();
         List<Integer> current = new LinkedList<>();
         traversePathSum(root, targetSum, resultList, current);
         return resultList;
     }

     public void traversePathSum( TreeNode node, int targetSum,
       List<List<Integer>> resultList, List<Integer> currentList ) {
         if( node == null )
             return;

         if( node.left == null && node.right == null
           && targetSum - node.val == 0 ) {
             currentList.add(new Integer(node.val));
             resultList.add(new LinkedList(currentList));
             // thought i can optimize this
             currentList.remove(currentList.size() - 1);
         }
         currentList.add(new Integer(node.val));
         traversePathSum( node.left, targetSum - node.val, resultList,
           currentList);
         traversePathSum( node.right, targetSum - node.val, resultList,
           currentList);
         currentList.remove(currentList.size() - 1);
     }



    // question 115
    /**
     *  IDEA 1:
     *  Dynamic programming double list TODO
     *
     *  p.s 剑指源码
     *
     **/
     public int numDistinct( String s, String t ) {
         int[][] dp =new int[s.length() + 1][t.length() + 1];
         dp[0][0] = 1;

         for( int i = 0; i < s.length(); ++i ) {
             dp[i + 1][0] = 1;
             for( int j = 0; j <= i && j < t.length(); ++j ) {
                 if( s.charAt(i) == t.charAt(j) ) {
                     dp[i + 1][j + 1] = dp[i][j] + dp[i][j + 1];
                 } else {
                     dp[i + 1][j + 1] = dp[i][j + 1];
                 }
             }
         }

         return dp[s.length()][t.length()];
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
