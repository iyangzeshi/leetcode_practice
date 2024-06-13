//You are a professional robber planning to rob houses along a street. Each hous
//e has a certain amount of money stashed. All houses at this place are arranged i
//n a circle. That means the first house is the neighbor of the last one. Meanwhil
//e, adjacent houses have a security system connected, and it will automatically c
//ontact the police if two adjacent houses were broken into on the same night. 
//
// Given a list of non-negative integers nums representing the amount of money o
//f each house, return the maximum amount of money you can rob tonight without ale
//rting the police. 
//
// 
// Example 1: 
//
// 
//Input: nums = [2,3,2]
//Output: 3
//Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 
//2), because they are adjacent houses.
// 
//
// Example 2: 
//
// 
//Input: nums = [1,2,3,1]
//Output: 4
//Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//Total amount you can rob = 1 + 3 = 4.
// 
//
// Example 3: 
//
// 
//Input: nums = [0]
//Output: 0
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 1000 
// 
// Related Topics Dynamic Programming 
// 👍 2298 👎 56

package leetcode.editor.en;

// 2020-10-21 18:40:38
// Jesse Yang
public class Leetcode0213HouseRobberIi{
    // Java: house-robber-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0213HouseRobberIi().new Solution();
        // TO TEST
        int[] nums = {1, 2, 1, 1};
        int res = sol.rob(nums);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int rob(int[] nums) {
        // corner case
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int len = nums.length;
        return Math.max(getLastAmount(0, len - 1, nums), getLastAmount(1, len - 1, nums));
    }
    
    /**
     *
     * @param from: the start sub array index in the nums array
     * @param len: the length of start array
     * @param nums: given int[]
     * @return: the last rob amount value in the sub array[from, from + len)
     */
    private int getLastAmount(int from, int len, int[] nums) {
        int[] dp = new int[len + 1];
        dp[len - 1] = nums[len - 1 + from];
        for (int i = len - 2; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], dp[i + 2] + nums[i + from]);
        }
        return dp[0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: dp, whether it picks the last one and do dp respectively
class Solution1 {
    public int rob(int[] nums) {
        // corner case
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int len = nums.length;
        // dp[i] means the largest amount when robbing beginning from i
        int[] dpRobLast = new int[len + 1];
        int[] dpNotRobLast = new int[len + 1];
        
        // initialization
        dpRobLast[len - 1] = nums[len - 1];
        dpRobLast[len - 2] = 0;
        dpNotRobLast[len - 1] = 0;
        dpNotRobLast[len - 2] = nums[len - 2];
        
        for (int i = len - 3; i >= 0; i--) {
            dpRobLast[i] = nums[i] + Math.max(dpRobLast[i + 2], dpRobLast[i + 3]);
            dpNotRobLast[i] = nums[i] + Math.max(dpNotRobLast[i + 2], dpNotRobLast[i + 3]);
        }
        return Math.max(
			Math.max(dpNotRobLast[0],
				dpNotRobLast[1]
			),
	        Math.max(dpRobLast[1],
		        dpRobLast[2]
	        )
        );
    }
}

// Solution 2: dp, 从sub array[0, len -2] 和 sub array[1, len - 1]里面取的最大值，分别求最大值
class Solution2 {
    public int rob(int[] nums) {
        // corner case
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int len = nums.length;
        return Math.max(getLastAmount(0, len - 1, nums), getLastAmount(1, len - 1, nums));
    }
    
    /**
     *
     * @param from: the start sub array index in the nums array
     * @param len: the length of start array
     * @param nums: given int[]
     * @return: the last rob amount value in the sub array
     */
    private int getLastAmount(int from, int len, int[] nums) {
        int[] dp = new int[len + 1];
        dp[len - 1] = nums[len - 1 + from];
        for (int i = len - 2; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], dp[i + 2] + nums[i + from]);
        }
        return dp[0];
    }
}

}