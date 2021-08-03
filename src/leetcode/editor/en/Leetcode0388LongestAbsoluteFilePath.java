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

// 2020-09-12 18:14:28
// Zeshi Yang
public class Leetcode0388LongestAbsoluteFilePath{
    // Java: longest-absolute-file-path
    public static void main(String[] args) {
        Solution sol = new Leetcode0388LongestAbsoluteFilePath().new Solution();
        // TO TEST
//        String s = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
//        int res = sol.lengthLongestPath(s);
//        System.out.println(res);
        String str = "ab\t\tc";
        System.out.println(str.lastIndexOf("\t"));
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int lengthLongestPath(String input) {
        String[] paths = input.split("\n");
        int[] stack = new int[paths.length + 1];
        int maxLen = 0;
        for (String str : paths) {
            int lev = str.lastIndexOf("\t") + 1;
            System.out.println("str: " + str + " index: " + lev);
            stack[lev + 1] = stack[lev] + str.length() - lev + 1;
            if (str.contains(".")) {
                maxLen = Math.max(maxLen, stack[lev + 1] - 1);
            }
        }
        return maxLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}