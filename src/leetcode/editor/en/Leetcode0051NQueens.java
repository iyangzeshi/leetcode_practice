//The n-queens puzzle is the problem of placing n queens on an n×n chessboard su
//ch that no two queens attack each other. 
//
// 
//
// Given an integer n, return all distinct solutions to the n-queens puzzle. 
//
// Each solution contains a distinct board configuration of the n-queens' placem
//ent, where 'Q' and '.' both indicate a queen and an empty space respectively. 
//
// Example: 
//
// 
//Input: 4
//Output: [
// [".Q..",  // Solution 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // Solution 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//Explanation: There exist two distinct solutions to the 4-queens puzzle as show
//n above.
// 
// Related Topics Backtracking 
// 👍 1934 👎 73

package leetcode.editor.en;

import java.util.*;
// 2020-07-26 12:58:22
// Zeshi Yang
public class Leetcode0051NQueens{
    // Java: n-queens
    public static void main(String[] args) {
        Solution sol = new Leetcode0051NQueens().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 2：DFS的方法往下call，设置投影检查是否冲突
// isValid，用3个array，投影的方式，投影到行，列，主次斜对角线上看有没有冲突
class Solution {
    /**
     Zeshi Yang's code
     Solution 2
     isValid，用投影的方式，把每个Q都投影到列，（主+次）对角线上，直接读值判断Q有没有冲突
     然后用backtracking
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        char[][] board = new char[n][n];
        boolean[][] projection = new boolean[3][2 * n - 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        dfs(0, n, board, result, projection);
        return result;
    }

    private void dfs(int colIndex, int n, char[][] board, List<List<String>> result, boolean[][] projection) {
        if (colIndex == n) {
            result.add(converMatrix(board));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isValid(projection, i, colIndex, n)) {
                board[i][colIndex] = 'Q';
                addValid(projection, i, colIndex, n);
                dfs(colIndex + 1, n, board, result, projection);
                removeValid(projection, i, colIndex, n); // backtracking
                board[i][colIndex] = '.'; // backtracking
            }
        }
    }

    private boolean isValid(boolean[][] projection, int x, int y, int n) {
        /*
         检测(x, y)的元素有没有和前面的冲突，DFS按照列走，只需要第[0~y)列已赋值
         分3行：
         第1行表示投影在列上【0-7】
         第2行表示投影在主对角线上
         第3行表示投影在次对角线上
         设左下角是[0, 0]
         */
        /*
        if (projection[0][x] || projection[1][y - x + (n - 1)] || projection[2][x + y]) {
            return false;
        }
        return true;*/
        return !projection[0][x] && !projection[1][y - x + (n - 1)] && !projection[2][x + y];
    }

    private void addValid(boolean[][] projection, int x, int y, int n) {
        projection[0][x] = true;
        projection[1][y - x + (n - 1)] = true;
        projection[2][x + y] = true;

    }

    private void removeValid(boolean[][] projection, int x, int y, int n) {
        projection[0][x] = false;
        projection[1][y - x + (n - 1)] = false;
        projection[2][x + y] = false;
    }


    private List<String> converMatrix(char[][] board) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            list.add(new String(board[i]));
        }
        return list;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1：DFS的方法往下call，遍历棋盘判断有没有冲突
/*
isValid，用矩阵遍历每个元素的方式，看是不是在同一列，同一行，同一个斜对角线
 */
class Solution1 {
    /**
     Solution 1
     isValid，用矩阵遍历每个元素的方式，看是不是在同一列，同一行，同一个斜对角线
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        char[][] board = new char[n][n];
        boolean[][] isValid = new boolean[3][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        dfs(0, n, board, result);
        return result;
    }

    private void dfs(int colIndex, int n, char[][] board, List<List<String>> result) {
        if (colIndex == n) {
            result.add(converMatrix(board));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isValid(board, i, colIndex)) {
                board[i][colIndex] = 'Q';
                dfs(colIndex + 1, n, board, result);
                board[i][colIndex] = '.';
            }
        }
    }

    private boolean isValid(char[][] board, int x, int y) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < y; j++) {
                /*
                 x == i表示同一行
                 Math.abs(x - i) == Math.abs(y - j)) && board[i][j] == 'Q'表示斜对角线上重合
                 */
                if ((x == i || Math.abs(x - i) == Math.abs(y - j)) && board[i][j] == 'Q') {
                    return false;
                }
            }
        }
        return true;
    }

    private List<String> converMatrix(char[][] board) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            list.add(new String(board[i]));
        }
        return list;
    }
}

// Solution 2：DFS的方法往下call，设置投影检查是否冲突
// isValid，用3个array，投影的方式，投影到行，列，主次斜对角线上看有没有冲突
class Solution2 {
    /**
     Zeshi Yang's code
     Solution 2
     isValid，用投影的方式，把每个Q都投影到列，（主+次）对角线上，直接读值判断Q有没有冲突
     然后用backtracking
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        char[][] board = new char[n][n];
        boolean[][] projection = new boolean[3][2 * n - 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        dfs(0, n, board, result, projection);
        return result;
    }

    private void dfs(int colIndex, int n, char[][] board, List<List<String>> result, boolean[][] projection) {
        if (colIndex == n) {
            result.add(converMatrix(board));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isValid(projection, i, colIndex, n)) {
                board[i][colIndex] = 'Q';
                addValid(projection, i, colIndex, n);
                dfs(colIndex + 1, n, board, result, projection);
                removeValid(projection, i, colIndex, n); // backtracking
                board[i][colIndex] = '.'; // backtracking
            }
        }
    }

    private boolean isValid(boolean[][] projection, int x, int y, int n) {
        /*
         检测(x, y)的元素有没有和前面的冲突，DFS按照列走，只需要第[0~y)列已赋值
         分3行：
         第1行表示投影在列上【0-7】
         第2行表示投影在主对角线上
         第3行表示投影在次对角线上
         设左下角是[0, 0]
         */
        /*
        if (projection[0][x] || projection[1][y - x + (n - 1)] || projection[2][x + y]) {
            return false;
        }
        return true;
         */
        return !projection[0][x] && !projection[1][y - x + (n - 1)] && !projection[2][x + y];
    }

    private void addValid(boolean[][] projection, int x, int y, int n) {
        projection[0][x] = true;
        projection[1][y - x + (n - 1)] = true;
        projection[2][x + y] = true;

    }

    private void removeValid(boolean[][] projection, int x, int y, int n) {
        projection[0][x] = false;
        projection[1][y - x + (n - 1)] = false;
        projection[2][x + y] = false;
    }


    private List<String> converMatrix(char[][] board) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            list.add(new String(board[i]));
        }
        return list;
    }
}
}