//Given two strings s and t, return true if they are equal when both are typed i
//nto empty text editors. '#' means a backspace character. 
//
// Note that after backspacing an empty text, the text will continue empty. 
//
// 
// Example 1: 
//
// 
//Input: s = "ab#c", t = "ad#c"
//Output: true
//Explanation: Both s and t become "ac".
// 
//
// Example 2: 
//
// 
//Input: s = "ab##", t = "c#d#"
//Output: true
//Explanation: Both s and t become "".
// 
//
// Example 3: 
//
// 
//Input: s = "a##c", t = "#a#c"
//Output: true
//Explanation: Both s and t become "c".
// 
//
// Example 4: 
//
// 
//Input: s = "a#c", t = "b"
//Output: false
//Explanation: s becomes "c" while t becomes "b".
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length, t.length <= 200 
// s and t only contain lowercase letters and '#' characters. 
// 
//
// 
// Follow up: Can you solve it in O(n) time and O(1) space? 
// Related Topics Two Pointers Stack 
// 👍 2472 👎 116

package leetcode.editor.en;

// 2021-05-12 16:24:29
// Jesse Yang
public class Leetcode0844BackspaceStringCompare{
    // Java: backspace-string-compare
    public static void main(String[] args) {
        Solution sol = new Leetcode0844BackspaceStringCompare().new Solution();
        // TO TEST
        String s = "bxj##tw";
        String t = "bxj###tw";
        boolean res = sol.backspaceCompare(s, t);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        int skipS = 0;
        int skipT = 0;
        
        while (i >= 0 || j >= 0) { // While there may be chars in build(s) or build (t)
            while (i >= 0) { // Find position of next possible char in build(s)
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else { // skipS == 0 && s.charAt(i) != #
                    break;
                }
            }
            while (j >= 0) { // Find position of next possible char in build(t)
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else { // skipT == 0 && t.charAt(j) != #
                    break;
                }
            }
            
            // If expecting to compare char vs nothing
            if (i == -1 && j == -1) {
                return true;
            } else if (i == -1 || j == -1) {
                return false;
            }
            
            // If two actual characters are different
            if (s.charAt(i) != t.charAt(j)) {
                return false;
            }
            i--;
            j--;
        }
        return true;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
/** 面试的时候，用Solution 2_1 */
// Solution 1: using 2 stacks(actually using the StringBuilder)
// T(n) = O(n), S(n) = O(n)
// 0 ms,击败了100.00% 的Java用户, 36.9 MB,击败了85.33% 的Java用户
/*
遇到正常的char，就放到stack里，如果遇到#，就把stack里面pop一个char出来
 */
class Solution1 {
    
    public boolean backspaceCompare(String s, String t) {
        
        String resS = build(s);
        String resT = build(t);
        return resS.equals(resT);
    }
    
    private String build(String str) {
        StringBuilder sb = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (ch != '#') {
                sb.append(ch);
            } else {
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
        return sb.toString();
    }
    
}

// Solution 2: 从后往前比较

// Solution 2_1: 从后往前比较
// T(n) = O(n), S(n) = O(1)
// 0 ms,击败了100.00% 的Java用户, 37.3 MB,击败了48.60% 的Java用户
/*
每次走的时候，遇到#就把这个一直往后跳，找到这个单词前面第一个能比较的char为止
对s和t都是这样
如果发现两个都走到的头了，就return true
如果发现有一个走到头了，另外一个没走到头，return false
两个都没有走到头，比较两个char是否相等，如果不相等的话，return false；否则继续走
 */
class Solution2_1 {
    
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        int skipS = 0;
        int skipT = 0;
        
        while (i >= 0 || j >= 0) { // While there may be chars in build(s) or build (t)
            while (i >= 0) { // Find position of next possible char in build(s)
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else { // skipS == 0 && s.charAt(i) != #
                    break;
                }
            }
            while (j >= 0) { // Find position of next possible char in build(t)
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else { // skipT == 0 && t.charAt(j) != #
                    break;
                }
            }
            
            // If expecting to compare char vs nothing
            if (i == -1 && j == -1) {
                return true;
            } else if (i == -1 || j == -1) {
                return false;
            }
            
            // If two actual characters are different
            if (s.charAt(i) != t.charAt(j)) {
                return false;
            }
            i--;
            j--;
        }
        return true;
    }
    
}

// Solution 2_2: 从后往前比较
// T(n) = O(n), S(n) = O(1)
// 0 ms,击败了100.00% 的Java用户, 37.3 MB,击败了48.60% 的Java用户
/*
用skip表示需要跳的步数
每次走的时候，
case 1: 要么s走1步: char是#,或者skip没用完
case 2: 要么t走1步，char是#,或者skip没用完
case 3: 要么s和t各走1步，两个char都不是#,skip都是0，比较两个char是否相等
    如果相等，继续
    如果不相等：return false
case 4: s和t都走不了，
    s和t都走到头了，return true
    有一个走到头了，另外一个没有走到头，return false
 */
class Solution2_2 {
    
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        int skipS = 0;
        int skipT = 0;
        while (i >= 0 || j >= 0) {
            if (i >= 0 && (s.charAt(i) == '#' || skipS > 0)) {
                if (s.charAt(i) == '#') {
                    skipS++;
                } else { // s.charAt(i) != '#' && skipS > 0
                    skipS--;
                }
                i--;
            } else if (j >= 0 && (t.charAt(j) == '#' || skipT > 0)) {
                if (t.charAt(j) == '#') {
                    skipT++;
                } else { // t.charAt(j) != '#' && skipT > 0
                    skipT--;
                }
                j--;
            } else if (i >= 0 && j >= 0 && s.charAt(i) == t.charAt(j)){
                i--;
                j--;
            } else {
                return false;
            }
        }
        return i == -1 && j == -1;
    }
    
}

}