class Solution {

    // Question 202
    /**
     *  IDEA 1:
     *  Using HashSet to avoid loops
     **/
    public boolean isHappy(int n) {
        if( n <= 0 )
            return false;
        int sum;
        HashSet<Integer> a = new HashSet<Integer>();
        while( n != 1 ){
            if( a.contains(n))
                return false;
            a.add(n);
            sum = 0;
            // make sure to cover every digit
            while( n / 10 > 0 ){
                // sum and square it
                sum += (n % 10)*(n % 10);
                n = n / 10;
            }
            sum += (n % 10)*(n % 10);
            n = sum;
            // Attention: the above loop can be simplify as below
            // make sure to cover every digit
            // while( n > 0 ){
            //     // sum and square it
            //     sum += (n % 10)*(n % 10);
            //     n = n / 10;
            // }
            // n = sum;
        }
        return true;
    }

    // Question 203
    /**
     *  IDEA 1:
     *  Reroute pointer to avoid uneccessary ListNode creation
     *  需要问题完整性 不可急于求成
     **/
    public ListNode removeElements(ListNode head, int val) {
        ListNode pre = null;
        if( head == null )
            return null;
        ListNode newHead = head;
        while( newHead != null && newHead.val == val )
            newHead = newHead.next;
        while( head != null ){
            if( head.val == val){
                if( pre != null ){
                    pre.next = head.next;
                }
            } else{
                pre = head;
            }
            head = head.next;
        }
        if( newHead != null && newHead.val != val)
            return newHead;
        return null;
    }


}
