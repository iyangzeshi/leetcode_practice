//On an size x size board, the numbers from 1 to size*size are written boustrophedonically s
//tarting from the bottom left of the board, and alternating direction each row. F
//or example, for a 6 x 6 board, the numbers are written as follows: 
//
// 
//
// 
//
// You start on square 1 of the board (which is always in the last row and first
// column). Each move, starting from square x, consists of the following: 
//
// 
// You choose a destination square S with number x+1, x+2, x+3, x+4, x+5, or x+6
//, provided this number is <= size*size.
//
// 
// (This choice simulates the result of a standard 6-sided die roll: ie., there 
//are always at most 6 destinations, regardless of the size of the board.) 
// 
// 
// If S has a snake or ladder, you move to the destination of that snake or ladd
//er. Otherwise, you move to S. 
// 
//
// A board square on row r and column c has a "snake or ladder" if board[r][c] !
//= -1. The destination of that snake or ladder is board[r][c]. 
//
// Note that you only take a snake or ladder at most once per move: if the desti
//nation to a snake or ladder is the start of another snake or ladder, you do not 
//continue moving. (For example, if the board is `[[4,-1],[-1,3]]`, and on the fir
//st move your destination square is `2`, then you finish your first move at `3`, 
//because you do not continue moving to `4`.) 
//
// Return the least number of moves required to reach square size*size. If it is not p
//ossible, return -1. 
//
// Example 1: 
//
// 
//Input: [
//[-1,-1,-1,-1,-1,-1],
//[-1,-1,-1,-1,-1,-1],
//[-1,-1,-1,-1,-1,-1],
//[-1,35,-1,-1,13,-1],
//[-1,-1,-1,-1,-1,-1],
//[-1,15,-1,-1,-1,-1]]
//Output: 4
//Explanation: 
//At the beginning, you start at square 1 [at row 5, column 0].
//You decide to move to square 2, and must take the ladder to square 15.
//You then decide to move to square 17 (row 3, column 5), and must take the snak
//e to square 13.
//You then decide to move to square 14, and must take the ladder to square 35.
//You then decide to move to square 36, ending the game.
//It can be shown that you need at least 4 moves to reach the size*size-th square, so
//the answer is 4.
// 
//
// Note: 
//
// 
// 2 <= board.length = board[0].length <= 20 
// board[i][j] is between 1 and size*size or is equal to -1.
// The board square with number 1 has no snake or ladder. 
// The board square with number size*size has no snake or ladder.
// 
// Related Topics Breadth-first Search 
// 👍 394 👎 881

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;

// 2020-08-27 21:58:47
// Jesse Yang
public class Leetcode0909SnakesAndLadders {

	// Java: snakes-and-ladders
	public static void main(String[] args) {
		Solution sol = new Leetcode0909SnakesAndLadders().new Solution();
		// TO TEST

		System.out.println();
	}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int snakesAndLadders(int[][] board) {
        // corner case
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return 0;
        }
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];
        int min = n * n;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        int moves = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                if (cur == n * n) {
                    min = Math.min(min, moves);
                }
                convert(cur, n, visited, board, queue);
            }
            moves++;
        }
        return min == n * n ? -1 : min;
    }

    private void convert(int cur, int n, boolean[] visited, int[][] board, Queue<Integer> queue) {
        for (int i = 1; i <= 6; i++) {
            int num = cur + i;
            if (num > n * n) {
                return;
            }
            int row = n - (num - 1) / n - 1;
            int col = (n - row) % 2 == 0 ? n - (num - 1) % n - 1 : (num - 1) % n;
            if (!visited[num]) {
                visited[num] = true;
                if (board[row][col] == -1) {
                    queue.offer(num);
                } else {
                    queue.offer(board[row][col]);
                }
            }
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}