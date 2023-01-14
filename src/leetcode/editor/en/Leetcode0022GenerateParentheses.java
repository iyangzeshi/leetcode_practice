//
//Given n pairs of parentheses, write a function to generate all combinations of
// well-formed parentheses.
// 
//
// 
//For example, given n = 3, a solution set is:
// 
// 
//[
//  "((()))",
//  "(()())",
//  "(())()",
//  "()(())",
//  "()()()"
//]
// Related Topics String Backtracking

package leetcode.editor.en;

import java.util.*;

public class Leetcode0022GenerateParentheses {
    // Java: generate-parentheses
    public static void main(String[] args) {
        Solution sol = new Leetcode0022GenerateParentheses().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> generateParenthesis(int n) {
        // corner case
        List<String> res = new LinkedList<>();
        dfs(res, new StringBuilder(), n, 0);
        return res;
    }
    
    private void dfs(List<String> res, StringBuilder path, int n, int delta) {
        int len = path.length();
        // base case
        // success case
        if (len == 2 * n && delta == 0) {
            res.add(path.toString());
            return;
        }
        // failure case
        if (len == 2 * n || delta < 0) {
            return;
        }
        
        // general case
        path.append("(");
        dfs(res, path, n, delta + 1);
        path.setLength(len);
        path.append(")");
        dfs(res, path, n, delta - 1);
        path.setLength(len);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: DFS，第1类搜索树
class Solution1 {
    public List<String> generateParenthesis(int n) {
        // corner case
        List<String> res = new LinkedList<>();
        dfs(res, new StringBuilder(), n, 0);
        return res;
    }
	
	/**
	 *
	 * @param res result
	 * @param path current path
	 * @param n given number n
	 * @param delta number of ( - number of )
	 */
    private void dfs(List<String> res, StringBuilder path, int n, int delta) {
        int len = path.length();
        // base case
        // success case
        if (len == 2 * n && delta == 0) {
            res.add(path.toString());
            return;
        }
        // failure case
        if (len == 2 * n || delta < 0) {
            return;
        }
        
        // general case
        path.append("(");
        dfs(res, path, n, delta + 1);
        path.setLength(len);
        path.append(")");
        dfs(res, path, n, delta - 1);
        path.setLength(len);
    }
}

//Solution 2_1: DFS 第2类搜索树 + 先进去再看要不要return
class Solution2_1 {

    // DFS
    //S1: get into different branches and if false then return
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        //corner case
        if (n <= 0) {
            return result;
        }

        // general case
        StringBuilder sb = new StringBuilder();
        DFS(n, 0, 0, sb, result);

        return result;
    }
    /**
     * @param n:        n pairs of parentheses
     * @param left:     number of left parentheses
     * @param right:    number of left parentheses
     * @param sb:       temporary answer carrier
     * @param result:   result
     */
    private void DFS(int n, int left, int right, StringBuilder sb, List<String> result) {

        //corner case
        if (left > n || right > left) {
            return;
        }

        // base case
        if (left + right == 2 * n) {
            result.add(sb.toString());
        }

        // case 1, try to add left Parentheses
        sb.append('(');
        DFS(n, left + 1, right, sb, result);
        // sb.deleteCharAt(sb.length() - 1);
        int length = sb.length();
        sb.setLength(length - 1);

        //case 2, try to add right Parentheses
        sb.append(')');
        DFS(n, left, right + 1, sb, result);
        // sb.deleteCharAt(sb.length() - 1);
        length = sb.length();
        sb.setLength(length - 1);

    }
}

// Solution 2_2: DFS 第2类搜索树 + 满足条件了再加括号
class Solution2_2 {

    // DFS
    //S2: get into different branches if it is right
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        //corner case
        if (n <= 0) {
            return result;
        }

        // general case
        StringBuilder sb = new StringBuilder();
        DFS(n, 0, 0, sb, result);

        return result;
    }
    /**
     * @param n:        n pairs of parentheses
     * @param left:     number of left parentheses
     * @param right:    number of left parentheses
     * @param sb:       temporary answer carrier
     * @param result:   result
     */
    private void DFS(int n, int left, int right, StringBuilder sb, List<String> result) {

        // base case
        if (left + right == 2 * n) {
            result.add(sb.toString());
        }

        // case 1, try to add left Parentheses
        if (left < n) {
            sb.append('(');
            DFS(n, left + 1, right, sb, result);
            // sb.deleteCharAt(sb.length() - 1);
            int length = sb.length();
            sb.setLength(length - 1);
        }

        //case 2, try to add right Parentheses
        if (right < left) {
            sb.append(')');
            DFS(n, left, right + 1, sb, result);
            // sb.deleteCharAt(sb.length() - 1);
            int length = sb.length();
            sb.setLength(length - 1);
        }
    }
}
}