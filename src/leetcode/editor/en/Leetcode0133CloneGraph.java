//Given a reference of a node in a connected undirected graph. 
//
// Return a deep copy (clone) of the graph. 
//
// Each node in the graph contains a val (int) and a list (List[Node]) of its ne
//ighbors. 
//
// 
//class Node {
//    public int val;
//    public List<Node> neighbors;
//}
// 
//
// 
//
// Test case format: 
//
// For simplicity sake, each node's value is the same as the node's index (1-ind
//exed). For example, the first node with val = 1, the second node with val = 2, a
//nd so on. The graph is represented in the test case using an adjacency list. 
//
// Adjacency list is a collection of unordered lists used to represent a finite 
//graph. Each list describes the set of neighbors of a node in the graph. 
//
// The given node will always be the first node with val = 1. You must return th
//e copy of the given node as a reference to the cloned graph. 
//
// 
// Example 1: 
//
// 
//Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
//Output: [[2,4],[1,3],[2,4],[1,3]]
//Explanation: There are 4 nodes in the graph.
//1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
//2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
//3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
//4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
// 
//
// Example 2: 
//
// 
//Input: adjList = [[]]
//Output: [[]]
//Explanation: Note that the input contains one empty list. The graph consists o
//f only one node with val = 1 and it does not have any neighbors.
// 
//
// Example 3: 
//
// 
//Input: adjList = []
//Output: []
//Explanation: This an empty graph, it does not have any nodes.
// 
//
// Example 4: 
//
// 
//Input: adjList = [[2],[1]]
//Output: [[2],[1]]
// 
//
// 
// Constraints: 
//
// 
// 1 <= Node.val <= 100 
// Node.val is unique for each node. 
// Number of Nodes will not exceed 100. 
// There is no repeated edges and no self-loops in the graph. 
// The Graph is connected and all nodes can be visited starting from the given n
//ode. 
// 
// Related Topics Depth-first Search Breadth-first Search Graph 
// 👍 1967 👎 1415

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
// 2020-09-07 15:58:10
// Zeshi Yang
public class Leetcode0133CloneGraph{
    // Java: clone-graph
    public static void main(String[] args) {
        Solution sol = new Leetcode0133CloneGraph().new Solution();
        // TO TEST
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        Node copyNode = sol.cloneGraph(node1);
        
        System.out.println(copyNode.val);
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        // corner case
        if (node == null) {
            return null;
        }
        
        // general case
        Map<Node, Node> oldToNew = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (!oldToNew.containsKey(cur)) {
                oldToNew.put(cur, new Node(cur.val));
            }
            Node curCopy = oldToNew.get(cur);
            // Node curCopy = oldToNew.computeIfAbsent(cur, k -> new Node(cur.val));
            List<Node> neighbors = cur.neighbors;
            for (Node neighbor: neighbors) {
                if (!oldToNew.containsKey(neighbor)) {
                    Node neighborCopy = new Node(neighbor.val);
                    oldToNew.put(neighbor, neighborCopy);
                    queue.offer(neighbor);
                }
                curCopy.neighbors.add(oldToNew.get(neighbor));
            }
        }
        return oldToNew.get(node);
    }
}

//leetcode submit region end(Prohibit modification and deletion)
/**面试的时候用Solution 2 **/

// Solution 1: BFS, T(n) = O(n), S(n) = O(n)
// 23 ms,击败了99.96% 的Java用户, 39.3 MB,击败了52.79% 的Java用户
class Solution1 {
    public Node cloneGraph(Node node) {
        // corner case
        if (node == null) {
            return null;
        }
        
        // general case
        Map<Node, Node> oldToNew = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (!oldToNew.containsKey(cur)) {
                oldToNew.put(cur, new Node(cur.val));
            }
            Node curCopy = oldToNew.get(cur);
            // Node curCopy = oldToNew.computeIfAbsent(cur, k -> new Node(cur.val));
            List<Node> neighbors = cur.neighbors;
            for (Node neighbor: neighbors) {
                if (!oldToNew.containsKey(neighbor)) {
                    Node neighborCopy = new Node(neighbor.val);
                    oldToNew.put(neighbor, neighborCopy);
                    queue.offer(neighbor);
                }
                curCopy.neighbors.add(oldToNew.get(neighbor));
            }
        }
        return oldToNew.get(node);
    }
}

// Solution 2: DFS, T(n) = O(n), S(n) = O(n)
/*
 create a hashmap to record the relationship between old node and new node,
 use DFS algorithm to traverse all the node and copy them
 */
class Solution2 {
    
    public Node cloneGraph(Node node) {
        // corner case
        if (node == null) {
            return null;
        }
        
        Map<Node, Node> oldToNew = new HashMap<>();
        return dfs(node, oldToNew);
    }
    
    /**
     * given the graph with starting node cur, return the copied graph with starting new node
     * @param cur: current node in the graph
     * @param oldToNew: Map, key: old node,; value: new copied node
     * @return copied new node for the given graph
     */
    private Node dfs(Node cur, Map<Node, Node> oldToNew) {
        // base case
        if (oldToNew.containsKey(cur)) {
            return oldToNew.get(cur);
        }
        
        // general case
        oldToNew.put(cur, new Node(cur.val));
        
        for (Node next : cur.neighbors) {
            Node neighbor = dfs(next, oldToNew);
            oldToNew.get(cur).neighbors.add(neighbor); // next的clone = dfs(next, oldToNew)
        }
        return oldToNew.get(cur);
    }
    
}

// Definition for a Node.
static class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
}