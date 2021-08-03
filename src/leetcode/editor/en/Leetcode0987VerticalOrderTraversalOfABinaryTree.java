//Given a binary tree, return the vertical order traversal of its nodes values. 
//
//
// For each node at position (X, Y), its left and right children respectively wi
//ll be at positions (X-1, Y-1) and (X+1, Y-1). 
//
// Running a vertical line from X = -infinity to X = +infinity, whenever the ver
//tical line touches some nodes, we report the values of the nodes in order from t
//op to bottom (decreasing Y coordinates). 
//
// If two nodes have the same position, then the value of the node that is repor
//ted first is the value that is smaller. 
//
// Return an list of non-empty reports in order of X coordinate. Every report wi
//ll have a list of values of nodes. 
//
// 
//
// Example 1: 
//
// 
//
// 
// 
//Input: [3,9,20,null,null,15,7]
//Output: [[9],[3,15],[20],[7]]
//Explanation: 
//Without loss of generality, we can assume the root node is at position (0, 0):
//
//Then, the node with value 9 occurs at position (-1, -1);
//The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
//The node with value 20 occurs at position (1, -1);
//The node with value 7 occurs at position (2, -2).
// 
//
// 
// Example 2: 
//
// 
//
// 
//Input: [1,2,3,4,5,6,7]
//Output: [[4],[2],[1,5,6],[3],[7]]
//Explanation: 
//The node with value 5 and the node with value 6 have the same position accordi
//ng to the given scheme.
//However, in the report "[1,5,6]", the node value of 5 comes first since 5 is s
//maller than 6.
// 
//
// 
// 
//
// Note: 
//
// 
// The tree will have between 1 and 1000 nodes. 
// Each node's value will be between 0 and 1000. 
// 
// 
//
// 
// 
// 
// Related Topics Hash Table Tree 
// 👍 1024 👎 2086

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import leetcode.editor.TreeNode;

// 2020-11-29 13:47:38
// Zeshi Yang
public class Leetcode0987VerticalOrderTraversalOfABinaryTree{
    // Java: vertical-order-traversal-of-a-binary-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0987VerticalOrderTraversalOfABinaryTree().new Solution();
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
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (root == null) return res;
        
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> colIndex = new LinkedList<>();
        
        queue.offer(root);
        colIndex.offer(0);
        int minCol = 0;
        int maxCol = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            HashMap<Integer, Integer> checkMap = new HashMap<>(); // <idx, amount>
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                int idx = colIndex.poll();
                map.putIfAbsent(idx, new LinkedList<>());
                checkMap.putIfAbsent(idx, 0);
                if (checkMap.get(idx) == 0) {
                    map.get(idx).add(cur.val);
                } else {
                    List<Integer> list = map.get(idx);
                    int len = list.size();
                    int count = checkMap.get(idx);
                    for (int i = count; i > 0; i--) {
                        if (list.get(list.size() - i) > cur.val) {
                            list.add(list.size() - i, cur.val);
                            break;
                        }
                    }
                    if (list.size() == len) {
                        list.add(cur.val);
                    }
                }
                checkMap.put(idx, checkMap.get(idx) + 1);
                minCol = Math.min(minCol, idx);
                maxCol = Math.max(maxCol, idx);
                if (cur.left != null) {
                    queue.offer(cur.left);
                    colIndex.offer(idx - 1);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                    colIndex.offer(idx + 1);
                }
            }
        }
        
        for (int i = minCol; i <= maxCol; i++) {
            res.add(map.get(i));
        }
        return res;
    }
    
}

//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: 把每个TreeNode包成另外一个class Location，然后无脑sort

/**
 * 里面存x和y的信息，实现comparable接口
 * 先按照x坐标排序，x一样的时候用y排序，x, y坐标再一样的时候用值排序
 */
class Solution1 {
    
    class Location implements Comparable<Location> {
        
        int x;
        int y;
        int val;
        
        Location(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
        
        @Override
        public int compareTo(Location that) {
            if (this.x != that.x) {
                return Integer.compare(this.x, that.x);
            } else if (this.y != that.y) {
                return Integer.compare(this.y, that.y);
            } else {
                return Integer.compare(this.val, that.val);
            }
        }
    }
    
    List<Location> locations;
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // Each location is a node's x position, y position, and value
        locations = new ArrayList<>();
        // To traverse tree
        dfs(root, 0, 0); // 0  0 是root的x和y坐标
        Collections.sort(locations);
        
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        
        int prev = locations.get(0).x;
        
        for (Location loc : locations) {
            // If the x value changed, it's part of a new report.
            if (loc.x != prev) {
                prev = loc.x;
                ans.add(new ArrayList<>());
            }
            ans.get(ans.size() - 1).add(loc.val);
        }
        
        return ans;
    }
    
    public void dfs(TreeNode node, int x, int y) {
        if (node != null) {
            locations.add(new Location(x, y, node.val));
            dfs(node.left, x - 1, y + 1);
            dfs(node.right, x + 1, y + 1);
        }
    }
}

// Solution 2:

/**
 * 设置一个HashMap<colIdx, List<Integer>>
 * BFS遍历，保证x坐标是递增的
 * 相同y的时候，按照值排序。在同一层的时候，设置一个HashMap<Integer, Integer>: <colIdx, amount>
 * 每次要加到List里面的时候，在相同(x, y)坐标里面进行insertion sort
 * 边遍历的时候，边排序，用HashMap
 */
class Solution2 {
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (root == null) {
            return res;
        }
        
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> colIndex = new LinkedList<>();
        
        queue.offer(root);
        colIndex.offer(0);
        int minCol = 0;
        int maxCol = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            HashMap<Integer, Integer> checkMap = new HashMap<>(); // <idx, amount>
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                int idx = colIndex.poll();
                map.putIfAbsent(idx, new LinkedList<>());
                checkMap.putIfAbsent(idx, 0);
                if (checkMap.get(idx) == 0) {
                    map.get(idx).add(cur.val);
                } else {
                    List<Integer> list = map.get(idx);
                    int len = list.size();
                    int count = checkMap.get(idx);
                    for (int i = count; i > 0; i--) {
                        if (list.get(list.size() - i) > cur.val) {
                            list.add(list.size() - i, cur.val);
                            break;
                        }
                    }
                    if (list.size() == len) {
                        list.add(cur.val);
                    }
                }
                checkMap.put(idx, checkMap.get(idx) + 1);
                minCol = Math.min(minCol, idx);
                maxCol = Math.max(maxCol, idx);
                if (cur.left != null) {
                    queue.offer(cur.left);
                    colIndex.offer(idx - 1);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                    colIndex.offer(idx + 1);
                }
            }
        }
        
        for (int i = minCol; i <= maxCol; i++) {
            res.add(map.get(i));
        }
        return res;
    }
    
}
}