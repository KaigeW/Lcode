class Solution {

    // Question 438
    /**
     *  IDEA 1:
     *  Used sliding window method to check if each character can form a
     *    permutation
     *  Time: O(n) loop once for s
     *  Space: O(n , 1) not sure why 1 ...
     *  ps. 剑指源码
     **/
     public List<Integer> findAnagrams(String s, String p) {
         List<Integer> resultList = new ArrayList<Integer>();
         if( s.length() < p.length() )
             return resultList;

         int[] chars = new int[26];
         for( int i = 0; i < p.length(); ++i ) {
             chars[p.charAt(i) - 'a']++;
             chars[s.charAt(i) - 'a']--;
         }

         if( isAllZero(chars) )
             resultList.add(0);

         for( int i = p.length(); i < s.length(); ++i ) {
             chars[s.charAt(i) - 'a']--;
             chars[s.charAt(i - p.length()) - 'a']++;
             if( isAllZero(chars) )
                 resultList.add(i - p.length() + 1);
         }
         return resultList;
     }

     public boolean isAllZero(int[] nums) {
         for( int num: nums )
             if( num != 0 )
                 return false;
         return true;
     }

}
