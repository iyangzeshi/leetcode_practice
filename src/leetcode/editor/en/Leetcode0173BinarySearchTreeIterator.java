//Implement an iterator over a binary search tree (BST). Your iterator will be i
//nitialized with the root node of a BST. 
//
// Calling next() will return the next smallest number in the BST. 
//
// 
//
// 
// 
//
// Example: 
//
// 
//
// 
//BSTIterator iterator = new BSTIterator(root);
//iterator.next();    // return 3
//iterator.next();    // return 7
//iterator.hasNext(); // return true
//iterator.next();    // return 9
//iterator.hasNext(); // return true
//iterator.next();    // return 15
//iterator.hasNext(); // return true
//iterator.next();    // return 20
//iterator.hasNext(); // return false
// 
//
// 
//
// Note: 
//
// 
// next() and hasNext() should run in average O(1) time and uses O(h) memory, wh
//ere h is the height of the tree. 
// You may assume that next() call will always be valid, that is, there will be 
//at least a next smallest number in the BST when next() is called. 
// 
// Related Topics Stack Tree Design 
// 👍 2614 👎 273

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import leetcode.editor.TreeDrawer;
import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-08-06 22:31:29
// Jesse Yang
public class Leetcode0173BinarySearchTreeIterator {

	// Java: binary-search-tree-iterator
	public static void main(String[] args) {
		// String str = "3,2,3,4,5,6,7,8,9,10";
		String str = "1, 2, 3, 6 , 8";
		TreeNode root = TreeGenerator.deserialize(str);
		TreeDrawer.draw(root);
		BSTIterator iterator = new Leetcode0173BinarySearchTreeIterator().new BSTIterator(root);
		// TO TEST
		List<Integer> res = new LinkedList<>();
		while (iterator.hasNext()) {
			res.add(iterator.next());
		}
		System.out.println(res);
	}

	//leetcode submit region begin(Prohibit modification and deletion)
//  Definition for a binary tree node.
/* public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }*/
	class BSTIterator {

		Stack<TreeNode> stack;

		public BSTIterator(TreeNode root) {
			stack = new Stack<>();
			TreeNode cur = root;
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
		}

		/**
		 * @return the next smallest number
		 */
		public int next() {
			TreeNode top = stack.pop();
			TreeNode cur = top.right;
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			return top.val;
		}

		/**
		 * @return whether we have a next smallest number
		 */
		public boolean hasNext() {
			return !stack.isEmpty();
		}
	}

/*
  Definition for a binary tree node.
  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
 */
//leetcode submit region end(Prohibit modification and deletion)

}