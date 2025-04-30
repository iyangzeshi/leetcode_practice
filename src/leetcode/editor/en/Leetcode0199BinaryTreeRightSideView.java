//Given a binary tree, imagine yourself standing on the right side of it, return
// the values of the nodes you can see ordered from top to bottom. 
//
// Example: 
//
// 
//Input: [1,2,3,null,5,null,4]
//Output: [1, 3, 4]
//Explanation:
//
//   1            <---
// /   \
//2     3         <---
// \     \
//  5     4       <---
// Related Topics Tree Depth-first Search Breadth-first Search 
// 👍 2585 👎 150

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import leetcode.editor.TreeNode;

// 2020-09-08 14:47:49
// Jesse Yang
public class Leetcode0199BinaryTreeRightSideView{
    // Java: binary-tree-right-side-view
    public static void main(String[] args) {
        Solution sol = new Leetcode0199BinaryTreeRightSideView().new Solution();
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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightSideList = new ArrayList<>();
        // corner case
        if (root == null) {
            return rightSideList;
        }
        dfs(0, root, rightSideList);
        return rightSideList;
    }

    private void dfs (int index, TreeNode cur, List<Integer> rightSideList) {
        // corner case
        if (cur == null) {
            return;
        }
        if (index == rightSideList.size()) {
            rightSideList.add(cur.val);
        }
        if (cur.right != null) {
            dfs(index + 1, cur.right, rightSideList);
        }
        if (cur.left != null) {
            dfs(index + 1, cur.left, rightSideList);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: BFS, T(n) = O(n), S(n) = O(n)
// BFS traverse from right to left level by level
class Solution1 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightSideList = new ArrayList<>();
        // corner case
        if (root == null) {
            return rightSideList;
        }
        bfs(root, rightSideList);
        return rightSideList;
    }

    private void bfs (TreeNode root, List<Integer> rightSideList) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isRightBound = true; // traverse from right children to left children
            while (size -- > 0) {
                TreeNode cur = queue.poll();
                if (isRightBound) {
                    rightSideList.add(cur.val);
                    isRightBound = false;
                }
                if (cur.right != null) {
                    TreeNode right = cur.right;
                    queue.offer(right);
                }
                if (cur.left != null) {
                    TreeNode left = cur.left;
                    queue.offer(left);
                }
            }
        }
    }
}

// Solution 2: DFS, T(n) = O(n), S(n) = O(n)
/*
traverse the whole tree as the following patterns: root -> right -> left
and 1 list to record the result rightSideList
step 1: traverse the whole tree
step 2: in the traversal, every time there is the depth > rightSideList.size,
means new node is in result
 */
class Solution2 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightSideList = new ArrayList<>();
        // corner case
        if (root == null) {
            return rightSideList;
        }
        dfs(0, root, rightSideList);
        return rightSideList;
    }

    // pre-order traverse the tree: right, cur, left
    private void dfs (int index, TreeNode cur, List<Integer> rightSideList) {
        // corner case
        if (cur == null) {
            return;
        }
        
        // general case
        if (index == rightSideList.size()) {
            rightSideList.add(cur.val);
        }
        if (cur.right != null) {
            dfs(index + 1, cur.right, rightSideList);
        }
        if (cur.left != null) {
            dfs(index + 1, cur.left, rightSideList);
        }
    }
}

}