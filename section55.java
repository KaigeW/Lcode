class Solution {

    // Question 542
    /**
     * IDEA:
     *
     * Use breadth search first
     *
     * Inspired by 剑指
     *
     * Time: O(n)
     * Space: O(1)
     *
     */
     public int[][] updateMatrix( int[][] matrix ) {
         int r = matrix.length;
         int c = matrix[0].length;

         int[][] dist = new int[r][c];

         Queue<int[]> queue = new LinkedList<>();

         for( int i = 0; i < r; ++i ) {
             for( int j = 0; j < c; ++j ) {
                 if( matrix[i][j] == 0 ) {
                     queue.add(new int[] {i, j} );
                     dist[i][j] = 0;
                 }
                 else
                     dist[i][j] = Integer.MAX_VALUE;
             }
         }

         while( !queue.isEmpty() ) {
             int[] temp = queue.remove();
             if( temp[0] - 1 >= 0
               && dist[temp[0] - 1][temp[1]] > dist[temp[0]][temp[1]] + 1 ) {
                 dist[temp[0] - 1][temp[1]] = dist[temp[0]][temp[1]] + 1;
                 queue.add(new int[] { temp[0] - 1, temp[1] } );
             }
             if( temp[1] - 1 >= 0
               && dist[temp[0]][temp[1] - 1] > dist[temp[0]][temp[1]] + 1 ) {
                 dist[temp[0]][temp[1] - 1] = dist[temp[0]][temp[1]] + 1;
                 queue.add(new int[] { temp[0], temp[1] - 1 } );
             }
             if( temp[0] + 1 < r
               && dist[temp[0] + 1][temp[1]] > dist[temp[0]][temp[1]] + 1 ) {
                 dist[temp[0] + 1][temp[1]] = dist[temp[0]][temp[1]] + 1;
                 queue.add(new int[] { temp[0] + 1, temp[1] } );
             }
             if( temp[1] + 1 < c
               && dist[temp[0]][temp[1] + 1] > dist[temp[0]][temp[1]] + 1 ) {
                 dist[temp[0]][temp[1] + 1] = dist[temp[0]][temp[1]] + 1;
                 queue.add(new int[] { temp[0], temp[1] + 1} );
             }
         }


         // while loop can be replaced by the following p.s. 剑指源码
         // int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
         // while( !queue.isEmpty() ) {
         //     int[] pos = queue.remove();
         //     int dist = dists[pos[0]][pos[1]];
         //     for( int[] dir: dirs ) {
         //         int r = pos[0] + dir[0];
         //         int c = pos[1] + dir[1];
         //         if( r >= 0 && c >= 0 && r < rows && c < cols
         //           && dists[r][c] > dist + 1) {
         //             dists[r][c] = dist + 1;
         //             queue.add(new int[]{r, c});
         //         }
         //     }
         // }

         return dist;
     }

}
