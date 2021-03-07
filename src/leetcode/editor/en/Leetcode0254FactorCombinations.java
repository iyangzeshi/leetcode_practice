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

import java.util.*;
// 2020-08-04 22:43:20
// Zeshi Yang
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
        List<List<Integer>> result = new ArrayList<>();
        helper(n, 2, result, new ArrayList<>());
        return result;
    }

    private void helper(int n, int index, List<List<Integer>> result, List<Integer> list){
        if(n <= 1){
            if(list.size() > 1){
                result.add(new ArrayList<>(list));
            }
            return;
        }
        for(int i = index; i <= n; i++){
            if(n % i == 0){
                list.add(i);
                helper(n / i, i, result, list);//因为i变了所以不会重复, n也变了
                list.remove(list.size() - 1);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: DFS
class Solution1 {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 3) {
            return result;
        }

        List<Integer> buffer = new ArrayList<>();
        dfs(n, 2, result, buffer);

        return result;
    }

    private void dfs(int n, int start, List<List<Integer>> result, List<Integer> buffer) {
        if (n <= 1) {
            if (buffer.size() > 1) {
                result.add(new ArrayList<>(buffer));
            }
            return;
        }

        for (int i = start; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                buffer.add(i);
                dfs(n / i, i, result, buffer);
                buffer.remove(buffer.size() - 1);
            }
        }

        buffer.add(n);
        dfs(1, n, result, buffer);
        buffer.remove(buffer.size() - 1);
    }
}
// Solution 2: DFS
class Solution2 {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 3) {
            return result;
        }

        List<Integer> buffer = new ArrayList<>();
        dfs(n, 2, result, buffer);

        return result;
    }

    /**
     *
     * @param n number
     * @param start: important, 否则可能会出现重复
     */
    private void dfs(int n, int start, List<List<Integer>> result, List<Integer> buffer) {
        if (n <= 1) {
            if (buffer.size() > 1) {
                result.add(new ArrayList<>(buffer));
            }
            return;
        }

        for (int i = start; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                buffer.add(i);
                dfs(n / i, i, result, buffer);
                buffer.remove(buffer.size() - 1);
            }
        }

        buffer.add(n);
        dfs(1, n, result, buffer);
        buffer.remove(buffer.size() - 1);
    }
}

}