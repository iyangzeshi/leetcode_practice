
//Given an array of integers arr and an integer d. In one step you can jump from
// index i to index: 
//
// 
// i + x where: i + x < arr.length and 0 < x <= d. 
// i - x where: i - x >= 0 and 0 < x <= d. 
// 
//
// In addition, you can only jump from index i to index j if arr[i] > arr[j] and
// arr[i] > arr[k] for all indices k between i and j (More formally min(i, j) < k 
//< max(i, j)). 
//
// You can choose any index of the array and start jumping. Return the maximum n
//umber of indices you can visit. 
//
// Notice that you can not jump outside of the array at any time. 
//
// 
// Example 1: 
//
// 
//Input: arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
//Output: 4
//Explanation: You can start at index 10. You can jump 10 --> 8 --> 6 --> 7 as s
//hown.
//Note that if you start at index 6 you can only jump to index 7. You cannot jum
//p to index 5 because 13 > 9. You cannot jump to index 4 because index 5 is betwe
//en index 4 and 6 and 13 > 9.
//Similarly You cannot jump from index 3 to index 2 or index 1.
// 
//
// Example 2: 
//
// 
//Input: arr = [3,3,3,3,3], d = 3
//Output: 1
//Explanation: You can start at any index. You always cannot jump to any index.
// 
//
// Example 3: 
//
// 
//Input: arr = [7,6,5,4,3,2,1], d = 1
//Output: 7
//Explanation: Start at index 0. You can visit all the indicies. 
// 
//
// Example 4: 
//
// 
//Input: arr = [7,1,7,1,7,1], d = 2
//Output: 2
// 
//
// Example 5: 
//
// 
//Input: arr = [66], d = 1
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= arr.length <= 1000 
// 1 <= arr[i] <= 10^5 
// 1 <= d <= arr.length 
// Related Topics Dynamic Programming 
// 👍 313 👎 13

package leetcode.editor.en;

// 2021-02-08 16:16:12
// Zeshi Yang
public class Leetcode1340JumpGameV{
    // Java: jump-game-v
    public static void main(String[] args) {
        Solution sol = new Leetcode1340JumpGameV().new Solution();
        // TO TEST
        int[] arr = {7,6,5,4,3,2,1};
        int d = 1;
        int res = sol.maxJumps(arr, d);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// DFS,T(n) = O(n), S(n) = O(n)
// 10 ms,击败了65.35% 的Java用户, 39.3 MB,击败了51.82% 的Java用户
/*
用DFS， 转化公式
base case:
    波谷的时候，return 1
dp[i] = max(dp[j]) + 1
    where i != j,
    0 <= j < arr.length,
    i - d <= j <= i + d
    and j的范围是一段每个值都arr[j] < arr[i]
 */
class Solution {
    
    public int maxJumps(int[] arr, int d) {
        // corner case
        int len = arr.length;
        if (len <= 1) {
            return len;
        }
        Integer[] dp = new Integer[len];//用来存储每个柱子的最大结果
        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans = Math.max(ans, maxStepsCanJump(arr, i, d, dp));
        }
        return ans;
    }
    
    private int maxStepsCanJump(int[] arr, int index, int d, Integer[] dp) {
        int res = 0;
        int len = arr.length;
        if (dp[index] != null) {
            return dp[index];   //当前柱子已经计算过，直接返回它的值
        }
        // base case
        if (isValley(arr, index)) {
            dp[index] = 1;
            res = 1;
            return res;
        }
        
        for (int i = index - 1; i >= Math.max(0, index - d); i--) {
            if (arr[i] >= arr[index]) {
                break;
            }
            res = Math.max(res, maxStepsCanJump(arr, i, d, dp));
        }
        for (int i = index + 1; i <= Math.min(len - 1, index + d); i++) {
            if (arr[i] >= arr[index]) {
                break;
            }
            res = Math.max(res, maxStepsCanJump(arr, i, d, dp));
        }
        dp[index] = res + 1;
        return res + 1;
    }
    
    // 判断是不是波谷
    private boolean isValley(int[] arr, int index) {
        int len = arr.length;
        if (index == 0 && arr[index] <= arr[index + 1] || index == len - 1 && arr[index] <= arr[index - 1]) {
            return true;
        }
        /*
        if (index - 1 >= 0 && index + 1 <= len - 1 && arr[index - 1] >= arr[index] && arr[index] <= arr[index + 1]) {
            return true;
        }
        return false;
         */
        return index - 1 >= 0 && index + 1 <= len - 1 && arr[index - 1] >= arr[index]
                && arr[index] <= arr[index + 1];
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}