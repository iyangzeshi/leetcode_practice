//There are a total of numCourses courses you have to take, labeled from 0 to nu
//mCourses-1. 
//
// Some courses may have prerequisites, for example to take course 0 you have to
// first take course 1, which is expressed as a pair: [0,1] 
//
// Given the total number of courses and a list of prerequisite pairs, is it pos
//sible for you to finish all courses? 
//
// 
// Example 1: 
//
// 
//Input: numCourses = 2, prerequisites = [[1,0]]
//Output: true
//Explanation: There are a total of 2 courses to take. 
//             To take course 1 you should have finished course 0. So it is poss
//ible.
// 
//
// Example 2: 
//
// 
//Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
//Output: false
//Explanation: There are a total of 2 courses to take. 
//             To take course 1 you should have finished course 0, and to take c
//ourse 0 you should
//             also have finished course 1. So it is impossible.
// 
//
// 
// Constraints: 
//
// 
// The input prerequisites is a graph represented by a list of edges, not adjace
//ncy matrices. Read more about how a graph is represented. 
// You may assume that there are no duplicate edges in the input prerequisites. 
//
// 1 <= numCourses <= 10^5 
// 
// Related Topics Depth-first Search Breadth-first Search Graph Topological Sort
// 
// 👍 4292 👎 185

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// 2020-09-06 17:50:53
// Zeshi Yang
public class Leetcode0207CourseSchedule{
    // Java: course-schedule
    public static void main(String[] args) {
        Solution sol = new Leetcode0207CourseSchedule().new Solution();
        // TO TEST
        int numCourses = 2;
        int[][] prerequeisites = {{1, 0}};
        boolean res = sol.canFinish(numCourses, prerequeisites);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 2: build graph and in-Degree check: T(v,e) = O(v + e), S(v, e) = O(v + e)
/**每次都选则没有先修课要求，或者自己已经满足先修课要求的课一次次往更高level的课选课 */

class Solution {
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        
        // step 1: build in-degree table
        // map: key: prerequisite, value: next course; and update inDegree
        int[] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> graph = buildGraph(prerequisites, inDegree);
        
        // step 2: BFS to taking course whose inDegree == 0
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) { // 没有先修课的课程
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            count++;
            if (graph.containsKey(cur)) {
                for (int next : graph.get(cur)) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }
        }
        return count == numCourses;
    }
    
    private Map<Integer, List<Integer>> buildGraph(int[][] prevs, int[] inDegree) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] connection : prevs) {
            int cur = connection[0];
            int prev = connection[1];
            inDegree[cur]++;
            graph.computeIfAbsent(prev, k -> new LinkedList<>()).add(cur);
        }
        
        return graph;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1_1: dfs build graph(map), topological sort, T(e, v) = O(e + v), S(e ,v) = O(e + v)
/*
use dfs with map to build graph(map)
and use topological sort to check the cycle
visited[] array to record whether Status
 */
class Solution1_1 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //build graph
        Map<Integer, List<Integer>> curToPrev = buildGraph(prerequisites);
        for (int i = 0; i < numCourses; i++) {
            int[] visited = new int[numCourses]; // 0 - not visited, 1 - visiting, 2 visited
            if (containsCycle(curToPrev, visited, i)) {
                return false;
            }
        }
        return true;
    }

    private Map<Integer, List<Integer>> buildGraph(int[][] prevs) {
        // corner case
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] connection: prevs) {
            int cur = connection[0];
            int prev = connection[1];
            List<Integer> prevCourses = graph.computeIfAbsent(cur, k-> new LinkedList<>());
            prevCourses.add(prev);
        }
        return graph;
    }

    private boolean containsCycle(Map<Integer, List<Integer>> curToPrev, int[] visited, int i) {
        if (visited[i] == 1) {
            return true;
        }
        if (visited[i] == 2) {
            return false;
        }
        visited[i] = 1;
        if (curToPrev.containsKey(i)) {
            for (int next: curToPrev.get(i)) {
                if (containsCycle(curToPrev, visited, next)) {
                    return true;
                }
            }
        }
        visited[i] = 2;
        return false;
    }

}

// Solution 1_2: dfs build graph(array), topological sort, T(e, v) = O(e + v), S(e ,v) = O(e + v)
/*
use dfs with map to build graph(Node Array)
and use topological sort to check the cycle
enum Status to record whether Status
 */
enum Status {
    INITIAL,
    PROCESSING,
    DONE
}

class Solution1_2 {
    
    class Node{
        int label; // # of course
        List<Integer> preCourses;
        Status status;
        public Node(int label){
            this.label = label;
            preCourses = new ArrayList<>();
            status = Status.INITIAL;
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        Node[] curToPrevs = buildGraph(numCourses, prerequisites);

        for (int i = 0; i < numCourses; i++) {
            if (isCycle(curToPrevs, i)) {
                return false;
            }
        }
        return true;
    }

    private Node[] buildGraph(int numCourses, int[][] prerequisites) {
        Node[] courses = new Node[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new Node(i);
        }
        for (int[] connect : prerequisites) {
            int prev = connect[1];
            int cur = connect[0];
            courses[prev].preCourses.add(cur);
        }
        return courses;
    }

    private boolean isCycle(Node[] courses, int idx) {
        if (courses[idx].status == Status.DONE) {
            return false;
        }
        if (courses[idx].status == Status.PROCESSING) {
            return true;
        }
        courses[idx].status = Status.PROCESSING;
        for (int next : courses[idx].preCourses) {
            if (isCycle(courses, next)) {
                return true;
            }
        }
        courses[idx].status = Status.DONE;
        return false;
    }
}

// Solution 2: build graph and in-Degree check: T(v,e) = O(v + e), S(v, e) = O(v + e)
/**每次都选则没有先修课要求，或者自己已经满足先修课要求的课一次次往更高level的课选课 */
class Solution2 {
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0] == null
                || prerequisites[0].length == 0) {
            return true;
        }
        
        // step 1: build in-degree table
        // map: key: prerequisite, value: next course; and update inDegree
        int[] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> graph = buildGraph(prerequisites, inDegree);
        
        // step 2: BFS to taking course whose inDegree == 0
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) { // 没有先修课的课程
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            count++;
            if (graph.containsKey(cur)) {
                for (int next : graph.get(cur)) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }
        }
        return count == numCourses;
    }
    
    private Map<Integer, List<Integer>> buildGraph(int[][] prevs, int[] inDegree) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] connection : prevs) {
            int cur = connection[0];
            int prev = connection[1];
            inDegree[cur]++;
            graph.computeIfAbsent(prev, k -> new LinkedList<>()).add(cur);
        }
        
        return graph;
    }
    
}
}