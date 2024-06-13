//You are given an m x n grid where each cell can have one of three values: 
//
// 
// 0 representing an empty cell, 
// 1 representing a fresh orange, or 
// 2 representing a rotten orange. 
// 
//
// Every minute, any fresh orange that is 4-directionally adjacent to a rotten 
//orange becomes rotten. 
//
// Return the minimum number of minutes that must elapse until no cell has a 
//fresh orange. If this is impossible, return -1. 
//
// 
// Example 1: 
// 
// 
//Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
//Output: 4
// 
//
// Example 2: 
//
// 
//Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
//Output: -1
//Explanation: The orange in the bottom left corner (row 2, column 0) is never 
//rotten, because rotting only happens 4-directionally.
// 
//
// Example 3: 
//
// 
//Input: grid = [[0,2]]
//Output: 0
//Explanation: Since there are already no fresh oranges at minute 0, the answer 
//is just 0.
// 
//
// 
// Constraints: 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 10 
// grid[i][j] is 0, 1, or 2. 
// 
//
// Related Topics Array Breadth-First Search Matrix 👍 12303 👎 387

package leetcode.editor.en;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// 2024-02-20 22:14:51
// Jesse Yang
public class Leetcode0994RottingOranges{
    // Java: rotting-oranges
    public static void main(String[] args) {
        Solution sol = new Leetcode0994RottingOranges().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// BFS, T(m, n) = O(m * n), S(m,n) = O(m * n)
class Solution {
    public int orangesRotting(int[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }
        
        // general case
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        int count = 0; // count of oranges
        int badNum = 0; // count of bad oranges
        // add all rotten oranges to the queue
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 || grid[i][j] == 2) {
                    count++;
                }
                if (grid[i][j] == 2) {
                    queue.offer(i * cols + j);
                    visited.add(i * cols + j);
                    badNum++;
                }
            }
        }
        if(badNum == count) {
            return 0;
        }
        int[][] directions = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        
        // BFS
        int minutes = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                int r = cur / cols;
                int c = cur % cols;
                for (int[] dir : directions) {
                    int i = r + dir[0];
                    int j = c + dir[1];
                    if (i >= 0 && i < rows && j >= 0 && j < cols
                            && !visited.contains(i * cols + j) && grid[i][j] == 1) {
                        queue.offer(i * cols + j);
                        visited.add(i * cols + j);
                        badNum++;
                    }
                }
            }
            minutes++; //when every level finished, minutes++,
        }
        if (badNum == count) {
            return  minutes;
        } else {
            return -1;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}