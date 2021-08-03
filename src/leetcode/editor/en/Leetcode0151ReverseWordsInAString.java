//Given an input string, reverse the string word by word. 
//
// 
//
// Example 1: 
//
// 
//Input: "the sky is blue"
//Output: "blue is sky the"
// 
//
// Example 2: 
//
// 
//Input: "  hello world!  "
//Output: "world! hello"
//Explanation: Your reversed string should not contain leading or trailing space
//s.
// 
//
// Example 3: 
//
// 
//Input: "a good   example"
//Output: "example good a"
//Explanation: You need to reduce multiple spaces between two words to a single 
//space in the reversed string.
// 
//
// 
//
// Note: 
//
// 
// A word is defined as a sequence of non-space characters. 
// Input string may contain leading or trailing spaces. However, your reversed s
//tring should not contain leading or trailing spaces. 
// You need to reduce multiple spaces between two words to a single space in the
// reversed string. 
// 
//
// 
//
// Follow up: 
//
// For C programmers, try to solve it in-place in O(1) extra space. Related Topi
//cs String 
// 👍 1136 👎 2926

package leetcode.editor.en;

import java.util.*;
// 2020-07-26 12:27:38
// Zeshi Yang
public class Leetcode0151ReverseWordsInAString{
    // Java: reverse-words-in-a-string
    public static void main(String[] args) {
        Solution sol = new Leetcode0151ReverseWordsInAString().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public String reverseWords(String s) {
        s = removeMultiSpace(s);
        if(s == null || s.length() <= 0) {
            return s;
        }

        char[] chars = s.toCharArray();
        int left = 0;
        int right = 0;
        int length = chars.length;

        int i = 0;
        while (i < length) {
            while (i < length && chars[i] != ' ') {
                i++;
            }
            right = i - 1;
            reverse(chars, left, right);
            if (i >= length - 1) {
                break;
            }
            while (chars[i] == ' ') {
                i++;
            }
            left = i;
        }
        reverse(chars, 0, length - 1);
        return  String.valueOf(chars);
    }

    private String removeMultiSpace(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int length = s.length();
        // to find the index of the First word
        while (i < length) {
            if (chars[i] != ' ') {
                break; // left = i,不能放在if里面，否则corner case，如果都是空格的话，left = 0
            }
            i++;
        }
        int left = i;
        int right = i;
        while (i < length) {
            while (i < length && chars[i] != ' ') chars[right++] = chars[i++];
            while (i < length && chars[i] == ' ') i++;
            if (i < length) chars[right++] = chars[i - 1];
        }
        return new String(chars, left, right - left);

    }

    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            swap(chars, left++, right--);
        }
    }

    private void swap(char[] chars, int left, int right) {
        char temp = chars[left];
        chars[left] = chars[right];
        chars[right] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: using library function trim()
class Solution1 {
    
    public String reverseWords(String s) {
        s = s.trim();
        // 在 Java 中，\\ 表示：我要插入一个正则表达式的反斜线
        // s表示space，空格； s+表示匹配多个空格
        List<String> words = Arrays.asList(s.split("\\s+"));
        // join(CharSequence delimiter, CharSequence... elements)
        // String message = String.join("-", "Java", "is", "cool");
        // message returned is: "Java-is-cool"
        Collections.reverse(words); // 要牢记
        return String.join(" ", words);
    }
}

// Solution 2: write the function by ourselves
class Solution2 {

    public String reverseWords(String s) {
        s = removeMultiSpace(s);
        if(s == null || s.length() <= 0) {
            return s;
        }

        char[] chars = s.toCharArray();
        int left = 0;
        int right = 0;
        int length = chars.length;

        int i = 0;
        while (i < length) {
            while (i < length && chars[i] != ' ') {
                i++;
            }
            right = i - 1;
            reverse(chars, left, right);
            if (i >= length - 1) {
                break;
            }
            while (chars[i] == ' ') {
                i++;
            }
            left = i;
        }
        reverse(chars, 0, length - 1);
        return  String.valueOf(chars);
    }

    private String removeMultiSpace(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int length = s.length();
        // to find the index of the First word
        while (i < length) {
            if (chars[i] != ' ') {
                break; // left = i,不能放在if里面，否则corner case，如果都是空格的话，left = 0
            }
            i++;
        }
        int left = i;
        int right = i;
        while (i < length) {
            while (i < length && chars[i] != ' ') chars[right++] = chars[i++];
            while (i < length && chars[i] == ' ') i++;
            if (i < length) chars[right++] = chars[i - 1];
        }
        return new String(chars, left, right - left);

    }

    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            swap(chars, left++, right--);
        }
    }

    private void swap(char[] chars, int left, int right) {
        char temp = chars[left];
        chars[left] = chars[right];
        chars[right] = temp;
    }
}
}