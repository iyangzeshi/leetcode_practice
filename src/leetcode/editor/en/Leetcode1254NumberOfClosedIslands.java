//Given a 2D grid consists of 0s (land) and 1s (water). An island is a maximal 4
//-directionally connected group of 0s and a closed island is an island totally (a
//ll left, top, right, bottom) surrounded by 1s.
//
// Return the number of closed islands.
//
//
// Example 1:
//
//
//
//
//Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,
//0,1,0,1],[1,1,1,1,1,1,1,0]]
//Output: 2
//Explanation:
//Islands in gray are closed because they are completely surrounded by water (gr
//oup of 1s).
//
// Example 2:
//
//
//
//
//Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
//Output: 1
//
//
// Example 3:
//
//
//Input: grid = [[1,1,1,1,1,1,1],
//               [1,0,0,0,0,0,1],
//               [1,0,1,1,1,0,1],
//               [1,0,1,0,1,0,1],
//               [1,0,1,1,1,0,1],
//               [1,0,0,0,0,0,1],
//               [1,1,1,1,1,1,1]]
//Output: 2
//
//
//
// Constraints:
//
//
// 1 <= grid.length, grid[0].length <= 100
// 0 <= grid[i][j] <=1
//
// Related Topics Depth-first Search
// 👍 603 👎 21

package leetcode.editor.en;

// 2020-12-23 23:42:39
// Jesse Yang
public class Leetcode1254NumberOfClosedIslands{
    // Java: number-of-closed-islands
    public static void main(String[] args) {
        Solution sol = new Leetcode1254NumberOfClosedIslands().new Solution();
        // TO TEST
        
        System.out.println();
    }
    
    // 面试的时候，这个不用写
    private void restoreLand(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    grid[i][j] = 0;
                }
            }
        }
    }
//leetcode submit region begin(Prohibit modification and deletion)

class Solution {
    
    private final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    public int closedIsland(int[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 0 && dfsSearch(i, j, grid)) {
                    count++;
                }
            }
        }
        // restoreLand(grid); // can be deleted if interviewer does not require
        return count;
    }
    
    // return if this island is closed island (not connected with border)
    private boolean dfsSearch(int row, int col, int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return false;
        }
        if (grid[row][col] == 1 || grid[row][col] == 2) {
            return true;
        }
        grid[row][col] = 2; // mark 2 as visited land
        boolean isClosedIsland = true;
        for (int[] dir: DIRECTIONS) {
            int i = row + dir[0];
            int j = col + dir[1];
            if (!dfsSearch(i, j, grid)) {
                isClosedIsland = false;
            }
        }
        return isClosedIsland;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: T(m, n) = O(m * n), S(m, n) = O(1)
// 2 ms,击败了69.87% 的Java用户, 39.7 MB,击败了30.72% 的Java用户
// 0 is land, 1 is water, island need to be closely surrounded by water.
/*
turn circumference land and their connected land into 2, no count change
和边界接壤的陆地，不算island
then traverse the grid, if we meet the land (0), turn its connected land to 2 and count++
 */
class Solution1 {
    
    private final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    public int closedIsland(int[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        traverseCircumference(grid);
        int count = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 0) {
                    dfsSearch(i, j, grid);
                    count++;
                }
            }
        }
        // restoreLand(grid); // can be deleted if interviewer does not require
        return count;
    }
    
    private void traverseCircumference(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int j = 0; j < cols; j++) {
            if (grid[0][j] == 0) {
                dfsSearch(0, j, grid);
            }
            if (grid[rows - 1][j] == 0) {
                dfsSearch(rows - 1, j, grid);
            }
        }
        for (int i = 0; i < rows; i++) {
            if (grid[i][0] == 0) {
                dfsSearch(i, 0, grid);
            }
            if (grid[i][cols - 1] == 0) {
                dfsSearch(i, cols - 1, grid);
            }
        }
    }
    
    private void dfsSearch(int row, int col, int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] == 2 || grid[row][col] == 1) {
            return;
        }
        grid[row][col] = 2; // mark 2 as visited land
        for (int[] dir: DIRECTIONS) {
            int i = row + dir[0];
            int j = col + dir[1];
            dfsSearch(i, j, grid);
        }
    }
    
}

// Solution 2: T(m, n) = O(m * n), S(m, n) = O(1)
// 2 ms,击败了70.24% 的Java用户, 39.3 MB,击败了80.98% 的Java用户
// 0 is land, 1 is water, island need to be closely surrounded by water.
/*
遍历land的时候，看整个connected island有没有连接到边界，
如果是连接到边界的话，就不是closed island
 */
class Solution2 {
    
    private final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    public int closedIsland(int[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 0 && dfsSearch(i, j, grid)) {
                    count++;
                }
            }
        }
        // restoreLand(grid); // can be deleted if interviewer does not require
        return count;
    }
    
    // return if this island is closed island (not connected with border)
    private boolean dfsSearch(int row, int col, int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return false;
        }
        if (grid[row][col] == 1 || grid[row][col] == 2) {
            return true;
        }
        grid[row][col] = 2; // mark 2 as visited land
        boolean isClosedIsland = true;
        for (int[] dir: DIRECTIONS) {
            int i = row + dir[0];
            int j = col + dir[1];
            if (!dfsSearch(i, j, grid)) {
                isClosedIsland = false;
            }
        }
        return isClosedIsland;
    }
    
}
}