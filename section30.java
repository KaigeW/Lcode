class Solution {
    // question 292
    /**
     *  IDEA 1:
     *  Just List, it will show the pattern
     **/
     // 1, 2, 3 you win 
     // 4 you lose
     // 5, 6, 7 you win
     // 8 you lose
     // ...
     // ...

     public boolean canWinNim(int n) {
         if( n >= 0 )
             return n % 4 != 0;
         return false;
     }

}
