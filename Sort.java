/**
 * Insertion Sort:
 *
 * 9,8,7,6,5,4,3,2,1
 * 8,9,7,6,5,4,3,2,1
 * 7,8,9,6,5,4,3,2,1
 * 6,7,8,9,5,4,3,2,1
 * 5,6,7,8,9,4,3,2,1
 * 4,5,6,7,8,9,3,2,1
 * 3,4,5,6,7,8,9,2,1
 * 2,3,4,5,6,7,8,9,1
 * 1,2,3,4,5,6,7,8,9
 *
 */
 public void insertionSort( int[] target ) {
     if( target.length <= 1 )
         return;
     for( int i = 1; i < target.length; ++i ) {
         int temp = target[i];
         int j = i - 1;

         while( j >= 0 && target[j] > temp ) {
             target[j + 1] = target[j];
             --j;
         }
         target[j + 1] = temp;
     }
 }

/**
 * heapSort:
 * 1,2,3,4,5,6,7,8,9
 * 1,2,3,9,5,6,7,8,4
 * 1,2,7,9,5,6,3,8,4
 * 1,9,7,2,5,6,3,8,4 -> 1,9,7,8,5,6,3,2,4
 * 9,1,7,2,5,6,3,8,4 -> 9,8,7,1,5,6,3,2,4 -> 9,8,7,4,5,6,3,2,1 end og first loop
 *
 * 9,8,7,4,5,6,3,2,1 -> 1,8,7,4,5,6,3,2,9 -> 8,1,7,4,5,6,3,2,9 -> 8,5,7,4,1,6,3,2,9
 * 2,5,7,4,1,6,3,8,9 -> 7,5,2,4,1,6,3,8,9 -> 7,5,6,4,1,2,3,8,9
 * 3,5,6,4,1,2,7,8,9 -> 6,5,3,4,1,2,7,8,9
 * 2,5,3,4,1,6,7,8,9 -> 5,2,3,4,1,6,7,8,9 -> 5,4,3,2,1,6,7,8,9
 * 1,4,3,2,5,6,7,8,9 -> 4,1,3,2,5,6,7,8,9 -> 4,2,3,1,5,6,7,8,9
 * 1,2,3,4,5,6,7,8,9 -> 3,2,1,4,5,6,7,8,9
 * 1,2,3,4,5,6,7,8,9 -> 2,1,3,4,5,6,7,8,9
 * 1,2,3,4,5,6,7,8,9 -> end of second loop
 *
 */
 public void heapSort( int[] arr ) {

     int n = arr.length;

     for (int i = arr.length / 2 - 1; i >= 0; i--)
         heapify(arr, arr.length, i);

     for( int i = n - 1; i > 0; i-- ) {
         int temp = arr[0];
         arr[0] = arr[i];
         arr[i] = temp;

         heapify(arr, i, 0);
     }
 }

 private void heapify( int[] arr, int len, int i ) {
     int largest = i;
     int l = 2 * i + 1;
     int r = 2 * i + 2;

     if( l < len && arr[l] > arr[largest] )
         largest = l;

     if( r < len && arr[r] > arr[largest] )
         largest = r;

     if( largest != i ) {
         int swap = arr[i];
         arr[i] = arr[largest];
         arr[largest] = swap;

         heapify( arr, len, largest);
     }

 }



/**
 * Counting Sort:
 *
 * 3,4,3,3,4,3,4,3,5,5
 * [3 - 5] --> 3:5, 4:3, 5:2
 *
 * 3,3,3,3,3,4,4,4,5,5
 */
 public void countingSort( int[] arr ) {
     int max = Integer.MIN_VALUE;
     int min = Integer.MAX_VALUE;

     for( int num: arr ) {
         if( max < num )
             max = num;
         if( min > num )
             min = num;
     }

     int[] numHolder = new int[max - min + 1];
     for( int num: arr ) {
         numHolder[num - min]++;
     }

     int i = 0;
     for( int index = 0; index < numHolder.length; ++index ) {
         while( numHolder[index] > 0 ) {
             arr[i] = index + min;
             ++i;
             numHolder[index]--;
         }
     }
 }



/**
 * mergeSort:
 *
 * 9,8,7,6,5,4,3,2,1
 * 9,8,7,6,5,4,3,1,2
 * 8,9,6,7,4,5,1,2,3
 * 6,7,8,9,1,2,3,4,5
 * 1,2,3,4,5,6,7,8,9
 *
 */
 public int[] mergeSort( int[] nums ) {
     int[] dst = new int[nums.length];
     dst = Arrays.copyOf(nums, nums.length);
     merge(nums, dst, 0, nums.length);
     return dst;
 }

 private void merge(int[] nums, int[] dst, int start, int end) {
     if( start + 1 >= end )
         return;

     int mid = (start + end) / 2;
     mergeSort( src, dst, start, mid );
     mergeSort( src, dst, mid, end );

     int i = start, j = mid, k = start;
     while( i < mid || j < end ) {
         if( j == end || ( i < mid && src[i] < src[j] ) )
             dst[k++] = src[i++];
         else
             dst[k++] = src[j++];
     }
 }


/**
 * quickSort: (assuming always pick the middle as the pivot)
 *
 * 9,8,7,6,5,4,3,2,1
 * 1,4,2,3,5,8,7,6,9          5
 * 1,2,3,4   5   6,7,8,9      3,7
 * 1,2   3 4 5 6 7   8,9          1,8
 * 1,2,3,4,5,6,7,8,9
 *
 */

 public void quickSort( int[] arr ) {
     quick(arr, 0, arr.length - 1);
     return arr;
 }

 private void quick( int[] arr, int start, int end ) {
     if( end > start ) {
         int pivot = partition(nums, start, end);
         quick(nums, start, pivot - 1);
         quick(nums, pivot + 1, end);
     }
 }

 private int partition(int[] nums, int start, int end) {
     int random = new Random().nextInt(end - start + 1) + start;
     swap( nums, random, end );
     int lessThanIndex = start;

     for( int i = start; i < end; ++i ) {
         if( nums[i] < nums[end] )
             swap(nums, i, lessThanIndex++);
     }

     swap(nums, lessThanIndex, end);

     return lessThanIndex;
 }

 private void swap( int[] nums, int index1, int index2 ) {
     if( index1 != index2 ) {
         int temp = nums[index1];
         nums[index1] = nums[index2];
         nums[index2] = temp;
     }
 }
