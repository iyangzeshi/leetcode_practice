//Given a sorted array nums, remove the duplicates in-place such that duplicates
// appeared at most twice and return the new length. 
//
// Do not allocate extra space for another array, you must do this by modifying 
//the input array in-place with O(1) extra memory. 
//
// Example 1: 
//
// 
//Given nums = [1,1,1,2,2,3],
//
//Your function should return length = 5, with the first five elements of nums b
//eing 1, 1, 2, 2 and 3 respectively.
//
//It doesn't matter what you leave beyond the returned length. 
//
// Example 2: 
//
// 
//Given nums = [0,0,1,1,1,1,2,3,3],
//
//Your function should return length = 7, with the first seven elements of nums 
//being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
//
//It doesn't matter what values are set beyond the returned length.
// 
//
// Clarification: 
//
// Confused why the returned value is an integer but your answer is an array? 
//
// Note that the input array is passed in by reference, which means modification
// to the input array will be known to the caller as well. 
//
// Internally you can think of this: 
//
// 
//// nums is passed in by reference. (i.e., without making a copy)
//int len = removeDuplicates(nums);
//
//// any modification to nums in your function would be known by the caller.
//// using the length returned by your function, it prints the first len element
//s.
//for (int i = 0; i < len; i++) {
//    print(nums[i]);
//}
// 
// Related Topics Array Two Pointers 
// 👍 1173 👎 705

package leetcode.editor.en;

// 2020-07-26 13:38:33
// Jesse Yang
public class Leetcode0080RemoveDuplicatesFromSortedArrayIi{
    // Java: remove-duplicates-from-sorted-array-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0080RemoveDuplicatesFromSortedArrayIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
[0, slow) solution so far
[slow, fast) i don't care 可以被reuse的区域
[fast, array.length - 1] remain to check 不能被override的区域
*/

class Solution {
    public int removeDuplicates(int[] nums) {
        // corner case
        int k = 2;
        if (nums == null || nums.length <= k) {
            return nums.length;
        }
        int slow = k;
        for (int fast = k; fast < nums.length; fast++) {
            if (nums[slow - k] != nums[fast]) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}