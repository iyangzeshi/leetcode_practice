
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
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    int[] arr;
    int n; //数组长度
    int d;
    int[] dp;   //用来存储每个柱子的最大结果
    
    public int maxJumps(int[] arr, int d) {
        this.arr = arr;
        this.n = arr.length;
        this.d = d;
        dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, getMaxFromOnePoint(i));
        }
        return ans;
    }
    
    private int getMaxFromOnePoint(int p) {
        if (dp[p] != 0) {
            return dp[p];   //当前柱子已经计算过，直接返回它的值
        }
        // 如果没有，分别计算它往左和往右跳一次可以得到的最大值
        int leftMax = 0;
        int l = 1;  // 往左跳的距离
        while (p - l >= 0 && l <= d) {
            if (arr[p - l] >= arr[p]) {   //遇到了高柱子挡路，只能结束
                break;
            } else {
                if (dp[p - l] == 0) {
                    dp[p - l] = getMaxFromOnePoint(p - l);
                }
                leftMax = Math.max(leftMax, dp[p - l]);
                l++;
            }
        }
        // 同理右边
        int rightMax = 0;
        int r = 1;
        while (p + r < n && r <= d) {
            if (arr[p + r] >= arr[p]) {
                break;
            } else {
                if (dp[p + r] == 0) {
                    dp[p + r] = getMaxFromOnePoint(p + r);
                }
                rightMax = Math.max(rightMax, dp[p + r]);
                r++;
            }
        }
        dp[p] = Math.max(leftMax, rightMax) + 1;
        return dp[p];
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}
