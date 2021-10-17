class Solution {
    // Question 733
    /**
     * IDEA:
     * LEFT, UP, RIGHT, DOWN recursion
     */
    public int[][] floodFill(int[][] image, int sr, int newColor){
        // Time: O(M*N)
        // Worst case: loop thru each element once
        // Space: O(0)
        // No extra space used
        if( color != newColor )
            dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    public void dfs( int[][] image, int r, int c, int color, int newColor){
        if( image[r][c] == color ){
            image[r][c] = newColor;
            if( r >= 1 ) dfs(image, r-1, c, color, newColor);
            if( c >= 1 ) dfs(image, r, c-1, color, newColor);
            if( r + 1 < image.length ) dfs( image, r+1, c, color, newColor);
            if( c + 1 < image[0].length ) dfs(image, r, c+1, color, newColor);
        }
    }

    // Question 735
    /**
     * IDEA :
     * From leetcode comment
     * COMPREHENSIV
     */
     public int[] asteroidCollision(int[] asteroids) {
         LinkedList<Integer> s = new LinkedList<>();
         for( int i : asteroids ){
             if( i > 0 )
                 s.add(i);
             else{
                 while(!s.isEmpty() && s.getLast() > 0 && s.getLast() < -i)
                     s.pollLast();
                 if(!s.isEmpty() && s.getLast() == -i )
                     s.pollLast();
                 else if( s.isEmpty || s.getLast() < 0)
                     s.add(i);
             }
         }
         return s.stream().mapToInt(i->i).toArray();
     }


    /**
     * IDEA 2:
     * Manual compare and loop
     */


     public int[] asteroidCollision(int[] asteroids) {
         Stack<Integer> aster = new Stack<>();
         boolean equal = false;
         for( int next: asteroids ) {
             if( aster.isEmpty() || aster.peek() < 0 || next > 0 )
                 aster.push(next);
             else if( Math.abs(aster.peek()) == Math.abs(next) ) {
                 aster.pop();
             }
             else if( Math.abs(aster.peek()) < Math.abs(next) ) {
                 aster.pop();
                 while( !aster.isEmpty() && aster.peek() > 0 ) {
                     if( Math.abs(aster.peek()) < Math.abs(next)) {
                         aster.pop();
                         continue;
                     }
                     else if( Math.abs(aster.peek()) == Math.abs(next) ){
                         aster.pop();
                         equal = true;
                     }
                     break;
                 }
                 if( !equal && ( aster.isEmpty() || aster.peek() < 0
                      || Math.abs(aster.peek()) < Math.abs(next)) )
                     aster.push(next);
                 equal = false;
             }
         }
         int[] rtn = new int[aster.size()];
         for( int i = aster.size() - 1; i >= 0; --i )
             rtn[i] = aster.pop();
         return rtn;
     }


    /**
     * IDEA 3:
     * Manual compare and loop
     * COMPREHENSIV
     *
     * Similar to idea1???
     *
     * ps. 剑指源码
     */
     public int[] asteroidCollision( int[] asteroids) {
         Stack<Integer> stack = new Stack<>();
         for( int as : asteroids) {
             while( !stack.empty() && stack.peek() > 0 && stack.peek() < -as ) {
                 stack.pop();
             }

             if(!stack.empty() && as < 0 && stack.peek() == -as ){
                 stack.pop();
             } else if( as > 0 || stack.empty() || stack.peek() < 0) {
                 stack.push(as);
             }
         }

         return stack.stream().mapToInt(i -> i).toArray();

     }



     // Question 738
     /**
      * IDEA 1:
      * Store each digit into a int ArrayList.
      * Compare each digit mark the left most illegal digit, and create a new
      * legal digit based on that.
      */

     public int monotoneIncreasingDigits( int N ) {
         if( N < 10 )
             return N;
         ArrayList<Integer> nums = new ArrayList<>();
         boolean addNine = false;
         int rtn = 0;
         int temp = N;
         while( N / 1 > 0 ){
             nums.add( N % 10 );
             N /= 10;
         }
         System.out.println( nums.toString() );
         int dig = 0;
         for( int i = 0 ; i < nums.size()-1; ++i ){
             if( nums.get(i) < nums.get(i+1) ||
                    ( nums.get(i) == nums.get(i+1) &&
                        nums.get(i+1) == nums.get(dig) ))
                 dig = i+1;
         }
         if(dig == 0)
             return temp;
         int index = nums.size() - 1;
         while( index >= 0 ){
             if( index == dig ){
                 rtn = rtn * 10 + (nums.get(index)-1);
                 addNine = true;
             }
             else if( !addNine )
                 rtn = rtn * 10 + nums.get(index);
             else
                 rtn = rtn * 10 + 9;
             index--;
         }
         return rtn;
     }

     /**
      * IDEA 2:
      * Instead of using ArrayList, manipulate directly when looping thru each
      * digit.
      * TODO!
      */

     /**
      * IDEA 3:
      * Convert n to a char array
      * Find the first illegal pattern
      * If found, Subtracted the first number by 1, then append the 9, and
      * return the final number
      * Else, return the original number
      */
     public iny monotoneIncreasingDigits(int N){
         char[] S = String.valueOf(N).toCharArray();
         int i = 1;
         while( i < S.length && S[i-1] <= S[i] ) i++;
         while( 0 < i && i < S.length && S[i-1] > S[i]) S[--i]--;
         for(int j = i + 1; j < S.length; ++j ) S[j] = '9';

         return Integer.parseInt(String.valueOf(S));
     }
     // Question 739
     /**
      * IDEA 1:
      *
      */
     public int[] dailyTemperatures(int[] T) {
         int[] rtn = new int[T.length];
         for( int i = 0; i < T.length - 1; ++i ){
             int sum = 0;
             for( int j = i + 1; j < T.length; ++j ){
                 if( T[i] >= T[j] ){
                     sum++;
                 }
                 else{
                     sum++;
                     break;
                 }
                 if( j == T.length - 1)
                     sum = 0;
             }
             rtn[i] = sum;
         }
         rtn[T.length-1] = 0;
         return rtn;
     }

     /**
      * IDEA 2:
      * COMPREHENSIV
      */
     public int[] dailyTemperatures( int[] T ){
         int[] ans = new int [T.length];
         Stack<Integer> stack = new Stack();
         for( int i = T.length - 1; i >= 0; --i ){
             while( !stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop()；
             ans[i] = stack.isEmpty()?0:stack.peek()-i;
             stack.push(i);
         }
         return ans;
     }

     // Question 740
     /**
      * IDEA :
      * Using Dynamic programming,
      */
     public int deleteAndEarn( int[] nums ) {
         int[] count = new int[10001];
         for(int x: nums) count[x]++;
         int avoid = 0, using = 0, prev = -1;

         for( int k = 0; k <= 10000; ++k ){
             if(count[k] > 0) {
                 int m = Math.max(avoid, using);
                 if( k - 1 != prev ){
                     using = k * count[k] + m;
                     avoid = m;
                 }
                 else{
                     using = k * count[k] + avoid;
                     avoid = m;
                 }
                 prev = k;
             }
         }
         return Math.max(avoid, using);
     }

}
