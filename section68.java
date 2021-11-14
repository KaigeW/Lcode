class Solution {
    // Question 676
    /**
     * IDEA:
     * Use prefix tree
     *
     * p.s. 剑指源码
     *
     */
     class MagicDictionary {

         static class TrieNode {
             public TrieNode[] children;
             public boolean isWord;

             public TrieNode() {
                 children = new TrieNode[26];
             }
         }

         private TrieNode root;

         public MagicDictionary() {
             root = new TrieNode();
         }

         public void buildDict(String[] dictionary) {
             for( String word: dictionary ) {
                 TrieNode node = root;
                 for( char ch: word.toCharArray() ) {
                     if( node.children[ch-'a'] == null)
                         node.children[ch-'a'] = new TrieNode();
                     node = node.children[ch - 'a'];
                 }
                 node.isWord = true;
             }
         }

         public boolean search(String searchWord) {
             return dfs(root, searchWord, 0, 0);
         }

         private boolean dfs( TrieNode root, String word, int i , int edit ) {
             if( root == null )
                 return false;
             if( root.isWord && i == word.length() && edit == 1 )
                 return true;
             if( i < word.length() && edit <= 1 ) {
                 boolean found = false;
                 for( int j = 0; j < 26 && !found; j++ ) {
                     int next = j == word.charAt(i) - 'a' ? edit: edit + 1;
                     found = dfs( root.children[j], word, i + 1, next);
                 }
                 return found;
             }

             return false;
         }

    }

    // Question 680
    /**
     * IDEA:
     * Use two pointer to pointing, and using a loop to keep checking for the
     *   next possible comparing bits
     *
     * This will not work for the following situation:
     *   cupucu
     *   In this case, deleting c from the left and deleting u from the right,
     *     will both result in a successful case in the following compare, but
     *     only if we delete u from the right, will make this word a palindrome
     */
     public boolean validPalindrome(String s) {
         int front = 0;
         int back = s.length() - 1;
         boolean once = false;
         while( front < back ) {
             if( s.charAt(front) == s.charAt(back) ) {
                 front++;
                 back--;
             }
             else {
                 if( once )
                     return false;
                 if( front + 1 <= back ) {
                     if( s.charAt(front + 1) == s.charAt(back) ) {
                         front += 2;
                         back--;
                         once = true;
                     } else if( s.charAt(front) == s.charAt(back - 1) ) {
                         front ++;
                         back -= 2;
                         once = true;
                     } else {
                         return false;
                     }
                 }
             }
         }
         return true;
     }

    /**
     * IDEA 2:
     * Use two pointer to pointing, and using a loop to keep checking for the
     *   next possible breaking index, if found, will check both possibilities
     *   and return if each possible situation will return a palindrome, if not
     *   if means the s itself is a palindrome, just need to check if the front
     *   reaches the middle index or not
     *   ps. 剑指源码
     */

     public boolean validPalindrome(String s) {
         int front = 0;
         int back =0;
         for( ; front < s.length() / 2; ++front, --back )
             if( s.charAt(front) != s.charAt(back) )
                 break;

         return front == s.length() / 2 || isPalindrome(s, front, back - 1)
             || isPalindrome(s, front + 1, back);
     }

     private boolean isPalindrome(String s, int front, int back) {
         while( front < back ) {
             if(s.charAt(front) != s.charAt(back)) {
                 break;
             }
             front++;
             back--;
         }
         return front >= back;
     }

}
