class Solution {
    // Question 380
    /**
     * IDEA 1:
     *
     * Using the minHeap
     * ps. 剑指源码
     *
     */
     public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k){
         Queue<int[]> maxHeap = new PriorityQueue<>(( p1, p2 ) -> p2[0] + p2[1] -
            p1[0] - p1[1]);
         for( int i = 0; i < Math.min(k, nums1.length); ++i ){
             for( int j = 0; j < Math.min(k, nums2.length); ++j ){
                 if( maxHeap.size() >= k ) {
                     int[] root = maxHeap.peek();
                     if( root[0] + root[1] > nums1[i] + nums2[j]) {
                         maxHeap.poll();
                         maxHeap.offer(new int[]{nums1[i], nums2[j]});
                     }
                 } else {
                         maxHeap.offer(new int[]{nums1[i], nums2[j]});
                 }
             }
         }

         List<List<Integer>> result = new LinkedList<>();
         while( !maxHeap.isEmpty() ) {
             int[] vals = maxHeap.poll();
             result.add(Arrays.asList(vals[0], vals[1]));
         }

         return result;
     }


    /**
     * IDEA 2:
     *
     * ATTENCION
     * Using the maxHeap
     * ps. 剑指源码
     *
     */
     public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k){
         Queue<int[]> minHeap = new PriorityQueue<>(( p1, p2 ) ->
           nums1[p1[0]] + nums2[p1[1]] - nums1[p2[0]] - nums2[p2[1]]);
         if( nums2.length > 0 ) {
             for( int i = 0; i < Math.min(k, nums1.length); ++i) {
                 minHeap.offer(new int[] {i, 0});
             }
         }

         List<List<Integer>> result = new ArrayList<>();
         while( k-- > 0 && !minHeap.isEmpty()) {
             int[] ids = minHeap.poll();
             result.add(Arrays.asList(nums1[ids[0]], nums2[ids[1]]));

             if( ids[1] < nums2.length - 1) {
                 minHeap.offer(new int[] {ids[0], ids[1] + 1});
             }
         }
         return result;
     }


    // Question 380
    /**
     * IDEA 1:
     *
     * ps. 剑指源码
     *
     */
     static class RandomizedSet {
         HashMap<Integer, Integer> numToLocation;
         ArrayList<Integer> nums;

         public RandomizedSet() {
             this.numToLocation = new HashMap<>();
             this.nums = new ArrayList<>();
         }

         public boolean insert(int val) {
             if( numToLocation.containsKey(val)) {
                 return false;
             }

             numToLocation.put(val, nums.size());
             nums.add(val);
             return true;
         }

         public boolean remove(int val) {
             if(!numToLocation.containsKey(val)) {
                 return false;
             }

             int location = numToLocation.get(val);

             // exchange remove val with the val from last index, later we can
             //   just delete the last index
             numToLocation.put(nums.get(nums.size() - 1), location);
             numToLocation.remove(val);
             nums.set(location, nums.get(nums.size() - 1));
             nums.remove(nums.size() - 1);
             return true;
         }

         public int getRandom() {
           Random random = new Random();
           int r = random.nextInt(nums.size());
           return nums.get(r);
         }
     }

    // Question 389
    /**
     * IDEA 1:
     * Use a hashMap to store the appearing chars, after two loops, the char
     * that holds a value is the result
     *
     */

     public char findTheDifference(String s, String t) {
         int[] chars = new int[26];
         int i = 0;
         for( ; i < s.length(); ++i ) {
             chars[s.charAt(i) - 'a']--;
             chars[t.charAt(i) - 'a']++;
         }

         chars[t.charAt(i) - 'a']++;
         for( i = 0; i < 26; ++i ) {
             if( chars[i] == 1 )
                 return (char) ('a' + i );
         }
         return '-';
     }

    /**
     * IDEA 2:
     * Use XOR
     *
     */
     public char findTheDifference(String s, String t) {
         char ch = 0;
         for( char c: s.toCharArray() ) ch ^= c;
         for( char c: t.toCharArray() ) ch ^= c;
         return ch;
     }


}
