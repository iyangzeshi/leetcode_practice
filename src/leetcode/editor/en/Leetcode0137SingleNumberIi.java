//Given a non-empty array of integers, every element appears three times except 
//for one, which appears exactly once. Find that single one. 
//
// Note: 
//
// Your algorithm should have a linear runtime complexity. Could you implement i
//t without using extra memory? 
//
// Example 1: 
//
// 
//Input: [2,2,3,2]
//Output: 3
// 
//
// Example 2: 
//
// 
//Input: [0,1,0,1,0,1,99]
//Output: 99 
// Related Topics Bit Manipulation 
// 👍 1856 👎 356

package leetcode.editor.en;

// 2020-07-29 21:02:21
// Jesse Yang
public class Leetcode0137SingleNumberIi {

	// Java: single-number-ii
	public static void main(String[] args) {
		Solution sol = new Leetcode0137SingleNumberIi().new Solution();
		// TO TEST
		int[] nums = {1, 1, 1, 2, 2, 2, 4, 4, 5, 5, 6, 5, 4};
		int m = 3;
		int n = 1;
		int res = sol.singleNumber(nums);
		System.out.println(res);
	}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new RuntimeException("should never happen");
        }
        int[] count = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                count[i] += num & 1;
                num >>= 1;
            }
        }
		int res = 0;
        for (int i = 0; i < 32; i++) {
            if (count[i] % 3 != 0) {
                res += (1 << i);
            }
        }
        return res;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

}