//Given an unsorted array of integers nums, return the length of the longest con
//secutive elements sequence. 
//
// Follow up: Could you implement the O(n) solution? 
//
// 
// Example 1: 
//
// 
//Input: nums = [100,4,200,1,3,2]
//Output: 4
//Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Theref
//ore its length is 4.
// 
//
// Example 2: 
//
// 
//Input: nums = [0,3,7,2,5,8,4,6,0,1]
//Output: 9
// 
//
// 
// Constraints: 
//
// 
// 0 <= nums.length <= 104 
// -109 <= nums[i] <= 109 
// 
// Related Topics Array Union Find 
// 👍 4276 👎 208

package leetcode.editor.en;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 2020-11-25 20:44:05
// Zeshi Yang
public class Leetcode0128LongestConsecutiveSequence{
    // Java: longest-consecutive-sequence
    public static void main(String[] args) {
        Solution sol = new Leetcode0128LongestConsecutiveSequence().new Solution();
        // TO TEST
        int[] nums = {1, 4, 6, 2, 3, 8, 9, 11, 13, 14, 15, 5};
        int res = sol.longestConsecutive(nums);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestConsecutive(int[] nums) {
        
        HashSet<Integer> set = new HashSet<>();
        
        for(int num: nums) {
            set.add(num);
        }
        int max = 0;
        for (int num : nums) {
            // 这样是从连续序列里面的最小值开始算
            int count = 0;
            if (set.contains(num)) {
                count = 1;
            } else {
                continue;
            }
            int left = num - 1;
            while (set.contains(left)) {
                set.remove(left);
                left--;
                count++;
            }
            int right = num + 1;
            while (set.contains(right)) {
                set.remove(right);
                right++;
                count++;
            }
            max = Math.max(max, count);
        }
        return max;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1_1: T(n) = O(n), S(n) = O(n)
/**
 * 先把nums里面的所有元素存到HashSet里面，
 * 然后遍历数组，每次找到一个数字，就统计包含这个数字的连续数字串的长度，并把这些数字串remove掉
 * 遍历完所有的连续数字串，选出数字串的最大长度
 */
class Solution1_1 {
    
    public int longestConsecutive(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;
        for (int num : nums) {
            // 这样是从连续序列里面的最小值开始算
            int count = 0;
            if (set.contains(num)) {
                count = 1;
            } else {
                continue;
            }
            int left = num - 1;
            while (set.contains(left)) {
                set.remove(left);
                left--;
                count++;
            }
            int right = num + 1;
            while (set.contains(right)) {
                set.remove(right);
                right++;
                count++;
            }
            max = Math.max(max, count);
        }
        return max;
    }
    
}

// Solution 1_2: T(n) = O(n), S(n) = O(n)
/**
 * 先把nums里面的所有元素存到HashSet里面，
 * 然后遍历数组，每次找到一个连续数字串的开头（这个连续数字串），就一直往后统计这个连续数字串的长度
 * 遍历完所有的连续数字串，选出数字串的最大长度
 */
class Solution1_2 {
    
    public int longestConsecutive(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;
        for (int num : nums) {
            // 这样是从连续序列里面的最小值开始算
            if (!set.contains(num - 1)) {
                int count = 0;
                while (set.contains(num)) {
                    count++;
                    num += 1;
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }
    
}
// Solution 2: T(n) = O(n), S(n) = O(n)
/**
 * 假如我们已经了有连续的序列，123 和 56，并且序列的边界保存了当前序列的长度。
 * 1  2  3
 * 3     3  <- 序列长度
 *
 * 5  6
 * 2  2  <- 序列长度
 *
 * 此时来了一个数字 4
 * 我们只需要考虑 4 - 1 = 3,以 3 结尾的序列的长度
 * 以及 4 + 1 = 5,以 5 开头的序列的长度
 * 所以当前就会得到一个包含 4 的，长度为 3 + 1 + 2 = 6 的序列
 * 1  2  3  4  5  6
 * 3     3     2  2   <- 序列长度
 *
 * 此时把两个边界的长度进行更新
 * 1  2  3  4  5  6
 * 6     3     2  6   <- 序列长度
 *
 * 此时如果又来了 7
 * 我们只需要考虑 7 - 1 = 6,以 6 结尾的序列的长度
 * 以及 7 + 1 = 8,以 8 开头的序列的长度，但是不存在以 8 开头的序列，所以这个长度是 0
 * 所以当前就会得到一个包含 7 的，长度为 6 + 1 + 0 = 7 的序列
 * 1  2  3  4  5  6  7
 * 6     3     2  6     <- 序列长度
 *
 * 此时把两个边界的长度进行更新
 * 1  2  3  4  5  6 7
 * 7     3     2  6 7  <- 序列长度
 */
class Solution2 {
    
    public int longestConsecutive(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            /*
            已经考虑过的数字就跳过，必须跳过，不然会出错
            比如 [1 2 1]
            最后的 1 如果不跳过，因为之前的 2 的最长长度已经更新成 2 了，所以会出错
            */
            if (map.containsKey(num)) {
                continue;
            }
            
            int left = map.getOrDefault(num - 1, 0); //找到以左边数字结尾的最长序列，默认为 0
            int right = map.getOrDefault(num + 1, 0); //找到以右边数开头的最长序列，默认为 0
            int sum = left + 1 + right;
            max = Math.max(max, sum);
            
            map.put(num, -1);//将当前数字放到 map 中，防止重复考虑数字，value 可以随便给一个值
            map.put(num - left, sum); //更新左边界长度
            map.put(num + right, sum); //更新右边界长度
        }
        return max;
    }
    
}
}