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


}
