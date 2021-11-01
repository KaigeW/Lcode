class Solution {
    // question 724
    /**
     *  IDEA 1:
     *  Calculate the sum of the index first, consider it as the rightSum,
     *    Use a loop, to calculate the sum from the leftmost, and subtract it
     *    from the rightSum, compare two values and return the first index that
     *    make those two sum values equal
     *
     * Time: O(n) most 2n
     * Space: O(1)
     **/
     public int pivotIndex(int[] nums) {
         int rightSum = 0;
         for( int num : nums )
             rightSum += num;
         int leftSum = 0;
         for( int i = 0; i < nums.length; ++i ) {
             rightSum -= nums[i];
             if( leftSum == rightSum )
                 return i;
             leftSum += nums[i];
         }
         return -1;
     }

    /**
     *  IDEA 2:
     *  Similar thoughts as above!
     *  Time: O(n) most 2n
     *  Space: O(1)
     * ps. 剑指源码
     **/
     public int pivotIndex(int[] nums) {
         int total = 0;
         for( int num: nums )
             total += num;

         int sum = 0;
         for( int i = 0; i < nums.length; ++i ) {
             sum += nums[i];
             if( sum - nums[i] == total - sum)
                 return i;
         }
         return -1;
     }


    // question 728
    /**
     *  IDEA 1:
     *  Just go thru and check
     **/

     public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> rtn = new ArrayList<Integer>();
        for( int i = left; i <= right; ++i )
            if( selfDivide(i) )
                rtn.add(i);
        return rtn;
     }

     public boolean selfDivide( int num ) {
         int numCpy = num;
         while( numCpy != 0 ) {
             int remainder = numCpy % 10;
             if ( remainder == 0 ||  num % remainder != 0 )
                 return false;
             numCpy /= 10;
         }
         return true;
     }



    // Question 729
    /**
     * IDEA 1:
     * Used HashMap to store integer to integer k, v pair. Keep on checking
     * k, v pair value to decide if the incoming start and end time is valid.
     * Once found valid overlap, we can remove the k, v pair to save memory.
     */
    class MyCalendar {
        // Time: O(N^2)
        // Need to go thru the HashMap each time we try to insert a k, v pair.
        // Space: O(N)
        // Worst case, need to store each incoming and time pair
        private Map<Integer, Integer> calendar;

        public MyCalendar() {
            calendar = new HashMap<>();
        }

        public boolean book(int start, int end) {
            int overS = -1;
            int overE = -1;
            if( start > end )
                return false;
            if( start == end )
                ++end;
            for( int f : calendar.keySet() ){
                if( end < f || start > calendar.get(f) ){
                    continue;
                }
                else if( end == f )
                    overE = f;
                else if( start == calendar.get(f) )
                    overS = f;
                else
                    return false;
            }
            if( overE == -1 && overS == -1 ){
                calendar.put(start, end);
            }
            else if(overE != -1 && overS != -1){
                calendar.put(overS, calendar.get(overE));
                calendar.remove(overE);
            }
            else if( overE != -1 ){
                calendar.put(start, calendar.get(overE));
                calendar.remove(overE);
            }
            else if( overS != -1 ){
                calendar.put(overS, end);
            }
            else
                return false;
            return true;
        }
    }

    /**
     * Your MyCalendar object will be instantiated and called as such:
     * MyCalendar obj = new MyCalendar();
     * boolean param_1 = obj.book(start,end);
     */

    /**
     * IDEA 2:
     * review treeMap...
     */

     class MyCalendar{
         TreeMap<Integer, Integer> calendar;

         MyCalendar(){
             calendar = new TreeMap();
         }

         public boolean book(int start, int end){
             Integer prev = calendar.floorKey(start),
                     next = calendar.ceilingKey(start);
             if((prev == null || calendar.get(prev) <= start) &&
                     (next == null || end <= next)){
                         calendar.put(start, end);
                         return true;
                     }
             return false;
         }
     }

    /**
     * IDEA 3:
     *
     * faster
     * p.s. 剑指源码
     *
     */

     class MyCalendar{
         TreeMap<Integer, Integer> calendar;

         MyCalendar(){
             calendar = new TreeMap();
         }

         public boolean book(int start, int end){
             Map.Entry<Integer, Integer> event = calendar.floorEntry(start);
             if( event != null && event.getValue() > start )
                 return false;
             event = calendar.ceilingEntry(start);
             if( event != null && event.getKey() < end )
                 return false;

             calendar.put(start, end);
             return true;
         }
     }

}
