class Solution {
   // for Question 2
   public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
   }
    // Question 1
    /**
     * IDEA:
     Counter case: If array has < 2 elements, will return an empty array
     * Used HashMap to store the (arrayVal, index) pair
     * Will have a loop walk through each element in the nums
     * First, will check if any pairing data(target - currentVal)
     * of the currentVal is inside of the set.
     *
     * If so, create a new array with two index and return it
     *
     * Otherwise, add the currentVal into hashmap.
     *
     * If the loop terminates, means no matching pairs exist
     * return the empty array
     */
    public int[] twoSum(int[] nums, int target){
        Map<Integer, Integer> maps = new HashMap<>();
        // Time: O(n)
        // In the worst case scenario, containsKey and put will be called n
        // times, which will be O(2n)
        // Space:O(n)
        // In the worst case scenario, extra hashmap will take n elements
        for( int i = 0; i < nums.length; ++i ){
            if( maps.containsKey(target-nums[i]) ){
                return new int[]{maps.get(target-nums[i]), i};
            }
            maps.put(nums[i],i);
        }
        return new int[]{};
    }

    // Question 2
    /**
     * IDEA:
     * Used a new ListNode to store the solution
     Counter case: two null nodes with a positive carry.
     * Will have a loop walk through each position existed digit( two lists not
     * null)
     * For each solution, create a new node with the answer for each solution
     * and append it to the list end.
     * After all the additions, if the result > 10, need to set the carry bit to
     * one, and -10.
     *
     * For the list structure, since the head of the list needs to be return,
     * Will create a listNode at the beginning, and manipulating its 'next' node
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Time: O(n)
        // Each degree needs one round of calculation. In worst case, we have
        // n possible degree.
        // Space: O(n)
        // Space depends on longest possible degree
        ListNode iter = new ListNode(0);
        ListNode rtn = iter;
        boolean carry = false;
        while(l1 != null || l2 != null || carry){
            int sum = 0;
            if(l1 != null ){
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null ){
                sum += l2.val;
                l2 = l2.next;
            }
            if(carry){
                ++sum;
                carry = false;
            }
            if(sum >= 10){
                sum -= 10;
                carry = true;
            }
            iter.next = new ListNode(sum);
            iter = iter.next;
        }
        return rtn.next;
    }

    // Question 2
    /**
     * IDEA 1:
     Counter case: If 1-char or empty string passed in, need to make sure the
         return value is updated before returning it.
     * Brute Force, loop thru each char, find its maximum unique char # till the
     * end.
     */
    public int lengthOfLongestSubstring1(String str) {
        // Time: O(n^2)
        // In worst case, will need to check the whole rest of chars for
        //     each char at each index. In all, n x n
        // Time: O(n)
        // In worst case, the whole string is unique
        Set<Character> temp = new HashSet<>();
        int s = 0;
        int e = 0;
        int limit = str.length();
        int rtn = 0;
        while( s < limit && e < limit ){
            if( temp.contains(str.charAt(e)) ){
                ++s;
                e = s;
                rtn = ( temp.size() > rtn? temp.size():rtn );
                temp.clear();
            }else{
                temp.add(str.charAt(e));
                ++e;
            }
        }
        rtn = ( temp.size() > rtn? temp.size():rtn );
        return rtn;
    }

    /**
     * IDEA 2:
     * Sliding windows, have two pointers a, b index pointing to 0 positions.
     * If found a new char, add it into the set, and add 1 to ending pointer b.
     *      Also, check the possible longest length.
     * If found a existing char, remove the char at beginning pointer a and
     *      increase a, until the exisiting char is removed from set.
     * Once begin/end pointer reached the end, break the loop, and return rtn
     */
     public int lengthOfLongestSubstring2(String str) {
        // Time: O(2n)
        // In worst cast, will have a duplicate at the end of the string, which
        //     means that the a and b need to be reached the end of string. 2n
        // Space: O(m)
        // Once found a duplicate, whole set will stop growing.
        Set<Character> temp = new HashSet<>();
        int s = 0, e = 0, limit = str.length(), rtn = 0;
        while( s < limit && e < limit ){
           if( temp.contains(str.charAt(e)) ){
               temp.remove(str.charAt(s++));
           }
           else{
              temp.add(str.charAt(e++));
              rtn = ((e-s)>rtn?e-s:rtn);
           }
        }
        return rtn;
     }

     /**
      * IDEA 3:
      * *** NEED TO BE FILLED ***
      * Sliding windows, have two pointers a, b index pointing to 0 positions.
      * If found a existing char, keep on checking and setting a to the newest
      * duplicate.
      * Once begin/end pointer reached the end, break the loop, and return rtn
      */
     public int lengthOfLongestSubstring(String s) {
        // Time: O(n)
        // Only walk thru once!!!
        // Space: O(m)
        // m is the unique chars in string
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
           i = (index[s.charAt(j)]>i?index[s.charAt(j)]:i);
           ans = (ans > (j - i + 1)?ans:(j - i + 1);
           index[s.charAt(j)] = j + 1;
        }
        return ans;
     }
}