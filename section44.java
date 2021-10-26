class Solution {

    // Question 437
    /**
     *  IDEA 1:
     *
     *  Based on previous in-order traversal algorithm
     *
     *  Time: O(n^2) loop once for s
     *  Space: O(n) not sure why 1 ...
     **/
     public int pathSum(TreeNode root, int targetSum) {
         List<Integer> nums = new ArrayList<Integer>();
         int[] rtn = new int[]{0};
         if( root != null )
             traverseTree( root, targetSum, nums, rtn);
         return rtn[0];
     }

     private void traverseTree( TreeNode node, int targetSum,
       List<Integer> nums, int[] rtn ) {
         if( node == null )
             return;
         nums.add(0);
         for( int i = 0; i < nums.size(); ++i ) {
             nums.set(i, nums.get(i) + node.val);
             if( nums[i] == targetSum )
                 rtn[0]++;
         }
         traverseTree( node.left, targetSum, nums );
         traverseTree( node.right, targetSum, nums );
         for( int i = 0; i < nums.size(); ++i )
             nums[i] -= node.val;
         nums.remove(nums.size() - 1);
     }

    /**
     *  IDEA 2:
     *
     *  Based on previous in-order traversal algorithm
     *
     *  Time: O(n^2) loop once for s
     *  Space: O(n) not sure why 1 ...
     *
     *  p.s. 剑指源码
     *
     **/
     public int pathSum(TreeNode root, int sum ) {
         Map<Integer, Integer> map = new HashMap<>();
         map.put( 0, 1 );
         return dfs(root, sum, map, 0);
     }

     private int dfs( TreeNode root, int sum, Map<Integer, Integer> map,
       int path) {
         if( root == null )
             return 0;
         path += root.val;
         int count = map.getOrDefault(path - sum, 0);
         map.put(path, map.getOrDefault(path, 0) + 1);
         count += dfs( root.left, sum, map, path);
         count += dfs( root.right, sum, map, path);

         map.put( path, map.get(path) - 1);

         return count;
     }


    // Question 438
    /**
     *  IDEA 1:
     *  Used sliding window method to check if each character can form a
     *    permutation
     *  Time: O(n) loop once for s
     *  Space: O(n , 1) not sure why 1 ...
     *  ps. 剑指源码
     **/
     public List<Integer> findAnagrams(String s, String p) {
         List<Integer> resultList = new ArrayList<Integer>();
         if( s.length() < p.length() )
             return resultList;

         int[] chars = new int[26];
         for( int i = 0; i < p.length(); ++i ) {
             chars[p.charAt(i) - 'a']++;
             chars[s.charAt(i) - 'a']--;
         }

         if( isAllZero(chars) )
             resultList.add(0);

         for( int i = p.length(); i < s.length(); ++i ) {
             chars[s.charAt(i) - 'a']--;
             chars[s.charAt(i - p.length()) - 'a']++;
             if( isAllZero(chars) )
                 resultList.add(i - p.length() + 1);
         }
         return resultList;
     }

     public boolean isAllZero(int[] nums) {
         for( int num: nums )
             if( num != 0 )
                 return false;
         return true;
     }

}
