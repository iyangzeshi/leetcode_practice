//Given an array nums, write a function to move all 0's to the end of it while m
//aintaining the relative order of the non-zero elements. 
//
// Example: 
//
// 
//Input: [0,1,0,3,12]
//Output: [1,3,12,0,0] 
//
// Note: 
//
// 
// You must do this in-place without making a copy of the array. 
// Minimize the total number of operations. 
// Related Topics Array Two Pointers 
// 👍 3889 👎 127

package leetcode.editor.en;

// 2020-07-26 13:38:50
// Zeshi Yang
public class Leetcode0283MoveZeroes {

	// Java: move-zeroes
	public static void main(String[] args) {
		Solution sol = new Leetcode0283MoveZeroes().new Solution();
		// TO TEST

		System.out.println();
	}

//leetcode submit region begin(Prohibit modification and deletion)
/*
    not 0 in [0,left)
    0 in [left, right)
    to be checked in [right, len)
*/
class Solution {
    
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        int len = nums.length;
        int left = 0;
        for (int right = 0; right < len; right++) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: 动fast指针，最后的位置补0
/*
    not 0 in [0,slow)
    0 in [slow, fast)
    to be checked in [fast, right]
*/
class Solution1 {

    public int[] moveZeroKeepOrder(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        int slow = 0;
        for (int fast = 0; fast < array.length; fast++) {
            if (array[fast] != 0) {
                array[slow] = array[fast];
                slow++;
            }
        }
        while (slow < array.length) {
            array[slow++] = 0;
        }
        return array;
    }
}

// Solution 2:  双指针，0的位置交换值
/*
    not 0 in [0, left)
    0 in [left, right)
    to be checked in [right, len)
*/
class Solution2 {
    
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        int len = nums.length;
        int left = 0;
        for (int right = 0; right < len; right++) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

}