//Given a non-empty special binary tree consisting of nodes with the non-negativ
//e value, where each node in this tree has exactly two or zero sub-node. If the n
//ode has two sub-nodes, then this node's value is the smaller value among its two
// sub-nodes. More formally, the property root.val = min(root.left.val, root.right
//.val) always holds. 
//
// Given such a binary tree, you need to output the second minimum value in the 
//set made of all the nodes' value in the whole tree. 
//
// If no such second minimum value exists, output -1 instead. 
//
// 
//
// 
// Example 1: 
//
// 
//Input: root = [2,2,5,null,null,5,7]
//Output: 5
//Explanation: The smallest value is 2, the second smallest value is 5.
// 
//
// Example 2: 
//
// 
//Input: root = [2,2,2]
//Output: -1
//Explanation: The smallest value is 2, but there isn't any second smallest valu
//e.
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [1, 25]. 
// 1 <= Node.val <= 231 - 1 
// root.val == min(root.left.val, root.right.val) for each internal node of the 
//tree. 
// 
// Related Topics Tree 
// 👍 820 👎 1080

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;
import leetcode.editor.TreeNode;

// 2021-05-01 16:17:40
// Jesse Yang
public class Leetcode0671SecondMinimumNodeInABinaryTree{
    // Java: second-minimum-node-in-a-binary-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0671SecondMinimumNodeInABinaryTree().new Solution();
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
    
    public int findSecondMinimumValue(TreeNode root) {
        int min1 = root.val; // 1st smallest one
        int min2 = min1 - 1; // 2nd smallest one
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val != min1) {
                if (min2 == min1 - 1) {
                    min2 = node.val;
                } else {
                    min2 = Math.min(min2, node.val);
                }
            }
            if (node.left != null) {
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return min2 == min1 - 1 ? -1 : min2;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1_1 BFS, 遍历整个tree，找到比root的值要到的最小值
// T(n) = O(n), S(n) = O(h)
// 0 ms,击败了100.00% 的Java用户, 36.6 MB,击败了7.05% 的Java用户
class Solution1_1 {
    
    public int findSecondMinimumValue(TreeNode root) {
        int min1 = root.val; // 1st smallest one
        int min2 = min1 - 1; // 2nd smallest one
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val != min1) {
                if (min2 == min1 - 1) {
                    min2 = node.val;
                } else {
                    min2 = Math.min(min2, node.val);
                }
            }
            if (node.left != null) {
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return min2 == min1 - 1 ? -1 : min2;
    }
    
}


// Solution 1_2 BFS，遍历tree的时候，只遍历parent的值是root.val的部分
// T(n) = O(n), S(n) = O(h)
// 0 ms,击败了100.00% 的Java用户, 36.6 MB,击败了7.05% 的Java用户
/*
每个node的值，都是以自己为root的subtree的最小值
所以只要遍历parent的值是root.val的那部分树的node就行了
 */
class Solution1_2 {
    
    public int findSecondMinimumValue(TreeNode root) {
        int min1 = root.val; // 1st smallest one
        int min2 = min1 - 1; // 2nd smallest one
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val == min1) {
                if (node.left != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            } else {
                if (min2 == min1 - 1) {
                    min2 = node.val;
                } else {
                    min2 = Math.min(min2, node.val);
                }
            }
        }
        return min2 == min1 - 1 ? -1 : min2;
    }
    
}

// Solution 2_1: DFS遍历整个tree
// T(n) = O(n), S(n) = O(h)
// 0 ms,击败了100.00% 的Java用户, 36.6 MB,击败了7.05% 的Java用户
/*
遍历整个tree，把所有对值加到HashSet里面
再在HashSet里面找第2小的值
 */
class Solution2_1 {
    
    public int findSecondMinimumValue(TreeNode root) {
        int min1 = root.val;
        Integer[] min2 = {null}; // second min1
        dfs(root, min1, min2);
        return min2[0] == null ? -1 : min2[0];
    }
    
    private void dfs(TreeNode root, int min1, Integer[] min2) {
        // base case
        if (root == null) {
            return;
        }
        if (root.val != min1) {
            if (min2[0] == null) {
                min2[0] = root.val;
            } else {
                min2[0] = Math.min(min2[0], root.val);
            }
        }
        dfs(root.left, min1, min2);
        dfs(root.right, min1, min2);
    }
    
}

// Solution 2_2: DFS, 只recursion可能的部分
// T(n) = O(n), S(n) = O(h)
// 0 ms,击败了100.00% 的Java用户, 35.9 MB,击败了90.38% 的Java用户
/*
分析： 每一条路径，从下往上是一次递减或者不改变的
所以root的是最小的

只遍历parent的值是root.val的部分

对于每个TreeNode node而言，
node是以自己为本身的subtree的最小值
而且每个node，它的左子树和右子树，至少有一个child.val = node.val
step 1: pre order dfs这个tree
step 2: 如果child.val = node.val, repeat pre order dfs
        如果child.val != node.val， 这个值也有可能是第2小的值，更新第2小的值的min2，但不会对这个点做dfs了
 */
class Solution2_2 {
    
    public int findSecondMinimumValue(TreeNode root) {
        int min1 = root.val; // 1st smallest one
        Integer[] min2 = {null}; // 2nd smallest one
        dfs(root, min1, min2);
        return min2[0] == null ? -1 : min2[0];
    }
    
    private void dfs(TreeNode root, int min1, Integer[] min2) {
        // base case
        if (root == null) {
            return;
        }
        if (root.val > min1) {
            if (min2[0] == null) {
                min2[0] = root.val;
            } else {
                min2[0] = Math.min(min2[0], root.val);
            }
            return;
        }
        
        dfs(root.left, min1, min2);
        dfs(root.right, min1, min2);
    }
    
}


}