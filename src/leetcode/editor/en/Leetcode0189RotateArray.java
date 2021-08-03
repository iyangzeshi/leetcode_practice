//Given an array, rotate the array to the right by k steps, where k is non-negat
//ive. 
//
// Follow up: 
//
// 
// Try to come up as many solutions as you can, there are at least 3 different w
//ays to solve this problem. 
// Could you do it in-place with O(1) extra space? 
// 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,3,4,5,6,7], k = 3
//Output: [5,6,7,1,2,3,4]
//Explanation:
//rotate 1 steps to the right: [7,1,2,3,4,5,6]
//rotate 2 steps to the right: [6,7,1,2,3,4,5]
//rotate 3 steps to the right: [5,6,7,1,2,3,4]
// 
//
// Example 2: 
//
// 
//Input: nums = [-1,-100,3,99], k = 2
//Output: [3,99,-1,-100]
//Explanation: 
//rotate 1 steps to the right: [99,-1,-100,3]
//rotate 2 steps to the right: [3,99,-1,-100]
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 2 * 104 
// -231 <= nums[i] <= 231 - 1 
// 0 <= k <= 105 
// 
// Related Topics Array 
// 👍 3892 👎 888

package leetcode.editor.en;

import java.util.Arrays;

// 2020-12-20 19:45:41
// Zeshi Yang
public class Leetcode0189RotateArray{
    // Java: rotate-array
    public static void main(String[] args) {
        Solution sol = new Leetcode0189RotateArray().new Solution();
        // TO TEST
        int[] nums = {1,2,3,4,5,6};
        int k = 3;
        sol.rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1:Using Cyclic Replacements, T(n) = O(n), S(n) = O(1)
class Solution1 {
    public void rotate(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == k) {
            return;
        }
        
        int len = nums.length;
        int start = 0;
        int index = 0;
        int count = 0;
        int num = nums[0];
        while (count++ != len) {
            int newIndex = (index + k) % len;
            int newNum = nums[newIndex];
            nums[newIndex] = num;
            if (newIndex == start) {
                newIndex = (newIndex + 1) % len;
                start = newIndex;
                newNum = nums[newIndex];
            }
            num = newNum;
            index = newIndex;
        }
    }
}

// Solution 2: reverse array by 2 steps, T(n) = O(n), S(n) = O(1)
class Solution2 {
    
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    
}
}
