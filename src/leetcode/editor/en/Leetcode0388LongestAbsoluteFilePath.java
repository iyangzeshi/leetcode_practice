//Suppose we have the file system represented in the following picture: 
//
// 
//
// We will represent the file system as a string where "
//\t" mean a subdirectory of the main directory, "
//\t\t" means a subdirectory of the subdirectory of the main directory and so on
//. Each folder will be represented as a string of letters and/or digits. Each fil
//e will be in the form "s1.s2" where s1 and s2 are strings of letters and/or digi
//ts. 
//
// For example, the file system above is represented as "dir
//\tsubdir1
//\t\tfile1.ext
//\t\tsubsubdir1
//\tsubdir2
//\t\tsubsubdir2
//\t\t\tfile2.ext". 
//
// Given a string input representing the file system in the explained format, re
//turn the length of the longest absolute path to a file in the abstracted file sy
//stem. If there is no file in the system, return 0. 
//
// 
// Example 1: 
//
// 
//Input: input = "dir
//\tsubdir1
//\tsubdir2
//\t\tfile.ext"
//Output: 20
//Explanation: We have only one file and its path is "dir/subdir2/file.ext" of l
//ength 20.
//The path "dir/subdir1" doesn't contain any files.
// 
//
// Example 2: 
//
// 
//Input: input = "dir
//\tsubdir1
//\t\tfile1.ext
//\t\tsubsubdir1
//\tsubdir2
//\t\tsubsubdir2
//\t\t\tfile2.ext"
//Output: 32
//Explanation: We have two files:
//"dir/subdir1/file1.ext" of length 21
//"dir/subdir2/subsubdir2/file2.ext" of length 32.
//We return 32 since it is the longest path.
// 
//
// Example 3: 
//
// 
//Input: input = "a"
//Output: 0
//Explanation: We don't have any files.
// 
//
// 
// Constraints: 
//
// 
// 1 <= input.length <= 104 
// input may contain lower-case or upper-case English letters, a new line charac
//ter '
//', a tab character '\t', a dot '.', a space ' ' or digits. 
// 
// 👍 624 👎 1515

package leetcode.editor.en;

import java.util.Stack;

// 2020-09-12 18:14:28
// Zeshi Yang
public class Leetcode0388LongestAbsoluteFilePath{
    // Java: longest-absolute-file-path
    public static void main(String[] args) {
        Solution sol = new Leetcode0388LongestAbsoluteFilePath().new Solution();
        // TO TEST
       String s = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
       int res = sol.lengthLongestPath(s);
       System.out.println(res);
        // String str = "ab\t\tc";
        // System.out.println(str.lastIndexOf("\t"));
    }
//leetcode submit region begin(Prohibit modification and deletion)
// T(n) = O(n), S(n) = O(n)，用stack 模拟路径的计算和变化
class Solution {

    public int lengthLongestPath(String input) {
	    // corner case
	    if (input == null || input.length() == 0) {
		    return 0;
	    }
	
	    // general case
	    String[] paths = input.split("\n");
	
	    Stack<String> stack = new Stack<>(); // to store every level dir/file name
	    int maxLen = 0;
	    int len = 0;
	    for (String str : paths) {
		    if (stack.isEmpty()) {
			    stack.push(str);
			    len += str.length();
		    } else {
			    int depth = str.lastIndexOf("\t") + 1;
			    while (depth != stack.size()) { // depth >= 1, so stack is never empty
				    String prev = stack.pop();
				    len -= (prev.length() + 1);
			    }
			    String name = str.substring(depth, str.length());
			    stack.push(name);
			    len += name.length() + 1;
		    }
		
		    if (stack.peek().contains(".")) {
			    maxLen = Math.max(maxLen, len);
		    }
	    }
	    return maxLen;
    }
	
}
//leetcode submit region end(Prohibit modification and deletion)
// T(n) = O(n), S(n) = O(n)，用stack 模拟路径的计算和变化
class Solution1 {
	public int lengthLongestPath(String input) {
		// corner case
		if (input == null || input.length() == 0) {
			return 0;
		}
		
		// general case
		String[] paths = input.split("\n");
		
		Stack<String> stack = new Stack<>(); // to store every level dir/file name
		int maxLen = 0;
		int len = 0;
		for (String str : paths) {
			if (stack.isEmpty()) {
				stack.push(str);
				len += str.length();
			} else {
				int depth = str.lastIndexOf("\t") + 1;
				while (depth != stack.size()) { // depth >= 1, so stack is never empty
					String prev = stack.pop();
					len -= (prev.length() + 1);
				}
				String name = str.substring(depth, str.length());
				stack.push(name);
				len += name.length() + 1;
			}
			
			if (stack.peek().contains(".")) {
				maxLen = Math.max(maxLen, len);
			}
		}
		return maxLen;
	}
}

// T(n) = O(n), S(n) = O(n)，solution 1的讲话，直接在
class Solution2 {
	public int lengthLongestPath(String input) {
		String[] paths = input.split("\n");
		
		// stack[i]: file path length from root to this file/dir
		int[] stack = new int[paths.length + 1];
		int maxLen = 0;
		for (String str : paths) {
			int lev = str.lastIndexOf("\t") + 1;
			int nameLength = str.length() - lev + 1; // the length contains the "\t"
			stack[lev + 1] = stack[lev] + nameLength;
			if (str.contains(".")) { // only file path contains "."
				maxLen = Math.max(maxLen, stack[lev + 1] - 1);
			}
		}
		return maxLen;
	}
}

}