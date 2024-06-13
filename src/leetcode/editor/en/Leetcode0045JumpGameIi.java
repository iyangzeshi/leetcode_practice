//Given an array of non-negative integers, you are initially positioned at the f
//irst index of the array. 
//
// Each element in the array represents your maximum jump length at that positio
//n. 
//
// Your goal is to reach the last index in the minimum number of jumps. 
//
// Example: 
//
// 
//Input: [2,3,1,1,4]
//Output: 2
//Explanation: The minimum number of jumps to reach the last index is 2.
//    Jump 1 step from index 0 to 1, then 3 steps to the last index. 
//
// Note: 
//
// You can assume that you can always reach the last index. 
// Related Topics Array Greedy 
// 👍 2595 👎 138

package leetcode.editor.en;

import java.util.ArrayDeque;
import java.util.Queue;

// 2020-07-26 12:47:55
// Jesse Yang
public class Leetcode0045JumpGameIi{
    // Java: jump-game-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0045JumpGameIi().new Solution();
        // TO TEST
        int[] nums = {1, 2, 1, 1, 1};
        int res = sol.jump(nums);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// first to think about corner cases: null, length = 1, or it can not reach the last element
class Solution {
    
    public int jump(int[] nums) {
        //corner case
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int len = nums.length;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        int max = 0;
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int index = queue.poll();
                for (int i = max + 1; i <= index + nums[index]; i++) {
                    if (i >= len - 1) {
                        return step + 1;
                    }
                    if (i > max) {
                        queue.offer(i);
                        max = i;
                    }
                }
            }
            step++;
        }
        throw new IllegalArgumentException("can not happen");
        // return step;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: bfs, T(n) = O(n), S(n) = O(1)
// 4 ms,击败了30.15% 的Java用户, 41.5 MB,击败了21.40% 的Java用户
/*
max当前能reach到的最大的范围
 */
class Solution1 {
    
    public int jump(int[] nums) {
        //corner case
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int len = nums.length;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        int max = 0;
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int index = queue.poll();
                for (int i = max + 1; i <= index + nums[index]; i++) {
                    if (i >= len - 1) {
                        return step + 1;
                    }
                    if (i > max) {
                        queue.offer(i);
                        max = i;
                    }
                }
            }
            step++;
        }
        throw new IllegalArgumentException("can not happen");
        // return step;
    }
    
}

// Solution 2: greedy, step step by step
/**
 4  5   0   3   2   1   1   10  3
 i
 preMax = 0	                pm
 curMax = 0                     cm
 minStep = 0
 curMax always record the farthest place that previous elements can reach
 preMax always record the farthest place of the same step
 */
// first to think about corner cases: null, length = 1, or it can not reach the last elemenet
class Solution2 {
    
    public int jump(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int len = nums.length;
        
        int preMax = 0;
        int curMax = 0;
        int minStep = 0;
        
        for (int i = 0; i < len; i++) {
            // corner case, if it can not reach the last element
            if (i > curMax) {
                return -1;
            }
            if (curMax >= len - 1) { // it has been far than the last element
                return minStep + 1;
            }
            if (i > preMax) { // to update the preMax
                minStep++;
                preMax = curMax;
            }
            curMax = Math.max(curMax, i + nums[i]);
        }
        return minStep;
    }
    
}
}