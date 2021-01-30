//You are given row x col grid representing a map where grid[i][j] = 1 represent
//s land and grid[i][j] = 0 represents water. 
//
// Grid cells are connected horizontally/vertically (not diagonally). The grid i
//s completely surrounded by water, and there is exactly one island (i.e., one or 
//more connected land cells). 
//
// The island doesn't have "lakes", meaning the water inside isn't connected to 
//the water around the island. One cell is a square with side length 1. The grid i
//s rectangular, width and height don't exceed 100. Determine the perimeter of the
// island. 
//
// 
// Example 1: 
//
// 
//Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
//Output: 16
//Explanation: The perimeter is the 16 yellow stripes in the image above.
// 
//
// Example 2: 
//
// 
//Input: grid = [[1]]
//Output: 4
// 
//
// Example 3: 
//
// 
//Input: grid = [[1,0]]
//Output: 4
// 
//
// 
// Constraints: 
//
// 
// row == grid.length 
// col == grid[i].length 
// 1 <= row, col <= 100 
// grid[i][j] is 0 or 1. 
// 
// Related Topics Hash Table 
// 👍 2220 👎 125

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;

// 2020-09-12 14:05:59
// Zeshi Yang
public class Leetcode0463IslandPerimeter{
    // Java: island-perimeter
    public static void main(String[] args) {
        Solution sol = new Leetcode0463IslandPerimeter().new Solution();
        // TO TEST
        int[][] grid = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        int perimeter = sol.islandPerimeter(grid);
        System.out.println(perimeter);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    private int[][] DIRECTIONS = {{-1, 0}, {0, -1}};
    
    public int islandPerimeter(int[][] grid) {
        int prm = 0; // perimeter
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return prm;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    for (int[] dir: DIRECTIONS) {
                        int i = row + dir[0];
                        int j = col + dir[1];
                        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == 0) {
                            prm++;
                        }
                    }
                }
            }
        }
        return prm * 2;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: bfs, T(m,n) = O(m * n), S(m, n) = O(m, n)
// 14 ms,击败了8.55% 的Java用户, 40.1 MB,击败了76.10% 的Java用户
/*
BFS遍历land，对于每个land,往上下左右4个方向走，
    如果还是陆地的话就不用管，
    如果不是陆地的话或者是matrix的边界：那就要把perimeter数+1
最后返回perimeter
 */
class Solution1 {
    
    private int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    return bfs(i, j, grid);
                }
            }
        }
        return 0;
    }
    
    private int bfs(int row, int col, int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        visited[row][col] = true;
        Queue<Integer> queue = new LinkedList<>();
        int index = row * cols + col;
        queue.offer(index);
        int perimeter = 0;
        while (!queue.isEmpty()) {
            index = queue.poll();
            row = index / cols;
            col = index % cols;
            for (int[] dir : DIRECTIONS) {
                int r = row + dir[0]; // neighbor row
                int c = col + dir[1]; // neighbor col
                if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == 1) {
                    if (!visited[r][c]) {
                        visited[r][c] = true;
                        int idx = r * cols + c;
                        queue.offer(idx);
                    }
                } else {
                    perimeter++;
                }
            }
        }
        return perimeter;
    }
    
}

// Solution 2: 两个for循环遍历所有的点，看这个点的与水的接壤长度
// Solution 2_1: 每个land走4个方向
// T(m,n) = O(m * n), S(m, n) = O(1)
// 7 ms,击败了46.91% 的Java用户, 39.9 MB,击败了90.74% 的Java用户
/*
2层for循环遍历land，对于每个land,往上下左右4个方向走，
    如果还是陆地的话就不用管，
    如果不是陆地的话或者是matrix的边界：那就要把perimeter数+1
最后返回perimeter
 */
class Solution2_1 {
    
    private int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int islandPerimeter(int[][] grid) {
        int prm = 0; // perimeter
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return prm;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    for (int[] dir: DIRECTIONS) {
                        int i = row + dir[0];
                        int j = col + dir[1];
                        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == 0) {
                            prm++;
                        }
                    }
                }
            }
        }
        return prm;
    }
    
}

// Solution 2_1: 每个land走2个方向，因为对于这种图形而言，上面的边长合 = 下面的边长合，左边的~ = 右边的~
// T(m,n) = O(m * n), S(m, n) = O(1)
// 6 ms,击败了60.09% 的Java用户, 39.7 MB,击败了97.74% 的Java用户
/*
2层for循环遍历land，对于每个land,往上下左右4个方向走，
    如果还是陆地的话就不用管，
    如果不是陆地的话或者是matrix的边界：那就要把perimeter数+1
最后返回perimeter
 */
class Solution2_2 {
    
    private int[][] DIRECTIONS = {{-1, 0}, {0, -1}};
    
    public int islandPerimeter(int[][] grid) {
        int prm = 0; // perimeter
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return prm;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    for (int[] dir: DIRECTIONS) {
                        int i = row + dir[0];
                        int j = col + dir[1];
                        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == 0) {
                            prm++;
                        }
                    }
                }
            }
        }
        return prm * 2;
    }
    
}
}