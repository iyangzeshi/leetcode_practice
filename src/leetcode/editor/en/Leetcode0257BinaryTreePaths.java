//Given a binary tree, return all root-to-leaf paths. 
//
// Note: A leaf is a node with no children. 
//
// Example: 
//
// 
//Input:
//
//   1
// /   \
//2     3
// \
//  5
//
//Output: ["1->2->5", "1->3"]
//
//Explanation: All root-to-leaf paths are: 1->2->5, 1->3
// Related Topics Tree Depth-first Search 
// 👍 2055 👎 117

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-10-19 17:10:21
// Zeshi Yang
public class Leetcode0257BinaryTreePaths{
    // Java: binary-tree-paths
    public static void main(String[] args) {
        Solution sol = new Leetcode0257BinaryTreePaths().new Solution();
        // TO TEST
        String str = "1,2,3,null,5";
        TreeNode root = TreeGenerator.deserialize(str);
        List<String> res = sol.binaryTreePaths(root);
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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        // corner case
        if (root == null) {
            return res;
        }
        StringBuilder path = new StringBuilder();
        // path.append(root.val);
        dfs(root, path, res);
        return res;
    }
    
    private void dfs(TreeNode cur, StringBuilder path, List<String> res) {
        path.append(cur.val);
        // base case
        if (cur.left == null && cur.right == null) {
            // path.setLength(path.length() - 2);
            res.add(path.toString());
            return;
        }
        path.append("->");
        int len = path.length();
        if (cur.left != null) {
            dfs(cur.left, path, res);
            path.setLength(len);
        }
        if (cur.right != null) {
            dfs(cur.right, path, res);
            // path.setLength(len);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}