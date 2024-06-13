//Serialization is the process of converting a data structure or object into a s
//equence of bits so that it can be stored in a file or memory buffer, or transmit
//ted across a network connection link to be reconstructed later in the same or an
//other computer environment. 
//
// Design an algorithm to serialize and deserialize an size-ary tree. An size-ary tree
// is a rooted tree in which each node has no more than size children. There is no re
//striction on how your serialization/deserialization algorithm should work. You j
//ust need to ensure that an size-ary tree can be serialized to a string and this str
//ing can be deserialized to the original tree structure. 
//
// For example, you may serialize the following 3-ary tree 
//
// 
//
// as [1 [3[5 6] 2 4]]. Note that this is just an example, you do not necessaril
//y need to follow this format. 
//
// Or you can follow LeetCode's level order traversal serialization format, wher
//e each group of children is separated by the null value. 
//
// 
//
// For example, the above tree may be serialized as [1,null,2,3,4,5,null,null,6,
//7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]. 
//
// You do not necessarily need to follow the above suggested formats, there are 
//many more different formats that work so please be creative and come up with dif
//ferent approaches yourself. 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 104]. 
// 0 <= Node.val <= 104 
// The height of the n-ary tree is less than or equal to 1000 
// Do not use class member/global/static variables to store states. Your encode 
//and decode algorithms should be stateless. 
// 
// Related Topics Tree 
// 👍 580 👎 28

package leetcode.editor.en;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 2021-02-25 14:18:22
// Jesse Yang
public class Leetcode0428SerializeAndDeserializeNAryTree{
    // Java: serialize-and-deserialize-n-ary-tree
    public static void main(String[] args) {
        Codec codec = new Leetcode0428SerializeAndDeserializeNAryTree().new Codec();
        // TO TEST
        // String data = "1,null,3,2,4,null,5,6,null,null,null,null,null";
        // Node root = codec.deserialize(data);
        // System.out.println(root.val);
    }
class Node {
    
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
class Codec {
    
    private String mark = "#";
    
    /*
    原理的tree的表达式:[1,null,3,2,4,null,5,6]
    serialize之后: [1,null,3,2,4,null,5,6,null,null,null,null,null]
    deserialize之后变成原来的tree
     */
    
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        sb.append(root.val).append(",").append(mark);
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur != null) {
                if (cur.children != null && cur.children.size() != 0) {
                    for (Node next : cur.children) {
                        sb.append(",").append(next.val);
                        queue.offer(next);
                    }
                }
                sb.append(",").append(mark);
            }
        }
        return sb.toString();
    }
    
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        // System.out.println(data);
        String dataStr = data.replaceAll("\\s*", "");
        String[] strArr = dataStr.split(",");
        
        Queue<Node> queue = new LinkedList<>();
        Node parent = null;
        Node root = null;
        boolean isFirst = true;
        for (String str : strArr) {
            if (str.equals(mark)) {
                parent = queue.poll();
                continue;
            }
            int cur = Integer.parseInt(str);
            Node node = new Node(cur, new LinkedList<>());
            if (isFirst) {
                root = node;
            } else {
                parent.children.add(node);
            }
            isFirst = false;
            queue.offer(node);
        }
        return root;
    }
    
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

// BFS, T(n) = O(n), S(n) = O(n)
// 21 ms,击败了8.24% 的Java用户, 41.1 MB,击败了28.32% 的Java用户
class Codec1 {
    
    private String mark = "null"; // no other child of this parent
    
    /*
    原理的tree的表达式:[1,null,3,2,4,null,5,6]
    serialize之后: [1,null,3,2,4,null,5,6,null,null,null,null,null]
    deserialize之后变成原来的tree
     */
    
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        sb.append(root.val).append(",").append(mark);
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur != null) {
                if (cur.children != null && cur.children.size() != 0) {
                    for (Node next : cur.children) {
                        sb.append(",").append(next.val);
                        queue.offer(next);
                    }
                }
                sb.append(",").append(mark);
            }
        }
        return sb.toString();
    }
    
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        // System.out.println(data);
        String dataStr = data.replaceAll("\\s*", "");
        String[] strArr = dataStr.split(",");
        
        Queue<Node> queue = new LinkedList<>();
        Node parent = null;
        Node root = null;
        boolean isFirst = true;
        for (String str : strArr) {
            if (str.equals(mark)) {
                parent = queue.poll();
                continue;
            }
            int cur = Integer.parseInt(str);
            Node node = new Node(cur, new LinkedList<>());
            if (isFirst) {
                root = node;
            } else {
                parent.children.add(node);
            }
            isFirst = false;
            queue.offer(node);
        }
        return root;
    }
    
}

// pre order DFS, T(n) = O(n), S(n) = O(n)
// 18 ms,击败了11.25% 的Java用户, 41.6 MB,击败了12.49% 的Java用户
class Codec2 {
    /*
    tree: [1,null,3,2,4,null,5,6]
    serialize: [1,3,5,null,6,null,null,2,null,4,null,null]
    每一个node走完它所有的children之后，后面加一个mark
    */
    
    private String mark = "null";
    
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }
        StringBuilder path = new StringBuilder();
        traverseTree(root, path);
        path.deleteCharAt(path.length() - 1);
        return path.toString();
    }
    
    // traverse tree by pre order dfs
    private void traverseTree(Node root, StringBuilder sb) {
        // base case
        if (root == null) {
            return;
        }
        
        sb.append(root.val).append(",");
        for (Node next : root.children) {
            traverseTree(next, sb);
        }
        sb.append(mark).append(",");
    }
    
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String dataStr = data.replaceAll("\\s*", "");
        String[] strArr = dataStr.split(",");
        LinkedList<String> list = new LinkedList<>(Arrays.asList(strArr));
        return deserialize_dfs(list);
        
    }
    
    private Node deserialize_dfs(LinkedList<String> list) {
        // base case
        if (list.getFirst().equals(mark)) {
            list.removeFirst();
            return null;
        }
        
        Node root = new Node(Integer.parseInt(list.removeFirst()), new LinkedList<>());
        while (!list.isEmpty()) {
            Node node = deserialize_dfs(list);
            if (node == null) {
                break;
            }
            root.children.add(node);
        }
        return root;
    }
    
}
}