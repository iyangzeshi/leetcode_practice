/**
Given an m x n grid. Each cell of the grid has a sign pointing to the next cell 
you should visit if you are currently in this cell. The sign of grid[i][j] can 
be: 

 
 1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][
j + 1]) 
 2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j 
- 1]) 
 3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j]) 

 4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j]) 

 

 Notice that there could be some signs on the cells of the grid that point 
outside the grid. 

 You will initially start at the upper left cell (0, 0). A valid path in the 
grid is a path that starts from the upper left cell (0, 0) and ends at the bottom-
right cell (m - 1, n - 1) following the signs on the grid. The valid path does 
not have to be the shortest. 

 You can modify the sign on a cell with cost = 1. You can modify the sign on a 
cell one time only. 

 Return the minimum cost to make the grid have at least one valid path. 

 
 Example 1: 
 
 
Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
Output: 3
Explanation: You will start at point (0, 0).
The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) 
change the arrow to down with cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0) 
change the arrow to down with cost = 1 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3) 
change the arrow to down with cost = 1 --> (3, 3)
The total cost = 3.
 

 Example 2: 
 
 
Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
Output: 0
Explanation: You can follow the path from (0, 0) to (2, 2).
 

 Example 3: 
 
 
Input: grid = [[1,2],[4,3]]
Output: 1
 

 
 Constraints: 

 
 m == grid.length 
 n == grid[i].length 
 1 <= m, n <= 100 
 1 <= grid[i][j] <= 4 
 

 Related Topics Array Breadth-First Search Graph Theory Heap (Priority Queue) 
Matrix Shortest Path 👍 2574 👎 35

*/
package leetcode.editor.en;

import java.util.Deque;
import java.util.LinkedList;

// 2026-01-16 20:06:18
// Jesse Yang
public class Leetcode1368MinimumCostToMakeAtLeastOneValidPathInAGrid{
    // Java: minimum-cost-to-make-at-least-one-valid-path-in-a-grid
    public static void main(String[] args) {
        Solution sol = new Leetcode1368MinimumCostToMakeAtLeastOneValidPathInAGrid().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    private final int[][] DIRS = {
        {0, 1},
        {0, -1},
        {1, 0},
        {-1, 0}
    };
    
    public int minCost(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }
        
        // general case
        int rows = grid.length;
        int cols = grid[0].length;
        // step 1: prefill dist with max value
        int[][] dist = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        
        // step 2: BFS
        Deque<int[]> deque = new LinkedList<>();
        dist[0][0] = 0;
        deque.offerFirst(new int[]{0, 0});
        
        
        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int row = cur[0];
            int col = cur[1];
            
            for (int d = 0; d < 4; d++) {
                int i = row + DIRS[d][0];
                int j = col + DIRS[d][1];
                if (i < 0 || i >= rows || j < 0 || j >= cols) {
                    continue;
                }
                // grid[row][col] == 1, d = 1: cost: 0 or 1
                int cost = (grid[row][col] == d + 1) ? 0 : 1;
                
                if (dist[row][col] + cost < dist[i][j]) {
                    dist[i][j] = dist[row][col] + cost;
                    if (cost == 0) {
                        deque.offerFirst(new int[]{i, j});
                    } else {
                        deque.offerLast(new int[]{i, j});
                    }
                }
            }
            return dist[rows - 1][cols - 1];
        }
        return rows;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
