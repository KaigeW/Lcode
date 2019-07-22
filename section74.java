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

}
