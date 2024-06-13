//
// Find all possible combinations of k numbers that add up to a number n, given 
//that only numbers from 1 to 9 can be used and each combination should be a uniqu
//e set of numbers. 
//
// Note: 
//
// 
// All numbers will be positive integers. 
// The solution set must not contain duplicate combinations. 
// 
//
// Example 1: 
//
// 
//Input: k = 3, n = 7
//Output: [[1,2,4]]
// 
//
// Example 2: 
//
// 
//Input: k = 3, n = 9
//Output: [[1,2,6], [1,3,5], [2,3,4]]
// 
// Related Topics Array Backtracking 
// 👍 1062 👎 51

package leetcode.editor.en;

import java.util.*;
// 2020-07-26 12:18:43
// Jesse Yang
public class Leetcode0216CombinationSumIii{
    // Java: combination-sum-iii
    public static void main(String[] args) {
        Solution sol = new Leetcode0216CombinationSumIii().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();

        if (n <= 0 || k > n) {
            return result;
        }

        List<Integer> list = new ArrayList<>();
        getResult(1, 0, n, k, list, result);
        return result;
    }

    private void getResult(int index, int sum, int n, int k, List<Integer> list, List<List<Integer>> result) {
        //corner case
        if (sum > n || list.size() > k) {
            return;
        }
        //edge case
        if (list.size() == k && sum == n) {
            result.add(new ArrayList<>(list));
        }
        for (int i = index; i <= 9; i++) {
            list.add(i);
            getResult(i + 1, sum + i, n, k, list, result);
            list.remove(list.size() - 1);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}