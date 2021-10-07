class Solution {

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
             this.num = new ArrayList<>();
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
           int r = random.nextInt(nums.size());
           return nums.get(r);
         }
     }

}
