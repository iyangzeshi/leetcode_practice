/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral
 order.

 Example 1:

 
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
 

 Example 2:
 
Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 Related Topics Array
 👍 2435 👎 574
*/

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
// 2020-07-26 13:00:43
// Jesse Yang
public class Leetcode0054SpiralMatrix{
    // Java: spiral-matrix
    public static void main(String[] args) {
        Solution sol = new Leetcode0054SpiralMatrix().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)

/*
step 1: traverse the matrix layer by layer, from outermost layer to the innermost layer
    the beginning point of every layer is row, col = {0, 0}, then {1, 1}, then {2, 2}
step 2: for every layer, traverse by direction
    right, down, left then up: {{0, 1}, {1, 0}, {0, -1}, {-1,0}};
    every layer has its own width and height
    until width and height is <= 1
 */
class Solution {
    
    private int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right, down, left, up
    
    public List<Integer> spiralOrder(int[][] matrix) {
        // corner case
        
        // general case
        List<Integer> res = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = 0;
        int col = 0;
        int height = rows;
        int width = cols;
        
        for (; height > 1 && width > 1; height -=2, width -=2) {
            int i = row;
            int j = col;
            for (int[] dir: DIRECTIONS) {
                int size = dir[0] == 0 ? width - 1 : height - 1;
                for (int k = 0; k < size; k++) {
                    res.add(matrix[i][j]);
                    i += dir[0];
                    j += dir[1];
                }
            }
            row++;
            col++;
        }
        /* after for loop, height <= 1 or width <= 1, which means
        (width, height) =
            (1,1) traverse
            (1,0) traverse
            (0,1) traverse
            (0,0) stop
        case 1: height == 1 or width == 1, need to continue traversre
        case 2: height = 0 or width == 0; stop traverse
         */
        
        if (width == 1) {
            int size = height;
            int i = row;
            int j = col;
            for (int k = 0; k < size; k++) {
                res.add(matrix[i][j]);
                i += 1;
            }
        } else if (height == 1){ // height = 1
            int size = width;
            int i = row;
            int j = col;
            for (int k = 0; k < size; k++) {
                res.add(matrix[i][j]);
                j += 1;
            }
        } else {
        
        }
        return res;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
/** 面试的时候，用Solution 2 */

// Solution 1: for循环，遍历所有的点，先往右，往下，往左，再往上， T(m, n) = O(m * n), S(m, n( = O(m * n);
class Solution1 {
    
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return res;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = 0;
        int col = 0;
        int height = rows;
        int width = cols;
        while (height > 1 && width > 1) {
            //traverse right
            for (int i = col; i < col + width - 1; i++) {
                res.add(matrix[row][i]);
            }
            //traverse down
            for (int i = row; i < row + height - 1; i++) {
                res.add(matrix[i][col + width - 1]);
            }
            //traverse left
            for (int i = col + width - 1; i > col; i--) {
                res.add(matrix[row + height - 1][i]);
            }
            //traverse up
            for (int i = row + height - 1; i > row; i--) {
                res.add(matrix[i][col]);
            }
            row++;
            col++;
            height -= 2;
            width -= 2;
        }
        
        if (height == 1) {
            for (int i = col; i < col + width; i++) {
                res.add(matrix[row][i]);
            }
        } else if (width == 1) {
            for (int i = row; i < row + height; i++) {
                res.add(matrix[i][col]);
            }
        }
        return res;
    }
    
}


/*
Solution 2:
step 1: traverse the matrix layer by layer, from outermost layer to the innermost layer
    the beginning point of every layer is row, col = {0, 0}, then {1, 1}, then {2, 2}
step 2: for every layer, traverse by direction
    right, down, left then up: {{0, 1}, {1, 0}, {0, -1}, {-1,0}};
    every layer has its own width and height
    until width and height is <= 1
 */
class Solution2 {
    
    private int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right, down, left, up
    
    public List<Integer> spiralOrder(int[][] matrix) {
        // corner case
        
        // general case
        List<Integer> res = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = 0;
        int col = 0;
        int height = rows;
        int width = cols;
        
        for (; height > 1 && width > 1; height -=2, width -=2) {
            int i = row;
            int j = col;
            for (int[] dir: DIRECTIONS) {
                int size = dir[0] == 0 ? width - 1 : height - 1;
                for (int k = 0; k < size; k++) {
                    res.add(matrix[i][j]);
                    i += dir[0];
                    j += dir[1];
                }
            }
            row++;
            col++;
        }
        /* after for loop, height <= 1 or width <= 1, which means
        (width, height) =
            (1,1) traverse
            (1,0) traverse
            (0,1) traverse
            (0,0) stop
        case 1: height == 1 or width == 1, need to continue traverse
        case 2: height = 0 or width == 0; stop traverse
         */
        
        if (width == 1) {
            int size = height;
            int i = row;
            int j = col;
            for (int k = 0; k < size; k++) {
                res.add(matrix[i][j]);
                i += 1;
            }
        } else if (height == 1){ // height = 1
            int size = width;
            int i = row;
            int j = col;
            for (int k = 0; k < size; k++) {
                res.add(matrix[i][j]);
                j += 1;
            }
        } else {
        
        }
        return res;
    }
    
}

}
