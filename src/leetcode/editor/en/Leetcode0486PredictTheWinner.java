//Given an array of scores that are non-negative integers. Player 1 picks one of
// the numbers from either end of the array followed by the player 2 and then play
//er 1 and so on. Each time a player picks a number, that number will not be avail
//able for the next player. This continues until all the scores have been chosen. 
//The player with the maximum score wins. 
//
// Given an array of scores, predict whether player 1 is the winner. You can ass
//ume each player plays to maximize his score. 
//
// Example 1: 
// 
//Input: [1, 5, 2]
//Output: False
//Explanation: Initially, player 1 can choose between 1 and 2. If he chooses 2 (
//or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then
// player 1 will be left with 1 (or 2). So, final score of player 1 is 1 + 2 = 3, 
//and player 2 is 5. Hence, player 1 will never be the winner and you need to retu
//rn False.
// 
// 
//
// Example 2: 
// 
//Input: [1, 5, 233, 7]
//Output: True
//Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 
//and 7. No matter which number player 2 choose, player 1 can choose 233. Finally,
// player 1 has more score (234) than player 2 (12), so you need to return True re
//presenting player1 can win.
// 
// 
//
// Note: 
// 
// 1 <= length of the array <= 20. 
// Any scores in the given array are non-negative integers and will not exceed 1
//0,000,000. 
// If the scores of both players are equal, then player 1 is still the winner. 
// 
// Related Topics Dynamic Programming Minimax 
// 👍 1437 👎 83

package leetcode.editor.en;

// 2020-07-03 13:20:42
public class Leetcode0486PredictTheWinner {
	
	// Java: predict-the-winner
	public static void main(String[] args) {
		
		Solution sol = new Leetcode0486PredictTheWinner().new Solution();
		// TO TEST
		
		System.out.println();
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    // dynamic programming
    // dp[i][j]=max{nums[i]+min⁡(dp[i+2,j],dp[i+1,j-1]), nums[j]+min(dp[i+1,j-1],dp[i,j-2])}
    public boolean PredictTheWinner(int[] nums) {
        
        int len = nums.length;
        int[][] dp = new int[len][len];
        // 区间是[i, j]，所以i <= j
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = nums[i];
            for (int j = i; j < len; j++) {
                int val1 = (i + 2 <= j ? dp[i + 2][j] : 0);
                int val2 = (i + 1 <= j - 1 ? dp[i + 1][j - 1] : 0);
                int val3 = (i <= j - 2 ? dp[i][j - 2] : 0);
                dp[i][j] = Math.max(nums[i] + Math.min(val1, val2), nums[j] + Math.min(val2, val3));
                
            }
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
	    return 2 * dp[0][len - 1] >= sum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}