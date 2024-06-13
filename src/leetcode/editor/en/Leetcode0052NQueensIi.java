//The n-queens puzzle is the problem of placing n queens on an n×n chessboard su
//ch that no two queens attack each other.
//
//
//
// Given an integer n, return the number of distinct solutions to the n-queens p
//uzzle.
//
// Example:
//
//
//Input: 4
//Output: 2
//Explanation: There are two distinct solutions to the 4-queens puzzle as shown
//below.
//[
// [".Q..",  // Solution 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // Solution 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//
// Related Topics Backtracking
// 👍 525 👎 154

package leetcode.editor.en;

// 2020-07-26 12:59:43
// Jesse Yang
public class Leetcode0052NQueensIi{
    // Java: n-queens-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0052NQueensIi().new Solution();
        // TO TEST
        int n = 4;
        int res = sol.totalNQueens(n);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
//Solution 2:介绍
/* isValid，用投影的方式，把每个Q都投影到列，（主+次）对角线上，直接读值判断Q有没有冲突,然后backtracking*/

// Solution 2_2: 先check当前要加(x,y)是否valid，不valid就return 0； 如果valid的，就把这个点加进去
//然后看加进去这个点之后，是否走到底了，走到底的话就return 1。否则继续走
class Solution {
    // Solution 2_2: 先check当前要加(x,y)是否valid，不valid就return 0； 如果valid的，就把这个点加进去
    //然后看加进去这个点之后，是否走到底了，走到底的话就return 1。否则继续走
    /**
     isValid，用投影的方式，把每个Q都投影到列，（主+次）对角线上，直接读值判断Q有没有冲突
     然后用backtracking
     */
    public int totalNQueens(int n) {
        int result = 0;
        //corner case
        if (n < 1) {
            return result;
        }
        boolean[][] projection = new boolean[3][2 * n - 1];
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += dfs(i, 0, n, projection);
        }
        return count;
    }

    private int dfs(int row, int col, int n, boolean[][] projection) {
        //base case
        if (!isValid(row, col, projection, n)) {
            return 0;
        }
        if (col == n - 1) {
            return 1;
        }
        // general case
        addCoordinate(n, row, col, projection);
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += dfs(i,col + 1, n, projection);
        }
        removeCoordinate(n, row, col, projection);
        return count;
    }

    private boolean isValid(int x, int y, boolean[][] projection, int n) {
        /*
         检测(x, y)的元素有没有和前面的冲突，DFS按照列走，只需要第[0~y)列已赋值
         分3行：
         第1行表示投影在第一列上
         第2行表示投影在主对角线上
         第3行表示投影在次对角线上
         设左下角是[0, 0]
         */
        return !projection[0][x] && !projection[1][y - x + (n - 1)] && !projection[2][x + y];
    }

    private void addCoordinate(int n, int x, int y, boolean[][] projection) {
        projection[0][x] = true;
        projection[1][y - x + (n - 1)] = true;
        projection[2][x + y] = true;

    }

    private void removeCoordinate(int n, int x, int y, boolean[][] projection) {
        projection[0][x] = false;
        projection[1][y - x + (n - 1)] = false;
        projection[2][x + y] = false;
    }

}

//leetcode submit region end(Prohibit modification and deletion)

//Solution 2:介绍
/* isValid，用投影的方式，把每个Q都投影到列，（主+次）对角线上，直接读值判断Q有没有冲突,然后backtracking*/

// Solution 2_1: 先check是不是走到底了，走到底了，就return 1；
// general case里面，直走valid的分支（先check valid，不valid就不走）
class Solution2_1 {
    // Solution 2_1: 先check是不是走到底了，走到底了，就return 1；
    // general case里面，直走valid的分支（先check valid，不valid就不走）
    /**
     Jesse Yang's code
     isValid，用投影的方式，把每个Q都投影到列，（主+次）对角线上，直接读值判断Q有没有冲突
     然后用backtracking
     */
    public int totalNQueens(int n) {
        int result = 0;
        //corner case
        if (n < 1) {
            return result;
        }
        boolean[][] projection = new boolean[3][2 * n - 1];
        result = dfs(0, n, projection);
        return result;
    }

    private int dfs(int col, int n, boolean[][] projection) {
        //base case
        if (col == n) {
            return 1;
        }
        int count = 0;
        for (int row = 0; row < n; row++) {
            if (isValid(row, col, projection, n)) {
                addCoordinate(n, row, col, projection);
                count += dfs(col + 1, n, projection);
                removeCoordinate(n, row, col, projection);
            }
        }
        return count;
    }

    private boolean isValid(int x, int y, boolean[][] projection, int n) {
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

    private void addCoordinate(int n, int x, int y, boolean[][] projection) {
        projection[0][x] = true;
        projection[1][y - x + (n - 1)] = true;
        projection[2][x + y] = true;

    }

    private void removeCoordinate(int n, int x, int y, boolean[][] projection) {
        projection[0][x] = false;
        projection[1][y - x + (n - 1)] = false;
        projection[2][x + y] = false;
    }

}

// Solution 2_2: 先check当前要加(x,y)是否valid，不valid就return 0； 如果valid的，就把这个点加进去
//然后看加进去这个点之后，是否走到底了，走到底的话就return 1。否则继续走
class Solution2_2 {
    // Solution 2_2: 先check当前要加(x,y)是否valid，不valid就return 0； 如果valid的，就把这个点加进去
    //然后看加进去这个点之后，是否走到底了，走到底的话就return 1。否则继续走
    /**
     isValid，用投影的方式，把每个Q都投影到列，（主+次）对角线上，直接读值判断Q有没有冲突
     然后用backtracking
     */
    public int totalNQueens(int n) {
        int result = 0;
        //corner case
        if (n < 1) {
            return result;
        }
        boolean[][] projection = new boolean[3][2 * n - 1];
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += dfs(i, 0, n, projection);
        }
        return count;
    }

    private int dfs(int row, int col, int n, boolean[][] projection) {
        //base case
        if (!isValid(row, col, projection, n)) {
            return 0;
        }
        if (col == n - 1) {
            return 1;
        }
        // general case
        addCoordinate(n, row, col, projection);
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += dfs(i,col + 1, n, projection);
        }
        removeCoordinate(n, row, col, projection);
        return count;
    }

    private boolean isValid(int x, int y, boolean[][] projection, int n) {
        /*
         检测(x, y)的元素有没有和前面的冲突，DFS按照列走，只需要第[0~y)列已赋值
         分3行：
         第1行表示投影在第一列上
         第2行表示投影在主对角线上
         第3行表示投影在次对角线上
         设左下角是[0, 0]
         */
        return !projection[0][x] && !projection[1][y - x + (n - 1)] && !projection[2][x + y];
    }

    private void addCoordinate(int n, int x, int y, boolean[][] projection) {
        projection[0][x] = true;
        projection[1][y - x + (n - 1)] = true;
        projection[2][x + y] = true;

    }

    private void removeCoordinate(int n, int x, int y, boolean[][] projection) {
        projection[0][x] = false;
        projection[1][y - x + (n - 1)] = false;
        projection[2][x + y] = false;
    }

}

}