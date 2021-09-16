class Solution {
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

}
