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

import java.util.*;
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
    // Solution 3: iteration, stack
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
            }
            else {
                stack.push(root);
                root = root.left;
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: recursion, in-order traverse, get a sorted array
class Solution1 {
    // Solution 1: in-Order traverse
    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> nums = inOrder(root, k, new ArrayList<>());
        return nums.get(k - 1);
    }

    private ArrayList<Integer> inOrder(TreeNode root, int k, ArrayList<Integer> array) {
        // corner & base case
        if (root == null) {
            return array;
        }

        // general case
        if (array.size() < k) {
            inOrder(root.left, k, array);
        }
        if (array.size() < k) {
            array.add(root.val);
        }
        if (array.size() < k) {
            inOrder(root.right, k, array);
        }
        return array;
    }
}

// Solution 2: 用heap
/*    要么是用maxHeap，in order traverse前面k个，然后return heap.poll()
    或者是用minHeap遍历整个BST，然后pop() k次*/


// Solution 3: stack
class Solution3 {
    // Solution 3: iteration, stack
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
            }
            else {
                stack.push(root);
                root = root.left;
            }
        }
        return -1;
    }
}
}