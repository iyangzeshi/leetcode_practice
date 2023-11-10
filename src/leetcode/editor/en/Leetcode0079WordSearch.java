//Given a 2D board and a word, find if the word exists in the grid. 
//
// The word can be constructed from letters of sequentially adjacent cell, where
// "adjacent" cells are those horizontally or vertically neighboring. The same let
//ter cell may not be used more than once. 
//
// Example: 
//
// 
//board =
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//
//Given word = "ABCCED", return true.
//Given word = "SEE", return true.
//Given word = "ABCB", return false.
// 
//
// 
// Constraints: 
//
// 
// board and word consists only of lowercase and uppercase English letters. 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// 1 <= word.length <= 10^3 
// 
// Related Topics Array Backtracking
/*
  @ClassName: Leetcode79WordSearch
 * @Description:
 * @Author: Zeshi(Jesse) Yang
 * @Date: 2020/06/23 周二 18:36
 */
package leetcode.editor.en;

public class Leetcode0079WordSearch {
	
	// Java: word-search
	public static void main(String[] args) {
		
		Solution sol = new Leetcode0079WordSearch().new Solution();
		// TO TEST
		
		char[][] board = new char[][]{
				{'A', 'B', 'C', 'E'},
				{'S', 'F', 'C', 'S'},
				{'A', 'D', 'E', 'E'}
		};
		String word = "ABCB";
		boolean res = sol.exist(board, word);
		System.out.println(res);
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
// DFS, T(m,n) = O(m * n * 3^o), S(m,n) = O(m*n), o is the length of the word
class Solution {
	
	private final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
	
	public boolean exist(char[][] board, String word) {
		// corner case
		if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
			return false;
		}
		int rows = board.length;
		int cols = board[0].length;
		boolean[][] visited = new boolean[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (dfs(0, i, j, word, board, visited)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean dfs(int idx, int row, int col, String word, char[][] matrix,
		boolean[][] visited) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int len = word.length();
		// base case - success case
		if (idx == len) {
			return true;
		}
		// base case - failure case
		if (row < 0 || row >= rows || col < 0 || col >= cols || word.charAt(idx) != matrix[row][col]
			|| visited[row][col]) {
			return false;
		}
		visited[row][col] = true;
		
		boolean res = false;
		for (int[] dir : DIRECTIONS) {
			int i = row + dir[0];
			int j = col + dir[1];
			res = dfs(idx + 1, i, j, word, matrix, visited);
			if (res) {
				break;
			}
		}
		
		visited[row][col] = false;
		return res;
	}
	
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: DFS, T(m,n) = O(m * n * 3^o), S(m,n) = O(m*n), o is the length of the word
// 82 ms,击败了26.81% 的Java用户, 37 MB,击败了88.93% 的Java用户
class Solution1 {
	
	private final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
	
	public boolean exist(char[][] board, String word) {
		// corner case
		if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
			return false;
		}
		int rows = board.length;
		int cols = board[0].length;
		boolean[][] visited = new boolean[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (dfs(0, i, j, word, board, visited)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean dfs(int idx, int row, int col, String word, char[][] matrix,
			boolean[][] visited) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int len = word.length();
		// base case - success case
		if (idx == len) {
			return true;
		}
		// base case - failure case
		if (row < 0 || row >= rows || col < 0 || col >= cols || word.charAt(idx) != matrix[row][col]
				|| visited[row][col]) {
			return false;
		}
		visited[row][col] = true;
		
		boolean res = false;
		for (int[] dir : DIRECTIONS) {
			int i = row + dir[0];
			int j = col + dir[1];
			res = dfs(idx + 1, i, j, word, matrix, visited);
			if (res) {
				break;
			}
		}
		
		visited[row][col] = false;
		return res;
	}
	
}

// Solution 2: DFS, 不用visited的写法, T(m,n) = O(m * n * 3^o), S(m,n) = O(m*n), o is the length of the word
// 41 ms,击败了69.64% 的Java用户, 36.8 MB,击败了92.12% 的Java用户
class Solution2 {
	
	private final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
	
	public boolean exist(char[][] board, String word) {
		// corner case
		if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
			return false;
		}
		int rows = board.length;
		int cols = board[0].length;
		boolean[][] visited = new boolean[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (dfs(0, i, j, word, board)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean dfs(int idx, int row, int col, String word, char[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int len = word.length();
		// base case - success case
		if (idx == len) {
			return true;
		}
		// base case - failure case
		if (row < 0 || row >= rows || col < 0 || col >= cols
			|| word.charAt(idx) != matrix[row][col]) {
			return false;
		}
		matrix[row][col] ^= 0x1FF; // mark this location no longer accessible
		boolean res = false;
		for (int[] dir : DIRECTIONS) {
			int i = row + dir[0];
			int j = col + dir[1];
			res = dfs(idx + 1, i, j, word, matrix);
			if (res) {
				break;
			}
		}
		matrix[row][col] ^= 0x1FF; // restore this location char
		return res;
	}
	
}

}