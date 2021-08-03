//Given an unsorted array of integers, find the length of longest increasing sub
//sequence. 
//
// Example: 
//
// 
//Input: [10,9,2,5,3,7,101,18]
//Output: 4 
//Explanation: The longest increasing subsequence is [2,3,7,101], therefore the 
//length is 4. 
//
// Note: 
//
// 
// There may be more than one LIS combination, it is only necessary for you to r
//eturn the length. 
// Your algorithm should run in O(n2) complexity. 
// 
//
// Follow up: Could you improve it to O(n log n) time complexity? 
// Related Topics Binary Search Dynamic Programming 
// 👍 4696 👎 109

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// 2020-07-08 00:23:55
public class Leetcode0300LongestIncreasingSubsequence{
    // Java: longest-increasing-subsequence
    public static void main(String[] args) {
        Solution sol = new Leetcode0300LongestIncreasingSubsequence().new Solution();
        // TO TEST
        int[] nums = {0,1,0,3,2,3};
        int res = sol.lengthOfLIS(nums);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int lengthOfLIS(int[] nums) {
        List<Integer> buffer = new ArrayList<>();
        for (int num : nums) {
            /*int index = Collections.binarySearch(buffer, num);
            if (index >= 0) {
                continue;
            } else {
                index = -index - 1;
            }*/
            int index = getIndex(buffer, num);
            if (index < buffer.size()) {
                buffer.set(index, num);
            } else {
                buffer.add(num);
            }
        }
        return buffer.size();
    }
    
    // 在buffer中找到>= target的值的索引index
    private int getIndex(List<Integer> buffer, int target) {
        int len = buffer.size();
        // corner case
        if (len == 0) {
            return 0;
        }
        if (buffer.get(len - 1) < target) {
            return len;
        }
        
        // general case
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (buffer.get(mid) == target) {
                return mid;
            } else if (buffer.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
        
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// 看到subsequence，要先想到能够分叉，然后肯定可以用dp来解

// Solution 1: DP T(n) = O(n^2), S(n) = O(n)
// 60 ms,击败了19.73% 的Java用户, 38.4 MB,击败了44.86% 的Java用户
/*
dp[i]: 以nums[i]结尾的longest Increasing subsequence的长度
dp[i]=max(dp[j]+1) if (0≤j<i & nums[j]<nums[i])
*/
class Solution1 {
    
    public int lengthOfLIS(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int len = nums.length;
        int[] dp = new int[len]; // dp[i]: [0, i] len of LIS
        Arrays.fill(dp, 1);
        int max = 1;
        
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    
}

// Solution 2: T(n) = O(nlog(n)), S(n) = O(n)
//4 ms,击败了74.71% 的Java用户, 38.6 MB,击败了35.23% 的Java用户
class Solution2 {
    
    public int lengthOfLIS(int[] nums) {
        List<Integer> buffer = new ArrayList<>();
        for (int num : nums) {
            /*int index = Collections.binarySearch(buffer, num);
            if (index >= 0) {
                continue;
            } else {
                index = -index - 1;
            }*/
            int index = getIndex(buffer, num);
            if (index < buffer.size()) {
                buffer.set(index, num);
            } else {
                buffer.add(num);
            }
        }
        return buffer.size();
    }
    
    // 在buffer中找到>= target的值的索引index
    private int getIndex(List<Integer> buffer, int target) {
        int len = buffer.size();
        // corner case
        if (len == 0) {
            return 0;
        }
        if (buffer.get(len - 1) < target) {
            return len;
        }
        
        // general case
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (buffer.get(mid) == target) {
                return mid;
            } else if (buffer.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
        
    }
}
}