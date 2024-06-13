//Given an array of integers with possible duplicates, randomly output the index
// of a given target number. You can assume that the given target number must exis
//t in the array. 
//
// Note: 
//The array size can be very large. Solution that uses too much extra space will
// not pass the judge. 
//
// Example: 
//
// 
//int[] nums = new int[] {1,2,3,3,3};
//Solution solution = new Solution(nums);
//
//// pick(3) should return either index 2, 3, or 4 randomly. Each index should h
//ave equal probability of returning.
//solution.pick(3);
//
//// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
//solution.pick(1);
// 
// Related Topics Reservoir Sampling 
// 👍 496 👎 685

package leetcode.editor.en;

import java.util.Random;

// 2020-08-05 21:32:28
// Jesse Yang
public class Leetcode0398RandomPickIndex {

	// Java: random-pick-index
	public static void main(String[] args) {
	    int[] nums = {1,2,3,3,3};
		Solution sol = new Leetcode0398RandomPickIndex().new Solution(nums);
		int res = sol.pick(3);
		// TO TEST
		System.out.println(res);
	}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

	private int[] nums;

	public Solution(int[] nums) {
		this.nums = nums;
	}

	public int pick(int target) {
		int count = 0;
		int sample = -1;
		Random random = new Random();
		int len = nums.length;
		for (int i = 0; i < len ; i++) {
			if (nums[i] == target) {
				int idx = random.nextInt(++count);
				if (idx == 0) {
					sample = i;
				}
			}
		}
		if (count == 0) {
			throw new RuntimeException("Not exist in array");
		} else {
			return sample;
		}
	}
}

/*
  Your Solution object will be instantiated and called as such:
  Solution obj = new Solution(nums);
  int param_1 = obj.pick(target);
 */
//leetcode submit region end(Prohibit modification and deletion)

}