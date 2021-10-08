class Solution {

    // Question 953
    /**
     * IDEA 1:
     * Use hashmap to store the order, and manually compare each char
     *
     * Time: O(mn)
     * Space: O(1)
     *
     * Inspired by 剑指
     *
     */
     public boolean isAlienSorted(String[] words, String order) {
         if( words == null || words.length == 1 )
             return true;
         int[] charOrder = new int[26];
         for( int i = 0; i < order.length(); ++i ){
             charOrder[order.charAt(i) - 'a'] = i;
         }

         for( int i = 0; i < words.length - 1; ++i ) {
             for( int j = 0; j < words[i].length(); ++j ) {
                 if( j >= words[i + 1].length() )
                     return false;
                 if( charOrder[words[i].charAt(j) - 'a'] <
                     charOrder[words[i + 1].charAt(j) - 'a'] ){
                     break;
                 } else if (charOrder[words[i].charAt(j) - 'a'] ==
                            charOrder[words[i + 1].charAt(j) - 'a']) {
                     continue;
                 } else
                     return false;
             }
         }
         return true;
     }

    /**
     * IDEA 2:
     * Use hashmap to store the order, and manually compare each char
     *
     * Memory is less than above
     *
     * Time: O(mn)
     * Space: O(1)
     *
     * Inspired by 剑指
     *
     */
     public boolean isAlienSorted(String[] words, String order) {
         int[] orderArray = new int[order.length()];
         for( int i = 0; i < order.length(); ++i) {
             orderArray[order.charAt(i) - 'a'] = i;
         }
         for( int i = 0; i < words.length - 1; ++i ) {
             if( !isSorted(words[i], words[i + 1], orderArray)) {
                 return false;
             }
         }
         return true;
     }

     private boolean isSorted(String word1, String word2, int[] order) {
         int i = 0;
         for(; i < word1.length() && i < word2.length(); ++i) {
             char ch1 = word1.charAt(i);
             char ch2 = word2.charAt(i);
             if( order[ch1 - 'a'] < order[ch2 - 'a']) {
                 return true;
             }
             if( order[ch1 - 'a'] > order[ch2 - 'a']) {
                 return false;
             }
         }

         return i == word1.length();
     }

}
