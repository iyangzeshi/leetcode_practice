//We are given a binary tree (with root node root), a target node, and an intege
//r value K. 
//
// Return a list of the values of all nodes that have a distance K from the targ
//et node. The answer can be returned in any order. 
//
// 
//
// 
// 
//
// 
// Example 1: 
//
// 
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
//
//Output: [7,4,1]
//
//Explanation: 
//The nodes that are a distance 2 from the target node (with value 5)
//have values 7, 4, and 1.
//
//
//
//Note that the inputs "root" and "target" are actually TreeNodes.
//The descriptions of the inputs above are just serializations of these objects.
//
// 
//
// 
//
// Note: 
//
// 
// The given tree is non-empty. 
// Each node in the tree has unique values 0 <= node.val <= 500. 
// The target node is a node in the tree. 
// 0 <= K <= 1000. 
// 
// 
// Related Topics Tree Depth-first Search Breadth-first Search 
// 👍 2800 👎 56

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

// 2020-11-24 21:36:57
// Jesse Yang
public class Leetcode0863AllNodesDistanceKInBinaryTree{
    // Java: all-nodes-distance-k-in-binary-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0863AllNodesDistanceKInBinaryTree().new Solution();
        // TO TEST
        TreeNode root = TreeGenerator.deserialize("0,6,1,null,null,null,2,7,3,null,8,4,9,null,"
                + "null,null,5");
        TreeDrawer.draw(root);
        TreeNode target = root.right.right.left;
        int K = 5;
        List<Integer> res =sol.distanceK(root, target, K);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)

// Definition for a binary tree node.
// public class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;
//     TreeNode(int x) { val = x; }
// }

/**
 * 把Tree变成Graph，然后bfs，T(n) = O(n)
 */
class Solution {
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (root == null || target == null || K < 0) {
            return res;
        }
        if (K == 0) {
            res.add(target.val);
            return res;
        }
        // build graph
        HashMap<TreeNode, TreeNode> childToParent = new HashMap<>();
        buildGraph(null, root, childToParent);
        // BFS
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);
        int minLen = 0;
        while (!queue.isEmpty()) {
            if (minLen == K) {
                res = queueToIntegerList(queue);
                break;
            }
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (childToParent.containsKey(cur)) {
                    TreeNode parent = childToParent.get(cur);
                    if (parent != null && !visited.contains(parent)) {
                        queue.offer(parent);
                        visited.add(parent);
                    }
                }
                if (cur.left != null && !visited.contains(cur.left)) {
                    queue.offer(cur.left);
                    visited.add(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right)) {
                    queue.offer(cur.right);
                    visited.add(cur.right);
                }
            }
            minLen++;
            
        }
        return res;
    }
    
    private List<Integer> queueToIntegerList(Queue<TreeNode> queue) {
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            res.add(queue.poll().val);
        }
        return res;
    }
    
    private void buildGraph(TreeNode parent, TreeNode root, Map<TreeNode, TreeNode> childToParent) {
        if (root == null) {
            return;
        }
        if (parent != null) {
            childToParent.put(root, parent);
        }
        buildGraph(root, root.left, childToParent);
        buildGraph(root, root.right, childToParent);
        
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}