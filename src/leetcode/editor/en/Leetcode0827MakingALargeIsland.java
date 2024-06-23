/**
You are given an n x n binary matrix grid. You are allowed to change at most 
one 0 to be 1. 

 Return the size of the largest island in grid after applying this operation. 

 An island is a 4-directionally connected group of 1s. 

 
 Example 1: 

 
Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with 
area = 3.
 

 Example 2: 

 
Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with 
area = 4. 

 Example 3: 

 
Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.
 

 
 Constraints: 

 
 n == grid.length 
 n == grid[i].length 
 1 <= n <= 500 
 grid[i][j] is either 0 or 1. 
 

 Related Topics Array Depth-First Search Breadth-First Search Union Find Matrix 
👍 3788 👎 71

*/
package leetcode.editor.en;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 2024-04-01 22:50:45
// Jesse Yang
public class Leetcode0827MakingALargeIsland{
    // Java: making-a-large-island
    public static void main(String[] args) {
        Solution sol = new Leetcode0827MakingALargeIsland().new Solution();
        // TO TEST
        int[][] grid = {{0, 0}, {0, 0}};
        int res = sol.largestIsland(grid);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// DFS: T(n) = O(n*2 log(n)), S(n) = O(n^2)
/*
Solution: DFS
1. traverse the matrix, using DFS find the size of each connected islands and record their size
2. traverse the all 0 to check whether they have connected 1 and connected largest one

 */
class Solution {
    
    private final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    public int largestIsland(int[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int len = grid.length;
        // key: element, value: 1st element in the group
        Map<Integer, Integer> mapToGroup = new HashMap<>();
        // key first element in the group, value: size of group
        Map<Integer, Integer> groupToSize = new HashMap<>();
        
        int maxArea = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (grid[i][j] == 1) {
                    int[] area = new int[1];
                    doDFSsearch(grid, i, j, i, j, area, mapToGroup);
                    groupToSize.put(i * len + j, area[0]);
                    maxArea = Math.max(maxArea, area[0]);
                }
            }
        }
        
        for (int row = 0; row < len; row++) {
            for (int col = 0; col < len; col++) {
                Set<Integer> groupSet = new HashSet<>();
                if (grid[row][col] == 0) {
                    int tempSize = 1;
                    for(int[] dir: DIRECTIONS) {
                        int i = row + dir[0];
                        int j = col + dir[1];
                        if (i < 0 || i >= len || j < 0 || j >= len || grid[i][j] == 0) {
                            continue;
                        }
                        int groupIndex = mapToGroup.get(i * len + j);
                        if (!groupSet.contains(groupIndex)) {
                            groupSet.add(groupIndex);
                            tempSize += groupToSize.get(groupIndex);
                        }
                    }
                    maxArea = Math.max(maxArea, tempSize);
                }
            }
        }
        return maxArea;
    }
    
    private void doDFSsearch(int[][] grid, int startRow, int startCol, int row, int col, int[] area,
            Map<Integer, Integer> mapToGroup) {
        //base case - failure case
        int len = grid.length;
        if (row < 0 || row >= len || col < 0 || col >= len
                || grid[row][col] == 0 || grid[row][col] == 2) {
            return;
        }
        
        grid[row][col] = 2;
        mapToGroup.put(row * len + col, startRow * len + startCol);
        area[0]++;
        
        //general case
        for (int[] direction: DIRECTIONS) {
            int r = row + direction[0];
            int c = col + direction[1];
            doDFSsearch(grid, startRow, startCol, r, c, area, mapToGroup);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

// 面试用Solution 1

/*
Solution 1: DFS: T(n) = O(n^2), S(n) = O(n^2)
1. traverse the matrix, using DFS find the size of each connected islands and record their size
2. traverse the all 0 to check whether they have connected 1, if connected combine these groups

 */
class Solution1 {
    
    private final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    public int largestIsland(int[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int len = grid.length;
        // key: element, value: 1st element in the group
        Map<Integer, Integer> mapToGroup = new HashMap<>();
        // key first element in the group, value: size of group
        Map<Integer, Integer> groupToSize = new HashMap<>();
        int maxArea = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (grid[i][j] == 1) {
                    int[] area = new int[1];
                    doDFSsearch(grid, i, j, i, j, area, mapToGroup);
                    groupToSize.put(i * len + j, area[0]);
                    maxArea = Math.max(maxArea, area[0]);
                }
            }
        }
        
        
        for (int row = 0; row < len; row++) {
            for (int col = 0; col < len; col++) {
                Set<Integer> groupSet = new HashSet<>();
                if (grid[row][col] == 0) {
                    int tempSize = 1;
                    for(int[] dir: DIRECTIONS) {
                        int i = row + dir[0];
                        int j = col + dir[1];
                        if (i < 0 || i >= len || j < 0 || j >= len || grid[i][j] == 0) {
                            continue;
                        }
                        int groupIndex = mapToGroup.get(i * len + j);
                        if (!groupSet.contains(groupIndex)) {
                            groupSet.add(groupIndex);
                            tempSize += groupToSize.get(groupIndex);
                        }
                    }
                    maxArea = Math.max(maxArea, tempSize);
                }
            }
        }
        return maxArea;
    }
    
    private void doDFSsearch(int[][] grid, int startRow, int startCol, int row, int col, int[] area,
            Map<Integer, Integer> mapToGroup) {
        //base case - failure case
        int len = grid.length;
        if (row < 0 || row >= len || col < 0 || col >= len
                || grid[row][col] == 0 || grid[row][col] == 2) {
            return;
        }
        
        grid[row][col] = 2;
        mapToGroup.put(row * len + col, startRow * len + startCol);
        area[0]++;
        
        //general case
        for (int[] direction: DIRECTIONS) {
            int r = row + direction[0];
            int c = col + direction[1];
            doDFSsearch(grid, startRow, startCol, r, c, area, mapToGroup);
        }
    }
}

/*
Solution 2: Union find T(n) = O(n*2 log(n)), S(n) = O(n^2)
1. traverse the matrix to let all connected 1 to become single Union
2. traverse the all 0 to check whether they have connected 1 and find the largest Union
Union-Find(DSU - disjointed Union)
1. 连通块问题
2. int[] parent, size,

1. 路径压缩 => 让连通块的每个成员都指向其连通块的父节点
2. 按秩归并 => 启发合并的思想，主要考虑连通块本身大小的因素，一般是小块合并入大块

“倍增” ->     1. LCA binary lifting
             2. RMQ -ST range query

 */
class Solution2 {
    
    private int[] parents;
    private int[] size;
    private int n;
    private final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    public int largestIsland(int[][] grid) {
        n = grid.length;
        parents = new int[n * n];
        size = new int[n * n];
        int res = 0;
        
        for (int i = 0; i < n * n; i++) {
            parents[i] = i;
            size[i] = 1;
        }
        
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1) {
                    int p = getIndex(row, col);
                    for (int[] dir : DIRECTIONS) {
                        int i = row + dir[0];
                        int j = col + dir[1];
                        if (i < 0 || i >= n || j < 0 || j >= n || grid[i][j] == 0) {
                            continue;
                        }
                        int q = getIndex(i, j);
                        if (!find(p, q)) {
                            union(p, q);
                        }
                    }
                    res = Math.max(res, size[findRoot(p)]);
                }
            }
        }
        
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 0) {
                    int tempSize = 1;
                    Set<Integer> set = new HashSet<>();
                    for (int[] dir : DIRECTIONS) {
                        int i = row + dir[0];
                        int j = col + dir[1];
                        if (i < 0 || i >= n || j < 0 || j >= n || grid[i][j] == 0) {
                            continue;
                        }
                        int q = getIndex(i, j);
                        int rootQ = findRoot(q);
                        if (!set.contains(rootQ)) { //check whether neighbor union is the same union
                            tempSize += size[findRoot(q)];
                            set.add(rootQ);
                        }
                    }
                    res = Math.max(res, tempSize);
                }
            }
        }
        return res;
    }
    
    private boolean find(int p, int q) {
        return findRoot(p) == findRoot(q);
    }
    
    private void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        if (rootP == rootQ) {
            return;
        }
        
        if (size[rootP] < size[rootQ]) { // p -> q
            parents[rootP] = parents[rootQ];
            size[rootQ] += size[rootP];
        } else { // q -> p
            parents[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }
    
    private int findRoot(int p) {
        // base case
        if (parents[p] == p) {
            return p;
        }
        
        parents[p] = findRoot(parents[p]);
        return parents[p];
    }
    
    private int getIndex(int row, int col) {
        return row * n + col;
    }
}
}