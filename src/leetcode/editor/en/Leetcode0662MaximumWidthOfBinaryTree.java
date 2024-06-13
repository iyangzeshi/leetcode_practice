//Given a binary tree, write a function to get the maximum width of the given tr
//ee. The maximum width of a tree is the maximum width among all levels. 
//
// The width of one level is defined as the length between the end-nodes (the le
//ftmost and right most non-null nodes in the level, where the null nodes between 
//the end-nodes are also counted into the length calculation. 
//
// It is guaranteed that the answer will in the range of 32-bit signed integer. 
//
//
// Example 1: 
//
// 
//Input: 
//
//           1
//         /   \
//        3     2
//       / \     \  
//      5   3     9 
//
//Output: 4
//Explanation: The maximum width existing in the third level with the length 4 (
//5,3,null,9).
// 
//
// Example 2: 
//
// 
//Input: 
//
//          1
//         /  
//        3    
//       / \       
//      5   3     
//
//Output: 2
//Explanation: The maximum width existing in the third level with the length 2 (
//5,3).
// 
//
// Example 3: 
//
// 
//Input: 
//
//          1
//         / \
//        3   2 
//       /        
//      5      
//
//Output: 2
//Explanation: The maximum width existing in the second level with the length 2 
//(3,2).
// 
//
// Example 4: 
//
// 
//Input: 
//
//          1
//         / \
//        3   2
//       /     \  
//      5       9 
//     /         \
//    6           7
//Output: 8
//Explanation:The maximum width existing in the fourth level with the length 8 (
//6,null,null,null,null,null,null,7).
// 
//
// 
// Constraints: 
//
// 
// The given binary tree will have between 1 and 3000 nodes. 
// 
// Related Topics Tree 
// 👍 1898 👎 353

package leetcode.editor.en;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import javafx.util.Pair;
import leetcode.editor.TreeNode;

// 2020-12-02 16:02:02
// Jesse Yang
public class Leetcode0662MaximumWidthOfBinaryTree{
    // Java: maximum-width-of-binary-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0662MaximumWidthOfBinaryTree().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
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
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
class Solution {

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
    
        // queue of elements [(node, col_index)]
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        int maxWidth = 0;
    
        queue.offer(new Pair<>(root, 0));
    
        while (queue.size() > 0) {
            Pair<TreeNode, Integer> head = queue.peek();
            int size = queue.size();
            Pair<TreeNode, Integer> pair = null;
        
            while (size-- > 0) {
                pair = queue.poll();
                TreeNode node = pair.getKey();
                if (node.left != null) {
                    queue.add(new Pair<>(node.left, 2 * pair.getValue() - 1)); // 这里很重要
                }
                if (node.right != null) {
                    queue.add(new Pair<>(node.right, 2 * pair.getValue())); // 这里很重要
                }
            }
        
            // calculate the length of the current level,
            maxWidth = Math.max(maxWidth, pair.getValue() - head.getValue() + 1);
        }
    
        return maxWidth;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: BFS, T(n) = O(n), S(n) = O(n)
// 1 ms,击败了87.75% 的Java用户, 38 MB,击败了99.28% 的Java用户
class Solution1 {
    
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        // queue of elements [(node, col_index)]
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        int maxWidth = 0;
        
        queue.offer(new Pair<>(root, 0));
        
        while (queue.size() > 0) {
            Pair<TreeNode, Integer> head = queue.peek();
            int size = queue.size();
            Pair<TreeNode, Integer> pair = null;
            
            while (size-- > 0) {
                pair = queue.poll();
                TreeNode node = pair.getKey();
                if (node.left != null) {
                    queue.add(new Pair<>(node.left, 2 * pair.getValue() - 1)); // 这里很重要
                }
                if (node.right != null) {
                    queue.add(new Pair<>(node.right, 2 * pair.getValue())); // 这里很重要
                }
            }
            
            // calculate the length of the current level,
            maxWidth = Math.max(maxWidth, pair.getValue() - head.getValue() + 1);
        }
        
        return maxWidth;
    }
}

// Solution 2: DFS,
// 1 ms,击败了87.75% 的Java用户, 38 MB,击败了99.28% 的Java用户
class Solution2 {
    
    private int maxWidth = 0;
    private HashMap<Integer, Integer> firstColIndexTable;
    
    public int widthOfBinaryTree(TreeNode root) {
        // table contains the first col_index for each level
        firstColIndexTable = new HashMap<>();
        
        // start from depth = 0, and colIndex = 0
        DFS(root, 0, 0);
        
        return maxWidth;
    }
    
    protected void DFS(TreeNode node, int depth, int colIndex) {
        if (node == null) {
            return;
        }
        // initialize the value, for the first seen colIndex per level
        if (!firstColIndexTable.containsKey(depth)) {
            firstColIndexTable.put(depth, colIndex);
        }
        int firstColIndex = firstColIndexTable.get(depth);
        
        maxWidth = Math.max(maxWidth, colIndex - firstColIndex + 1);
        
        // Preorder DFS. Note: it is important to put the priority on the left child
        DFS(node.left, depth + 1, 2 * colIndex - 1); // 这里
        DFS(node.right, depth + 1, 2 * colIndex);
    }
}

}