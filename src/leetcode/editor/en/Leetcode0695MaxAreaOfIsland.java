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
class Solution {

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
//leetcode submit region end(Prohibit modification and deletion)
}