class Solution {
    // question 292
    /**
     *  IDEA 1:
     *  Just List, it will show the pattern
     **/
     // 1, 2, 3 you win
     // 4 you lose
     // 5, 6, 7 you win
     // 8 you lose
     // ...
     // ...

     public boolean canWinNim(int n) {
         if( n >= 0 )
             return n % 4 != 0;
         return false;
     }

    // question 297
    /**
     *  IDEA 1:
     *  Ok in the positive cases, not ok in the negative!!!
     *    Consider things throughly!!!
     **/

     static class Codec {

         private index = 0;
         public String serialize(TreeNode root) {
             if( root == null )
                 return "*";

             return String.valueOf(root.val) + serialize(root.left)
              + serialize(root.right);
         }

         public TreeNode deserialize(String data) {
             char[] cArray = data.toCharArray();
             index = 0;
             return detects(cArray);
         }

         private TreeNode detects(char[] cArray) {
             if( cArray[index] == '*' )
                 return null;

             TreeNode rtn = new TreeNode(cArray[index] - '0');
             ++index;
             rtn.left = detects(cArray);
             ++index;
             rtn.right = detects(cArray);
             return rtn;
         }

     }

    /**
     *  IDEA 2:
     *
     *
     *  p.s. 剑指源码
     **/
     static class Codec {
         public String serialize( TreeNode root ) {
             if( root == null ) {
                 return "#";
             }

             return String.valueOf(root.val) + ","
                      + serialize(root.left) + ","
                      + serialize(root.right);
         }

         public TreeNode deserialize(String data) {
             String[] nodeStrs = data.split(",");
             int[] i = {0};
             return dfs(nodeStrs, i);
         }

         private TreeNode dfs( String[] strs, int[] i ) {
             String str = strs[i[0]];
             i[0]++;

             if( str.equals("#") ) {
                 return null;
             }

             TreeNode node = new TreeNode(Integer.valueOf(str));
             node.left = dfs(strs, i);
             node.right = dfs(strs, i);
             return node;
         }
     }
}
