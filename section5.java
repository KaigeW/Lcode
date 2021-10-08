class Solution {

    // Question 49
    /**
     * IDEA 1:
     * For each of the string values, presort its character order so that each
     *   of the anagram from one string will have the same string value. We will
     *   use it as a key of a map, and store the corresponding string values
     *   into the map.
     *
     * 注：leetcode compiler里使用ArrayList会比LinkedList快
     *
     * Time: O(nmlogn)
     * Space: O(n)
     *
     * ps. 剑指源码
     *
     */
     public List<List<String>> groupAnagrams(String[] strs) {
         Map<String, List<String>> groups = new HashMap<>();
         for(String str: strs) {
             char[] charArray = str.toCharArray();
             Arrays.sort(charArray);
             String sorted = new String(charArray);

             groups.putIfAbsent(sorted, new LinkedList<String>());
             groups.get(sorted).add(str);
         }

         return new LinkedList<>(groups.values());
     }

}
