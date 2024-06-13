//Given a string s, remove duplicate letters so that every letter appears once 
//and only once. You must make sure your result is the smallest in lexicographical 
//order among all possible results. 
//
// 
// Example 1: 
//
// 
//Input: s = "bcabc"
//Output: "abc"
// 
//
// Example 2: 
//
// 
//Input: s = "cbacdcbc"
//Output: "acdb"
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 10⁴ 
// s consists of lowercase English letters. 
// 
//
// 
// Note: This question is the same as 1081: https://leetcode.com/problems/
//smallest-subsequence-of-distinct-characters/ 
//
// Related Topics String Stack Greedy Monotonic Stack 👍 8476 👎 606

package leetcode.editor.en;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

// 2024-02-26 21:23:27
// Jesse Yang
public class Leetcode0316RemoveDuplicateLetters{
    // Java: remove-duplicate-letters
    public static void main(String[] args) {
        Solution sol = new Leetcode0316RemoveDuplicateLetters().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// T(n) = O(n), S(n) = O(n)
/*
traverse the String to get char countMap
every time, add a new char to stack
if next char only appear once: add it to the stack
if next char appear more than once:
    add it is smaller than stack.peek(), pop the stack util not smaller or the peek is the
    last occurrence, then add char to the stack
    add it is larger than stack.peek(), add it
 */
class Solution {
    public String removeDuplicateLetters(String s) {
        // corner case
        int len = s.length();
        Map<Character, Integer> lastSeen = new HashMap<>();
        Set<Character> visited = new HashSet<>();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            lastSeen.put(ch, i);
        }
        
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            
            //  we can only try to add c if it's not already in our solution
            if (!visited.contains(ch)) {
                while (!stack.isEmpty()
                        && ch < stack.peek()
                        && lastSeen.get(ch) > i) {
                    stack.pop();
                }
            }
            stack.push(ch);
            visited.add(ch);
            
        }
        StringBuilder sb = new StringBuilder();
        for (Character ch : stack) {
            sb.append(ch);
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    
    public String removeDuplicateLetters(String s) {
        
        Stack<Character> stack = new Stack<>();
        
        Set<Character> visited = new HashSet<>();
        
        // this will let us know if there are any more instances of s[i] left in s
        Map<Character, Integer> last_occurrence = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            last_occurrence.put(s.charAt(i), i);
        }
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // we can only try to add c if it's not already in our solution
            // this is to maintain only one of each character
            if (!visited.contains(ch)) {
                // if the last letter in our solution:
                //     1. exists
                //     2. is greater than c so removing it will make the string smaller
                //     3. it's not the last occurrence
                // we remove it from the solution to keep the solution optimal
                while (!stack.isEmpty()
                        && ch < stack.peek()
                        && last_occurrence.get(stack.peek()) > i) {
                    visited.remove(stack.pop());
                }
                visited.add(ch);
                stack.push(ch);
            }
        }
        StringBuilder sb = new StringBuilder(stack.size());
        for (Character c : stack) {
            sb.append(c.charValue());
        }
        return sb.toString();
    }
    
}
}