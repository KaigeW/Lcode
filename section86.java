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
         for (int j : richer2.get(i)) if (quiet[res[i]] > quiet[dfs(j, quiet)])
         res[i] = res[j];
         return res[i];
     }


    // Question 852
    /**
     * IDEA :
     * Linear check
     *
     * Time: O(n)
     * Space: O(1)
     *
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
         int low = 0, high = A.length - 1;
         while( low < high ) {
             int mid = (low + high) / 2;
             if( A[mid - 1] < A[mid] && A[mid] > A[mid + 1] )
                 return mid;
             else if( A[mid - 1] < A[mid] && A[mid] < A[mid + 1] )
                 low = mid;
             else if( A[mid - 1] > A[mid] && A[mid] > A[mid + 1] )
                 high = mid;
            else
                return -1;
         }
         return low;
     }



    /**
     * IDEA :
     * Binary Search
     *
     * p.s. 剑指源码
     *
     */
     public int peakIndexInMountainArray(int[] A) {
         int low = 1, high = A.length - 2;
         while( low <= high ) {
             int mid = (low + high) / 2;
             if( A[mid - 1] < A[mid] && A[mid] > A[mid + 1] )
                 return mid;

             if( A[mid - 1] < A[mid] )
                 low = mid + 1;
             else
                 high = mid - 1;
         }
         return -1;
     }


    /**
     * IDEA :
     * Binary Search
     *
     * Most data efficient
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


    // Question 854
    /**
     * IDEA :
     * COMPREHENSIV
     */
    public int kSimilarity(String A, String B) {
        Queue<String> queue = new ArrayDeque();
        queue.offer(A);

        Map<String, Integer> dist = new HashMap();
        dist.put(A, 0);

        while( !queue.isEmpty() ){
            String S = queue.poll();
            if(S.equals(B)) return dist.get(S);
            for(String T: neighbors(S, B)) {
                if( !dist.containsKey(T)){
                    dist.put(T.dist.get(S) + 1);
                    queue.offer(T);
                }
            }
        }

        throw null;
    }

    public List<String> neighbors(String S, string target){
        List<String> ans = new ArrayList();
        int i = 0;
        for( ;i < S.length(); ++i) {
            if( S.charAt(i) != target.charAt(i) ) break;
        }

        char[] T = S.toCharArray();
        for(int j = i + 1; j < S.length(); ++j)
            if(S.charAt(j) == target.charAt(i)) {
                swap(T, i, j);
                ans.add(new String(T));
                swap(T, i, j);
            }

        return ans;
    }

    public void swap(char[] T, int i, int j){
        char tmp = T[i];
        T[i] = T[j];
        T[j] = tmp;
    }


    // Question 855
    /**
     * IDEA :
     *
     */
    class ExamRoom{
        int N;
        TreeSet<Integer> students;

        public ExamRoom( int N ){
            this.N = N;
            students = new TreeSet();
        }

        public int seat(){
            int student = 0;
            if( students.size() > 0 ){
                int dist students.first();
                Integer prev = null;
                for( Integer s: students ){
                    if( prev != null ){
                        int d = (s - prev) / 2;
                        if( d > dist ) {
                            dist = d;
                            student = prev + d;
                        }
                    }
                    prev = s;
                }

                if( N - 1 - students.last() > dist )
                    student = N - 1;
            }

            student.add(student);
            return student;
        }

        public void leave(int p){
            students.remove(p);
        }
    }


    // Quesion 856
    /**
     * IDEA 1:
     * Use Stack, Magical....
     */
    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack();
        stack.push(0);

        for( char c : S.toCharArray() ){
            if( c == '(' )
                stack.push(0);
            else{
                int v = stack.pop();
                int w = stack.pop();
                stack.push(w + Math.max(2 * v, 1));
            }
        }

        return stack.pop();
    }

    /**
     * IDEA 2:
     * Divide and Conquer ???
     */
    public int scoreOfParentheses(String S) {
        return F(S, 0, S.length);
    }

    public int F( String S, int i, int j ){
        int ans = 0, bal = 0;

        for( int k = i ; k < j ; ++k ) {
            bal += S.charAt(k) == '('? 1:-1;
            if( bal == 0 ){
                if( k - i == 1 ) ans++;
                else ans += 2 * F(S, i+1, k);
                i = k + 1;
            }
        }

        return ans;
    }


    // Question 857
    /**
     * IDEA 1: 41/46
     * Exceed time limit
     */
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        double[] pay = new double[quality.length];
        for( int i = 0; i < quality.length; ++i ){
            ArrayList<Double> possIncome = new ArrayList<>();
            pay[i] = wage[i];
            for( int j = 0; j < quality.length; ++j ){
                double amount = (double) quality[j] * wage[i] /
                                    (double) quality[i];
                if( amount < wage[j] || i == j )
                    continue;
                possIncome.add(amount);
            }
            if( possIncome.size() < K - 1 ){
                pay[i] = 0;
                continue;
            }
            else{
                Collections.sort(possIncome);
                for( int k = 0; k < K-1; ++k ){
                    pay[i] += possIncome.get(k);
                }
            }
            possIncome.clear();
        }
        Arrays.sort(pay);
        for( double rtn: pay )
            if( rtn != 0 )
                return rtn;
        return 0;
    }

    /**
     * IDEA 2:
     */
    public double mincostToHireWorkers(int[] quality, int[] wage, int K){
        int N = quality.length;
        double ans = le9;

        for( int captain = 0; captain < N; ++captain ){
            double factor = (double) wage[captain] / quality[captain];
            double prices[] = new double[N];
            int t = 0;
            for( int worker = 0; worker < N; ++worker ){
                double price = factor * quality[worker];
                if( price < wage[worker]) continue;
                prices[t++] = price;
            }

            if( t < K ) continue;
            Arrays.sort(prices, 0, t);
            double cand = 0;
            for( int i = 0; i < K; ++i)
                cand += prices[i];
            ans = Math.min(ans, cand);
        }

        return ans;
    }

    /**
     * IDEA 3:
     */
    public double mincostToHireWorkers(int[] quality, int[] wage, int K){
        int N = quality.length;
        Worker[] workers = new Worker[N];
        for (int i = 0; i < N; ++i)
            workers[i] = new Worker(quality[i], wage[i]);
        Arrays.sort(workers);

        double ans = 1e9;
        int sumq = 0;
        PriorityQueue<Integer> pool = new PriorityQueue();
        for (Worker worker: workers) {
            pool.offer(-worker.quality);
            sumq += worker.quality;
            if (pool.size() > K)
                sumq += pool.poll();
            if (pool.size() == K)
                ans = Math.min(ans, sumq * worker.ratio());
        }

        return ans;
    }

    class Worker implements Comparable<Worker> {
        public int quality, wage;
        public Worker(int q, int w) {
            quality = q;
            wage = w;
        }

        public double ratio() {
            return (double) wage / quality;
        }

        public int compareTo(Worker other) {
            return Double.compare(ratio(), other.ratio());
        }
    }
}
