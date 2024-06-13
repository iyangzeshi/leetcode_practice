//Given a 2D matrix matrix, find the sum of the elements inside the rectangle de
//fined by its upper left corner (row1, col1) and lower right corner (row2, col2).
// 
//
// Implement the NumMatrix class: 
//
// 
// NumMatrix(int[][] matrix) initializes the object with the integer matrix matr
//ix. 
// void update(int row, int col, int val) updates the value of matrix[row][col] 
//to be val. 
// int sumRegion(int row1, int col1, int row2, int col2) returns the sum of the 
//elements of the matrix array inside the rectangle defined by its upper left corn
//er (row1, col1) and lower right corner (row2, col2). 
// 
//
// 
// Example 1: 
//
// 
//Input
//["NumMatrix", "sumRegion", "update", "sumRegion"]
//[[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 
//3, 0, 5]]], [2, 1, 4, 3], [3, 2, 2], [2, 1, 4, 3]]
//Output
//[null, 8, null, 10]
//
//Explanation
//NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 
//0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
//numMatrix.sumRegion(2, 1, 4, 3); // return 8
//numMatrix.update(3, 2, 2);
//numMatrix.sumRegion(2, 1, 4, 3); // return 10
// 
//
// 
// Constraints: 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 200 
// -105 <= matrix[i][j] <= 105 
// 0 <= row < m 
// 0 <= col < n 
// -105 <= val <= 105 
// 0 <= row1 <= row2 < m 
// 0 <= col1 <= col2 < n 
// At most 104 calls will be made to sumRegion and update. 
// 
// Related Topics Binary Indexed Tree Segment Tree 
// 👍 501 👎 66

package leetcode.editor.en;

// 2021-04-07 21:59:28
// Jesse Yang
public class Leetcode0308RangeSumQuery2dMutable{
    // Java: range-sum-query-2d-mutable
    public static void main(String[] args) {
        // NumMatrix numMatrix = new Leetcode0308RangeSumQuery2dMutable().new NumMatrix();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class NumMatrix {

    public NumMatrix(int[][] matrix) {
        
    }
    
    public void update(int row, int col, int val) {
        
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return 0;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
//leetcode submit region end(Prohibit modification and deletion)

}