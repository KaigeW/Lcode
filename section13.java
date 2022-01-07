class Solution {

    // Question 124
    /**
     * IDEA:
     * If the sum of a set(parent, left, and right) is greater than or equivalent
     *   to the value of the parent, we can set the max value at that node to be
     *   the sum. Otherwise, leave the value unchanged. And figure out the max
     *
     *   FIGURE OUT A WAY TODO
     *
     */

     public int maxPathSum(TreeNode root) {
         int[] maxValue = new int[] {Integer.MIN_VALUE};
         int leftMax = 0, rightMax = 0;
         if( root.left != null )
             leftMax = maxPossibleSum( root.left, maxValue );
         if( root.left != null )
             rightMax = maxPossibleSum( root.right, maxValue );
         int rootMax = root.val + leftMax > root.val?
             root.val + leftMax : root.val;
         rootMax = rootMax + rightMax > rootMax?
             rootMax + rightMax : rootMax;
         return maxValue[0] < rootMax? rootMax : maxValue[0];
     }

     private int maxPossibleSum( TreeNode node, int[] maxValue ) {
         if( node == null )
             return 0;
         int leftMax = maxPossibleSum( node.left, maxValue );
         int rightMax = maxPossibleSum( node.right, maxValue );
         int lrMax = Math.max(leftMax, rightMax);
         lrMax = node.val + lrMax > node.val?
           node.val + lrMax : node.val;
         maxValue[0] = maxValue[0] < lrMax? lrMax : maxValue[0];
         return lrMax;
     }

    /**
     * IDEA:
     *
     * p.s. 剑指源码
     *
     */
     public int maxPathSum(TreeNode root) {
         int[] maxSum = {Integer.MIN_VALUE};
         dfs(root, maxSum);
         return maxSum[0];
     }

     private int dfs( TreeNode root, int[] maxSum ) {
         if( root == null )
             return 0;

         int[] maxSumLeft = { Integer.MIN_VALUE};
         int left = Math.max(0, dfs(root.left, maxSumLeft));

         int[] maxSumRight = { Integer.MIN_VALUE};
         int right = Math.max(0, dfs(root.right, maxSumRight));

         maxSum[0] = Math.max(maxSumLeft[0], maxSumRight[0]);
         maxSum[0] = Math.max(maxSum[0], root.val + left + right);

         return root.val + Math.max(left, right);
     }

    // Question 125
    /**
     * IDEA:
     * Use two pointer to loop from back and front, until they are equal or
     * back < front
     */
     public boolean isPalindrome(String s) {
         int front = 0;
         int back = s.length() - 1;
         while( front < back ) {
             char a = s.charAt(front);
             char b = s.charAt(back);
             if( !((a >= 'A' && a <= 'Z') || (a >= 'a' && a <= 'z') || (a >= '0' && a <= '9')) ) {
                 front++;
                 continue;
             }
             if( !((b >= 'A' && b <= 'Z') || (b >= 'a' && b <= 'z') || (b >= '0' && b <= '9'))) {
                 back--;
                 continue;
             }
             if( a >= 'A' && a <= 'Z')
                 a += 'z' - 'Z';

             if( b >= 'A' && b <= 'Z')
                 b += 'z' - 'Z';
}
             if( a == b ) {
                 front++;
                 back--;
             }
             else
                 return false;
         }
         return true;
     }

    /**
     * IDEA:
     * Same idea, different implementation
     *   My implementation is faster!
     *   ps. 剑指源码
     */
     public boolean isPalindrome( String s ) {
         int i = 0;
         int j = s.length() - 1;
         while( i < j ) {
             char ch1 = s.charAt(i);
             char ch2 = s.charAt(j);
             if(!Character.isLetterOrDigit(ch1)) {
                 i++;
             } else if (!Character.isLetterOrDigit(ch2)) {
                 j--;
             } else {
                 ch1 = Character.toLowerCase(ch1);
                 ch2 = Character.toLowerCase(ch2);
                 if( ch1 != ch2 ) {
                     return false;
                 }
                 i++;
                 j--;
             }
         }
         return true;
     }

    // Question 127
    /**
     * IDEA 1:
     *
     * breadth first search
     *
     * p.s. 剑指源码
     *
     */
     public int ladderLength(String beginWord, String endWord,
       List<String> wordList) {
         Queue<String> queue1 = new LinkedList<>();
         Queue<String> queue2 = new LinkedList<>();

         Set<String> notVisited = new HashSet<>( wordList );
         int length = 1;
         queue1.add(beginWord);

         while( !queue1.isEmpty() ) {
             String word = queue1.remove();
             if( word.equals(endWord) ) {
                 return length;
             }

             List<String> neighbors = getNeighbors(word);
             for( String neighbor: neighbors ) {
                 if( notVisited.contains(neighbor) ) {
                     queue2.add(neighbor);
                     notVisited.remove(neighbor);
                 }
             }

             if( queue1.isEmpty() ) {
                 length++;
                 queue1 = queue2;
                 queue2 = new LinkedList<>();
             }
         }

         return 0;
     }

     private List<String> getNeighbors(String word) {
         List<String> neighbors = new LinkedList<>();
         char[] chars = word.toCharArray();
         for( int i = 0; i < chars.length; ++i ) {
             char old = chars[i];

             for( char ch = 'a'; ch <= 'z'; ++ch ) {
                 if( old != ch ) {
                     chars[i] = ch;
                     neighbors.add(new String(chars));
                 }
             }

             chars[i] = old;
         }

         return neighbors;
     }

     /**
      * IDEA 2:
      *
      * breadth first search bidirectionaly
      *
      * p.s. 剑指源码
      *
      * faster but memory...
      *
      */
      public int ladderLength( String beginWord, String endWord,
        List<String> wordList) {
          Set<String> notVisited = new HashSet<>(wordList);
          if( !notVisited.contains(endWord) ) {
              return 0;
          }

          Set<String> set1 = new HashSet<>();
          Set<String> set2 = new HashSet<>();

          int length = 2;
          set1.add(beginWord);
          set2.add(endWord);

          notVisited.remove(endWord);
          while( !set1.isEmpty() && !set2.isEmpty() ) {
              if( set2.size() < set1.size() ) {
                  Set<String> temp = set1;
                  set1 = set2;
                  set2 = temp;
              }

              Set<String> set3 = new HashSet<>();
              for( String word: set1 ) {
                  List<String> neighbors = getNeighbors(word);
                  for( String neighbor: neighbors ) {
                      if( set2.contains(neighbor) ) {
                          return length;
                      }
                      if( notVisited.contains(neighbor) ) {
                          set3.add(neighbor);
                          notVisited.remove(neighbor);
                      }
                  }
              }

              length++;
              set1 = set3;
          }
          return 0;
      }

    // Question 129
    /**
     * IDEA:
     * preorder traverse the tree
     *
     * Space: O(1)
     * Time: O(n)
     */
     public int sumNumbers(TreeNode root) {
         int sum = 0;
         int[] rtn = {0};
         traverse(root, sum, rtn);
         return rtn[0];
     }

     public void traverse(TreeNode root, int sum, int[] rtn) {
         if( root == null )
             return;
         if( root.left == null && root.right == null ) {
             rtn[0] += sum * 10 + root.val;
             return;
         }
         traverse(root.left, sum * 10 + root.val, rtn);
         traverse(root.right, sum * 10 + root.val, rtn);
     }

    /**
     * IDEA2:
     * p.s. 剑指源码
     * TODO: 理解
     * Space: O(1)
     * Time: O(n)
     */
}
