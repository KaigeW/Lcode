class Solution {
    // Question 11
    /**
     * IDEA :
     * TODO
     * NOT REALLY UNDERSTAND.....
     */
    public int maxArea(int[] height) {
        // Time: O(N)
        // Loop thru the incoming array exactly once
        // Space: O(1)
        // Several instance variables used
        int end = height.length - 1;
        int start = 0;
        int area = 0;
        while( start < end ){
            if( height[start] < height[end]){
                area = (end - start) * height[start] > area ?
                        (end - start) * height[start] : area;
                start++;
            }
            else{
                area = (end - start) * height[end] > area ?
                        (end - start) * height[end] : area;
                end++;
            }
        }
        return area;
    }

    // Question 12
    /**
     * IDEA 1:
     * Based on found pattern. Will be explained in following code
     */
    public String intToRoman( int num ){
        // Time: O(4N)
        // Loop thru each digit once. Worst case 4n.
        // Space: O(N)
        // A char array is created
        if( num < 1 || num > 3999 )
            return new String();
        char[] S = String.valueOf(num).toCharArray();
        // Use StringBuffer can be faster here
        StringBuffer rtn = new StringBuffer();
        int tens = S.length-1;
        int count = 0;
        char t = ' ', f = ' ', o = ' ';
        while( tens >= 0 ){
            // In each tens position, 5, 10, 1 have different char
            // representation. However, the overall displaying rule is the same.
            int digit = S[count] - '0';

            // find one, five, ten in each digit first
            if( tens == 3 ){
                o = 'M';
            }else if( tens == 2 ){
                t = 'M'; f = 'D'; o = 'C';
            }else if( tens == 1 ){
                t = 'C'; f = 'L'; o = 'X';
            }else{
                t = 'X'; f = 'V'; o = 'I';
            }

            // Adding pattern
            while( digit > 0 ){
                // 4 and 9 is displayed as 1 to 5 and 1 to 10
                if( digit == 4 || digit == 9 ){
                    rtn.append(o);
                    ++digit;
                }else if( digit == 5 || digit == 10 ){
                    rtn.append(digit == 5? f:t);
                    digit = 0;
                // 6,7,8 is displayed as 5+1, +2, +3
                }else if( digit >= 5 ){
                    rtn.append(f);
                    digit -= 5;
                // 1,2,3 is basically adding ones
                }else{
                    rtn.append(o);
                    --digit;
                }
            }
            --tens;
            ++count;
        }
        System.out.println(rtn);
        return rtn.toString();
    }

    /**
     * IDEA 2:
     * YOUXIU
     */

    public String intToRoman(int num) {
        // Time: O(1)
        // Once the space is allocated, array lookup will be just O(1)
        // Space: O(N)
        // Several instance variables used
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }

    // Question 13
    /**
     * IDEA :
     * Loop from the end, e.g. IIV is 7, VI is 4   from small to big.
     */
    public static int romanToInt(String s) {
        // Time: O(N)
        // Loop thru each char of string exactly once
        // Space: O(1)
        // 1 int variables used
     	 int res = 0;
     	 for (int i = s.length() - 1; i >= 0; i--) {
     		 char c = s.charAt(i);
     		 switch (c) {
     		 case 'I':
     		 	 res += (res >= 5 ? -1 : 1);
     			 break;
     		 case 'V':
     			 res += 5;
     			 break;
     		 case 'X':
     			 res += 10 * (res >= 50 ? -1 : 1);
     			 break;
     		 case 'L':
     			 res += 50;
     			 break;
     		 case 'C':
     			 res += 100 * (res >= 500 ? -1 : 1);
     			 break;
     		 case 'D':
     			 res += 500;
     			 break;
     		 case 'M':
     			 res += 1000;
     			 break;
     		 }
     	 }
     	 return res;
    }

    // Question 14
    /**
     * IDEA :
     * Find the most different pair of String, return the common prefix
     */
    public String longestCommonPrefix(String[] strs) {
        // Time: O(NlogN)
        // Sorting String might take a lot of time
        // Space: O(M)
        // M is the length of the String
        if(strs.length == 0)
            return new String();
        Arrays.sort(strs);
        String str = Math.max(strs);
        String end = Math.min(strs);
        int i = 0;
        for( i = 1; i < str.length()+1; ++i ){
            if( !str.startsWith(end.substring(0,i)) )
                break;
        }
        return end.substring(0,i-1);
    }



    // Question 15
    /**
     * IDEA 1:
     * loop thru the array, and using two sum tricks to find the matched pairs
     * Time: O(nlogn + n^2 )
     *      search  + figuring
     * Space: O(n)
     * ps. 剑指源码
     * 请注意在使用loop前 搞清loop的用途
     */

     public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        for( int i = 0; i < nums.length - 2 && nums[i] <= 0; ++i ) {
            int target = -nums[i];
            int front = i + 1;
            int back = nums.length - 1;
            while( front < back ) {
                if( nums[front] + nums[back] == target ) {
                    result.add(Arrays.asList(nums[i], nums[front], nums[back]));
                    while ( nums[front] == nums[++front] && front < back );
                }
                else if( nums[front] + nums[back] < target )
                    ++front;
                else
                    --back;
            }
            while ( nums[i] == nums[i+1] && i < nums.length - 2 && nums[i] <= 0 )
                ++i;
        }
        return result;
     }

    // Question 19
    /**
     * IDEA 1:
     * Figure out the length first, then loop the second time to delete
     *
     * Space: O(1)
     * Time: O(n)
     */
     public ListNode removeNthFromEnd(ListNode head, int n) {
         ListNode nHead = head;
         int length = 0;
         while( head != null ) {
             head = head.next;
             length++;
         }
         int loop = length - n;
         head = nHead;
         if( loop == 0 )
             return head.next;
         while( loop > 1 ) {
             head = head.next;
             loop--;
         }
         head.next = head.next.next;
         return nHead;
     }

    /**
     * IDEA 2:
     * Use two pointers so that the distance between these two pointers is k+1,
     *   when the right side pointer is at the end, left side would be at k+1
     *
     * Space: O(1)
     * Time: O(n)
     */

     public ListNode removeNthFromEnd(ListNode head, int n) {
         ListNode leftNode = head, rightNode = head;
         int loop = n;
         while( n-- > 0 ) {
             rightNode = rightNode.next;
         }

         if( rightNode.next == null )
             return head.next;
         while( rightNode.next != null ) {
             leftNode = leftNode.next;
             rightNode = rightNode.next;
         }
         leftNode.next = leftNode.next.next;
         return head;
     }

    /**
     * IDEA 3:
     * Use two pointers so that the distance between these two pointers is k+1,
     *   when the right side pointer is at the end, left side would be at k+1
     *
     * Space: O(1)
     * Time: O(n)
     *
     * ps. 剑指源码
     */
     public ListNode removeNthFromEnd(ListNode head, int n) {
         ListNode dummy = new ListNode(0);
         dummy.next = head;

         ListNode front = head, back = dummy;
         for( int i = 0; i < n; i++ ) {
             front = front.next;
         }

         while( front != null ) {
             front = front.next;
             back = back.next;
         }

         back.next = back.next.next;
         return dummy.next;
     }

}
