//Given a nested list of integers, return the sum of all integers in the list we
//ighted by their depth. 
//
// Each element is either an integer, or a list -- whose elements may also be in
//tegers or other lists. 
//
// Different from the previous question where weight is increasing from root to 
//leaf, now the weight is defined from bottom up. i.e., the leaf level integers ha
//ve weight 1, and the root level integers have the largest weight. 
//
// Example 1: 
//
// 
// 
//Input: [[1,1],2,[1,1]]
//Output: 8 
//Explanation: Four 1's at depth 1, one 2 at depth 2.
// 
//
// 
// Example 2: 
//
// 
//Input: [1,[4,[6]]]
//Output: 17 
//Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4
//*2 + 6*1 = 17.
// 
// 
// 
// Related Topics Depth-first Search

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode0364NestedListWeightSumIi {
	
	// Java: nested-list-weight-sum-ii
	public static void main(String[] args) {
		Solution sol = new Leetcode0364NestedListWeightSumIi().new Solution();
		// TO TEST
		
		System.out.println();
	}
//leetcode submit region begin(Prohibit modification and deletion)

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
// Solution 1: BFS, 每增加一级，都加上原来所有数字的总和
/*
记录3个值
res 从第1层开始到当前层，答案所要求的加权求和
sum 从第1层开始到现在的算数（每一层权重1）求和
每遍历完一层，就用res += sum
 */
class Solution {
	
	public int depthSumInverse(List<NestedInteger> nestedList) {
		
		Queue<NestedInteger> queue = new LinkedList<>();
		for (NestedInteger ni : nestedList) {
			queue.offer(ni);
		}
		int sum = 0;
		int levelSum = 0;
		while (!queue.isEmpty()) {
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
			sum += levelSum;
		}
		return sum;
	}
	
}
//leetcode submit region end(Prohibit modification and deletion)
/**面试的时候，用Solution 2*/

// Solution 1: BFS, 每增加一级，都加上原来所有数字的总和
/*
记录3个值
res 从第1层开始到当前层，答案所要求的加权求和
sum 从第1层开始到现在的算数（每一层权重1）求和
每遍历完一层，就用res += sum
 */
class Solution1 {
	
	public int depthSumInverse(List<NestedInteger> nestedList) {
		
		Queue<NestedInteger> queue = new LinkedList<>();
		for (NestedInteger ni : nestedList) {
			queue.offer(ni);
		}
		int res = 0;
		int sum = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				NestedInteger cur = queue.poll();
				if (cur.isInteger()) {
					sum += cur.getInteger();
				} else {
					for (NestedInteger ni : cur.getList()) {
						queue.offer(ni);
					}
				}
			}
			res += sum;
		}
		return res;
	}
	
}

// Solution 2: BFS
// 2 ms,击败了6.08% 的Java用户, 38.6 MB,击败了5.45% 的Java用户
/*
BFS遍历NestedInteger
计算3个值
sum: 所有值的求和
weightedSum: 所有数的加权求和，深度第i的数字，权数为i
level - nested的层数

结果是sum * level - weighted sum
 */
class Solution2 {
	
	public int depthSumInverse(List<NestedInteger> nestedList) {
		
		Queue<NestedInteger> queue = new LinkedList<>();
		for (NestedInteger ni : nestedList) {
			queue.offer(ni);
		}
		int weightedSum = 0;
		int sum = 0;
		// int levelSum = 0;
		int level = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			int levelSum = 0;
			while (size-- > 0) {
				NestedInteger cur = queue.poll();
				if (cur.isInteger()) {
					levelSum += cur.getInteger();
				} else {
					for (NestedInteger ni: cur.getList()) {
						queue.offer(ni);
					}
				}
			}
			weightedSum += levelSum * level++;
			sum += levelSum;
		}
		
		return sum * level - weightedSum;
	}
}

abstract class NestedInteger {
	
	// Constructor initializes an empty nested list.
	public NestedInteger() { }
	
	// Constructor initializes a single integer.
	public NestedInteger(int value) { }
	
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