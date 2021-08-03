//Given a matrix consists of 0 and 1, find the distance of the nearest 0 for eac
//h cell. 
//
// The distance between two adjacent cells is 1. 
//
// 
//
// Example 1: 
//
// 
//Input:
//[[0,0,0],
// [0,1,0],
// [0,0,0]]
//
//Output:
//[[0,0,0],
// [0,1,0],
// [0,0,0]]
// 
//
// Example 2: 
//
// 
//Input:
//[[0,0,0],
// [0,1,0],
// [1,1,1]]
//
//Output:
//[[0,0,0],
// [0,1,0],
// [1,2,1]]
// 
//
// 
//
// Note: 
//
// 
// The number of elements of the given matrix will not exceed 10,000. 
// There are at least one 0 in the given matrix. 
// The cells are adjacent in only four directions: up, down, left and right. 
// 
// Related Topics Depth-first Search Breadth-first Search

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode0542Zero1Matrix {
    // Java: 01-matrix
    public static void main(String[] args) {
        Solution solution = new Leetcode0542Zero1Matrix().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    private final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public int[][] updateMatrix(int[][] matrix) {
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return null;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] res = new int[rows][cols];
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(i * cols + j);
                }
            }
        }
        
        int minLen = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                int row = cur / cols;
                int col = cur % cols;
                for (int[] dir : DIRECTIONS) {
                    int r = row + dir[0];
                    int c = col + dir[1];
                    if (r >= 0 && r < rows && c >= 0 && c < cols && matrix[r][c] == 1
                            && res[r][c] == 0) {
                        queue.offer(r * cols + c);
                        res[r][c] = minLen;
                    }
                }
            }
            minLen++;
        }
        return res;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}