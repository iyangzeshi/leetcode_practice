//Design a stack that supports push, pop, top, and retrieving the minimum elemen
//t in constant time. 
//
// 
// push(x) -- Push element x onto stack. 
// pop() -- Removes the element on top of the stack. 
// top() -- Get the top element. 
// getMin() -- Retrieve the minimum element in the stack. 
// 
//
// 
// Example 1: 
//
// 
//Input
//["MinStack","push","push","push","getMin","pop","top","getMin"]
//[[],[-2],[0],[-3],[],[],[],[]]
//
//Output
//[null,null,null,null,-3,null,0,-2]
//
//Explanation
//MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin(); // return -3
//minStack.pop();
//minStack.top();    // return 0
//minStack.getMin(); // return -2
// 
//
// 
// Constraints: 
//
// 
// Methods pop, top and getMin operations will always be called on non-empty sta
//cks. 
// 
// Related Topics Stack Design 
// 👍 3554 👎 343

package leetcode.editor.en;

import java.util.Stack;
// 2020-08-04 11:40:04
// Zeshi Yang
public class Leetcode0155MinStack{
    // Java: min-stack
    public static void main(String[] args) {
        MinStack sol = new Leetcode0155MinStack().new MinStack();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// T(n) = O(1), S(n) = O(n)
class MinStack {
    
    Stack<Integer> stack;
    Stack<Integer> minStack;
    
    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            minStack.push(Math.min(minStack.peek(), x));
        }
    }
    
    public void pop() {
        if (!stack.isEmpty()) {
            minStack.pop();
            stack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        if (minStack.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return minStack.peek();
        }
    }
    
}
/*
  Your MinStack object will be instantiated and called as such:
  MinStack obj = new MinStack();
  obj.push(x);
  obj.pop();
  int param_3 = obj.top();
  int param_4 = obj.getMin();
 */

/*
  Your MinStack object will be instantiated and called as such:
  MinStack obj = new MinStack();
  obj.push(x);
  obj.pop();
  int param_3 = obj.top();
  int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)

}