//Given an array with n objects colored red, white or blue, sort them in-place s
//o that objects of the same color are adjacent, with the colors in the order red,
// white and blue. 
//
// Here, we will use the integers 0, 1, and 2 to represent the color red, white,
// and blue respectively. 
//
// Note: You are not suppose to use the library's sort function for this problem
//. 
//
// Example: 
//
// 
//Input: [2,0,2,1,1,0]
//Output: [0,0,1,1,2,2] 
//
// Follow up: 
//
// 
// A rather straight forward solution is a two-pass algorithm using counting sor
//t. 
// First, iterate the array counting number of 0's, 1's, and 2's, then overwrite
// array with total number of 0's, then 1's and followed by 2's. 
// Could you come up with a one-pass algorithm using only constant space? 
// 
// Related Topics Array Two Pointers Sort 
// 👍 3619 👎 230

package leetcode.editor.en;

// 2020-08-04 11:26:36
// Jesse Yang
public class Leetcode0075SortColors{
    // Java: sort-colors
    public static void main(String[] args) {
        Solution sol = new Leetcode0075SortColors().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public void sortColors(int[] nums) {
        
        /*pOne, pTwo, pThree
        0 in [0,pZero)
        1 in [pZero,pOne)
        to be checked in [pOne, pTwo)
        2 in (pTwo, right]
        */
        if (nums == null || nums.length == 0) {
            return;
        }
        int right = nums.length - 1;
        int pZero = 0;
        int pOne = 0;
        int pTwo = right;
        while (pOne <= pTwo) {
            if (nums[pOne] == 0) {
                swap(nums, pZero++, pOne++);
            } else if (nums[pOne] == 1) {
                pOne++;
            } else { // nums[pTwo] ==2
                swap(nums, pOne, pTwo--);
            }
        }
        
    }
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}