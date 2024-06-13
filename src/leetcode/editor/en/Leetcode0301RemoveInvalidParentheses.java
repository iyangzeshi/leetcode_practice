//Remove the minimum number of invalid parentheses in order to make the input st
//ring valid. Return all possible results. 
//
// Note: The input string may contain letters other than the parentheses ( and )
//. 
//
// Example 1: 
//
// 
//Input: "()())()"
//Output: ["()()()", "(())()"]
// 
//
// Example 2: 
//
// 
//Input: "(a)())()"
//Output: ["(a)()()", "(a())()"]
// 
//
// Example 3: 
//
// 
//Input: ")("
//Output: [""]
// Related Topics Depth-first Search Breadth-first Search
/*
  @ClassName: Leetcode301RemoveInvalidParentheses
 * @Description:
 * @Author: Jesse Yang
 * @Date: 2020/06/24 周二 23:05
 */
package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leetcode0301RemoveInvalidParentheses {
	
	// Java: remove-invalid-parentheses
	public static void main(String[] args) {
		
		Solution sol = new Leetcode0301RemoveInvalidParentheses().new Solution();
		// TO TEST
		String s = "()())()";
		List<String> res = sol.removeInvalidParentheses(s);
		System.out.println(Arrays.toString(res.toArray()));
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 2: DFS + 第2类搜索树 + 跳层, deduplicate with jump DFS levels
class Solution {
    
    public List<String> removeInvalidParentheses(String s) {
        // corner case
        char[] chars = s.toCharArray();
        int[] rm = calMRP(chars);
        List<String> res = new ArrayList<>();
        dfs(res, new StringBuilder(), chars, 0, rm[0], rm[1], 0);
        return res;
    }
    
    /**
     * @param res: result
     * @param path: temporary path
     * @param chars: String
     * @param idx: index
     * @param rml: the number of removal of left parentheses
     * @param rmr: the number of removal of right parentheses
     * @param delta: the difference between left parentheses - right parentheses
     */
    private void dfs(List<String> res, StringBuilder path, char[] chars, int idx, int rml,
            int rmr, int delta) {
        // base case
        // success case
        if (rml == 0 && rmr == 0 && delta == 0 && idx == chars.length) {
            res.add(path.toString());
            return;
        }
        
        // failure case
        if (rml < 0 || rmr < 0 || delta < 0 || idx == chars.length) {
            return;
        }
        
        int len = path.length();
        char ch = chars[idx];
        if (ch == '(') {
            // skip '('
            dfs(res, path, chars, idx + 1, rml - 1, rmr, delta);
            
            //add '('
            int skip = 1;
            while (idx + skip < chars.length && chars[idx] == chars[idx + skip]) {
                skip++;
            }
            path.append(chars, idx, skip); // append(char[] str, int offset, int len)
            dfs(res, path, chars, idx + skip, rml, rmr, delta + skip);
            path.setLength(len);
            
        } else if (ch == ')') {
            // skip ')'
            dfs(res, path, chars, idx + 1, rml, rmr - 1, delta);
            
            //add ')'
            int skip = 1;
            while (idx + skip < chars.length && chars[idx] == chars[idx + skip]) {
                skip++;
            }
            path.append(chars, idx, skip);
            dfs(res, path, chars, idx + skip, rml, rmr, delta - skip);
            path.setLength(len);
        } else {
            path.append(ch);
            dfs(res, path, chars, idx + 1, rml, rmr, delta);
            path.setLength(len); // backtracking一定要做，因为它下面延展出来的的分支需要走回去
        }
    }
    
    private int[] calMRP(char[] chars) {
        
        int rml = 0;
        int rmr = 0;
        for (char ch : chars) {
            if (ch == '(') {
                rml++;
            } else if (ch == ')') {
                if (rml > 0) {
                    rml--;
                } else {
                    rmr++;
                }
            }
        }
        return new int[]{rml, rmr};
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
/*
第2中写法更好，因为第1种写法是用HashSet，很耗费资源，
第2种写法虽然复杂，但是直接在DFS里面跳层数，节约资源
 */
 
// Solution 1: DFS + HashSet, deduplicate with HashSet
class Solution1 {

    public List<String> removeInvalidParentheses(String s) {
        // corner case
        char[] chars = s.toCharArray();
        int[] rm = calMRP(chars);
        Set<String> res = new HashSet<>();
        dfs(res, new StringBuilder(), chars, 0, rm[0], rm[1], 0);
        return new ArrayList<>(res);
    }
    
    /**
     * @param res: result
     * @param path: temporary path
     * @param chars: String
     * @param idx: index
     * @param rml: the number of removal of left parentheses
     * @param rmr: the number of removal of right parentheses
     * @param delta: the difference between left parentheses - right parentheses, chosen left
     *             parentheses - right parentheses
     */
    private void dfs(Set<String> res, StringBuilder path, char[] chars, int idx, int rml,
            int rmr, int delta) {
        // base case - success case
        if (rml == 0 && rmr == 0 && delta == 0 && idx == chars.length) {
            res.add(path.toString());
            return;
        }
        
        // base case - failure case
        if (rml < 0 || rmr < 0 || delta < 0 || idx == chars.length) {
            return;
        }
        
        int len = path.length();
        char ch = chars[idx];
        if (ch == '(') {
            // skip '('
            dfs(res, path, chars, idx + 1, rml - 1, rmr, delta);
            
            //add '('
            path.append(ch);
            dfs(res, path, chars, idx + 1, rml, rmr, delta + 1);
            path.setLength(len);
            
        } else if (ch == ')') {
            // skip ')'
            dfs(res, path, chars, idx + 1, rml, rmr - 1, delta);
            
            //add ')'
            path.append(ch);
            dfs(res, path, chars, idx + 1, rml, rmr, delta - 1);
            path.setLength(len);
        } else {
            path.append(ch);
            dfs(res, path, chars, idx + 1, rml, rmr, delta);
            path.setLength(len); // backtracking一定要做，因为它下面延展出来的的分支需要走回去
        }
    }
    
    /**
     * calculate minimum removal of parentheses
     * @param chars given char array
     * @return {rml, rmt}
     * rml: the number of removal of left parentheses
     * rmr: the number of removal of right parentheses
     */
    private int[] calMRP(char[] chars) {
        int rml = 0;
        int rmr = 0;
        for (char ch : chars) {
            if (ch == '(') {
                rml++;
            } else if (ch == ')') {
                if (rml > 0) {
                    rml--;
                } else {
                    rmr++;
                }
            }
        }
        return new int[]{rml, rmr};
    }
    
}

// Solution 2: DFS + 第2类搜索树 + 跳层, deduplicate with jump DFS levels
class Solution2 {
	
    public List<String> removeInvalidParentheses(String s) {
        // corner case
        char[] chars = s.toCharArray();
        int[] rm = calRMP(chars); // Remove Parentheses
        List<String> res = new ArrayList<>();
        dfs(res, new StringBuilder(), chars, 0, rm[0], rm[1], 0);
        return res;
    }
	
	// calculate removal parentheses
	private int[] calRMP(char[] chars) {
		
		int rml = 0;
		int rmr = 0;
		for (char ch : chars) {
			if (ch == '(') {
				rml++;
			} else if (ch == ')') {
				if (rml > 0) {
					rml--;
				} else {
					rmr++;
				}
			}
		}
		return new int[]{rml, rmr};
	}
    
    /**
     *
     * @param res: result
     * @param path: temporary path
     * @param chars: String
     * @param idx: index
     * @param rml: the number of removal of left parentheses
     * @param rmr: the number of removal of right parentheses
     * @param delta: the difference between left parentheses - right parentheses
     */
    private void dfs(List<String> res, StringBuilder path, char[] chars, int idx, int rml,
            int rmr, int delta) {
        // base case
        // success case
        if (rml == 0 && rmr == 0 && delta == 0 && idx == chars.length) {
            res.add(path.toString());
            return;
        }
        
        // failure case
        if (rml < 0 || rmr < 0 || delta < 0 || idx == chars.length) {
            return;
        }
        
        int len = path.length();
        char ch = chars[idx];
        if (ch == '(') {
            // skip '('
            dfs(res, path, chars, idx + 1, rml - 1, rmr, delta);
            
            // add '('
            int skip = 1;
            while (idx + skip < chars.length && chars[idx] == chars[idx + skip]) {
                skip++;
            }
            path.append(chars, idx, skip); // append(char[] str, int offset, int len)
            dfs(res, path, chars, idx + skip, rml, rmr, delta + skip);
            path.setLength(len);
            
        } else if (ch == ')') {
            // skip ')'
            dfs(res, path, chars, idx + 1, rml, rmr - 1, delta);
            
            //add ')'
            int skip = 1;
            while (idx + skip < chars.length && chars[idx] == chars[idx + skip]) {
                skip++;
            }
            path.append(chars, idx, skip);
            dfs(res, path, chars, idx + skip, rml, rmr, delta - skip);
            path.setLength(len);
        } else {
            path.append(ch);
            dfs(res, path, chars, idx + 1, rml, rmr, delta);
            path.setLength(len); // backtracking一定要做，因为它下面延展出来的的分支需要走回去
        }
    }
    
}
    
}