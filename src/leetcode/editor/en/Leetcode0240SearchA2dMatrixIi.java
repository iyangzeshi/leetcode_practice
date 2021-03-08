//Write an efficient algorithm that searches for a value in an m x n matrix. Thi
//s matrix has the following properties: 
//
// 
// Integers in each row are sorted in ascending from left to right. 
// Integers in each column are sorted in ascending from top to bottom. 
// 
//
// Example: 
//
// Consider the following matrix: 
//
// 
//[
//  [1,   4,  7, 11, 15],
//  [2,   5,  8, 12, 19],
//  [3,   6,  9, 16, 22],
//  [10, 13, 14, 17, 24],
//  [18, 21, 23, 26, 30]
//]
// 
//
// Given target = 5, return true. 
//
// Given target = 20, return false. 
// Related Topics Binary Search Divide and Conquer 
// 👍 3194 👎 75

package leetcode.editor.en;

// 2020-07-26 00:58:45
// Zeshi Yang
public class Leetcode0240SearchA2dMatrixIi {

	// Java: search-a-2d-matrix-ii
	public static void main(String[] args) {
		Solution sol = new Leetcode0240SearchA2dMatrixIi().new Solution();
		// TO TEST
        int[][] matrix = {{-5}};
        int target = -5;
        boolean res = sol.searchMatrix(matrix, target);
		System.out.println(res);
	}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public boolean searchMatrix(int[][] matrix, int target) {
        // start our "pointer" in the bottom-left
        int row = matrix.length - 1;
        int col = 0;
        
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else { // found it
                return true;
            }
        }
        return false;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    
    public boolean searchMatrix(int[][] matrix, int target) {
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        // general case
        int row1 = chooseTopRow(matrix, target);
        int row2 = chooseBottomRow(matrix, target, row1);
        for (int i = row1; i <= row2; i++) {
            int[] nums = matrix[i];
            int start = 0;
            int end = nums.length - 1;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (nums[mid] == target) {
                    return true;
                } else if (nums[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return false;
    }
    
    // 找到第一个末尾数字大于等于target的行
    private int chooseTopRow(int[][] matrix, int target) {
        int cols = matrix[0].length;
        int start = 0;
        int end = matrix.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid][0] == target) {
                return mid;
            } else if (matrix[mid][cols - 1] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
    
    // 找到最后一个元素小于等于target的最大行index
    private int chooseBottomRow(int[][] matrix, int target, int start) {
        int end = matrix.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid][0] == target) {
                return mid;
            } else if (matrix[mid][0] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }
    
}

}