//Given an n-ary tree, return the postorder traversal of its nodes' values. 
//
// Nary-Tree input serialization is represented in their level order traversal, 
//each group of children is separated by the null value (See examples). 
//
// 
//
// Follow up: 
//
// Recursive solution is trivial, could you do it iteratively? 
//
// 
// Example 1: 
//
// 
//
// 
//Input: root = [1,null,3,2,4,null,5,6]
//Output: [5,6,3,2,4,1]
// 
//
// Example 2: 
//
// 
//
// 
//Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null
//,12,null,13,null,null,14]
//Output: [2,6,14,11,7,3,12,8,4,13,9,10,5,1]
// 
//
// 
// Constraints: 
//
// 
// The height of the n-ary tree is less than or equal to 1000 
// The total number of nodes is between [0, 10^4] 
// 
// Related Topics Tree 
// 👍 821 👎 72

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;

// 2020-12-02 11:09:43
// Zeshi Yang
public class Leetcode0590NAryTreePostorderTraversal{
    // Java: n-ary-tree-postorder-traversal
    public static void main(String[] args) {
        Solution sol = new Leetcode0590NAryTreePostorderTraversal().new Solution();
        // TO TEST
        String data = "1,null,3,2,4,null,5,6";
        Node root = TreeOfNodeGenerator.deserialize(data);
        List<Integer> res = sol.postorder(root);
        System.out.println(res);
    }
    
static class Node {
    
    public int val;
    public List<Node> children;
    
    public Node() {
    }
    
    public Node(int _val) {
        val = _val;
    }
    
    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}

static class TreeOfNodeGenerator {
    
    public static Node deserialize(String data) {
        String str = data.replaceAll("\\s*", "");
        String[] ss = str.split(",");
        if (ss.length == 0) {
            throw new IllegalArgumentException();
        }
        if (ss.length == 1 && (ss[0].equals("null") || ss[0].equals(" null"))) {
            return null;
        }
        
        Node root = new Node(Integer.parseInt(ss[0]));
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        /*int i = 1;
        while (i < ss.length) {
            Node cur = queue.poll();
            
            Node left = ss[i].equals("null") ? null : new Node(Integer.parseInt(ss[i]));
            i++;
            Node right = (i >= ss.length || ss[i].equals("null")) ? null
                    : new Node(Integer.parseInt(ss[i]));
            
            // cur.left = left;
            // cur.right = right;
            
            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }
            
            i++;
        }*/
        Node cur = root;
        for (int i = 1; i < ss.length; i++) {
            if (ss[i].equals("null")) {
                cur = queue.poll();
                cur.children = new ArrayList<>();
            } else {
                Node child = new Node(Integer.parseInt(ss[i]));
                cur.children.add(child);
                queue.offer(child);
            }
        }
        return root;
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
    public List<Integer> postorder(Node root) {
        Stack<Node> stack = new Stack<>();
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.addFirst(node.val);
            for (Node item : node.children) {
                if (item != null) {
                    stack.push(item);
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: recursion, T(n) = O(n), S(n) = O(n)
// 0 ms,击败了100.00% 的Java用户, 40.2 MB,击败了19.61% 的Java用户
class Solution1 {
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        dfsPostOrderTraversal(root, res);
        return res;
    }
    
    private void dfsPostOrderTraversal(Node root, List<Integer> res) {
        // base case
        if (root == null) {
            return;
        }
        
        List<Node> list = root.children;
        for (Node node: list) {
            dfsPostOrderTraversal(node, res);
        }
        res.add(root.val);
    }
}

// Solution 2: stack 模拟recursion的过程, stack真的在做post order遍历 T(n) = O(n), S(n) = O(n)
// 9 ms,击败了8.84% 的Java用户, 39.7 MB,击败了79.95% 的Java用户
/**
 * stack pop的时候，把值加到List<Integer>里面
 * stack里面存的点pop出来的时候，说明这个点为root的子树已经遍历完了
 * 先stack加栈，每次遇到Node的时候，都往stack里面加左边的第一个点
 * pop的时候，先看当前的点是兄弟节点里面的第几个
 *      如果是最后一个，说明这个分支走完了
 *      如果不是最后一个，把后面一个兄弟节点push到stack里面，而且往左边走到底
 */
class Solution2 {
    
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<Node> stack = new Stack<>();
        // stack.push(root);
        // processing
        while (root != null) {
            stack.push(root);
            List<Node> children = root.children;
            if (children != null && children.size() != 0) {
                root = children.get(0);
            } else {
                root = null;
            }
        }
        // general case
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            res.add(cur.val);
            if (!stack.isEmpty()) {
                List<Node> brothers = stack.peek().children;
                int indexInBrothers;
                if (brothers.contains(cur)) {
                    indexInBrothers = brothers.indexOf(cur);
                } else {
                    throw new RuntimeException("Can not happen");
                }
                /*
                如果cur不是它父节点的最后一个child，就继续往下走，
                否则表示这个点为root的subtree已经走完了
                 */
                if (indexInBrothers != brothers.size() - 1) {
                    cur = brothers.get(indexInBrothers + 1);
                    while (cur != null) {
                        stack.push(cur);
                        List<Node> children = cur.children;
                        if (cur.children != null && cur.children.size() != 0) {
                            cur = children.get(0);
                        } else {
                            cur = null;
                        }
                    }
                }
            }
        }
        return res;
    }
    
}

// Solution 3: stack, res可以用addFirst()方法， T(n) = O(n), S(n) = O(n)
// 2 ms,击败了46.58% 的Java用户, 40.1 MB,击败了27.91% 的Java用户
/**
 * stack先压root
 * 每次poll的时候，把它值addFirst()放到res里面，再把所有children从左往右push到stack里面
 */
class Solution3 {
    public List<Integer> postorder(Node root) {
        Stack<Node> stack = new Stack<>();
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.addFirst(node.val);
            for (Node item : node.children) {
                if (item != null) {
                    stack.push(item);
                }
            }
        }
        return res;
    }
}

// Solution 4: stack, children从右边往左压到stack里面T(n) = O(n), S(n) = O(k * n), k是node的最大children数量
// 3 ms,击败了27.20% 的Java用户, 40.1 MB,击败了27.91% 的Java用户
/**
 * stack, 每次不管push之后，都把它的所有children从右边往左压到stack里面
 */
class Solution4 {
    public List<Integer> postorder(Node root) {
        Stack<Node> stack = new Stack<>();
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        
        // processing
        Node cur = root;
        stack.push(cur); // 一直往下走,优先走左边
        pushEveryChildAndStepToFirstOne(stack, cur);
        
        while (!stack.isEmpty()) {
            cur = stack.pop();
            res.add(cur.val);
            if (!stack.isEmpty()) {
                List<Node> brothers = stack.peek().children;
                int indexInBrothers;
                if (brothers != null && brothers.contains(cur)) {
                    continue;
                }
                
                cur = stack.peek();
                pushEveryChildAndStepToFirstOne(stack, cur);
            }
        }
        return res;
    }
    
    private void pushEveryChildAndStepToFirstOne(Stack<Node> stack, Node cur) {
        while (cur.children != null && cur.children.size() != 0) {
            List<Node> children = cur.children;
            ListIterator<Node> nodeListIterator = children.listIterator(children.size());
            while (nodeListIterator.hasPrevious()) {
                cur = nodeListIterator.previous();
                stack.push(cur);
            }
        }
    }
}

}