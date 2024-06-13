//Given a 2D board containing 'X' and 'O' (the letter O), capture all regions su
//rrounded by 'X'. 
//
// A region is captured by flipping all 'O's into 'X's in that surrounded region
//. 
//
// Example: 
//
// 
//X X X X
//X O O X
//X X O X
//X O X X
// 
//
// After running your function, the board should be: 
//
// 
//X X X X
//X X X X
//X X X X
//X O X X
// 
//
// Explanation: 
//
// Surrounded regions shouldn’t be on the border, which means that any 'O' on th
//e border of the board are not flipped to 'X'. Any 'O' that is not on the border 
//and it is not connected to an 'O' on the border will be flipped to 'X'. Two cell
//s are connected if they are adjacent cells connected horizontally or vertically.
// 
// Related Topics Depth-first Search Breadth-first Search Union Find 
// 👍 1851 👎 670

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;

// 2020-07-26 13:32:09
// Jesse Yang
public class Leetcode0130SurroundedRegions{
    // Java: surrounded-regions
    public static void main(String[] args) {
        // Solution sol = new Leetcode0130SurroundedRegions().new Solution();
        // TO TEST
        
        System.out.println();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
// Solution 2: DFS
class Solution {

    int[][] directions = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public void solve(char[][] board) {
	    // corner case
	    if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
		    return;
	    }
	
	    int rows = board.length;
	    int cols = board[0].length;
	
	    // do DFS for the first and last row
	    for (int j = 0; j < cols; j++) {
		    doDFSflipY(board, 0, j);
		    doDFSflipY(board, rows - 1, j);
	    }
	
	    // do DFS for the first and last col
	    for (int i = 1; i < rows - 1; i++) {
		    doDFSflipY(board, i, 0);
		    doDFSflipY(board, i, cols - 1);
	    }
	
	    //traverse the whole board, change 0 into X, and Y into 0
	    flip(board);
	
    }

    private void flip(char[][] board) {
	    int cols = board[0].length;
	    for (char[] chars : board) {
		    for (int j = 0; j < cols; j++) {
			    if (chars[j] == 'O') {
				    chars[j] = 'X';
			    }
			    if (chars[j] == 'Y') {
				    chars[j] = 'O';
			    }
		    }
	    }
    }

    private void doDFSflipY(char[][] board, int row, int col) {
	    //base case
	    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
		    || board[row][col] == 'X' || board[row][col] == 'Y') {
		    return;
	    }
	
	    board[row][col] = 'Y';
	
	    //general case
	    for (int[] direction : directions) {
		    int r = row + direction[0];
		    int c = col + direction[1];
		    doDFSflipY(board, r, c);
	    }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: BFS
class Solution1 {
    
    int[][] directions = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    
    public void solve(char[][] board) {
        // corner case
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }
        
        int rows = board.length;
        int cols = board[0].length;
        
        Queue<Integer> queue = new LinkedList<>();
        
        // do BFS for the first and last row
        for (int j = 0; j < cols; j++) {
            flipToYByIndex(board, cols, queue, 0, j);
            flipToYByIndex(board, cols, queue, rows - 1, j);
        }
        
        // do BFS for the first and last col
        for (int i = 1; i < rows - 1; i++) {
            flipToYByIndex(board, cols, queue, i, 0);
            flipToYByIndex(board, cols, queue, i, cols - 1);
        }
        
        //traverse the whole board, change o into X, and Y into 0
        flip(board);
    }
    
    //traverse the whole board, change o into X, and Y into 0
    private void flip(char[][] board) {
        int cols = board[0].length;
        for (char[] chars : board) {
            for (int j = 0; j < cols; j++) {
                if (chars[j] == 'O') {
                    chars[j] = 'X';
                }
                if (chars[j] == 'Y') {
                    chars[j] = 'O';
                }
            }
        }
    }
    
    private void flipToYByIndex(char[][] board, int cols, Queue<Integer> queue, int i, int j) {
        if (board[i][j] == 'O') {
            board[i][j] = 'Y';
            queue.offer(i * cols + j);
            doBFSflipY(board, queue);
        }
    }
    
    // queue is used to place the elements already changed to Y
    private void doBFSflipY(char[][] board, Queue<Integer> queue) {
        
        int cols = board[0].length;
        
        while (!queue.isEmpty()) {
            int index = queue.poll();
            int row = index / cols;
            int col = index % cols;
            for (int[] direction : directions) {
                int r = row + direction[0];
                int c = col + direction[1];
                if (r < 0 || r >= board.length || c < 0 || c >= board[0].length
                        || board[r][c] != 'O') {
                    continue;
                }
                board[r][c] = 'Y';
                queue.offer(r * cols + c);
            }
        }
    }
}

// Solution 2: DFS
class Solution2 {

    int[][] directions = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public void solve(char[][] board) {
        // corner case
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }

        int rows = board.length;
        int cols = board[0].length;

        // do DFS for the first and last row
        for (int j = 0; j < cols; j++) {
            doDFSflipY(board, 0, j);
            doDFSflipY(board, rows - 1, j);
        }

        // do DFS for the first and last col
        for (int i = 1; i < rows - 1; i++) {
            doDFSflipY(board, i, 0);
            doDFSflipY(board, i, cols - 1);
        }

        //traverse the whole board, change 0 into X, and Y into 0
        flip(board);
    
    }
    
    private void flip(char[][] board) {
        int cols = board[0].length;
        for (char[] chars : board) {
            for (int j = 0; j < cols; j++) {
                if (chars[j] == 'O') {
                    chars[j] = 'X';
                }
                if (chars[j] == 'Y') {
                    chars[j] = 'O';
                }
            }
        }
    }
    
    private void doDFSflipY(char[][] board, int row, int col) {
        //base case
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || board[row][col] == 'X' || board[row][col] == 'Y') {
            return;
        }

        board[row][col] = 'Y';

        //general case
        for (int[] direction : directions) {
            int r = row + direction[0];
            int c = col + direction[1];
            doDFSflipY(board, r, c);
        }
    }
}
}