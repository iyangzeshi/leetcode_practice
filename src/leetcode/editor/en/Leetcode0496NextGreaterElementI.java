//
//You are given two arrays (without duplicates) nums1 and nums2 where nums1’s el
//ements are subset of nums2. Find all the next greater numbers for nums1's elemen
//ts in the corresponding places of nums2. 
// 
//
// 
//The Next Greater Number of a number x in nums1 is the first greater number to 
//its right in nums2. If it does not exist, output -1 for this number.
// 
//
// Example 1: 
// 
//Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
//Output: [-1,3,-1]
//Explanation:
//    For number 4 in the first array, you cannot find the next greater number f
//or it in the second array, so output -1.
//    For number 1 in the first array, the next greater number for it in the sec
//ond array is 3.
//    For number 2 in the first array, there is no next greater number for it in
// the second array, so output -1.
// 
// 
//
// Example 2: 
// 
//Input: nums1 = [2,4], nums2 = [1,2,3,4].
//Output: [3,-1]
//Explanation:
//    For number 2 in the first array, the next greater number for it in the sec
//ond array is 3.
//    For number 4 in the first array, there is no next greater number for it in
// the second array, so output -1.
// 
// 
//
//
// Note: 
// 
// All elements in nums1 and nums2 are unique. 
// The length of both nums1 and nums2 would not exceed 1000. 
// 
// Related Topics Stack 
// 👍 1756 👎 2308

package leetcode.editor.en;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// 2020-09-10 14:07:09
// Jesse Yang
public class Leetcode0496NextGreaterElementI{
    // Java: next-greater-element-i
    public static void main(String[] args) {
        Solution sol = new Leetcode0496NextGreaterElementI().new Solution();
        // TO TEST
        int[] nums1 ={2, 4};
        int[] nums2 ={1,2,3,4};
        int[] res = sol.nextGreaterElement(nums1, nums2);
        System.out.println(Arrays.toString(res));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
// T(n) = O(n), S(n) = O(n), using non increasing stack to mimic the research process
/*
step 1: for every num2 in nums2,
build a map that the value of map is the next greater element in array nums2
	keep a non-increasing stack that store the num2 in the arrays nums2
	for every coming num2,
		if num2 > stack.peek(), this coming num2 is next greater number of the stack.peek, keep
		pop until the stack.peek() <= num2 and push num2
		if num2 <= stack.peek() this coming num2 is not the target number of the stack.peek, push it
step 2: traverse num1 in array nums1, get the target number and make it a array
 */
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // corner case
        if (nums1 == null || nums1.length == 0) {
            return new int[0];
        }

        Map<Integer, Integer> nums2NextGreaterNum = new HashMap<>();
        int len1 = nums1.length;
        Stack<Integer> stack = new Stack<>(); // non-increasing stack
	    for (int num : nums2) {
			if (stack.isEmpty()) {
				stack.push(num);
			} else if (num > stack.peek()) {
				while (!stack.isEmpty() && num > stack.peek()) {
					nums2NextGreaterNum.put(stack.peek(), num);
					stack.pop();
				}
				stack.push(num);
			} else {
				stack.push(num);
			}
	    }
        int[] res = new int[len1];
        for (int i = 0; i < len1; i++) {
            res[i] = nums2NextGreaterNum.getOrDefault(nums1[i], -1);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}