class Solution {
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
}
