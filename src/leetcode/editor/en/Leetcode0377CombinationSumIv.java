//Given an integer array with all positive numbers and no duplicates, find the n
//umber of possible combinations that add up to a positive integer target. 
//
// Example: 
//
// 
//nums = [1, 2, 3]
//target = 4
//
//The possible combination ways are:
//(1, 1, 1, 1)
//(1, 1, 2)
//(1, 2, 1)
//(1, 3)
//(2, 1, 1)
//(2, 2)
//(3, 1)
//
//Note that different sequences are counted as different combinations.
//
//Therefore the output is 7.
// 
//
// 
//
// Follow up: 
//What if negative numbers are allowed in the given array? 
//How does it change the problem? 
//What limitation we need to add to the question to allow negative numbers? 
//
// Credits: 
//Special thanks to @pbrother for adding this problem and creating all test case
//s. 
// Related Topics Dynamic Programming 
// 👍 1699 👎 206

package leetcode.editor.en;

// 2020-11-10 13:20:28
// Jesse Yang
public class Leetcode0377CombinationSumIv{
    // Java: combination-sum-iv
    public static void main(String[] args) {
        Solution sol = new Leetcode0377CombinationSumIv().new Solution();
        // TO TEST
        int[] nums = {1, 2, 3, 4};
        int target = 4;
        int res = sol.combinationSum4(nums, target);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// T(n) = O(m * target), S(n) = O(target)
class Solution {
    public int combinationSum4(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // dp[j] means the number of combinations satisfying sum of them is j
        int[] dp = new int[target + 1];
        dp[0] = 1;
        int len = nums.length;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i < num) {
                    continue;
                }
                dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: DFS with memo, 0 ms, 37MB, T(m, target) = O(m * target), S(m, target) = O(target)
class Solution1 {
    
    public int combinationSum4(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // dp[j] means the number of combinations satisfying sum of them is j
        Integer[] dp = new Integer[target + 1];
        return dfs(target, nums, dp);
    }
    
    private int dfs(int sum, int[] nums, Integer[] memo) {
        // base case
        if (sum < 0) {
            return 0;
        }
        if (sum == 0) {
            memo[0] = 1;
            return 1;
        }
        if (memo[sum] != null) {
            return memo[sum];
        }
        
        int len = nums.length;
        int res = 0;
        for (int num : nums) {
            res += dfs(sum - num, nums, memo);
        }
        memo[sum] = res;
        return res;
    }
    
}

// Solution 2: DP, 1 ms, 36.3MB, T(m, target) = O(m * target), S(m, target) = O(target)
class Solution2 {
    
    public int combinationSum4(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // dp[j] means the number of combinations satisfying sum of them is j
        int[] dp = new int[target + 1];
        dp[0] = 1;
        int len = nums.length;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i < num) {
                    continue;
                }
                dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }
    
}

}