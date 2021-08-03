//Given a non-negative index k where k ≤ 33, return the kth index row of the Pas
//cal's triangle. 
//
// Note that the row index starts from 0. 
//
// 
//In Pascal's triangle, each number is the sum of the two numbers directly above
// it. 
//
// Example: 
//
// 
//Input: 3
//Output: [1,3,3,1]
// 
//
// Follow up: 
//
// Could you optimize your algorithm to use only O(k) extra space? 
// Related Topics Array 
// 👍 851 👎 198

package leetcode.editor.en;

import java.util.*;
// 2020-08-04 11:33:52
// Zeshi Yang
public class Leetcode0119PascalsTriangleIi{
    // Java: pascals-triangle-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0119PascalsTriangleIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> getRow(int rowIndex) {
        // update result from left to right
        List<Integer> result = new ArrayList<>();

        //corner case
        if (rowIndex < 0) {
            return result;
        }

        for (int i = 0; i <= rowIndex; i++) {
            result.add(0, 1);
            for (int j = 1; j < i; j++) {
                result.set(j, result.get(j) + result.get(j + 1));
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}