//Serialization is the process of converting a data structure or object into a s
//equence of bits so that it can be stored in a file or memory buffer, or transmit
//ted across a network connection link to be reconstructed later in the same or an
//other computer environment. 
//
// Design an algorithm to serialize and deserialize a binary tree. There is no r
//estriction on how your serialization/deserialization algorithm should work. You 
//just need to ensure that a binary tree can be serialized to a string and this st
//ring can be deserialized to the original tree structure. 
//
// Example: 
//
// 
//You may serialize the following tree:
//
//    1
//   / \
//  2   3
//     / \
//    4   5
//
//as "[1,2,3,null,null,4,5]"
// 
//
// Clarification: The above format is the same as how LeetCode serializes a bina
//ry tree. You do not necessarily need to follow this format, so please be creativ
//e and come up with different approaches yourself. 
//
// Note: Do not use class member/global/static variables to store states. Your s
//erialize and deserialize algorithms should be stateless. 
// Related Topics Tree Design 
// 👍 3066 👎 150

package leetcode.editor.en;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import leetcode.editor.TreeDrawer;
import leetcode.editor.TreeNode;

// 2020-07-26 13:11:26
// Zeshi Yang
public class Leetcode0297SerializeAndDeserializeBinaryTree{
    // Java: serialize-and-deserialize-binary-tree
    public static void main(String[] args) {
        Codec sol = new Leetcode0297SerializeAndDeserializeBinaryTree().new Codec();
        // TO TEST
    
        String data = "1, 3, 5, null, 12 ,3, null";
        TreeNode root = sol.deserialize(data);
        TreeDrawer.draw(root);
        System.out.println(sol.serialize(root));
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
public class Codec {
    
    private String mark = "null";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // corner case
        if (root == null) {
            return "";
        }
        
        StringBuilder path = new StringBuilder();
        traverseTree(root, path);
        path.deleteCharAt(path.length() - 1);
        return path.toString();
    }
    
    // traverse tree by pre order dfs
    private StringBuilder traverseTree(TreeNode root, StringBuilder sb) {
        // base case
        if (root == null) {
            sb.append(mark).append(",");
            return sb;
        }
        
        sb.append(root.val).append(",");
        traverseTree(root.left, sb);
        traverseTree(root.right, sb);
        return sb;
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // corner case
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] strArr = data.split(",");
        LinkedList<String> list = new LinkedList<>(Arrays.asList(strArr));
        return helper(list);
    }
    
    private TreeNode helper(LinkedList<String> list) {
        // base case
        if (list.getFirst().equals(mark)) {
            list.removeFirst();
            return null;
        }
        
        int val = Integer.parseInt(list.removeFirst());
        TreeNode root = new TreeNode(val);
        root.left = helper(list);
        root.right = helper(list);
        return root;
    }
    
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
/**面试的时候，用Solution 1*/

// Solution 1: BFS, T(n) = O(n), S(n) = O(n)
// 12 ms,击败了55.81% 的Java用户, 40.4 MB,击败了92.70% 的Java用户
public class Codec1 {
    
    private String mark = "null";
    
    // Encodes a tree to a single string.
    // use level order to encode, and null to make it a complete tree
    public String serialize(TreeNode root) {
        // corner case
        if (root == null) {
            return null;
        }
        
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur != null) {
                sb.append(cur.val).append(",");
                queue.offer(cur.left);
                queue.offer(cur.right);
            } else {
                sb.append(mark).append(",");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // corner case
        if (data == null) {
            return null;
        }
        
        String[] values = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        int counter = 0;
        TreeNode root = new TreeNode(Integer.parseInt(values[counter++]));
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            //recover the left node
            if (counter < values.length) {
                String left = values[counter++];
                if (!left.equals(mark)) {
                    temp.left = new TreeNode(Integer.parseInt(left));
                    queue.offer(temp.left);
                }
            }
            
            //recover the right node
            if (counter < values.length) {
                String right = values[counter++];
                if (!right.equals(mark)) {
                    temp.right = new TreeNode(Integer.parseInt(right));
                    queue.offer(temp.right);
                }
            }
        }
        return root;
    }
    
}

// Solution 2: pre order DFS, T(n) = O(n), S(n) = O(n)
// 8 ms,击败了80.24% 的Java用户, 40.8 MB,击败了76.29% 的Java用户
public class Codec2 {
    
    private String mark = "null";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // corner case
        if (root == null) {
            return "";
        }
        
        StringBuilder path = new StringBuilder();
        traverseTree(root, path);
        path.deleteCharAt(path.length() - 1);
        return path.toString();
    }
    
    // traverse tree by pre order dfs
    private StringBuilder traverseTree(TreeNode root, StringBuilder sb) {
        // base case
        if (root == null) {
            sb.append(mark).append(",");
            return sb;
        }
        
        sb.append(root.val).append(",");
        traverseTree(root.left, sb);
        traverseTree(root.right, sb);
        return sb;
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // corner case
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] strArr = data.split(",");
        LinkedList<String> list = new LinkedList<>(Arrays.asList(strArr));
        return deserializeDfs(list);
    }
    
    /*
    这里好好想，不容易解释
    每次会找到以第一个头节点起点的subtree，并把这个subtree的所有元素从List里面删除
     */
    private TreeNode deserializeDfs(LinkedList<String> list) {
        // base case
        if (list.getFirst().equals(mark)) {
            list.removeFirst();
            return null;
        }
        
        int val = Integer.parseInt(list.removeFirst());
        TreeNode root = new TreeNode(val);
        root.left = deserializeDfs(list);
        root.right = deserializeDfs(list);
        return root;
    }
    
}
}