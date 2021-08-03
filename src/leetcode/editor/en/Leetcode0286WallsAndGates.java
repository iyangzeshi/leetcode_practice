//You are given a m x n 2D grid initialized with these three possible values. 
//
// 
// -1 - A wall or an obstacle. 
// 0 - A gate. 
// INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to 
//represent INF as you may assume that the distance to a gate is less than 2147483
//647. 
// 
//
// Fill each empty room with the distance to its nearest gate. If it is impossib
//le to reach a gate, it should be filled with INF. 
//
// Example: 
//
// Given the 2D grid: 
//
// 
//INF  -1  0  INF
//INF INF INF  -1
//INF  -1 INF  -1
//  0  -1 INF INF
// 
//
// After running your function, the 2D grid should be: 
//
// 
//  3  -1   0   1
//  2   2   1  -1
//  1  -1   2  -1
//  0  -1   3   4
// 
// Related Topics Breadth-first Search

package leetcode.editor.en;
import java.util.*;

public class Leetcode0286WallsAndGates {
    // Java: walls-and-gates
    public static void main(String[] args) {
        Solution solution = new Leetcode0286WallsAndGates().new Solution();
        // TO TEST
        
        System.out.println();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private static final int EMPTY = Integer.MAX_VALUE;
    private static final int GATE = 0;
    private final List<int[]> DIRECTIONS = Arrays.asList(
            new int[] { 1,  0},
            new int[] {-1,  0},
            new int[] { 0,  1},
            new int[] { 0, -1}
    );

    public void wallsAndGates(int[][] rooms) {
    
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
            return;
        }
        //先找到矩阵中是Gate 0的位置
        int m = rooms.length;
        if (m == 0) return;
        int n = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (rooms[row][col] == GATE) {
                    q.add(new int[] { row, col });
                }
            }
        }
    
        //用BFS赋值
        //因为是BFS，所以每个点可以最多被赋值一次，且只有是Empty的点会被赋值
        while (!q.isEmpty()) {
            int[] point = q.poll();
            int row = point[0];
            int col = point[1];
            for (int[] direction : DIRECTIONS) { // 遍历4个方向
                int r = row + direction[0];
                int c = col + direction[1];
                if (r >= 0 && r < m && c >= 0 && c < n && rooms[r][c] == EMPTY) {
                    rooms[r][c] = rooms[row][col] + 1;
                    q.add(new int[] { r, c });
                }
            
            }
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)

}