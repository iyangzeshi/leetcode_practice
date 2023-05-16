
/**
Given a circular integer array nums (i.e., the next element of nums[nums.length 
- 1] is nums[0]), return the next greater number for every element in nums. 

 The next greater number of a number x is the first greater number to its 
traversing-order next in the array, which means you could search circularly to find 
its next greater number. If it doesn't exist, return -1 for this number. 

 
 Example 1: 

 
Input: nums = [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number. 
The second 1's next greater number needs to search circularly, which is also 2.
 

 Example 2: 

 
Input: nums = [1,2,3,4,3]
Output: [2,3,4,-1,4]
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁴ 
 -10⁹ <= nums[i] <= 10⁹ 
 

 Related Topics Array Stack Monotonic Stack 👍 6296 👎 170

*/
package leetcode.editor.en;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

// 2023-02-15 22:13:21
// Jesse Yang
public class Leetcode0503NextGreaterElementIi{
    // Java: next-greater-element-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0503NextGreaterElementIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// T(n) = O(n), S(n) = O(n), using non increasing stack to mimic the research process
/*
step1 : traverse nums2, find the largest num in nums2 and store it in the HashSet
step 2: for every num2 in nums2,
build a map that the value of map is the next greater element in array nums2
	keep a stack that store the num2's index of non-increasing number in the arrays nums2
	for every coming num2,
		if num2 > nums2[stack.peek()], this coming num2 is next greater number of the stack.peek,
		keep popping until the nums2[stack.peek()] <= num2 and push num2's index
		if num2 <= nums2[stack.peek()] this coming num2 is not the target number of the stack.peek, push it
step 2: traverse num1 in array nums1, get the target number and make it a array
 */
class Solution {
    public int[] nextGreaterElements(int[] nums) {
	    int[] res = new int[nums.length];
	    Arrays.fill(res, -1);
	    int maxNum = findMax(nums);
	    Set<Integer> maxIndex = getMaxIndex(maxNum, nums);
	    // stack that stores the index of non-increasing number in array nums
	    Stack<Integer> stack = new Stack<>();
		boolean flag = false; // traverse more than 1 time
	    for (int i = 0; i < nums.length; i++) {
		    if (stack.isEmpty()) {
				stack.push(i);
		    } else if (nums[i] > nums[stack.peek()]) {
				while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
					int index = stack.pop();
					res[index] = nums[i];
				}
				stack.push(i);
		    } else { // nums[i] <= nums[stack.peek()]
				stack.push(i);
		    }
			
		    if (flag && maxIndex.contains(i)) {
			    break;
		    }
			if (i == nums.length - 1) {
				i = -1; // so that in next loop, i = 0;
				flag = true;
			}
	    }
        return res;
    }
	
	private int findMax(int[] nums) {
		int max = Integer.MIN_VALUE;
		for (int num : nums) {
			max = Math.max(max, num);
		}
		return max;
	}
	
	private Set<Integer> getMaxIndex(int maxNum, int[] nums) {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (maxNum == nums[i]) {
				set.add(i);
			}
		}
		return set;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}
