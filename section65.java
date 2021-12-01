class Solution {

    // Question 647
    /**
     * IDEA 1:
     * Have a separate palindrome function to check if a given string is a valid
     *   palindrome, loop thru the whole String to figure out all of the
     *   string combination, and call the separate checking function.
     *
     *   Space: O(1)
     *   Time: O(n^2)
     *
     */
     public int countSubstrings(String s) {
         int count = 0;
         for( int i = 0; i < s.length(); ++i ) {
             for( int j = i + 1; j < s.length() + 1; ++j)
                 if(validPalindrome(s.substring(i,j)))
                     count++;
         }
         return count;
     }

     public boolean validPalindrome(String s) {
         int front = 0;
         int back = s.length() - 1;
         while( front < back ) {
             if( s.charAt(front) == s.charAt(back) ) {
                 front++;
                 back--;
             } else
                 return false;
         }
         return true;
     }

    /**
     * IDEA 2:
     * Loop the string from begining to the end. Consider this as the middle
     *   point, expand the index both ways, and check the palindrome possibility
     *
     *   One point is palindrome can be both odd and even length of the String,
     *   Therefore, we need to consider both cases. i, and (i, i+1)
     *
     *   ps. 剑指源码
     *   Space: O(1)
     *   Time: O(n^2)
     */
     public int countSubstrings(String s) {
         if( s == null || s.length() == 0 )
             return 0;
         int count = 0;
         for( int i = 0; i < s.length(); ++i ) {
             count += countPalindrome(s, i, i);
             count += countPalindrome(s, i, i + 1);
         }
         return count;
     }

     private int countPalindrome(String s, int start, int end) {
         int count = 0;
         while( start >= 0 && end < s.length()
             && s.charAt(start) == s.charAt(end)) {
             count++;
             start--;
             end++;
         }
         return count;
     }

    // Question 648
    /**
     *
     * IDEA 1:
     * For each of the word in the sentence, we loop thru the dictionary word,
     *   use the default substring function to check existence
     *
     * More memory efficient (based on the stats on leetcode)
     *
     */

     public String replaceWords(List<String> dictionary, String sentence) {
         StringBuilder sb = new StringBuilder();
         String[] words = sentence.split(" ");
         String temp;
         boolean found = false;
         for(String word: words) {
             sb.append(' ');
             temp = "";
             for( String dic: dictionary ) {
                 if( word.startsWith(dic) ) {
                     if( temp.isEmpty() )
                         temp = dic;
                     else if( dic.length() < temp.length() )
                         temp = dic;
                     found = true;
                 }
             }
             if( found ) {
                 found = false;
                 sb.append(temp);
             }
             else
                 sb.append(word);
         }
         return sb.deleteCharAt(0).toString();
     }

    /**
     *
     * IDEA 2:
     * Use prefix-tree
     *
     * p.s. 剑指源码
     *
     * More time efficient (based on the stats on leetcode)
     *
     */


     public String replaceWords(List<String> dictionary, String sentence) {
         TrieNode root = buildTrie(dictionary);
         StringBuilder builder = new StringBuilder();

         String[] words = sentence.split(" ");
         for( int i = 0; i < words.length; i++ ) {
             String prefix = findPrefix(root, words[i]);
             if( !prefix.isEmpty() ) {
                 words[i] = prefix;
             }
         }

         return String.join(" ", words);
     }

     static class TrieNode{
         TrieNode children[];
         boolean isWord;

         public TrieNode() {
             children = new TrieNode[26];
         }
     }

     private TrieNode root;


     private TrieNode buildTrie(List<String> dict) {
         TrieNode root = new TrieNode();
         for( String word: dict ) {
             TrieNode node = root;
             for( char ch: word.toCharArray() ) {
                 if( node.children[ch-'a'] == null)
                     node.children[ch-'a'] = new TrieNode();
                 node = node.children[ch - 'a'];
             }

             node.isWord = true;
         }

         return root;
     }

     private String findPrefix( TrieNode root, String word ) {
         TrieNode node = root;
         StringBuilder builder = new StringBuilder();
         for( char ch: word.toCharArray() ) {
             if( node.isWord || node.children[ch-'a'] == null )
                 break;
             builder.append(ch);
             node = node.children[ch - 'a'];
         }
         return node.isWord ? builder.toString(): "";
     }
}
