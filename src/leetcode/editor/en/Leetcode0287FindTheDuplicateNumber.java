//Given an array of integers nums containing n + 1 integers where each integer i
//s in the range [1, n] inclusive. 
//
// There is only one duplicate number in nums, return this duplicate number. 
//
// Follow-ups: 
//
// 
// How can we prove that at least one duplicate number must exist in nums? 
// Can you solve the problem without modifying the array nums? 
// Can you solve the problem using only constant, O(1) extra space? 
// Can you solve the problem with runtime complexity less than O(n2)? 
// 
//
// 
// Example 1: 
// Input: nums = [1,3,4,2,2]
//Output: 2
// Example 2: 
// Input: nums = [3,1,3,4,2]
//Output: 3
// Example 3: 
// Input: nums = [1,1]
//Output: 1
// Example 4: 
// Input: nums = [1,1,2]
//Output: 1
// 
// 
// Constraints: 
//
// 
// 2 <= n <= 3 * 104 
// nums.length == n + 1 
// 1 <= nums[i] <= n 
// All the integers in nums appear only once except for precisely one integer wh
//ich appears two or more times. 
// 
// Related Topics Array Two Pointers Binary Search 
// 👍 5469 👎 630

package leetcode.editor.en;

// 2020-09-10 21:07:20
// Jesse Yang
public class Leetcode0287FindTheDuplicateNumber{
    // Java: find-the-duplicate-number
    public static void main(String[] args) {
        Solution sol = new Leetcode0287FindTheDuplicateNumber().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Floyd's Tortoise and Hare (Cycle Detection)
class Solution {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return -1;
        }
        int slow = nums[nums[0]];
        int fast = nums[slow];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = nums[0];
        while (slow != fast) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return fast;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}