//Given a 2D matrix matrix, find the sum of the elements inside the rectangle de
//fined by its upper left corner (row1, col1) and lower right corner (row2, col2).
// 
//
// 
// 
//The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) 
//and (row2, col2) = (4, 3), which contains sum = 8.
// 
//
// Example: 
// 
//Given matrix = [
//  [3, 0, 1, 4, 2],
//  [5, 6, 3, 2, 1],
//  [1, 2, 0, 1, 5],
//  [4, 1, 0, 1, 7],
//  [1, 0, 3, 0, 5]
//]
//
//sumRegion(2, 1, 4, 3) -> 8
//sumRegion(1, 1, 2, 2) -> 11
//sumRegion(1, 2, 2, 4) -> 12
// 
// 
//
// Note: 
// 
// You may assume that the matrix does not change. 
// There are many calls to sumRegion function. 
// You may assume that row1 ≤ row2 and col1 ≤ col2. 
// 
// Related Topics Dynamic Programming 
// 👍 1032 👎 179

package leetcode.editor.en;

// 2020-07-26 13:33:17
// Jesse Yang
public class Leetcode0304RangeSumQuery2dImmutable{
    // Java: range-sum-query-2d-immutable
    public static void main(String[] args) {
        int[][] matrix = {{1,2}, {3,4}};
        NumMatrix sol = new Leetcode0304RangeSumQuery2dImmutable().new NumMatrix(matrix);
        // TO TEST
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class NumMatrix {
    
    private int[][] sums;
    
    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int rows = matrix.length;;
        int cols = matrix[0].length; // column
        
        // sum[i][j] from matrix[0, 0] to matrix[i - 1][j -  1] inclusively
        sums = new int[rows + 1][cols + 1];
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                sums[r + 1][c + 1] = sums[r + 1][c] + sums[r][c + 1] + matrix[r][c] - sums[r][c];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sums[row2 + 1][col2 + 1] - sums[row1][col2 + 1] - sums[row2 + 1][col1] + sums[row1][col1];
    }
    
}

/*
  Your NumMatrix object will be instantiated and called as such:
  NumMatrix obj = new NumMatrix(matrix);
  int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
//leetcode submit region end(Prohibit modification and deletion)

}