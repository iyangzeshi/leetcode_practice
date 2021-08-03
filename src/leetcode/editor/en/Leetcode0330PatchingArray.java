//Given a sorted positive integer array nums and an integer n, add/patch element
//s to the array such that any number in range [1, n] inclusive can be formed by t
//he sum of some elements in the array. Return the minimum number of patches required.
//
// Example 1: 
//
// 
//Input: nums = [1,3], n = 6
//Output: 1 
//Explanation:
//Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4
//.
//Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,
//3], [1,2,3].
//Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
//So we only need 1 patch. 
//
// Example 2: 
//
// 
//Input: nums = [1,5,10], n = 20
//Output: 2
//Explanation: The two patches can be [2, 4].
// 
//
// Example 3: 
//
// 
//Input: nums = [1,2,2], n = 5
//Output: 0
// Related Topics Greedy 
// 👍 566 👎 77

package leetcode.editor.en;

// 2020-11-13 12:01:07
// Zeshi Yang
public class Leetcode0330PatchingArray{
    // Java: patching-array
    public static void main(String[] args) {
        Solution sol = new Leetcode0330PatchingArray().new Solution();
        // TO TEST
        int[] nums = {};
        int n = 7;
        int res = sol.minPatches(nums, n);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
1. 贪心法则： 对于一个覆盖 [1,n] 的数组 arr 来说，添加数字 n 连续扩容范围最大，扩容至 [1,2n]
2. 思路： 设置一个初始范围 [1,1] ，通过不断确认并扩大数组可以覆盖的范围，最终计算出最少需要加入的数。
3. 当nums[i] <= 左开右闭的右边界add的时候，不需要加入新值，只需要更新右边界为add + nums[i]，而一旦nums[i]越过覆盖范围的右边界add的时候，根据上述贪心法则double add的size直到包含当前的nums[i]，每double add一次就相当于加入一个新值，count + 1。
4. 该题由于是不断加和，要注意把边界add设置为long，否则可能会因为出界而报错！
 */
class Solution {

    // time = O(n), space = O(1)
    public int minPatches(int[] nums, int n) {
        // corner case
        if (nums == null || n == 0) {
            return 0;
        }

        int len = nums.length;
        long border = 1; // current existing and added number can reach to [1, border]
        int count = 0;
        int i = 0;
        while (border <= n) {
            if (i < len && nums[i] <= border) {
                border += nums[i++];
            } else {
                border *= 2;
                count++;
            }
        }
        return count;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}