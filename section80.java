class Solution {

    // Question 797
    /**
     * IDEA1:
     *
     * depth first search
     *
     * p.s. 剑指源码
     *
     */
     public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
         List<List<Integer>> result = new LinkedList<>();
         List<Integer> path = new LinkedList<>();
         dfs( graph, result, path, 0 );
         return result;
     }

     private void dfs( int[][] graph, List<List<Integer>> result,
       List<Integer> path, int source ) {
         path.add(source);
         if( source == graph.length - 1 )
             result.add(new LinkedList<>(path));
         else
             for( int next: graph[source] ) {
                 dfs( graph, result, path, next);
             }
         path.remove(path.size() - 1);
     }
}
