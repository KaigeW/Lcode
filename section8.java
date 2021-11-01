class Solution {

    // Question 76
    /**
     * TODO:
     * IDEA:
     * Use two pointer to pointing at the
     */
     public String minWindow( String s, String t ) {
         Map<Character, Integer> charToCount = new HashMap<>();
         for(char ch: t.toCharArray()) {
             charToCount.put(ch, charToCount.getOrDefault(ch, 0) + 1);
         }

         int count = charToCount.size();
         int start = 0, end = 0, minStart = 0, minEnd = 0;

         int minLength = Integer.MAX_VALUE;
         while( end < s.length() || (count == 0 && end == s.length())) {
             if( count > 0 ) {
                 char endCh = s.charAt(end);
                 if( charToCount.containsKey(endCh)) {
                     if( charToCount.get(endCh) == 0 ) {
                         count--;
                     }
                 }
                 end++;
             } else {
             if( end - start < minLength) {
                 minLength = end - start;
                 minStart = start;
                 minEnd = end;
             }
             char startCh = s.charAt(start);
             if( charToCount.containsKey(startCh) ) {
                 charToCount.put(startCh, charToCount.get(startCh) + 1);
                 if( charToCount.get(startCh) == 1)
                     count++;
             }
             start++;
         }
         return minLength < Integer.MAX_VALUE?
                  s.substring(minStart, minEnd):"";
     }
}
