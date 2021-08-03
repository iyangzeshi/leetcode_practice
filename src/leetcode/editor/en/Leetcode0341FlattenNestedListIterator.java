//Given a nested list of integers, implement an iterator to flatten it. 
//
// Each element is either an integer, or a list -- whose elements may also be in
//tegers or other lists. 
//
// Example 1: 
//
// 
// 
//Input: [[1,1],2,[1,1]]
//Output: [1,1,2,1,1]
//Explanation: By calling next repeatedly until hasNext returns false, 
//             the order of elements returned by next should be: [1,1,2,1,1]. 
//
// 
// Example 2: 
//
// 
//Input: [1,[4,[6]]]
//Output: [1,4,6]
//Explanation: By calling next repeatedly until hasNext returns false, 
//             the order of elements returned by next should be: [1,4,6].
// 
// 
// 
// Related Topics Stack Design 
// 👍 1749 👎 694

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import leetcode.editor.NestedInteger;

// 2020-09-12 18:56:31
// Zeshi Yang
public class Leetcode0341FlattenNestedListIterator{
    // Java: flatten-nested-list-iterator
    public static void main(String[] args) {
        NestedInteger num1 = new NestedInteger(1);
        NestedInteger num2 = new NestedInteger(2);
        NestedInteger nestedNode1 = new NestedInteger();
        nestedNode1.add(num1);
        nestedNode1.add(num1);
        NestedInteger nestedNode2 = new NestedInteger(2);
        NestedInteger nestedNode3 = new NestedInteger();
        nestedNode3.add(num1);
        nestedNode3.add(num1);
        List<NestedInteger> list = new ArrayList<>();
        list.add(nestedNode1);
        list.add(nestedNode2);
        list.add(nestedNode3);
        NestedIterator sol = new Leetcode0341FlattenNestedListIterator().new NestedIterator(list);
        // TO TEST
        System.out.println();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    private Queue<Integer> queue;

    // time = O(n + m), space = O(n + k)
    // n : total num of integers, m: total num of lists, k: maximum nesting depth
    public NestedIterator(List<NestedInteger> nestedList) {
        queue = new LinkedList<>();
        loadNestedInteger(queue, nestedList);
    }

    // time = O(1), space = O(n + k)
    @Override
    public Integer next() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.poll();
    }

    // time = O(1), space = O(n + k)
    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    private void loadNestedInteger(Queue<Integer> queue, List<NestedInteger> nestedList) {
        for (NestedInteger n : nestedList) {
            if (n.isInteger()) {
                queue.offer(n.getInteger());
            } else {
                loadNestedInteger(queue, n.getList());
            }
        }
    }
}

/*
  Your NestedIterator object will be instantiated and called as such:
  NestedIterator i = new NestedIterator(nestedList);
  while (i.hasNext()) v[f()] = i.next();
 */
//leetcode submit region end(Prohibit modification and deletion)

}