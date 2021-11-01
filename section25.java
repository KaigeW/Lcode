class Solution {

    // Question 242
    /**
     * IDEA 1:
     * Create an array to represent a hashmap, loop thru two strings, and check
     *   the hashmap to determine wheather if the they are anagram
     */
     public boolean isAnagram(String str1, String str2) {
         if( str1 == null || str2 == null ||
             str1.length() != str2.length() )
             return false;
         int[] charCount = new int[26];
         for( int i = 0; i < str1.length(); ++i ) {
             charCount[str1.charAt(i)-'a']++;
             charCount[str2.charAt(i)-'a']--;
         }

         for( int i = 0; i < 26; ++i ) {
             if( charCount[i] != 0 )
                 return false;
         }
         return true;
     }

    /**
     * IDEA 2:
     * Similar to above,different looping technique
     * ps. 剑指源码
     *
     */
     public boolean isAnagram(String str1, String str2) {
         if( str1.length() != str2.length() ) {
             return false;
         }

         int[] count = new int[26];
         for( char ch: str1.toCharArray()) {
             count[ch - 'a']++;
         }

         for( char ch: str2.toCharArray()) {
             if( count[ch - 'a'] == 0 )
                 return false;
             count[ch - 'a']--;
         }
         return true;
     }

}
