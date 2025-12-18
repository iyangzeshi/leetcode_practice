/**
 * Given an integer array nums, move all the even integers at the beginning of the array followed by
 * all the odd integers.
 * <p>
 * Return any array that satisfies this condition.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [3,1,2,4] Output: [2,4,3,1] Explanation: The outputs [4,2,3,1], [2,4,1,3], and
 * [4,2,1,3] would also be accepted.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [0] Output: [0]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 1 <= nums.length <= 5000 0 <= nums[i] <= 5000
 * <p>
 * <p>
 * Related Topics Array Two Pointers Sorting 👍 5628 👎 157
 */
package leetcode.editor.en;

// 2025-12-11 01:15:36
// Jesse Yang
public class Leetcode0905SortArrayByParity {
    
    // Java: sort-array-by-parity
    public static void main(String[] args) {
        Solution sol = new Leetcode0905SortArrayByParity().new Solution();
        // TO TEST
        
        System.out.println();
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        
        public int[] sortArrayByParity(int[] nums) {
            int i = 0; // index of odd number
            int j = nums.length - 1; // index of even number
            while (i < j) {
                if (nums[i] % 2 > nums[j] % 2) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
                
                if (nums[i] % 2 == 0) {
                    i++;
                }
                if (nums[j] % 2 == 1) {
                    j--;
                }
            }
            
            return nums;
        }
        
    }
//leetcode submit region end(Prohibit modification and deletion)
}
