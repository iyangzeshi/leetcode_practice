/**
Given an array of strings words and a width maxWidth, format the text such that
each line has exactly maxWidth characters and is fully (left and right)
justified.

 You should pack your words in a greedy approach; that is, pack as many words
as you can in each line. Pad extra spaces ' ' when necessary so that each line
has exactly maxWidth characters.

 Extra spaces between words should be distributed as evenly as possible. If the
number of spaces on a line does not divide evenly between words, the empty
slots on the left will be assigned more spaces than the slots on the right.

 For the last line of text, it should be left-justified, and no extra space is
inserted between words.

 Note:

 
 A word is defined as a character sequence consisting of non-space characters
only.
 Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.

 The input array words contains at least one word.
 

 
 Example 1:

 
Input: words = ["This", "is", "an", "example", "of", "text", "justification."],
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]

 Example 2:

 
Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16

Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall
be", because the last line must be left-justified instead of fully-justified.
Note that the second line is also left-justified because it contains only one
word.

 Example 3:

 
Input: words = ["Science","is","what","we","understand","well","enough","to",
"explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth
= 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]

 
 Constraints:

 
 1 <= words.length <= 300
 1 <= words[i].length <= 20
 words[i] consists of only English letters and symbols.
 1 <= maxWidth <= 100
 words[i].length <= maxWidth
 

 Related Topics Array String Simulation 👍 4444 👎 5300

*/
package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

// 2025-12-23 17:46:55
// Jesse Yang
public class Leetcode0068TextJustification{
    // Java: text-justification
    public static void main(String[] args) {
        Solution sol = new Leetcode0068TextJustification().new Solution();
        // TO TEST
        String[] words = {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
        int maxWidth = 20;
        List<String> res = sol.fullJustify(words, maxWidth);
        for (String str: res) {
            System.out.println(str);
        }
        /*
        ["Science  is  what we","understand      well","enough to explain to","a  computer.  Art is","everything  else we","do                  "]
        ["Science  is  what we","understand      well","enough to explain to","a  computer.  Art is","everything  else  we","do                  "]
         */
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int len = words.length;
        int i = 0;
        while (i < len) {
            int j = i;
            int rowLength = 0;
            int count = 0;
            while (j < len && rowLength + words[j].length() <= maxWidth) {
                if (j != i) {
                    if (rowLength + words[j].length() + 1 > maxWidth) {
                        break;
                    }
                    rowLength++;
                }
                rowLength += words[j].length();
                count++;
                j++;
            }
            StringBuilder sb = new StringBuilder();
            if (j != len) {
                int dividend = maxWidth - rowLength;
                if (count != 1) {
                    int quotient = dividend / (count - 1);
                    int remainder = dividend % (count - 1);
                    for (int k = i; k < j; k++) {
                        if (k != i) {
                            sb.append(" ");
                            for (int l = 0; l < quotient; l++) {
                                sb.append(" ");
                            }
                            if (remainder != 0) {
                                sb.append(" ");
                                remainder--;
                            }
                        }
                        sb.append(words[k]);
                    }
                } else {
                    sb.append(words[i]);
                    while (sb.length() < maxWidth) {
                        sb.append(" ");
                    }
                }
            } else { // j == len
                for (int k = i; k < j; k++) {
                    if (k != i) {
                        sb.append(" ");
                    }
                    sb.append(words[k]);
                }
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
            }
            i = j;
            res.add(sb.toString());
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
