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
        int[] nums = {10,9,2,5,3,7,101,18,19};
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
    
    // 在buffer中找到 > target值的第1个索引index
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
            if (buffer.get(mid) == target) { // since buffer is increasing(no duplicate)
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

// Solution 2: Greedy: T(n) = O(nlog(n)), S(n) = O(n)
//4 ms,击败了74.71% 的Java用户, 38.6 MB,击败了35.23% 的Java用户
/*
keep increasing list buffer

从DP的方法推导出来
step1 :
我们定义dp[i] 为以 nums[i] 结尾的最长长度。为了算 dp[i]，我们要看前面所有的 dp[j]。这太慢了。

step 2:
与其记录“以哪个位置结尾”，不如记录**“达到某个长度，最少需要多大的末尾数字”**？
长度为 1 的序列，末尾最小能是多少？
长度为 2 的序列，末尾最小能是多少？
{10, 20, 30, 5, 6, 7, 8}
len 1: 5
len 2: 5,6
len 3: 5,6,7
len 4: 5,6,7,8


step 3:
sequence 不同len的candidate他们的最后一个元素都是递增的，所以

step 4:：引入二分既然 tails 是有序的，当我们看到一个新的数字 $x$ 时，我们就不需要遍历 tails了。
直接用二分查找找到它该去的位置：如果它能让某个长度的“最小末尾”变得更小，就替换它（贪心）。
如果它比所有的末尾都大，就开辟一个新的长度（增加长度）。

最终结果
 */
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
    
    // using binary search 在buffer中找到>= target的值的索引index
    
    /**
     *
     * @param buffer 加油
     * @param target
     * @return
     */
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
