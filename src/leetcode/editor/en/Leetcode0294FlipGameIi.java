//You are playing the following Flip Game with your friend: Given a string that 
//contains only these two characters: + and -, you and your friend take turns to f
//lip two consecutive "++" into "--". The game ends when a person can no longer ma
//ke a move and therefore the other person will be the winner. 
//
// Write a function to determine if the starting player can guarantee a win. 
//
// Example: 
//
// 
//Input: s = "++++"
//Output: true 
//Explanation: The starting player can guarantee a win by flipping the middle "+
//+" to become "+--+".
// 
//
// Follow up: 
//Derive your algorithm's runtime complexity. Related Topics Backtracking Minima
//x

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;
/**
  *@ClassName: Leetcode294FlipGameIi
  *@Description:
  *@Author: Jesse Yang
  *@Date: 2020/06/30 周二 18:34
  */
public class Leetcode0294FlipGameIi {
    // Java: flip-game-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0294FlipGameIi().new Solution();
        // TO TEST
        String board = "++++";
        boolean res = sol.canWin(board);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // DFS with pruning, 这个题目没有办法DP
    public boolean canWin(String s) {
        // corner case
        Map<String, Boolean> mem = new HashMap<>();
        return dfs(s.toCharArray(), mem);
    }
    
    /**
     * @param board: the search statement
     * @return: 在当前状态下，先手玩家能不能保证赢
     */
    private boolean dfs(char[] board, Map<String, Boolean> mem) {
        int len = board.length;
        String str = String.valueOf(board);
        // lookup the DP form
        if (mem.containsKey(str)) {
            return mem.get(str);
        }
        
        // base case, failure case
        if (canNotFlip(board)) {
            return false;
        }
        
        for (int i = 0; i < len - 1; i++) {
            if (board[i] == '+' && board[i + 1] == '+') {
                board[i] = '-';
                board[i + 1] = '-';
                boolean res = dfs(board, mem);
                board[i] = '+';
                board[i + 1] = '+';
                if (!res) {
                    mem.put(str, true);
                    return true;
                }
            }
        }
        mem.put(str, false);
        return false;
    }
    
    private boolean canNotFlip(char[] board) {
        int len = board.length;
        for (int i = 0; i < len - 1; i++) {
            if (board[i] == '+' && board[i + 1]== '+') {
                return false;
            }
        }
        return true;
    }
    // time complexity: O(2^n)
}
//leetcode submit region end(Prohibit modification and deletion)
}