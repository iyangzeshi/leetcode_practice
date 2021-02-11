//On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) facing
// east. 
//
// Here, the north-west corner of the grid is at the first row and column, and t
//he south-east corner of the grid is at the last row and column. 
//
// Now, we walk in a clockwise spiral shape to visit every position in this grid
//. 
//
// Whenever we would move outside the boundary of the grid, we continue our walk
// outside the grid (but may return to the grid boundary later.) 
//
// Eventually, we reach all R * C spaces of the grid. 
//
// Return a list of coordinates representing the positions of the grid in the or
//der they were visited. 
//
// 
//
// Example 1: 
//
// 
//Input: R = 1, C = 4, r0 = 0, c0 = 0
//Output: [[0,0],[0,1],[0,2],[0,3]]
//
//
// 
//
// 
//
// Example 2: 
//
// 
//Input: R = 5, C = 6, r0 = 1, c0 = 4
//Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,
//3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1]
//,[4,0],[3,0],[2,0],[1,0],[0,0]]
//
//
// 
//
// 
// 
// 
//
// Note: 
//
// 
// 1 <= R <= 100 
// 1 <= C <= 100 
// 0 <= r0 < R 
// 0 <= c0 < C 
// 
// 
// Related Topics Math 
// 👍 293 👎 367

package leetcode.editor.en;

import java.util.Arrays;

// 2021-02-10 17:29:29
// Zeshi Yang
public class Leetcode0885SpiralMatrixIii{
    // Java: spiral-matrix-iii
    public static void main(String[] args) {
        Solution sol = new Leetcode0885SpiralMatrixIii().new Solution();
        // TO TEST
        int[][] res = sol.spiralMatrixIII(1, 4, 0, 0);
        System.out.println(Arrays.deepToString(res));
    }
//leetcode submit region begin(Prohibit modification and deletion)
// T(R, C) = O(max(R, C) ^2), S(R, C) = O(R, C)
// 4 ms,击败了45.59% 的Java用户, 40.5 MB,击败了22.33% 的Java用户
/*
定义好方向，按顺序分别是右，下，左，上
分别走，size步，size步, size + 1步，size + 1步
起点initialize之后
从size = 1开始往右走，每次走完一圈之后，就size += 2
如果走到的位置在矩阵外面，就不加到res[][]里面，让他继续走，走到能在matrix里面的范围为止
走到数组int[][] res放满的时候，循环结束
 */
class Solution {
    
    final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {

        int[][] res = new int[R * C][2];
        int index = 0; // index in the output
        int row = r0;
        int col = c0;
        
        res[index++] = new int[]{row, col};
        if (R * C == 1) {
            return res;
        }
    
        for (int size = 1;; size += 2) {/* size < 2 * (R + C)这一句可以删掉*/
            for (int dir = 0; dir < 4; dir++) {  // dir: direction index
                int steps = size + (dir / 2);  // number of steps in this direction
                for (int step = 0; step < steps; step++) {  // for each step in this direction...
                    // step in the dir-th direction
                    row += DIRECTIONS[dir][0];
                    col += DIRECTIONS[dir][1];
                    if (0 <= row && row < R && 0 <= col && col < C) {
                        res[index++] = new int[]{row, col};
                        if (index == R * C) {
                            return res;
                        }
                    }
                }
            }
        }
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}