//Given a 2d grid map of '1's (land) and '0's (water), count the number of islan
//ds. An island is surrounded by water and is formed by connecting adjacent lands 
//horizontally or vertically. You may assume all four edges of the grid are all su
//rrounded by water. 
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

// 2020-07-26 13:32:55
// Zeshi Yang
public class Leetcode0200NumberOfIslands{
    // Java: number-of-islands
    public static void main(String[] args) {
        Solution sol = new Leetcode0200NumberOfIslands().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
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
//leetcode submit region end(Prohibit modification and deletion)

}