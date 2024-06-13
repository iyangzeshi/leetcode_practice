//Given an array of numbers nums, in which exactly two elements appear only once
// and all the other elements appear exactly twice. Find the two elements that app
//ear only once. 
//
// Example: 
//
// 
//Input:  [1,2,1,3,2,5]
//Output: [3,5] 
//
// Note: 
//
// 
// The order of the result is not important. So in the above example, [5, 3] is 
//also correct. 
// Your algorithm should run in linear runtime complexity. Could you implement i
//t using only constant space complexity? 
// Related Topics Bit Manipulation 
// 👍 1727 👎 110

package leetcode.editor.en;

// 2020-08-01 16:08:27
// Jesse Yang
public class Leetcode0260SingleNumberIii{
    // Java: single-number-iii
    public static void main(String[] args) {
        Solution sol = new Leetcode0260SingleNumberIii().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] singleNumber(int[] nums) {
        // corner case

        // general case
        int xor = 0;
        for (int num: nums) {
            xor ^= num;
        }
        int mask = 1;
        while ((xor & mask) == 0) {
            mask <<= 1;
        }
        int group1 = 0;
        int group2 = 0;
        for (int num: nums) {
            if ((num & mask) == 0) { // bit value is 0 in that bir position
                group1 ^= num;
//            } else {
//                group2 ^= num;
            }
        }
        // group2 = group1 & group2 & group1 = xor ^ group1'
        return new int[] {group1, xor ^ group1};
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}