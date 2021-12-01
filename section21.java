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

    // Question 206
    /**
     *  IDEA 1:
     *  Reverse a list
     *  Space: O(n)
     *  Time: O(n)
     *
     **/
     public ListNode reverseList(ListNode head) {
         ListNode newHead = null;
         ListNode temp = head;
         while( head != null ) {
             newHead = new ListNode(head.val, newHead);
             head = head.next;
         }
         return newHead;
     }

     /**
      *  IDEA 2:
      *  Reverse a list
      *  Space: O(1)
      *  Time: O(n)
      *
      **/
     public ListNode reverseList( ListNode head ) {
         if( head == null )
             return null;
         ListNode prev = null;
         ListNode next = head;
         while( head.next != null ) {
             next = head.next;
             head.next = prev;
             prev = head;
             head = next;
         }
         head.next = prev;
         return head;
     }

    /**
     *  IDEA 3:
     *  Reverse a list
     *  Space: O(1)
     *  Time: O(n)
     *
     * ps. 剑指源码
     *
     **/
     public ListNode reverseList(ListNode head) {
         ListNode prev = null;
         ListNode cur = head;
         while( cur != null ) {
             ListNode next = cur.next;
             cur.next = prev;
             prev = cur;
             cur = next;
         }
         return prev;
     }



    // Question 208
    /**
     * IDEA 1:
     *  Inspired by 剑指，the implementation of the TrieNode
     */
     static class Trie {
         static class TrieNode {
             TrieNode children[];
             boolean isWord;

             public TrieNode() {
                 children = new TrieNode[26];
             }
         }

         private TrieNode root;

         public Trie() {
             root = new TrieNode();
         }

         public void insert(String word) {
             TrieNode temp = root;
             for( char cha: word.toCharArray() ) {
                 if( temp.children[cha - 'a'] == null )
                     temp.children[cha - 'a'] = new TrieNode();
                 temp = temp.children[cha - 'a'];
             }
             temp.isWord = true;
         }

         public boolean search(String word) {
             TrieNode temp = root;
             for( char cha: word.toCharArray() ) {
                 if( temp.children[cha - 'a'] == null )
                     return false;
                 temp = temp.children[cha - 'a'];
             }
             return temp.isWord;
         }

         public boolean startsWith(String prefix) {
             TrieNode temp = root;
             for( char cha: prefix.toCharArray() ) {
                 if( temp.children[cha - 'a'] == null )
                     return false;
                 temp = temp.children[cha - 'a'];
             }
             return true;
         }
     }
}
