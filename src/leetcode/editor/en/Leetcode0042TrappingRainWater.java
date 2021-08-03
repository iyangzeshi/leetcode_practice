//Given n non-negative integers representing an elevation map where the width of
// each bar is 1, compute how much water it is able to trap after raining. 
//
// 
//The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In 
//this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos
// for contributing this image! 
//
// Example: 
//
// 
//Input: [0,1,0,2,1,0,1,3,2,1,2,1]
//Output: 6 
// Related Topics Array Two Pointers Stack 
// 👍 7988 👎 128

package leetcode.editor.en;

import java.util.Stack;

// 2020-09-09 18:51:40
// Zeshi Yang
public class Leetcode0042TrappingRainWater{
    // Java: trapping-rain-water
    public static void main(String[] args) {
        Solution sol = new Leetcode0042TrappingRainWater().new Solution();
        // TO TEST
        int[] height = {4,2,0,3,2,5};
        int res = sol.trap(height);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int trap(int[] height) {
        // corner case
        if (height == null || height.length <= 2) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int res = 0;
        // 区间[0, left - 1] 和[right + 1, len - 1]的水已经加好了，范围在缩小
        while (left <= right) { // left <= right也行
            if (height[left] < height[right]) {
                leftMax = Math.max(height[left], leftMax);
                res += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(height[right], rightMax);
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// 面试的时候，用Solution 2_2

// Solution 1: dp, leftMax, rightMax, T(n) =  O(n), S(n) = O(n), 3 pass, can be reduced to 2 pass.
// 1 ms,击败了83.33% 的Java用户, 38.5 MB,击败了58.35% 的Java用户
class Solution1 {
    
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int len = height.length;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        leftMax[0] = height[0];
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }
        rightMax[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0 ; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int sum = 0;
        for (int i = 0; i < len; i++) {
            int maxHeight = Math.min(leftMax[i], rightMax[i]);
            sum += maxHeight - height[i];
        }
        return sum;
    }
}

// Solution 2: 2 pointers, left and right. T(n) = O(n), S(n) = O(1)
// 0 ms,击败了100.00% 的Java用户, 38.7 MB,击败了32.75% 的Java用户
class Solution2_1 {
    
    public int trap(int[] height) {
        // corner case
        if (height == null || height.length == 0) {
            return 0;
        }
        
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int res = 0;
        // 区间[0, left - 1] 和[right + 1, len - 1]的水已经加好了，范围在缩小
        while (left < right) { // left <= right也行
            if (height[left] < height[right]) {
                leftMax = Math.max(height[left], leftMax);
                res += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(height[right], rightMax);
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }
    
}

// 相同原理，类似的，另外一种写法 2 pointers, left and right. T(n) = O(n), S(n) = O(1)
// 0 ms,击败了100.00% 的Java用户, 38.5 MB,击败了58.35% 的Java用户
class Solution2_2 {
    
    public int trap(int[] height) {
        // corner case
        if (height == null || height.length <= 2) {
            return 0;
        }
        int len = height.length;
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[0];
        int rightMax = height[len - 1];
        int res = 0;
        // [0, left]和[right, len - 1]已经加好水了，区间(left, right)的水还没加好，这个范围在缩小
        while (left < right) { // left < right - 1也行
            if (leftMax <= rightMax) {
                left++;
                leftMax = Math.max(height[left], leftMax);
                res += leftMax - height[left];
            } else {
                right--;
                rightMax = Math.max(height[right], rightMax);
                res += rightMax - height[right];
            }
        }
        return res;
    }
    
}


// Solution 3: decreasing stack (non-increasing bottom up)

/**
 * stack的底是从当前元素往左边看最高的元素，然后stack是单调递减
 * 每次遇到新元素，
 * 如果和栈顶一样大，随便
 * 如果比栈顶小，push进去
 * 如果比栈顶大，一直pop，pop到栈顶>= 当前高度
 *      pop的时候，加水，加的水的高度是min(height[top], height[i]),宽度是i - top - 1
 */
// 3 ms,击败了16.02% 的Java用户, 38.6 MB,击败了44.56% 的Java用户
class Solution3 {
    
    public int trap(int[] height) {
        // corner case
        if (height == null || height.length <= 2) {
            return 0;
        }
        /*
         decreasing stack from bottom up, the bottom value of stack is the max height from left to
         current index.
        */
        Stack<Integer> stack = new Stack<>(); // decreasing or non-increasing order from bottom up
        int total = 0;
        int len = height.length;
        for (int i = 0 ; i < len; i++) {
            if (!stack.isEmpty()) {
                while (!stack.isEmpty() && height[i] >= height[stack.peek()]) { // > or >= ?都行
                    int prev = stack.pop();
                    if (stack.isEmpty()) {
                        break;
                    }
                    int top = stack.peek();
                    // 下面的等式乘号右边不能是* (i - prev)
                    int width = i - top - 1;
                    int addHeight = Math.min(height[top], height[i]) - height[prev];
                    total += addHeight * width;
                }
            }
            stack.push(i);
        }
        return total;
    }
    
}

}