class Solution {

    // Question 301
    /**
     *  IDEA 1:
     *  Using HashSet to avoid loops
     **/
    public List<String> removeInvalidParentheses(String s) {

    }

    // Question 303
    /**
     *  IDEA 1:
     *  Add the number as requested
     **/
    static class NumArray1 {

        private int[] nums;

        public NumArray(int[] nums) {
            this.nums = nums;
        }

        public int sumRange(int left, int right) {
            int rtn = 0;
            if ( left <= right && right < nums.length )
                for( int i = left; i < right + 1; ++i )
                    rtn += nums[i];
            return rtn;
        }
    }

    /**
     *  IDEA 2:
     *  pre-calculate the sum at the constructor level
     **/
    static class NumArray2 {

        private int[] sums;

        public NumArray(int[] nums) {
            this.sums = new int[nums.length + 1];
            this.sums[0] = 0;
            int sum = 0;
            for( int i = 1; i < nums.length + 1; ++i ) {
                sum = sum + nums[i-1];
                sums[i] = sum;
            }
        }

        public int sumRange(int left, int right) {
            return sums[right+1] - sums[left];
        }
    }

    // Question 304
    /**
     *  IDEA 1:
     *  Plain logical calculate
     *  Not Passing, Time Limit Exceed
     **/

    static class NumMatrix1{

        private int[][] matrix;

        public NumMatrix(int[][] matrix) {
            this.matrix = matrix;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for( int i = row1; i <= row2; ++i )
                for( int j = col1; j <= col2 ; ++j )
                    sum += matrix[i][j];
            return sum;
        }
    }

    /**
     *  IDEA 2:
     *  Have a matrix of sum in the constructor
     **/

    static class NumMatrix2{

        private int[][] sumMatrix;

        public NumMatrix(int[][] matrix) {
            // constructor a new sum matrix
            sumMatrix = new int[matrix.length][matrix[0].length];
            // calculate the first col, and first row of sumMatrix
            int sum = 0;
            for( int i = 0; i < matrix.length;++i ){
                sum += matrix[i][0];
                sumMatrix[i][0] = sum;
            }
            sum = 0;
            for( int j = 0; j < matrix[0].length;++j ){
                sum += matrix[0][j];
                sumMatrix[0][j] = sum;
            }
            sum = 0;
            for( int i = 1; i < matrix.length; ++i ){
                sum = sumMatrix[i][0];
                for( int j = 1; j < matrix[0].length; ++j) {
                    sum += sumMatrix[i-1][j];
                    sum += matrix[i][j];
                    sum -= sumMatrix[i-1][j-1];
                    sumMatrix[i][j] = sum;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if( row1 == 0 && col1 == 0 )
                return sumMatrix[row2][col2];
            else if ( row1 == 0 && col1 != 0 )
                return sumMatrix[row2][col2] - sumMatrix[row2][col1-1];
            else if ( row1 != 0 && col1 == 0 )
                return sumMatrix[row2][col2] - sumMatrix[row1-1][col2];
            else
                return sumMatrix[row2][col2] - sumMatrix[row2][col1-1] - sumMatrix[row1-1][col2] + sumMatrix[row1 - 1][col1-1];
        }
    }

    /**
     *  IDEA 3:
     *  Have a matrix of sum in the constructor
     **/

    static class NumMatrix3 {
        int[][] preSum;

        public NumMatrix(int[][] matrix) {
            preSum = new int[matrix.length + 1][matrix[0].length + 1];

            for(int i = 0; i < matrix.length; i++){
                for(int j = 0; j < matrix[0].length; j++){
                    //preSum[i + 1][j + 1] = curSum
                    preSum[i + 1][j + 1] = preSum[i][j + 1] + preSum[i + 1][j] - preSum[i][j] + matrix[i][j];
                }
            }

        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return preSum[row2 + 1][col2 + 1] + preSum[row1][col1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1];
        }
    }

    // Question 306
    /**
     *  IDEA 1:
     *  Use loop to count, find out the first possible additivePattern,
     *      // Note: the whole string might be the first pattern
     *      If found, keep on adding to see the result.
     *
     *      For addition between int type
     *        Order of addition matters here, and rememeber a + b = b + a
     **/

     long sec = 0;
     long remain = 0;

     public boolean isAdditiveNumber(String num) {
         int len = num.length();
         return isAdditiveNumberSupport(num, len);
     }

     public boolean isAdditiveNumberSupport(String num, int len) {
         int firstFew = 3;
         while ( firstFew <= len ) {
             if ( determineFirstAdditivePattern(num.substring(0, firstFew ))) {
                 if( firstFew == len || determineRest(num, firstFew) )
                     return true;
             }
             firstFew++;
         }
         return false;
     }

     public boolean determineRest( String num, int firstFew ) {
         int startIndex = firstFew;
         int endIndex = 0;
         while( endIndex < num.length() ) {
             long next = this.sec + this.remain;
             String nextNum = String.valueOf(next);
             endIndex = startIndex + nextNum.length();
             if( endIndex > num.length() || !num.substring(startIndex, endIndex).equals(nextNum) )
                 return false;
             this.sec = this.remain;
             this.remain = next;
             startIndex = endIndex;
         }
         return true;
     }

     public boolean determineFirstAdditivePattern(String oneAdditivePattern) {
         double size = oneAdditivePattern.length();
         // e.g. 7 / 2 = 3.5
         for( int i = 1; i < size / 2; ++i ) {
             int sec = (int)((size - i) / 2);
             int remain = (int)size - i - sec;
             if( sec <= remain ) {
                 if( determineAdd(oneAdditivePattern, i, sec, remain) || determineAdd(oneAdditivePattern, sec, i, remain))
                     return true;
             } else
                 break;
         }
         return false;
     }

     public boolean determineAdd( String str, int first, int second, int result ) {
         if( str.charAt(0) == '0' && str.substring(0, first).length() !=1
                 || str.charAt(first) == '0' && str.substring(first, first+second).length() !=1
                 || str.charAt(first + second) == '0' && str.substring( first + second, result + first + second).length() !=1)
             return false;
         long a = Long.parseLong(str.substring(0, first));
         long b = Long.parseLong(str.substring(first, first+second));
         long c = Long.parseLong(str.substring( first + second, result + first + second));
         if( a + b == c ) {
             this.sec = b;
             this.remain = c;
             return true;
         }
         return false;
     }



    // Question 306
    /**
     *  IDEA 2:
     *  Using loops to figure out. I think one above is a little is a little smarter,
     *    but smarter makes the memory heavy crying face
     **/
    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() < 3) return false;
        final int N = num.length();
        for (int len1 = 1; len1 <= N/2; len1++) {
            if (num.charAt(0) == '0' && len1 > 1) return false;
            long a = Long.parseLong(num.substring(0, len1));  // Note: use Long because the number may be out of the range of Integer type.
            for (int len2 = 1; len1 + len2 < N; len2++) {
                if (num.charAt(len1) == '0' && len2 > 1) break;
                long b = Long.parseLong(num.substring(len1, len1 + len2));
                if (isValid(a, b, len1 + len2, num)) return true;
            }
        }
    return false;
}

    private boolean isValid(long a, long b, int start, String num) {
        if (start == num.length()) return true;
        long sum = a + b;
        String str = String.valueOf(sum);
        return num.startsWith(str, start) && isValid(b, sum, start + str.length(), num);
    }

    // Question 307
    /**
     *  IDEA 1:
     *  Using sum array, every time updates, updates the target sum array
     *    Simple ones are good, but will die when manipulations are a lot.
     **/
     static class NumArray {

         private int[] sumNums;
         private int[] nums;

         public NumArray(int[] nums) {
             this.sumNums = new int[nums.length + 1];
             this.nums = nums;
             int sum = 0;
             for( int i = 1; i < nums.length + 1; ++i ) {
                 sum += nums[i - 1];
                 this.sumNums[i] = sum;
             }
         }

         public void update(int index, int val) {
             int adjust = val - nums[index];
             nums[index] = val;
             for( int i = index + 1; i < sumNums.length; ++i )
                 sumNums[i] += adjust;
         }

         public int sumRange(int left, int right) {
             return sumNums[right+1] - sumNums[left];
         }
     }

    /**
     *  IDEA 2:
     *  Using segment tree
     **/
     static class NumArray {
         int numLength;
         private int[] tree;

         public NumArray( int[] nums ) {
             if( nums != null && nums.length != 0 ){
                 this.numLength = nums.length;
                 this.tree = new int[2 * numLength];
                 for( int i = 0; i < numLength; ++i )
                     this.tree[i + numLength] = nums[i];

                 for( int i = numLength - 1; i > 0; i-- )
                     this.tree[i] = this.tree[i*2] + this.tree[i*2+1];
             }
         }

         public void update( int index, int val ) {
             int updateNode = index + numLength;
             if( this.tree[updateNode] != val ) {
                 int diff = val - this.tree[updateNode];
                 this.tree[updateNode] = val;
                 while( updateNode / 2 > 0 ) {
                     updateNode /= 2 ;
                     this.tree[updateNode] += diff;
                 }
             }
         }

         public int sumRange( int left, int right ) {
             int leftI = left + this.numLength;
             int rightI = right + this.numLength;
             int sum = 0;

             while( leftI <= rightI ) {
                 if( (leftI & 1) == 1 ) {
                     sum += this.tree[leftI];
                     leftI++;
                 }
                 if( (rightI & 1) == 0 ) {
                     sum += this.tree[rightI];
                     rightI--;
                 }

                 leftI /= 2;
                 rightI /= 2;
             }
             return sum;
         }
     }

     static class NumArray {
         private int N;
         private int[] tree;

         public NumArray(int[] nums) {
             if(nums != null && nums.length != 0){
                  this.N = nums.length;
                  this.tree = new int[2*N];
                  int i;

                  for(i = 0; i < N; i++){
                      this.tree[N + i] = nums[i];
                  }

                  for(i = N - 1; i >= 0; i--){
                      this.tree[i] = this.tree[i<<1] + this.tree[(i<<1) + 1];
                  }
             }
         }

         public void update(int index, int val) {
             int treeIndex = this.N + index;
             if(this.tree[treeIndex] != val){ // mark this important step - update only if needed
                 this.tree[treeIndex] = val;

                 while(treeIndex != 0){
                     treeIndex = (treeIndex >> 1);
                     this.tree[treeIndex] = this.tree[treeIndex << 1] + this.tree[(treeIndex << 1) + 1];
                 }
             }
         }

         public int sumRange(int left, int right) {
             int leftIndex = left + this.N;
             int rightIndex = right + this.N;
             int sum = 0;

             while(leftIndex <= rightIndex){
                 if((leftIndex & 1) == 1){ // odd
                     sum = sum + this.tree[leftIndex];
                     ++leftIndex;
                 }

                 if((rightIndex & 1) == 0){ // even
                     sum = sum + this.tree[rightIndex];
                     --rightIndex;
                 }

                 leftIndex = (leftIndex >> 1);
                 rightIndex = (rightIndex >> 1);
             }
             return sum;
         }
     }

     /**
      *  IDEA 2:
      *  Using segment tree(TreeNode version): TODO
      **/

}
