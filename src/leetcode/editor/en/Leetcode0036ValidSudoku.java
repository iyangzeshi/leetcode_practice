//Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be val
//idated according to the following rules: 
//
// 
// Each row must contain the digits 1-9 without repetition. 
// Each column must contain the digits 1-9 without repetition. 
// Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without r
//epetition. 
// 
//
// 
//A partially filled sudoku which is valid. 
//
// The Sudoku board could be partially filled, where empty cells are filled with
// the character '.'. 
//
// Example 1: 
//
// 
//Input:
//[
//  ["5","3",".",".","7",".",".",".","."],
//  ["6",".",".","1","9","5",".",".","."],
//  [".","9","8",".",".",".",".","6","."],
//  ["8",".",".",".","6",".",".",".","3"],
//  ["4",".",".","8",".","3",".",".","1"],
//  ["7",".",".",".","2",".",".",".","6"],
//  [".","6",".",".",".",".","2","8","."],
//  [".",".",".","4","1","9",".",".","5"],
//  [".",".",".",".","8",".",".","7","9"]
//]
//Output: true
// 
//
// Example 2: 
//
// 
//Input:
//[
//  ["8","3",".",".","7",".",".",".","."],
//  ["6",".",".","1","9","5",".",".","."],
//  [".","9","8",".",".",".",".","6","."],
//  ["8",".",".",".","6",".",".",".","3"],
//  ["4",".",".","8",".","3",".",".","1"],
//  ["7",".",".",".","2",".",".",".","6"],
//  [".","6",".",".",".",".","2","8","."],
//  [".",".",".","4","1","9",".",".","5"],
//  [".",".",".",".","8",".",".","7","9"]
//]
//Output: false
//Explanation: Same as Example 1, except with the 5 in the top left corner being
// 
//    modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is 
//invalid.
// 
//
// Note: 
//
// 
// A Sudoku board (partially filled) could be valid but is not necessarily solva
//ble. 
// Only the filled cells need to be validated according to the mentioned rules. 
//
// The given board contain only digits 1-9 and the character '.'. 
// The given board size is always 9x9. 
// 
// Related Topics Hash Table 
// 👍 1697 👎 448

package leetcode.editor.en;

import java.util.*;
// 2020-07-26 12:59:57
// Zeshi Yang
public class Leetcode0036ValidSudoku{
    // Java: valid-sudoku
    public static void main(String[] args) {
        Solution sol = new Leetcode0036ValidSudoku().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isValidSudoku(char[][] board) {
        //corner case
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return false;
        }

        int row = board.length;
        int col = row;

        for (int i = 0; i < row; i++) {
            if (!isPartiallyValid(board, i, i, 0, 8)) { // check the collon
                return false;
            }
        }

        for (int j = 0; j < col; j++) {
            if (!isPartiallyValid(board, 0, 8, j, j)) { // check the row
                return false;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // check the sub-box
                if (!isPartiallyValid(board, 3 * i, 3 * i + 2, 3 * j, 3 * j + 2)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isPartiallyValid(char[][] board, int x1, int x2, int y1, int y2) {
        Set<Character> set = new HashSet<>(); // 每次检查必须新建set

        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                char temp = board[i][j];

                if (temp != '.' && set.contains(temp)) {
                    return false;
                }
                else {
                    set.add(temp);
                }
                /* 或者将上面的if else语句改成如下
                 如果set已经有temp的话，set.add()返回false；否则添加temp，返回true
                 if (temp != '.' && !set.add(temp)) {
                 return false;
                 }
                 */
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}