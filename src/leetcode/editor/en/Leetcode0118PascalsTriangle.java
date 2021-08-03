//Given a non-negative integer numRows, generate the first numRows of Pascal's t
//riangle. 
//
// 
//In Pascal's triangle, each number is the sum of the two numbers directly above
// it. 
//
// Example: 
//
// 
//Input: 5
//Output:
//[
//     [1],
//    [1,1],
//   [1,2,1],
//  [1,3,3,1],
// [1,4,6,4,1]
//]
// 
// Related Topics Array 
// 👍 1504 👎 110

package leetcode.editor.en;

import java.util.*;
// 2020-07-26 12:40:12
// Zeshi Yang
public class Leetcode0118PascalsTriangle{
    // Java: pascals-triangle
    public static void main(String[] args) {
        Solution sol = new Leetcode0118PascalsTriangle().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> generate(int numRows) {
        final List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                }
                else {
                    list.add(result.get(i - 1).get(j - 1) +result.get(i - 1).get(j));
                }
            }
            result.add(list);
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: add 1 to the far right in new row and update the list from right to left
class Solution1 {
    public List<Integer> getRow(int rowIndex) {
        // update result from right to left
        List<Integer> result = new ArrayList<>();

        //corner case
        if (rowIndex < 0) {
            return result;
        }

        for (int i = 0; i <= rowIndex; i++) {
            result.add(1);
            for (int j = i - 1; j >= 1; j--) {
                result.set(j, result.get(j) + result.get(j - 1));
            }
        }
        return result;
    }
}

// Solution 2: add 1 to the far left in  new row and update the list from right to left
class Solution2 {
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
}