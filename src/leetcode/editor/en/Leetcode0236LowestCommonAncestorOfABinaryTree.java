//Given a binary tree, find the lowest common ancestor (LCA) of two given nodes 
//in the tree. 
//
// According to the definition of LCA on Wikipedia: “The lowest common ancestor 
//is defined between two nodes p and q as the lowest node in T that has both p and
// q as descendants (where we allow a node to be a descendant of itself).” 
//
// Given the following binary tree: root = [3,5,1,6,2,0,8,null,null,7,4] 
//
// 
//
// Example 1: 
//
// 
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//Output: 3
//Explanation: The LCA of nodes 5 and 1 is 3.
// 
//
// Example 2: 
//
// 
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//Output: 5
//Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant o
//f itself according to the LCA definition.
// 
//
// 
//
// Note: 
//
// 
// All of the nodes' values will be unique. 
// p and q are different and both values will exist in the binary tree. 
// 
// Related Topics Tree 
// 👍 3811 👎 173

package leetcode.editor.en;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import leetcode.editor.TreeDrawer;
import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-07-26 13:02:11
// Jesse Yang
public class Leetcode0236LowestCommonAncestorOfABinaryTree{
    // Java: lowest-common-ancestor-of-a-binary-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode0236LowestCommonAncestorOfABinaryTree().new Solution();
        // TO TEST
        String serializedTreeNode = "3,5,1,6,2,0,8,null,null,7,4";
        TreeNode root = TreeGenerator.deserialize(serializedTreeNode);
        TreeDrawer.draw(root);
        TreeNode p = root.left.left;
        TreeNode q = root.right.right;
        System.out.printf("find %s %s \n", p.val, q.val);
        TreeNode LCA = sol.lowestCommonAncestor(root, p, q);
        System.out.println(LCA.val);
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
 * recursion, T(n) = O(n), S(n) = O(n)
 * 数学归纳法：
 * base case:
 *      如果遇到p或者q的话，向上return p或者q
 *      如果走到底，还没有遇到p或者q的话，return null
 * general case:
 *      两边都是null的话，return null；
 *      遇到p，return p；
 *      遇到q，return q；
 *      遇到p，q， return 自己
 */

class Solution {
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case, failure
        if (root == null) {
            return null;
        }
        // base case, success
        if(root == p || root == q) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left != null && right != null) {
            return root;
        }
        /*
         if (left != null) {
         return left;
         }
         if (right != null) {
         return right;
         }
         return null;
         */
        // 上面的语句可以合并成如下
        return left == null ? right : left;
        
    }
    
}


//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: recursion, T(n) = O(n), S(n) = O(n)
// 4 ms,击败了100.00% 的Java用户，41.7 MB,击败了10.70% 的Java用户
/**
 * recursion
 * 数学归纳法：
 * base case:
 *      如果遇到p或者q的话，向上return p或者q
 *      如果走到底，还没有遇到p或者q的话，return null
 * general case:
 *      两边都是null的话，return null；
 *      遇到p，return p；
 *      遇到q，return q；
 *      遇到p，q， return 自己
 */
class Solution1 {
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case, failure
        if (root == null) {
            return null;
        }
        // base case, success
        if(root == p || root == q) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left != null && right != null) {
            return root;
        }
        /*
         if (left != null) {
         return left;
         }
         if (right != null) {
         return right;
         }
         return null;
         */
        // 上面的语句可以合并成如下
        return left == null ? right : left;
        
    }
}

// Solution 2: 用Stack做post order遍历 T(n) = O(n), S(n) = O(n)
// 7 ms,击败了24.17% 的Java用户, 40 MB,击败了93.73% 的Java用户
/**
 * 用 stack post-order遍历，模拟solution 1 中 recursion的过程
 * 遇到出现的p或者q的时候，标记一下，
 * 后面在遇到p和q中的另外一个的时候，return栈顶
 */
class Solution2 {
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        // 往左下角走到最下面
        // TreeNode firstFound = root; // the first found between p and q
        TreeNode res = root; // potential LCA
        boolean findPOrQ = false;
        
        // 找到p和q中的一个，或者都到null的时候就停止
        while (cur != null) {
            if (cur == p || cur == q) {
                stack.push(cur);
                // firstFound = cur;
                res = cur;
                findPOrQ = true;
                break;
            }
            stack.push(cur);
            if (cur.left != null) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        
        while (!stack.isEmpty()) {
            cur = stack.pop();
            
            // 如果res这个点是P or Q, res的 recursive parent有可能是LCA
            if (!stack.isEmpty() && res == cur) { // 如果当前
                res = stack.peek();
            }
            if (!stack.isEmpty() && cur != stack.peek().right) {
                TreeNode top = stack.peek();
                cur = top.right;
                while (cur != null) { // 一直往下走,优先走左边
                    stack.push(cur);
                    if (cur == p || cur == q) {
                        // p 和 q都已经找到了
                        if (findPOrQ) {
                            return res;
                        }
                        // firstFound = cur;
                        res = cur;
                        findPOrQ = true;
                        break;
                    } else {
                        if (cur.left != null) {
                            cur = cur.left;
                        } else {
                            cur = cur.right;
                        }
                    }
                }
            }
        }
        return res;
    }
    
}

// Solution 3:建图，题目就变成了 Leetcode 160， T(n) = O(n), S(n) = O(n)
// 6 ms,击败了26.78% 的Java用户, 40.2 MB,击败了92.30% 的Java用户
/**
 * 把Tree变成Graph，建图，用HashMap 存(key: child,parent:  parent), 题目就变成了Leetcode 160
 */
class Solution3 {
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case, failure
        if (root == null) {
            return null;
        }
        if (p == null || q == null) {
            return p == null ? q : p;
        }
        Map<TreeNode, TreeNode> childToParent = new HashMap<>();
        buildGraph(root, null, childToParent);
        
        return LCA(p, q, childToParent);
    }
    
    private void buildGraph(TreeNode root, TreeNode parent, Map<TreeNode, TreeNode> childToParent) {
        // base case
        if (root == null) {
            return;
        }
        childToParent.put(root, parent);
        buildGraph(root.left, root, childToParent);
        buildGraph(root.right, root, childToParent);
    }
    
    private TreeNode LCA(TreeNode p, TreeNode q, Map<TreeNode, TreeNode> map) {
        // corner case
        if (!map.containsKey(p) && ! map.containsKey(q)) {
            return null;
        }
        
        Set<TreeNode> visited = new HashSet<>();
        /*
        p和q各走一步，找到交叉点的方法
         */
        while (map.containsKey(p) || map.containsKey(q)) {
            if (!visited.contains(p)) {
                if (p != null) {
                    visited.add(p);
                    p = map.get(p);
                }
            } else {
                return p;
            }
            
            if (!visited.contains(q)) {
                if (q != null) {
                    visited.add(q);
                    q = map.get(q);
                }
            } else {
                return q;
            }
        }
        return null;
        /*
        先让p走完，再走q，找intersection的方法
         */
        /*while (p != null) {
            visited.add(p);
            p = map.get(p);
        }

        while (q != null) {
            if (visited.contains(q)) {
                return q;
            }
            visited.add(q);
            q = map.get(q);
        }
        return null;*/
    }
    
}

}