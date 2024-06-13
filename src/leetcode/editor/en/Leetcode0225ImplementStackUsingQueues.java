//Implement a last in first out (LIFO) stack using only two queues. The implemen
//ted stack should support all the functions of a normal queue (push, top, pop, an
//d empty). 
//
// Implement the MyStack class: 
//
// 
// void push(int x) Pushes element x to the top of the stack. 
// int pop() Removes the element on the top of the stack and returns it. 
// int top() Returns the element on the top of the stack. 
// boolean empty() Returns true if the stack is empty, false otherwise. 
// 
//
// Notes: 
//
// 
// You must use only standard operations of a queue, which means only push to ba
//ck, peek/pop from front, size, and is empty operations are valid. 
// Depending on your language, the queue may not be supported natively. You may 
//simulate a queue using a list or deque (double-ended queue), as long as you use 
//only a queue's standard operations. 
// 
//
// 
// Example 1: 
//
// 
//Input
//["MyStack", "push", "push", "top", "pop", "empty"]
//[[], [1], [2], [], [], []]
//Output
//[null, null, null, 2, 2, false]
//
//Explanation
//MyStack myStack = new MyStack();
//myStack.push(1);
//myStack.push(2);
//myStack.top(); // return 2
//myStack.pop(); // return 2
//myStack.empty(); // return False
// 
//
// 
// Constraints: 
//
// 
// 1 <= x <= 9 
// At most 100 calls will be made to push, pop, top, and empty. 
// All the calls to pop and top are valid. 
// 
//
// 
//Follow-up: Can you implement the stack such that each operation is amortized O
//(1) time complexity? In other words, performing n operations will take overall O
//(n) time even if one of those operations may take longer. Related Topics Stack D
//esign 
// 👍 879 👎 631

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;
// 2020-12-12 20:09:24
// Jesse Yang
public class Leetcode0225ImplementStackUsingQueues{
    // Java: implement-stack-using-queues
    public static void main(String[] args) {
        MyStack sol = new Leetcode0225ImplementStackUsingQueues().new MyStack();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class MyStack {
    
    private Queue<Integer> q1;
    private Queue<Integer> q2;
    private Integer top;
    
    /** Initialize your data structure here.*/
    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }
    
    /** Push element x onto stack.*/
    public void push(int x) {
        q1.add(x);
        top = x;
    }
    
    /** Removes the element on top of the stack and returns that element.*/
    public int pop() {
        while (q1.size() > 1) {
            top = q1.remove();
            q2.add(top);
        }
        int res = q1.poll();
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
        return res;
    }
    
    /** Get the top element.*/
    public int top() {
        return top;
    }
    
    /** Returns whether the stack is empty.*/
    public boolean empty() {
        return q1.isEmpty();
    }
    
}
/*
  Your MyStack object will be instantiated and called as such:
  MyStack obj = new MyStack();
  obj.push(x);
  int param_2 = obj.pop();
  int param_3 = obj.top();
  boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: push: T(n) = O(n); peek: T(n) = O(1), pop: T(n) = O(1)
// 0 ms,击败了100.00% 的Java用户, 36.1 MB,击败了98.97% 的Java用户
/**
 * 用queue实现stack，
 * queue是FIFO, stack是FILO有两种方法
 * 第1种方法：
 *      设置一个queue
 *      实现stack的push, 把queue里面原先所有的element一次poll出来在offer到queue的末尾, O(n)
 *      实现stack的peek, 把queue里面的第一个元素peek出来, O(1)
 *      实现stack的pop, 把queue里面的第一个元素poll出来, O(1)
 */
class MyStack1 {
    
    Queue<Integer> queue;
    
    /** Initialize your data structure here.*/
    public MyStack1() {
        queue = new LinkedList<>();
    }
    
    /** Push element x onto stack.*/
    public void push(int x) {
        int size = queue.size();
        queue.offer(x);
        
        while (size-- > 0) {
            queue.offer(queue.poll());
        }
    }
    
    /** Removes the element on top of the stack and returns that element.*/
    public int pop() {
        return queue.poll();
    }
    
    /** Get the top element.*/
    public int top() {
        return queue.peek();
    }
    
    /** Returns whether the stack is empty.*/
    public boolean empty() {
        return queue.isEmpty();
    }
    
}

// Solution 2: push: T(n) = O(1); peek: T(n) = O(1), pop: T(n) = O(n)
// 0 ms,击败了100.00% 的Java用户, 36.8 MB,击败了69.07% 的Java用户
/**
 * 第2种方法：
 *      设置两个queue
 *      实现stack的push, 直接把新的元素放到offer到queue里面
 *      实现stack的peek, 把queue1里面所有元素，都依次offer另外一个queue2里面，return queue1里面的最后一个元素
 *      实现stack里面的pop，把queue1里面除了最后一个元素以外的其他元素，都依次offer到queue2里面，queue1里面的最后一个元素poll出来
 *
 */
class MyStack2 {
    
    private Queue<Integer> q1;
    private Queue<Integer> q2;
    private Integer top;
    
    /** Initialize your data structure here.*/
    public MyStack2() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }
    
    /** Push element x onto stack.*/
    public void push(int x) {
        q1.add(x);
        top = x;
    }
    
    /** Removes the element on top of the stack and returns that element.*/
    public int pop() {
        while (q1.size() > 1) {
            top = q1.remove();
            q2.add(top);
        }
        int res = q1.poll();
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
        return res;
    }
    
    /** Get the top element.*/
    public int top() {
        return top;
    }
    
    /** Returns whether the stack is empty.*/
    public boolean empty() {
        return q1.isEmpty();
    }
    
}
}