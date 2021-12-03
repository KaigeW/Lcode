class Solution {

    // Question 215
    /**
     *  IDEA 1:
     *  Use array to sort first, then the kth index would be the kth largest
     **/
     public int findKthLargest(int[] nums, int k) {
         if( k - 1 >= nums.length )
             return Integer.MIN_VALUE;
         Arrays.sort(nums);
         return nums[k - 1];
     }

     /**
      *  IDEA 2:
      *
      *  Couldn't beat Arrays helper function, above, but still good to know
      *
      *  Use heap, PriorityQueue with a size of the n, where n is the nth wanted
      *    largest number. The min heap is used here, so that the peek() funtion
      *    will always return the smallest in the heap and the nth largest in
      *    the heap
      **/
      public int findKthLargest( int[] nums, int k) {
          PriorityQueue<Integer> minHeap = new PriorityQueue<>();
          for( int num: nums ) {
              if( minHeap.size() < k )
                  minHeap.offer(num);
              else {
                  if( num > minHeap.peek() ) {
                      minHeap.poll();
                      minHeap.offer(num);
                  }
              }
          }
          return minHeap.peek();
      }

    /**
     *  IDEA 3:
     *
     *  Use quick sort, use the pivot solution to solve.
     *  if pivot is chosen bigger than n-k, then the value k would be inside of
     *     left side of the array.
     *
     *  faster, less memory
     *
     **/
     public int findKthLargest( int[] nums, int k ) {
         // kth largest
         int target = nums.length - k;
         int start = 0;
         int end = nums.length - 1;
         int index = partition( nums, start, end );
         while( index != target ) {
             if( index < target ) {
                 start = index + 1;
             } else {
                 end = index - 1;
             }
             index = partition( nums, start, end );
         }
         return nums[target];
     }

     private int partition( int[] nums, int start, int end ) {
         int pivot = new Random().nextInt(end - start + 1) + start;
         swap( nums, pivot, end );
         int p1 = start - 1;
         for( int i = start; i < end; ++i ) {
             if( nums[i] < nums[end] ) {
                 p1++;
                 swap(nums, i, p1);
             }
         }

         p1++;
         swap(nums, p1, end);

         return p1;
     }

     private void swap( int[] nums, int i1, int i2 ) {
         int temp = nums[i1];
         nums[i1] = nums[i2];
         nums[i2] = temp;
     }


    // Question 219
    /**
     *  IDEA 1:
     *  Use HashMap to store the value and index,
     **/
     public boolean containsNearbyDuplicate(int[] nums, int k) {
         Map<Integer, Integer> dupCheck = new HashMap<Integer, Integer>();
         for( int i = 0; i < nums.length; ++i ) {
             if( dupCheck.containsKey(nums[i]) && i - nums[i] <= k )
                 return true;
             dupCheck.put( nums[i], i );
         }
         return false;
     }

    /**
     *  IDEA 2:
     *  It iterates over the array using a sliding window.
     *    The front of the window is at i, the rear of the window is k steps
     *    back. The elements within that window are maintained using a Set.
     *    While adding new element to the set, if add() returns false, it means
     *    the element already exists in the set. At that point, we return true.
     *    If the control reaches out of for loop, it means that inner return
     *    true never executed, meaning no such duplicate element was found.
     **/
     public boolean containsNearbyDuplicate(int[] nums, int k) {
         Set<Integer> set = new HashSet<Integer>();
         for(int i = 0; i < nums.length; i++){
             if(i > k) set.remove(nums[i-k-1]);
             if(!set.add(nums[i])) return true;
         }
         return false;
     }


     // Question 220
     /**
      *  IDEA 1:
      *  Use loop to manually check every combination
      **/
      public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
          if( t == 0 ) {
              Set<Integer> set = new HashSet<Integer>();
              for(int i = 0; i < nums.length; i++){
                  if(i > k) set.remove(nums[i-k-1]);
                  if(!set.add(nums[i])) return true;
              }
              return false;
          } else if ( k == 0 ){
              return false;
          } else {
              for( int i = 0; i < nums.length - 1; ++i ) {
                  int subLoops = i + k + 1;
                  if( subLoops > nums.length )
                      subLoops = nums.length;
                  for( int j = i + 1; j < subLoops; ++j )
                      if( Math.abs( (long)nums[i] - (long)nums[j] ) <= t )
                          return true;
              }
              return false;
          }
      }
     /**
      *  IDEA 2:
      *
      *  p.s. 剑指源码
      **/
      public boolean containsNearbyAlmostDuplicate( int[] nums, int k, int t) {
          TreeSet<Long> set = new TreeSet<>();
          for( int i = 0; i < nums.length; ++i ) {
              Long lower = set.floor((long) nums[i]);
              if( lower != null && lower >= (long) nums[i] - t ) {
                  return true;
              }

              Long upper = set.ceiling( (long)nums[i] );
              if( upper != null && upper <= (long) nums[i] + t ) {
                  return true;
              }

              set.add( (long)nums[i] );
              if( i >= k )
                  set.remove((long)nums[i - k]);
          }
          return false;
      }


     /**
      *  IDEA 3:
      *  todo
      **/
      public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
          if (k < 1 || t < 0) return false;
          Map<Long, Long> map = new HashMap<>();
          for (int i = 0; i < nums.length; i++) {
              long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
              long bucket = remappedNum / ((long) t + 1);
              if (map.containsKey(bucket)
                      || (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t)
                          || (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t))
                              return true;
              if (map.entrySet().size() >= k) {
                  long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
                  map.remove(lastBucket);
              }
              map.put(bucket, remappedNum);
          }
          return false;
      }


}
