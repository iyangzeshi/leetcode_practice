//Given a root of an size-ary tree, return a deep copy (clone) of the tree.
//
// Each node in the n-ary tree contains a val (int) and a list (List[Node]) of i
//ts children. 
//
// 
//class Node {
//    public int val;
//    public List<Node> children;
//}
// 
//
// Nary-Tree input serialization is represented in their level order traversal, 
//each group of children is separated by the null value (See examples). 
//
// Follow up: Can your solution work for the graph problem? 
//
// 
// Example 1: 
//
// 
//
// 
//Input: root = [1,null,3,2,4,null,5,6]
//Output: [1,null,3,2,4,null,5,6]
// 
//
// Example 2: 
//
// 
//
// 
//Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null
//,12,null,13,null,null,14]
//Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,nu
//ll,13,null,null,14]
// 
//
// 
// Constraints: 
//
// 
// The depth of the n-ary tree is less than or equal to 1000. 
// The total number of nodes is between [0, 10^4]. 
// 
// Related Topics Hash Table Tree Depth-first Search Breadth-first Search 
// 👍 110 👎 6

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
// 2020-12-21 12:29:06
// Jesse Yang
public class Leetcode1490CloneNAryTree{
    // Java: clone-n-ary-tree
    public static void main(String[] args) {
        Solution sol = new Leetcode1490CloneNAryTree().new Solution();
        // TO TEST
        
        System.out.println();
    }
class Node {
    public int val;
    public List<Node> children;
    
    
    public Node() {
        children = new ArrayList<>();
    }
    
    public Node(int _val) {
        val = _val;
        children = new ArrayList<>();
    }
    
    public Node(int _val,ArrayList<Node> _children) {
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

    
    public Node() {
        children = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }
    
    public Node(int _val,ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
// BFS, T(n) = O(n), S(n) = O(n)
// 4 ms,击败了26.43% 的Java用户, 40.8 MB,击败了58.08% 的Java用户
class Solution {
    
    public Node cloneTree(Node root) {
        if (root == null) {
            return null;
        }
        HashMap<Node, Node> oldToNew = new HashMap<>(); // old Node to corresponding new node
        Node curCopy = new Node(root.val);
        oldToNew.put(root, curCopy);
        bfs(root, oldToNew);
        return curCopy;
    }
    
    private void bfs(Node cur, HashMap<Node, Node> map) {
        Queue<Node> queue = new LinkedList<>();
        Node curCopy;
        queue.offer(cur);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            curCopy = map.get(cur);
            for (Node neighbor : cur.children) {
                if (map.containsKey(neighbor)) {
                    curCopy.children.add(map.get(neighbor));
                } else {
                    Node neighborCopy = new Node(neighbor.val);
                    map.put(neighbor, neighborCopy);
                    curCopy.children.add(neighborCopy);
                    queue.offer(neighbor);
                }
            }
        }
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}