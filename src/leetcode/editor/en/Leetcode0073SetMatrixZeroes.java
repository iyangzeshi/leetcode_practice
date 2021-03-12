//Given an m x n matrix. If an element is 0, set its entire row and column to 0.
// Do it in-place. 
//
// Follow up: 
//
// 
// A straight forward solution using O(mn) space is probably a bad idea. 
// A simple improvement uses O(m + n) space, but still not the best solution. 
// Could you devise a constant space solution? 
// 
//
// 
// Example 1: 
//
// 
//Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
//Output: [[1,0,1],[0,0,0],[1,0,1]]
// 
//
// Example 2: 
//
// 
//Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
//Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
// 
//
// 
// Constraints: 
//
// 
// m == matrix.length 
// n == matrix[0].length 
// 1 <= m, n <= 200 
// -231 <= matrix[i][j] <= 231 - 1 
// 
// Related Topics Array 
// 👍 2963 👎 349

package leetcode.editor.en;

// 2021-01-19 12:10:44
// Zeshi Yang
public class Leetcode0073SetMatrixZeroes{
    // Java: set-matrix-zeroes
    public static void main(String[] args) {
        Solution sol = new Leetcode0073SetMatrixZeroes().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// 先遍历第一行和第一列，再遍历剩下的点，为0做标记T(n, m) = O(n * m), S(n, m) = O(1)
// 1 ms,击败了92.24% 的Java用户, 40.8 MB,击败了42.51% 的Java用户
/*
step1 :
    设置两个boolean firstRow 和 firstCol,分别表示第一行和第一列有没有0
    遍历第一行和第一列，update firstRow 和 firstCol
    
step2:
    遍历剩下来的所有元素，遍历到0的时候，就把这个0最左边和坐上面的地方标记成0
   
step3:
    遍历除了0th row 和col之外的所有元素，如果这个元素的最左边或者最上面的话，就把这个元素设置成0
        
step 4:
     如果firstRow == true，把第一行都变成0
     如果firstCol == true，把第一列都变成0
 */
class Solution {
    
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        boolean row0Has0 = false; // the first rows has 0
        boolean col0Has0 = false; // the first column has 0
        
        // step 1: traverse first row to judge if it exists 0
        for (int i = 0; i < cols; i++) {
            if (matrix[0][i] == 0) {
                row0Has0 = true;
                break;
            }
        }
        // step 1: traverse first col to judge if it exists 0
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                col0Has0 = true;
                break;
            }
        }
        
        // step 2: traverse leftover elements, update 0's left head and upper head to 0
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        
        // step 3: update leftover elements to 0 if needed
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        // step 4: update first row and column to 0 if need
        if (col0Has0) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
        if (row0Has0) {
            for (int j = 0; j < cols; j++) {
                matrix[0][j] = 0;
            }
            
        }
        
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}