//Given an m x n matrix of non-negative integers representing the height of each
// unit cell in a continent, the "Pacific ocean" touches the left and top edges of
// the matrix and the "Atlantic ocean" touches the right and bottom edges. 
//
// Water can only flow in four directions (up, down, left, or right) from a cell
// to another one with height equal or lower. 
//
// Find the list of grid coordinates where water can flow to both the Pacific an
//d Atlantic ocean. 
//
// Note: 
//
// 
// The order of returned grid coordinates does not matter. 
// Both m and n are less than 150. 
// 
//
// 
//
// Example: 
//
// 
//Given the following 5x5 matrix:
//
//  Pacific ~   ~   ~   ~   ~ 
//       ~  1   2   2   3  (5) *
//       ~  3   2   3  (4) (4) *
//       ~  2   4  (5)  3   1  *
//       ~ (6) (7)  1   4   5  *
//       ~ (5)  1   1   2   4  *
//          *   *   *   *   * Atlantic
//
//Return:
//
//[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with paren
//theses in above matrix).
// 
//
// 
// Related Topics Depth-first Search Breadth-first Search

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode0417PacificAtlanticWaterFlow {
	
	// Java: pacific-atlantic-water-flow
	public static void main(String[] args) {
		Solution sol = new Leetcode0417PacificAtlanticWaterFlow().new Solution();
		// TO TEST
		int[][] matrix = new int[][]{
				{3, 3, 3, 3, 3, 3},
				{3, 0, 3, 3, 0, 3},
				{3, 3, 3, 3, 3, 3},
		};
		List<List<Integer>> result = sol.pacificAtlantic(matrix);
		System.out.println(Arrays.deepToString(result.toArray()));
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
// bfs, T(m,n) = O(m,n), S(m, n) = O(m, n)
// 6 ms,击败了46.67% 的Java用户, 40.4 MB,击败了56.91% 的Java用户
/*
用bfs遍历一边能到达pacific的地方，对atlantic也一样，两个重复的地方就是所求的坐标
 */
class Solution {
	
	private final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public List<List<Integer>> pacificAtlantic(int[][] matrix) {
		List<List<Integer>> res = new ArrayList<>();
		// corner case
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return res;
		}
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		boolean[][] pacific = new boolean[rows][cols];
		boolean[][] atlantic = new boolean[rows][cols];
		Queue<Integer> queue = new LinkedList<>();
		
		int row;
		int col = 0; // column
		for (row = 0; row < rows; row++) {// left
			queue.offer(row * cols + col);
			pacific[row][col] = true;
		}
		row = 0;
		for (col = 1; col < cols; col++) {// top
			queue.offer(row * cols + col);
			pacific[row][col] = true;
		}
		bfs(queue, pacific, atlantic, matrix, res);
		
		col = cols - 1;
		for (row = 0; row < rows; row++) {//right
			queue.offer(row * cols + col);
			atlantic[row][col] = true;
		}
		row = rows - 1;
		for (col = 0; col < cols - 1; col++) {// bottom
			queue.offer(row * cols + col);
			atlantic[row][col] = true;
		}
		bfs(queue, atlantic, pacific, matrix, res);
		
		return res;
	}
	
	private void bfs(Queue<Integer> queue, boolean[][] self, boolean[][] other,
			int[][] matrix, List<List<Integer>> res) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			int row = cur / cols;
			int col = cur % cols;
			if (other[row][col]) {
				res.add(Arrays.asList(row, col));
			}
			
			for (int[] dir : DIRECTIONS) {
				int i = row + dir[0];
				int j = col + dir[1];
				if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[row][col] > matrix[i][j]) {
					continue;
				}
				
				if (!self[i][j]) {
					queue.offer(i * cols + j);
					self[i][j] = true;
				}
			}
		}
	}
	
}
//leetcode submit region end(Prohibit modification and deletion)

}