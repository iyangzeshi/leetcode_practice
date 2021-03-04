//Given a binary tree, collect a tree's nodes as if you were doing this: Collect
// and remove all leaves, repeat until the tree is empty. 
//
// 
//
// Example: 
//
// 
//Input: [1,2,3,4,5]
//  
//          1
//         / \
//        2   3
//       / \     
//      4   5    
//
//Output: [[4,5,3],[2],[1]]
// 
//
// 
//
// Explanation: 
//
// 1. Removing the leaves [4,5,3] would result in this tree: 
//
// 
//          1
//         / 
//        2          
// 
//
// 
//
// 2. Now removing the leaf [2] would result in this tree: 
//
// 
//          1          
// 
//
// 
//
// 3. Now removing the leaf [1] would result in the empty tree: 
//
// 
//          []         
// 
//[[3,5,4],[2],[1]], [[3,4,5],[2],[1]], etc, are also consider correct answers s
//ince per each level it doesn't matter the order on which elements are returned. 
//Related Topics Tree Depth-first Search 
// 👍 1269 👎 19

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import leetcode.editor.TreeNode;

// 2021-03-04 12:50:51
// Zeshi Yang
public class Leetcode0366FindLeavesOfBinaryTree{
    // Java: find-leaves-of-binary-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0366FindLeavesOfBinaryTree().new Solution();
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
// T(n) = O(n), S(n) = O(n)
// 0 ms,击败了100.00% 的Java用户, 37.4 MB,击败了74.62% 的Java用户
class Solution {
    
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }
    
    private int dfs(TreeNode node, List<List<Integer>> res) {
        if (node == null) {
            return -1;
        }
        // 取左右subtree的 max作为current的 h
        int leftHeight = dfs(node.left, res);
        int rightHeight = dfs(node.right, res);
        int height = 1 + Math.max(leftHeight, rightHeight);
        /*
         因为每一层都要有一个list，所以当不够就新增
         create a new list to store this level node’s val
        */
        if (res.size() < height + 1) {
            res.add(new ArrayList<>());
        }
        // 然后因为 java pass reference 所以可以直接这样取对应的list 新增
        // the list of this level
        res.get(height).add(node.val);
        return height;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}