//You are a professional robber planning to rob houses along a street. Each hous
//e has a certain amount of money stashed, the only constraint stopping you from r
//obbing each of them is that adjacent houses have security system connected and i
//t will automatically contact the police if two adjacent houses were broken into 
//on the same night. 
//
// Given a list of non-negative integers representing the amount of money of eac
//h house, determine the maximum amount of money you can rob tonight without alert
//ing the police. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,3,1]
//Output: 4
//Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//             Total amount you can rob = 1 + 3 = 4.
// 
//
// Example 2: 
//
// 
//Input: nums = [2,7,9,3,1]
//Output: 12
//Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 
//(money = 1).
//             Total amount you can rob = 2 + 9 + 1 = 12.
// 
//
// 
// Constraints: 
//
// 
// 0 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
// Related Topics Dynamic Programming 
// 👍 5443 👎 164

package leetcode.editor.en;

// 2020-09-14 12:15:05
// Jesse Yang
public class Leetcode0198HouseRobber{
    // Java: house-robber
    public static void main(String[] args) {
        Solution sol = new Leetcode0198HouseRobber().new Solution();
        // TO TEST
        
        System.out.println();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
// T(n) = O(n), S(n) = O(1)
class Solution {
    public int rob(int[] nums) {
        int preMax = 0; // in the end of loop, the preMax is max value on choosing prev location
        int curMax = 0; // in the end of loop, the preMax is max value on choosing current location
        for (int num: nums) {
            int temp = curMax;
            curMax = Math.max(curMax, preMax + num);
            preMax = temp;
        }
        return curMax;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}