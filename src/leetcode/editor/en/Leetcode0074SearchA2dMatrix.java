//Write an efficient algorithm that searches for a value in an m x n matrix. Thi
//s matrix has the following properties: 
//
// 
// Integers in each row are sorted from left to right. 
// The first integer of each row is greater than the last integer of the previou
//s row. 
// 
//
// Example 1: 
//
// 
//Input:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 3
//Output: true
// 
//
// Example 2: 
//
// 
//Input:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 13
//Output: false 
// Related Topics Array Binary Search 
// 👍 1881 👎 161

package leetcode.editor.en;

// 2020-08-04 11:26:35
// Zeshi Yang
public class Leetcode0074SearchA2dMatrix{
    // Java: search-a-2d-matrix
    public static void main(String[] args) {
        Solution sol = new Leetcode0074SearchA2dMatrix().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int left = 0;
        int right = row * col - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[mid / col][mid % col] == target) {
                return true;
            } else if (matrix[mid / col][mid % col] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}