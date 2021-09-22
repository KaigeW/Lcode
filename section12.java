class Solution {
    // question 118?
    /**
     *  IDEA 1:
     *  Just List
     **/

     public List<List<Integer>> generate(int numRows) {
         List rtn = new ArrayList<ArrayList<Integer>>();
         List nums = new ArrayList<Integer>();
         if( numRows > 0 ) {
             nums.add(1);
             rtn.add(new ArrayList<>(nums));
         }
         if( numRows > 1 ) {
             nums.add(1);
             rtn.add(new ArrayList<>(nums));
         }
         numRows -= 2;
         while( numRows-- > 0 ) {
             List temp = new ArrayList<>();
             temp.add(1);
             for( int i = 0; i < nums.size() - 1; ++i )
                 temp.add((int)nums.get(i)+(int)nums.get(i+1));
             temp.add(1);
             nums = temp;
             rtn.add(temp);
         }
         return rtn;
     }


}
