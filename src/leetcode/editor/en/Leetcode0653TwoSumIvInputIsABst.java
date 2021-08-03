//Given a Binary Search Tree and a target number, return true if there exist two
// elements in the BST such that their sum is equal to the given target. 
//
// Example 1: 
//
// 
//Input: 
//    5
//   / \
//  3   6
// / \   \
//2   4   7
//
//Target = 9
//
//Output: True
// 
//
// 
//
// Example 2: 
//
// 
//Input: 
//    5
//   / \
//  3   6
// / \   \
//2   4   7
//
//Target = 28
//
//Output: False
// 
//
// 
// Related Topics Tree 
// 👍 1432 👎 141

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;


// 2020-07-14 12:26:54
public class Leetcode0653TwoSumIvInputIsABst {

	// Java: two-sum-iv-input-is-a-bst
	public static void main(String[] args) {
		Solution sol = new Leetcode0653TwoSumIvInputIsABst().new Solution();
		// TO TEST
		String str = "2, 1, 3";
		int target = 5;
		TreeNode root = TreeGenerator.deserialize(str);
		String serial = TreeGenerator.serialize(root);
		System.out.println(serial);
		boolean res = sol.findTarget(root, target);
		System.out.println(res);
	}
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
	// Solution 3: 两个stack,左stack模拟array中的left++,右stack模拟right--
	public boolean findTarget(TreeNode root, int k) {
		if (root == null) {
			return false;
		}
		Stack<TreeNode> lStack = new Stack<>(); // left stack
		Stack<TreeNode> rStack = new Stack<>(); // right stack
		// initialize left stack
		initializeLeftStack(lStack, root);
		initializeRightStack(rStack, root);

		while (!lStack.isEmpty() && !rStack.isEmpty()) {
			TreeNode left = lStack.peek();
			TreeNode right = rStack.peek();
			if (left == right) {
				return false;
			}
			if (left.val + right.val == k) {
				// left.val != right.val ->
				// 两个栈都有root 注意root.val * 2 = target的情况
				return true;
			} else if (left.val + right.val < k) { // left++
				lStackGoRight(lStack);
			} else { // right--
				rStackGoLeft(rStack);
			}
		}
		return false;
	}

	private void initializeLeftStack(Stack<TreeNode> stack, TreeNode root) {
		while (root != null) {
			stack.push(root);
			root = root.left;
		}
	}

	private void initializeRightStack(Stack<TreeNode> stack, TreeNode root) {
		while (root != null) {
			stack.push(root);
			root = root.right;
		}
	}
	// 让left stack往右走
	private void lStackGoRight(Stack<TreeNode> stack) {
		TreeNode top = stack.pop();
		TreeNode cur = top.right;
		while (cur != null) {
			stack.push(cur);
			cur = cur.left;
		}
	}

	// 让right stack往左走
	private void rStackGoLeft(Stack<TreeNode> stack) {
		TreeNode top = stack.pop();
		TreeNode cur = top.left;
		while (cur != null) {
			stack.push(cur);
			cur = cur.right;
		}
	}
}

//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: use HashSet and in order traverse
// T(n) = O(n), S(n) = O(n)
class Solution1 {

	public boolean findTarget(TreeNode root, int k) {
		Set<Integer> set = new HashSet<>();
		return find(root, k, set);
	}

	public boolean find(TreeNode root, int k, Set<Integer> set) {
		if (root == null) {
			return false;
		}

		boolean left = find(root.left, k, set);
		if (set.contains(k - root.val)) {
			return true;
		}
		set.add(root.val);
		boolean right = find(root.right, k, set);
		return left || right;
	}
}

// Solution 2: InOrder traverse to form a sorted array
// T(n) = O(n), S(n) = O(n)
class Solution2 {
	
	public boolean findTarget(TreeNode root, int k) {
		List<Integer> list = new ArrayList<>();
		inorder(root, list);
		int l = 0, r = list.size() - 1;
		while (l < r) {
			int sum = list.get(l) + list.get(r);
			if (sum == k) {
				return true;
			}
			if (sum < k) {
				l++;
			} else {
				r--;
			}
		}
		return false;
	}
	public void inorder(TreeNode root, List < Integer > list) {
		if (root == null)
			return;
		inorder(root.left, list);
		list.add(root.val);
		inorder(root.right, list);
	}
}

// Solution 3: 两个stack,左stack模拟array中的left++,右stack模拟right--
// T(n) = O(n), S(n) = O(lgn)
class Solution3 {
	// Solution 3: 两个stack,左stack模拟array中的left++,右stack模拟right--
	public boolean findTarget(TreeNode root, int k) {
		if (root == null) {
			return false;
		}
		Stack<TreeNode> lStack = new Stack<>(); // left stack
		Stack<TreeNode> rStack = new Stack<>(); // right stack
		// initialize left stack
		initializeLeftStack(lStack, root);
		initializeRightStack(rStack, root);

		while (!lStack.isEmpty() && !rStack.isEmpty()) {
			TreeNode left = lStack.peek();
			TreeNode right = rStack.peek();
			if (left == right) {
				return false;
			}
			if (left.val + right.val == k) {
				// left.val != right.val ->
				// 两个栈都有root 注意root.val * 2 = target的情况
				return true;
			} else if (left.val + right.val < k) { // left++
				lStackGoRight(lStack);
			} else { // right--
				rStackGoLeft(rStack);
			}
		}
		return false;
	}
	
	// 让stack沿着left child一直走到底
	private void initializeLeftStack(Stack<TreeNode> stack, TreeNode root) {
		while (root != null) {
			stack.push(root);
			root = root.left;
		}
	}
	
	// 让stack沿着right child一直走到底
	private void initializeRightStack(Stack<TreeNode> stack, TreeNode root) {
		while (root != null) {
			stack.push(root);
			root = root.right;
		}
	}
	// 让left stack往右走
	private void lStackGoRight(Stack<TreeNode> stack) {
		TreeNode top = stack.pop();
		TreeNode cur = top.right;
		while (cur != null) {
			stack.push(cur);
			cur = cur.left;
		}
	}

	// 让right stack往左走
	private void rStackGoLeft(Stack<TreeNode> stack) {
		TreeNode top = stack.pop();
		TreeNode cur = top.left;
		while (cur != null) {
			stack.push(cur);
			cur = cur.right;
		}
	}
}

}