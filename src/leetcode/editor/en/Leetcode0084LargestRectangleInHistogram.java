/*
Given n non-negative integers representing the histogram's bar height where the
width of each bar is 1, find the area of largest rectangle in the histogram.

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

The largest rectangle is shown in the shaded area, which has area = 10 unit.

 Example:

Input: [2,1,5,6,2,3]
Output: 10
 
 Related Topics Array Stack
 👍 3929 👎 88
*/

package leetcode.editor.en;

import java.util.Arrays;
import java.util.Stack;

// 2020-08-13 22:56:55
// Jesse Yang
public class Leetcode0084LargestRectangleInHistogram{
    // Java: largest-rectangle-in-histogram
    public static void main(String[] args) {
        Solution sol = new Leetcode0084LargestRectangleInHistogram().new Solution();
        // TO TEST
	    int[] heights = {2,3};
        int res = sol.largestRectangleArea(heights);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// 前后缀分解 T(n) = O(n), S(n) = O(n)
/*
every area = width * height;
    We need to calculate area by find every different height,
    so for every height,
        we need to find the nearest height h1 and h2 around the height h (h1, h2 < h),
        and calculate its area
    step 1: create an array leftLess to record first elements less than current element
    step 2: create an array rightLess to record first elements less than current element
    step 3: for every height, the area = height * width, width = rightLess[i] - rightLess[i]
    
 */
class Solution {
    public int largestRectangleArea(int[] heights) {
        // corner case
        if (heights == null || heights.length == 0) {
            return 0;
        }
        
        // general case
        int len = heights.length;
        int[] leftLess = new int[len];
        Stack<Integer> stack = new Stack<>(); // store index
        
        // search 1st previous smaller element, maintain an increasing stack
        leftLess[0] = -1;
        stack.push(0);
        for (int i = 1; i < len; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                leftLess[i] = -1;
            } else {
                leftLess[i] = stack.peek();
            }
            stack.push(i);
        }
        
        // search 1st next smaller element, maintain a non-decreasing stack
        stack.clear();
        int[] rightLess = new int[len];
        Arrays.fill(rightLess, len);
        rightLess[len - 1] = len;
        stack.push(0);
        for (int i = 1; i < len; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int index = stack.pop();
                rightLess[index] = i;
            }
            if (stack.isEmpty()) {
                rightLess[i] = len;
            }
            stack.push(i);
        }
        
        int maxArea = 0;
        for (int i = 0; i < len; i++) {
            maxArea = Math.max(maxArea, (rightLess[i] - leftLess[i] - 1) * heights[i]);
        }
        return maxArea;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: 前后缀分解 T(n) = O(n), S(n) = O(n) 对于每个高度的柱形，往左和往右找到第一个比自己高度低的柱子
// 7 ms,击败了75.56% 的Java用户, 48.4 MB,击败了9.23% 的Java用户
/*
area = length × height
选择了height之后，选择这个height对应的最长的length
分别求出包含每个柱子的矩形区域的最大面积，然后选最大的。要包含这个柱子，也就是这个柱子是当前矩形区域的高度。
也就是，这个柱子是当前矩形区域中柱子最高的。如下图中包含橙色柱子的矩形区域的最大面积。
所以只要想办法分别找到每个柱子左边和右边第一个比它小的左边，
两个坐标差 * 这个柱子的高度，就是包含这个主子的最大面积
证明：stack来证明
 */
class Solution1_1 {
    public int largestRectangleArea(int[] heights) {
        // corner case
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int len = heights.length;
        //求每个柱子的左边第一个小的柱子的下标
        int[] leftLess = new int[len];
        leftLess[0] = -1;
        for (int i = 1; i < len; i++) {
            int l = i - 1;
            while (l >= 0 && heights[l] >= heights[i]) {
                l = leftLess[l];
            }
            leftLess[i] = l;
        }

        //求每个柱子的右边第一个小的柱子的下标
        int[] rightLess = new int[len];
        rightLess[len - 1] = len;
        for (int i = len - 2; i >= 0; i--) {
            int r = i + 1;
            while (r <= len - 1 && heights[r] >= heights[i]) {
                r = rightLess[r];
            }
            rightLess[i] = r;
        }

        //求包含每个柱子的矩形区域的最大面积，选出最大的
        int maxArea = 0;
        for (int i = 0; i < len; i++) {
            int area = (rightLess[i] - leftLess[i] - 1) * heights[i];
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }

}

// Solution 1_2: 前后缀分解 T(n) = O(n), S(n) = O(n)
class Solution1_2 {
    public int largestRectangleArea(int[] heights) {
        // corner case
        if (heights == null || heights.length == 0) {
            return 0;
        }
        
        // general case
        int len = heights.length;
        int[] leftLess = new int[len];
        Stack<Integer> stack = new Stack<>(); // store index
        
        // search 1st previous smaller element, maintain an increasing stack
        leftLess[0] = -1;
        stack.push(0);
        for (int i = 1; i < len; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                leftLess[i] = -1;
            } else {
                leftLess[i] = stack.peek();
            }
            stack.push(i);
        }
        
        // search 1st next smaller element, maintain a non-decreasing stack
        stack.clear();
        int[] rightLess = new int[len];
        Arrays.fill(rightLess, len);
        rightLess[len - 1] = len;
        stack.push(0);
        for (int i = 1; i < len; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int index = stack.pop();
                rightLess[index] = i;
            }
            if (stack.isEmpty()) {
                rightLess[i] = len;
            }
            stack.push(i);
        }
        
        int maxArea = 0;
        for (int i = 0; i < len; i++) {
            maxArea = Math.max(maxArea, (rightLess[i] - leftLess[i] - 1) * heights[i]);
        }
        return maxArea;
    }
}


// optimize Solution 1_2 to get this one
// Solution 2: stack, T(n) = O(n), S(n) = O(n)
// 8 ms, stack里面存高度递增的index
/*
monotonic stack
经典模型： next greater element
解决的基本问题模型：next/previous greater / smaller/ greaterOrEqual / smallestOrEqual elements
T(n) = O(n)

dp: 二进制优化，单调队列优化

如何由Solution 1 --> Solution 2 :
除了用dp方法找到每一个柱形图左边第一个比它自己小的柱子index以外，还可以用stack来做
    keep stack为一个increasing stack,这样可以update pop stack使得stack.peek() < current number
    stack的top索引就是左边第一个比自己小的数字
    然后发现某个元素自己被pop()出来的时候，那个current的值就是右边第一个比它自己低的柱形
    拿这个时候，包含它自己的最大长方形的面积就可以直接算了
    
1. 本题用一个有序栈来做，从底到顶是升序，如果进来的值比栈顶元素小，则一直pop()到比进来的元素小为止。
2. 每次弹栈都要计算对应延伸出来的面积。如果栈不为空，则宽度为 i - stack.peek() - 1；
        如果此时栈已清空，则宽度为 i，而高度则为弹栈元素作为index所在的高度。
3. 本题最大的另一个trick在于，要人为地在最后引入一个0，保证所有在栈内的元素一一都弹出去，
        从而一一计算对应的面积，所以在循环遍历的时候，要多遍历一次，即 i 可以取[0, len]。

every area = width * height;
    We need to calculate area by find every different height, so for every height,
        we need to find the nearest height h1 and h2 around the height h, and calculate its area
    step 1: using an increasing stack(according to heights[i]) to store i
        so that in the stack, the lower index is the left first height that smaller than the height.
    step 2:
        if the coming height is not smaller than the heights[stack.peek()], push it
        if the coming height is smaller than the heights[stack.peek()],
            we find the 1st right border to the height h,
            so we can do the calculation for this related area.
 */
class Solution2 {
    public int largestRectangleArea(int[] heights) {
        // corner case
        if (heights == null || heights.length == 0) {
            return 0;
        }
        // general case
        int len = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= len; i++) {
            int height = i < len ? heights[i] : 0; // 最后加一个高度是0的柱形图，强制把所有的柱形图弹出来
            while (!stack.isEmpty() && heights[stack.peek()] > height) {
                int topIdx = stack.pop();
				int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
                int area = heights[topIdx] * width;
                maxArea = Math.max(maxArea, area);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
}