class Solution {

    // Question 501
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    /**
     Counter case: If passed-in node is null, we need to ignore it
     * IDEA 1:
     * Create a HashMap to record the number and its appearance times.
     * Find its max appearance time, find the number with the same appearance
     * and add them into a ArrayList, finally, convert ArrayList to an array
     */
    public Map<Integer, Integer> rMap;
    public int[] findMode(TreeNode root) {
        // Time: O(3n)
        // Once for general loop, once for searching max, once for searching ans
        // Space: O(3n)
        // One n for HashMap, one n for ArrayList, one n for array
        if( root == null )
            return new int[] {};
        rMap = new HashMap<Integer, Integer>();
        recurse( root );
        int max = Collections.max(rMap.values());
        ArrayList<Integer> rtn = new ArrayList<>();

        for( Integer x: rMap.keySet() ){
            if( rMap.get(x) == max )
                rtn.add(x);
        }

        int[] rtnA = new int[rtn.size()];
        for( int i = 0; i < rtn.size(); ++i ){
            rtnA[i] = rtn.get(i);
        }
        return rtnA;
    }

    public void recurse( TreeNode node ){
        if( node == null )
            return;
        if(rMap.containsKey(node.val) )
            rMap.put(node.val, rMap.get(node.val) + 1);
        else
            rMap.put(node.val, 1);
        recurse(node.left);
        recurse(node.right);
    }

    /**
     * IDEA 2:
     * Use inorder to traverse the tree, from small to large
     * Use several variable as indicators
     * In each traversal, we handle the value of each node. Will have #uniquenum
       , #maxCount available when finish the loop
     * traverse the tree again to find the answer
     **/
    public int[] findMode(TreeNode root) {
        inorder(root);
        modes = new int[modeCount];
        // modeCount will be the count of number with max appearance
        modeCount = 0;
        currCount = 0;
        inorder(root);
        return modes;
    }

    private int currVal;
    private int currCount = 0;
    private int maxCount = 0;
    private int modeCount = 0;

    private int[] modes;

    private void handleValue(int val) {
        if (val != currVal) {
            currVal = val;
            currCount = 0;
        }
        currCount++;
        if (currCount > maxCount) {
            maxCount = currCount;
            modeCount = 1;
        } else if (currCount == maxCount) {
            if (modes != null)
                modes[modeCount] = currVal;
            modeCount++;
        }
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        handleValue(root.val);
        inorder(root.right);
    }

    
}
