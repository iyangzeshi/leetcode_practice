//Given a string s, partition s such that every substring of the partition is a 
//palindrome. 
//
// Return all possible palindrome partitioning of s. 
//
// Example: 
//
// 
//Input: "aab"
//Output:
//[
//  ["aa","b"],
//  ["a","a","b"]
//]
// 
// Related Topics Backtracking 
// 👍 2331 👎 79

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

// 2020-10-19 23:37:52
// Zeshi Yang
public class Leetcode0131PalindromePartitioning{
    // Java: palindrome-partitioning
    public static void main(String[] args) {
        Solution sol = new Leetcode0131PalindromePartitioning().new Solution();
        // TO TEST
        String s = "aab";
        List<List<String>> res = sol.partition(s);
        System.out.println(res);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        // corner case
        if (s == null || s.length() == 0) {
            return res;
        }
        int len = s.length();
        Boolean[][] isPal = findPal(s); // upper triangular matrix
        Integer[] memo = new Integer[len];
        dfs(0, isPal, s, new ArrayList<>(), res);
        return res;
    }
    
    private Boolean[][] findPal(String s) {
        int len = s.length();
        Boolean[][] isPal = new Boolean[len][len]; // whether s[i-j] is Palindrome
        for (int i = len - 1; i >=0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j) {
                    isPal[i][j] = true;
                } else if (i + 1 == j) {
                    isPal[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    isPal[i][j] = (isPal[i + 1][j - 1] && s.charAt(i) == s.charAt(j));
                }
            }
        }
        return isPal;
    }
    
    private void dfs(int index, Boolean[][] isPal, String s, List<String> path,
            List<List<String>> res) {
        int len = s.length();
        // base case
        if (index == len) {
            res.add(new ArrayList<>(path));
        }
        for (int i = index; i < len; i++) {
            if (isPal[index][i]) {
                path.add(s.substring(index, i + 1));
                dfs(i + 1, isPal, s, path, res);
                path.remove(path.size() - 1);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}