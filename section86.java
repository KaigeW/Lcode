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


    // Question 852
    /**
     * IDEA :
     * Linear check
     */
    public int peakIndexInMountainArray(int[] A) {
        int lastIndex = 0;
        for( int i = 0; i < A.length; ++i ){
            if( A[lastIndex] > A[i] )
                break;
            if( A[lastIndex] != A[i] )
                lastIndex = i;
        }
        return lastIndex;
    }

    /**
     * IDEA :
     * Binary Search
     */
    public int peakIndexInMountainArray(int[] A) {
        int lo = 0, hi = A.length - 1;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (A[mi] < A[mi + 1])
                lo = mi + 1;
            else
                hi = mi;
        }
        return lo;
    }

    // Question 853
    /**
     * IDEA 1:
     * Use HashMap to store the position-speed pair. Sort the position array and
     * start counting fleet. Rules are shown below
     *
     * TODO: change value of HashMap to Time and see
     */

    public int carFleet(int target, int[] position, int[] speed) {
        int len = position.length;
        if( len <= 1 )
            return len;
        Map<Integer, Integer> cars = new HashMap();
        for( int i = 0; i < len; ++i )
            cars.put(position[i], speed[i]);
        Arrays.sort(position);
        int rtn = 1;
        double lastTime = ((double)target-position[len-1]) /
                            cars.get(position[len-1]);
        for( int i = len-2; i >= 0; --i ){
            double curTime = ((double)target-position[i]) / cars.get(position[i]);
            // If it takes longer time than the last car, a fleet shows up(last)
            if( curTime > lastTime ){
                lastTime = curTime;
                ++rtn;
            }
            // If it takes shorter or equal time than the last car
            // no new fleet generate
        }
        return rtn;
    }

    /**
     * IDEA 2:
     * Instead of using HashMap, using TreeMap will help us sorting the values
     * in first place. Instead of storing the speed info as the value, we can
     * store the TimeToDesti, in this case, we can use the T result directly to
     * compute the fleet number
     *
     * Somehow the time it takes can be longer since we're dealing with TreeMap
     */
    public int carFleet(int target, int[] pos, int[] speed) {
        TreeMap<Integer, Double> m = new TreeMap<>();
        for (int i = 0; i < pos.length; ++i) m.put(-pos[i], (double)(target - pos[i]) / speed[i]);
        int res = 0; double cur = 0;
        for (double time : m.values()) {
            if (time > cur) {
                cur = time;
                res++;
            }
        }
        return res;
    }


}
