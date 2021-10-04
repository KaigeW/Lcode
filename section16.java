class Solution {
    // Question 155
    /**
     * IDEA 1:
     * Having two member variables to help achieve constant time. Only pop Will
     * takes time(What if we removed a min? )
     */
    class MinStack {

        private Stack<Integer> stk;
        private int min;
        private int len = 0;
        /** initialize your data structure here. */
        public MinStack() {
            stk = new Stack<Integer>();
        }

        public void push(int x) {
            if( len == 0 )
                min = x;
            ++len;
            stk.push(x);
            if( min > x )
                min = x;
        }

        public void pop() {
            --len;
            if( stk.pop() == min ){
                if( len != 0){
                    this.min = stk.pop();
                    Stack<Integer> sto = new Stack<>();
                    sto.push(this.min);
                    while( !stk.isEmpty() ){
                        int temp = stk.pop();
                        if( this.min > temp )
                            this.min = temp;
                        sto.push(temp);
                    }
                    while( !sto.isEmpty() ){
                        stk.push(sto.pop());
                    }
                }
            }
        }

        public int top() {
            return stk.peek();
        }

        public int getMin() {
            return min;
        }
    }

    /**
     * IDEA 2:
     */
    class MinStack {
        int min = Integer.MAX_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        public void push(int x) {
            // only push the old minimum value when the current
            // minimum value changes after pushing the new value x
            if(x <= min){
                stack.push(min);
                min=x;
            }
            stack.push(x);
        }

        public void pop() {
            // if pop operation could result in the changing of the current minimum value,
            // pop twice and change the current minimum value to the last minimum value.
            if(stack.pop() == min) min=stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    /**
     * IDEA 3:
     */
    public class MinStack {
        long min;
        Stack<Long> stack;

        public MinStack(){
            stack=new Stack<>();
        }

        public void push(int x) {
            if (stack.isEmpty()){
                stack.push(0L);
                min=x;
            }else{
                stack.push(x-min);//Could be negative if min value needs to change
                if (x<min) min=x;
            }
        }

        public void pop() {
            if (stack.isEmpty()) return;

            long pop=stack.pop();

            if (pop<0)  min=min-pop;//If negative, increase the min value

        }

        public int top() {
            long top=stack.peek();
            if (top>0){
                return (int)(top+min);
            }else{
                return (int)(min);
          }
        }

        public int getMin() {
            return (int)min;
        }
    }


    // Question 160
    /**
     * IDEA 1:
     * Figure out the length of two lists, then let the longer one start first,
     *   until they have the same remaining nodes. We can check the equality of
     *   the node
     *
     *  Space: O(1)
     *  Time: O(n)
     *  Idea inspired by 剑指
     */
     public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
         int lengthA = 0, lengthB = 0;
         ListNode newHeadA = headA, newHeadB = headB;
         while( newHeadA != null ) {
             newHeadA = newHeadA.next;
             lengthA++;
         }
         while( newHeadB != null ) {
             newHeadB = newHeadB.next;
             lengthB++;
         }
         if( lengthA > lengthB ) {
             int loop = lengthA - lengthB;
             while( loop-- > 0 ){
                 headA = headA.next;
             }
         } else if( lengthA < lengthB ){
             int loop = lengthB - lengthA;
             while( loop-- > 0 ){
                 headB = headB.next;
             }
         }

         while( headA != headB ) {
             headA = headA.next;
             headB = headB.next;
         }

         return headA;
     }





}
