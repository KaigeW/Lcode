class Solution {
    // Question 394
    /**
     *  IDEA 1:
     * Using Stack
     **/
    public String decodeString(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            // count the number
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            }
            // start a new String and push the old string into a Stack
            if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            }
            // start appending the old String with the repeatedtimes new
            // String
            else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder (resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            }
            else {
                res += s.charAt(idx++);
            }
        }
        return res;
     }


    // Question 394
    /**
     *
     *  IDEA 1:
     *  Using Depth first search
     *
     **/
    public double[] calcEquation(List<List<String>> equations, double[] values,
      List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        double[] results = new double[queries.size()];
        for( int i = 0; i < queries.size(); ++i ) {
            String from = queries.get(i).get(0);
            String to = queries.get(i).get(1);
            if( !graph.containsKey(from) || !graph.containsKey(to) ) {
                results[i] = -1;
            } else {
                Set<String> visited = new HashSet<>();
                results[i] = dfs(graph, from, to, visited);
            }
        }

        return results;
    }

    private Map<String, Map<String, Double>> buildGraph(
      List<List<String>> equations, double[] values ) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for( int i = 0; i < equations.size(); ++i ) {
            String var1 = equations.get(i).get(0);
            String var2 = equations.get(i).get(1);

            graph.putIfAbsent(var1, new HashMap<String, Double>());
            graph.get(var1).put(var2, values[i]);

            graph.putIfAbsent(var2, new HashMap<String, Double>());
            graph.get(var2).put(var1, 1.0 / values[i]);
        }

        return graph;
    }

    private double dfs( Map<String, Map<String, Double>> graph, String from,
      String to, Set<String> visited ) {
        if( from.equals(to) ) {
            return 1.0;
        }

        visited.add(from);
        for( Map.Entry<String, Double> entry: graph.get(from).entrySet() ) {
            String next = entry.getKey();
            if( !visited.contains(next) ) {
                double nextValue = dfs( graph, next, to, visited );
                if( nextValue > 0 )
                    return entry.getValue() * nextValue;
            }
        }

        visited.remove(from);
        return -1.0;
    }


}
