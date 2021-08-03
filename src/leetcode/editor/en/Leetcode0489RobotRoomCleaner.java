//Given a robot cleaner in a room modeled as a grid. 
//
// Each cell in the grid can be empty or blocked. 
//
// The robot cleaner with 4 given APIs can move forward, turn left or turn right
//. Each turn it made is 90 degrees. 
//
// When it tries to move into a blocked cell, its bumper sensor detects the obst
//acle and it stays on the current cell. 
//
// Design an algorithm to clean the entire room using only the 4 given APIs show
//n below. 
//
// 
//interface Robot {
//  // returns true if next cell is open and robot moves into the cell.
//  // returns false if next cell is obstacle and robot stays on the current cel
//l.
//  boolean move();
//
//  // Robot will stay on the same cell after calling turnLeft/turnRight.
//  // Each turn will be 90 degrees.
//  void turnLeft();
//  void turnRight();
//
//  // Clean the current cell.
//  void clean();
//}
// 
//
// Example: 
//
// 
//Input:
//room = [
//  [1,1,1,1,1,0,1,1],
//  [1,1,1,1,1,0,1,1],
//  [1,0,1,1,1,1,1,1],
//  [0,0,0,1,0,0,0,0],
//  [1,1,1,1,1,1,1,1]
//],
//row = 1,
//col = 3
//
//Explanation:
//All grids in the room are marked by either 0 or 1.
//0 means the cell is blocked, while 1 means the cell is accessible.
//The robot initially starts at the position of row=1, col=3.
//From the top left corner, its position is one row below and three columns righ
//t.
// 
//
// Notes: 
//
// 
// The input is only given to initialize the room and the robot's position inter
//nally. You must solve this problem "blindfolded". In other words, you must contr
//ol the robot using only the mentioned 4 APIs, without knowing the room layout an
//d the initial robot's position. 
// The robot's initial position will always be in an accessible cell. 
// The initial direction of the robot will be facing up. 
// All accessible cells are connected, which means the all cells marked as 1 wil
//l be accessible by the robot. 
// Assume all four edges of the grid are all surrounded by wall. 
// 
// Related Topics Depth-first Search 
// 👍 1193 👎 66

package leetcode.editor.en;

import java.util.HashSet;
import java.util.Set;

// 2020-10-19 22:53:45
// Zeshi Yang
public class Leetcode0489RobotRoomCleaner{
    // Java: robot-room-cleaner
    public static void main(String[] args) {
        Solution sol = new Leetcode0489RobotRoomCleaner().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

class Solution {
	// 注意：这题DIRECTIONS的4个方向必须按照顺时针或者逆时针的顺序来写，不能乱写！！！
	// By default以后都采用顺时针(上右下左)来写！！！
	private final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //顺时针，上右下左
	
	public void cleanRoom(Robot robot) {
		dfs(robot, 0, 0, 0, new HashSet<>());
	}
	
	private void dfs(Robot robot, int row, int col, int dir, Set<String> visited) {
		// base case - success
		robot.clean();
		
		for (int n = 0; n < 4; n++) {
			int curDir = (dir + n) % 4; // 避免出界的方法 → 取余
			int r = row + DIRECTIONS[curDir][0]; // new row
			int c = col + DIRECTIONS[curDir][1]; // new col
			if (visited.add(r + "+" + c) && robot.move()) {
				dfs(robot, r, c, curDir, visited);
				robot.turnLeft();
				robot.turnLeft();
				robot.move();
				robot.turnRight();
				robot.turnRight(); // 左左动右右 → setback()
			}
			robot.turnRight(); // 顺时针转90度遍历下一个，与上面DIRECTIONS里方向的顺序要match!!!
		}
	}
	
}
//leetcode submit region end(Prohibit modification and deletion)
interface Robot {
    
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    boolean move();
    
    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    void turnLeft();
    
    void turnRight();
    
    // Clean the current cell.
    void clean();
}

}