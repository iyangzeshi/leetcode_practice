//Write a program to solve a Sudoku puzzle by filling the empty cells. 
//
// A sudoku solution must satisfy all of the following rules: 
//
// 
// Each of the digits 1-9 must occur exactly once in each row. 
// Each of the digits 1-9 must occur exactly once in each column. 
// Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-b
//oxes of the grid. 
// 
//
// Empty cells are indicated by the character '.'. 
//
// 
//A sudoku puzzle... 
//
// 
//...and its solution numbers marked in red. 
//
// Note: 
//
// 
// The given board contain only digits 1-9 and the character '.'. 
// You may assume that the given Sudoku puzzle will have a single unique solutio
//n. 
// The given board size is always 9x9. 
// 
// Related Topics Hash Table Backtracking 
// 👍 1846 👎 91

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 2020-07-26 13:00:15
// Jesse Yang
public class Leetcode0037SudokuSolver {
    
    // Java: sudoku-solver
    public static void main(String[] args) {
        Solution sol = new Leetcode0037SudokuSolver().new Solution();
        // TO TEST
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, 
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        sol.solveSudoku(board);
        for (char[] row: board) {
            System.out.println(Arrays.toString(row));
        }
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
//  DFS, T(n) = O(1), S(n) = O(1)
// 4 ms,击败了92.35% 的Java用户, 36.7 MB,击败了41.72% 的Java用户
/*
思路：按顺序从上到下，从左到右遍历空的地方，遍历能不能放1~9
遍历的时候，需要检查行，列，和box有没有已经存在的数字，为了减少check次数，用数组表示

step 1: 设置3个数组
    rows[i][num]表示第i行是否已经存在数字num
    cols[j][num]表示第j列是否已经存在数字num
    boxes[k][num]表示第k个box是否已经存在数字num

step 2: 先遍历整个board，更新以上3个数组，返回需要填的位置的List emptyCells

step 3: DFS遍历整个空的 emptyCells List, 每个地方都遍历 1 ~ 9

 */
class Solution {
    
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            for (char[] row: board) {
                System.out.println(Arrays.toString(row));
            }
            return;
        }
        
        int size = 9;
        
        // rows[i][j] is true, means in i-th row, it has number j already.
        boolean[][] rows = new boolean[size][size + 1];
        boolean[][] cols = new boolean[size][size + 1]; // similar as above
        boolean[][] boxes = new boolean[size][size + 1]; // similar as above
        
        List<Integer> emptyCells = recordBoard(board, rows, cols, boxes); // index of emptyCells space
        fillEmptySpace(0, emptyCells, board, rows, cols, boxes);
    }
    
    // return the indexes of empty space and update rows, cols, boxes boolean[][]
    private List<Integer> recordBoard(char[][] board, boolean[][] rows, boolean[][] cols,
            boolean[][] boxes) {
        List<Integer> emptyCells = new ArrayList<>();
        int n = 3;
        int size = n * n;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == '.') {
                    int index = i * size + j;
                    emptyCells.add(index);
                } else {
                    int num = board[i][j] - '0';
                    rows[i][num] = true;
                    cols[j][num] = true;
                    boxes[i / n * n + j / n][num] = true;
                }
            }
        }
        return emptyCells;
    }
    
    private boolean fillEmptySpace(int level, List<Integer> emptyCells, char[][] board,
            boolean[][] rows, boolean[][] cols, boolean[][] boxes) {
        // base case
        if (level == emptyCells.size()) {
            return true;
        }
        
        int n = 3;
        int size = n * n;
        int index = emptyCells.get(level);
        int row = index / size;
        int col = index % size;
        int boxIndex = row / n * n + col / n;
        for (int num = 1; num <= size; num++) {
            if (!rows[row][num] && !cols[col][num] && !boxes[boxIndex][num]) {
                
                rows[row][num] = true;
                cols[col][num] = true;
                boxes[boxIndex][num] = true;
                board[row][col] = (char) (num + '0');
                
                if (fillEmptySpace(level + 1, emptyCells, board, rows, cols, boxes)) {
                    return true;
                }
                
                rows[row][num] = false;
                cols[col][num] = false;
                boxes[boxIndex][num] = false;
                board[row][col] = '.';
            }
        }
        return false;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}