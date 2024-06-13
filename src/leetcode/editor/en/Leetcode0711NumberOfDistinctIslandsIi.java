//Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (r
//epresenting land) connected 4-directionally (horizontal or vertical.) You may as
//sume all four edges of the grid are surrounded by water. 
//
// Count the number of distinct islands. An island is considered to be the same 
//as another if they have the same shape, or have the same shape after rotation (9
//0, 180, or 270 degrees only) or reflection (left/right direction or up/down dire
//ction). 
//
// Example 1: 
// 
//11000
//10000
//00001
//00011
// 
//Given the above grid map, return 1.
// 
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
//are considered same island shapes. Because if we make a 180 degrees clockwise 
//rotation on the first island, then two islands will have the same shapes.
// 
//
// Example 2: 
// 
//11100
//10001
//01001
//01110 
//Given the above grid map, return 2. 
// 
//Here are the two distinct islands:
// 
//111
//1
// 
//and
// 
//1
//1
// 
// 
//Notice that:
// 
//111
//1
// 
//and
// 
//1
//111
// 
//are considered same island shapes. Because if we flip the first array in the u
//p/down direction, then they have the same shapes.
// 
//
// Note:
//The length of each dimension in the given grid does not exceed 50.
// Related Topics Hash Table Depth-first Search 
// 👍 165 👎 181

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 2021-01-19 21:47:39
// Jesse Yang
public class Leetcode0711NumberOfDistinctIslandsIi{
    // Java: number-of-distinct-islands-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0711NumberOfDistinctIslandsIi().new Solution();
        // TO TEST
        int[][] grid = {
                {1,1,0,0,0},
                {1,0,0,0,0},
                {0,0,0,0,1},
                {0,0,0,1,1}
        };
        for (int[] row: grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("number of distinct islands is");
        int res = sol.numDistinctIslands2(grid);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)

// use dfs to find all the islands and canonical it
// T(m, n) = O(m * n * log(m * n)), S(m, n) = O(m * n)
// 101 ms,击败了14.45% 的Java用户, 40 MB,击败了56.67% 的Java用户
/*
for every point(x, y), the rotate by 90 degree is (y, -x)
upReflect is (-x, y)
so all the transformed shape is (x, y), (y, -x), (-x, -y), (-y, x)
                                (y, x), (x, -y), (-y, -x), (-x, y)
and for every transformed shape, normalized it(time complexity is log(m * n)
, move its upper left corner to the (0, 0) point
then store in the HashSet to deduplicate
 */
class Solution {
    
    private final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
    public int numDistinctIslands2(int[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        
        /*
        HashSet里面的只能是List<List<Integer>>, 不能是List<int[]>，否则HashSet去重会出问题
        因为两个值相同的Array并不能equals相同, List重写过equals函数
         */
        Set<List<List<Integer>>> existedShapes = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited.contains(i * cols + j) && grid[i][j] == 1) {
                    List<List<Integer>> shape = new ArrayList<>(); //
                    dfs(i, j, shape, visited, grid);
                    List<List<List<Integer>>> allTransformedShapes = canonical(shape);
                    boolean existed = false; // this shape exists
                    for (List<List<Integer>> transformedShape : allTransformedShapes) {
                        if (existedShapes.contains(transformedShape)) {
                            existed = true;
                            break;
                        }
                        
                    }
                    if (!existed) {
                        count++;
                        existedShapes.add(allTransformedShapes.get(0));
                    }
                }
            }
        }
        return count;
    }
    
    private void dfs(int row, int col, List<List<Integer>> shape, Set<Integer> visited,
            int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        
        // base case - failure
        if (row < 0 || row >= rows || col < 0 || col >= cols || board[row][col] == 0
                || visited.contains(row * cols + col)) {
            return;
        }
        
        visited.add(row * cols + col);
        shape.add(Arrays.asList(row, col));
        
        for(int[] dir: DIRECTIONS) {
            int i = row + dir[0];
            int j = col + dir[1];
            dfs(i, j, shape, visited, board);
        }
    }
    
    private List<List<List<Integer>>> canonical(List<List<Integer>> shape) {
        List<List<List<Integer>>> shapes = new ArrayList<>();
        shapes.add(shape);
        List<List<Integer>> temp = shape;
        for (int i = 1; i < 8; i++) {
            if (i == 4) {
                temp = upReflect(temp);
            } else {
                temp = rotateBy90(temp);
            }
            shapes.add(temp);
        }
        for (List<List<Integer>> transformedShape : shapes) {
            normalize(transformedShape);
        }
        return shapes;
    }
    
    /**
     * return the List<Point>, for every point(x, y), map to (y, -x)
     * @param shape given shape List<List<Integer>>
     * @return return new List<List<Integer>> represent shape that rotated by 90 of given shape
     */
    private List<List<Integer>> rotateBy90(List<List<Integer>> shape) {
        List<List<Integer>> res = new ArrayList<>();
        for (List<Integer> list : shape) {
            List<Integer> temp = new ArrayList<>();
            temp.add(list.get(1));
            temp.add(-list.get(0));
            res.add(temp);
        }
        return res;
    }
    
    /**
     * return the List<Point>, for every point(x, y), map to (-x, y)
     * @param shape given shape List<List<Integer>>
     * @return return new List<List<Integer>> represent shape that up reflected given shape
     */
    private List<List<Integer>> upReflect(List<List<Integer>> shape) {
        List<List<Integer>> res = new ArrayList<>();
        for (List<Integer> list : shape) {
            List<Integer> temp = new ArrayList<>();
            temp.add(-list.get(0));
            temp.add(list.get(1));
            res.add(temp);
        }
        return res;
    }
    
    private void normalize(List<List<Integer>> shape) {
        shape.sort((o1, o2) -> o1.get(0) - o2.get(0) != 0 ?
                o1.get(0) - o2.get(0) :
                o1.get(1) - o2.get(1));
        // the coordinate of the most left point in first row of the shape
        List<Integer> upperLeft = new ArrayList<>(shape.get(0));
        for (List<Integer> list : shape) {
            for (int i = 0; i < list.size(); i++) {
                list.set(i, list.get(i) - upperLeft.get(i));
            }
        }
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}