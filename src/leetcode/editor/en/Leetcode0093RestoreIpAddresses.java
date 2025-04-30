//Given a string containing only digits, restore it by returning all possible va
//lid IP address combinations. 
//
// A valid IP address consists of exactly four integers (each integer is between
// 0 and 255) separated by single points. 
//
// Example: 
//
// 
//Input: "25525511135"
//Output: ["255.255.11.135", "255.255.111.35"]
// 
// Related Topics String Backtracking

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode0093RestoreIpAddresses {
	
	// Java: restore-ip-addresses
	public static void main(String[] args) {
		
		Solution sol = new Leetcode0093RestoreIpAddresses().new Solution();
		// TO TEST
		
		String s = "010010";
		List<String> res = sol.restoreIpAddresses(s);
		System.out.println(Arrays.toString(res.toArray()));
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public List<String> restoreIpAddresses(String s) {
        
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        dfs(res, new StringBuilder(), s, 0, 0);
        return res;
    }
    
    private void dfs(List<String> res, StringBuilder path, String s, int idx, int num) {
        // base case
        // success case
        if (idx == s.length() && num == 4) {
            path.setLength(path.length() - 1);
            res.add(path.toString());
            return;
        }
        
        // failure case
        if (idx >= s.length() || num >= 4) {
            return;
        }
        
        //general case
        int len = path.length();
        for (int l = 1; l <= 3; l++) {
            if (idx + l > s.length()) {
                break;
            }
            String str = s.substring(idx, idx + l); // [idx, idx + l - 1]
            int val = Integer.parseInt(str);
            
            if (val >= 0 && val <= 255) {
                path.append(val).append(".");
                dfs(res, path, s, idx + l, num + 1);
                path.setLength(len);
            }
            // 如果IP的每一个单元都不能是0开头的非0数字，那么这里遇到0的时候，就不应该继续搜索下去,"012"是invalid
            if (val == 0) { //这个if不能放在if (val >= 0 && val <= 255) {} 的上面，否则遇到0会直接跳过去。
                break;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}