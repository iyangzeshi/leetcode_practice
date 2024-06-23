/**
In an infinite chess board with coordinates from -infinity to +infinity, you 
have a knight at square [0, 0]. 

 A knight has 8 possible moves it can make, as illustrated below. Each move is 
two squares in a cardinal direction, then one square in an orthogonal direction. 

 
 Return the minimum number of steps needed to move the knight to the square [x, 
y]. It is guaranteed the answer exists. 

 
 Example 1: 

 
Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]
 

 Example 2: 

 
Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 

 
 Constraints: 

 
 -300 <= x, y <= 300 
 0 <= |x| + |y| <= 300 
 

 Related Topics Breadth-First Search 👍 1503 👎 405

*/
package leetcode.editor.en;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// 2024-06-16 22:51:01
// Jesse Yang
public class Leetcode1197MinimumKnightMoves{
    // Java: minimum-knight-moves
    public static void main(String[] args) {
        Solution sol = new Leetcode1197MinimumKnightMoves().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    private final int[][] DIRECTIONS = new int[][] {{2, 1},{1, 2},{-1, 2},{-2, 1},{-2,-1},{-1,-2},{1,-2},{2,-1}};
    
    public int minKnightMoves(int x, int y) {
        
        x = Math.abs(x);
        y = Math.abs(y);
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        
        Set<String> visited = new HashSet<>();
        visited.add("0,0");
        
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size -- > 0){
                int[] cur = queue.poll();
                int curX = cur[0];
                int curY = cur[1];
                if (curX == x && curY == y) {
                    return result;
                }
                
                for (int[] d : DIRECTIONS) {
                    int newX = curX + d[0];
                    int newY = curY + d[1];
                    if (!visited.contains(newX + "," + newY)
                            && newX >= -1 && newY >= -1) {
                        queue.offer(new int[] {newX, newY});
                        visited.add(newX + "," + newY);
                    }
                }
            }
            result++;
        }
        return -1;
        
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}