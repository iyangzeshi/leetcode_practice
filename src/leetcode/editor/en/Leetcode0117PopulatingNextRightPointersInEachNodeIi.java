//Given a binary tree 
//
// 
//struct Node {
//  int val;
//  Node *left;
//  Node *right;
//  Node *next;
//}
// 
//
// Populate each next pointer to point to its next right node. If there is no ne
//xt right node, the next pointer should be set to NULL. 
//
// Initially, all next pointers are set to NULL. 
//
// 
//
// Follow up: 
//
// 
// You may only use constant extra space. 
// Recursive approach is fine, you may assume implicit stack space does not coun
//t as extra space for this problem. 
// 
//
// 
// Example 1: 
//
// 
//
// 
//Input: root = [1,2,3,4,5,null,7]
//Output: [1,#,2,3,#,4,5,7,#]
//Explanation: Given the above binary tree (Figure A), your function should popu
//late each next pointer to point to its next right node, just like in Figure B. T
//he serialized output is in level order as connected by the next pointers, with '
//#' signifying the end of each level.
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the given tree is less than 6000. 
// -100 <= node.val <= 100 
// 
// Related Topics Tree Depth-first Search 
// 👍 1540 👎 176

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;

// 2020-07-10 00:48:59
public class Leetcode0117PopulatingNextRightPointersInEachNodeIi {

	// Java: populating-next-right-pointers-in-each-node-ii
	public static void main(String[] args) {
		Solution sol = new Leetcode0117PopulatingNextRightPointersInEachNodeIi().new Solution();
		// TO TEST

		System.out.println();
	}
//leetcode submit region begin(Prohibit modification and deletion)
// Definition for a Node.
/*
class Node {
    public int val;
    public Node left;
    public Node right;
	public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {

	// leftmost: 当前层最左边Node
	// prev: 下一层的上一个childNode
	// 方法：走这一层，用processChild()链接下一层相邻的childNode

	public Node connect(Node root) {
		Node[] prev = new Node[1];
		Node[] leftmost = new Node[1];
		if (root == null) {
			return root;
		}
		leftmost[0] = root;
		while (leftmost[0] != null) {
			prev[0] = null;
			Node cur = leftmost[0];
			leftmost[0] = null;
			while (cur != null) {
				processChild(cur.left, prev, leftmost);
				processChild(cur.right, prev, leftmost);
				cur = cur.next;
			}
		}
		return root;
	}

	public void processChild(Node childNode, Node[] prev, Node[] leftmost) {
		if (childNode != null) {
			// If the "prev" pointer is already set, setup its next pointer
			if (prev[0] != null) {
				prev[0].next = childNode;
			} else {
				// else it means this child node is the first node
				leftmost[0] = childNode;
			}
			prev[0] = childNode;
		}
	}
}

//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: BFS
class Solution1 {

	public Node connect(Node root) {
		if (root == null) {
			return root;
		}

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				Node node = queue.poll();
				if (i < size - 1) {
					node.next = queue.peek();
				}
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}
		}
		// Since the tree has now been modified, return the root node
		return root;
	}
}

// Solution 2: 这一层，用processChild()链接下一层相邻的childNode
class Solution2 {

	// leftmost: 当前层最左边Node
	// prev: 下一层的上一个childNode
	// 方法：走这一层，用processChild()链接下一层相邻的childNode

	public Node connect(Node root) {
		Node[] prev = new Node[1];
		Node[] leftmost = new Node[1];
		if (root == null) {
			return root;
		}
		leftmost[0] = root;
		Node curr;
		while (leftmost[0] != null) {
			prev[0] = null;
			curr = leftmost[0];
			leftmost[0] = null;
			while (curr != null) {
				processChild(curr.left, prev, leftmost);
				processChild(curr.right, prev, leftmost);
				curr = curr.next;
			}
		}
		return root;
	}

	public void processChild(Node childNode, Node[] prev, Node[] leftmost) {
		if (childNode != null) {
			// If the "prev" pointer is already set, setup its next pointer
			if (prev[0] != null) {
				prev[0].next = childNode;
			} else {
				// else it means this child node is the first node
				leftmost[0] = childNode;
			}
			prev[0] = childNode;
		}
	}
}

private class Node {

	public int val;
	public Node left;
	public Node right;
	public Node next;

	public Node() {
	}

	public Node(int _val) {
		val = _val;
	}

	public Node(int _val, Node _left, Node _right, Node _next) {
		val = _val;
		left = _left;
		right = _right;
		next = _next;
	}
}
}