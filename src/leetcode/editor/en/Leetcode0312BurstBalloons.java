//Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number
// on it represented by array nums. You are asked to burst all the balloons. If th
//e you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Her
//e left and right are adjacent indices of i. After the burst, the left and right 
//then becomes adjacent. 
//
// Find the maximum coins you can collect by bursting the balloons wisely. 
//
// Note: 
//
// 
// You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can n
//ot burst them. 
// 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100 
// 
//
// Example: 
//
// 
//Input: [3,1,5,8]
//Output: 167 
//Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
//             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
// Related Topics Divide and Conquer Dynamic Programming

package leetcode.editor.en;
/**
  *@ClassName: Leetcode312BurstBalloons
  *@Description:
  *@Author: Jesse Yang
  *@Date: 2020/07/03 周五 00:20
  */
public class Leetcode0312BurstBalloons {
    // Java: burst-balloons
    public static void main(String[] args) {
        Solution sol = new Leetcode0312BurstBalloons().new Solution();
        // TO TEST
        int[] nums = {3,1,5,8};
        System.out.println(sol.maxCoins(nums));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // dynamic programming
    public int maxCoins(int[] nums) {
        //corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[][] dp = new int[len][len];
    
        /*for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }*/
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                int max = 0;
                for (int k = i; k <= j; k++) { // k 在[i, j]之间
                    int val = 0;
                    val += (k == i ? 0 : dp[i][k - 1]); // 加[i, k - 1]
                    val += (k == j ? 0 : dp[k + 1][j]); // 加 [k + 1, j]
                    val += (i == 0 ? 1 : nums[i - 1]) * nums[k] * (j == len - 1 ? 1 : nums[j + 1]);
                    max = Math.max(max, val);
                }
                dp[i][j] = max;
            }
        }
        return dp[0][len - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}