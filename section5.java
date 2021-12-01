class Solution {

    // Question 43
    /**
     *
     */
     public String multiply(String num1, String num2) {
         int m = num1.length(), n = num2.length();
         int[] pos = new int[m + n];

         for(int i = m - 1; i >= 0; i--) {
             for(int j = n - 1; j >= 0; j--) {
                 int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                 int p1 = i + j, p2 = i + j + 1;
                 int sum = mul + pos[p2];

                 pos[p1] += sum / 10;
                 pos[p2] = (sum) % 10;
             }
         }

         StringBuilder sb = new StringBuilder();
         for(int p : pos) if(!(sb.length() == 0 && p == 0)) sb.append(p);
         return sb.length() == 0 ? "0" : sb.toString();
     }


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
