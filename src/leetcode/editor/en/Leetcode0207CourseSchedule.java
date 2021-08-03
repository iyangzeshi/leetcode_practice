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

enum Status {
    INITIAL,
    PROCESSING,
    DONE
}

class Node{
    int label; // # of course
    List<Integer> precourses;
    Status status;
    public Node(int label){
        this.label = label;
        precourses = new ArrayList<>();
        status = Status.INITIAL;
    }
}

class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        Node[] coursesToPrerequisites = buildGraph(numCourses, prerequisites);

        for (int i = 0; i < numCourses; i++) {
            if (containCycle(coursesToPrerequisites, i)) {
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
        for (int[] prerequisite : prerequisites) {
            int prev = prerequisite[1];
            int cur = prerequisite[0];
            courses[prev].precourses.add(cur);
        }
        return courses;
    }

    private boolean containCycle(Node[] courses, int idx) {
        if (courses[idx].status == Status.DONE) {
            return false;
        }
        if (courses[idx].status == Status.PROCESSING) {
            return true;
        }
        courses[idx].status = Status.PROCESSING;
        for (int next : courses[idx].precourses) {
            if (containCycle(courses, next)) {
                return true;
            }
        }
        courses[idx].status = Status.DONE;
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: dfs with map
class Solution1 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //build graph
        Map<Integer, List<Integer>> courseToPrecourses = buildGraph(prerequisites);
        for (int i = 0; i < numCourses; i++) {
            int[] visited = new int[numCourses];
            if (containsCycle(courseToPrecourses, visited, i)) {
                return false;
            }
        }
        return true;
    }

    private Map<Integer, List<Integer>> buildGraph(int[][] prerequisites) {
        // corner case
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] connection: prerequisites) {
            int course = connection[0];
            int precourse = connection[1];
            List<Integer> precourses;
            if (graph.containsKey(course)) {
                precourses = graph.get(course);
            } else {
                precourses = new LinkedList<>();
            }
            precourses.add(precourse);
            graph.put(course, precourses);
        }
        return graph;
    }

    private boolean containsCycle(Map<Integer, List<Integer>> courseToPrecourses,
            int[] visited, int i) {
        if (visited[i] == 1) {
            return true;
        }
        if (visited[i] == 2) {
            return false;
        }
        visited[i] = 1;
        if (courseToPrecourses.containsKey(i)) {
            for (int next: courseToPrecourses.get(i)) {
                if (containsCycle(courseToPrecourses, visited, next)) {
                    return true;
                }
            }
        }
        visited[i] = 2;
        return false;
    }

}

// Solution 2: dfs with array

/*enum Status {
    INITIAL,
    PROCESSING,
    DONE
}

class Node{
    int label;
    List<Integer> precourses;
    Status status;
    public Node(int label){
        this.label = label;
        precourses = new ArrayList<>();
        status = Status.INITIAL;
    }
}*/

class Solution2 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        Node[] coursesToPrerequisites = buildGraph(numCourses, prerequisites);

        for (int i = 0; i < numCourses; i++) {
            if (isCycle(coursesToPrerequisites, i)) {
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
        for (int[] prerequisite : prerequisites) {
            int prev = prerequisite[1];
            int cur = prerequisite[0];
            courses[prev].precourses.add(cur);
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
        for (int next : courses[idx].precourses) {
            if (isCycle(courses, next)) {
                return true;
            }
        }
        courses[idx].status = Status.DONE;
        return false;
    }
}
}