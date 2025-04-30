//Given a 2d grid map of '1's (LAND) and '0's (WATER), count the number of islan
//ds. An island is surrounded by WATER and is formed by connecting adjacent lands
//horizontally or vertically. You may assume all four edges of the grid are all su
//rrounded by WATER.
//
// 
// Example 1: 
//
// 
//Input: grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//Output: 1
// 
//
// Example 2: 
//
// 
//Input: grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//Output: 3
// 
// Related Topics Depth-first Search Breadth-first Search Union Find 
// 👍 5793 👎 200

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;

// 2020-07-26 13:32:55
// Jesse Yang
public class Leetcode0200NumberOfIslands{
    // Java: number-of-islands
    public static void main(String[] args) {
        Solution sol = new Leetcode0200NumberOfIslands().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
Solution 2: BFS
step 1: for every island 1, mark all connected island as 2 (visited) by doing bfs search
step 2: after every new dfs search, add count of island by 1
T(m,n) = O(m*n), S(m,n) = O(min(m,n))
 */
class Solution {
    // solution : DFS
    private final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}}; // ↓←↑→

    public int numIslands(char[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    queue.offer(new int[]{i, j});
                    grid[i][j] = '2';
                    while (!queue.isEmpty()) {
                        int[] poll = queue.poll();
                        int row = poll[0];
                        int col = poll[1];
                        for (int[] dir : DIRECTIONS) {
                            int r = row + dir[0];
                            int c = col + dir[1];
                            if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == '1') {
                                queue.offer(new int[]{r, c});
                                grid[r][c] = '2';
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// 面试的时候，用BFS，因为空间复杂度会小一些
/*
Solution 1: DFS
step 1: for every island 1, mark all connected island as 2 (visited) by doing dfs search
step 2: after every new dfs search, add count of island by 1
T(m,n) = O(m*n), S(m,n) = O(m*n)
 */
class Solution1 {
    // solution : DFS
    private final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}}; // ↓←↑→
    
    public int numIslands(char[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    doDFSsearch(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    
    private void doDFSsearch(char[][] grid, int row, int col) {
        //base case
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length
                || grid[row][col] == '0' || grid[row][col] == '2') {
            return;
        }
        
        grid[row][col] = '2';
        
        //general case
        for (int[] direction: DIRECTIONS) {
            int r = row + direction[0];
            int c = col + direction[1];
            doDFSsearch(grid, r, c);
        }
    }
}

/*
Solution 2: BFS
step 1: for every island 1, mark all connected island as 2 (visited) by doing bfs search
step 2: after every new dfs search, add count of island by 1
T(m,n) = O(m*n), S(m,n) = O(min(m,n))
 */
class Solution2 {
    // solution : DFS
    private final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private final char LAND = '1';
    private final char WATER = '0';
    private final char VISITEDWATER = '2';
    
    public int numIslands(char[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        
        // BFS
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == LAND) {
                    count++;
                    queue.offer(new int[] {i, j});
                    grid[i][j] = WATER;
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        int row = cur[0];
                        int col = cur[1];
                        for (int[] dir: DIRECTIONS) {
                            int r = row + dir[0];
                            int c = col + dir[1];
                            if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == LAND) {
                                queue.offer(new int[] {r, c});
                                grid[r][c] = VISITEDWATER;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}
}