class Solution {
    // Question 11
    /**
     * IDEA :
     * NOT REALLY UNDERSTAND.....
     */
    public int maxArea(int[] height) {
        int end = height.length - 1;
        int start = 0;
        int area = 0;
        while( start < end ){
            if( height[start] < height[end]){
                area = (end - start) * height[start] > area ?
                        (end - start) * height[start] : area;
                start++;
            }
            else{
                area = (end - start) * height[end] > area ?
                        (end - start) * height[end] : area;
                end++;
            }
        }
        return area;
    }
}
