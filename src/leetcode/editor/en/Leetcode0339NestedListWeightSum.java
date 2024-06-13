//Given a nested list of integers, return the sum of all integers in the list we
//ighted by their depth. 
//
// Each element is either an integer, or a list -- whose elements may also be in
//tegers or other lists. 
//
// 
// Example 1: 
//
// 
//Input: [[1,1],2,[1,1]]
//Output: 10 
//Explanation: Four 1's at depth 2, one 2 at depth 1. 
//
// 
// Example 2: 
//
// 
//Input: [1,[4,[6]]]
//Output: 27 
//Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2
// + 6*3 = 27. 
// 
// 
// Related Topics Depth-first Search

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode0339NestedListWeightSum {
	
	// Java: nested-list-weight-sum
	public static void main(String[] args) {
		Solution sol = new Leetcode0339NestedListWeightSum().new Solution();
		// TO TEST
		
		System.out.println();
	}

//leetcode submit region begin(Prohibit modification and deletion)
/*
  // This is the interface that allows for creating nested lists.
  // You should not implement it, or speculate about its implementation
  public interface NestedInteger {
      // Constructor initializes an empty nested list.
      public NestedInteger();
 
      // Constructor initializes a single integer.
      public NestedInteger(int value);
 
      // @return true if this NestedInteger holds a single integer, rather than a nested list.
      public boolean isInteger();
 
      // @return the single integer that this NestedInteger holds, if it holds a single integer
      // Return null if this NestedInteger holds a nested list
      public Integer getInteger();
 
      // Set this NestedInteger to hold a single integer.
      public void setInteger(int value);
 
      // Set this NestedInteger to hold a nested list and adds a nested integer to it.
      public void add(NestedInteger ni);
 
      // @return the nested list that this NestedInteger holds, if it holds a nested list
      // Return empty list if this NestedInteger holds a single integer
      public List<NestedInteger> getList();
  }
 */
// Solution 1: BFS, T(n) = O(n), S(n) = O(n)
// 2 ms,击败了18.29% 的Java用户,
/*
第i层的数字权重是i
所以用BFS从上往下遍历，遍历的时候记录当前层的序号
一层的合算出来之后*当前的层数的序号，就是这一层的加权合
每一层的加权合加起来之后，就是结果
 */
class Solution {
	
	public int depthSum(List<NestedInteger> nestedList) {
		return depthSum(nestedList, 1);
	}
	
	public int depthSum(List<NestedInteger> list, int depth) {
		int sum = 0;
		for (NestedInteger n : list) {
			if (n.isInteger()) {
				sum += n.getInteger() * depth;
			} else {
				sum += depthSum(n.getList(), depth + 1);
			}
		}
		return sum;
	}
}
//leetcode submit region end(Prohibit modification and deletion)
/**面试的时候，用Solution 1 */

// Solution 1: BFS, T(n) = O(n), S(n) = O(n)
// 2 ms,击败了18.29% 的Java用户,
/*
第i层的数字权重是i
所以用BFS从上往下遍历，遍历的时候记录当前层的序号
一层的合算出来之后*当前的层数的序号，就是这一层的加权合
每一层的加权合加起来之后，就是结果
 */
class Solution1 {
	
	public int depthSum(List<NestedInteger> nestedList) {
		int sum = 0;
		int level = 1;
		Queue<NestedInteger> queue = new LinkedList<>();
		for (NestedInteger ni : nestedList) {
			queue.offer(ni);
		}
		
		while (!queue.isEmpty()) {
			int levelSum = 0;
			int size = queue.size();
			while (size-- > 0) {
				NestedInteger cur = queue.poll();
				if (cur.isInteger()) {
					levelSum += cur.getInteger();
				} else {
					for (NestedInteger ni : cur.getList()) {
						queue.offer(ni);
					}
				}
			}
			sum += levelSum * level++;
		}
		return sum;
	}
}

// Solution 2: DFS, T(n) = O(n), S(n) = O(n)
// 0 ms,击败了100.00% 的Java用户, 36 MB,击败了96.00% 的Java用户
/*
第i层的数字权重是i
所以用BFS从上往下遍历，遍历的时候记录当前层的序号
一层的合算出来之后*当前的层数的序号，就是这一层的加权合
每一层的加权合加起来之后，就是结果
 */
class Solution2 {
	
	public int depthSum(List<NestedInteger> nestedList) {
		return depthSum(nestedList, 1);
	}
	
	public int depthSum(List<NestedInteger> list, int depth) {
		int sum = 0;
		for (NestedInteger n : list) {
			if (n.isInteger()) {
				sum += n.getInteger() * depth;
			} else {
				sum += depthSum(n.getList(), depth + 1);
			}
		}
		return sum;
	}
}

abstract class NestedInteger {
	
	// Constructor initializes an empty nested list.
	public NestedInteger() {}
	
	// Constructor initializes a single integer.
	public NestedInteger(int value) {}
	
	// @return true if this NestedInteger holds a single integer, rather than a nested list.
	public abstract boolean isInteger();
	
	// @return the single integer that this NestedInteger holds, if it holds a single integer
	// Return null if this NestedInteger holds a nested list
	public abstract Integer getInteger();
	
	// Set this NestedInteger to hold a single integer.
	public abstract void setInteger(int value);
	
	// Set this NestedInteger to hold a nested list and adds a nested integer to it.
	public abstract void add(NestedInteger ni);
	
	// @return the nested list that this NestedInteger holds, if it holds a nested list
	// Return null if this NestedInteger holds a single integer
	public abstract List<NestedInteger> getList();
}
	
}