//There are a total of n courses you have to take labelled from 0 to n - 1. 
//
// Some courses may have prerequisites, for example, if prerequisites[i] = [ai, 
//bi] this means you must take the course bi before the course ai. 
//
// Given the total number of courses numCourses and a list of the prerequisite p
//airs, return the ordering of courses you should take to finish all courses. 
//
// If there are many valid answers, return any of them. If it is impossible to f
//inish all courses, return an empty array. 
//
// 
// Example 1: 
//
// 
//Input: numCourses = 2, prerequisites = [[1,0]]
//Output: [0,1]
//Explanation: There are a total of 2 courses to take. To take course 1 you shou
//ld have finished course 0. So the correct course order is [0,1].
// 
//
// Example 2: 
//
// 
//Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//Output: [0,2,1,3]
//Explanation: There are a total of 4 courses to take. To take course 3 you shou
//ld have finished both courses 1 and 2. Both courses 1 and 2 should be taken afte
//r you finished course 0.
//So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3
//].
// 
//
// Example 3: 
//
// 
//Input: numCourses = 1, prerequisites = []
//Output: [0]
// 
//
// 
// Constraints: 
//
// 
// 1 <= numCourses <= 2000 
// 0 <= prerequisites.length <= numCourses * (numCourses - 1) 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// ai != bi 
// All the pairs [ai, bi] are distinct. 
// 
// Related Topics Depth-first Search Breadth-first Search Graph Topological Sort
// 
// 👍 2617 👎 140

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;

// 2020-09-07 00:37:45
// Zeshi Yang

/**
 * There are total n courses you have to take labelled from 0 to n - 1. Given the total number of
 * courses and a list of prerequisite pairs, return the ordering of courses you should take to
 * finish all courses.
 */
public class Leetcode0210CourseScheduleIi {
	
	// Java: course-schedule-ii
	public static void main(String[] args) {
		Solution sol = new Leetcode0210CourseScheduleIi().new Solution();
		// TO TEST
		int numCourses = 2;
		int[][] prerequeisites = {{1, 0}};
		int[] res = sol.findOrder(numCourses, prerequeisites);
		System.out.println(Arrays.toString(res));
	}
//leetcode submit region begin(Prohibit modification and deletion)
// in-degree写法
class Solution {
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //init
        int[] inDegree = new int[numCourses];
        
        // step 1: build in-degree table
        // map: key -> value, prerequisites -> next course, update inDegree
        Map<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites, inDegree);
        
        // step 2: put points of inDegree == 0 into the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) { // 没有先修课的课程
                queue.offer(i);
            }
        }
        int count = 0; // 查环用
        int[] res = new int[numCourses]; // topologicalOrder
        
        // BFS
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res[count++] = cur;
            if (graph.containsKey(cur)) { // else this course is not prerequisites of any other
                for (int course : graph.get(cur)) {
                    inDegree[course]--;
                    if (inDegree[course] == 0) {
                        queue.offer(course);
                    }
                }
            }
        }
        
        return count == numCourses ? res : new int[0];
    }
    
    private Map<Integer, List<Integer>> buildGraph(int numCourses, int[][] prerequisites,
            int[] inDegree) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int prev = prerequisite[1];
            int cur = prerequisite[0];
            inDegree[cur]++;
            map.computeIfAbsent(prev, k -> new LinkedList<>()).add(cur);
        }
        return map;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: DFS，检测环并且建图, T(v,e) = O(v + e), S(v, e) = O(v + e)
// 3 ms,击败了93.93% 的Java用户, 40.3 MB,击败了51.13% 的Java用户
enum Status {
    INITIAL,
    PROCESSING,
    DONE
}

class Solution1 {
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        LinkedList<Integer> res = new LinkedList<>();// topologicalOrder
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0] == null
                || prerequisites[0].length == 0) {
            int[] ans = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                ans[i] = i;
            }
            return ans;
        }
        Node[] coursesToPrerequisites = buildGraph(numCourses, prerequisites);
        
        for (int i = 0; i < numCourses; i++) {
            if (containCycle(coursesToPrerequisites, i, res)) {
                return new int[0];
            }
        }
        return ListToArray(res);
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
    
    private boolean containCycle(Node[] courses, int idx, LinkedList<Integer> res) {
        if (courses[idx].status == Status.DONE) {
            return false;
        }
        if (courses[idx].status == Status.PROCESSING) {
            return true;
        }
        courses[idx].status = Status.PROCESSING;
        // res.add(idx); // part 1
        for (int next : courses[idx].precourses) {
            if (containCycle(courses, next, res)) {
                return true;
            }
        }
        courses[idx].status = Status.DONE;
        res.addFirst(idx); // part2
        /*
        必须用part2，不能用part1
        因为是一个图，所有的，比如C依赖于A和B，
        part2可以实现，所有A和B都必须在C之前加入
        part1不能，只能实现A或者B有一个在C之前，剩下的不能保证放在C之前
         */
        return false;
    }
    
    private int[] ListToArray(List<Integer> list) {
        int len = list.size();
        int[] res = new int[len];
        ListIterator<Integer> iter = list.listIterator();
        for (int i = 0; i < len; i++) {
            res[i] = iter.next();
        }
        return res;
    }
    
    class Node {
        
        int label;
        List<Integer> precourses;
        Status status;
        
        public Node(int label) {
            this.label = label;
            precourses = new ArrayList<>();
            status = Status.INITIAL;
        }
    }
    
}

// Solution 2: in-Degree
// 5 ms,击败了67.96% 的Java用户, 39.5 MB,击败了96.71% 的Java用户
/**
 * 每次都选则没有先修课要求，或者自己已经满足先修课要求的课一次次往更高level的课选课
 */
class Solution2 {
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //init
        int[] inDegree = new int[numCourses];
        
        // step 1: build in-degree table
        // map: key -> value, prerequisites -> next course, update inDegree
        Map<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites, inDegree);
        
        // step 2: put points of inDegree == 0 into the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) { // 没有先修课的课程
                queue.offer(i);
            }
        }
        int count = 0; // 查环用
        int[] res = new int[numCourses]; // topologicalOrder
        
        // BFS
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res[count++] = cur;
            if (graph.containsKey(cur)) { // else this course is not prerequisites of any other
                for (int course : graph.get(cur)) {
                    inDegree[course]--;
                    if (inDegree[course] == 0) {
                        queue.offer(course);
                    }
                }
            }
        }
        
        return count == numCourses ? res : new int[0];
    }
    
    private Map<Integer, List<Integer>> buildGraph(int numCourses, int[][] prerequisites,
            int[] inDegree) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int prev = prerequisite[1];
            int cur = prerequisite[0];
            inDegree[cur]++;
            map.computeIfAbsent(prev, k -> new LinkedList<>()).add(cur);
        }
        return map;
    }
    
}

}