//Given a sorted array of integers nums and integer values a, b and c. Apply a q
//uadratic function of the form f(x) = ax2 + bx + c to each element x in the array
//. 
//
// The returned array must be in sorted order. 
//
// Expected time complexity: O(n) 
//
// 
// Example 1: 
//
// 
//Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
//Output: [3,9,15,33]
// 
//
// 
// Example 2: 
//
// 
//Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
//Output: [-23,-5,1,7]
// 
// 
// Related Topics Math Two Pointers Sort 
// 👍 373 👎 110

package leetcode.editor.en;

import java.util.Arrays;

// 2020-12-15 21:20:51
// Zeshi Yang
public class Leetcode0360SortTransformedArray{
    // Java: sort-transformed-array
    public static void main(String[] args) {
        Solution sol = new Leetcode0360SortTransformedArray().new Solution();
        // TO TEST
        int[] nums = {-4,-2,2,4};
        int a = -1;
        int b = 3;
        int c = 5;
        int[] res = sol.sortTransformedArray(nums, a, b, c);
        System.out.println(Arrays.toString(res));
    }
//leetcode submit region begin(Prohibit modification and deletion)
// T(n) = O(n), S(n) = O(n)
class Solution {
    
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] res = new int[nums.length];
        // corner case,实际上是线性函数
        if (nums == null || nums.length == 0) {
            return res;
        }
        int len = nums.length;
        res = new int[len];
        if (a == 0) {
            for (int i = 0; i < len; i++) {
                int num = nums[i];
                res[i] = calulate(num, a, b, c);
            }
            return b >= 0 ? res : reverse(res);
        }
        
        float axis = (float) -b / (2 * a);
        int start = 0;
        int end = len - 1;
        int index = len - 1;
        while (start <= end) {
            if (Math.abs(nums[start] - axis) > Math.abs(nums[end] - axis)) {
                res[index--] = calulate(nums[start++], a, b, c);
            } else {
                res[index--] = calulate(nums[end--], a, b, c);
            }
        }
        return a > 0 ? res : reverse(res);
    }
    
    private int[] reverse(int[] nums) {
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        while (start <= end) {
            swap(nums, start++, end--);
        }
        return nums;
    }
    
    private void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }
    
    private int calulate(int num, int a, int b, int c) {
        return a * num * num + b * num + c;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}