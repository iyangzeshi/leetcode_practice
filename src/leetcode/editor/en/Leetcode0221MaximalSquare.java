//Given a 2D binary matrix filled with 0's and 1's, find the largest square cont
//aining only 1's and return its area. 
//
// Example: 
//
// 
//Input: 
//
//1 0 1 0 0
//1 0 1 1 1
//1 1 1 1 1
//1 0 0 1 0
//
//Output: 4
// Related Topics Dynamic Programming 
// 👍 3158 👎 79

package leetcode.editor.en;

// 2020-07-26 13:27:00
// Jesse Yang
public class Leetcode0221MaximalSquare{
    // Java: maximal-square
    public static void main(String[] args) {
        Solution sol = new Leetcode0221MaximalSquare().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int maximalSquare(char[][] matrix) {
        // corner case
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        //general case
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] result = new int[cols];
        int prev = 0;
        int size = matrix[0][0] - '0';

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0 || matrix[i][j] == '0') {
                    result[(j - 1 + cols) % cols] = prev;
                    prev = matrix[i][j] - '0';
                }
                else {
                    int temp = prev;
                    prev = Math.min(result[j - 1], Math.min(prev, result[j])) + 1;
                    result[j - 1] = temp;
                }
                size = Math.max(size, prev);
            }
        }
        return size * size;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: dynamic programming， 初始化单独写
class Solution1_1 {

    public int maximalSquare(char[][] matrix) {
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] result = new int[rows][cols];
        // base case，初始化没有缺，也没有重复的部分
        result[0][0] = matrix[0][0] - '0';
        int size = matrix[0][0] - '0';

        for (int i = 1; i < rows; i++) {
            result[i][0] = matrix[i][0] - '0';
            size = Math.max(size, result[i][0]);
        }
        for (int j = 1; j < cols; j++) {
            result[0][j] = matrix[0][j] - '0';
            size = Math.max(size, result[0][j]);
        }

        // general case
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == '0') {
                    result[i][j] = 0;
                } else {
                    result[i][j] = Math.min(result[i - 1][j - 1],Math.min(result[i][j - 1], result[i - 1][j])) + 1;
                }
                size = Math.max(size, result[i][j]);
            }
        }
        return size * size;
    }
}

// Solution 1.2: dynamic programming，初始化合在一起写
class Solution1_2 {

    public int maximalSquare(char[][] matrix) {
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] result = new int[rows][cols];
        int size = matrix[0][0] - '0';


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0 || matrix[i][j] == '0') {
                    result[i][j] = matrix[i][j] - '0';
                } else {
                    result[i][j] = Math.min(result[i - 1][j - 1],Math.min(result[i][j - 1], result[i - 1][j])) + 1;
                }
                size = Math.max(size, result[i][j]);
            }
        }
        return size * size;
    }
}

// Solution 2: dynamic programming with reduced space complexity
class Solution2 {

    public int maximalSquare(char[][] matrix) {
        // corner case
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        //general case
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] result = new int[cols];
        int prev = 0;
        int size = matrix[0][0] - '0';

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0 || matrix[i][j] == '0') {
                    result[(j - 1 + cols) % cols] = prev;
                    prev = matrix[i][j] - '0';
                } else {
                    int temp = prev;
                    prev = Math.min(result[j - 1], Math.min(prev, result[j])) + 1;
                    result[j - 1] = temp;
                }
                size = Math.max(size, prev);
            }
        }
        return size * size;
    }
}
}