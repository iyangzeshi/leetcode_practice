//Given an array of size n, find the majority element. The majority element is t
//he element that appears more than ⌊ n/2 ⌋ times. 
//
// You may assume that the array is non-empty and the majority element always ex
//ist in the array. 
//
// Example 1: 
//
// 
//Input: [3,2,3]
//Output: 3 
//
// Example 2: 
//
// 
//Input: [2,2,1,1,1,2,2]
//Output: 2
// 
// Related Topics Array Divide and Conquer Bit Manipulation 
// 👍 3326 👎 224

package leetcode.editor.en;

// 2020-07-29 21:49:44
// Zeshi Yang
public class Leetcode0169MajorityElement {

	// Java: majority-element
	public static void main(String[] args) {
		Solution sol = new Leetcode0169MajorityElement().new Solution();
		// TO TEST

		int[] nums = {2, 2, 1, 1, 1, 2, 2, 1, 1};
		int res = sol.majorityElement(nums);
		System.out.println(res);
	}

//leetcode submit region begin(Prohibit modification and deletion)
// 2 ms,击败了64.45% 的Java用户, 45.1 MB,击败了19.16% 的Java用户
class Solution {
    
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;
        
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        
        return candidate;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: bit operation, T(n) = O(n), S(n) = O(1)
// 4 ms,击败了48.31% 的Java用户, 42.3 MB,击败了71.57% 的Java用户
/*
设置一个32位长的数组，统计里面所有数字的二进制的合（不进位）
int halfLen = len / 2;
超过一半的数字是相同的。那二进制求和的，这些位上面的数字>= halfLen的话，说明这个数字在这一位上就是1
 */
class Solution1 {
    
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new RuntimeException("should never happen");
        }
        int INT_BIT_NUMBER = 32;
        int[] count = new int[INT_BIT_NUMBER];
        for (int num : nums) {
            for (int i = 0; i < INT_BIT_NUMBER; i++) {
                count[i] += num & 1;
                num >>= 1;
            }
        }
        int res = 0;
        int len = nums.length;
        int halfLen = len / 2;
        for (int i = 0; i < INT_BIT_NUMBER; i++) {
            if (count[i] > halfLen) {
                res += (1 << i);
            }
        }
        return res;
    }
    
}

// Solution 2: Boyer-Moore Voting Algorithm, T(n) = O(n), S(n) = O(1)
// 2 ms,击败了64.45% 的Java用户, 45.1 MB,击败了19.16% 的Java用户
/*
target number's count must > len / 2
so the count - left number's count > 0
record the candidate and maintain its count
 */
class Solution2 {
    
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;
        
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        
        return candidate;
    }
    
}
}