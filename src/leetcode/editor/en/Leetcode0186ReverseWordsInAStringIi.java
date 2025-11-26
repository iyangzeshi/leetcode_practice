//Given an input string , reverse the string word by word. 
//
// Example: 
//
// 
//Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
//Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"] 
//
// Note: 
//
// 
// A word is defined as a sequence of non-space characters. 
// The input string does not contain leading or trailing spaces. 
// The words are always separated by a single space. 
// 
//
// Follow up: Could you do it in-place without allocating extra space? 
// Related Topics String 
// 👍 451 👎 98

package leetcode.editor.en;

// 2020-07-26 12:30:15
// Jesse Yang
public class Leetcode0186ReverseWordsInAStringIi{
    // Java: reverse-words-in-a-string-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0186ReverseWordsInAStringIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // reverse each word and then reverse whole string
        public void reverseWords(char[] s) {
            if (s == null || s.length == 0) {
                return;
            }
            int left = 0;

            int length = s.length;
            for (int i = 0; i <= length; i++) {
                if (i == length || s[i] == ' ') {
                    reverse(s, left, i - 1);
                    left = i;
                }
            }

            reverse(s, 0, length - 1);
        }

        private void reverse(char[] arr, int start, int end) {
            while(start < end) {
                char temp = arr[start];
                arr[start++] = arr[end];
                arr[end--] = temp;
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}