//Given a binary tree, find its maximum depth. 
//
// The maximum depth is the number of nodes along the longest path from the root
// node down to the farthest leaf node. 
//
// Note: A leaf is a node with no children. 
//
// Example: 
//
// Given binary tree [3,9,20,null,null,15,7], 
//
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7 
//
// return its depth = 3. 
// Related Topics Tree Depth-first Search 
// 👍 2585 👎 75

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javafx.util.Pair;
import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-07-26 11:43:32
// Jesse Yang
public class Leetcode0104MaximumDepthOfBinaryTree{
    // Java: maximum-depth-of-binary-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0104MaximumDepthOfBinaryTree().new Solution();
        // TO TEST
        TreeNode root = TreeGenerator.deserialize("3,9,20,null,null,15,7");
        int res = sol.maxDepth(root);
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

/*
 Solution 1_2: post order DFS recursion using stack
 T(n) = O(n), S(n) = O(h), lg(n) <= h <= n
*/
class Solution {
    
    class Node {
        TreeNode treeNode;
        Node left;
        Node right;
        Integer height;
        
        public Node(TreeNode treeNode) {
            this.treeNode = treeNode;
        }
        
    }
    
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<Node> stack = new Stack<>();
        Node rootNode = new Node(root);
        stack.push(rootNode);
        
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            // corner case
            if (node.treeNode == null) {
                node.height = 0;
                continue;
            }
            
            if (node.left != null && node.left.height != null
                    && node.right != null && node.right.height != null) {
                node.height = Math.max(node.left.height, node.right.height) + 1;
                continue;
            }
            
            Node right = new Node(node.treeNode.right);
            node.right = right;
            stack.push(right);
            
            Node left = new Node(node.treeNode.left);
            node.left = left;
            stack.push(left);
        }
        
        return 0;
    }
}
    
//leetcode submit region end(Prohibit modification and deletion)

/*
 Solution 1_1: post order DFS recursion
 T(n) = O(n), S(n) = O(h), lg(n) <= h <= n
*/
class Solution1_1 {
    
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
    
}



/* Solution 2_1: DFS recursion pre-order traverse
T(n) = O(n), S(n) = O(h), lg(n) <= h <= n
use maxDepth to update the longest length from root to current node
 */
class Solution2_1 {
    
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] maxDepth = {1};
        dfs(root, 1, maxDepth);
        return maxDepth[0];
    }
    // depth: the length from root(inclusive) to current node(inclusive)
    private void dfs(TreeNode root, int depth, int[] maxDepth) {
        // corner case
        if (root == null) {
            return;
        }
        
        // general case
        maxDepth[0] = Math.max(depth, maxDepth[0]);
        dfs(root.left, depth + 1, maxDepth);
        dfs(root.right, depth + 1, maxDepth);
    }
}

/*
 Solution 2_3: pre-order traverse DFS : using while and stack，
 stack中存的是模拟堆栈的行为，current call完之后，删除掉，把左右子树的call存进去
 */
class Solution2_2{
    
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        /*
        Pair
            TreeNode: current node
            Integer: the depth from root to current node
         */
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root, 1));  // 初始节点，深度为 1
        
        int maxDepth = 0;
        
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.pop();
            TreeNode node = current.getKey();
            int depth = current.getValue();
            
            if (node != null) {
                maxDepth = Math.max(maxDepth, depth);
                
                // 注意：先压 right，再压 left，保证前序顺序：根 → 左 → 右
                if (node.right != null) {
                    stack.push(new Pair<>(node.right, depth + 1));
                }
                if (node.left != null) {
                    stack.push(new Pair<>(node.left, depth + 1));
                }
            }
        }
        
        return maxDepth;
    }
}
/*
Solution 2_3: pre-order traverse DFS : using while and stack，stack中存的是root到current的路径
T(n) = O(n), S(n) = O(h), lgn <= h <= n
post order traverse dfs by stack
stack是一条从root开始到下面的path，post order遍历就行了
preprocessing，先往下走（尽量沿着左边走）

当stack不为空的时候：
    然后每次pop一个点cur出来，令parent = stack.peek()
    如果cur是parent的left child，就走parent的右子树，尽量往下走，走到底
    如果cur是parent的right child，就说明这个parent的所有subtree走完了（下次就会把parent给pop出来了）
 */
class Solution2_3 {
    
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        int max = 1;
        // pre-processing
        while (cur != null) { // 一直往下走,优先走左边
            stack.push(cur);
            max = Math.max(max, stack.size());
            if (cur.left != null) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        
        while (!stack.isEmpty()) {
            cur = stack.pop();
            if (!stack.isEmpty() && cur != stack.peek().right) {
                cur = stack.peek().right;
                while (cur != null) { // 一直往下走,优先走左边
                    stack.push(cur);
                    if (cur.left != null) {
                        cur = cur.left;
                    } else {
                        cur = cur.right;
                    }
                }
                max = Math.max(max, stack.size());
            }
        }
        return max;
    }
    
}

// Solution 3: BFS, T(n) = O(n), S(n) = O(n)
// 1 ms,击败了14.50% 的Java用户, 38.8 MB,击败了85.92% 的Java用户
class Solution2 {
    
    public int maxDepth(TreeNode root) {
        //corner case
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int depth = 0;
        
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }
    
}
}