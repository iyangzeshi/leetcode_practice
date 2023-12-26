//You are given a 0-indexed integer array nums and a positive integer k. 
//
// You can apply the following operation on the array any number of times: 
//
// 
// Choose any subarray of size k from the array and decrease all its elements 
//by 1. 
// 
//
// Return true if you can make all the array elements equal to 0, or false 
//otherwise. 
//
// A subarray is a contiguous non-empty part of an array. 
//
// 
// Example 1: 
//
// 
//Input: nums = [2,2,3,1,1,0], k = 3
//Output: true
//Explanation: We can do the following operations:
//- Choose the subarray [2,2,3]. The resulting array will be nums = [1,1,2,1,1,0
//].
//- Choose the subarray [2,1,1]. The resulting array will be nums = [1,1,1,0,0,0
//].
//- Choose the subarray [1,1,1]. The resulting array will be nums = [0,0,0,0,0,0
//].
// 
//
// Example 2: 
//
// 
//Input: nums = [1,3,1,1], k = 2
//Output: false
//Explanation: It is not possible to make all the array elements equal to 0.
// 
//
// 
// Constraints: 
//
// 
// 1 <= k <= nums.length <= 10⁵ 
// 0 <= nums[i] <= 10⁶ 
// 
//
// Related Topics Array Prefix Sum 👍 342 👎 19

package leetcode.editor.en;

// 2023-12-28 00:59:24
// Jesse Yang
public class Leetcode2772ApplyOperationsToMakeAllArrayElementsEqualToZero{
    // Java: apply-operations-to-make-all-array-elements-equal-to-zero
    public static void main(String[] args) {
        Solution sol = new Leetcode2772ApplyOperationsToMakeAllArrayElementsEqualToZero().new Solution();
        // TO TEST
        int[] nums = {0,45,82,98,99};
        int k = 4;
        System.out.println(sol.checkArray(nums, k));
    }
//leetcode submit region begin(Prohibit modification and deletion)
// T(n) = O(n), S(n) = O(1),
/*
original array: [2,2,3,1,1,0]
in order to let the 1st elements become 0, we have to decrease first 3 elements by 2 [2, 2, 3] - 2
[0, 0, 1, 1, 1, 0]
... in order to make following no-zero number to 0, we have to decrease that number as well[1,1,1]-2
[0, 0, 0, 0, 0]


operation 1: [2,2,3,1,1,0]
operation 2: [0,0,1,1,1,0]
operation 3: [0,0,0,0,0,0]

Time complexity is too high, we need to optimize it.
直接模拟的时间复杂度是 O(nk)，该时间复杂度过高，需要优化。

可以考虑使用差分数组降低时间复杂度。创建长度为 n 的差分数组 diffs，
其中 diffs[i] = nums[i] − nums[i − 1]
特别地，定义 nums[−1]=0，则 diffs[0] = nums[0]
对于 0 ≤ i < n−k，将数组 nums下标范围 [i,i + k − 1]的子数组的每个元素值都减少 nums[i]
等价于将 diffs[i]的值减少 nums[i]并将 diffs[i + k]的值增加 nums[i]，
当 i + k = n 时不更新 diffs[i+k]的值。

如果最终能达成目的，则diff数组中所有的数字都会是0；
如果diff数组中所有的数字都是0，
目标是将差分数组 diffs中的所有元素值都变成 0。

1. 如果 diffs[i] < 0则不能使 diffs[i]变成0，返回 false
2. 如果 diffs[i] > 0，则需要将 diffs[i]的值减少 nums[i] 并将 diffs[i+k]的值增加 nums[i]，考虑以下情况。
    2.1 如果 i + k > n，则不能实现操作，返回 false
    2.2 如果 i + k = n，则将 diffs[i]的值更新为0，不更新 diffs[i+k]的值
    2.3 如果 i + k < n，则将 diffs[i + k] 的值增加 diffs[i]，然后将 diffs[i] 的值更新为 0
    

总结：通过差分数组，把需要在原数组上操作长度为k的部分，变成在差分数组上操作2次。从而把时间复杂度从O(nk)减小到O(n)

 */
class Solution {
    public boolean checkArray(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        // general case
        int len = nums.length;
        int[] diff = new int[len];
        
        diff[0] = nums[0];
        for (int i = 1; i < len; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
        
        for (int i = 0; i < len; i++) {
            if (diff[i] < 0) {
                return false;
            } else if (diff[i] > 0) {
                if (i + k > len) {
                    return false;
                } else if (i + k == len) {
                    diff[i] = 0;
                } else {
                    diff[i + k] += diff[i];
                    diff[i] = 0;
                }
            }
            // only left possibility diff[i] == 0, no action needed, do next loop
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}