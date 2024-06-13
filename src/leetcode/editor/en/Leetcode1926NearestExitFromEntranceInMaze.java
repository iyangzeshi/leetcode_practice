//You are given an m x n matrix maze (0-indexed) with empty cells (represented 
//as '.') and walls (represented as '+'). You are also given the entrance of the 
//maze, where entrance = [entrancerow, entrancecol] denotes the row and column of 
//the cell you are initially standing at. 
//
// In one step, you can move one cell up, down, left, or right. You cannot step 
//into a cell with a wall, and you cannot step outside the maze. Your goal is to 
//find the nearest exit from the entrance. An exit is defined as an empty cell 
//that is at the border of the maze. The entrance does not count as an exit. 
//
// Return the number of steps in the shortest path from the entrance to the 
//nearest exit, or -1 if no such path exists. 
//
// 
// Example 1: 
// 
// 
//Input: maze = [['+','+','.','+'],['.','.','.','+'],['+','+','+','.']], 
//entrance = [1,2]
//Output: 1
//Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
//Initially, you are at the entrance cell [1,2].
//- You can reach [1,0] by moving 2 steps left.
//- You can reach [0,2] by moving 1 step up.
//It is impossible to reach [2,3] from the entrance.
//Thus, the nearest exit is [0,2], which is 1 step away.
// 
//
// Example 2: 
// 
// 
//Input: maze = [['+','+','+'],['.','.','.'],['+','+','+']], entrance = [1,0]
//Output: 2
//Explanation: There is 1 exit in this maze at [1,2].
//[1,0] does not count as an exit since it is the entrance cell.
//Initially, you are at the entrance cell [1,0].
//- You can reach [1,2] by moving 2 steps right.
//Thus, the nearest exit is [1,2], which is 2 steps away.
// 
//
// Example 3: 
// 
// 
//Input: maze = [['.','+']], entrance = [0,0]
//Output: -1
//Explanation: There are no exits in this maze.
// 
//
// 
// Constraints: 
//
// 
// maze.length == m 
// maze[i].length == n 
// 1 <= m, n <= 100 
// maze[i][j] is either '.' or '+'. 
// entrance.length == 2 
// 0 <= entrancerow < m 
// 0 <= entrancecol < n 
// entrance will always be an empty cell. 
// 
//
// Related Topics Array Breadth-First Search Matrix 👍 2207 👎 92

package leetcode.editor.en;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// 2024-02-20 21:27:55
// Jesse Yang
public class Leetcode1926NearestExitFromEntranceInMaze{
    // Java: nearest-exit-from-entrance-in-maze
    public static void main(String[] args) {
        Solution sol = new Leetcode1926NearestExitFromEntranceInMaze().new Solution();
        // TO TEST
        char[][] maze = {{'+','+','+'},{'.','.','.'},{'+','+','+'}};
        int[] entrance = {1, 0};
        int res = sol.nearestExit(maze, entrance);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// BFS T(m, n) = O(m * n), S(m,n) = O(m * n)
/*
Using BFS to go steps by steps
maze:
{
{'+','+','-','+'},
{'.','.','.','+'},
{'+','+','+','.'}
}

entrance = {1, 2}

step 0: {1, 2}
step 1: {0, 2}, {1, 1}
step 2:
 */
class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        // corner case
        if (maze == null || maze.length == 0 || maze[0] == null || maze[0].length == 0) {
            return -1;
        }
        
        int rows = maze.length;
        int cols = maze[0].length;
        int row = entrance[0];
        int col = entrance[1];
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        //BFS
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(row * cols + col);
        visited.add(row * cols + col);
        
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            while (size-- > 0) {
                int cur = queue.poll();
                int r = cur / cols;
                int c = cur % cols;
                for (int[] dir : directions) {
                    int i = r + dir[0];
                    int j = c + dir[1];
                    if (i >= 0 && i < rows && j >= 0 && j < cols && maze[i][j] != '+'
                            && !visited.contains(i * cols + j)) {
                        queue.offer(i * cols + j);
                        visited.add(i * cols + j);
                        if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                            return steps;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}