//Given a collection of candidate numbers (candidates) and a target number (targ
//et), find all unique combinations in candidates where the candidate numbers sums
// to target. 
//
// Each number in candidates may only be used once in the combination. 
//
// Note: 
//
// 
// All numbers (including target) will be positive integers. 
// The solution set must not contain duplicate combinations. 
// 
//
// Example 1: 
//
// 
//Input: candidates = [10,1,2,7,6,1,5], target = 8,
//A solution set is:
//[
//  [1, 7],
//  [1, 2, 5],
//  [2, 6],
//  [1, 1, 6]
//]
// 
//
// Example 2: 
//
// 
//Input: candidates = [2,5,2,1,2], target = 5,
//A solution set is:
//[
//  [1,2,2],
//  [5]
//]
// 
// Related Topics Array Backtracking 
// 👍 1804 👎 67

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// 2020-07-26 12:18:22
// Zeshi Yang
public class Leetcode0040CombinationSumIi{
    // Java: combination-sum-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0040CombinationSumIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // corner case
        if (candidates == null || candidates.length == 0) {
            return result;
        }

        Arrays.sort(candidates);
        List<Integer> list = new ArrayList<>();
        int index = 0;
        int sum = 0;

        getResult(index, sum, target, candidates, list, result);
        return result;
    }

    private void getResult(int index, int sum, int target, int[] candidates,
            List<Integer> list, List<List<Integer>> result) {
        //base case175
        
        if (sum == target) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (sum > target) {
            return;
        }
        int length = candidates.length;
        for (int i = index; i < length; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            list.add(candidates[i]);
            getResult(i + 1, sum + candidates[i], target, candidates,list, result);
            list.remove(list.size() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}