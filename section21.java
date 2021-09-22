class Solution {

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