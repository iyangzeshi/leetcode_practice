//You want to build a house on an empty land which reaches all buildings in the 
//shortest amount of distance. You can only move up, down, left and right. You are
// given a 2D grid of values 0, 1 or 2, where: 
//
// 
// Each 0 marks an empty land which you can pass by freely. 
// Each 1 marks a building which you cannot pass through. 
// Each 2 marks an obstacle which you cannot pass through. 
// 
//
// Example: 
//
// 
//Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
//
//1 - 0 - 2 - 0 - 1
//|   |   |   |   |
//0 - 0 - 0 - 0 - 0
//|   |   |   |   |
//0 - 0 - 1 - 0 - 0
//
//Output: 7 
//
//Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at 
//(0,2),
//             the point (1,2) is an ideal empty land to build a house, as the t
//otal 
//             travel distance of 3+3+1=7 is minimal. So return 7. 
//
// Note: 
//There will be at least one building. If it is not possible to build such house
// according to the above rules, return -1. 
// Related Topics Breadth-first Search

package leetcode.editor.en;
import java.util.*;

public class Leetcode0317ShortestDistanceFromAllBuildings {
    // Java: shortest-distance-from-all-buildings
    public static void main(String[] args) {
        Solution solution = new Leetcode0317ShortestDistanceFromAllBuildings().new Solution();
        // TO TEST
        
        System.out.println();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        private final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
        public int shortestDistance(int[][] grid) {
            // corner case
            if (grid == null || grid.length == 0) {
                return -1;
            }
        
            int rows = grid.length;
            int cols = grid[0].length;
            int[][] distance = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0 ; j < cols; j++) {
                    if (grid[i][j] == 1) {
                        bfs(grid, i, j, distance);
                    }
                }
            }
            int minDistance = Integer.MAX_VALUE;
        
            // edge case, some 0 can not reach to 1
            for (int i = 0; i < rows; i++) {
                for (int j = 0 ; j < cols; j++) {
                    if (grid[i][j] == 0 && distance[i][j] != 0) {
                        minDistance = Math.min(minDistance, distance[i][j]);
                    }
                }
            }
            return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
        }
    
        private void bfs(int[][] matrix, int i, int j, int[][] distance) {
            int rows = matrix.length;
            int cols = matrix[0].length;
            boolean[][] visited = new boolean[rows][cols];
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i * cols + j);
            int minLen = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                while(size-- > 0 ) {
                    int cur = queue.poll();
                    int row = cur / cols;
                    int col = cur % cols;
                    for(int[] dir: DIRECTIONS) {
                        int r = row + dir[0];
                        int c = col + dir[1];
                        if (r >= 0 && r < rows && c >= 0 && c < cols && matrix[r][c] == 0 && !visited[r][c]) {
                            queue.offer(r * cols + c);
                            visited[r][c] = true;
                            distance[r][c] += minLen;
                        }
                    }
                }
                minLen++;
            }
            // check whether some empty rooms can reach to the buildings
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (matrix[r][c] == 0 && !visited[r][c]) {
                        matrix[r][c] = 2;
                    }
                }
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}