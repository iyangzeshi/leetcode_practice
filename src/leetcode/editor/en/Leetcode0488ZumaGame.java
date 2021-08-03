//Think about Zuma Game. You have a row of balls on the table, colored red(R), y
//ellow(Y), blue(B), green(G), and white(W). You also have several balls in your h
//and. 
//
// Each time, you may choose a ball in your hand, and insert it into the row (in
//cluding the leftmost place and rightmost place). Then, if there is a group of 3 
//or more balls in the same color touching, remove these balls. Keep doing this un
//til no more balls can be removed. 
//
// Find the minimal balls you have to insert to remove all the balls on the tabl
//e. If you cannot remove all the balls, output -1. 
//
// 
// Example 1: 
//
// 
//Input: board = "WRRBBW", hand = "RB"
//Output: -1
//Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW
// 
//
// Example 2: 
//
// 
//Input: board = "WWRRBBWW", hand = "WRBRW"
//Output: 2
//Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty
// 
//
// Example 3: 
//
// 
//Input: board = "G", hand = "GGGGG"
//Output: 2
//Explanation: G -> G[G] -> GG[G] -> empty 
// 
//
// Example 4: 
//
// 
//Input: board = "RBYYBBRRB", hand = "YRBGB"
//Output: 3
//Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B
//] -> empty 
// 
//
// 
// Constraints: 
//
// 
// You may assume that the initial row of balls on the table won’t have any 3 or
// more consecutive balls with the same color. 
// 1 <= board.length <= 16 
// 1 <= hand.length <= 5 
// Both input strings will be non-empty and only contain characters 'R','Y','B',
//'G','W'. 
// 
// Related Topics Depth-first Search

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

public class Leetcode0488ZumaGame {
	
	// Java: zuma-game
	public static void main(String[] args) {
		
		Solution sol = new Leetcode0488ZumaGame().new Solution();
		
		// TO TEST
		String board = "RRWWRRBBRR";
		String hand = "WB";
		
		int res = sol.findMinStep(board, hand);
		System.out.println(res);
		
        /*Solution1 sol1 = new Leetcode488ZumaGame().new Solution1();
        int res1 = sol1.findMinStep(board, hand);
        System.out.println(res1);*/
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int findMinStep(String board, String hand) {
        
        int[] c = new int[128];
        for (char x : hand.toCharArray()) {
            c[x]++;
        }
        return aux(board, c);
    }
    
    private int aux(String s, int[] c) {
        
        if ("".equals(s)) {
            return 0;
        }
        //worst case, every character needs 2 characters; plus one to make it impossible, ;-)
        int res = 2 * s.length() + 1;
        for (int i = 0; i < s.length(); ) {
            int j = i++;
            while (i < s.length() && s.charAt(i) == s.charAt(j)) {
                i++;
            }
            int inc = 3 - i + j;
            if (c[s.charAt(j)] >= inc) {
                int used = Math.max(inc, 0);
                c[s.charAt(j)] -= used;
                int temp = aux(s.substring(0, j) + s.substring(i), c);
                if (temp >= 0) {
                    res = Math.min(res, used + temp);
                }
                c[s.charAt(j)] += used;
            }
        }
        return res == 2 * s.length() + 1 ? -1 : res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Solution1 {
    // DFS 实际游戏中的解法
    // int min;
    public int findMinStep(String board, String hand) {
        
        int maxSteps = hand.length() + 1;
        int min = maxSteps;
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : hand.toCharArray()) {
            if (!map.containsKey(ch)) {
                map.put(ch, 0);
            }
            map.put(ch, map.get(ch) + 1);
        }
        min = dfs(board, map, maxSteps, 0);
        return (min == hand.length() + 1) ? -1 : min;
    }
    
    private int dfs(String board, Map<Character, Integer> hand, int maxSteps, int steps) {
        int len = board.length();
        if (len == 0) {
            return steps;
        }
        if (hand.size() == 0) {
            return maxSteps;
        }
        int min = maxSteps;
        for (int i = 0; i < len; i++) {
            char ch = board.charAt(i);
            Integer count = hand.get(ch);
            if (count == null) {
                continue;
            }
            //combine body code of add 1 ball and 2 ball
            if (i < len - 1 && ch == board.charAt(i + 1)) {
                // add 1 ball
                int newCount = count - 1;
                if (newCount == 0) {
                    hand.remove(ch);
                }
                String newBoard = convert(board, i - 1, i + 2);
                int res = dfs(newBoard, hand, maxSteps, steps + 1);
                min = Math.min(min, res);
                hand.put(ch, count);
            } else if (count >= 2) {
                // add 2 balls
                int newCount = count - 2;
                if (newCount == 0) {
                    hand.remove(ch);
                }
                String newBoard = convert(board, i - 1, i + 1);
                int res = dfs(newBoard, hand, maxSteps, steps + 2);
                min = Math.min(min, res);
                hand.put(ch, count);
            }
        }
        return min;
    }
    
    private String convert(String board, int left, int right) {
        
        int len = board.length();
        while (left >= 0 && right < len) {
            char ch = board.charAt(left);
            int count = 0;
            
            int l = left;
            while (l >= 0 && board.charAt(l) == ch) {
                l--;
                count++;
            }
            int r = right;
            while (r < len && board.charAt(r) == ch) {
                r++;
                count++;
            }
            
            if (count >= 3) {
                left = l;
                right = r;
            } else {
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= left; i++) {
            sb.append(board.charAt(i));
        }
        for (int j = right; j < len; j++) {
            sb.append(board.charAt(j));
        }
        return sb.toString();
    }
    
}
/*
Soluion 1有一个testcase过不了
https://leetcode.com/problems/zuma-game/discuss/97007/Standard-test-program-is-wrong
Input     "RRWWRRBBRR"
          "WB"
Output    -1
Expected   2
RRWWRRBBRR -> RRWWRRBBR[W]R -> RRWWRRBB[B]RWR -> RRWWRRRWR -> RRWWWR -> RRR -> empty
 */
 
class Solution2 {
    // leetcode 讨论区的答案，但是实际游戏中不需要这么操作，每次加东西一定是要消除掉字母的
    public int findMinStep(String board, String hand) {
        
        int[] c = new int[128];
        for (char x : hand.toCharArray()) {
            c[x]++;
        }
        return aux(board, c);
    }
    
    private int aux(String s, int[] c) {
        
        if ("".equals(s)) {
            return 0;
        }
        //worst case, every character needs 2 characters; plus one to make it impossible, ;-)
        int res = 2 * s.length() + 1;
        for (int i = 0; i < s.length(); ) {
            int j = i++;
            while (i < s.length() && s.charAt(i) == s.charAt(j)) {
                i++;
            }
            int inc = 3 - i + j;
            if (c[s.charAt(j)] >= inc) {
                int used = Math.max(inc, 0);
                c[s.charAt(j)] -= used;
                int temp = aux(s.substring(0, j) + s.substring(i), c);
                if (temp >= 0) {
                    res = Math.min(res, used + temp);
                }
                c[s.charAt(j)] += used;
            }
        }
        return res == 2 * s.length() + 1 ? -1 : res;
    }
}
}