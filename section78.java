class Solution {

    // Question 785
    /**
     * IDEA:
     *
     * Graph
     *
     * p.s. 剑指源码
     *
     */

     public boolean isBipartite( int[][] graph ) {
         int size = graph.length;
         int[] palatte = new int[size];
         Arrays.fill(palatte, -1);
         for( int i = 0; i < size; ++i ) {
             if( colors[i] == -1 ) {
                 if( !setColor( graph, colors, i, 0 ) ) {
                     return false;
                 }
             }
         }

         return true;
     }

     private boolean setColor( int[][] graph, int[] colors, int i, int color ) {
         Queue<Integer> queue = new LinkedList<>();
         queue.add(i);
         colors[i] = color;
         while( !queue.isEmpty() ) {
             int v = queue.remove();
             for( int neighbor: graph[v] ) {
                 if( colors[neighbor] >= 0 ) {
                     if( colors[neighbor] == colors[v])
                         return false;
                 } else {
                     queue.add(neighbor);
                     colors[neighbor] = 1 - colors[v];
                 }
             }
         }
         return true;
     }
}
