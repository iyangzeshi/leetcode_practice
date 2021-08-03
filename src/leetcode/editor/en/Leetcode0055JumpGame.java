//Given an array of non-negative integers, you are initially positioned at the f
//irst index of the array. 
//
// Each element in the array represents your maximum jump length at that positio
//n. 
//
// Determine if you are able to reach the last index. 
//
// 
// Example 1: 
//
// 
//Input: nums = [2,3,1,1,4]
//Output: true
//Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
// 
//
// Example 2: 
//
// 
//Input: nums = [3,2,1,0,4]
//Output: false
//Explanation: You will always arrive at index 3 no matter what. Its maximum jum
//p length is 0, which makes it impossible to reach the last index.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 3 * 10^4 
// 0 <= nums[i][j] <= 10^5 
// 
// Related Topics Array Greedy 
// 👍 4353 👎 329

package leetcode.editor.en;

import java.util.ArrayDeque;
import java.util.Queue;

// 2020-07-26 12:45:53
// Zeshi Yang
public class Leetcode0055JumpGame{
    // Java: jump-game
    public static void main(String[] args) {
        Solution sol = new Leetcode0055JumpGame().new Solution();
        // TO TEST
        int[] nums = {2,3,1,1,4};
        boolean res = sol.canJump(nums);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public boolean canJump(int[] nums) {
        //corner case
        if (nums == null || nums.length < 2) {
            return true;
        }
        int len = nums.length;
        Queue<Integer> queue = new ArrayDeque<>();
        int max = 0;
        queue.offer(0);
        while (!queue.isEmpty()) {
            int index = queue.poll();
            /*
              本来是要把index + 1到index + nums[index]加到queue里面的，
              但是比max小的元素，都已经遍历过了
              所以直接从max + 1开始加
             */
            for (int i = max + 1; i <= index + nums[index]; i++) {
                if (i >= len - 1) {
                    return true;
                }
                if (i > max) {
                    queue.offer(i);
                    max = i;
                }
            }
        }
        return false;
    }
    
}

//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: BFS, T(n) = O(n), S(n) = O(n)
// 4 ms,击败了36.17% 的Java用户, 40.3 MB,击败了99.00% 的Java用户
class Solution1 {
    
    public boolean canJump(int[] nums) {
        //corner case
        if (nums == null || nums.length < 2) {
            return true;
        }
        int len = nums.length;
        Queue<Integer> queue = new ArrayDeque<>();
        int max = 0;
        queue.offer(0);
        while (!queue.isEmpty()) {
            int index = queue.poll();
            /*
              本来是要把index + 1到index + nums[index]加到queue里面的，
              但是比max小的元素，都已经遍历过了
              所以直接从max + 1开始加
             */
            for (int i = max + 1; i <= index + nums[index]; i++) {
                if (i >= len - 1) {
                    return true;
                }
                if (i > max) {
                    queue.offer(i);
                    max = i;
                }
            }
        }
        return false;
    }
    
}

// Solution2: DFS recursion
class Solution2 {
    
    public boolean canJump(int[] nums) {
        int index = 0;
        return canJump(nums, index);
    }
    
    public boolean canJump(int[] nums, int index) {
        if (index >= nums.length - 1) {
            return true; // base case
        }
        int jump = nums[index];
        for (int i = 1; i <= jump; i++) { // 从远到近
            if (canJump(nums, index + i)) {
                return true;
            }
        }
        return false;
    }
    
}

// Solution 3: dynamic programming
// Solution 3_1 dynamic programming
// T(n) = O(n^2), S(n) = O(n), ,每个true的dp值只会被遍历一次, false的不一定，false的可能遍历多次
// 307 ms,击败了26.49% 的Java用户,40.7 MB,击败了90.53% 的Java用户
/*
dp[i] means whether nums[i] can reach to nums[len - 1]
fill the dp table from right to left
 */
class Solution3_1 {
    
    //fill the dp table from right to left
    public boolean canJump(int[] nums) {
        //corner case
        if (nums == null || nums.length < 2) {
            return true;
        }
        int len = nums.length;
        boolean[] dp = new boolean[len];
        
        dp[len - 1] = true;
        
        for (int i = len - 2; i >= 0; i--) {
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j < len && dp[i + j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }
    
}

//Solution 3_2 dynamic programming
// T(n) = O(n^2), S(n) = O(n),每个true的dp值只会被遍历一次
// 278 ms,击败了27.77% 的Java用户, 41.2 MB,击败了45.47% 的Java用户，false的可能遍历多次
/*
dp[i] means whether nums[i] can reach to nums[len - 1]
fill the dp table from right to left
 */
class Solution3_2 {
    
    //fill the dp table from right to left
    public boolean canJump(int[] nums) {
        //corner case
        if (nums == null || nums.length < 2) {
            return true;
        }
        int len = nums.length;
        boolean[] dp = new boolean[len];
        dp[len - 1] = true;
        
        for (int i = len - 2; i >= 0; i--) {
            for (int j = nums[i]; j >= 1; j--) {
                if (i + j >= len - 1 || dp[i + j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }
    
}

// Solution 3_3 DP
// T(n) = O(n), S(n) = O(n),dp数组里面每个值只被遍历一次,从0走到最后一个能reach的点为止
// 2 ms,击败了44.22% 的Java用户, 41 MB,击败了53.92% 的Java用户
/*
dp[i] means whether we can reach to nums[i] from 0
 */
class Solution3_3 {
    
    //fill the dp table from right to left
    public boolean canJump(int[] nums) {
        //corner case
        if (nums == null || nums.length < 2) {
            return true;
        }
        int len = nums.length;
        boolean[] dp = new boolean[len]; // whether nums[i] can be reach from nums[0]
        
        dp[0] = true;
        int prev = 0;
        int prevMax = nums[0];
        int curMax = 0;
        while (prev < len) {
            for (int j = prev; j <= prevMax && j < len; j++) {
                curMax = Math.max(curMax, j + nums[j]);
                if (j < len && !dp[j]) {
                    dp[j] = true;
                }
            }
            if (prevMax == curMax) {
                break;
            }
            prev = prevMax + 1;
            prevMax = curMax;
        }
        return dp[len - 1];
    }
    
}

// Solution 4:
// greedy, T(n) = O(n), S(n) = O(1),从0走到最后一个能reach的点为止
// 1 ms,击败了86.85% 的Java用户, 40.9 MB,击败了64.09% 的Java用户
class Solution4 {
    
    public boolean canJump(int[] nums) {
        //corner case
        if (nums == null || nums.length < 2) {
            return true;
        }
        
        int maxRange = 0;
        for (int i = 0; i <= maxRange; i++) {
            maxRange = Math.max(maxRange, i + nums[i]);
            if (maxRange >= nums.length - 1) {
                return true;
            }
        }
        return false;
        
    }
    
}
}