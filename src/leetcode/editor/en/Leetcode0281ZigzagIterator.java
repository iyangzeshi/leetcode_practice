//Given two 1d vectors, implement an iterator to return their elements alternate
//ly. 
//
// 
//
// Example: 
//
// 
//Input:
//v1 = [1,2]
//v2 = [3,4,5,6] 
//Output: [1,3,2,4,5,6]
//Explanation: By calling next repeatedly until hasNext returns false, the order
// of elements returned by next should be: [1,3,2,4,5,6]. 
//
// 
//
// Follow up: 
//
// What if you are given k 1d vectors? How well can your code be extended to suc
//h cases? 
//
// Clarification for the follow up question: 
//The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If
// "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For exampl
//e: 
//
// 
//Input:
//[1,2,3]
//[4,5,6,7]
//[8,9]
//
//Output: [1,4,8,2,5,9,3,6,7].
// 
// Related Topics Design 
// 👍 380 👎 21

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

// 2020-08-07 10:38:23
// Zeshi Yang
public class Leetcode0281ZigzagIterator {

	// Java: zigzag-iterator
	public static void main(String[] args) {
//        List<Integer> v1 = new ArrayList<>(Arrays.asList(1,2,3,4));
//        List<Integer> v2 = new ArrayList<>(Arrays.asList(5,7,19,12,16,19));
		List<Integer> v1 = new ArrayList<>();
		List<Integer> v2 = new ArrayList<>();
		ZigzagIterator iter = new Leetcode0281ZigzagIterator().new ZigzagIterator(v1, v2);
		// TO TEST
        List<Integer> res = new LinkedList<>();
        while (iter.hasNext()) {
            res.add(iter.next());
        }
		System.out.println(res);
	}

//leetcode submit region begin(Prohibit modification and deletion)
public class ZigzagIterator {

	Queue<ListIterator<Integer>> queue;

	public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
		queue = new LinkedList<>();
		if (!v1.isEmpty()) {
			queue.add(v1.listIterator());
		}
		if (!v2.isEmpty()) {
			queue.add(v2.listIterator());
		}
	}

	public int next() {
		ListIterator<Integer> head = queue.poll();
		int res = head.next();
		if (head.hasNext()) {
			queue.offer(head);
		}
		return res;
	}

	public boolean hasNext() {
		return !queue.isEmpty();
	}
}

/*
  Your ZigzagIterator object will be instantiated and called as such:
  ZigzagIterator i = new ZigzagIterator(v1, v2);
  while (i.hasNext()) v[f()] = i.next();
 */
//leetcode submit region end(Prohibit modification and deletion)

}