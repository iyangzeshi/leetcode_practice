//A binary tree is given such that each node contains an additional random point
//er which could point to any node in the tree or null. 
//
// Return a deep copy of the tree. 
//
// The tree is represented in the same input/output way as normal binary trees w
//here each node is represented as a pair of [val, random_index] where: 
//
// 
// val: an integer representing Node.val 
// random_index: the index of the node (in the input) where the random pointer p
//oints to, or null if it does not point to any node. 
// 
//
// You will be given the tree in class Node and you should return the cloned tre
//e in class NodeCopy. NodeCopy class is just a clone of Node class with the same 
//attributes and constructors. 
//
// 
// Example 1: 
//
// 
//Input: root = [[1,null],null,[4,3],[7,0]]
//Output: [[1,null],null,[4,3],[7,0]]
//Explanation: The original binary tree is [1,null,4,7].
//The random pointer of node one is null, so it is represented as [1, null].
//The random pointer of node 4 is node 7, so it is represented as [4, 3] where 3
// is the index of node 7 in the array representing the tree.
//The random pointer of node 7 is node 1, so it is represented as [7, 0] where 0
// is the index of node 1 in the array representing the tree.
// 
//
// Example 2: 
//
// 
//Input: root = [[1,4],null,[1,0],null,[1,5],[1,5]]
//Output: [[1,4],null,[1,0],null,[1,5],[1,5]]
//Explanation: The random pointer of a node can be the node itself.
// 
//
// Example 3: 
//
// 
//Input: root = [[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
//Output: [[1,6],[2,5],[3,4],[4,3],[5,2],[6,1],[7,0]]
// 
//
// Example 4: 
//
// 
//Input: root = []
//Output: []
// 
//
// Example 5: 
//
// 
//Input: root = [[1,null],null,[2,null],null,[1,null]]
//Output: [[1,null],null,[2,null],null,[1,null]]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 1000]. 
// Each node's value is between [1, 10^6]. 
// 
// Related Topics Hash Table Tree Depth-first Search Breadth-first Search 
// 👍 109 👎 19

package leetcode.editor.en;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// 2020-12-21 11:23:31
// Zeshi Yang
public class Leetcode1485CloneBinaryTreeWithRandomPointer{
    // Java: clone-binary-tree-with-random-pointer
    public static void main(String[] args) {
        Solution sol = new Leetcode1485CloneBinaryTreeWithRandomPointer().new Solution();
        // TO TEST
        
        System.out.println();
    }
    
public class Node {
    
    int val;
    Node left;
    Node right;
    Node random;
    
    Node() {
    }
    
    Node(int val) {
        this.val = val;
    }
    
    Node(int val, Node left, Node right, Node random) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.random = random;
    }
    
}

public class NodeCopy {
    
    int val;
    NodeCopy left;
    NodeCopy right;
    NodeCopy random;
    
    NodeCopy() {
    }
    
    NodeCopy(int val) {
        this.val = val;
    }
    
    NodeCopy(int val, NodeCopy left, NodeCopy right, NodeCopy random) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.random = random;
    }
    
}
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for Node.
 * public class Node {
 *     int val;
 *     Node left;
 *     Node right;
 *     Node random;
 *     Node() {}
 *     Node(int val) { this.val = val; }
 *     Node(int val, Node left, Node right, Node random) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *         this.random = random;
 *     }
 * }
 */

class Solution {
    
    public NodeCopy copyRandomBinaryTree(Node root) {
        Map<Node, NodeCopy> copyMap = new HashMap<>(); // old Node to new NodeCopy
        dfsCopy(root, copyMap);
        return copyMap.get(root);
    }
    
    private NodeCopy dfsCopy(Node root, Map<Node, NodeCopy> map) {
        // base case
        if (root == null) {
            return null;
        }
        if (map.containsKey(root)) { // avoid revisiting
            return map.get(root);
        }
        NodeCopy copyNode = new NodeCopy(root.val);
        map.put(root, copyNode);
        NodeCopy left = dfsCopy(root.left, map);
        NodeCopy right = dfsCopy(root.right, map);
        NodeCopy random = dfsCopy(root.random, map);
        copyNode.left = left;
        copyNode.right = right;
        copyNode.random = random;
        return copyNode;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: BFS, T(n) = O(n), S(n) = O(n)
// 12 ms,击败了19.96% 的Java用户, 40 MB,击败了74.58% 的Java用户
class Solution1 {
    public NodeCopy copyRandomBinaryTree(Node root) {
        // corner case
        if (root == null) {
            return null;
        }
        HashMap<Node, NodeCopy> oldToNew = new HashMap<>(); // old Node to corresponding new node
        NodeCopy curCopy = new NodeCopy(root.val);
        oldToNew.put(root, curCopy);
        bfs(root, oldToNew);
        return curCopy;
    }
    
    private void bfs(Node cur, HashMap<Node, NodeCopy> map) {
        Queue<Node> queue = new LinkedList<>();
        NodeCopy curCopy;
        queue.offer(cur);
        while(!queue.isEmpty()) {
            cur = queue.poll();
            curCopy = map.get(cur);
            if (cur.left != null) {
                NodeCopy left;
                if (map.containsKey(cur.left)) {
                    left = map.get(cur.left);
                } else {
                    left = new NodeCopy(cur.left.val);
                    map.put(cur.left, left);
                    queue.offer(cur.left);
                }
                curCopy.left = left;
            }
            if (cur.right != null) {
                NodeCopy right;
                if (map.containsKey(cur.right)) {
                    right = map.get(cur.right);
                } else {
                    right = new NodeCopy(cur.right.val);
                    map.put(cur.right, right);
                    queue.offer(cur.right);
                }
                curCopy.right = right;
            }
            if (cur.random != null) {
                NodeCopy random;
                if (map.containsKey(cur.random)) {
                    random = map.get(cur.random);
                } else {
                    random = new NodeCopy(cur.random.val);
                    map.put(cur.random, random);
                    queue.offer(cur.random);
                }
                curCopy.random = random;
            }
        }
    }
    
}

// Solution 2: DFS, recursion
// Solution 2_1: DFS, top down, pre-order recursion, T(n) = O(n), S(n) = O(n)
// 6 ms,击败了99.62% 的Java用户, 40.1 MB,击败了74.58% 的Java用户
class Solution2_1 {
    
    public NodeCopy copyRandomBinaryTree(Node root) {
        Map<Node, NodeCopy> copyMap = new HashMap<>(); // old Node to new NodeCopy
        dfsCopy(root, copyMap);
        return copyMap.get(root);
    }
    
    private NodeCopy dfsCopy(Node root, Map<Node, NodeCopy> map) {
        // base case
        if (root == null) {
            return null;
        }
        if (map.containsKey(root)) { // avoid revisiting
            return map.get(root);
        }
        NodeCopy copyNode = new NodeCopy(root.val);
        map.put(root, copyNode);
        copyNode.left = dfsCopy(root.left, map);
        copyNode.right = dfsCopy(root.right, map);
        copyNode.random = dfsCopy(root.random, map);
        return copyNode;
    }
    
}

// Solution 2_2: DFS, bottom up, post-order recursion, T(n) = O(n), S(n) = O(n)
// 6 ms,击败了99.62% 的Java用户, 40.1 MB,击败了74.58% 的Java用户
class Solution2_2 {
    
    public NodeCopy copyRandomBinaryTree(Node root) {
        Map<Node, NodeCopy> copyMap = new HashMap<>(); // old Node to new NodeCopy
        dfsCopy(root, copyMap);
        return copyMap.get(root);
    }
    
    private NodeCopy dfsCopy(Node root, Map<Node, NodeCopy> map) {
        // base case
        if (root == null) {
            return null;
        }
        if (map.containsKey(root)) { // avoid revisiting
            return map.get(root);
        }
        NodeCopy copyNode = new NodeCopy(root.val);
        map.put(root, copyNode);
        NodeCopy left = dfsCopy(root.left, map);
        NodeCopy right = dfsCopy(root.right, map);
        NodeCopy random = dfsCopy(root.random, map);
        copyNode.left = left;
        copyNode.right = right;
        copyNode.random = random;
        return copyNode;
    }
    
}
}
