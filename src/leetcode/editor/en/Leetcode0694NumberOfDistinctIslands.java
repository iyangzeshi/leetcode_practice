//Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (r
//epresenting land) connected 4-directionally (horizontal or vertical.) You may as
//sume all four edges of the grid are surrounded by water. 
//
// Count the number of distinct islands. An island is considered to be the same 
//as another if and only if one island can be translated (and not rotated or refle
//cted) to equal the other. 
//
// Example 1: 
// 
//11000
//11000
//00011
//00011
// 
//Given the above grid map, return 1.
// 
//
// Example 2: 
// 11011
//10000
//00001
//11011 
//Given the above grid map, return 3. 
//Notice that:
// 
//11
//1
// 
//and
// 
// 1
//11
// 
//are considered different island shapes, because we do not consider reflection 
/// rotation.
// 
//
// Note:
//The length of each dimension in the given grid does not exceed 50.
// Related Topics Hash Table Depth-first Search 
// 👍 870 👎 56

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 2020-08-27 20:48:52
// Jesse Yang
public class Leetcode0694NumberOfDistinctIslands {

	// Java: number-of-distinct-islands
	public static void main(String[] args) {
		Solution sol = new Leetcode0694NumberOfDistinctIslands().new Solution();
		// TO TEST
        int[][] grid = {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}
        };
        for (int[] row: grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("number of distinct islands is");
        int res = sol.numDistinctIslands(grid);
		System.out.println(res);
	}

//leetcode submit region begin(Prohibit modification and deletion)
/*
核心: 岛屿形状相同 = 岛屿中所有坐标点的相对位置相同
step 1: DFS 找到一个岛屿的所有坐标
step 2: normalize（归一化）让形状可以对比
step 3: 把 normalize 后的 shape 放进 HashSet 去重
T(m,n) = O(m × n)
S(m,n) = O(m × n)
 */
class Solution {
    
    private final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    
    public int numDistinctIslands(int[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
    
        /*
        里面的只能是List<List<Integer>>, 不能是List<int[]>，否则HashSet去重会出问题
        因为两个值相同的Array并不能equals相同
         */
        Set<List<List<Integer>>> shapes = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited.contains(i * cols + j) && grid[i][j] == 1) {
                    List<List<Integer>> shape = new ArrayList<>();
                    dfs(grid, i, j, shape, visited);
                    normalize(shape);
                    shapes.add(shape);
                }
            }
        }
        return shapes.size();
    }
    
    private void dfs(int[][] board, int row, int col, List<List<Integer>> shape,
            Set<Integer> visited) {
        int rows = board.length;
        int cols = board[0].length;
        // no success case
        
        // base case - fail
        if (row < 0 || row >= rows || col < 0 || col >= cols || board[row][col] == 0
                || visited.contains(row * cols + col)) {
            return;
        }
        visited.add(row * cols + col);
        shape.add(Arrays.asList(row, col));
        for (int[] dir: DIRECTIONS) {
            dfs(board, row + dir[0], col + dir[1], shape, visited);
        }
    }
    
    private void normalize(List<List<Integer>> shape) {
        /*shape.sort((o1, o2) -> o1.get(0) - o2.get(0) != 0 ?
                o1.get(0) - o2.get(0) :
                o1.get(1) - o2.get(1));*/
        // the coordinate of the most left point in first row of the shape
        List<Integer> upperLeft = new ArrayList<>(shape.get(0));
        for (List<Integer> list: shape) {
            for (int i = 0; i < list.size(); i++) {
                list.set(i, list.get(i) - upperLeft.get(i));
            }
        }
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: dfs 带方向表示从头开始的转向
// T(m, n) = O(m, n), S(m, n) = O(m, n)
// 11 ms,击败了39.40% 的Java用户, 39.9 MB,击败了60.93% 的Java用户
class Solution1 {
    
    public int numDistinctIslands(int[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        Set<String> shapes = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited.contains(i * cols + j) && grid[i][j] == 1) {
                    StringBuilder path = new StringBuilder();
                    dfs(grid, i, j, path, "start", visited);
                    shapes.add(path.toString());
                }
            }
        }
        return shapes.size();
    }
    
    private void dfs(int[][] board, int row, int col, StringBuilder path, String turn,
            Set<Integer> visited) {
        int rows = board.length;
        int cols = board[0].length;
        // no success case
        
        // base case - fail
        if (row < 0 || row >= rows || col < 0 || col >= cols || board[row][col] == 0
                || visited.contains(row * cols + col)) {
            return;
        }
        visited.add(row * cols + col);
        path.append(turn);
        
        dfs(board, row - 1, col, path, "u", visited); // go upper
        dfs(board, row, col + 1, path, "r", visited); // go right
        dfs(board, row + 1, col, path, "d", visited); // go down
        dfs(board, row, col - 1, path, "l", visited); // go left
        path.append("end");
    }
    
}

// Solution 2: dfs, 把所有的shape normalize，先按照行排序，然后列排序，每个值减掉这个shape的上面最左边的点
// T(m, n) = O(m, n), S(m, n) = O(m, n)
// 16 ms,击败了25.15% 的Java用户, 39.4 MB,击败了95.47% 的Java用户
class Solution2 {
    
    public int numDistinctIslands(int[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        Set<List<List<Integer>>> shapes = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited.contains(i * cols + j) && grid[i][j] == 1) {
                    List<List<Integer>> shape = new ArrayList<>();
                    dfs(grid, i, j, shape, visited);
                    normalize(shape);
                    shapes.add(shape);
                }
            }
        }
        return shapes.size();
    }
    
    private void dfs(int[][] board, int row, int col, List<List<Integer>> shape,
            Set<Integer> visited) {
        int rows = board.length;
        int cols = board[0].length;
        // no success case
        
        // base case - fail
        if (row < 0 || row >= rows || col < 0 || col >= cols || board[row][col] == 0
                || visited.contains(row * cols + col)) {
            return;
        }
        visited.add(row * cols + col);
        shape.add(Arrays.asList(row, col));
        
        dfs(board, row - 1, col, shape, visited); // go upper
        dfs(board, row, col + 1, shape, visited); // go right
        dfs(board, row + 1, col, shape, visited); // go down
        dfs(board, row, col - 1, shape, visited); // go left
    }
    
    private void normalize(List<List<Integer>> shape) {
        // the coordinate of the most left point in first row of the shape
        List<Integer> upperLeft = new ArrayList<>(shape.get(0));
        for (List<Integer> list: shape) {
            for (int i = 0; i < list.size(); i++) {
                list.set(i, list.get(i) - upperLeft.get(i));
            }
        }
    }
    
}

}