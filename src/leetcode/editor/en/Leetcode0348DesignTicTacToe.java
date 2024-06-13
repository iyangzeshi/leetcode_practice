//Design a Tic-tac-toe game that is played between two players on a n x n grid.
// 
//
// You may assume the following rules:
// 
// A move is guaranteed to be valid and is placed on an empty block. 
// Once a winning condition is reached, no more moves is allowed. 
// A player who succeeds in placing n of their marks in a horizontal, vertical, 
//or diagonal row wins the game. 
// 
// 
//
// Example: 
// 
//Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
//
//TicTacToe toe = new TicTacToe(3);
//
//toe.move(0, 0, 1); -> Returns 0 (no one wins)
//|X| | |
//| | | |    // Player 1 makes a move at (0, 0).
//| | | |
//
//toe.move(0, 2, 2); -> Returns 0 (no one wins)
//|X| |O|
//| | | |    // Player 2 makes a move at (0, 2).
//| | | |
//
//toe.move(2, 2, 1); -> Returns 0 (no one wins)
//|X| |O|
//| | | |    // Player 1 makes a move at (2, 2).
//| | |X|
//
//toe.move(1, 1, 2); -> Returns 0 (no one wins)
//|X| |O|
//| |O| |    // Player 2 makes a move at (1, 1).
//| | |X|
//
//toe.move(2, 0, 1); -> Returns 0 (no one wins)
//|X| |O|
//| |O| |    // Player 1 makes a move at (2, 0).
//|X| |X|
//
//toe.move(1, 0, 2); -> Returns 0 (no one wins)
//|X| |O|
//|O|O| |    // Player 2 makes a move at (1, 0).
//|X| |X|
//
//toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
//|X| |O|
//|O|O| |    // Player 1 makes a move at (2, 1).
//|X|X|X|
// 
// 
//
// Follow up: 
//Could you do better than O(n2) per move() operation?
// Related Topics Design 
// 👍 819 👎 61

package leetcode.editor.en;

// 2020-09-09 17:48:25
// Jesse Yang
public class Leetcode0348DesignTicTacToe{
    // Java: design-tic-tac-toe
    public static void main(String[] args) {
        int n = 3;
        TicTacToe ticTacToe = new Leetcode0348DesignTicTacToe().new TicTacToe(n);
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
public class TicTacToe {

    private int[] rows;
    private int[] cols;
    private int diagonal;
    private int antiDiagonal;

    /**
     * Initialize your data structure here.
     */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     *
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either: 0: No one wins. 1: Player 1 wins.
     * 2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
        int toAdd = player == 1 ? 1 : -1;
    
        rows[row] += toAdd;
        cols[col] += toAdd;
        if (row == col) {
            diagonal += toAdd;
        }
    
        if (col == (cols.length - row - 1)) {
            antiDiagonal += toAdd;
        }
    
        int size = rows.length;
        if (Math.abs(rows[row]) == size ||
                Math.abs(cols[col]) == size ||
                Math.abs(diagonal) == size ||
                Math.abs(antiDiagonal) == size) {
            return player;
        }
    
        return 0;
    }
}


/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1:
class TicTacToe1 {
    
    /** Initialize your data structure here. */
    // first dimensionality:  0 for player 1. row 2 for player 2.
    // second dimensionality: is projection into different directions
    int[][] personRow;
    int[][] personCol;
    int[][] personDiagonal;
    int[][] personSubdiagonal;
    int n;
    
    public TicTacToe1(int n) {
        personRow = new int[2][n];
        personCol = new int[2][n];
        personDiagonal = new int[2][2 * n - 1];
        personSubdiagonal = new int[2][2 * n - 1];
        this.n = n;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        if (++personRow[player - 1][row] == n ||
                ++personCol[player - 1][col] == n ||
                ++personDiagonal[player - 1][row + col] == n ||
                ++personSubdiagonal[player - 1][row - col + n - 1] == n) {
            return player;
        }
        return 0;
    }
}
}