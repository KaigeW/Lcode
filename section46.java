class Solution {

    // Question 454
    /**
     * IDEA 1:
     * thought of Dynamic, but no, can use brute force which O(n^4)
     * Use hashmap
     *
     * p.s. Inspired by leetcode bbs
     *
     */
     public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
         int result = 0;
         Map<Integer, Integer> oneTwoSum = new HashMap<>();
         for( int one: nums1 )
             for( int two: nums2 )
                 if( oneTwoSum.containsKey(one + two) )
                     oneTwoSum.put( one + two, oneTwoSum.get(one+two) + 1);
                 else
                     oneTwoSum.put( one+two, 1);

         for( int three: nums3 )
             for( int four: nums4 )
                 if( oneTwoSum.containsKey( -(three + four) ) )
                     result += oneTwoSum.get(-(three+four));
         return result;
     }


}
