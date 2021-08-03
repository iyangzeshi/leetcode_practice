//Given a sorted array nums, remove the duplicates in-place such that each eleme
//nt appear only once and return the new length. 
//
// Do not allocate extra space for another array, you must do this by modifying 
//the input array in-place with O(1) extra memory. 
//
// Example 1: 
//
// 
//Given nums = [1,1,2],
//
//Your function should return length = 2, with the first two elements of nums be
//ing 1 and 2 respectively.
//
//It doesn't matter what you leave beyond the returned length. 
//
// Example 2: 
//
// 
//Given nums = [0,0,1,1,1,2,2,3,3,4],
//
//Your function should return length = 5, with the first five elements of nums b
//eing modified to 0, 1, 2, 3, and 4 respectively.
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
//} Related Topics Array Two Pointers 
// 👍 2625 👎 5295

package leetcode.editor.en;

// 2020-07-26 12:20:56
// Zeshi Yang
public class Leetcode0026RemoveDuplicatesFromSortedArray{
    // Java: remove-duplicates-from-sorted-array
    public static void main(String[] args) {
        Solution sol = new Leetcode0026RemoveDuplicatesFromSortedArray().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return -1;
        }
        if(nums.length <= 1) {
            return nums.length;
        }
        int slow = 0;

        //S2:
        // [0, s] solution so far
        // (s, f) explored and i don't care / will be update or assign
        // [f, length - 1] unknown to explore

        for(int fast = 1; fast < nums.length; fast++) {
            if (nums[slow] != nums[fast]) {
                nums[++slow] = nums[fast];
            }
        }
        return slow + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: [0, s) solution so far
class Solution1 {
    
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return -1;
        }
        if (nums.length <= 1) {
            return nums.length;
        }
        int slow = 1;
        
        //S1:
        // [0, s) solution so far
        // [s, f) explored and i don't care / will be update or assign
        // [f, length - 1] unknown to explore
        
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[slow - 1] != nums[fast]) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }
    
}

// Solution 2: [0, s] solution so far
class Solution2 {
    
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return -1;
        }
        if (nums.length <= 1) {
            return nums.length;
        }
        int slow = 0;
        
        //S2:
        // [0, s] solution so far
        // (s, f) explored and i don't care / will be update or assign
        // [f, length - 1] unknown to explore
        
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[slow] != nums[fast]) {
                nums[++slow] = nums[fast];
            }
        }
        return slow + 1;
    }
    
}
}