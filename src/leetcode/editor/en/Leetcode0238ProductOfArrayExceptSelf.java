//Given an array nums of n integers where n > 1, return an array output such tha
//t output[i] is equal to the product of all the elements of nums except nums[i]. 
//
//
// Example: 
//
// 
//Input:  [1,2,3,4]
//Output: [24,12,8,6]
// 
//
// Constraint: It's guaranteed that the product of the elements of any prefix or
// suffix of the array (including the whole array) fits in a 32 bit integer. 
//
// Note: Please solve it without division and in O(n). 
//
// Follow up: 
//Could you solve it with constant space complexity? (The output array does not 
//count as extra space for the purpose of space complexity analysis.) 
// Related Topics Array 
// 👍 5492 👎 449

package leetcode.editor.en;

import java.util.Arrays;

// 2020-09-10 16:38:13
// Jesse Yang
public class Leetcode0238ProductOfArrayExceptSelf{
    // Java: product-of-array-except-self
    public static void main(String[] args) {
        Solution sol = new Leetcode0238ProductOfArrayExceptSelf().new Solution();
        // TO TEST
        int[] nums = {1, 2, 3, 4};
        int[] res =sol.productExceptSelf(nums);
        System.out.println(Arrays.toString(res));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] productExceptSelf(int[] nums) {
        long prod = 1;
        int numOfZeros = 0;
        for (int num : nums) {
            if (num != 0) {
                prod *= num;
            } else {
                numOfZeros++;
                if (numOfZeros > 1) {
                    break;
                }
            }
        }
        int len = nums.length;
        int[] res = new int[len];
        if (numOfZeros == 2) {
            Arrays.fill(res, 0);
        } else if (numOfZeros == 1) {
            for (int i = 0; i < len; i++) {
                if (nums[i] == 0) {
                    res[i] = (int) prod;
                } else {
                    res[i] = 0;
                }
            }
        } else {
            for (int i = 0; i < len; i++) {
                res[i] = (int) prod / nums[i];
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}