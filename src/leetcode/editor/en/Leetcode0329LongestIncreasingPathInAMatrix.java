//Given an integer matrix, find the length of the longest increasing path. 
//
// From each cell, you can either move to four directions: left, right, up or do
//wn. You may NOT move diagonally or move outside of the boundary (i.e. wrap-aroun
//d is not allowed). 
//
// Example 1: 
//
// 
//Input: nums = 
//[
//  [9,9,4],
//  [6,6,8],
//  [2,1,1]
//] 
//Output: 4 
//Explanation: The longest increasing path is [1, 2, 6, 9].
// 
//
// Example 2: 
//
// 
//Input: nums = 
//[
//  [3,4,5],
//  [3,2,6],
//  [2,2,1]
//] 
//Output: 4 
//Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is
// not allowed.
// 
// Related Topics Depth-first Search Topological Sort Memoization

package leetcode.editor.en;

/**
 * @ClassName: Leetcode329LongestIncreasingPathInAMatrix
 * @Description:
 * @Author: Jesse Yang
 * @Date: 2020/06/30 周二 17:53
 */
public class Leetcode0329LongestIncreasingPathInAMatrix {
	
	// Java: longest-increasing-path-in-a-matrix
	public static void main(String[] args) {
		
		Solution sol = new Leetcode0329LongestIncreasingPathInAMatrix().new Solution();
		// TO TEST
		int[][] matrix = new int[][]{{7, 7, 5}, {2, 4, 6}, {8, 2, 0}};
		int res = sol.longestIncreasingPath(matrix);
		System.out.println(res);
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	// DFS with pruning
	private final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public int longestIncreasingPath(int[][] matrix) {
		
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return 0;
		}
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] mem = new int[rows][cols];
		int max = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				max = Math.max(max, dfs(matrix, i, j, null, mem));
			}
		}
		return max;
	}
	
	/**
	 * @param matrix given matrix
	 * @param i:      row index
	 * @param j:      col index
	 * @param preVal: previous number
	 * @param mem:    the memory to store the max lengh starting from matrix[i][j]
	 * @return: find the longest longest Increasing path starting from matrix[i][j]
	 */
	private int dfs(int[][] matrix, int i, int j, Integer preVal, int[][] mem) {
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		if (i < 0 || i >= rows || j < 0 || j >= cols || (preVal != null
				&& matrix[i][j] <= preVal)) {
			return 0;
		}
		// lookup DP form
		if (mem[i][j] > 0) {
			return mem[i][j];
		}
		int max = 0;
		for (int[] dir : DIRECTIONS) {
			int row = i + dir[0];
			int col = j + dir[1];
			max = Math.max(max, dfs(matrix, row, col, matrix[i][j], mem));
		}
		// fill in the DP form
		mem[i][j] = max + 1;
		return max + 1;
	}
}

//leetcode submit region end(Prohibit modification and deletion)
class Solution1_1 {
	// DFS, Time Limit Exceeded
	private final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public int longestIncreasingPath(int[][] matrix) {
		
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return 0;
		}
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] mem = new int[rows][cols];
		int max = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				max = Math.max(max, dfs(matrix, i, j, null, mem));
			}
		}
		return max;
	}
	
	/**
	 * @param matrix given matrix
	 * @param i:      row index
	 * @param j:      col index
	 * @param preVal: previous number
	 * @param mem:    the memory to store the max lengh starting from matrix[i][j]
	 * @return: find the longest longest Increasing path starting from matrix[i][j]
	 */
	private int dfs(int[][] matrix, int i, int j, Integer preVal, int[][] mem) {
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		if (i < 0 || i >= rows || j < 0 || j >= cols || (preVal != null
				&& matrix[i][j] <= preVal)) {
			return 0;
		}
		// lookup DP form
	/*if (mem[i][j] > 0) {
		return mem[i][j];
	}*/
		int max = 0;
		for (int[] dir : DIRECTIONS) {
			int row = i + dir[0];
			int col = j + dir[1];
			max = Math.max(max, dfs(matrix, row, col, matrix[i][j], mem));
		}
		// fill in the DP form
		// mem[i][j] = max + 1;
		return max + 1;
	}
}
class Solution1_2 {
	// DFS with pruning
	private final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public int longestIncreasingPath(int[][] matrix) {
		
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return 0;
		}
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] mem = new int[rows][cols];
		int max = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				max = Math.max(max, dfs(matrix, i, j, null, mem));
			}
		}
		return max;
	}
	
	/**
	 * @param matrix given matrix
	 * @param i:      row index
	 * @param j:      col index
	 * @param preVal: previous number
	 * @param mem:    the memory to store the max lengh starting from matrix[i][j]
	 * @return: find the longest longest Increasing path starting from matrix[i][j]
	 */
	private int dfs(int[][] matrix, int i, int j, Integer preVal, int[][] mem) {
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		if (i < 0 || i >= rows || j < 0 || j >= cols || (preVal != null
				&& matrix[i][j] <= preVal)) {
			return 0;
		}
		// lookup DP form
		if (mem[i][j] > 0) {
			return mem[i][j];
		}
		int max = 0;
		for (int[] dir : DIRECTIONS) {
			int row = i + dir[0];
			int col = j + dir[1];
			max = Math.max(max, dfs(matrix, row, col, matrix[i][j], mem));
		}
		// fill in the DP form
		mem[i][j] = max + 1;
		return max + 1;
	}
}
}