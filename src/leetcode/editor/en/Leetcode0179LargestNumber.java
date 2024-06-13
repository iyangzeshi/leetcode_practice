//Given a list of non negative integers, arrange them such that they form the la
//rgest number. 
//
// Example 1: 
//
// 
//Input: [10,2]
//Output: "210" 
//
// Example 2: 
//
// 
//Input: [3,30,34,5,9]
//Output: "9534330"
// 
//
// Note: The result may be very large, so you need to return a string instead of
// an integer. 
// Related Topics Sort 
// 👍 1937 👎 226

package leetcode.editor.en;

import java.util.*;
// 2020-07-26 13:48:23
// Jesse Yang
public class Leetcode0179LargestNumber{
    // Java: largest-number
    public static void main(String[] args) {
        Solution sol = new Leetcode0179LargestNumber().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String largestNumber(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return null;
        }

        //general case
        int len = nums.length;
        Integer[] array = new Integer[len];

        for (int i = 0; i < nums.length; i++) {
            array[i] = nums[i];
        }

        Arrays.sort(array, (o1, o2) ->
                (o2 + String.valueOf(o1)).compareTo
                        (o1 + String.valueOf(o2)));
        // edge case
        if(array[0] == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (Integer i: array) {
            sb.append(i);
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}