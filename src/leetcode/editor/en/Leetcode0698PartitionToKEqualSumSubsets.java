//Given an array of integers nums and a positive integer k, find whether it's po
//ssible to divide this array into k non-empty subsets whose sums are all equal. 
//
// 
//
// Example 1: 
//
// 
//Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
//Output: True
//Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,
//3) with equal sums.
// 
//
// 
//
// Note: 
//
// 
// 1 <= k <= len(nums) <= 16. 
// 0 < nums[i] < 10000. 
// 
// Related Topics Dynamic Programming Recursion 
// 👍 2701 👎 172

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 2021-03-11 16:13:13
// Zeshi Yang
public class Leetcode0698PartitionToKEqualSumSubsets{
    // Java: partition-to-k-equal-sum-subsets
    public static void main(String[] args) {
        Solution sol = new Leetcode0698PartitionToKEqualSumSubsets().new Solution();
        // TO TEST
        int[] nums = new int[25];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        int k = 5;
        List<List<Integer>> res = new Followup().partitionKSubsets(nums, k);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// DFS, 这组数字分成等分的k租。每次遇到一个数字num，有k种方法放置
// 1 ms,击败了90.92% 的Java用户, 38.9 MB,击败了23.17% 的Java用户
class Solution {
    
    public boolean canPartitionKSubsets(int[] nums, int k) {
        // corner case
        if (k == 1) {
            return true;
        }
        if (nums == null || nums.length == 0 || k > nums.length) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k > 0) {
            return false;
        }
        int target = sum / k;
        Arrays.sort(nums);
        int len = nums.length - 1;
        if (nums[len] > target) {
            return false;
        }
        // 如果运行到这里，还有值是n/k的，那么这个值一定在数组的最后面
        while (len >= 0 && nums[len] == target) {
            len--;
            k--;
        }
        return search(len, new int[k], nums, target);
    }
    
    /*
    用groups分别表示，不同的k个subset的sum
    每次遇到一个数字num，都有k种放法，每个group都有可能把这个num放进去
    T(n, k) = O(k^n), S(n, k) = O(n)
     */
    public boolean search(int index, int[] groups, int[] nums, int target) {
        if (index < 0) {
            return true;
        }
        int num = nums[index];
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + num <= target) {
                groups[i] += num;
                if (search(index - 1, groups, nums, target)) {
                    return true;
                }
                groups[i] -= num;
            }
            if (groups[i] == 0) {
                break;
            }
        }
        return false;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

// follow up, 把分配的结果返回出来
static class Followup {
    
    public List<List<Integer>> partitionKSubsets(int[] nums, int k) {
        // corner case
        if (k == 1) {
            return null;
        }
        if (nums == null || nums.length == 0 || k > nums.length) {
            return new ArrayList<>();
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k > 0) {
            return null;
        }
        
        int target = sum / k;
        Arrays.sort(nums);
        int rows = nums.length - 1;
        if (nums[rows] > target) {
            return null;
        }
        while (rows >= 0 && nums[rows] == target) {
            rows--;
            k--;
        }
        List<List<Integer>> res = new ArrayList<>(k);
        search(rows, nums, target, new int[k], res);
        return res;
    }
    
    /*
    要把nums分成k堆，让每一堆的合都相等
    思路就是，没遇到一个num，遍历所有的情况，他放在0 ~ k-1 堆中的一堆都是有可能的
    如果放到某一堆，这一堆的合出界超过 > target的话，就不要加入到这一对里面了
     */
    public boolean search(int index, int[] nums, int target, int[] groups,
            List<List<Integer>> combinations) {
        if (index < 0) {
            return true;
        }
        
        int num = nums[index];
        for (int i = 0; i < groups.length; i++) {
            if (i >= combinations.size()) {
                combinations.add(new ArrayList<>());
            }
            if (groups[i] + num <= target) {
                groups[i] += num;
                int size = combinations.get(i).size();
                combinations.get(i).add(num);
                if (search(index - 1, nums, target, groups, combinations)) {
                    return true;
                }
                groups[i] -= num;
                combinations.get(i).remove(size);
            }
            if (groups[i] == 0) {
                break;
            }
        }
        return false;
    }
    
}
}