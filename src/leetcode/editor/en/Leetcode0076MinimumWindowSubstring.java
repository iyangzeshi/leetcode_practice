//Given two strings s and t, return the minimum window in s which will contain a
//ll the characters in t. If there is no such window in s that covers all characte
//rs in t, return the empty string "". 
//
// Note that If there is such a window, it is guaranteed that there will always 
//be only one unique minimum window in s. 
//
// 
// Example 1: 
// Input: s = "ADOBECODEBANC", t = "ABC"
//Output: "BANC"
// Example 2: 
// Input: s = "a", t = "a"
//Output: "a"
// 
// 
// Constraints: 
//
// 
// 1 <= s.length, t.length <= 105 
// s and t consist of English letters. 
// 
//
// 
//Follow up: Could you find an algorithm that runs in O(n) time? Related Topics 
//Hash Table Two Pointers String Sliding Window 
// 👍 5769 👎 395

package leetcode.editor.en;

// 2021-01-07 15:56:32
// Jesse Yang
public class Leetcode0076MinimumWindowSubstring{
    // Java: minimum-window-substring
    public static void main(String[] args) {
        Solution sol = new Leetcode0076MinimumWindowSubstring().new Solution();
        // TO TEST
        String s = "a";
        String t = "aa";
        String res = sol.minWindow(s, t);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// T(n) = O(n), S(n) = O(1)
// 4 ms,击败了83.71% 的Java用户, 39.3 MB,击败了64.99% 的Java用户
/*
用一个int[] dict统计t String里面每个字母出现的次数
用两个指针right, left遍历s String
int total表示还需要t里面的字母数
right指针每次遇到一个新的值的时候，dict[ch - 'a']--，
    如果ch还需要出现的次数dict[ch] > 0的话，就把total--
    每次total = 0的时候，left指针就一直往右走，走到total != 0位置，
    这个就是right 指针在当前位置的最小长度
 */
class Solution {
    
    public String minWindow(String s, String t) {
        // corner case
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        
        int[] dict = new int[128]; // ASCII
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            dict[ch]++; // 记录target string进入dict
        }
        
        int start = 0;
        int total = t.length(); // t里面的字母统计 - window里面的符合条件的字母个数
        int min = Integer.MAX_VALUE;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (dict[ch]-- > 0) {
                total--; // 注意这里无论如何，dict[c]都需要--而变化，哪怕if条件不满足导致total不变！！！
            }
            while (total == 0) {
                if (right - left + 1 < min) {
                    min = right - left + 1;
                    start = left;
                }
                // 访问过的char由于左端窗口收缩，又都在收缩的同时dict里加了回来
                char chLeft = s.charAt(left++);
                if (++dict[chLeft] > 0) {
                    total++;
                }
            }
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(start, start + min);
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}