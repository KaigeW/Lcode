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

    // Question 77
    /**
     * IDEA:
     * p.s. 剑指源码
     */
     public List<List<Integer>> combine( int n, int k ) {
         List<List<Integer>> result = new LinkedList<>();
         LinkedList<Integer> combination = new LinkedList<>();
         helper(n, k, l, combination, result);

         return result;
     }

     private void helper( int n, int k, int i, LinkedList<Integer> combination,
       List<List<Integer>> result ) {
         if( combination.size() == k ) {
             result.add(new LinkedList<>(combination));
         } else if ( i <= n ) {
             helper(n, k, i + 1, combination, result);

             combination.add(i);
             helper(n, k, i + 1, combination, result);
             combination.removeLast();
         }
     }

    // Question 78
    /**
     * IDEA:
     * Use arrayList to loop
     */

     public List<List<Integer>> subsets( int[] nums ) {
         List<List<Integer>> lists = new ArrayList<List<Integer>>();
         List<List<Integer>> tempList = new ArrayList<List<Integer>>();
         lists.add(new ArrayList<Integer>());

         for( int num: nums ) {
             for( List<Integer> list: lists ) {
                 List<Integer> temp = new ArrayList(list);
                 temp.add((Integer)num);
                 tempList.add(temp);
             }
             lists.addAll(tempList);
             tempList.clear();
         }
         return lists;
     }


    /**
     * IDEA 1:
     * p.s. 剑指源码
     *
     * Not really understand this... not efficient than above...
     *
     */
     public List<List<Integer>> subsets( int[] nums ) {
         List<List<Integer>> result = new LinkedList<>();
         if( nums.length == 0 ) {
             return result;
         }

         helper( nums, 0, new LinkedList<Integer>(), result);
         return result;
     }

     private void helper( int[] nums, int index, LinkedList<Integer> subset,
       List<List<Integer>> result ) {
         if( index == nums.length ) {
             result.add( new LinkedList<>(subset));
         } else if ( index < nums.length ) {
             helper(nums, index + 1, subset, result);

             subset.add(nums[index]);
             helper( nums, index + 1, subset, result);
             subset.removeLast();
         }
     }

    // Question 80
    /**
     * IDEA:
     * Once find the duplicate, find the next different piece,
     *   right before the next iteration, make sure the value exists for twice
     */
     public int removeDuplicates(int[] nums) {
         if( nums.length <= 2 )
             return nums.length;
         int possibleK = 0;
         // for loop
         for( int i = 0; i < nums.length; ++i ){
             // check for the first appearance of duplication
             if( i + 1 < nums.length && nums[i] == nums[i + 1] ) {
                 // call function that returns the index that is not the duplicate, and start the loop from there
                 nums[possibleK] = nums[i];
                 ++possibleK;
                 int index = i + 2;
                 while( index < nums.length && nums[i] == nums[index] )
                     index++;
                 i = index - 1;
             }
             nums[possibleK] = nums[i];
             ++possibleK;
         }
         return possibleK;
     }

     /**
      * IDEA 1:
      * Sliding window. Logically, inside of a loop, the current number has to be larger than the one before previous one
      * e.g. 1,1,2,2,2,3,3,3,3,4
      *      t t t t f t t f f t
      *
      * p.s. leetcode源码
      */
      public int findMaxLength(int[] nums) {
          int i = 0;
          for (int n : nums)
              if (i < 2 || n > nums[i - 2])
                  nums[i++] = n;
          return i;
      }
}
