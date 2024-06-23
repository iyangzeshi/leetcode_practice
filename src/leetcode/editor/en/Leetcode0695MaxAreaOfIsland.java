//Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (r
//epresenting land) connected 4-directionally (horizontal or vertical.) You may as
//sume all four edges of the grid are surrounded by water. 
//
// Find the maximum area of an island in the given 2D array. (If there is no isl
//and, the maximum area is 0.) 
//
// Example 1: 
//
// 
//[[0,0,1,0,0,0,0,1,0,0,0,0,0],
// [0,0,0,0,0,0,0,1,1,1,0,0,0],
// [0,1,1,0,1,0,0,0,0,0,0,0,0],
// [0,1,0,0,1,1,0,0,1,0,1,0,0],
// [0,1,0,0,1,1,0,0,1,1,1,0,0],
// [0,0,0,0,0,0,0,0,0,0,1,0,0],
// [0,0,0,0,0,0,0,1,1,1,0,0,0],
// [0,0,0,0,0,0,0,1,1,0,0,0,0]]
// 
//Given the above grid, return 6. Note the answer is not 11, because the island 
//must be connected 4-directionally.
//
// Example 2: 
//
// 
//[[0,0,0,0,0,0,0,0]] 
//Given the above grid, return 0.
//
// Note: The length of each dimension in the given grid does not exceed 50. 
// Related Topics Array Depth-first Search 
// 👍 2110 👎 83

package leetcode.editor.en;

import java.util.*;
// 2020-08-27 21:56:16
// Jesse Yang
public class Leetcode0695MaxAreaOfIsland {

    // Java: max-area-of-island
    public static void main(String[] args) {
        Solution sol = new Leetcode0695MaxAreaOfIsland().new Solution();
        // TO TEST
        int[][] grid = {{0, 1}};
        int res = sol.maxAreaOfIsland(grid);
        System.out.println(res);
    }

//leetcode submit region begin(Prohibit modification and deletion)
/*
Solution 1: DFS, T(m,n) = O(m,n), S(m,n) = O(m,n)
用DFS计算每个岛的面积，然后求他们中间的最大值
 */
class Solution {

    private final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public int maxAreaOfIsland(int[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int maxArea = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    int[] area = new int[1];
                    doDFSsearch(grid, i, j, area);
                    maxArea = Math.max(maxArea, area[0]);
                }
            }
        }
        return maxArea;
    }
    
    private void doDFSsearch(int[][] grid, int row, int col, int[] area) {
        //base case - failure case
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length
                || grid[row][col] == 0 || grid[row][col] == 2) {
            return;
        }
        
        grid[row][col] = 2;
        area[0]++;
        
        //general case
        for (int[] direction: DIRECTIONS) {
            int r = row + direction[0];
            int c = col + direction[1];
            doDFSsearch(grid, r, c, area);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
/*
Solution 1: DFS, T(m,n) = O(m,n), S(m,n) = O(m,n)
用DFS计算每个岛的面积，然后求他们中间的最大值
 */
class Solution1 {
    
    private final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    
    public int maxAreaOfIsland(int[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        int maxArea = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    int[] area = new int[1];
                    doDFSsearch(grid, i, j, area);
                    maxArea = Math.max(maxArea, area[0]);
                }
            }
        }
        return maxArea;
    }
    
    private void doDFSsearch(int[][] grid, int row, int col, int[] area) {
        //base case - failure case
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length
                || grid[row][col] == 0 || grid[row][col] == 2) {
            return;
        }
        
        grid[row][col] = 2;
        area[0]++;
        
        //general case
        for (int[] direction: DIRECTIONS) {
            int r = row + direction[0];
            int c = col + direction[1];
            doDFSsearch(grid, r, c, area);
        }
    }
    
}

/*
Solution 2: BFS, T(m,n) = O(m,n), S(m,n) = O(m,n)
用BFS计算每个岛的面积，然后求他们中间的最大值
 */
class Solution2 {
    
    private final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        Set<Integer> visited = new HashSet<>();
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited.contains(i * cols + j)) {
                    int area = bfs(i, j, visited, grid);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }
    
    private int bfs(int i, int j, Set<Integer> visited, int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        visited.add(i * cols + j);
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i * cols + j);
        
        int area = 1;
        while (!queue.isEmpty()) {
            int index = queue.poll();
            int row = index / cols;
            int col = index % cols;
            for (int[] dir : DIRECTIONS) {
                int r = row + dir[0];
                int c = col + dir[1];
                int neighbor = r * cols + c;
                if (r >= 0 && r < rows && c >= 0 && c < cols &&
                        grid[r][c] == 1 && !visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                    area++;
                }
            }
        }
        return area;
    }
    
}
}