//Given a positive integer n, generate a square matrix filled with elements from
// 1 to n2 in spiral order. 
//
// Example: 
//
// 
//Input: 3
//Output:
//[
// [ 1, 2, 3 ],
// [ 8, 9, 4 ],
// [ 7, 6, 5 ]
//]
// 
// Related Topics Array 
// 👍 991 👎 111

package leetcode.editor.en;

import java.util.Arrays;

// 2020-07-26 13:01:03
// Zeshi Yang
public class Leetcode0059SpiralMatrixIi{
    // Java: spiral-matrix-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0059SpiralMatrixIi().new Solution();
        // TO TEST
        int n = 3;
        int[][] matrix = sol.generateMatrix(n);
        System.out.println(Arrays.deepToString(matrix));
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    private int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right, down, left, up
    
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int index = 0; // 正方形边框的左上角坐标
        int count = 1;
        for (int size = n; size > 1; size -= 2) {
            int i = index;
            int j = index;
            for (int[] dir: DIRECTIONS) {
                for (int step = 0; step < size - 1; step++) {
                    matrix[i][j] = count++;
                    i += dir[0];
                    j += dir[1];
                }
            }
            index++;
        }
        if (n % 2 == 1) {
            matrix[index][index] = count;
        }
        return matrix;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
/* 面试的时候，用Solution 2 */
// Solution 1：从外面往里面走，设置好初始的height和width, T(m ,n) = O(m * n), S(m ,n) = O(m * n)
// 0 ms,击败了100.00% 的Java用户, 37 MB,击败了81.11% 的Java用户
class Solution1 {
    
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        
        int row = 0; // 正方形边框的左上角横坐标
        int col = 0; // 正方形边框的左上角纵坐标
        int height = n;
        int width = n;
        int num = 1;
        
        while (height > 1 && width > 1) {
            //traverse right
            for (int i = col; i < col + width - 1; i++) {
                matrix[row][i] = num++;
            }
            //traverse down
            for (int i = row; i < row + height - 1; i++) {
                matrix[i][col + width - 1] = num++;
            }
            //traverse left
            for (int i = col + width - 1; i > col; i--) {
                matrix[row + height - 1][i] = num++;
            }
            //traverse up
            for (int i = row + height - 1; i > row; i--) {
                matrix[i][col] = num++;
            }
            row++;
            col++;
            height -= 2;
            width -= 2;
        }
        //edge case
        if (height == 1) {
            for (int i = col; i < col + width; i++) {
                matrix[row][i] = num;
                num++;
            }
        } else { // width == 1
            for (int i = row; i < row + height; i++) {
                matrix[i][col] = num;
                num++;
            }
        }
        return matrix;
    }
    
}

// Solution 2: 思路同上Solution 1的代码简化版，T(m ,n) = O(m * n), S(m ,n) = O(m * n)
// 0 ms,击败了100.00% 的Java用户, 37 MB,击败了67.56% 的Java用户
class Solution2 {
    
    private int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right, down, left, up
    
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int index = 0; // 正方形边框的左上角坐标
        int count = 1;
        for (int size = n; size > 1; size -= 2) {
            int i = index;
            int j = index;
            for (int[] dir: DIRECTIONS) {
                for (int step = 0; step < size - 1; step++) {
                    matrix[i][j] = count++;
                    i += dir[0];
                    j += dir[1];
                }
            }
            index++;
        }
        if (n % 2 == 1) {
            matrix[index][index] = count;
        }
        return matrix;
    }
    
}
}