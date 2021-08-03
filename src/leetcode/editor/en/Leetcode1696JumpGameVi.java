
//You are given a 0-indexed integer array nums and an integer k. 
//
// You are initially standing at index 0. In one move, you can jump at most k st
//eps forward without going outside the boundaries of the array. That is, you can 
//jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive
//. 
//
// You want to reach the last index of the array (index n - 1). Your score is th
//e sum of all nums[j] for each index j you visited in the array. 
//
// Return the maximum score you can get. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,-1,-2,4,-7,3], k = 2
//Output: 7
//Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (und
//erlined above). The sum is 7.
// 
//
// Example 2: 
//
// 
//Input: nums = [10,-5,-2,4,0,3], k = 3
//Output: 17
//Explanation: You can choose your jumps forming the subsequence [10,4,3] (under
//lined above). The sum is 17.
// 
//
// Example 3: 
//
// 
//Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
//Output: 0
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length, k <= 105 
// -104 <= nums[i] <= 104 
// 
// Related Topics Dequeue 
// 👍 279 👎 19

package leetcode.editor.en;

import java.util.ArrayDeque;
import java.util.Deque;

// 2021-02-08 16:17:10
// Zeshi Yang
public class Leetcode1696JumpGameVi{
    // Java: jump-game-vi
    public static void main(String[] args) {
        Solution sol = new Leetcode1696JumpGameVi().new Solution();
        // TO TEST
        int[] nums = {10,-5,-2,4,0,3};
        int k = 3;
        int res = sol.maxResult(nums, k);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// descending deque, T(n) = O(n), S(n) = O(k)
// 21 ms,击败了63.34% 的Java用户,55.2 MB,击败了46.32% 的Java用户
/*

it is like Leetcode 239,
for every i, we need to find the max value from previous windows[i - k, i -1],
    for so the score[i] = max value from windows + nums[i]

improvement way 1:
first we think about dp, for every i, we traverse every value in the window.
T(n) = O(n*k), TLE. So we need to improve it.

improvement way 2:
We can use heap<int[]{index, value}> to find the max value in the previous window
T(n) = O(n * log(k)

improvement way 3:
we can maintain the descending deque,
whose first elements is always the max value in the previous window.

programming step for improvement way 3
    for every i:
        if the peekFirst lay before the previous window, pop it
        then get the peekFirst, which is max value in the previous window.
            so the current score is the max value in previous window + nums[i]
        pop the last element until the deque last element is null or larger than the score
        then push the score into deque.
    
   so we consider to assign element in the deque as int[] {index, score}
 */
class Solution {
    
    public int maxResult(int[] nums, int k) {
        int len = nums.length;
        int score = nums[0];
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offerLast(new int[]{0, score});
        for (int i = 1; i < len; i++) {
            // pop the old index
            if (deque.peekFirst() != null && deque.peekFirst()[0] < i - k) {
                deque.pollFirst();
            }
            score = deque.peek()[1] + nums[i];
            // pop the smaller value
            while (deque.peekLast() != null && score >= deque.peekLast()[1]) {
                deque.pollLast();
            }
            deque.offerLast(new int[]{i, score});
        }
        return score;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}
