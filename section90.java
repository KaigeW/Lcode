class Solution {


   // Question 896
   /**
    * IDEA 1:
    * Set an integer as sign indicator, equals as 0, positive 1, negative -1
    * Loop thru the list, once set the indicator and conflicts exist, return
    * false.
    * Otherwise, return true.
    * Attention, 4444 is considered true.
    */
   public boolean isMonotonic(int[] A) {
      // Time: O(n)
      // Worst case 6 comparisons for each n. Still O(n)
      // Space: O(1)
      // Only used a integer as indicators
      if(A.length <= 2)
           return true;
      int po = A[1]-A[0];
      for( int i = 2; i < A.length; ++i ){
         //4
           if( po == 0 ){
              if(A[i] - A[i-1] > 0)
                   po = 1;
              else if( A[i] - A[i-1] < 0 )
                   po = -1;
           }
           else{
              if( (po > 0 && ( A[i]-A[i-1]<0 ))|| (po < 0 && ( A[i]-A[i-1]>0 )))
                   return false;
           }
      }
      return true;
   }

   /**
    * IDEA 2:
    * Have two variables as increasing or decreasing indicator
    * Loop thru the array once, compare two consecutive neighbors to see if they
    * match the indicator result(At least have one true indicator for monotonic)
    * situations.
    * Return the or result of two indicators.
    */
   public boolean isMonotonic(int[] A) {
      // Time: O(n)
      // Will have at most 4 operations for each int, so still O(n)
      // Space: O(1)
      // Used two booleans var as indicators, O(1)
      boolean inc = true, dec = true;
      for (int i = 1; i < A.length; ++i) {
           inc &= A[i - 1] <= A[i];
           dec &= A[i - 1] >= A[i];
      }
      return inc || dec;
    }

    // Question 897
    /**
     * IDEA 1:
     * Since we have a inordered BST, we need to get back the correct order
     * First, add the sorted node value into an ArrayList in a correct order,
     * Loop thru the arrayList and create a tree using the required order.
     */
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> vals = new ArrayList();
        inorder(root, vals);
        TreeNode ans = new TreeNode(0);
        TreeNode cur = ans;
        for( int val: vals ){
            cur.right = new TreeNode(val);
            cur = cur.right;
        }
        return ans.right;
    }

    public void inorder(TreeNode node, List<Integer> vals){
        if( node == null ) return;
        inorder(node.left, vals);
        vals.add(node.val);
        inorder(node.right, vals);
    }

    /**
     * IDEA 2:
     * Base on the IDEA 1, instead of having a list that saves all of the values
     * first. During the iteration, we can definitely change the property of
     * nodes itself to make it a right most tree.
     */
     TreeNode cur;
     public TreeNode increasingBST(TreeNode root) {
         TreeNode ans = new TreeNode(0);
         cur = ans;
         inorder(root);
         return ans.right;
     }

     public void inorder(TreeNode node ){
         if( node == null ) return;
         inorder(node.left);
         node.left = null;
         cur.right = node;
         cur = node;
         inorder(node.right);
     }


     /**
     * IDEA 3:
     * ATTENTION
     */
     public TreeNode increasingBST(TreeNode root) {
         return inorder(root, null);
     }

     public TreeNode inorder(TreeNode dad, TreeNode son){
         if( dad == null ) return son;
         TreeNode rtn = inorder(dad.left, dad);
         dad.left = null;
         dad.right = inorder(dad.right, son);
         return rtn;
     }


    // Question 898
    /**
     * IDEA 1: 50 / 83 test cases passed. Time exceeds
     * 论考虑时间的重要性
     * Use HashSet to store all of the possible OR results, return the total
     * unique result number (size of the set).
     * Asked for finding the combination of the given array. Start from the left
     * first element, continuously OR the initial value with each one of the
     * right remaining values. And use that value as a new intial value, call
     * the addResult functions again until the last index is reached.
     *
     * Might have the duplicates calculation. e.g. 1,1,2. Will have two 1,2
     * calculations
     * Using recursive functions might result in a larger time cost and mem use
     */

    public int subarrayBitwiseORs(int[] A) {
        // Time: O(n^(n/2))
        // Find all of the possible combinations for the giving array
        // Space: O(m)
        // Used an extra HashSet stores the unique results. m is the number of
        // them
        Set<Integer> rtnList = new HashSet<>();
        for(int i = 0; i < A.length; ++i){
            addResult(A, A[i], i + 1, rtnList);
        }
        return rtnList.size();
    }

    public void addResult(int[] A, int target, int index, Set<Integer> rtnList){
        rtnList.add(target);
        if( index >= A.length )
            return;
        for( int i = index; i < A.length; ++i ){
            target = target | A[i];
            addResult(A, target, i+1, rtnList);
        }
    }

    /**
     * IDEA 2:
     * Use HashSet to store all of the possible OR results, return the total
     * unique result number (size of the set).
     * Loop thru the int array A. For each element x, or it with cur, a HashSet
     * with initial value 0, save the result and value x back into cur. Append
     * all values into Ans set. And finally return the size of ans.
     */
     // Time: O(N log W)
     // O(NlogW), where N is the length of A, and W is the maximum size of
     // elements in A.
     // Space: O(N log W)
     // O(NlogW), the size of the answer.
    public int subarrayBitwiseORs(int[] A) {
       // store the final answer
       Set<Integer> ans = new HashSet<>();
       // temp hashset to store the partial answer
       Set<Integer> cur = new HashSet<>();
       // Intial zero
       cur.add(0);
       for( int x: A ){
           // temp hashset for cur
           Set<Integer> cur2 = new HashSet<>();
           // loop thru and or each of the partial answer,
           // store the answer into cur2
           for(int y : cur)
               cur2.add(x|y);
           // In case x is not in the cur2 set.
           cur2.add(x);
           cur = cur2;
           // append new partial answers to answer set.
           ans.addAll(cur);
       }
       return ans.size();
    }

    // Question 899
    /**
     * IDEA:
     * Two situations:
     * If K = 1, we only can find the rotating string solution one by
     * one. Then return the one with the smallest lexicographic difference.
     * If K is larger than 1, we always can get the standard smallest
     * lexicographic difference by sorting the string sequence. It's the same
     * thing
     */
    public String orderlyQueue(String S, int K) {
      // Time: O(N^2)
      // Worst case, when K is 1, need to be looped at most N time
      // Space: O(N^2)
      // Worst case, when K is 1, need to create at most n strings.
        if( K == 1 ){
            String smlO = S;
            for( int i = 0; i < S.length(); ++i ){
                String T = S.substring(i) + S.substring(0,i);
                if( smlO.compareTo(T) >= 0 ) smlO = T;
            }
            return smlO;
        }
        else{
            char[] temp = S.toCharArray();
            Arrays.sort(temp);
            return new String(temp);
        }
    }

    // Question 900
    // Assuming, we're not allow to change the passing data
    /** IDEA 1: 4 / 9 test cases passed.
     * 论询问参与长度的重要性!
     * create two class members sto(stored array), and cur(current position)
     *      Cur would be used as pointer.
     * In next member functions, each time passed in a n number, will start
     *      calculating the correct relating position(addtion of all the possible
     *      length values) and return the correct value.

     * PROBLEM is when the n gets larger and larger, the class member int cur
     *      would be the addition of these large numbers which might exceed its
     *      range.
     */
    // ONLY WORK WITH SHORT LENGTH AMOUNT!!!
    // Don't mess up with the index!
    public class RLEIterator1 {
      // Time: O(n x m)
      // Each next call might cause whole loop addtion needed, if we have m next
      //       function calls, it would be m x n
      // Space: O(n) / O(1)
      // Space can be allocated elsewhere. We're just assigning reference addr.
      int[] sto;
      long cur;

      public RLEIterator(int[] A) {
          sto = A;
          cur = -1;
      }

      public int next(int n) {

          int gI = 0, sI = 0;
          while( gI < sto.length ){
              if( sto[gI] + sI > cur + n ){
                  cur += n;
                  return sto[gI+1];
              }
              else{
                  sI += sto[gI];
                  gI += 2;
              }
          }
          return -1;
      }
    }

    /** IDEA 2:
     * create three class members sto(stored array), curRem(currentRemainder),
     *      curIndex(current Index of sto). curIndex and curRem would be used as
     *      pointer.
     *
     * In next member functions, each time passed in a n number, will start
     *      calculating the correct relating position(curRem+n > sto[curIndex])
     *      and return the correct value.
     */
    class RLEIterator {
       // Time: O(n)
       // We only have a constant call times for each elements. Once we go over
       //      that element, we're done with it. In all, it would be O(n)
       // Space: O(n) / O(1)
       // Space can be allocated elsewhere. We're just assigning reference addr.
       int[] sto;
       int curIndex, curRem;

       public RLEIterator(int[] A) {
           sto = A;
           curIndex = 0;
           curRem = 0;
       }

       public int next(int n) {
           while(curIndex < sto.length ){
               if( curRem + n > sto[curIndex] ){
                   n -= sto[curIndex] - curRem;
                   curIndex += 2;
                   curRem = 0;
               }
               else{
                   curRem += n;
                   return sto[curIndex+1];
               }
           }
           return -1;
       }
   }
}
