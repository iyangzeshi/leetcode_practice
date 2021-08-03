//You are given an n x n 2D matrix representing an image. 
//
// Rotate the image by 90 degrees (clockwise). 
//
// Note: 
//
// You have to rotate the image in-place, which means you have to modify the inp
//ut 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation. 
//
// Example 1: 
//
// 
//Given input matrix = 
//[
//  [1,2,3],
//  [4,5,6],
//  [7,8,9]
//],
//
//rotate the input matrix in-place such that it becomes:
//[
//  [7,4,1],
//  [8,5,2],
//  [9,6,3]
//]
// 
//
// Example 2: 
//
// 
//Given input matrix =
//[
//  [ 5, 1, 9,11],
//  [ 2, 4, 8,10],
//  [13, 3, 6, 7],
//  [15,14,12,16]
//], 
//
//rotate the input matrix in-place such that it becomes:
//[
//  [15,13, 2, 5],
//  [14, 3, 4, 1],
//  [12, 6, 8, 9],
//  [16, 7,10,11]
//]
// 
// Related Topics Array 
// 👍 3160 👎 232

package leetcode.editor.en;

// 2020-07-26 13:47:47
// Zeshi Yang
public class Leetcode0048RotateImage{
    // Java: rotate-image
    public static void main(String[] args) {
        Solution sol = new Leetcode0048RotateImage().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void rotate(int[][] matrix) {
        // corner case
        if (matrix == null || matrix.length <= 1) {
            return;
        }

        //general case
        int size = matrix.length;
        matrixRotate(matrix, 0, size);
    }

    private void matrixRotate(int[][] matrix, int offset, int size) {
        // base case
        if (size <= 1) {
            return;
        }
        for (int i = 0; i < size - 1; i++) {
            int temp = matrix[offset][offset + i];
            matrix[offset][offset + i] = matrix[offset + size - 1 - i][offset];
            matrix[offset + size - 1 - i][offset] = matrix[offset + size - 1][offset + size - 1 - i];
            matrix[offset + size - 1][offset + size - 1 - i] = matrix[offset + i][offset + size - 1];
            matrix[offset + i][offset + size - 1] = temp;
        }

        matrixRotate(matrix, offset + 1, size - 2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}