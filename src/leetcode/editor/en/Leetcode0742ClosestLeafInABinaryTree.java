//Given a binary tree where every node has a unique value, and a target key k, f
//ind the value of the nearest leaf node to target k in the tree.
// 
//Here, nearest to a leaf means the least number of edges travelled on the binar
//y tree to reach any leaf of the tree. Also, a node is called a leaf if it has no
// children.
// 
//In the following examples, the input tree is represented in flattened form row
// by row.
//The actual root tree given will be a TreeNode object.
// 
//Example 1:
// 
//Input:
//root = [1, 3, 2], k = 1
//Diagram of binary tree:
//          1
//         / \
//        3   2
//
//Output: 2 (or 3)
//
//Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.
// 
// 
//Example 2:
// 
//Input:
//root = [1], k = 1
//Output: 1
//
//Explanation: The nearest leaf node is the root node itself.
// 
// 
//
// 
//Example 3:
// 
//Input:
//root = [1,2,3,4,null,null,null,5,null,6], k = 2
//Diagram of binary tree:
//             1
//            / \
//           2   3
//          /
//         4
//        /
//       5
//      /
//     6
//
//Output: 3
//Explanation: The leaf node with value 3 (and not the leaf node with value 6) i
//s nearest to the node with value 2.
// 
// 
//
// Note: 
// 
// root represents a binary tree with at least 1 node and at most 1000 nodes. 
// Every node has a unique node.val in range [1, 1000]. 
// There exists some node in the given binary tree for which node.val == k. 
// 
// Related Topics Tree 
// 👍 519 👎 102

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import leetcode.editor.TreeDrawer;
import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-11-15 13:45:53
// Jesse Yang
public class Leetcode0742ClosestLeafInABinaryTree{
    // Java: closest-leaf-in-a-binary-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0742ClosestLeafInABinaryTree().new Solution();
        // TO TEST
        String data = "1,2,3,null,null,4,5,6,null,null,7,8,9,10";
        int k = 3;
        TreeNode root = TreeGenerator.deserialize(data);
        TreeDrawer.draw(root);
        System.out.println("target: " + k);
        int res = sol.findClosestLeaf(root, k);
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
    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        TreeNode[] target = new TreeNode[1];
        getParentTilTarget(root, k, parentMap, target);
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target[0]);
        queue.offer(target[0]);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size -- > 0) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) {
                    return cur.val;
                }
                List<TreeNode> neighbors = new ArrayList<>();
                if (cur.left != null && !visited.contains(cur.left)) {
                    neighbors.add(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right)) {
                    neighbors.add(cur.right);
                }
                TreeNode parent = parentMap.get(cur);
                if (parent != null && !visited.contains(parent)) {
                    neighbors.add(parent);
                }
                visited.addAll(neighbors);
                for (TreeNode neighbor: neighbors) {
                    queue.offer(neighbor);
                }
            }
        }
        throw new ArithmeticException("can not happen");
    }
    
    // DFS
    private boolean getParentTilTarget(TreeNode root, int k, Map<TreeNode, TreeNode> parentMap,
            TreeNode[] target) {
        if (root.val == k) {
            target[0] = root;
            return true;
        }
        if (root.left != null) {
            TreeNode left = root.left;
            parentMap.put(left, root);
            if (getParentTilTarget(left, k, parentMap, target)) {
                return true;
            }
        }
        if (root.right != null) {
            TreeNode right = root.right;
            parentMap.put(right, root);
            return getParentTilTarget(right, k, parentMap, target);
        }
        return false;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: T(n) = O(n), 2ms
/*
 先从root开始找，把target的所有ancestor的距离算出来，存在hashMap里面，
 然后用dfs + backtracking遍历每个leaf，更新最短距离的leaf值
 */
class Solution1 {
    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, Integer> nodeDistance = new HashMap<>();
        setDisFromParent(root, k, nodeDistance);
        TreeNode[] minDisNode = new TreeNode[1];
        dfs(root, 0, k, nodeDistance, minDisNode);
        return minDisNode[0].val;
    }
    
    private Integer setDisFromParent(TreeNode root, int k, Map<TreeNode, Integer> nodeDistance) {
        // base case
        if (root == null) {
            return null;
        }
        if (root.val == k) {
            nodeDistance.put(root, 0);
            return 0;
        }
        Integer left = setDisFromParent(root.left, k, nodeDistance);
        Integer distance = left != null ? left : setDisFromParent(root.right, k, nodeDistance);
        /*if (left != null) {
            distance = left;
        } else {
            Integer right = setDisFromParent(root.right, k, nodeDistance);
            distance = right;
        }*/
        
        if (distance != null) {
            distance++;
            nodeDistance.put(root, distance);
        }
        return distance;
    }
    
    private void dfs(TreeNode root, int dis, int k, Map<TreeNode, Integer> nodeDistance,
            TreeNode[] minDisNode) {
        // base case
        if (root == null) {
            return;
        }
        if (nodeDistance.get(root) != null) {
            dis = nodeDistance.get(root);
        }
        if (root.left == null && root.right == null) {
            nodeDistance.put(root, dis);
            Integer minDis = nodeDistance.get(minDisNode[0]);
            if (minDis == null) {
                minDisNode[0] = root;
            } else if (minDis > dis) {
                minDisNode[0] = root;
            }
            return;
        }
        
        Integer minDis = nodeDistance.get(minDisNode[0]);
        if (minDis != null && minDis <= dis) { // backtracking, 回溯
            nodeDistance.put(root, dis);
            return;
        }
        dfs(root.left, dis + 1, k, nodeDistance, minDisNode);
        dfs(root.right, dis + 1, k, nodeDistance, minDisNode);
    }
    
}

// Solution 2:T(n) = O(n), 3ms, 面试用这个方法，快，不容易写错
/*
convert tree to graph(just store necessary TreeNode's parent, then do bfs from target as beginning
 */
class Solution2 {
    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        TreeNode[] target = new TreeNode[1];
        getParentTilTarget(root, k, parentMap, target);
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target[0]);
        queue.offer(target[0]);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size -- > 0) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) {
                    return cur.val;
                }
                List<TreeNode> neighbors = new ArrayList<>();
                if (cur.left != null && !visited.contains(cur.left)) {
                    neighbors.add(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right)) {
                    neighbors.add(cur.right);
                }
                TreeNode parent = parentMap.get(cur);
                if (parent != null && !visited.contains(parent)) {
                    neighbors.add(parent);
                }
                visited.addAll(neighbors);
                for (TreeNode neighbor: neighbors) {
                    queue.offer(neighbor);
                }
            }
        }
        throw new ArithmeticException("can not happen");
    }
    
    // DFS
    private boolean getParentTilTarget(TreeNode root, int k, Map<TreeNode, TreeNode> parentMap,
            TreeNode[] target) {
        if (root.val == k) {
            target[0] = root;
            return true;
        }
        if (root.left != null) {
            TreeNode left = root.left;
            parentMap.put(left, root);
            if (getParentTilTarget(left, k, parentMap, target)) {
                return true;
            }
        }
        if (root.right != null) {
            TreeNode right = root.right;
            parentMap.put(right, root);
            return getParentTilTarget(right, k, parentMap, target);
        }
        return false;
    }
    
}
}