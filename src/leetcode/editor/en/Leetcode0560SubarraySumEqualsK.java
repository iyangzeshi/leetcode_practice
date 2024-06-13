//Given an array of integers and an integer k, you need to find the total number
// of continuous subarrays whose sum equals to k. 
//
// Example 1: 
//
// 
//Input:nums = [1,1,1], k = 2
//Output: 2
// 
//
// 
// Constraints: 
//
// 
// The length of the array is in range [1, 20,000]. 
// The range of numbers in the array is [-1000, 1000] and the range of the integ
//er k is [-1e7, 1e7]. 
// 
// Related Topics Array Hash Table 
// 👍 4822 👎 159

package leetcode.editor.en;

import java.util.*;
// 2020-07-26 13:33:40
// Jesse Yang
public class Leetcode0560SubarraySumEqualsK{
    // Java: subarray-sum-equals-k
    public static void main(String[] args) {
        Solution sol = new Leetcode0560SubarraySumEqualsK().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int subarraySum(int[] nums, int target) {
        //corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        HashMap<Integer, Integer> map = new HashMap<>();// key: curSum, value: occurrences of curSum
        map.put(0, 1);  // 千万别忘了
        int curSum = 0;
        int count = 0;
        for (int num : nums) {
            curSum = curSum + num;
            if (map.containsKey(curSum - target)) {
                count += map.get(curSum - target);
            }
            map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
/**面试的时候，用Solution 2 */

// Solution 1: brute force, traverse all the subArray, cumSum, T(n) = O(n^2), O(n) = O(n)
// 1273 ms,击败了12.46% 的Java用户, 41.3 MB,击败了74.25% 的Java用户
class Solution1 {

    public int subarraySum(int[] nums, int target) {
        //corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int count = 0;
        int[] cumsum = new int[nums.length + 1];
        cumsum[0] = 0;
        for (int i = 1; i < cumsum.length; i++) {
            cumsum[i] = cumsum[i - 1] + nums[i - 1];
        }

        for (int start = 0; start < nums.length; start++) {
            for(int end = start + 1; end <= nums.length; end++) {
                if (cumsum[end] - cumsum[start] == target) {
                    count++;
                }
            }
        }
        return count;
    }
}

// Solution 2: hashMap
// 17 ms,击败了96.12% 的Java用户, 41.8 MB,击败了43.81% 的Java用户
/*解题思路：
先得出cumulative sum的array，如果target符合要求的话。一定存在某个 i 和 j， 使得
    cumsum[j]- cumsum[i] = target。(j > i)或者
    cumsum[j] = target
所以一定有:
    cumsum[j] - target = cumsum[i]
    cumsum[j] = target
联想到HashMap

解决方法：
step1: HashMap<Integer, Integer> key- cumSum, value: count of curSum
    hashMap先加一个(0, 1)
step2: 然后遍历cumsum数组，每遍历一个数字，看把cumsum[i]放到HashMap里面，
step2: 然后看cumsum[i] - target在不在HashSet里面，在的话，就表示存在
*/
class Solution2 {

    public int subarraySum(int[] nums, int target) {
        //corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();// key: curSum, value: count of curSum
        map.put(0, 1);  // 千万别忘了
        int curSum = 0;
        int count = 0;
        for (int num : nums) {
            curSum = curSum + num;
            if (map.containsKey(curSum - target)) {
                count += map.get(curSum - target);
            }
            map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        }
        return count;
    }
}

}