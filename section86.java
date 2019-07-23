class Solution {
    // Question 851
    /**
     * Dynamic Programming
     */
    private Map<Integer, ArrayList<Integer>> richerList;

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        richerList = new HashMap<Integer, ArrayList<Integer>>();
        for( int i = 0; i < quiet.length; ++i )
            richerList.put(i,new ArrayList<Integer>());
        for( int j = 0; j < richer.length; ++j )
            richerList.get(richer[j][1]).add(richer[j][0]);
        int[] rtn = new int[quiet.length];
        for( int j = 0; j < quiet.length; ++j )
            rtn[j] = -1;
        for( int i = 0; i < quiet.length; ++i ){
            loudPerson( richer, quiet, rtn, i );
        }
        return rtn;
    }

    public void loudPerson( int[][] richer, int[] quiet, int[] array, int me ){
        if( array[me] != -1 ){
            return;
        }
        if( richerList.get(me).isEmpty() ){
            array[me] = me;
            return;
        }
        int xiao = me;
        for( int x: richerList.get(me) ){
            loudPerson(richer, quiet, array, x);
            if( quiet[xiao] > quiet[array[x]] ){
                xiao = array[x];
            }
        }
        array[me] = xiao;
    }

    /**
     * IDEA :
     * Similar Time and Space tho...
     */
     HashMap<Integer, List<Integer>> richer2 = new HashMap<>();
     int res[];
     public int[] loudAndRich(int[][] richer, int[] quiet) {
         int n = quiet.length;
         for (int i = 0; i < n; ++i) richer2.put(i, new ArrayList<Integer>());
         for (int[] v : richer) richer2.get(v[1]).add(v[0]);
         res = new int[n]; Arrays.fill(res, -1);
         for (int i = 0; i < n; i++) dfs(i, quiet);
         return res;
     }

     int dfs(int i, int[] quiet) {
         if (res[i] >= 0) return res[i];
         res[i] = i;
         for (int j : richer2.get(i)) if (quiet[res[i]] > quiet[dfs(j, quiet)]) res[i] = res[j];
         return res[i];
     }
}
