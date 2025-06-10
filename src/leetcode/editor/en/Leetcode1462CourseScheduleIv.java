/**
There are a total of numCourses courses you have to take, labeled from 0 to 
numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, 
bi] indicates that you must take course ai first if you want to take course bi. 

 
 For example, the pair [0, 1] indicates that you have to take course 0 before 
you can take course 1. 
 

 Prerequisites can also be indirect. If course a is a prerequisite of course b, 
and course b is a prerequisite of course c, then course a is a prerequisite of 
course c. 

 You are also given an array queries where queries[j] = [uj, vj]. For the jᵗʰ 
query, you should answer whether course uj is a prerequisite of course vj or not. 


 Return a boolean array answer, where answer[j] is the answer to the jᵗʰ query. 


 
 Example 1: 
 
 
Input: numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
Output: [false,true]
Explanation: The pair [1, 0] indicates that you have to take course 1 before 
you can take course 0.
Course 0 is not a prerequisite of course 1, but the opposite is true.
 

 Example 2: 

 
Input: numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
Output: [false,false]
Explanation: There are no prerequisites, and each course is independent.
 

 Example 3: 
 
 
Input: numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,
2]]
Output: [true,true]
 

 
 Constraints: 

 
 2 <= numCourses <= 100 
 0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2) 
 prerequisites[i].length == 2 
 0 <= ai, bi <= numCourses - 1 
 ai != bi 
 All the pairs [ai, bi] are unique. 
 The prerequisites graph has no cycles. 
 1 <= queries.length <= 10⁴ 
 0 <= ui, vi <= numCourses - 1 
 ui != vi 
 

 Related Topics Depth-First Search Breadth-First Search Graph Topological Sort ?
? 2022 👎 88

*/
package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

// 2025-05-29 01:43:29
// Jesse Yang
public class Leetcode1462CourseScheduleIv{
    // Java: course-schedule-iv
    public static void main(String[] args) {
        Solution sol = new Leetcode1462CourseScheduleIv().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        Boolean[][] reachable = new Boolean[numCourses][numCourses];
        
        // 初始化：直接的前置课程
        for (int[] pre : prerequisites) {
            int from = pre[0];
            int to = pre[1];
            reachable[from][to] = true;
            reachable[to][from] = false;
        }
        
        // 回答查询
        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            int from = q[0];
            int to = q[1];
            reachableFromTo(from, to, reachable);
            res.add(reachable[from][to]);
        }
        
        return res;
    }
    
    private boolean reachableFromTo(int course1, int course2, Boolean[][] reachable) {
        // corner case
        if (course1 == course2) {
            reachable[course1][course2] = true;
            return true;
        }
        // base case
        if (reachable[course1][course2] != null) {
            return reachable[course1][course2];
        }
        
        // general case
        int len = reachable.length;
        for (int i = 0; i < len; i++) {
            if (reachable[course1][i].equals(true) && reachableFromTo(i, course2, reachable)) {
                reachable[course1][course2] = true;
                return true;
            }
        }
        reachable[course1][course2] = false;
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
/*
Solution 1:
 */

/*
Floyd-Warshall算法
 */
class Solution2 {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        boolean[][] reachable = new boolean[numCourses][numCourses];
        
        // 初始化：直接的前置课程
        for (int[] pre : prerequisites) {
            reachable[pre[0]][pre[1]] = true;
        }
        
        
        // Floyd-Warshall
        for (int k = 0; k < numCourses; k++) {
            for (int i = 0; i < numCourses; i++) {
                for (int j = 0; j < numCourses; j++) {
                    if (reachable[i][k] && reachable[k][j]) {
                        reachable[i][j] = true;
                    }
                }
            }
        }
        
        // 回答查询
        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            res.add(reachable[q[0]][q[1]]);
        }
        
        return res;
    }
}
}