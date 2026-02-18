//Design a max stack data structure that supports the stack operations and suppo
//rts finding the stack's maximum element. 
//
// Implement the MaxStack class: 
//
// 
// MaxStack() Initializes the stack object. 
// void push(int x) Pushes element x onto the stack. 
// int pop() Removes the element on top of the stack and returns it. 
// int top() Gets the element on the top of the stack without removing it. 
// int peekMax() Retrieves the maximum element in the stack without removing it.
// 
// int popMax() Retrieves the maximum element in the stack and removes it. If th
//ere is more than one maximum element, only remove the top-most one. 
// 
//
// 
// Example 1: 
//
// 
//Input
//["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop",
// "top"]
//[[], [5], [1], [5], [], [], [], [], [], []]
//Output
//[null, null, null, null, 5, 5, 1, 5, 1, 5]
//
//Explanation
//MaxStack stk = new MaxStack();
//stk.push(5);   // [5] the top of the stack and the maximum number is 5.
//stk.push(1);   // [5, 1] the top of the stack is 1, but the maximum is 5.
//stk.push(5);   // [5, 1, 5] the top of the stack is 5, which is also the maxim
//um, because it is the top most one.
//stk.top();     // return 5, [5, 1, 5] the stack did not change.
//stk.popMax();  // return 5, [5, 1] the stack is changed now, and the top is di
//fferent from the max.
//stk.top();     // return 1, [5, 1] the stack did not change.
//stk.peekMax(); // return 5, [5, 1] the stack did not change.
//stk.pop();     // return 1, [5] the top of the stack and the max element is no
//weight 5.
//stk.top();     // return 5, [5] the stack did not change.
// 
//
// 
// Constraints: 
//
// 
// -107 <= x <= 107 
// At most 104 calls will be made to push, pop, top, peekMax, and popMax. 
// There will be at least one element in the stack when pop, top, peekMax, or po
//pMax is called. 
// 
//
// 
//Follow up: Could you come up with a solution that supports O(1) for each top c
//all and O(logn) for each other call? Related Topics Design 
// 👍 883 👎 210

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;

// 2021-02-10 14:43:53
// Jesse Yang
public class Leetcode0716MaxStack{
    // Java: max-stack
    public static void main(String[] args) {
        MaxStack sol = new Leetcode0716MaxStack().new MaxStack();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class MaxStack {
    
    Stack<Integer> stack;
    Stack<Integer> maxStack;
    
    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }
    
    // O(1)
    public void push(int x) {
        stack.push(x);
        int top = maxStack.isEmpty() ? x : maxStack.peek();
        maxStack.push(Math.max(top, x));
    }
    
    //O(1)
    public int pop() {
        maxStack.pop();
        return stack.pop();
    }
    
    // O(1)
    public int top() {
        return stack.peek();
    }
    
    // O(1)
    public int peekMax() {
        return maxStack.peek();
    }
    
    // O(n)
    public int popMax() {
        int max = peekMax();
        Stack<Integer> buffer = new Stack<>();
        while (top() != max) {
            buffer.push(pop());
        }
        pop();
        while (!buffer.isEmpty()) {
            push(buffer.pop());
        }
        return max;
    }
    
}
/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: 2 stacks
// T(n) = O(n) - popMax, T(n) = O(1) - other, S(n) = O(n)
// 17 ms,击败了52.72% 的Java用户, 41.5 MB,击败了53.64% 的Java用户
/*
思想: 维护2个stack，
    一个就是普通的stack,
    另外一个maxStack里面存的是从底部开始到当前的最大值
step1: push, pop, top都和普通的stack一样
step2: peekMax 直接return maxStack.top()
step3: 让2个Stack一直pop，直到stack里面pop的值是和maxStack里pop的值一样为止
 */
class MaxStack1 {
    
    Stack<Integer> stack;
    Stack<Integer> maxStack;
    
    public MaxStack1() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }
    
    // O(1)
    public void push(int x) {
        stack.push(x);
        int top = maxStack.isEmpty() ? x : maxStack.peek();
        maxStack.push(Math.max(top, x));
    }
    
    //O(1)
    public int pop() {
        maxStack.pop();
        return stack.pop();
    }
    
    // O(1)
    public int top() {
        return stack.peek();
    }
    
    // O(1)
    public int peekMax() {
        return maxStack.peek();
    }
    
    // O(n)
    public int popMax() {
        int max = peekMax();
        Stack<Integer> buffer = new Stack<>();
        while (top() != max) {
            buffer.push(pop());
        }
        pop();
        while (!buffer.isEmpty()) {
            push(buffer.pop());
        }
        return max;
    }
    
}

// Solution 2: Double Linked List + TreeMap
// T(n) = O(lgn) for every operation, S(n) = O(n)
// 15 ms,击败了85.16% 的Java用户, 40.9 MB,击败了80.87% 的Java用户
/*
思想：维护一个DDL和TreeMap
DLL(double linkedlist)是按照时间顺序排好的DLL
TreeMap的key是数字，List<Node> 是这个数字的一系列位置
 */
class MaxStack2 {
    
    class Node {
        
        int val;
        Node prev, next;
        
        public Node(int v) {
            val = v;
        }
        
    }
    
    class DoubleLinkedList {
        
        Node head, tail;
        
        public DoubleLinkedList() {
            head = new Node(0);
            tail = new Node(0);
            head.next = tail;
            tail.prev = head;
        }
        
        public void add(Node node) {
            node.next = tail;
            node.prev = tail.prev;
            tail.prev = tail.prev.next = node;
        }
        
        public int pop() {
            return unlink(tail.prev).val;
        }
        
        public int peek() {
            return tail.prev.val;
        }
        
        public Node unlink(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            return node;
        }
    
    }
    
    TreeMap<Integer, List<Node>> map;
    DoubleLinkedList dll;
    // O(1)
    
    public MaxStack2() {
        map = new TreeMap<>();
        dll = new DoubleLinkedList();
    }
    // O(lgn)
    
    public void push(int x) {
        Node node = new Node(x);
        dll.add(node);
        map.computeIfAbsent(x, k -> new ArrayList<>()).add(node);
    }
    // O(lgn)
    
    public int pop() {
        int val = dll.pop();
        List<Node> list = map.get(val);
        list.remove(list.size() - 1);
        if (list.isEmpty()) {
            map.remove(val);
        }
        return val;
    }
    // O(1)
    
    public int top() {
        return dll.peek();
    }
    // O(lgn)
    
    public int peekMax() {
        return map.lastKey();
    }
    // O(lgn)
    
    public int popMax() {
        int max = peekMax();
        List<Node> list = map.get(max);
        Node node = list.remove(list.size() - 1);
        dll.unlink(node);
        if (list.isEmpty()) {
            map.remove(max);
        }
        return max;
    }
    
}
}
