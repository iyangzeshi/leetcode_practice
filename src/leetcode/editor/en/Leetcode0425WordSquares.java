//Given a set of words (without duplicates), find all word squares you can build
// from them. 
//
// A sequence of words forms a valid word square if the kth row and column read 
//the exact same string, where 0 ≤ k < max(numRows, numColumns). 
//
// For example, the word sequence ["ball","area","lead","lady"] forms a word squ
//are because each word reads the same both horizontally and vertically. 
//
// 
//b a l l
//a r e a
//l e a d
//l a d y
// 
//
// Note: 
// 
// There are at least 1 and at most 1000 words. 
// All words will have the exact same length. 
// Word length is at least 1 and at most 5. 
// Each word contains only lowercase English alphabet a-z. 
// 
// 
//
// Example 1:
// 
//Input:
//["area","lead","wall","lady","ball"]
//
//Output:
//[
//  [ "wall",
//    "area",
//    "lead",
//    "lady"
//  ],
//  [ "ball",
//    "area",
//    "lead",
//    "lady"
//  ]
//]
//
//Explanation:
//The output consists of two word squares. The order of output does not matter (
//just the order of words in each word square matters).
// 
// 
//
// Example 2:
// 
//Input:
//["abat","baba","atan","atal"]
//
//Output:
//[
//  [ "baba",
//    "abat",
//    "baba",
//    "atan"
//  ],
//  [ "baba",
//    "abat",
//    "baba",
//    "atal"
//  ]
//]
//
//Explanation:
//The output consists of two word squares. The order of output does not matter (
//just the order of words in each word square matters).
// 
// Related Topics Backtracking Trie 
// 👍 695 👎 44

package leetcode.editor.en;

import java.util.List;

// 2021-02-21 15:50:00
// Zeshi Yang
public class Leetcode0425WordSquares{
    // Java: word-squares
    public static void main(String[] args) {
        Solution sol = new Leetcode0425WordSquares().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> wordSquares(String[] words) {
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}