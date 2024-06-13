//You are climbing a stair case. It takes n steps to reach to the top. 
//
// Each time you can either climb 1 or 2 steps. In how many distinct ways can yo
//u climb to the top? 
//
// Example 1: 
//
// 
//Input: 2
//Output: 2
//Explanation: There are two ways to climb to the top.
//1. 1 step + 1 step
//2. 2 steps
// 
//
// Example 2: 
//
// 
//Input: 3
//Output: 3
//Explanation: There are three ways to climb to the top.
//1. 1 step + 1 step + 1 step
//2. 1 step + 2 steps
//3. 2 steps + 1 step
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 45 
// 
// Related Topics Dynamic Programming 
// 👍 4559 👎 146

package leetcode.editor.en;

// 2020-08-04 11:25:35
// Jesse Yang
public class Leetcode0070ClimbingStairs{
    // Java: climbing-stairs
    public static void main(String[] args) {
        Solution sol = new Leetcode0070ClimbingStairs().new Solution();
        // TO TEST
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int cur = 0;
        int pre1 = 1;
        int pre2 = 2;

        for (int i = 3; i <= n; i++) {
            cur = pre1 + pre2;
            pre1 = pre2;
            pre2 = cur;
        }
        return cur;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}