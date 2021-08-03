//Given a binary tree, return the vertical order traversal of its nodes' values.
// (ie, from top to bottom, column by column). 
//
// If two nodes are in the same row and column, the order should be from left to
// right. 
//
// Examples 1: 
//
// 
//Input: [3,9,20,null,null,15,7]
//
//   3
//  /\
// /  \
// 9  20
//    /\
//   /  \
//  15   7 
//
//Output:
//
//[
//  [9],
//  [3,15],
//  [20],
//  [7]
//]
// 
//
// Examples 2: 
//
// 
//Input: [3,9,8,4,0,1,7]
//
//     3
//    /\
//   /  \
//   9   8
//  /\  /\
// /  \/  \
// 4  01   7 
//
//Output:
//
//[
//  [4],
//  [9],
//  [3,0,1],
//  [8],
//  [7]
//]
// 
//
// Examples 3: 
//
// 
//Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left c
//hild is 5)
//
//     3
//    /\
//   /  \
//   9   8
//  /\  /\
// /  \/  \
// 4  01   7
//    /\
//   /  \
//   5   2
//
//Output:
//
//[
//  [4],
//  [9,5],
//  [3,0,1],
//  [8,2],
//  [7]
//]
// Related Topics Depth-first Search Breadth-first Search 
// 👍 1049 👎 169

package leetcode.editor.en;

import java.util.*;
import leetcode.editor.TreeNode;

// 2020-07-26 12:00:56
// Zeshi Yang
public class Leetcode0314BinaryTreeVerticalOrderTraversal{
    // Java: binary-tree-vertical-order-traversal
    public static void main(String[] args) {
        Solution sol = new Leetcode0314BinaryTreeVerticalOrderTraversal().new Solution();
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
/*
思路，用queue来level order traverse； traverse中poll时候，
用HashMap<Integer, List<Integer>>把每个poll出来的element的列存到HashMap里面
 */
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        // corner case
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> numCol = new LinkedList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        int min = 0;
        int max = 0;

        queue.offer(root);
        numCol.offer(0);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int col = numCol.poll();

            // update map
            if (!map.containsKey(col)) {
                List<Integer> temp = new ArrayList<>();
                temp.add(node.val);
                map.put(col, temp);
            }
            else {
                map.get(col).add(node.val);
            }

            //update the queue
            if (node.left != null) {
                queue.add(node.left);
                numCol.add(col - 1);
                min = Math.min(min, col - 1);
            }
            if (node.right != null) {
                queue.add(node.right);
                numCol.add(col + 1);
                max = Math.max(max, col + 1);
            }

        }
        for (int i = min; i <= max; i++) {
            result.add(map.get(i));
        }
        return result;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}