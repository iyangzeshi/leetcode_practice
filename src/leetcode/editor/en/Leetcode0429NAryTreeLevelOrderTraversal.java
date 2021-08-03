//Given an n-ary tree, return the level order traversal of its nodes' values. 
//
// Nary-Tree input serialization is represented in their level order traversal, 
//each group of children is separated by the null value (See examples). 
//
// 
// Example 1: 
//
// 
//
// 
//Input: root = [1,null,3,2,4,null,5,6]
//Output: [[1],[3,2,4],[5,6]]
// 
//
// Example 2: 
//
// 
//
// 
//Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null
//,12,null,13,null,null,14]
//Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
// 
//
// 
// Constraints: 
//
// 
// The height of the n-ary tree is less than or equal to 1000 
// The total number of nodes is between [0, 104] 
// 
// Related Topics Tree Breadth-first Search 
// 👍 751 👎 52

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
// 2020-12-02 16:32:32
// Zeshi Yang
public class Leetcode0429NAryTreeLevelOrderTraversal{
    // Java: n-ary-tree-level-order-traversal
    public static void main(String[] args) {
        Solution sol = new Leetcode0429NAryTreeLevelOrderTraversal().new Solution();
        // TO TEST
        
        System.out.println();
    }
    
    private class Node {
        public int val;
        public List<Node> children;
        
        public Node() {}
        
        public Node(int _val) {
            val = _val;
        }
        
        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
    
    class Solution {
        
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                List<Integer> level = new ArrayList<>();
                int size = queue.size();
                while (size-- > 0) {
                    Node node = queue.poll();
                    level.add(node.val);
                    queue.addAll(node.children);
                }
                result.add(level);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}