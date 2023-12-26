//You are given a 0-indexed array nums of length n, consisting of non-negative 
//integers. For each index i from 0 to n - 1, you must determine the size of the 
//minimum sized non-empty subarray of nums starting at i (inclusive) that has the 
//maximum possible bitwise OR. 
//
// 
// In other words, let Bij be the bitwise OR of the subarray nums[i...j]. You 
//need to find the smallest subarray starting at i, such that bitwise OR of this 
//subarray is equal to max(Bik) where i <= k <= n - 1. 
// 
//
// The bitwise OR of an array is the bitwise OR of all the numbers in it. 
//
// Return an integer array answer of size n where answer[i] is the length of 
//the minimum sized subarray starting at i with maximum bitwise OR. 
//
// A subarray is a contiguous non-empty sequence of elements within an array. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,0,2,1,3]
//Output: [3,3,2,2,1]
//Explanation:
//The maximum possible bitwise OR starting at any index is 3. 
//- Starting at index 0, the shortest subarray that yields it is [1,0,2].
//- Starting at index 1, the shortest subarray that yields the maximum bitwise 
//OR is [0,2,1].
//- Starting at index 2, the shortest subarray that yields the maximum bitwise 
//OR is [2,1].
//- Starting at index 3, the shortest subarray that yields the maximum bitwise 
//OR is [1,3].
//- Starting at index 4, the shortest subarray that yields the maximum bitwise 
//OR is [3].
//Therefore, we return [3,3,2,2,1]. 
// 
//
// Example 2: 
//
// 
//Input: nums = [1,2]
//Output: [2,1]
//Explanation:
//Starting at index 0, the shortest subarray that yields the maximum bitwise OR 
//is of length 2.
//Starting at index 1, the shortest subarray that yields the maximum bitwise OR 
//is of length 1.
//Therefore, we return [2,1].
// 
//
// 
// Constraints: 
//
// 
// n == nums.length 
// 1 <= n <= 10⁵ 
// 0 <= nums[i] <= 10⁹ 
// 
//
// Related Topics Array Binary Search Bit Manipulation Sliding Window 👍 531 👎 
//22

package leetcode.editor.en;

import java.util.Arrays;

// 2023-12-18 23:59:06
// Jesse Yang
public class Leetcode2411SmallestSubarraysWithMaximumBitwiseOr{
    // Java: smallest-subarrays-with-maximum-bitwise-or
    public static void main(String[] args) {
        Solution sol = new Leetcode2411SmallestSubarraysWithMaximumBitwiseOr().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)

// T(n) = O(n), S(n) = O(n)
/*
思路：
设数组nums[i]; 0 <= i <= n, n = nums.length
数字里面的每个数字化成2进制，j <= 30, 最多30位
相当于把数组中的每个数字化成二进制，这样可以得到一个二维数组。
二维数组的每一列都是nums[i]化成2进制的结果。
e.g. nums ={0, 5, 2, 5}

7       0   5   2   5

1       0   1   0   1
1       0   0   1   0
1       0   1   0   1

题目的问题，相当于问对于nums[i]，每一位二进制数字往右找到第一个1，就是这一位数字变成1的地方。
这个距离设置dist[j],则对于nums[i]来说，他对应的结果就是max{dist[j]}, 0 <= j <= 30

最直接的思想设置一个二维数组dp[i][j], dp[i][j]表示nums[i]二进制第j位 右边第一个1的位置
从左往右遍历。

状态压缩 - 从右往左遍历
后来发现其实可以，如果从右往左遍历的滑，就只需要一个一维数组

算法：

1. dp数组，表示按位存储最近的1的位置。初始化为-1，表示没有1
2. 从后往前遍历，对于第i个位置：
2.1 按位遍历，如果出现1，则修改当前位的dp为i。
2.2 如果当前位的dp不为-1，说明需要计算最远的位置dp-i+1。
2.3 所有位最远的位置即为最短的长度。


 */
class Solution {
    public int[] smallestSubarrays(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        int len = nums.length;
        int[] dp = new int[32];
        Arrays.fill(dp, -1);
        int[] res = new int[len];
        
        for (int i = len - 1; i >= 0; i--) {
            // distance of nums[i]'s every binary number to next bit 1 in following numbers
            int dist = 1;
            for (int j = 0; j < 31; j++) {
                if ((nums[i] >> j & 1) == 1) {
                    dp[j] = i;
                }
                if (dp[j] != -1) {
                    dist = Math.max(dist, dp[j] - i + 1);
                }
            }
            res[i] = dist;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}