//Write a function that reverses a string. The input string is given as an array
// of characters char[]. 
//
// Do not allocate extra space for another array, you must do this by modifying 
//the input array in-place with O(1) extra memory. 
//
// You may assume all the characters consist of printable ascii characters. 
//
// 
//
// 
// Example 1: 
//
// 
//Input: ["h","e","l","l","o"]
//Output: ["o","l","l","e","h"]
// 
//
// 
// Example 2: 
//
// 
//Input: ["H","a","n","n","a","h"]
//Output: ["h","a","n","n","a","H"]
// 
// 
// 
// Related Topics Two Pointers String 
// 👍 1485 👎 695

package leetcode.editor.en;

// 2020-07-26 12:27:16
// Zeshi Yang
public class Leetcode0344ReverseString{
    // Java: reverse-string
    public static void main(String[] args) {
        Solution sol = new Leetcode0344ReverseString().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void reverseString(char[] s) {
        if (s == null || s.length <=1) {
            return;
        }

        int left = 0;
        int right = s.length - 1;

        while(left < right) {
            swap(s, left++, right--);
        }
    }

    private void swap(char[] s, int left, int right) {
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}