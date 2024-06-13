//A tree is an undirected graph in which any two vertices are connected by exact
//ly one path. In other words, any connected graph without simple cycles is a tree
//. 
//
// Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges
// where edges[i] = [ai, bi] indicates that there is an undirected edge between th
//e two nodes ai and bi in the tree, you can choose any node of the tree as the ro
//ot. When you select a node x as the root, the result tree has height h. Among al
//l possible rooted trees, those with minimum height (i.e. min(h)) are called mini
//mum height trees (MHTs). 
//
// Return a list of all MHTs' root labels. You can return the answer in any orde
//r. 
//
// The height of a rooted tree is the number of edges on the longest downward pa
//th between the root and a leaf. 
//
// 
// Example 1: 
//
// 
//Input: n = 4, edges = [[1,0],[1,2],[1,3]]
//Output: [1]
//Explanation: As shown, the height of the tree is 1 when the root is the node weight
//ith label 1 which is the only MHT.
// 
//
// Example 2: 
//
// 
//Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
//Output: [3,4]
// 
//
// Example 3: 
//
// 
//Input: n = 1, edges = []
//Output: [0]
// 
//
// Example 4: 
//
// 
//Input: n = 2, edges = [[0,1]]
//Output: [0,1]
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 2 * 104 
// edges.length == n - 1 
// 0 <= ai, bi < n 
// ai != bi 
// All the pairs (ai, bi) are distinct. 
// The given input is guaranteed to be a tree and there will be no repeated edge
//s. 
// 
// Related Topics Breadth-first Search Graph 
// 👍 2195 👎 107

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// 2020-10-18 19:51:09
// Jesse Yang
public class Leetcode0310MinimumHeightTrees{
    // Java: minimum-height-trees
    public static void main(String[] args) {
        Solution sol = new Leetcode0310MinimumHeightTrees().new Solution();
        // TO TEST
        int n = 6;
        int[][] edges = {{3,0},{3,1},{3,2},{3,4},{5,4}};
        List<Integer> res = sol.findMinHeightTrees(n, edges);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (edges == null || edges.length == 0 || edges[0] == null || edges[0].length == 0) {
            res.add(0);
            return res;
        }
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int[] degree = new int[n];
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
            graph.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            res.clear(); // 这里每次都清空res，直到保存最后的结果
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                res.add(cur); // 无脑加答案，只有最后一层的才matter
                Set<Integer> nexts = graph.get(cur);
                for (int next : nexts) {
                    degree[next]--; // 减掉一层再往下看
                    if (degree[next] == 1) {
                        queue.offer(next);
                    }
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}