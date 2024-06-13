//You are given an array of logs. Each log is a space-delimited string of words,
// where the first word is the identifier. 
//
// There are two types of logs: 
//
// 
// Letter-logs: All words (except the identifier) consist of lowercase English l
//etters. 
// Digit-logs: All words (except the identifier) consist of digits. 
// 
//
// Reorder these logs so that: 
//
// 
// The letter-logs come before all digit-logs. 
// The letter-logs are sorted lexicographically by their contents. If their cont
//ents are the same, then sort them lexicographically by their identifiers. 
// The digit-logs maintain their relative ordering. 
// 
//
// Return the final order of the logs. 
//
// 
// Example 1: 
//
// 
//Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","le
//t3 art zero"]
//Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig
//2 3 6"]
//Explanation:
//The letter-log contents are all different, so their ordering is "art can", "ar
//t zero", "own kit dig".
//The digit-logs have a relative order of "dig1 8 1 5 1", "dig2 3 6".
// 
//
// Example 2: 
//
// 
//Input: logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act z
//oo"]
//Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
// 
//
// 
// Constraints: 
//
// 
// 1 <= logs.length <= 100 
// 3 <= logs[i].length <= 100 
// All the tokens of logs[i] are separated by a single space. 
// logs[i] is guaranteed to have an identifier and at least one word after the i
//dentifier. 
// 
// Related Topics String 
// 👍 1023 👎 2814

package leetcode.editor.en;

import java.util.Arrays;

// 2021-03-12 15:51:24
// Jesse Yang
public class Leetcode0937ReorderDataInLogFiles{
    // Java: reorder-data-in-log-files
    public static void main(String[] args) {
        Solution sol = new Leetcode0937ReorderDataInLogFiles().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// 5 ms,击败了72.71% 的Java用户, 39.7 MB,击败了44.41% 的Java用户
class Solution {
    
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) {
                    return cmp;
                }
                return split1[0].compareTo(split2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        return logs;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}