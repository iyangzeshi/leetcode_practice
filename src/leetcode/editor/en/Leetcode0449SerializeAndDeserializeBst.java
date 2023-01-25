//Serialization is the process of converting a data structure or object into a s
//equence of bits so that it can be stored in a file or memory buffer, or transmit
//ted across a network connection link to be reconstructed later in the same or an
//other computer environment. 
//
// Design an algorithm to serialize and deserialize a binary search tree. There 
//is no restriction on how your serialization/deserialization algorithm should wor
//k. You just need to ensure that a binary search tree can be serialized to a stri
//ng and this string can be deserialized to the original tree structure. 
//
// The encoded string should be as compact as possible. 
//
// Note: Do not use class member/global/static variables to store states. Your s
//erialize and deserialize algorithms should be stateless. 
// Related Topics Tree 
// 👍 1397 👎 70

package leetcode.editor.en;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import leetcode.editor.TreeDrawer;
import leetcode.editor.TreeGenerator;
import leetcode.editor.TreeNode;

// 2020-09-07 16:49:05
// Zeshi Yang
public class Leetcode0449SerializeAndDeserializeBst{
    // Java: serialize-and-deserialize-bst
    public static void main(String[] args) {
        // TO TEST
	    Codec codec = new Leetcode0449SerializeAndDeserializeBst().new Codec();
        String data = "2,1,3";
        TreeNode root = TreeGenerator.deserialize(data);
        TreeDrawer.draw(root);
        data = codec.serialize(root);
        System.out.println(data);
        root = codec.deserialize(data);
        TreeDrawer.draw(root);
    
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
// Solution 1_1: BFS, T(n) = O(n), S(n) = O(n)
// 8 ms,击败了51.12% 的Java用户, 40 MB,击败了53.49% 的Java用户
/*
遇到null的child的时候，要在serialize的结果里标记一下，加上null，
因为同一个serialized的StringString可以对应不同的BST，但是一个BST只能对应一个serialized的String
 */
public class Codec {
    
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
            sb.append(cur.val).append(",");
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // corner case
        if (data == null || data.length() == 0) {
            return null;
        }
        
        String[] strArr = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        Map<Integer, Integer> lower = new HashMap<>();
        Map<Integer, Integer> upper = new HashMap<>();
        int count = 0;
        int rootVal = Integer.parseInt(strArr[count++]);
        lower.put(rootVal, Integer.MIN_VALUE);
        upper.put(rootVal, Integer.MAX_VALUE);
        TreeNode root = new TreeNode(rootVal);
        queue.offer(root);
        
        while (count < strArr.length) {
            TreeNode cur = queue.poll();
            String next = strArr[count];
            int val = Integer.parseInt(next);
            if (val < cur.val && val > lower.get(cur.val)) {
                TreeNode left = new TreeNode(val);
                cur.left = left;
                queue.offer(left);
                count++;
                lower.put(val, lower.get(cur.val));
                upper.put(val, cur.val);
            }
            if (count == strArr.length) {
                break;
            }
            next = strArr[count];
            val = Integer.parseInt(next);
            if (val > cur.val && val < upper.get(cur.val)) {
                TreeNode right = new TreeNode(val);
                cur.right = right;
                queue.offer(right);
                count++;
                lower.put(val, cur.val);
                upper.put(val, upper.get(cur.val));
            }
            lower.remove(cur.val); // 可有可无，这个点被poll出来之后，后面就用不到了
            upper.remove(cur.val); // 可有可无，这个点被poll出来之后，后面就用不到了
        }
        return root;
    }
    
}
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

/**面试的时候，用Solution 1_1: BFS*/

// Solution 1_1: BFS, T(n) = O(n), S(n) = O(n)
// 8 ms,击败了51.12% 的Java用户, 40 MB,击败了53.49% 的Java用户
/*
遇到null的child的时候，要在serialize的结果里标记一下，加上null，
因为同一个serialized的StringString可以对应不同的BST，但是一个BST只能对应一个serialized的String
 */
public class Codec1_1 {
    
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
        if (data == null || data.length() == 0) {
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

// Solution 1_2: BFS, T(n) = O(n), S(n) = O(n)
// 10 ms,击败了40.42% 的Java用户, 40.2 MB,击败了36.88% 的Java用户
/*
利用BST的性质改进版本
遇到null的child的时候，不用管，直接跳过，因为是BST，所以这个点的位置肯定是唯一确定的
用两个HashMap来标记每个点的child的lower bound和upper bound
 */
public class Codec1_2 {
    
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
            sb.append(cur.val).append(",");
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // corner case
        if (data == null || data.length() == 0) {
            return null;
        }
        
        String[] strArr = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        Map<Integer, Integer> lower = new HashMap<>();
        Map<Integer, Integer> upper = new HashMap<>();
        int count = 0;
        int rootVal = Integer.parseInt(strArr[count++]);
        lower.put(rootVal, Integer.MIN_VALUE);
        upper.put(rootVal, Integer.MAX_VALUE);
        TreeNode root = new TreeNode(rootVal);
        queue.offer(root);
        
        while (count < strArr.length) {
            TreeNode cur = queue.poll();
            String next = strArr[count];
            int val = Integer.parseInt(next);
            if (val < cur.val && val > lower.get(cur.val)) {
                TreeNode left = new TreeNode(val);
                cur.left = left;
                queue.offer(left);
                count++;
                lower.put(val, lower.get(cur.val));
                upper.put(val, cur.val);
            }
            if (count == strArr.length) {
                break;
            }
            next = strArr[count];
            val = Integer.parseInt(next);
            if (val > cur.val && val < upper.get(cur.val)) {
                TreeNode right = new TreeNode(val);
                cur.right = right;
                queue.offer(right);
                count++;
                lower.put(val, cur.val);
                upper.put(val, upper.get(cur.val));
            }
            lower.remove(cur.val); // 可有可无，这个点被poll出来之后，后面就用不到了
            upper.remove(cur.val); // 可有可无，这个点被poll出来之后，后面就用不到了
        }
        return root;
    }
    
}

// Solution 2_1: pre order DFS, T(n) = O(n), S(n) = O(n)
// 6 ms,击败了66.99% 的Java用户, 39.9 MB,击败了63.02% 的Java用户
/*
pre order dfs 遍历
遇到null的时候，就在stringBuilder里面加一个"null", 这样路径和List就是一一对应的
 */
public class Codec2_1 {
    
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
    private void traverseTree(TreeNode root, StringBuilder sb) {
        // base case
        if (root == null) {
            sb.append(mark).append(",");
            return;
        }
        
        sb.append(root.val).append(",");
        traverseTree(root.left, sb);
        traverseTree(root.right, sb);
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

// Solution 2_2: pre order DFS, T(n) = O(n), S(n) = O(n)
// 5 ms,击败了76.80% 的Java用户, 40.2 MB,击败了44.53% 的Java用户
/*
pre order dfs 遍历
遇到null的时候，直接跳过就行了，因为BST的性质，每个值的为止是确定的
 */
public class Codec2_2 {
    
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
    private void traverseTree(TreeNode root, StringBuilder sb) {
        // base case
        if (root == null) {
            // sb.append(mark).append(",");
            return;
        }
        
        sb.append(root.val).append(",");
        traverseTree(root.left, sb);
        traverseTree(root.right, sb);
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // corner case
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] strArr = data.split(",");
        LinkedList<String> list = new LinkedList<>(Arrays.asList(strArr));
        return deserializeDfs(list, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private TreeNode deserializeDfs(LinkedList<String> list, int lower, int upper) {
        int val = Integer.parseInt(list.removeFirst());
        TreeNode root = new TreeNode(val);
        if (!list.isEmpty()) {
            int next = Integer.parseInt(list.getFirst());
            if (lower < next && next < val) {
                root.left = deserializeDfs(list, lower, root.val);
            }
        }
        if (!list.isEmpty()) {
            int next = Integer.parseInt(list.getFirst());
            if (val < next && next < upper) {
                root.right = deserializeDfs(list, root.val, upper);
            }
        }
        return root;
    }
    
}

}