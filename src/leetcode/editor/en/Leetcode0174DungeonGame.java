//
// The demons had captured the princess (P) and imprisoned her in the bottom-rig
//ht corner of a dungeon. The dungeon consists of M x size rooms laid out in a 2D gri
//d. Our valiant knight (K) was initially positioned in the top-left room and must
// fight his way through the dungeon to rescue the princess. 
//
// The knight has an initial health point represented by a positive integer. If 
//at any point his health point drops to 0 or below, he dies immediately. 
//
// Some of the rooms are guarded by demons, so the knight loses health (negative
// integers) upon entering these rooms; other rooms are either empty (0's) or cont
//ain magic orbs that increase the knight's health (positive integers). 
//
// In order to reach the princess as quickly as possible, the knight decides to 
//move only rightward or downward in each step. 
//
// 
//
// Write a function to determine the knight's minimum initial health so that he 
//is able to rescue the princess. 
//
// For example, given the dungeon below, the initial health of the knight must b
//e at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN. 
//
// 
// 
// 
// -2 (K) 
// -3 
// 3 
// 
// 
// -5 
// -10 
// 1 
// 
// 
// 10 
// 30 
// -5 (P) 
// 
// 
// 
//
// 
//
// Note: 
//
// 
// The knight's health has no upper bound. 
// Any room can contain threats or power-ups, even the first room the knight ent
//ers and the bottom-right room where the princess is imprisoned. 
// 
// Related Topics Binary Search Dynamic Programming 
// 👍 1922 👎 45

package leetcode.editor.en;

// 2020-10-21 18:08:23
// Jesse Yang
public class Leetcode0174DungeonGame{
    // Java: dungeon-game
    public static void main(String[] args) {
        Solution sol = new Leetcode0174DungeonGame().new Solution();
        // TO TEST
        int[][] dungeon = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };
        int res = sol.calculateMinimumHP(dungeon);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int rows = dungeon.length;
        int cols = dungeon[0].length;
        // dp[i][j] is the shortest health point needed to reach the princess ( >= 0 at any place)
        int[][] dp = new int[rows][cols];
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                if (i == rows - 1 && j == cols - 1) {
                    dp[i][j] = Math.max(0, -dungeon[i][j]); // important 到达公主那一格的时候也得是活的
                } else {
                    int down = i == rows - 1 ? Integer.MAX_VALUE : dp[i + 1][j];
                    int right = j == cols - 1 ? Integer.MAX_VALUE : dp[i][j + 1];
                    dp[i][j] = Math.max(0, Math.min(down, right) - dungeon[i][j]); // 比0大，否则就挂了
                }
            }
        }
        return dp[0][0] + 1; // > 0 at any place, so it must be added by 1
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}