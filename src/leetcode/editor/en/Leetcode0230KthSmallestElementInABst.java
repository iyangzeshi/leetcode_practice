//Given a binary search tree, write a function kthSmallest to find the kth small
//est element in it. 
//
// 
//
// Example 1: 
//
// 
//Input: root = [3,1,4,null,2], k = 1
//   3
//  / \
// 1   4
//  \
//   2
//Output: 1 
//
// Example 2: 
//
// 
//Input: root = [5,3,6,2,4,null,null,1], k = 3
//       5
//      / \
//     3   6
//    / \
//   2   4
//  /
// 1
//Output: 3
// 
//
// Follow up: 
//What if the BST is modified (insert/delete operations) often and you need to f
//ind the kth smallest frequently? How would you optimize the kthSmallest routine?
// 
//
// 
// Constraints: 
//
// 
// The number of elements of the BST is between 1 to 10^4. 
// You may assume k is always valid, 1 ≤ k ≤ BST's total elements. 
// 
// Related Topics Binary Search Tree 
// 👍 2646 👎 64

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Stack;
import leetcode.editor.TreeNode;

// 2020-07-26 14:12:01
// Zeshi Yang
public class Leetcode0230KthSmallestElementInABst{
    // Java: kth-smallest-element-in-a-bst
    public static void main(String[] args) {
        Solution sol = new Leetcode0230KthSmallestElementInABst().new Solution();
        // TO TEST
        System.out.println();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
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
// T(n, k) = O(n, k), S(n, k) = O(n, k)
class Solution {
	public int kthSmallest(TreeNode root, int k) {
		int[] kIdxNum = new int[2]; // kIdxNum[0] = idx, kIdxNum[1] = number
		inOrder(root, k, kIdxNum);
		return kIdxNum[1];
	}
	
	private void inOrder(TreeNode root, int k, int[] kIdxNum) {
		// corner & base case
		if (root == null) {
			return;
		}
		
		// general case
		inOrder(root.left, k, kIdxNum);
		if (kIdxNum[0] < k) {
			kIdxNum[0]++;
			if (kIdxNum[0] == k) {
				kIdxNum[1] = root.val;
				return;
			}
		}
		inOrder(root.right, k, kIdxNum);
	}
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1_1: recursion, in-order traverse, get a sorted array
class Solution1 {
    public int kthSmallest(TreeNode root, int k) {
	    ArrayList<Integer> nums = new ArrayList<>();
		inOrder(root, k, nums);
        return nums.get(k - 1);
    }

    private void inOrder(TreeNode root, int k, ArrayList<Integer> array) {
        // corner & base case
        if (root == null) {
            return;
        }

        // general case
	    inOrder(root.left, k, array);
        if (array.size() < k) {
            array.add(root.val);
        }
	    inOrder(root.right, k, array);
    }
}

// Solution 1_2: recursion, in-order traverse to k-th smallest number, return
// T(n, k) = O(n, k), S(n, k) = O(n, k)
class Solution1_2 {
	public int kthSmallest(TreeNode root, int k) {
		int[] kIdxNum = new int[2]; // kIdxNum[0] = idx, kIdxNum[1] = number
		inOrder(root, k, kIdxNum);
		return kIdxNum[1];
	}
	
	private void inOrder(TreeNode root, int k, int[] kIdxNum) {
		// corner & base case
		if (root == null) {
			return;
		}
		
		// general case
		inOrder(root.left, k, kIdxNum);
		if (kIdxNum[0] < k) {
			kIdxNum[0]++;
			if (kIdxNum[0] == k) {
				kIdxNum[1] = root.val;
				return;
			}
		}
		inOrder(root.right, k, kIdxNum);
	}
}

// Solution 2: stack inorder iterate BST
// T(n, k) = O(n, k), S(n, k) = O(n, k)
class Solution3 {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();

        // in order traverse by iteration
        while(root != null || !stack.isEmpty()) {
            if (root == null) {
                root = stack.pop();
                if(--k == 0) {
                    return root.val;
                }
                root = root.right;
            } else {
                stack.push(root);
                root = root.left;
            }
        }
        return -1;
    }
}
}