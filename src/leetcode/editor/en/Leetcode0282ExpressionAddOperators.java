//Given a string that contains only digits 0-9 and a target value, return all po
//ssibilities to add binary operators (not unary) +, -, or * between the digits so
// they evaluate to the target value. 
//
// Example 1: 
//
// 
//Input: num = "123", target = 6
//Output: ["1+2+3", "1*2*3"] 
// 
//
// Example 2: 
//
// 
//Input: num = "232", target = 8
//Output: ["2*3+2", "2+3*2"] 
//
// Example 3: 
//
// 
//Input: num = "105", target = 5
//Output: ["1*0+5","10-5"] 
//
// Example 4: 
//
// 
//Input: num = "00", target = 0
//Output: ["0+0", "0-0", "0*0"]
// 
//
// Example 5: 
//
// 
//Input: num = "3456237490", target = 9191
//Output: []
// 
//
// 
// Constraints: 
//
// 
// 0 <= num.length <= 10 
// num only contain digits. 
// 
// Related Topics Divide and Conquer

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

public class Leetcode0282ExpressionAddOperators {
	
	// Java: expression-add-operators
	public static void main(String[] args) {
		
		Solution sol = new Leetcode0282ExpressionAddOperators().new Solution();
		Solution1 sol1 = new Leetcode0282ExpressionAddOperators().new Solution1();
		
		// TO TEST
		String num = "2112345678";
		int target = -2112345678;
		
		List<String> res = sol.addOperators(num, target);
		List<String> res1 = sol1.addOperators(num, target);
		System.out.println(res.toString());
		System.out.println(res1.toString());
		// System.out.println(Arrays.toString(res.toArray()));
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	
	public List<String> addOperators(String num, int target) {
		
		List<String> res = new ArrayList<>();
		// corner case
		if (num == null || num.length() == 0) {
			return res;
		}
		// general case
		dfs(res, new StringBuilder(), num, 0, target, 0, 0);
		return res;
	}
	
	private void dfs(List<String> res, StringBuilder path, String s, int idx, long target,
			long sum, long lastValue) {
		// base case
		// success case
		if (idx == s.length() && sum == target) {
			res.add(path.toString());
			return;
		}
		
		// failure case
		if (idx == s.length()) {
			return;
		}
		long val = 0;
		int len = path.length();
		for (int i = idx; i < s.length(); i++) {
			val = val * 10 + s.charAt(i) - '0';
			if (len != 0) {
				// for "+"
				path.append("+").append(val);
				dfs(res, path, s, i + 1, target, sum + val, val);
				path.setLength(len);
				// for "-"
				path.append("-").append(val);
				dfs(res, path, s, i + 1, target, sum - val, -val);
				path.setLength(len);
				// for "*"
				path.append("*").append(val);
				dfs(res, path, s, i + 1, target, sum - lastValue + lastValue * val,
						lastValue * val);
				path.setLength(len);
			} else {
				path.append(val);
				dfs(res, path, s, i + 1, target, sum + val, val);
				path.setLength(len);
			}
			
			if (val == 0) {
				break;
			}
		}
	}
}

//leetcode submit region end(Prohibit modification and deletion)
// 下面两种思路是一样的，不过是实现的时候有点不一样，
// dfs里面第一个运算不一样，代码写的不一样，Solution1写的更好理解一点
class Solution1 {
	
	public List<String> addOperators(String num, int target) {
		
		List<String> res = new ArrayList<>();
		// corner case
		if (num == null || num.length() == 0) {
			return res;
		}
		// general case
		dfs(res, new StringBuilder(), num, 0, target, 0, 0);
		return res;
	}
	
	private void dfs(List<String> res, StringBuilder path, String s, int idx, long target,
			long sum, long lastValue) {
		// base case
		// success case
		if (idx == s.length() && sum == target) {
			res.add(path.toString());
			return;
		}
		
		// failure case
		if (idx == s.length()) {
			return;
		}
		long val = 0;
		int len = path.length();
		for (int i = idx; i < s.length(); i++) {
			val = val * 10 + s.charAt(i) - '0';
			if (len != 0) {
				// for "+"
				path.append("+").append(val);
				dfs(res, path, s, i + 1, target, sum + val, val);
				path.setLength(len);
				// for "-"
				path.append("-").append(val);
				dfs(res, path, s, i + 1, target, sum - val, -val);
				path.setLength(len);
				// for "*"
				path.append("*").append(val);
				dfs(res, path, s, i + 1, target, sum - lastValue + lastValue * val,
						lastValue * val);
				path.setLength(len);
			} else {
				path.append(val);
				dfs(res, path, s, i + 1, target, sum + val, val);
				path.setLength(len);
			}
			
			if (val == 0) {
				break;
			}
		}
	}
}

class Solution2 {
	
	public List<String> addOperators(String num, int target) {
		
		List<String> res = new ArrayList<>();
		// corner case
		if (num == null || num.length() == 0) {
			return res;
		}
		// general case
		dfs(res, new StringBuilder(), num, 0, target, 0, 0);
		return res;
	}
	
	private void dfs(List<String> res, StringBuilder path, String s, int idx, long target,
			long sum, long lastValue) {
		// base case - success case
		if (idx == s.length() && sum == target) {
			res.add(path.substring(1));
			return;
		}
		
		// base case - failure case
		if (idx == s.length()) {
			return;
		}
		long val = 0;
		int len = path.length();
		for (int i = idx; i < s.length(); i++) {
			val = val * 10 + s.charAt(i) - '0';
			
			// for "+"
			path.append("+").append(val);
			dfs(res, path, s, i + 1, target, sum + val, val);
			path.setLength(len);
			if (len == 0 && val == 0) {
				break;
			}
			if (len == 0) {
				continue;
			}
			// for "-"
			path.append("-").append(val);
			dfs(res, path, s, i + 1, target, sum - val, -val);
			path.setLength(len);
			// for "*"
			path.append("*").append(val);
			dfs(res, path, s, i + 1, target, sum - lastValue + lastValue * val,
					lastValue * val);
			path.setLength(len);
			if (val == 0) {
				break;
			}
		}
	}
}

}