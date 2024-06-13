//Given an integer array, find three numbers whose product is maximum and output
// the maximum product. 
//
// Example 1: 
//
// 
//Input: [1,2,3]
//Output: 6
// 
//
// 
//
// Example 2: 
//
// 
//Input: [1,2,3,4]
//Output: 24
// 
//
// 
//
// Note: 
//
// 
// The length of the given array will be in range [3,104] and all elements are i
//n the range [-1000, 1000]. 
// Multiplication of any three numbers in the input won't exceed the range of 32
//-bit signed integer. 
// 
//
// 
// Related Topics Array Math 
// 👍 1124 👎 369

package leetcode.editor.en;

import java.util.*;
// 2020-07-26 14:04:27
// Jesse Yang
public class Leetcode0628MaximumProductOfThreeNumbers{
    // Java: maximum-product-of-three-numbers
    public static void main(String[] args) {
        Solution sol = new Leetcode0628MaximumProductOfThreeNumbers().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximumProduct(int[] nums) {
        int len = nums.length;
        // corner case
        if (len == 3) {
            return nums[0] * nums[1] * nums[2];
        }

        // general case
        PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder()); // maxHeap
        PriorityQueue<Integer> large = new PriorityQueue<>(); // minHeap
        small.offer(nums[0]);
        small.offer(nums[1]);
        large.offer(nums[0]);
        large.offer(nums[1]);
        large.offer(nums[2]);

        for (int i = 3; i < len; i++) {
            if (nums[i] < small.peek()) {
                small.poll();
                small.offer(nums[i]);
            }

            if (nums[i] > large.peek()) {
                large.poll();
                large.offer(nums[i]);
            }
        }
        int[] largest = {large.poll(), large.poll(), large.poll()};
        int[] smallest = {small.poll(), small.poll()};
        int max1 = largest[0] * largest[1] * largest[2];
        int max2 = smallest[0] * smallest[1] * largest[2];
        return Math.max(max1, max2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}