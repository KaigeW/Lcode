class Solution {

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
}