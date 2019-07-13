class Solution {

    // Question 899
    public String orderlyQueue(String S, int K) {

    }

    // Question 900
    // 论询问参与长度的重要性
    // Assuming, we're not allow to change the passing data
    /** IDEA 1:
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
