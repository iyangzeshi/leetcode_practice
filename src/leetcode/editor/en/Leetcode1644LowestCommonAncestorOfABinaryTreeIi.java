//Given the root of a binary tree, return the lowest common ancestor (LCA) of tw
//o given nodes, p and q. If either node p or q does not exist in the tree, return
// null. All values of the nodes in the tree are unique. 
//
// According to the definition of LCA on Wikipedia: "The lowest common ancestor 
//of two nodes p and q in a binary tree T is the lowest node that has both p and q
// as descendants (where we allow a node to be a descendant of itself)". A descend
//ant of a node x is a node y that is on the path from node x to some leaf node. 
//
// 
// Example 1: 
//
// 
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//Output: 3
//Explanation: The LCA of nodes 5 and 1 is 3. 
//
// Example 2: 
//
// 
//
// 
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//Output: 5
//Explanation: The LCA of nodes 5 and 4 is 5. A node can be a descendant of itse
//lf according to the definition of LCA. 
//
// Example 3: 
//
// 
//
// 
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
//Output: null
//Explanation: Node 10 does not exist in the tree, so return null.
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [1, 104]. 
// -109 <= Node.val <= 109 
// All Node.val are unique. 
// p != q 
// 
//
// 
//Follow up: Can you find the LCA traversing the tree, without checking nodes ex
//istence? Related Topics Tree 
// 👍 46 👎 1

package leetcode.editor.en;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import leetcode.editor.TreeDrawer;
import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-12-01 16:28:42
// Jesse Yang
public class Leetcode1644LowestCommonAncestorOfABinaryTreeIi{
    // Java: lowest-common-ancestor-of-a-binary-tree-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode1644LowestCommonAncestorOfABinaryTreeIi().new Solution();
        // TO TEST
        // TO TEST
        String serializedTreeNode = "3,5,1,6,2,0,8,null,null,7,4";
        TreeNode root = TreeGenerator.deserialize(serializedTreeNode);
        TreeDrawer.draw(root);
        TreeNode p = root.left;
        TreeNode q = root.right;
        System.out.printf("find %s %s \n", p.val, q.val);
        TreeNode LCA = sol.lowestCommonAncestor(root, p, q);
        System.out.println(LCA.val);
    }
//leetcode submit region begin(Prohibit modification and deletion)
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
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        Map<TreeNode, TreeNode> childToParent = new HashMap<>();
        buildGraph(root, null, childToParent);
        if (!childToParent.containsKey(p) || !childToParent.containsKey(q)) {
            return null;
        }
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
        // while (p != null) {
        //     visited.add(p);
        //     p = map.get(p);
        // }
        //
        // while (q != null) {
        //     if (visited.contains(q)) {
        //         return q;
        //     }
        //     visited.add(q);
        //     q = map.get(q);
        // }
        // return null;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: recursion, T(n) = O(n), S(n) = O(n)
// 7 ms,击败了92.46% 的Java用户, 42.3 MB,击败了28.53% 的Java用户
/**
 * 设置一个 2 size的boolean数组，记录是否已经遇到了p和q
 * recursion 数学归纳法：
 * base case:
 *      走到底，遇到null的话，return null
 *      如果遇到p或者q:
 *          如果还没遇到p和q中的另外一个，继续往下call，call往之后return 当前root
 *          否则（已经遇到p和q中的另外一个）说明两个都已经遇到了，return root
 *      否则继续往下call
 * general case:
 *      两边都是null的话，return null；
 *      遇到p，return p；
 *      遇到q，return q；
 *      遇到p，q， return 自己
 */
class Solution1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        boolean[] findPQ = {false, false}; // {findP, findQ}
        TreeNode LCA = findPAndQ(root, p, q, findPQ);
        if (findPQ[0] && findPQ[1]) {
            return LCA;
        } else {
            return null;
        }
    }
    
    private TreeNode findPAndQ(TreeNode root, TreeNode p, TreeNode q, boolean[] findPQ) {
        // base case, failure
        if (root == null) {
            return null;
        }
        
        if (root == q) {
            findPQ[1] = true;
            if (findPQ[0]) { // base case, success
                return root;
            }
        }
        if (root == p) {
            findPQ[0] = true;
            if (findPQ[1]) { // base case, success
                return root;
            }
        }
        
        TreeNode left = findPAndQ(root.left, p, q, findPQ);
        TreeNode right = findPAndQ(root.right, p, q, findPQ);
        
        if (left != null && right != null) {
            return root;
        }
        if (root == p || root == q) {
            return root;
        }
        return left == null ? right : left;
    }
    
}

// Solution 2: 用Stack做post order遍历 T(n) = O(n), S(n) = O(n)
// 12 ms,击败了16.32% 的Java用户, 40 MB,击败了100.00% 的Java用户
/**
 * 用 stack post-order遍历，模拟solution 1 中 recursion的过程
 * 遇到出现的p或者q的时候，标记一下，
 * 后面在遇到p和q中的另外一个的时候，return栈顶
 */
class Solution2 {
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        // 往左下角走到最下面
        TreeNode res = null;
        boolean[] findPOrQ = {false, false}; // {findP, findQ}
        
        // 找到p和q中的一个，或者都到null的时候就停止
        while (cur != null) {
            if (cur == p || cur == q) {
                stack.push(cur);
                res = cur;
                if (cur == p) {
                    if (findPOrQ[1]) {
                        return q;
                    }
                    findPOrQ[0] = true;
                } else {
                    findPOrQ[1] = true;
                    if (findPOrQ[0]) {
                        return p;
                    }
                }
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
            if (!stack.isEmpty() && res == cur) {
                res = stack.peek();
            }
            if (!stack.isEmpty() && cur != stack.peek().right) {
                TreeNode top = stack.peek();
                cur = top.right;
                while (cur != null) { // 一直往下走,优先走左边
                    stack.push(cur);
                    if (cur == p || cur == q) {
                        if (cur == p) {
                            findPOrQ[0] = true;
                        } else {
                            findPOrQ[1] = true;
                        }
                        // p 和 q都已经找到了
                        if (findPOrQ[0] && findPOrQ[1]) {
                            return res;
                        }
                        res = cur;
                    }
                    if (cur.left != null) {
                        cur = cur.left;
                    } else {
                        cur = cur.right;
                    }
                    
                }
            }
        }
        return null;
    }
    
}

// Solution 3:建图，题目就变成了 Leetcode 160， T(n) = O(n), S(n) = O(n)
// 22 ms,击败了7.82% 的Java用户, 41.2 MB,击败了94.92% 的Java用户
/**
 * 把Tree变成Graph，用HashMap 存(key: child,parent:  parent), 题目就变成了Leetcode 160
 */
class Solution3 {
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        Map<TreeNode, TreeNode> childToParent = new HashMap<>();
        buildGraph(root, null, childToParent);
        if (!childToParent.containsKey(p) || !childToParent.containsKey(q)) {
            return null;
        }
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
        // while (p != null) {
        //     visited.add(p);
        //     p = map.get(p);
        // }
        //
        // while (q != null) {
        //     if (visited.contains(q)) {
        //         return q;
        //     }
        //     visited.add(q);
        //     q = map.get(q);
        // }
        // return null;
    }
    
}

}