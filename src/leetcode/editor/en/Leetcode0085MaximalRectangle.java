/*
Given a rows x cols binary matrix filled with 0's and 1's, find the largest
rectangle containing only 1's and return its area.

 
 Example 1:
Input: matrix = [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1',
'1'],['1','0','0','1','0']]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.
 

 Example 2:
Input: matrix = []
Output: 0
 

 Example 3:
Input: matrix = [['0']]
Output: 0
 

 Example 4:
Input: matrix = [['1']]
Output: 1
 

 Example 5:
Input: matrix = [['0','0']]
Output: 0
 
 Constraints:
 
 rows == matrix.length
 cols == matrix.length
 0 <= row, cols <= 200
 matrix[i][j] is '0' or '1'.
 
 Related Topics Array Hash Table Dynamic Programming Stack
 👍 3723 👎 81
*/

package leetcode.editor.en;

import java.util.Arrays;
import java.util.Stack;

// 2021-01-14 14:55:25
// Jesse Yang
public class Leetcode0085MaximalRectangle{
    // Java: maximal-rectangle
    public static void main(String[] args) {
        Solution sol = new Leetcode0085MaximalRectangle().new Solution();
        // TO TEST
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        int res = sol.maximalRectangle(matrix);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 2: DP，对于每一个连续的高度，找到往左边和往右边能拓展到哪里，对每一个高度计算一下area
// T(n, m) = O(n * m), S(n, m) = O(n)
// 2 ms,击败了99.29% 的Java用户, 42.7 MB,击败了26.05% 的Java用户
/*
对于matrix, 遍历每一层， rolling base
设置left[], right[]数组表示每一层每一个位置，左边和右边比它自己小的边界(]
height[]数组表示当前这个地方往上看有多少个连续的1
 */
class Solution {
    
    public int maximalRectangle(char[][] matrix) {
        //给定矩阵坐标，其中求出最大面积的表示
        //采用分别记录左右高可达边界坐标，从而面积值为（右-左）*gao
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int rows = matrix.length; // rows
        int cols = matrix[0].length; // cols
        int[] left = new int[cols]; // 往左边看第一个高度比自己小的坐标，的右边一个数字
        int[] right = new int[cols]; // 往右看第一个比自己高度小的坐标
        int[] height = new int[cols];
        int maxArea = 0;
        
        //对于右边界默认值为n
        Arrays.fill(right, cols);
        for (char[] chars : matrix) {
            int curLeft = 0;
            int curRight = cols;
            //求高
            for (int j = 0; j < cols; j++) {
                if (chars[j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
        
            //求左边界
            for (int j = 0; j < cols; j++) {
                if (chars[j] == '1') {
                    left[j] = Math.max(left[j], curLeft);
                } else {
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }
        
            //求右边界(从右边开始)
            for (int j = cols - 1; j >= 0; j--) {
                if (chars[j] == '1') {
                    right[j] = Math.min(right[j], curRight);
                } else {
                    right[j] = cols;
                    curRight = j;
                }
            }
        
            //求面积
            for (int j = 0; j < cols; j++) {
                maxArea = Math.max(maxArea, (right[j] - left[j]) * height[j]);
            }
        }
        return maxArea;
        
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: dp calculate height, stack to calculate max rectangle
/*
此题是之前那道的 Largest Rectangle in Histogram 的扩展，
这道题的二维矩阵每一层向上都可以看做一个直方图，输入矩阵有多少行，就可以形成多少个直方图，
对每个直方图都调用 Largest Rectangle in Histogram 中的方法，就可以得到最大的矩形面积。

那么这道题唯一要做的就是将每一层都当作直方图的底层，并向上构造整个直方图，
由于题目限定了输入矩阵的字符只有 '0' 和 '1' 两种，所以处理起来也相对简单。
方法是，对于每一个点，如果是 ‘0’，则赋0，如果是 ‘1’，就赋之前的 height 值加上1
 */
 
 
// Solution 1_1: dp calculate height, stack to calculate max rectangle
// T(n, m) = O(n * m), S(n, m) = O(n * m)
// 7 ms,击败了65.39% 的Java用户, 42 MB,击败了69.70% 的Java用户
// dp[i][j] 表示matrix[i][j]往上看有多少个连续的1
class Solution1_1 {
    public int maximalRectangle(char[][] matrix) {
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int[][] dp = getHeights(matrix);
        int maxRect = 0;
        for(int[] heights: dp) {
            int height = calMaxRect(heights);
            maxRect = Math.max(maxRect, height);
        }
        return maxRect;
    }
    
    /**
     * T(n, m) = O(n * m), S(n, m) = O(n * m)
     * @param matrix given matrix
     * @return an array dp[][] that dp[i][j] means the number of consecutive 1 upward
     */
    
    private int[][] getHeights(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols]; // dp[i][j] means how many consecutive 1 from matrix[i][j] to up
        /*
        state transaction function,
        if i == 0 : dp[i][j] =  (matrix[i][j] == '1')
        if i > 0  : dp[i][j] =  dp[i][j] + 1 if matrix[i][j] == '1'
                    dp[i][j] =  0 if matrix[i][j] == '0'
        */
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0) {
                    dp[i][j] = matrix[i][j] == '1' ? 1 : 0;
                } else {
                    dp[i][j] = (matrix[i][j] == '1' ? dp[i - 1][j] + 1 : 0);
                }
            }
        }
        return dp;
    }
    
    private int calMaxRect(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int len = heights.length;
        int maxArea = 0;
        for (int i = 0; i <= len; i++) {
            int height = i < len ? heights[i] : 0; // 最后加一个高度是0的柱形图，强制把所有的柱形图弹出来
            while (!stack.isEmpty() && heights[stack.peek()] >= height) {
                int top = stack.pop();
                int area = heights[top] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                maxArea = Math.max(maxArea, area);
            }
            stack.push(i);
        }
        return maxArea;
    }
}

// Solution 1_2: T(n, m) = O(n * m), S(n, m) = O(n)
// n is number of rows, m is number of columns
// 5 ms,击败了75.71% 的Java用户, 42.2 MB,击败了63.24% 的Java用户
/*
 heights 是上面dp的rolling base，减少了空间复杂度
 heights[i] 表示matrix的当前行往上看，每一列往上由多少个连续的1
 */
class Solution1_2 {
    public int maximalRectangle(char[][] matrix) {
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int[] heights = null;
        int maxRect = 0;
        for(char[] row : matrix) {
            heights = getHeights(heights, row);
            int height = calMaxRect(heights);
            maxRect = Math.max(maxRect, height);
        }
        return maxRect;
    }
    
    private int[] getHeights(int[] heights, char[] row) {
        int cols = row.length;
        boolean isFirstRow = false;
        if (heights == null) {
            heights = new int[cols];
            isFirstRow = true;
            
        }
        for (int i = 0; i < cols; i++) {
            if (isFirstRow) {
                heights[i] = row[i] == '1' ? 1 : 0;
            } else {
                heights[i] = row[i] == '1' ? heights[i] + 1: 0;
            }
        }
        return heights;
    }
    
    private int calMaxRect(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int len = heights.length;
        int maxArea = 0;
        for (int i = 0; i <= len; i++) {
            int height = i < len ? heights[i] : 0; // 最后加一个高度是0的柱形图，强制把所有的柱形图弹出来
            while (!stack.isEmpty() && heights[stack.peek()] >= height) {
                int top = stack.pop();
                int area = heights[top] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                maxArea = Math.max(maxArea, area);
            }
            stack.push(i);
        }
        return maxArea;
    }
}

// Solution 2: DP，对于每一个连续的高度，找到往左边和往右边能拓展到哪里，对每一个高度计算一下area
// T(n, m) = O(n * m), S(n, m) = O(n)
// 2 ms,击败了99.29% 的Java用户, 42.7 MB,击败了26.05% 的Java用户
/*
对于matrix, 遍历每一层， rolling base
设置left[], right[]数组表示每一层每一个位置，左边和右边比它自己小的边界(]
height[]数组表示当前这个地方往上看有多少个连续的1
 */
class Solution2 {
    
    public int maximalRectangle(char[][] matrix) {
        //给定矩阵坐标，其中求出最大面积的表示
        //采用分别记录左右高可达边界坐标，从而面积值为（右-左）*高
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int rows = matrix.length; // rows
        int cols = matrix[0].length; // cols
        int[] left = new int[cols]; // 往左边看第一个高度比自己小的坐标，的右边一个数字
        int[] right = new int[cols]; // 往右看第一个比自己高度小的坐标
        int[] height = new int[cols];
        int maxArea = 0;
        
        //对于右边界默认值为n
        Arrays.fill(right, cols);
        for (char[] chars : matrix) {
            int curLeft = 0;
            int curRight = cols;
            //求高
            for (int j = 0; j < cols; j++) {
                if (chars[j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            
            //求左边界[ 闭区间
            for (int j = 0; j < cols; j++) {
                if (chars[j] == '1') {
                    left[j] = Math.max(left[j], curLeft);
                } else {
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }
            
            //求右边界(从右边开始)
            for (int j = cols - 1; j >= 0; j--) {
                if (chars[j] == '1') {
                    right[j] = Math.min(right[j], curRight);
                } else {
                    right[j] = cols;
                    curRight = j;
                }
            }
            
            //求面积
            for (int j = 0; j < cols; j++) {
                maxArea = Math.max(maxArea, (right[j] - left[j]) * height[j]);
            }
        }
        return maxArea;
        
    }
    
}
}