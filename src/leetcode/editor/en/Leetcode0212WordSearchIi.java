//Given a 2D board and a list of words from the dictionary, find all words in th
//e board. 
//
// Each word must be constructed from letters of sequentially adjacent cell, whe
//re "adjacent" cells are those horizontally or vertically neighboring. The same l
//etter cell may not be used more than once in a word. 
//
// 
//
// Example: 
//
// 
//Input: 
//board = [
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//words = ["oath","pea","eat","rain"]
//
//Output: ["eat","oath"]
// 
//
// 
//
// Note: 
//
// 
// All inputs are consist of lowercase letters a-z. 
// The values of words are distinct. 
// 
// Related Topics Backtracking Trie 
// 👍 2814 👎 121

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
// 2020-08-27 23:14:47
// Zeshi Yang
public class Leetcode0212WordSearchIi{
    // Java: word-search-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0212WordSearchIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	
	private final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public List<String> findWords(char[][] board, String[] words) {
		List<String> res = new ArrayList<>();
		int rows = board.length;
		int cols = board[0].length;
		boolean[][] visited = new boolean[rows][cols];
		Trie trie = new Trie();
		
		for (String word : words) {
			trie.addWord(word);
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				dfs(board, i, j, new StringBuilder(), trie.root, res, visited);
			}
		}
		return res;
	}
	
	private void dfs(char[][] board, int row, int col, StringBuilder path, TrieNode parent,
			List<String> res, boolean[][] visited) {
		int rows = board.length;
		int cols = board[0].length;
		// base case - failure
		if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col]
				|| parent.children[board[row][col] - 'a'] == null) {
			return;
		}
		
		visited[row][col] = true;
		TrieNode cur = parent.children[board[row][col] - 'a'];
		path.append(board[row][col]);
		if (cur.isWord) {
			res.add(path.toString());
			cur.isWord = false;
		}
		for (int[] dir : DIRECTIONS) {
			int i = row + dir[0];
			int j = col + dir[1];
			dfs(board, i, j, path, cur, res, visited);
		}
		visited[row][col] = false;
		path.setLength(path.length() - 1);
		
		// optimization: incrementally remove the leaf nodes
		if (cur.size == 0) {
			parent.children[cur.ch - 'a'] = null;
			parent.size--;
		}
	}
	
	private class TrieNode {
		
		private final char ch;
		private final TrieNode[] children;
		private boolean isWord;
		int size; // children size
		
		
		public TrieNode(char ch) {
			this.ch = ch;
			this.children = new TrieNode[26];
			this.isWord = false;
			this.size = 0;
		}
		
	}
	
	private class Trie {
		
		private final TrieNode root;
		
		public Trie() {
			root = new TrieNode('\0');
		}
		
		private void addWord(String word) {
			char[] chars = word.toCharArray();
			TrieNode cur = root;
			for (char ch : chars) {
				if (cur.children[ch - 'a'] == null) {
					cur.children[ch - 'a'] = new TrieNode(ch);
					cur.size++;
				}
				cur = cur.children[ch - 'a'];
			}
			cur.isWord = true;
		}
		
	}
	
}
//leetcode submit region end(Prohibit modification and deletion)

/** 面试的时候，如果DFS用Solution 1, 如果用Trie的话，用Solution 2_2 **/

// Solution 1: DFS, T(M, size, k) = O(M * size * 2^(k - 1)), S(M, size, k) = O(max(k, M))
// M is number of cells in board, k is max length of words
// size: total number of letters in the dict
// 2 ms,击败了77.13% 的Java用户, 37.1 MB,击败了96.15% 的Java用户
class Solution1 {

    private final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> res = new HashSet<>();
        // corner case
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return new ArrayList<>(res);
        }
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (String word: words) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (dfs(board, i, j, word, 0, visited)) {
                        res.add(word);
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    private boolean dfs(char[][] matrix, int i, int j, String word, int idx, boolean[][] visited) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int len = word.length();
        // base case
        // success case
        if (idx == len) {
            return true;
        }
        // failure case
        if (i < 0 || i >= rows || j < 0 || j >= cols || word.charAt(idx) != matrix[i][j]
                || visited[i][j]) {
            return false;
        }
        visited[i][j] = true;
        // Solution 1:
        boolean res = false;
        for (int[] dir : DIRECTIONS) {
            int row = i + dir[0];
            int col = j + dir[1];
            res = dfs(matrix, row, col, word, idx + 1, visited);
            if (res) {
                break;
            }
        }
        visited[i][j] = false;
        return res;
    }
}

// Solution 2: Trie:

// Solution 2_1: Trie， T(M, size, k) = O(M * 2^(k - 1)), S(M, size, k) = O(max(size, M))
// M is number of cells in board, k is max length of words
// size: total number of letters in the dict
// 192 ms,击败了31.14% 的Java用户, 38.8 MB,击败了37.51% 的Java用户
class Solution2 {
	
	private final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public List<String> findWords(char[][] board, String[] words) {
		List<String> res = new ArrayList<>();
		int m = board.length;
		int n = board[0].length;
		boolean[][] visited = new boolean[m][n];
		Trie trie = new Trie();
		
		for (String word : words) { // Space O(size)
			trie.addWord(word);
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dfs(board, i, j, new StringBuilder(), trie.root, res, visited);
			}
		}
		return res;
	}
	
	private void dfs(char[][] board, int row, int col, StringBuilder path, TrieNode parent,
			List<String> res, boolean[][] visited) {
		int rows = board.length;
		int cols = board[0].length;
		// base case - failure
		if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col]
				|| parent.children[board[row][col] - 'a'] == null) {
			return;
		}
		
		visited[row][col] = true;
		TrieNode cur = parent.children[board[row][col] - 'a'];
		path.append(board[row][col]);
		if (cur.isWord) {
			res.add(path.toString());
			cur.isWord = false;
		}
		for (int[] dir : DIRECTIONS) {
			int i = row + dir[0];
			int j = col + dir[1];
			dfs(board, i, j, path, cur, res, visited);
		}
		visited[row][col] = false;
		path.setLength(path.length() - 1);
	}
	
	private class TrieNode {
		
		private final char ch;
		private TrieNode[] children;
		private boolean isWord;
		
		
		public TrieNode(char ch) {
			this.ch = ch;
			this.children = new TrieNode[26];
			this.isWord = false;
		}
		
		
	}
	
	private class Trie {
		
		private TrieNode root;
		
		public Trie() {
			root = new TrieNode('\0');
		}
		
		private void addWord(String word) {
			char[] chars = word.toCharArray();
			TrieNode cur = root;
			for (char ch : chars) {
				if (cur.children[ch - 'a'] == null) {
					cur.children[ch - 'a'] = new TrieNode(ch);
				}
				cur = cur.children[ch - 'a'];
			}
			cur.isWord = true;
		}
		
	}
	
}


// Solution 2_1: Trie， T(m, n, size, k) = O(M * 2^(k - 1)), S(M, size, k) = O(max(k, M)) To Do
// To Do, 棋盘可以不用设置 visited[[] 数组，可以遍历的时候给它赋#,遍历完之后赋值回去，S(M, size, k) = O(size)
// M is number of cells in board, k is max length of words
// size: total number of letters in the dict
// 1 ms,击败了94.54% 的Java用户, 37.6 MB,击败了56.20% 的Java用户
class Solution2_2 {
	
	private final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public List<String> findWords(char[][] board, String[] words) {
		List<String> res = new ArrayList<>();
		int rows = board.length;
		int cols = board[0].length;
		boolean[][] visited = new boolean[rows][cols];
		Trie trie = new Trie();
		
		for (String word : words) {
			trie.addWord(word);
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				dfs(board, i, j, new StringBuilder(), trie.root, res, visited);
			}
		}
		return res;
	}
	
	private void dfs(char[][] board, int row, int col, StringBuilder path, TrieNode parent,
			List<String> res, boolean[][] visited) {
		int rows = board.length;
		int cols = board[0].length;
		// base case - failure
		if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col]
				|| parent.children[board[row][col] - 'a'] == null) {
			return;
		}
		
		visited[row][col] = true;
		TrieNode cur = parent.children[board[row][col] - 'a'];
		path.append(board[row][col]);
		if (cur.isWord) {
			res.add(path.toString());
			cur.isWord = false;
		}
		for (int[] dir : DIRECTIONS) {
			int i = row + dir[0];
			int j = col + dir[1];
			dfs(board, i, j, path, cur, res, visited);
		}
		visited[row][col] = false;
		path.setLength(path.length() - 1);
		
		// optimization: incrementally remove the leaf nodes
		if (cur.size == 0) {
			parent.children[cur.ch - 'a'] = null;
			parent.size--;
		}
	}
	
	private class TrieNode {
		
		private final char ch;
		private final TrieNode[] children;
		private boolean isWord;
		int size; // children size
		
		
		public TrieNode(char ch) {
			this.ch = ch;
			this.children = new TrieNode[26];
			this.isWord = false;
			this.size = 0;
		}
		
	}
	
	private class Trie {
		
		private final TrieNode root;
		
		public Trie() {
			root = new TrieNode('\0');
		}
		
		private void addWord(String word) {
			char[] chars = word.toCharArray();
			TrieNode cur = root;
			for (char ch : chars) {
				if (cur.children[ch - 'a'] == null) {
					cur.children[ch - 'a'] = new TrieNode(ch);
					cur.size++;
				}
				cur = cur.children[ch - 'a'];
			}
			cur.isWord = true;
		}
		
	}
	
}
}