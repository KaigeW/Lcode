class Solution {

    // Question 695
    /**
     *  IDEA 1:
     *
     *  Graph breadth first search
     *
     *  p.s. 剑指源码
     **/
     public int maxAreaOfIsland(int[][] grid) {
         int rows = grid.length;
         int cols = grid[0].length;
         boolean[][] visited = new boolean[rows][cols];

         int maxArea = 0;
         for( int i = 0; i < rows; i++ ) {
             for( int j = 0; j < cols; ++j ) {
                 if( grid[i][j] == 1 && !visited[i][j] ) {
                     int area = calculateArea( grid, visited, i, j );
                     maxArea = maxArea < area? area: maxArea;
                 }
             }
         }
         return maxArea;
     }

     private int calculateArea( int[][] grid, boolean[][] visited, int row, int col ) {
         Queue<int[]> queue = new LinkedList<>();
         // Use Stack<int[]> to achieve the depth first search or recursive
         // Slower than breadth first
         queue.add(new int[] {row, col});
         visited[row][col] = true;

         int area = 0;
         while( !queue.isEmpty() ) {
             int[] pos = queue.remove();
             area++;

             if( pos[0] + 1 < grid.length && grid[pos[0] + 1][pos[1]] == 1
               && !visited[pos[0] + 1][pos[1]]) {
                 queue.add(new int[] {pos[0] + 1, pos[1]});
                 visited[pos[0] + 1][pos[1]] = true;
             }
             if( pos[0] - 1 >= 0 && grid[pos[0] - 1][pos[1]] == 1
               && !visited[pos[0] - 1][pos[1]]) {
                 queue.add(new int[] {pos[0] - 1, pos[1]});
                 visited[pos[0] - 1][pos[1]] = true;
             }
             if( pos[1] + 1 < grid[0].length && grid[pos[0]][pos[1] + 1] == 1
               && !visited[pos[0]][pos[1] + 1]) {
                 queue.add(new int[] {pos[0], pos[1] + 1});
                 visited[pos[0]][pos[1] + 1] = true;
             }
             if( pos[1] - 1 >= 0 && grid[pos[0]][pos[1] - 1] == 1
               && !visited[pos[0]][pos[1] - 1]) {
                 queue.add(new int[] {pos[0], pos[1] - 1});
                 visited[pos[0]][pos[1] - 1] = true;
             }
         }
         return area;
     }

}
