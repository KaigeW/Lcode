class Solution {

    // Question 318
    /**
     * IDEA 1:
     * compare if possible product is bigger than the result
     * Use two hashset to store each of the chars, then add one to Another
     *   if nothing repeat, calculate the result
     *   2215ms 慢成狗
     * Time: O(n^2)
     * Space: O(1)
     */
     public int maxProduct( String[] words ) {
         int result = 0;
         Set<Character> set1 = new HashSet<>();
         Set<Character> set2 = new HashSet<>();
         for( int i = 0; i < words.length; ++i ) {
             for( int j = i + 1; j < words.length; ++j) {
                 int len1 = words[i].length();
                 int len2 = words[j].length();
                 if( len1 * len2 > result ) {
                     boolean flag = true;
                     for( char a : words[i].toCharArray() ) set1.add(a);
                     for( char a : words[j].toCharArray() ) set2.add(a);
                     for( Object a : set1.toArray() )
                         if( !set2.add((char)a) ) {
                             flag = false;
                             break;
                         }
                     if( flag ) result = len1 * len2;
                     set1.clear();
                     set2.clear();
                 }
             }
         }
         return result;
     }

    /**
     * IDEA 2:
     * Since only lower case are included, we can use int[26] to represent
     *   hashset
     *
     * Inspired by 剑指
     *
     * Time: O(n^2)
     * Space: O(n)
     */
     public int maxProduct( String[] words ) {
         boolean[][] flags = new boolean[words.length][26];
         for( int i = 0; i< words.length; ++i ) {
             for( char c: words[i].toCharArray() ) {
                 flags[i][c-'a'] = true;
             }
         }

         int result = 0;
         for( int i = 0; i < words.length; ++i ) {
             for( int j = i + 1; j < words.length; j++ ) {
                 if( words[i].length() * words[j].length() > result ){
                     int k = 0;
                     for( ; k < 26; ++k ) {
                         if( flags[i][k] && flags[j][k] ) {
                             break;
                         }
                     }

                     if( k == 26 ) {
                         int prod = words[i].length() * words[j].length();
                         result = Math.max(result, prod);
                     }
                 }
             }
         }
         return result;
     }

    /**
     * IDEA 3:
     * Based on the previous experience
     *   We can use binary integer as the hashset, which should speed up the
     *   calculation process, 26 boolean comparing vs. 1 bit wise &
     * Time: O(n^2)
     * Space: O(n)
     */
     public int maxProduct( String[] words ) {
         int[] flags = new int[words.length];
         for( int i = 0; i < words.length; ++i ) {
             for( char a: words[i].toCharArray() ) {
                 flags[i] |= 1 << ((a - 'a')) ;
             }
         }

         int result = 0;
         for( int i = 0; i < words.length; ++i ) {
             for( int j = i + 1; j < words.length; j++ ) {
                 if( words[i].length() * words[j].length() > result
                     && ((flags[i] & flags[j]) == 0) )
                         result = words[i].length() * words[j].length();
             }
         }
         return result;
     }

}
