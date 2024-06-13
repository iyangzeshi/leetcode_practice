//We are given an array asteroids of integers representing asteroids in a row. 
//
// For each asteroid, the absolute value represents its size, and the sign repre
//sents its direction (positive meaning right, negative meaning left). Each astero
//id moves at the same speed. 
//
// Find out the state of the asteroids after all collisions. If two asteroids me
//et, the smaller one will explode. If both are the same size, both will explode. 
//Two asteroids moving in the same direction will never meet. 
//
// 
// Example 1: 
//
// 
//Input: asteroids = [5,10,-5]
//Output: [5,10]
//Explanation: The 10 and -5 collide resulting in 10.  The 5 and 10 never collid
//e.
// 
//
// Example 2: 
//
// 
//Input: asteroids = [8,-8]
//Output: []
//Explanation: The 8 and -8 collide exploding each other.
// 
//
// Example 3: 
//
// 
//Input: asteroids = [10,2,-5]
//Output: [10]
//Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resul
//ting in 10.
// 
//
// Example 4: 
//
// 
//Input: asteroids = [-2,-1,1,2]
//Output: [-2,-1,1,2]
//Explanation: The -2 and -1 are moving left, while the 1 and 2 are moving right
//. Asteroids moving the same direction never meet, so no asteroids will meet each
// other.
// 
//
// 
// Constraints: 
//
// 
// 1 <= asteroids <= 104 
// -1000 <= asteroids[i] <= 1000 
// asteroids[i] != 0 
// 
// Related Topics Stack 
// 👍 1636 👎 145

package leetcode.editor.en;

import java.util.Arrays;
import java.util.Stack;

// 2020-12-21 14:32:59
// Jesse Yang
public class Leetcode0735AsteroidCollision {
	
	// Java: asteroid-collision
	public static void main(String[] args) {
		Solution sol = new Leetcode0735AsteroidCollision().new Solution();
		// TO TEST
		int[] asteroids = {-2, 1, 1, -1};
		int[] res = sol.asteroidCollision(asteroids);
		System.out.println(Arrays.toString(res));
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int[] asteroidCollision(int[] asteroids) {
        // corner case
        if (asteroids == null || asteroids.length == 0) {
            return new int[0];
        }
        
        Stack<Integer> stack = new Stack<>();
        
        for (int asteroid : asteroids) {
        	boolean explodeCur = false;
	        while (!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
		        if (stack.peek() >= -asteroid) {
			        explodeCur = true;
		        }
	        	if (stack.peek() <= -asteroid) {
			        stack.pop();
		        }
	        	if (explodeCur) {
	        		break;
		        }
	        }
	        if (!explodeCur) {
		        stack.push(asteroid);
	        }
        }
        int[] res = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}