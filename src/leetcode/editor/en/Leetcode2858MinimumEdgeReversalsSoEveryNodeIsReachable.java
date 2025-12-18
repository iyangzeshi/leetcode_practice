/**
 * There is a simple directed graph with n nodes labeled from 0 to n - 1. The graph would form a
 * tree if its edges were bi-directional.
 * <p>
 * You are given an integer n and a 2D integer array edges, where edges[i] = [ui, vi] represents a
 * directed edge going from node ui to node vi.
 * <p>
 * An edge reversal changes the direction of an edge, i.e., a directed edge going from node ui to
 * node vi becomes a directed edge going from node vi to node ui.
 * <p>
 * For every node i in the range [0, n - 1], your task is to independently calculate the minimum
 * number of edge reversals required so it is possible to reach any other node starting from node i
 * through a sequence of directed edges.
 * <p>
 * Return an integer array answer, where answer[i] is the minimum number of edge reversals required
 * so it is possible to reach any other node starting from node i through a sequence of directed
 * edges.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * <p>
 * Input: n = 4, edges = [[2,0],[2,1],[1,3]] Output: [1,1,0,2] Explanation: The image above shows
 * the graph formed by the edges. For node 0: after reversing the edge [2,0], it is possible to
 * reach any other node starting from node 0. So, answer[0] = 1. For node 1: after reversing the
 * edge [2,1], it is possible to reach any other node starting from node 1. So, answer[1] = 1. For
 * node 2: it is already possible to reach any other node starting from node 2.
 * <p>
 * So, answer[2] = 0. For node 3: after reversing the edges [1,3] and [2,1], it is possible to reach
 * any other node starting from node 3. So, answer[3] = 2.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * <p>
 * <p>
 * Input: n = 3, edges = [[1,2],[2,0]] Output: [2,0,1] Explanation: The image above shows the graph
 * formed by the edges. For node 0: after reversing the edges [2,0] and [1,2], it is possible to
 * reach any other node starting from node 0. So, answer[0] = 2. For node 1: it is already possible
 * to reach any other node starting from node 1.
 * <p>
 * So, answer[1] = 0. For node 2: after reversing the edge [1, 2], it is possible to reach any other
 * node starting from node 2. So, answer[2] = 1.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 2 <= n <= 10⁵ edges.length == n - 1 edges[i].length == 2 0 <= ui == edges[i][0] < n 0 <= vi ==
 * edges[i][1] < n ui != vi The input is generated such that if the edges were bi-directional, the
 * graph would be a tree.
 * <p>
 * <p>
 * Related Topics Dynamic Programming Depth-First Search Breadth-First Search Graph 👍 383 👎 8
 */
package leetcode.editor.en;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

// 2025-12-02 22:39:45
// Jesse Yang
public class Leetcode2858MinimumEdgeReversalsSoEveryNodeIsReachable {
    
    // Java: minimum-edge-reversals-so-every-node-is-reachable
    public static void main(String[] args) {
        Solution sol = new Leetcode2858MinimumEdgeReversalsSoEveryNodeIsReachable().new Solution();
        // TO TEST
        
        System.out.println();
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
/*
核心想法：Tree + 重新选根（reroot DP）
把所有边看成无向树，然后在树上做两次 DFS：
第1次：以 0 为根，算出从 0 出发需要反转几条边（answer[0]）
第2次：在树上「换根」，根据父亲的答案推导孩子的答案。
assuming there are n nodes
T(n) = O(n), S(n) = O(n)
 */
class Solution {
    
    Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
    int[] res;
    
    public int[] minEdgeReversals(int n, int[][] edges) {
        graph.clear();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], x -> new HashMap<>()).put(edge[1], 0);
            graph.computeIfAbsent(edge[1], x -> new HashMap<>()).put(edge[0], 1);
        }
        
        res = new int[n];
        Arrays.fill(res, -1);
        dfsCount(0, new boolean[n], res);
        dfsReroot(0, new boolean[n],res);
        return res;
    }
    
    private void dfsReroot(int cur , boolean[] visited, int[] res) {
        visited[cur] = true;
        for (Map.Entry<Integer, Integer> entry : graph.get(cur).entrySet()) {
            int vertex = entry.getKey();
            int cost = entry.getValue();
            if (visited[vertex]) {
                continue;
            }
            // 对例子 edges = [[0,1],[2,0],[3,2]]:
            // 从 0 出发：
            // - 0 -> 1 的 w = 0，不增加反转
            // - 0 -> 2 的 w = 1，说明原始方向是 2 -> 0，需要反转一次
            if (cost == 0) { // cost from cur to vertex is 0, then cost from vertex to cur is 1
                res[vertex] = res[cur] + 1;
            } else { // cost == 1
                res[vertex] = res[cur] - 1;
            }
            dfsReroot(vertex, visited, res);
        }
    }
    
    // calculate cost from cur node to all unvisted node
    private int dfsCount(int cur, boolean[] visited, int[] res) {
        visited[cur] = true;
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : graph.get(cur).entrySet()) {
            int vertex = entry.getKey();
            int cost = entry.getValue();
            if (visited[vertex]) {
                continue;
            }
            // 对例子 edges = [[0,1],[2,0],[3,2]]:
            // 从 0 出发：
            // - 0 -> 1 的 w = 0，不增加反转
            // - 0 -> 2 的 w = 1，说明原始方向是 2 -> 0，需要反转一次
            sum += (cost + dfsCount(vertex, visited, res));
        }
        res[cur] = sum;
        return sum;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
}