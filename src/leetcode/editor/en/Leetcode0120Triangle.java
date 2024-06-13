//Given a triangle, find the minimum path sum from top to bottom. Each step you 
//may move to adjacent numbers on the row below. 
//
// For example, given the following triangle 
//
// 
//[
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
// 
//
// The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11). 
//
// Note: 
//
// Bonus point if you are able to do this using only O(n) extra space, where n i
//s the total number of rows in the triangle. 
// Related Topics Array Dynamic Programming 
// 👍 2034 👎 243

package leetcode.editor.en;

import java.util.*;
// 2020-07-26 12:41:25
// Jesse Yang
public class Leetcode0120Triangle{
    // Java: triangle
    public static void main(String[] args) {
        Solution sol = new Leetcode0120Triangle().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 5: Dynamic Programming with reduced space complexity
class Solution {
    //S5: Dynamic Programming with space complexity O(n)
    public int minimumTotal(List<List<Integer>> triangle) {
        //corner case
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int length = triangle.size();
        int[] minPath = new int[length + 1];


        for (int i = length - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                minPath[j] = Math.min(minPath[j], minPath[j + 1]) + triangle.get(i).get(j);
            }
        }
        return minPath[0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: DFS with carry sum, from top to bottom
class Solution1 {
    //S1: DFS with carry sum, from top to bottom
    Integer min = null;
    public int minimumTotal(List<List<Integer>> triangle) {
        //corner case
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        dfs(0, 0, 0, triangle);
        return min;
    }
    private void dfs(int row, int col, int sum, List<List<Integer>> triangle) {
        // base case
        if (row == triangle.size()) {
            // found a path from top to bottom
            if (min == null) {
                min = sum;
            }
            if (sum < min) {
                min = sum;
            }
            return;
        }
        dfs(row + 1, col, sum + triangle.get(row).get(col), triangle);
        dfs(row + 1, col + 1, sum + triangle.get(row).get(col), triangle);
    }
}

// Solution 2: Recrusion from bottom to top
class Solution2 {
    //S2: Recrusion from bottom to top
    public int minimumTotal(List<List<Integer>> triangle) {
        //corner case
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        return divideConquer(0, 0, triangle);
    }
    private int divideConquer(int row, int col, List<List<Integer>> triangle) {
        // base case
        if (row == triangle.size()) {
            return 0;
        }

        int left = divideConquer(row + 1, col, triangle);
        int right = divideConquer(row + 1, col + 1, triangle);

        return triangle.get(row).get(col) + Math.min(left, right);
    }
}

// Solution 3: Recursion with 记忆化存储 to prune
class Solution3 {
    //S3: Recursion with 记忆化存储 to prune
    public int minimumTotal(List<List<Integer>> triangle) {
        int length = triangle.size();
        int[][] minPath = new int[length][length];
        return divideConquer(0, 0, minPath, triangle);
    }
    private int divideConquer(int row, int col, int[][] minPath, List<List<Integer>> triangle) {
        // base case
        if (row == triangle.size() - 1) {
            return triangle.get(row).get(col);
        }
        // if we already have the minimum path sum from (i,j) to bottom, pruning & return
        int left = (minPath[row + 1][col] == 0 ?
                divideConquer(row + 1, col, minPath, triangle) : minPath[row + 1][col]);
        int right = (minPath[row + 1][col + 1] == 0 ?
                divideConquer(row + 1, col + 1, minPath,triangle) : minPath[row + 1][col + 1]);
        minPath[row][col] = triangle.get(row).get(col) + + Math.min(left, right);
        return minPath[row][col];
    }
}

// Solution 4: Dynamic Programming
class Solution4 {
    //S4: Dynamic Programming
    public int minimumTotal(List<List<Integer>> triangle) {
        //corner case
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int length = triangle.size();
        int[][] minPath = new int[length][length];

        // initialize the last row
        for (int j = 0; j < length; j++) {
            minPath[length - 1][j] = triangle.get(length - 1).get(j);
        }

        for (int i = length - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                minPath[i][j] = Math.min(minPath[i + 1][j], minPath[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return minPath[0][0];
    }
}

// Solution 5: Dynamic Programming with reduced space complexity
class Solution5 {
    //S5: Dynamic Programming with space complexity O(n)
    public int minimumTotal(List<List<Integer>> triangle) {
        //corner case
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int length = triangle.size();
        int[] minPath = new int[length + 1];


        for (int i = length - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                minPath[j] = Math.min(minPath[j], minPath[j + 1]) + triangle.get(i).get(j);
            }
        }
        return minPath[0];
    }
}
}