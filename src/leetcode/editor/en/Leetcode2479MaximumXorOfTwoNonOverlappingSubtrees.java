/**
There is an undirected tree with n nodes labeled from 0 to n - 1. You are given 
the integer n and a 2D integer array edges of length n - 1, where edges[i] = [
ai, bi] indicates that there is an edge between nodes ai and bi in the tree. The 
root of the tree is the node labeled 0. 

 Each node has an associated value. You are given an array values of length n, 
where values[i] is the value of the iᵗʰ node. 

 Select any two non-overlapping subtrees. Your score is the bitwise XOR of the 
sum of the values within those subtrees. 

 Return the maximum possible score you can achieve. If it is impossible to find 
two nonoverlapping subtrees, return 0. 

 Note that: 

 
 The subtree of a node is the tree consisting of that node and all of its 
descendants. 
 Two subtrees are non-overlapping if they do not share any common node. 
 

 
 Example 1: 
 
 
Input: n = 6, edges = [[0,1],[0,2],[1,3],[1,4],[2,5]], values = [2,8,3,6,2,5]
Output: 24
Explanation: Node 1's subtree has sum of values 16, while node 2's subtree has 
sum of values 8, so choosing these nodes will yield a score of 16 XOR 8 = 24. It 
can be proved that is the maximum possible score we can obtain.
 

 Example 2: 
 
 
Input: n = 3, edges = [[0,1],[1,2]], values = [4,6,1]
Output: 0
Explanation: There is no possible way to select two non-overlapping subtrees, 
so we just return 0.
 

 
 Constraints: 

 
 2 <= n <= 5 * 10⁴ 
 edges.length == n - 1 
 0 <= ai, bi < n 
 values.length == n 
 1 <= values[i] <= 10⁹ 
 It is guaranteed that edges represents a valid tree. 
 

 Related Topics Tree Depth-First Search Graph Trie 👍 28 👎 3

*/
package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

// 2024-03-25 20:36:47
// Jesse Yang
public class Leetcode2479MaximumXorOfTwoNonOverlappingSubtrees{
    // Java: maximum-xor-of-two-non-overlapping-subtrees
    public static void main(String[] args) {
        Solution sol = new Leetcode2479MaximumXorOfTwoNonOverlappingSubtrees().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // time = O(n * k), space = O(n * k)
    List<Integer>[] graph;
    long[] weight;
    TrieNode root;
    int[] values;
    long res;
    public long maxXor(int n, int[][] edges, int[] values) {
        this.values = values;
        res = 0;
        weight = new long[n];
        graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            graph[a].add(b);
            graph[b].add(a);
        }
        
        // calculate all subtree node sum
        dfs1(0, -1);
        
        // find max XOR for each subtree
        root = new TrieNode();
        dfs2(0, -1);
        return res;
    }
    
    private void dfs2(int u, int fa) {
        long x = weight[u], s = 0;
        TrieNode p = root;
        for (int i = 46; i >= 0; i--) {
            int t = (int)(x >> i & 1);
            if (p.next[t ^ 1] != null) {
                p = p.next[t ^ 1];
                s = s * 2 + 1;
            } else if (p.next[t] != null) {
                p = p.next[t];
                s *= 2;
            }
        }
        res = Math.max(res, s);
        
        // 继续向下遍历该点为根的所有子树
        for (int j : graph[u]) {
            if (j == fa) continue;
            dfs2(j, u);
        }
        
        insert(x);
    }
    
    private void insert(long x) {
        TrieNode p = root;
        for (int i = 46; i >= 0; i--) {
            int u = (int)(x >> i & 1);
            if (p.next[u] == null) p.next[u] = new TrieNode();
            p = p.next[u];
        }
    }
    
    private long dfs1(int u, int fa) {
        weight[u] += values[u];
        for (int j : graph[u]) {
            if (j == fa) continue;
            weight[u] += dfs1(j, u);
        }
        return weight[u];
    }
    
    private class TrieNode {
        private TrieNode[] next;
        public TrieNode() {
            this.next = new TrieNode[2];
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
/*

XOR Trie = 建立在bit位基础上
int 32位 (前面补0）0010010101110
int 32位 (前面补0）0100101010101

 */
}