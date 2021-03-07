//Given a singly linked list where elements are sorted in ascending order, conve
//rt it to a height balanced BST. 
//
// For this problem, a height-balanced binary tree is defined as a binary tree i
//n which the depth of the two subtrees of every node never differ by more than 1.
// 
//
// Example: 
//
// 
//Given the sorted linked list: [-10,-3,0,5,9],
//
//One possible answer is: [0,-3,9,-10,null,5], which represents the following he
//ight balanced BST:
//
//      0
//     / \
//   -3   9
//   /   /
// -10  5
// 
// Related Topics Linked List Depth-first Search 
// 👍 1984 👎 87

package leetcode.editor.en;

import java.util.HashMap;
import leetcode.editor.ListNode;
import leetcode.editor.TreeNode;

// 2020-07-09 20:48:45
public class Leetcode0109ConvertSortedListToBinarySearchTree {

	// Java: convert-sorted-list-to-binary-search-tree
	public static void main(String[] args) {
		Solution sol = new Leetcode0109ConvertSortedListToBinarySearchTree().new Solution();
		// TO TEST

		System.out.println();
	}
//leetcode submit region begin(Prohibit modification and deletion)
/*
  Definition for singly-linked list.
  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 */
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
// Solution 2: recursion 二等分 + HashMap
class Solution {
	
	public TreeNode sortedListToBST(ListNode head) {
		//corner case
		if (head == null) {
			return null;
		}
		if (head.next == null) {
			return new TreeNode(head.val);
		}
		HashMap<Integer, ListNode> map = new HashMap<>();
		int count = 0;
		ListNode cur = head;
		while (cur != null) {
			map.put(count, cur);
			count++;
			cur = cur.next;
		}
		return helper(0, count - 1, map);
	}
	
	private TreeNode helper(int start, int end, HashMap<Integer, ListNode> map) {
		//base case
		if (start > end) {
			return null;
		}
		int mid = start + (end - start) / 2;
		TreeNode root = new TreeNode(map.get(mid).val);
		root.left = helper(start, mid - 1, map);
		root.right = helper(mid + 1, end, map);
		return root;
	}
	
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: 普通做法，recursion 二等分 T(n) = O(nlog(n))
class Solution1 {

// find the mid to be the root,
// and left subtrees's mid to be the left subtree's root;
// right subtree's mid to be the right subtree's root
// do it recursively
	public TreeNode sortedListToBST(ListNode head) {
		// corner case
		if (head == null) {
			return null;
		}

		return constructBST(0, checkLength(head) - 1, head);
	}

	private int checkLength(ListNode head) {
		if (head == null) {
			return 0;
		}

		return checkLength(head.next) + 1;
	}

	private TreeNode constructBST(int start, int end, ListNode head) {
		if (start > end) {
			return null;
		}

		int mid = start + (end - start) / 2;
		ListNode temp = head;

		for (int i = 0; i < mid; i++) {
			temp = temp.next;
		}

		TreeNode root = new TreeNode(temp.val);
		root.left = constructBST(start, mid - 1, head);
		root.right = constructBST(mid + 1, end, head);
		return root;
	}
}

// Solution 2: recursion 二等分 + HashMap T(n) = O(n)
class Solution2 {
	// Solution 2: HashMap
	public TreeNode sortedListToBST(ListNode head) {
		//corner case
		if (head == null) {
			return null;
		}
		if (head.next == null) {
			return new TreeNode(head.val);
		}
		HashMap<Integer, ListNode> map = new HashMap<>();
		int count = 0;
		ListNode cur = head;
		while (cur != null) {
			map.put(count, cur);
			count++;
			cur = cur.next;
		}
		return helper(0, count - 1, map);
	}

	private TreeNode helper(int start, int end, HashMap<Integer, ListNode> map) {
		//base case
		if (start > end) {
			return null;
		}
		int mid = start + (end - start) / 2;
		TreeNode root = new TreeNode(map.get(mid).val);
		root.left = helper(start, mid - 1, map);
		root.right = helper(mid + 1, end, map);
		return root;
	}
}

}