//Given a list of airline tickets represented by pairs of departure and arrival 
//airports [from, to], reconstruct the itinerary in order. All of the tickets belo
//ng to a man who departs from JFK. Thus, the itinerary must begin with JFK. 
//
// Note: 
//
// 
// If there are multiple valid itineraries, you should return the itinerary that
// has the smallest lexical order when read as a single string. For example, the i
//tinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"]. 
// All airports are represented by three capital letters (IATA code). 
// You may assume all tickets form at least one valid itinerary. 
// One must use all the tickets once and only once. 
// 
//
// Example 1: 
//
// 
//Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
//Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
// 
//
// Example 2: 
//
// 
//Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//
//Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
//Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL"
//,"SFO"].
//             But it is larger in lexical order.
// 
// Related Topics Depth-first Search Graph 
// 👍 1894 👎 985

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// 2020-07-19 23:01:09
// Zeshi Yang
public class Leetcode0332ReconstructItinerary {
    
    // Java: reconstruct-itinerary
    public static void main(String[] args) {
        Solution sol = new Leetcode0332ReconstructItinerary().new Solution();
        // TO TEST
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("JFK", "KUL"));
        tickets.add(Arrays.asList("JFK", "NRT"));
        tickets.add(Arrays.asList("NRT", "JFK"));
        List<String> res = sol.findItinerary(tickets);
        System.out.println(res);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // origin -> list of destinations
    HashMap<String, LinkedList<String>> flightMap = new HashMap<>();
    LinkedList<String> result = null;
    
    public List<String> findItinerary(List<List<String>> tickets) {
        // Step 1). build the graph first
        for(List<String> ticket : tickets) {
            String origin = ticket.get(0);
            String dest = ticket.get(1);
            if (this.flightMap.containsKey(origin)) {
                LinkedList<String> destList = this.flightMap.get(origin);
                destList.add(dest);
            } else {
                LinkedList<String> destList = new LinkedList<String>();
                destList.add(dest);
                this.flightMap.put(origin, destList);
            }
        }
        
        // Step 2). order the destinations
        this.flightMap.forEach((key, value) -> Collections.sort(value));
        
        this.result = new LinkedList<String>();
        // Step 3). post-order DFS
        this.DFS("JFK");
        return this.result;
    }
    
    protected void DFS(String origin) {
        // Visit all the outgoing edges first.
        if (this.flightMap.containsKey(origin)) {
            LinkedList<String> destList = this.flightMap.get(origin);
            while (!destList.isEmpty()) {
                // while we visit the edge, we trim it off from graph.
                String dest = destList.pollFirst();
                DFS(dest);
            }
        }
        // add the airport to the head of the itinerary
        this.result.offerFirst(origin);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1_1: pre-order DFS
/*
 assuming： e is the number of total flights，
              d is the max number of flights from the airport
              v is the number of airports
 time complexity : O(e^d), Space complexity: O(v +ｅ)
*/
/*
好懂，但是code长
尝试所有的路线，pre order dfs，back tracking,
如果当前这个点能走的话，而且能遍历完所有城市的话，就加到result里面；
否则回到当前这个点，并且剪枝prune
 */
class Solution1_1 {
    
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new LinkedList<>();
        if (tickets == null || tickets.size() == 0 || tickets.get(0) == null) {
            return res;
        }
        int flightsCount = tickets.size();
        Map<String, List<String>> graph = buildGraph(tickets);
        Map<String, boolean[]> visitedEdges = initialVisitedEdges(graph);
        search("JFK", flightsCount, new LinkedList<>(), res, graph, visitedEdges);
        return res;
    }
    
    private Map<String, List<String>> buildGraph(List<List<String>> tickets) {
        HashMap<String, List<String>> graph = new HashMap<>();
        for (List<String> pair : tickets) {
            String from = pair.get(0);
            String to = pair.get(1);
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        }
        for (List<String> neighbors: graph.values()) {
            Collections.sort(neighbors);
        }
        return graph;
    }
    
    private Map<String,boolean[]> initialVisitedEdges(Map<String, List<String>> graph) {
        Map<String, boolean[]> map = new HashMap<>();
        for(Map.Entry<String, List<String>> entry: graph.entrySet()) {
            String str = entry.getKey();
            List<String> neighbors = entry.getValue();
            map.put(str, new boolean[neighbors.size()]);
        }
        return map;
    }
    
    // search cur之后(包括），res里面的是cur开始的路径
    private boolean search(String cur, int flightsCount, List<String> route, List<String> res,
            Map<String, List<String>> graph, Map<String, boolean[]> visitedEdges) {
        route.add(cur);
        List<String> nexts = graph.get(cur);
        // base case - success case
        if (route.size() == flightsCount + 1) {
            res.addAll(route);
            return true;
        }
        boolean[] visited = visitedEdges.get(cur); // visited[i] is false - ith edge is not visited
        if (nexts != null) {
            for (int i = 0; i < nexts.size(); i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    if (search(nexts.get(i), flightsCount, route, res, graph, visitedEdges)) {
                        return true;
                    }
                    visited[i] = false;
                }
            }
        }
        route.remove(route.size() - 1);
        return false;
    }
    
}

// Solution 1_2:post order DFS + topological sort
/*
 assuming： e is the number of total flights，
              d is the max number of flights from the airport
              v is the number of airports
 time complexity : average: O(e * log(e/v)), worst case: O(e * log(e)), Space complexity: O(v +ｅ)
*/
/*
难懂，但是code短，而且效率高
按照字母序post order 遍历，相当于topological sort
 */
class Solution1_2 {
    
    public List<String> findItinerary(List<List<String>> tickets) {
        LinkedList<String> res = new LinkedList<>();
        if (tickets == null || tickets.size() == 0 || tickets.get(0) == null) {
            return res;
        }
        
        HashMap<String, PriorityQueue<String>> graph = buildGraph(tickets);
        search("JFK", graph, res);
        return res;
    }
    
    // average: O(e * log(e/v)), worst case: O(e * log(e))
    private HashMap<String, PriorityQueue<String>> buildGraph(List<List<String>> tickets) {
        HashMap<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> pair : tickets) {
            String from = pair.get(0);
            String to = pair.get(1);
            graph.computeIfAbsent(from, k -> new PriorityQueue<>()).offer(to);
        }
        return graph;
    }
    
    // search cur之后，res里面的是cur开始的路径
    private void search(String cur, HashMap<String, PriorityQueue<String>> graph,
            LinkedList<String> res) {
        PriorityQueue<String> nexts = graph.get(cur);
        // res.add(cur); // 这样写是错的，只能res.addFirst(cur)，而且放在最后面
        while (nexts != null && !nexts.isEmpty()) {
            String to = nexts.poll();
            search(to, graph, res);
        }
        res.addFirst(cur);
    }
    
}

}