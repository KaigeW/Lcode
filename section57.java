class Solution {
    // Question 567
    /**
     * IDEA 1:
     * Counter case: It is impossible for a String to contain another String with
        larger size
     * use an int array to count how many times each character show up in str1
     * loop thru the str2 for s2.len - s1.len +1 (repeat) time to get exactly
     * repeat length of str1 strings.
     * for each string, we create the same int array like the beginning, we
     * count how many times each character shows up. And compare this fresh_made
     * int array to the beginning int array. See if they have the same content,
     * if so, it has the permutation, yay
     * if not, return false
     */
    public boolean checkInclusion(String s1, String s2) {
        // Time: O(26n^2)
        // Compare two int[26] arrays for n^2 time
        // Space: O(1)
        // We created 2 int[26] arrays.
        if( s1.length() > s2.length() )
            return false;
        int[] chars = new int[26];
        for( int i = 0; i < s1.length(); ++i )
            chars[s1.charAt(i)-'a']++;
        for( int i = 0; i <= s2.length()-s1.length(); ++i){
            int[] temp = new int[26];
            for( int index = 0; index < s1.length(); ++index )
                temp[s2.charAt(i+index)-'a']++;
            if(match(temp, chars))
                return true;
        }
        return false;
    }

    /**
     * IDEA 2: Instead of creating int array for repeat times in the second
     * phrase, we can just use one instead to decrease the execution time
     */
    public boolean checkInclusion(String s1, String s2) {
        // Time: O(26n^2)
        // Compare two int[26] arrays for n^2 time
        // Space: O(1)
        // We created 2 int[26] arrays.
        if( s1.length() > s2.length() )
            return false;
        int[] chars = new int[26];
        int[] temp = new int[26];
        for( int i = 0; i < s1.length(); ++i ){
            chars[s1.charAt(i)-'a']++;
            temp[s2.charAt(i)-'a']++;
        }
        if(match(temp, chars))
            return true;
        for( int i = 1; i <= s2.length()-s1.length(); ++i){
            temp[s2.charAt(i-1)-'a']--;
            temp[s2.charAt(i+s1.length()-1)-'a']++;
            if(match(temp, chars))
                return true;
        }
        return false;
    }


    public boolean match(int[] chars, int[] temp){
        for( int j = 0; j < 26; ++j )
            if( temp[j] != chars[j] )
                return false;
        return true;
    }

    /**
     * IDEA 3: FANCY ANSWER
     **/
    public boolean checkInclusion(String s1, String s2) {
        // Time: O(26n)
        // ???
        // Space: O(1)
        // We created 2 int[26] arrays.
        if (s1.length() > s2.length())
            return false;
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        int count = 0;
        for (int i = 0; i < 26; i++)
            if (s1map[i] == s2map[i])
                count++;
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            int r = s2.charAt(i + s1.length()) - 'a', l = s2.charAt(i) - 'a';
            if (count == 26)
                return true;
            s2map[r]++;
            if (s2map[r] == s1map[r])
                count++;
            else if (s2map[r] == s1map[r] + 1)
                count--;
            s2map[l]--;
            if (s2map[l] == s1map[l])
                count++;
            else if (s2map[l] == s1map[l] - 1)
                count--;
        }
        return count == 26;
    }

    /**
     * IDEA 4:
     * ps. 剑指源码
     * Time: O(M+N)
     * Space: O(1)
     **/

     public boolean checkInclusion(String s1, String s2) {
         if( s2.length() < s1.length() )
             return false;
         int[] counts = new int[26];

         // reason of having s2 is to initiate the s1 loop, and s2 check
         for( int i = 0; i < s1.length(); ++i ) {
           counts[s1.charAt(i) - 'a']++;
           counts[s2.charAt(i) - 'a']--;
         }
         if( areAllZero(counts))
             return true;

         for( int i = s1.length(); i < s2.length(); ++i ) {
             counts[s2.charAt(i) - 'a']--;
             counts[s2.charAt(i - s1.length()) - 'a']++;
             if( areAllZero(counts)) {
                 return true;
             }
         }

         return false;
     }

     private boolean areAllZero(int[] counts) {
       for( int count: counts ) {
           if( count != 0 ) {
               return false;
           }
       }
       return true;
     }
}
