//Numbers can be regarded as product of its factors. For example, 
//
// 
//8 = 2 x 2 x 2;
//  = 2 x 4.
// 
//
// Write a function that takes an integer n and return all possible combinations
// of its factors. 
//
// Note: 
//
// 
// You may assume that n is always positive. 
// Factors should be greater than 1 and less than n. 
// 
//
// Example 1: 
//
// 
//Input: 1
//Output: []
// 
//
// Example 2: 
//
// 
//Input: 37
//Output:[] 
//
// Example 3: 
//
// 
//Input: 12
//Output:
//[
//  [2, 6],
//  [2, 2, 3],
//  [3, 4]
//] 
//
// Example 4: 
//
// 
//Input: 32
//Output:
//[
//  [2, 16],
//  [2, 2, 8],
//  [2, 2, 2, 4],
//  [2, 2, 2, 2, 2],
//  [2, 4, 4],
//  [4, 8]
//]
// 
// Related Topics Backtracking 
// 👍 602 👎 25

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;
// 2020-08-04 22:43:20
// Jesse Yang
public class Leetcode0254FactorCombinations{
    // Java: factor-combinations
    public static void main(String[] args) {
        Solution sol = new Leetcode0254FactorCombinations().new Solution();
        // TO TEST
        int n = 8;
        List<List<Integer>> res = sol.getFactors(n);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 3) {
            return res;
        }
        
        List<Integer> buffer = new ArrayList<>();
        dfs(n, 2, res, buffer);
        return res;
    }
    
    private void dfs(int n, int start, List<List<Integer>> res, List<Integer> buffer) {
        if (n <= 1) {
            if (buffer.size() > 1) {
                res.add(new ArrayList<>(buffer));
            }
            return;
        }
        
        int sqrt = (int) Math.sqrt(n);
        for (int i = start; i <= sqrt; i++) {
            if (n % i == 0) {
                buffer.add(i);
                dfs(n / i, i, res, buffer);
                buffer.remove(buffer.size() - 1);
            }
        }
        
        buffer.add(n);
        dfs(1, n, res, buffer);
        buffer.remove(buffer.size() - 1);
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: DFS
// 2 ms,击败了94.01% 的Java用户, 38.8 MB,击败了72.46% 的Java用户
class Solution1 {
    
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        // base case
        if (n <= 3) {
            return res;
        }
        
        List<Integer> buffer = new ArrayList<>();
        dfs(n, 2, res, buffer);
        return res;
    }
    
    /**
     * @param n      number
     * @param start: important, 否则可能会出现重复
     */
    private void dfs(int n, int start, List<List<Integer>> res, List<Integer> combination) {
        // base case
        if (n <= 1) {
            if (combination.size() > 1) {
                res.add(new ArrayList<>(combination));
            }
            return;
        }
        
        int sqrt = (int) Math.sqrt(n);
        for (int i = start; i <= sqrt; i++) {
            if (n % i == 0) {
                combination.add(i);
                dfs(n / i, i, res, combination);
                combination.remove(combination.size() - 1);
            }
        }
        
        combination.add(n);
        dfs(1, n, res, combination);
        combination.remove(combination.size() - 1);
    }
    
}

}