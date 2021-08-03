//Given a complete binary tree, count the number of nodes. 
//
// Note: 
//
// Definition of a complete binary tree from Wikipedia: 
//In a complete binary tree every level, except possibly the last, is completely
// filled, and all nodes in the last level are as far left as possible. It can hav
//e between 1 and 2h nodes inclusive at the last level h. 
//
// Example: 
//
// 
//Input: 
//    1
//   / \
//  2   3
// / \  /
//4  5 6
//
//Output: 6 
// Related Topics Binary Search Tree 
// 👍 2128 👎 202

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;
import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-07-09 18:07:24
public class Leetcode0222CountCompleteTreeNodes{
    // Java: count-complete-tree-nodes
    public static void main(String[] args) {
        Solution sol = new Leetcode0222CountCompleteTreeNodes().new Solution();
        // TO TEST
        String str = "1,2,3,4,5,6";
        TreeNode node = TreeGenerator.deserialize(str);
        int res = sol.countNodes(node);
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

    private Map<TreeNode, Integer> map = new HashMap<>();

    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        int lHeight = getHeight(root.left);
        int rHeight = getHeight(root.right);

        int count = 1;
        if (lHeight > rHeight) { // right subtree is a full tree
            count += (1 << rHeight) - 1;
            return count + countNodes(root.left);
        } else if (lHeight == rHeight) { // left subtree is a full tree
            count += (1 << lHeight) - 1;
            // count += Math.pow(2, lHeight) - 1; // 这句话和上面一句话等价
            return count + countNodes(root.right);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private int getHeight(TreeNode root) {
        // base case
        if (root == null) {
            return 0;
        }
        // lookup the form
        if (map.containsKey(root)) {
            return map.get(root);
        }

        int height = getHeight(root.left) + 1;
        // fill in the form
        map.put(root, height);
        return height;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int countLeft = countNodes(root.left);
        int countRight = countNodes(root.right);

        return countLeft + countRight + 1;
    }
}
class Solution2_1 {
    // Solution 2_1: 左右子数中，一定有一个是full tree，然后只要算另外一个就可以了，算出来了，数目在求和
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;

        int lHeight = getHeight(root.left);
        int rHeight = getHeight(root.right);

        int count = 1;
        if (lHeight > rHeight) { // right subtree is a full tree
            count += (1 << rHeight) - 1;
            return count + countNodes(root.left);
        } else if (lHeight == rHeight) { // left subtree is a full tree
            count += (1 << lHeight) - 1;
            return count + countNodes(root.right);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private int getHeight(TreeNode root) {
        int height = 0;
        while (root != null) {
            height++;
            root = root.left;
        }
        return height;
    }
}
class Solution2_2 {
    // Solution 2: 在solution 2_1的基础上优化，那些TreeNode的高度，避免重复运算
    private Map<TreeNode, Integer> map = new HashMap<>();

    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        int lHeight = getHeight(root.left);
        int rHeight = getHeight(root.right);

        int count = 1;
        if (lHeight > rHeight) { // right subtree is a full tree
            count += (1 << rHeight) - 1;
            return count + countNodes(root.left);
        } else if (lHeight == rHeight) { // left subtree is a full tree
            count += (1 << lHeight) - 1;
            return count + countNodes(root.right);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private int getHeight(TreeNode root) {
        // base case
        if (root == null) {
            return 0;
        }
        // lookup the form
        if (map.containsKey(root)) {
            return map.get(root);
        }

        int height = getHeight(root.left) + 1;
        // fill in the form
        map.put(root, height);
        return height;
    }
}
}