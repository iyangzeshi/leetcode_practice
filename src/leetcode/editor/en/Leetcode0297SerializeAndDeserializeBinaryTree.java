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

import java.util.*;
import leetcode.editor.TreeNode;

// 2020-07-26 13:11:26
// Zeshi Yang
public class Leetcode0297SerializeAndDeserializeBinaryTree{
    // Java: serialize-and-deserialize-binary-tree
    public static void main(String[] args) {
        Codec sol = new Leetcode0297SerializeAndDeserializeBinaryTree().new Codec();
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
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    // use level order to encode, and null to make it a complete tree
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp !=null) {
                sb.append(temp.val).append(",");
                queue.offer(temp.left);
                queue.offer(temp.right);
            }
            else {
                sb.append("null" + ",");
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        String[] values = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        int counter = 0;
        TreeNode root = new TreeNode(Integer.parseInt(values[counter++]));
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            //recover the left node
            if (counter < values.length) {
                String left = values[counter++];
                if(!left.equals("null")) {
                    temp.left = new TreeNode (Integer.parseInt(left));
                    queue.offer(temp.left);
                }
            }

            //recover the right node
            if(counter < values.length) {
                String right = values[counter++];
                if (!right.equals("null")) {
                    temp.right = new TreeNode(Integer.parseInt(right));
                    queue.offer(temp.right);
                }
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

}